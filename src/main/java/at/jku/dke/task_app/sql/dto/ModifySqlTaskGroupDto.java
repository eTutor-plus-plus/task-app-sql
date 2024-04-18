package at.jku.dke.task_app.sql.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * This class represents a data transfer object for modifying a sql task group.
 *
 * @param ddlStatements         The DDL statements for creating the tables.
 * @param diagnoseDmlStatements The DML statements for inserting the diagnose data.
 * @param submitDmlStatements   The DML statements for inserting the submission data.
 */
public record ModifySqlTaskGroupDto(
    @NotNull String ddlStatements,
    @NotNull String diagnoseDmlStatements,
    @NotNull String submitDmlStatements) implements Serializable {
}
