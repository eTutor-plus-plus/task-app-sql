package at.jku.dke.task_app.sql.evaluation;

import at.jku.dke.etutor.task_app.dto.GradingDto;
import at.jku.dke.etutor.task_app.dto.SubmitSubmissionDto;
import at.jku.dke.task_app.sql.data.entities.TaskType;
import at.jku.dke.task_app.sql.data.repositories.SqlRaTaskRepository;
import at.jku.dke.task_app.sql.dto.SubmissionDto;
import at.jku.dke.task_app.sql.evaluation.analyzer.SqlAnalysis;
import at.jku.dke.task_app.sql.evaluation.analyzer.SqlAnalyzer;
import at.jku.dke.task_app.sql.evaluation.analyzer.SqlAnalyzerConfig;
import at.jku.dke.task_app.sql.evaluation.grading.SqlGrading;
import at.jku.dke.task_app.sql.evaluation.reporting.SqlReport;
import at.jku.dke.task_app.sql.ra2sql.RelationalAlgebraConverter;
import at.jku.dke.task_app.sql.ra2sql.RelationalAlgebraParseException;
import at.jku.dke.task_app.sql.services.SqlSchemaService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Locale;

import static at.jku.dke.etutor.task_app.dto.SubmissionMode.SUBMIT;

/**
 * Service that evaluates submissions.
 */
@Service
public class EvaluationService {
    private static final Logger LOG = LoggerFactory.getLogger(EvaluationService.class);

    private final SqlRaTaskRepository taskRepository;
    private final SqlAnalyzer analyzer;
    private final MessageSource messageSource;

    /**
     * Creates a new instance of class {@link EvaluationService}.
     *
     * @param taskRepository The task repository.
     * @param messageSource  The message source.
     * @param analyzer       The SQL analyzer.
     */
    public EvaluationService(SqlRaTaskRepository taskRepository, SqlAnalyzer analyzer, MessageSource messageSource) {
        this.taskRepository = taskRepository;
        this.analyzer = analyzer;
        this.messageSource = messageSource;
    }

    /**
     * Evaluates a input.
     *
     * @param submission The input to evaluate.
     * @return The evaluation result.
     */
    @Transactional
    public GradingDto evaluate(SubmitSubmissionDto<SubmissionDto> submission) {
        // find task and task group
        var task = this.taskRepository.findById(submission.taskId()).orElseThrow(() -> new EntityNotFoundException("Task " + submission.taskId() + " does not exist."));
        var taskGroup = task.getTaskGroup();

        // prepare
        LOG.info("Evaluating input for task {} with mode {} and feedback-level {}", submission.taskId(), submission.mode(), submission.feedbackLevel());
        var locale = Locale.of(submission.language());
        var schema = taskGroup.getSchemaName() + (submission.mode().equals(SUBMIT) ? SqlSchemaService.SUFFIX_SUBMISSION : SqlSchemaService.SUFFIX_DIAGNOSE);
        var config = new SqlAnalyzerConfig(task.getSolution(), schema);
        String query = submission.submission().input();
        if (task.getType() == TaskType.SQL) {
            switch (submission.mode()) {
                case RUN:
                    config.addCriterion(SqlEvaluationCriterion.CORRECT_SYNTAX);
                    break;
                case DIAGNOSE:
                case SUBMIT:
                    config.addCriterion(SqlEvaluationCriterion.CORRECT_SYNTAX);
                    config.addCriterion(SqlEvaluationCriterion.CARTESIAN_PRODUCT);
                    config.addCriterion(SqlEvaluationCriterion.CORRECT_COLUMNS);
                    config.addCriterion(SqlEvaluationCriterion.CORRECT_ORDER);
                    config.addCriterion(SqlEvaluationCriterion.CORRECT_TUPLES);
                    break;
            }
        } else if (task.getType() == TaskType.RELALG) {
            try {
                query = RelationalAlgebraConverter.convertToSql(taskGroup.getSchemaDescription(), query);
            } catch (RelationalAlgebraParseException ex) {
                var analysis = new SqlAnalysis();
                analysis.setAnalysisException(ex);
                var report = new SqlReport(this.messageSource, locale, submission.mode(), submission.feedbackLevel(), analysis, new SqlGrading(task, analysis));
                return new GradingDto(task.getMaxPoints(), BigDecimal.ZERO, report.getGeneralFeedback(), report.getCriteria());
            }
            catch (Exception ex) {
                var analysis = new SqlAnalysis();
                analysis.setAnalysisException(ex);
                var report = new SqlReport(this.messageSource, locale, submission.mode(), submission.feedbackLevel(), analysis, new SqlGrading(task, analysis));
                return new GradingDto(task.getMaxPoints(), BigDecimal.ZERO, report.getGeneralFeedback(), report.getCriteria());
            }

            switch (submission.mode()) {
                case RUN:
                    config.addCriterion(SqlEvaluationCriterion.CORRECT_SYNTAX);
                    break;
                case DIAGNOSE:
                case SUBMIT:
                    config.addCriterion(SqlEvaluationCriterion.CORRECT_SYNTAX);
                    config.addCriterion(SqlEvaluationCriterion.CARTESIAN_PRODUCT);
                    config.addCriterion(SqlEvaluationCriterion.CORRECT_COLUMNS);
                    config.addCriterion(SqlEvaluationCriterion.CORRECT_TUPLES);
                    break;
            }
        }

        // analyze, grade, feedback
        var analysis = this.analyzer.analyze(query, config);
        var grading = new SqlGrading(task, analysis);
        var report = new SqlReport(this.messageSource, locale, submission.mode(), submission.feedbackLevel(), analysis, grading);
        return new GradingDto(task.getMaxPoints(), grading.getPoints(), report.getGeneralFeedback(), report.getCriteria());
    }
}
