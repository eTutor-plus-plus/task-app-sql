package at.jku.dke.task_app.sql.dto;

import jakarta.validation.constraints.NotNull;

/**
 * This class represents a data transfer object for submitting a solution.
 *
 * @param input The user input.
 */
public record SubmissionDto(@NotNull String input) {
}
