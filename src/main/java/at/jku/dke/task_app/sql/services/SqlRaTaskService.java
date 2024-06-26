package at.jku.dke.task_app.sql.services;

import at.jku.dke.etutor.task_app.dto.ModifyTaskDto;
import at.jku.dke.etutor.task_app.dto.TaskModificationResponseDto;
import at.jku.dke.etutor.task_app.services.BaseTaskInGroupService;
import at.jku.dke.task_app.sql.data.entities.SqlRaTask;
import at.jku.dke.task_app.sql.data.entities.SqlRaTaskGroup;
import at.jku.dke.task_app.sql.data.entities.TaskType;
import at.jku.dke.task_app.sql.data.repositories.SqlRaTaskGroupRepository;
import at.jku.dke.task_app.sql.data.repositories.SqlRaTaskRepository;
import at.jku.dke.task_app.sql.dto.ModifySqlTaskDto;
import at.jku.dke.task_app.sql.ra2sql.RelationalAlgebraConverter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * This class provides methods for managing {@link SqlRaTask}s of type SQL.
 */
@Service
public class SqlRaTaskService extends BaseTaskInGroupService<SqlRaTask, SqlRaTaskGroup, ModifySqlTaskDto> {

    /**
     * Creates a new instance of class {@link SqlRaTaskService}.
     *
     * @param repository          The task repository.
     * @param taskGroupRepository The task group repository.
     */
    public SqlRaTaskService(SqlRaTaskRepository repository, SqlRaTaskGroupRepository taskGroupRepository) {
        super(repository, taskGroupRepository);
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

        // TODO: test query
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
        // TODO: test query
    }

    @Override
    protected TaskModificationResponseDto mapToReturnData(SqlRaTask task, boolean create) {
        return new TaskModificationResponseDto(null, null);
    }
}
