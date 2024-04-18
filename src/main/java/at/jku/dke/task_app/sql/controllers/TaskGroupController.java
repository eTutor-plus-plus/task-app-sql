package at.jku.dke.task_app.sql.controllers;

import at.jku.dke.etutor.task_app.controllers.BaseTaskGroupController;
import at.jku.dke.task_app.sql.data.entities.SqlRaTaskGroup;
import at.jku.dke.task_app.sql.dto.ModifySqlTaskGroupDto;
import at.jku.dke.task_app.sql.dto.TaskGroupDto;
import at.jku.dke.task_app.sql.services.SqlRaTaskGroupService;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing {@link SqlRaTaskGroup}s.
 */
@RestController
public class TaskGroupController extends BaseTaskGroupController<SqlRaTaskGroup, TaskGroupDto, ModifySqlTaskGroupDto> {

    /**
     * Creates a new instance of class {@link TaskGroupController}.
     *
     * @param taskGroupService The task group service.
     */
    public TaskGroupController(SqlRaTaskGroupService taskGroupService) {
        super(taskGroupService);
    }

    @Override
    protected TaskGroupDto mapToDto(SqlRaTaskGroup taskGroup) {
        return new TaskGroupDto(taskGroup.getDdlStatements(), taskGroup.getDiagnoseDmlStatements(), taskGroup.getSubmitDmlStatements());
    }

}
