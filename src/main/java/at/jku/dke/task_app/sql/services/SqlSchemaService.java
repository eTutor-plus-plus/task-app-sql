package at.jku.dke.task_app.sql.services;

import at.jku.dke.task_app.sql.config.JdbcConnectionParameters;
import at.jku.dke.task_app.sql.dto.SchemaInfoDto;
import at.jku.dke.task_app.sql.dto.TableDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for managing task group schemas.
 */
public class SqlSchemaService implements AutoCloseable {
    private static final Logger LOG = LoggerFactory.getLogger(SqlSchemaService.class);

    /**
     * The suffix for the submission-schema.
     */
    public static final String SUFFIX_SUBMISSION = "_submission";

    /**
     * The suffix for the diagnose-schema.
     */
    public static final String SUFFIX_DIAGNOSE = "_diagnose";

    private final JdbcConnectionParameters parameters;
    private final Connection connection;

    /**
     * Creates a new instance of class {@link SqlSchemaService}.
     *
     * @param parameters The JDBC connection parameters.
     * @throws SQLException If the connection could not be established.
     */
    public SqlSchemaService(JdbcConnectionParameters parameters) throws SQLException {
        this.parameters = parameters;
        this.connection = this.getConnection();
        this.connection.setAutoCommit(false);
    }

    /**
     * Creates a new database connection.
     *
     * @return The connection.
     * @throws SQLException If the connection could not be established.
     */
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.parameters.url(), this.parameters.admin().username(), this.parameters.admin().password());
    }

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
    public SchemaInfoDto create(String schemaPrefix, String ddl, String dmlDiagnose, String dmlSubmit) throws SQLException {
        try {
            this.deleteSchemas(schemaPrefix);
            this.createSchemas(schemaPrefix);
            this.createTables(schemaPrefix, ddl);
            this.fillTables(schemaPrefix, dmlDiagnose, true);
            this.fillTables(schemaPrefix, dmlSubmit, false);
            this.commit();

            // build table info
            return getSchemaInfoDto(schemaPrefix + SUFFIX_DIAGNOSE);
        } catch (SQLException ex) {
            this.rollback();
            throw ex;
        }
    }

    /**
     * Gets information about the tables in the specified schema.
     *
     * @param schema The schema name.
     * @return The schema information.
     * @throws SQLException If the information could not be retrieved.
     */
    SchemaInfoDto getSchemaInfoDto(String schema) throws SQLException {
        schema = schema.toLowerCase();

        List<TableDto> tables = new ArrayList<>();
        var rsTables = this.connection.getMetaData().getTables(null, schema, null, new String[]{"TABLE"});
        while (rsTables.next()) {
            String tableName = rsTables.getString("TABLE_NAME");

            // load columns
            List<TableDto.ColumnDto> columns = new ArrayList<>();
            var rsColumns = this.connection.getMetaData().getColumns(null, schema, tableName, null);
            while (rsColumns.next()) {
                String columnName = rsColumns.getString("COLUMN_NAME");
                String columnType = rsColumns.getString("TYPE_NAME");
                boolean nullable = rsColumns.getInt("NULLABLE") != DatabaseMetaData.columnNoNulls;
                boolean pk = false;

                // load primary keys
                var rsPk = this.connection.getMetaData().getPrimaryKeys(null, schema, tableName);
                while (rsPk.next()) {
                    if (rsPk.getString("COLUMN_NAME").equals(rsColumns.getString("COLUMN_NAME"))) {
                        pk = true;
                        break;
                    }
                }

                // add column to list
                columns.add(new TableDto.ColumnDto(columnName, columnType, nullable, pk));
            }

            // load foreign keys
            List<TableDto.ForeignKeyDto> foreignKeys = new ArrayList<>();
            var rsFk = this.connection.getMetaData().getImportedKeys(null, schema, tableName);
            while (rsFk.next()) {
                String fkName = rsFk.getString("FKTABLE_NAME");
                String fkColumnName = rsFk.getString("FKCOLUMN_NAME");
                String pkTableName = rsFk.getString("PKTABLE_NAME");
                String pkColumnName = rsFk.getString("PKCOLUMN_NAME");
                foreignKeys.add(new TableDto.ForeignKeyDto(fkName, fkColumnName, pkTableName, pkColumnName));
            }

            tables.add(new TableDto(tableName, columns, foreignKeys, null));
        }
        return new SchemaInfoDto(tables);
    }

    //#region --- SCHEMA ---

    /**
     * Creates the schemas with the specified prefix. Does not commit the transaction.
     * <p>
     * Uses the schema-prefix and creates the diagnose- and submission version of it.
     *
     * @param schemaPrefix The schema prefix.
     * @throws SQLException If the schemas could not be created.
     */
    void createSchemas(String schemaPrefix) throws SQLException {
        LOG.info("Creating schemas with prefix {}", schemaPrefix);
        try {
            this.createSchema(schemaPrefix + SUFFIX_SUBMISSION);
            this.grantUsage(schemaPrefix + SUFFIX_SUBMISSION);

            this.createSchema(schemaPrefix + SUFFIX_DIAGNOSE);
            this.grantUsage(schemaPrefix + SUFFIX_DIAGNOSE);

        } catch (SQLException ex) {
            LOG.error(String.format("Could not create schemas with prefix %s", schemaPrefix), ex);
            throw ex;
        }
    }

    /**
     * Creates the specified schema if it does not exist. Does not commit the transaction.
     *
     * @param schemaName The schema name.
     * @throws SQLException If the schema could not be created.
     */
    private void createSchema(String schemaName) throws SQLException {
        final String query = String.format("CREATE SCHEMA IF NOT EXISTS %s;", schemaName);
        LOG.info("Creating schema {} with {}", schemaName, query);
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(query);
        } catch (SQLException ex) {
            LOG.error(String.format("Could not create schema %s", schemaName), ex);
            throw ex;
        }
    }

    /**
     * Grants usage for the executor to specified schema. Does not commit the transaction.
     *
     * @param schemaName The schema name.
     * @throws SQLException If the permission could not be granted.
     */
    private void grantUsage(String schemaName) throws SQLException {
        final String query = String.format("GRANT USAGE ON SCHEMA %s TO %s;", schemaName, this.parameters.executor().username());
        LOG.info("Granting usage to schema {} with {}", schemaName, query);
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(query);
        } catch (SQLException ex) {
            LOG.error(String.format("Could not grant usage to schema %s", schemaName), ex);
            throw ex;
        }
    }

    /**
     * Deletes the schemas with the specified prefix (if they exist). Does not commit the transaction.
     * <p>
     * Uses the schema-prefix and deletes the diagnose- and submission version of it.
     *
     * @param schemaPrefix The schema prefix.
     * @throws SQLException If the schemas could not be deleted.
     */
    void deleteSchemas(String schemaPrefix) throws SQLException {
        LOG.info("Deleting schemas with prefix {}", schemaPrefix);
        try {
            this.deleteSchema(schemaPrefix + SUFFIX_SUBMISSION);
            this.deleteSchema(schemaPrefix + SUFFIX_DIAGNOSE);
        } catch (SQLException ex) {
            LOG.error(String.format("Could not delete schemas with prefix %s", schemaPrefix), ex);
            throw ex;
        }
    }

    /**
     * Deletes the specified schema if it exists. Does not commit the transaction.
     *
     * @param schemaName The schema name.
     * @throws SQLException If the schema could not be deleted.
     */
    private void deleteSchema(String schemaName) throws SQLException {
        final String query = String.format("DROP SCHEMA IF EXISTS %s CASCADE;", schemaName);
        LOG.info("Deleting schema {} with {}", schemaName, query);
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(query);
        } catch (SQLException ex) {
            LOG.error(String.format("Could not delete schema %s", schemaName), ex);
            throw ex;
        }
    }

    //#endregion

    //#region --- TABLES ---

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
    void createTables(String schemaPrefix, String ddlStatements) throws SQLException {
        LOG.info("Creating tables in schemas with prefix {}", schemaPrefix);
        try {
            this.execute(schemaPrefix + SUFFIX_DIAGNOSE, ddlStatements);
            this.grantReadAccess(schemaPrefix + SUFFIX_DIAGNOSE);

            this.execute(schemaPrefix + SUFFIX_SUBMISSION, ddlStatements);
            this.grantReadAccess(schemaPrefix + SUFFIX_SUBMISSION);
        } catch (SQLException ex) {
            LOG.error(String.format("Could not create tables for schemas with prefix %s", schemaPrefix), ex);
            throw ex;
        }
    }

    /**
     * Fills the schema tables using the specified DML-statements. Does not commit the transaction.
     *
     * @param schemaPrefix   The schema prefix.
     * @param dmlStatements  The DML statements.
     * @param diagnoseSchema {@code true} if the diagnose-schema should be filled; {@code false} if the submission-schema should be filled.
     */
    void fillTables(String schemaPrefix, String dmlStatements, boolean diagnoseSchema) throws SQLException {
        var schema = schemaPrefix + (diagnoseSchema ? SUFFIX_DIAGNOSE : SUFFIX_SUBMISSION);
        LOG.info("Filling tables in schema  {}", schema);
        try {
            this.execute(schema, dmlStatements);
        } catch (SQLException ex) {
            LOG.error(String.format("Could not fill tables for schema %s", schema), ex);
            throw ex;
        }
    }

    /**
     * Grants read access for the executor to the currently existing tables in the specified schema. Does not commit the transaction.
     *
     * @param schemaName The schema name.
     * @throws SQLException If the permission could not be granted.
     */
    private void grantReadAccess(String schemaName) throws SQLException {
        final String query = String.format("GRANT SELECT ON ALL TABLES IN SCHEMA %s TO %s;", schemaName, this.parameters.executor().username());
        LOG.info("Granting read access to schema {} with {}", schemaName, query);
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(query);
        } catch (SQLException ex) {
            LOG.error(String.format("Could not grant read access to schema %s", schemaName), ex);
            throw ex;
        }
    }
    //#endregion

    //#region --- HELPERS ---

    /**
     * Executes the specified query in the specified schema. Does not commit the transaction.
     *
     * @param schemaName The schema name.
     * @param query      The query to execute.
     * @throws SQLException If the query caused an error.
     */
    private void execute(String schemaName, String query) throws SQLException {
        if (query == null)
            return;

        query = query.trim();
        if (query.isBlank() || query.length() < 3)
            return;

        query = "SET search_path TO " + schemaName + ";\n" + query + "SET search_path TO public;";
        LOG.debug("Executing query in schema {}: {}", schemaName, query);
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(query);
        } catch (SQLException ex) {
            LOG.error(String.format("Could not execute statements in schema %s", schemaName), ex);
            throw ex;
        }
    }

    //#endregion

    /**
     * Commits the transaction.
     *
     * @throws SQLException If the commit failed.
     */
    void commit() throws SQLException {
        this.connection.commit();
    }

    /**
     * Rolls back the transaction.
     *
     * @throws SQLException If the rollback failed.
     */
    void rollback() throws SQLException {
        this.connection.rollback();
    }

    /**
     * Rolls back the transaction and closes the connection.
     *
     * @throws SQLException If the rollback or the connection closing failed.
     */
    @Override
    public void close() throws SQLException {
        this.connection.rollback();
        this.connection.close();
    }
}
