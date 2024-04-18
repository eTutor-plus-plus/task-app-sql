package at.jku.dke.task_app.sql.data.entities;

import at.jku.dke.etutor.task_app.data.entities.BaseTaskGroup;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 * Represents a SQL task group.
 */
@Entity
@Table(name = "task_group")
public class SqlRaTaskGroup extends BaseTaskGroup {
    @Column(name = "ddl_statements", nullable = false)
    private String ddlStatements;

    @Column(name = "diagnose_dml_statements", nullable = false)
    private String diagnoseDmlStatements;

    @Column(name = "submit_dml_statements", nullable = false)
    private String submitDmlStatements;

    @Column(name = "schema_name", nullable = false, length = 200)
    private String schemaName;

    @Column(name = "schema_description")
    @JdbcTypeCode(SqlTypes.JSON)
    private String schemaDescription;

    /**
     * Creates a new instance of class {@link SqlRaTaskGroup}.
     */
    public SqlRaTaskGroup() {
    }

    /**
     * Gets the DDL statements.
     *
     * @return The DDL statements.
     */
    public String getDdlStatements() {
        return ddlStatements;
    }

    /**
     * Sets the DDL statements.
     *
     * @param ddlStatements The DDL statements.
     */
    public void setDdlStatements(String ddlStatements) {
        this.ddlStatements = ddlStatements;
    }

    /**
     * Gets the diagnose DML statements.
     *
     * @return The diagnose DML statements.
     */
    public String getDiagnoseDmlStatements() {
        return diagnoseDmlStatements;
    }

    /**
     * Sets the diagnose DML statements.
     *
     * @param diagnoseDmlStatements The diagnose DML statements.
     */
    public void setDiagnoseDmlStatements(String diagnoseDmlStatements) {
        this.diagnoseDmlStatements = diagnoseDmlStatements;
    }

    /**
     * Gets the submit DML statements.
     *
     * @return The submit DML statements.
     */
    public String getSubmitDmlStatements() {
        return submitDmlStatements;
    }

    /**
     * Sets the submit DML statements.
     *
     * @param submitDmlStatements The submit DML statements.
     */
    public void setSubmitDmlStatements(String submitDmlStatements) {
        this.submitDmlStatements = submitDmlStatements;
    }

    /**
     * Gets the schema name.
     *
     * @return The schema name.
     */
    public String getSchemaName() {
        return schemaName;
    }

    /**
     * Sets the schema name.
     *
     * @param schemaName The schema name.
     */
    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    /**
     * Gets the schema description.
     *
     * @return The schema description.
     */
    public String getSchemaDescription() {
        return schemaDescription;
    }

    /**
     * Sets the schema description.
     *
     * @param schemaDescription The schema description.
     */
    public void setSchemaDescription(String schemaDescription) {
        this.schemaDescription = schemaDescription;
    }
}
