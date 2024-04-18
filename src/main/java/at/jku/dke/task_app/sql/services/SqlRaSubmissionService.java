package at.jku.dke.task_app.sql.services;

import at.jku.dke.etutor.task_app.dto.GradingDto;
import at.jku.dke.etutor.task_app.dto.SubmitSubmissionDto;
import at.jku.dke.etutor.task_app.services.BaseSubmissionService;
import at.jku.dke.task_app.sql.data.entities.SqlRaSubmission;
import at.jku.dke.task_app.sql.data.entities.SqlRaTask;
import at.jku.dke.task_app.sql.data.repositories.SqlRaSubmissionRepository;
import at.jku.dke.task_app.sql.data.repositories.SqlRaTaskRepository;
import at.jku.dke.task_app.sql.dto.SubmissionDto;
import at.jku.dke.task_app.sql.evaluation.EvaluationService;
import org.springframework.stereotype.Service;

/**
 * This class provides methods for managing {@link SqlRaSubmission}s.
 */
@Service
public class SqlRaSubmissionService extends BaseSubmissionService<SqlRaTask, SqlRaSubmission, SubmissionDto> {

    private final EvaluationService evaluationService;

    /**
     * Creates a new instance of class {@link SqlRaSubmissionService}.
     *
     * @param submissionRepository The input repository.
     * @param taskRepository       The task repository.
     * @param evaluationService    The evaluation service.
     */
    public SqlRaSubmissionService(SqlRaSubmissionRepository submissionRepository, SqlRaTaskRepository taskRepository, EvaluationService evaluationService) {
        super(submissionRepository, taskRepository);
        this.evaluationService = evaluationService;
    }

    @Override
    protected SqlRaSubmission createSubmissionEntity(SubmitSubmissionDto<SubmissionDto> submitSubmissionDto) {
        return new SqlRaSubmission(submitSubmissionDto.submission().input());
    }

    @Override
    protected GradingDto evaluate(SubmitSubmissionDto<SubmissionDto> submitSubmissionDto) {
        return this.evaluationService.evaluate(submitSubmissionDto);
    }

    @Override
    protected SubmissionDto mapSubmissionToSubmissionData(SqlRaSubmission submission) {
        return new SubmissionDto(submission.getSubmission());
    }

}
