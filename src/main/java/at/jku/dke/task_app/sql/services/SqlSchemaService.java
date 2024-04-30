package at.jku.dke.task_app.sql.services;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;

import java.sql.SQLException;

public interface SqlSchemaService extends AutoCloseable {
    /**
     * The suffix for the submission-schema.
     */
    String SUFFIX_SUBMISSION = "_submission";
    /**
     * The suffix for the diagnose-schema.
     */
    String SUFFIX_DIAGNOSE = "_diagnose";

    /**
     * Creates the schemas, tables and fills the tables with the specified DDL- and DML-statements.
     *
     * @param schemaPrefix The schema prefix.
     * @param ddl          The DDL statements.
     * @param dmlDiagnose  The DML statements for the diagnose-schema.
     * @param dmlSubmit    The DML statements for the submission-schema.
     * @return The created tables.
     * @throws SQLException If the schemas, tables or data could not be created.
     */
    SchemaInfoDto create(String schemaPrefix, String ddl, String dmlDiagnose, String dmlSubmit) throws SQLException;

    /**
     * Gets information about the tables in the specified schema.
     *
     * @param schema The schema name.
     * @return The schema information.
     * @throws SQLException If the information could not be retrieved.
     */
    SchemaInfoDto getSchemaInfoDto(String schema) throws SQLException;

    /**
     * Creates the schemas with the specified prefix. Does not commit the transaction.
     * <p>
     * Uses the schema-prefix and creates the diagnose- and submission version of it.
     *
     * @param schemaPrefix The schema prefix.
     * @throws SQLException If the schemas could not be created.
     */
    void createSchemas(String schemaPrefix) throws SQLException;

    /**
     * Deletes the schemas with the specified prefix (if they exist). Does not commit the transaction.
     * <p>
     * Uses the schema-prefix and deletes the diagnose- and submission version of it.
     *
     * @param schemaPrefix The schema prefix.
     * @throws SQLException If the schemas could not be deleted.
     */
    void deleteSchemas(String schemaPrefix) throws SQLException;

    /**
     * Creates the tables using the specified DDL-statements in the specified schema prefix.
     * Executes in the diagnose- and submission-database.
     * Grants SELECT permission to the executor.
     * Does not commit the transaction.
     *
     * @param schemaPrefix  The schema prefix.
     * @param ddlStatements The DDL statements.
     * @throws SQLException If the tables could not be created.
     */
    void createTables(String schemaPrefix, String ddlStatements) throws SQLException;

    /**
     * Fills the schema tables using the specified DML-statements. Does not commit the transaction.
     *
     * @param schemaPrefix   The schema prefix.
     * @param dmlStatements  The DML statements.
     * @param diagnoseSchema {@code true} if the diagnose-schema should be filled; {@code false} if the submission-schema should be filled.
     */
    void fillTables(String schemaPrefix, String dmlStatements, boolean diagnoseSchema) throws SQLException;

    /**
     * Commits the transaction.
     *
     * @throws SQLException If the commit failed.
     */
    void commit() throws SQLException;

    /**
     * Rolls back the transaction.
     *
     * @throws SQLException If the rollback failed.
     */
    void rollback() throws SQLException;

    /**
     * Rolls back the transaction and closes the connection.
     *
     * @throws SQLException If the rollback or the connection closing failed.
     */
    @Override
    void close() throws SQLException;
}
