package at.jku.dke.task_app.sql.services;

import at.jku.dke.etutor.task_app.dto.ModifyTaskDto;
import at.jku.dke.etutor.task_app.dto.SubmissionMode;
import at.jku.dke.etutor.task_app.dto.TaskModificationResponseDto;
import at.jku.dke.etutor.task_app.services.BaseTaskInGroupService;
import at.jku.dke.task_app.sql.data.entities.SqlRaTask;
import at.jku.dke.task_app.sql.data.entities.SqlRaTaskGroup;
import at.jku.dke.task_app.sql.data.entities.TaskType;
import at.jku.dke.task_app.sql.data.repositories.SqlRaTaskGroupRepository;
import at.jku.dke.task_app.sql.data.repositories.SqlRaTaskRepository;
import at.jku.dke.task_app.sql.dto.ModifySqlTaskDto;
import at.jku.dke.task_app.sql.evaluation.EvaluationService;
import at.jku.dke.task_app.sql.ra2sql.RelationalAlgebraConverter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

/**
 * This class provides methods for managing {@link SqlRaTask}s of type SQL.
 */
@Service
public class SqlRaTaskService extends BaseTaskInGroupService<SqlRaTask, SqlRaTaskGroup, ModifySqlTaskDto> {

    private final EvaluationService evaluationService;

    /**
     * Creates a new instance of class {@link SqlRaTaskService}.
     *
     * @param repository          The task repository.
     * @param taskGroupRepository The task group repository.
     * @param evaluationService   The evaluation service.
     */
    public SqlRaTaskService(SqlRaTaskRepository repository, SqlRaTaskGroupRepository taskGroupRepository, EvaluationService evaluationService) {
        super(repository, taskGroupRepository);
        this.evaluationService = evaluationService;
    }

    @Override
    protected SqlRaTask createTask(long id, ModifyTaskDto<ModifySqlTaskDto> modifyTaskDto) {
        if (!modifyTaskDto.taskType().equals("sql") && !modifyTaskDto.taskType().equals("relalg"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid task type.");

        var task = new SqlRaTask();
        task.setId(id);
        task.setType(modifyTaskDto.taskType().equals("sql") ? TaskType.SQL : TaskType.RELALG);
        task.setWrongOrderPenalty(modifyTaskDto.additionalData().wrongOrderPenalty());
        task.setSuperfluousColumnsPenalty(modifyTaskDto.additionalData().superfluousColumnsPenalty());
        if (task.getType() == TaskType.SQL) {
            task.setSolution(modifyTaskDto.additionalData().solution());
        } else {
            task.setRelationalAlgebraSolution(modifyTaskDto.additionalData().solution());
            var tg = this.taskGroupRepository.findById(modifyTaskDto.taskGroupId()).orElseThrow();
            task.setSolution(RelationalAlgebraConverter.convertToSql(tg.getSchemaDescription(), task.getRelationalAlgebraSolution()));
        }

        return task;
    }

    @Override
    protected void updateTask(SqlRaTask task, ModifyTaskDto<ModifySqlTaskDto> modifyTaskDto) {
        if (!modifyTaskDto.taskType().equals("sql") && !modifyTaskDto.taskType().equals("relalg"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid task type.");
        if (!((modifyTaskDto.taskType().equals("sql") && task.getType().equals(TaskType.SQL)) || (!modifyTaskDto.taskType().equals("sql") && task.getType().equals(TaskType.RELALG)))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task type cannot be changed.");
        }

        task.setWrongOrderPenalty(modifyTaskDto.additionalData().wrongOrderPenalty());
        task.setSuperfluousColumnsPenalty(modifyTaskDto.additionalData().superfluousColumnsPenalty());
        if (task.getType() == TaskType.SQL) {
            task.setSolution(modifyTaskDto.additionalData().solution());
        } else {
            boolean raChanged = !task.getRelationalAlgebraSolution().equals(modifyTaskDto.additionalData().solution());
            task.setRelationalAlgebraSolution(modifyTaskDto.additionalData().solution());
            if (raChanged) {
                var tg = this.taskGroupRepository.findById(modifyTaskDto.taskGroupId()).orElseThrow();
                task.setSolution(RelationalAlgebraConverter.convertToSql(tg.getSchemaDescription(), task.getRelationalAlgebraSolution()));
            }
        }
    }

    @Override
    protected TaskModificationResponseDto mapToReturnData(SqlRaTask task, boolean create) {
        return new TaskModificationResponseDto(null, null);
    }

    @Override
    protected void afterCreate(SqlRaTask task, ModifyTaskDto<ModifySqlTaskDto> dto) {
        this.afterUpdate(task, dto);
    }

    @Override
    protected void afterUpdate(SqlRaTask task, ModifyTaskDto<ModifySqlTaskDto> dto) {
        try {
            var result = this.evaluationService.execute(task.getId(), SubmissionMode.DIAGNOSE, task.getSolution());
            if (result.getTuples().isEmpty())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Solution returns empty result for submission mode DIAGNOSE");

            result = this.evaluationService.execute(task.getId(), SubmissionMode.SUBMIT, task.getSolution());
            if (result.getTuples().isEmpty())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Solution returns empty result for submission mode SUBMIT");
        } catch (SQLException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}
