package at.jku.dke.task_app.sql.controllers;

import at.jku.dke.etutor.task_app.controllers.BaseSubmissionController;
import at.jku.dke.task_app.sql.data.entities.SqlRaSubmission;
import at.jku.dke.task_app.sql.dto.SubmissionDto;
import at.jku.dke.task_app.sql.services.SqlRaSubmissionService;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing {@link SqlRaSubmission}s.
 */
@RestController
public class SubmissionController extends BaseSubmissionController<SubmissionDto> {
    /**
     * Creates a new instance of class {@link SubmissionController}.
     *
     * @param submissionService The input service.
     */
    public SubmissionController(SqlRaSubmissionService submissionService) {
        super(submissionService);
    }
}
