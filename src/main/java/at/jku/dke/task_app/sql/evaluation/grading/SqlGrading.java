package at.jku.dke.task_app.sql.evaluation.grading;

import at.jku.dke.task_app.sql.data.entities.SqlRaTask;
import at.jku.dke.task_app.sql.evaluation.SqlEvaluationCriterion;
import at.jku.dke.task_app.sql.evaluation.analyzer.ColumnCriterionAnalysis;
import at.jku.dke.task_app.sql.evaluation.analyzer.OrderCriterionAnalysis;
import at.jku.dke.task_app.sql.evaluation.analyzer.SqlAnalysis;
import at.jku.dke.task_app.sql.evaluation.analyzer.SqlCriterionAnalysis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Grades the SQL analysis.
 */
public class SqlGrading {

    private final SqlRaTask task;
    private final SqlAnalysis analysis;
    private final BigDecimal points;
    private final boolean isCorrect;
    private final List<GradingEntry> details;

    /**
     * Creates a new instance of class {@link SqlGrading}.
     *
     * @param task     The task containing the information about the grading penalties.
     * @param analysis The analysis object containing information about the analyzed query.
     */
    public SqlGrading(SqlRaTask task, SqlAnalysis analysis) {
        this.task = task;
        this.analysis = analysis;
        this.isCorrect = analysis.getAnalysisException() == null &&
                         analysis.getCriterionAnalysis().values().stream().allMatch(SqlCriterionAnalysis::isCriterionSatisfied);
        if (this.isCorrect) {
            this.points = task.getMaxPoints();
            this.details = List.of();
        } else if (analysis.getAnalysisException() != null) {
            this.points = BigDecimal.ZERO;
            this.details = List.of();
        } else {
            this.details = calculateMinusPoints(analysis, task);
            BigDecimal deduction = details.stream().map(GradingEntry::minusPoints).reduce(BigDecimal.ZERO, BigDecimal::add);
            this.points = task.getMaxPoints().subtract(deduction).max(BigDecimal.ZERO);
        }
    }

    /**
     * Gets the graded task.
     *
     * @return The task.
     */
    public SqlRaTask getTask() {
        return task;
    }

    /**
     * Gets the graded analysis.
     *
     * @return The analysis.
     */
    public SqlAnalysis getAnalysis() {
        return analysis;
    }

    /**
     * Returns whether the solution is fully correct.
     *
     * @return {@code true} if the solution is correct; {@code false} otherwise.
     */
    public boolean isCorrect() {
        return isCorrect;
    }

    /**
     * Gets the details of the grading.
     *
     * @return The details.
     */
    public List<GradingEntry> getDetails() {
        return Collections.unmodifiableList(details);
    }

    /**
     * Gets the detail of the specified criterion.
     *
     * @param criterion The criterion for which to retrieve the grading entry.
     * @return The grading entry, if available.
     */
    public Optional<GradingEntry> getDetail(SqlEvaluationCriterion criterion) {
        return this.details.stream().filter(x -> x.criterion() == criterion).findFirst();
    }

    /**
     * Gets the total points.
     *
     * @return The points.
     */
    public BigDecimal getPoints() {
        return this.points;
    }

    /**
     * Calculates all minus points with regard to the configured penalties.
     *
     * @param analysis The analysis object containing information about the analyzed query and detected errors.
     * @param task     The task containing the information about the grading penalties.
     * @return The calculated minus points.
     */
    private static List<GradingEntry> calculateMinusPoints(SqlAnalysis analysis, SqlRaTask task) {
        List<GradingEntry> entries = new ArrayList<>();

        for (var entry : analysis.getCriterionAnalysis().entrySet()) {
            if (entry.getKey() == SqlEvaluationCriterion.CORRECT_COLUMNS && entry.getValue() instanceof ColumnCriterionAnalysis cca && !cca.getSuperfluousColumns().isEmpty())
                entries.add(new GradingEntry(SqlEvaluationCriterion.CORRECT_COLUMNS, task.getSuperfluousColumnsPenalty().longValue() < 0 ?
                    task.getMaxPoints() :
                    task.getSuperfluousColumnsPenalty()));
            else if (entry.getKey() == SqlEvaluationCriterion.CORRECT_ORDER && entry.getValue() instanceof OrderCriterionAnalysis oca && oca.isOrderingIncorrect())
                entries.add(new GradingEntry(SqlEvaluationCriterion.CORRECT_ORDER, task.getWrongOrderPenalty().longValue() < 0 ?
                    task.getMaxPoints() :
                    task.getWrongOrderPenalty()));
            else
                entries.add(new GradingEntry(entry.getKey(), task.getMaxPoints()));
        }

        return entries;
    }
}
