package at.jku.dke.task_app.sql.controllers;

import at.jku.dke.etutor.task_app.controllers.BaseTaskController;
import at.jku.dke.task_app.sql.data.entities.SqlRaTask;
import at.jku.dke.task_app.sql.data.entities.TaskType;
import at.jku.dke.task_app.sql.dto.ModifySqlTaskDto;
import at.jku.dke.task_app.sql.dto.TaskDto;
import at.jku.dke.task_app.sql.services.SqlRaTaskService;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * Controller for managing {@link SqlRaTask}s of type SQL.
 */
@RestController
public class TaskController extends BaseTaskController<SqlRaTask, TaskDto, ModifySqlTaskDto> {

    /**
     * Creates a new instance of class {@link TaskController}.
     *
     * @param taskService The task service.
     */
    public TaskController(SqlRaTaskService taskService) {
        super(taskService);
    }

    @Override
    protected TaskDto mapToDto(SqlRaTask task) {
        return new TaskDto(
            task.getType().equals(TaskType.SQL) ? task.getSolution() : task.getRelationalAlgebraSolution(),
            task.getType().equals(TaskType.SQL) ? null : task.getSolution(),
            task.getWrongOrderPenalty(),
            task.getSuperfluousColumnsPenalty());
    }

    @Override
    protected URI createDetailsUri(long id) {
        return URI.create("/api/task/sql/" + id);
    }

}
