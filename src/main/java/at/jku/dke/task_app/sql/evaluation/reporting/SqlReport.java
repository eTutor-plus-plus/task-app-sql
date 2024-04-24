package at.jku.dke.task_app.sql.evaluation.reporting;

import at.jku.dke.etutor.task_app.dto.CriterionDto;
import at.jku.dke.etutor.task_app.dto.SubmissionMode;
import at.jku.dke.task_app.sql.evaluation.SqlEvaluationCriterion;
import at.jku.dke.task_app.sql.evaluation.analyzer.*;
import at.jku.dke.task_app.sql.evaluation.grading.SqlGrading;
import org.springframework.context.MessageSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Class for generating a report for the SQL evaluation.
 */
public class SqlReport {
    private final MessageSource messageSource;
    private final Locale locale;
    private final SubmissionMode mode;
    private final int feedbackLevel;
    private final SqlAnalysis analysis;
    private final SqlGrading grading;

    /**
     * Creates a new instance of class {@link SqlReport}.
     *
     * @param messageSource The message source.
     * @param locale        The this.locale.
     * @param mode          The submission mode.
     * @param feedbackLevel The feedback level.
     * @param analysis      The analysis.
     * @param grading       The grading.
     */
    public SqlReport(MessageSource messageSource, Locale locale, SubmissionMode mode, int feedbackLevel, SqlAnalysis analysis, SqlGrading grading) {
        if (feedbackLevel < 0 || feedbackLevel > 3)
            throw new IllegalArgumentException("feedbackLevel must be between 0 and 3");

        this.messageSource = messageSource;
        this.locale = locale;
        this.mode = mode;
        this.feedbackLevel = mode == SubmissionMode.SUBMIT ? Math.max(1, feedbackLevel) : feedbackLevel;
        this.analysis = analysis;
        this.grading = grading;
    }

    /**
     * Gets the general feedback.
     *
     * @return The general feedback.
     */
    public String getGeneralFeedback() {
        if (this.analysis.getAnalysisException() != null)
            return this.messageSource.getMessage("executionFailed", null, this.locale);

        if (this.mode == SubmissionMode.RUN) {
            var syntax = this.analysis.getCriterionAnalysis(SqlEvaluationCriterion.CORRECT_SYNTAX);
            if (syntax instanceof SyntaxCriterionAnalysis sca && !sca.isCriterionSatisfied())
                return this.messageSource.getMessage("syntaxError", null, this.locale);

            return this.messageSource.getMessage("noSyntaxError", null, this.locale);
        }

        return this.grading.isCorrect() ?
            this.messageSource.getMessage(this.mode == SubmissionMode.SUBMIT ? "correct" : "possiblyCorrect", null, this.locale) :
            this.messageSource.getMessage("incorrect", null, this.locale);
    }

    /**
     * Gets the detailed feedback.
     *
     * @return The detailed feedback.
     */
    public List<CriterionDto> getCriteria() {
        var criteria = new ArrayList<CriterionDto>();

        // Syntax
        Throwable syntaxException = analysis.getAnalysisException();
        var criterionAnalysis = this.analysis.getCriterionAnalysis(SqlEvaluationCriterion.CORRECT_SYNTAX);
        if (criterionAnalysis instanceof SyntaxCriterionAnalysis sca) {
            if (sca.isCriterionSatisfied()) {
                criteria.add(new CriterionDto(
                    this.messageSource.getMessage("criterium.syntax", null, this.locale),
                    null,
                    true,
                    this.messageSource.getMessage("criterium.syntax.valid", null, this.locale)));
            } else {
                syntaxException = sca.getException();
                criteria.add(new CriterionDto(
                    this.messageSource.getMessage("criterium.syntax", null, this.locale),
                    null,
                    false,
                    this.messageSource.getMessage("criterium.syntax.invalid", null, this.locale)));
            }
        }

        // Other criteria
        this.createCartesianProductCriterion().ifPresent(criteria::add);
        this.createColumnsCriterion().ifPresent(criteria::add);
        this.createOrderCriterion().ifPresent(criteria::add);
        this.createTupleCriterion().ifPresent(criteria::add);

        // Query result
        this.createQueryResultCriterion(syntaxException).ifPresent(criteria::add);
        return criteria;
    }

    private Optional<CriterionDto> createCartesianProductCriterion() {
        var criterionAnalysis = this.analysis.getCriterionAnalysis(SqlEvaluationCriterion.CARTESIAN_PRODUCT);
        if (this.feedbackLevel <= 0 || this.mode == SubmissionMode.RUN || !(criterionAnalysis instanceof CartesianProductCriterionAnalysis ca) || ca.isCriterionSatisfied())
            return Optional.empty();

        if (ca.getException() != null) {
            return Optional.of(new CriterionDto(
                this.messageSource.getMessage("criterium.cartesian", null, this.locale),
                null,
                false,
                ca.getException().getMessage()));
        }

        return Optional.of(new CriterionDto(
            this.messageSource.getMessage("criterium.cartesian", null, this.locale),
            null,
            false,
            this.messageSource.getMessage("criterium.cartesian.invalid", null, this.locale)));
    }

    private Optional<CriterionDto> createColumnsCriterion() {
        var criterionAnalysis = this.analysis.getCriterionAnalysis(SqlEvaluationCriterion.CORRECT_COLUMNS);
        if (this.feedbackLevel <= 0 || this.mode == SubmissionMode.RUN || !(criterionAnalysis instanceof ColumnCriterionAnalysis ca) || ca.isCriterionSatisfied())
            return Optional.empty();

        if (ca.getException() != null) {
            return Optional.of(new CriterionDto(
                this.messageSource.getMessage("criterium.columns", null, this.locale),
                null,
                false,
                ca.getException().getMessage()));
        }

        if (this.mode == SubmissionMode.SUBMIT) {
            var entry = this.grading.getDetail(SqlEvaluationCriterion.CORRECT_COLUMNS);
            return Optional.of(new CriterionDto(
                this.messageSource.getMessage("criterium.columns", null, this.locale),
                entry.map(e -> e.minusPoints().negate()).orElse(null),
                false,
                this.messageSource.getMessage("criterium.columns.invalid", null, this.locale)
            ));
        }

        // from here on only mode DIAGNOSE
        StringBuilder message = new StringBuilder();
        switch (this.feedbackLevel) {
            case 1:
                if (ca.hasMissingColumns())
                    message.append(this.messageSource.getMessage("criterium.columns.missing", null, this.locale)).append("<br>");
                if (ca.hasSuperfluousColumns())
                    message.append(this.messageSource.getMessage("criterium.columns.superfluous", null, this.locale));
                break;
            case 2:
                if (ca.hasMissingColumns())
                    message.append(this.messageSource.getMessage("criterium.columns.missingCount", new Object[]{ca.getMissingColumns().size()}, this.locale)).append("<br>");
                if (ca.hasSuperfluousColumns())
                    message.append(this.messageSource.getMessage("criterium.columns.superfluousCount", new Object[]{ca.getSuperfluousColumns().size()}, this.locale));
                break;
            case 3:
                if (ca.hasMissingColumns())
                    message.append(this.messageSource.getMessage("criterium.columns.missingList", new Object[]{String.join(", ", ca.getMissingColumns())}, this.locale)).append("<br>");
                if (ca.hasSuperfluousColumns())
                    message.append(this.messageSource.getMessage("criterium.columns.superfluousList", new Object[]{String.join(", ", ca.getSuperfluousColumns())}, this.locale));
                break;
        }

        return Optional.of(new CriterionDto(
            this.messageSource.getMessage("criterium.columns", null, this.locale),
            null,
            false,
            message.toString()
        ));
    }

    private Optional<CriterionDto> createOrderCriterion() {
        var criterionAnalysis = this.analysis.getCriterionAnalysis(SqlEvaluationCriterion.CORRECT_ORDER);
        if (this.feedbackLevel <= 0 || this.mode == SubmissionMode.RUN || !(criterionAnalysis instanceof OrderCriterionAnalysis ca) || ca.isCriterionSatisfied())
            return Optional.empty();

        if (ca.getException() != null) {
            return Optional.of(new CriterionDto(
                this.messageSource.getMessage("criterium.order", null, this.locale),
                null,
                false,
                ca.getException().getMessage()));
        }

        var grade = this.grading.getDetail(SqlEvaluationCriterion.CORRECT_ORDER);
        return Optional.of(new CriterionDto(
            this.messageSource.getMessage("criterium.order", null, this.locale),
            this.mode == SubmissionMode.SUBMIT ? grade.map(x -> x.minusPoints().negate()).orElse(null) : null,
            false,
            this.messageSource.getMessage("criterium.order.invalid", null, this.locale)));
    }

    private Optional<CriterionDto> createTupleCriterion() {
        var criterionAnalysis = this.analysis.getCriterionAnalysis(SqlEvaluationCriterion.CORRECT_TUPLES);
        if (this.feedbackLevel <= 0 || this.mode == SubmissionMode.RUN || !(criterionAnalysis instanceof TupleCriterionAnalysis ca) || ca.isCriterionSatisfied())
            return Optional.empty();

        if (ca.getException() != null) {
            return Optional.of(new CriterionDto(
                this.messageSource.getMessage("criterium.tuples", null, this.locale),
                null,
                false,
                ca.getException().getMessage()));
        }

        if (this.mode == SubmissionMode.SUBMIT) {
            return Optional.of(new CriterionDto(
                this.messageSource.getMessage("criterium.tuples", null, this.locale),
                null,
                false,
                this.messageSource.getMessage("criterium.tuples.invalid", null, this.locale)
            ));
        }

        // from here on only mode DIAGNOSE
        StringBuilder message = new StringBuilder();
        switch (this.feedbackLevel) {
            case 1:
                if (ca.hasMissingTuples())
                    message.append(this.messageSource.getMessage("criterium.tuples.missing", null, this.locale)).append("<br>");
                if (ca.hasSuperfluousTuples())
                    message.append(this.messageSource.getMessage("criterium.tuples.superfluous", null, this.locale));
                break;
            case 2:
                if (ca.hasMissingTuples())
                    message.append(this.messageSource.getMessage("criterium.tuples.missingCount", new Object[]{ca.getMissingTuples().size()}, this.locale)).append("<br>");
                if (ca.hasSuperfluousTuples())
                    message.append(this.messageSource.getMessage("criterium.tuples.superfluousCount", new Object[]{ca.getSuperfluousTuples().size()}, this.locale));
                break;
            case 3:
                if (ca.hasMissingTuples())
                    message.append(this.messageSource.getMessage("criterium.tuples.missingList", new Object[]{ca.getMissingTuples().size(), createTable(ca.getColumnLabels(), ca.getMissingTuples())}, this.locale)).append("<br>");
                if (ca.hasSuperfluousTuples())
                    message.append(this.messageSource.getMessage("criterium.tuples.superfluousList", new Object[]{ca.getSuperfluousTuples().size(), createTable(ca.getColumnLabels(), ca.getSuperfluousTuples())}, this.locale));
                break;
        }

        return Optional.of(new CriterionDto(
            this.messageSource.getMessage("criterium.tuples", null, this.locale),
            null,
            false,
            message.toString()
        ));
    }

    private Optional<CriterionDto> createQueryResultCriterion(Throwable syntaxException) {
        if (syntaxException == null) {
            if (this.mode == SubmissionMode.SUBMIT || this.analysis.getQueryResult() == null)
                return Optional.empty();

            return Optional.of(new CriterionDto(
                this.messageSource.getMessage("criterium.result", null, this.locale),
                null,
                this.grading.isCorrect(),
                createTable(this.analysis.getQueryResult().getColumnNames(), this.analysis.getQueryResult().getTuples())
            ));
        }

        String message = "<strong>" + syntaxException.getMessage() + "</strong>";
        if (syntaxException instanceof SQLException sex)
            message += "<br>Code: " + sex.getErrorCode() + ", State: " + sex.getSQLState();

        return Optional.of(new CriterionDto(
            this.messageSource.getMessage("criterium.result", null, this.locale),
            null,
            false,
            message
        ));
    }

    private static String createTable(List<String> columnHeaders, List<List<String>> tuples) {
        StringBuilder builder = new StringBuilder("<table border=\"1\"><thead><tr>");
        columnHeaders.forEach(c -> builder.append("<th>").append(c).append("</th>"));
        builder.append("</tr></head><tbody>");
        tuples.forEach(t -> {
            builder.append("<tr>");
            t.forEach(d -> builder.append("<td>").append(d).append("</td>"));
            builder.append("</tr>");
        });
        return builder.append("</tbody></table>").toString();
    }
}
