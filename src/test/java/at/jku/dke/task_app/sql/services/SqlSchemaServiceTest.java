package at.jku.dke.task_app.sql.services;

import at.jku.dke.task_app.sql.AppPostgresContainer;
import at.jku.dke.task_app.sql.config.JdbcConnectionParameters;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.images.builder.Transferable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SqlSchemaServiceTest {
    final static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(AppPostgresContainer.IMAGE_NAME)
        .withUsername("test_admin")
        .withPassword("test_password")
        .withDatabaseName("test_db")
        .withCopyToContainer(Transferable.of("""
            #!/bin/bash
            set -e
            POSTGRES="psql -v ON_ERROR_STOP=1 --username test_admin --dbname test_db"

            $POSTGRES <<-EOSQL
            CREATE USER test_executor PASSWORD 'test_executor_pwd';
            EOSQL
            """), "/docker-entrypoint-initdb.d/create_user.sh");

    @BeforeAll
    static void beforeAl() {
        postgresContainer.start();
    }

    @AfterAll
    static void afterAll() {
        postgresContainer.stop();
    }

    private SqlSchemaService schemaService;

    @BeforeEach
    void setUp() throws SQLException {
        schemaService = new SqlSchemaServiceImpl(new JdbcConnectionParameters(
            postgresContainer.getJdbcUrl(),
            new JdbcConnectionParameters.UserCredentials(postgresContainer.getUsername(), postgresContainer.getPassword()),
            new JdbcConnectionParameters.UserCredentials("test_executor", "test_executor_pwd"),
            1, 10000, 1000
        ));
    }

    //#region --- create ---
    @Test
    void testCreateInvalidSchemaName() {
        // Arrange
        final String schemaPrefix = "m@.3";

        // Act & Assert
        assertThrows(SQLException.class, () -> schemaService.create(schemaPrefix, "", "", ""));
    }
    //#endregion

    //#region --- createSchemas ---
    @Test
    void testCreateSchema() throws SQLException {
        // Arrange
        final String schemaPrefix = "testCreateSchema";

        // Act
        schemaService.createSchemas(schemaPrefix);
        schemaService.commit();

        // Assert
        boolean submissionFound = false;
        boolean diagnoseFound = false;

        try (Connection con = DriverManager.getConnection(postgresContainer.getJdbcUrl(), postgresContainer.getUsername(), postgresContainer.getPassword())) {
            ResultSet schemas = con.getMetaData().getSchemas();
            while (schemas.next()) {
                String schemaName = schemas.getString(1);
                if (schemaName.equalsIgnoreCase(schemaPrefix + SqlSchemaService.SUFFIX_SUBMISSION))
                    submissionFound = true;
                else if (schemaName.equalsIgnoreCase(schemaPrefix + SqlSchemaService.SUFFIX_DIAGNOSE))
                    diagnoseFound = true;
            }
        }

        assertTrue(submissionFound);
        assertTrue(diagnoseFound);
    }

    @Test
    void testCreateSchemaInvalidName() {
        // Arrange
        final String schemaPrefix = "m@.3";

        // Act & Assert
        assertThrows(SQLException.class, () -> schemaService.createSchemas(schemaPrefix));
    }
    //#endregion

    //#region --- createTables ---
    @Test
    void testCreateTables() throws SQLException {
        // Arrange
        final String schemaPrefix = "testCreateTables";
        schemaService.createSchemas(schemaPrefix);

        final String statements = """
            CREATE TABLE dept (
                id INT PRIMARY KEY,
                name VARCHAR(50) NOT NULL
            );

            CREATE TABLE emp (
                id INT PRIMARY KEY,
                name VARCHAR(50) NOT NULL,
                dept_id INT NOT NULL,
                FOREIGN KEY (dept_id) REFERENCES dept(id)
            );
            """;

        // Act
        schemaService.createTables(schemaPrefix, statements);
        schemaService.commit();

        // Assert
        try (Connection con = DriverManager.getConnection(postgresContainer.getJdbcUrl(), postgresContainer.getUsername(), postgresContainer.getPassword())) {
            // find department in submission schema
            var rs = con.getMetaData().getTables(null, (schemaPrefix + SqlSchemaService.SUFFIX_SUBMISSION).toLowerCase(), "dept", null);
            assertTrue(rs.next());

            // find employee in submission schema
            rs = con.getMetaData().getTables(null, (schemaPrefix + SqlSchemaService.SUFFIX_SUBMISSION).toLowerCase(), "emp", null);
            assertTrue(rs.next());

            // find department in diagnose schema
            rs = con.getMetaData().getTables(null, (schemaPrefix + SqlSchemaService.SUFFIX_DIAGNOSE).toLowerCase(), "dept", null);
            assertTrue(rs.next());

            // find employee in diagnose schema
            rs = con.getMetaData().getTables(null, (schemaPrefix + SqlSchemaService.SUFFIX_DIAGNOSE).toLowerCase(), "emp", null);
            assertTrue(rs.next());
        }
    }

    @Test
    void testCreateTablesInvalidDdl() throws SQLException {
        // Arrange
        final String schemaPrefix = "testCreateTablesInvalidDdl";
        schemaService.createSchemas(schemaPrefix);

        final String statements = """
            CREATE TABLE dept (
                id INT PRIMARY
            );
            """;

        // Act & Assert
        assertThrows(SQLException.class, () -> schemaService.createTables(schemaPrefix, statements));
    }
    //#endregion

    //#region --- fillTables ---
    @Test
    void testFillTables() throws SQLException {
        // Arrange
        final String schemaPrefix = "testFillTables";
        final String ddl_statements = """
            CREATE TABLE dept (
                id INT PRIMARY KEY,
                name VARCHAR(50) NOT NULL
            );

            CREATE TABLE emp (
                id INT PRIMARY KEY,
                name VARCHAR(50) NOT NULL,
                dept_id INT NOT NULL,
                FOREIGN KEY (dept_id) REFERENCES dept(id)
            );
            """;
        schemaService.createSchemas(schemaPrefix);
        schemaService.createTables(schemaPrefix, ddl_statements);

        // Act
        schemaService.fillTables(schemaPrefix, """
            INSERT INTO dept (id, name) VALUES (1, 'IT');
            INSERT INTO dept (id, name) VALUES (2, 'HR');
            INSERT INTO dept (id, name) VALUES (3, 'Sales');

            INSERT INTO emp (id, name, dept_id) VALUES (1, 'Max', 1);
            INSERT INTO emp (id, name, dept_id) VALUES (2, 'Peter', 1);
            INSERT INTO emp (id, name, dept_id) VALUES (3, 'Hans', 2);
            INSERT INTO emp (id, name, dept_id) VALUES (4, 'Klaus', 3);
            INSERT INTO emp (id, name, dept_id) VALUES (5, 'Franz', 3);
            """, false);
        schemaService.fillTables(schemaPrefix, """
            INSERT INTO dept (id, name) VALUES (3, 'IT');
            INSERT INTO dept (id, name) VALUES (4, 'HR');
            INSERT INTO dept (id, name) VALUES (5, 'Sales');

            INSERT INTO emp (id, name, dept_id) VALUES (1, 'Max', 3);
            INSERT INTO emp (id, name, dept_id) VALUES (2, 'Peter', 3);
            INSERT INTO emp (id, name, dept_id) VALUES (3, 'Hans', 4);
            INSERT INTO emp (id, name, dept_id) VALUES (4, 'Klaus', 5);
            INSERT INTO emp (id, name, dept_id) VALUES (5, 'Franz', 5);
            """, true);
        schemaService.commit();

        // Assert
        try (Connection con = DriverManager.getConnection(postgresContainer.getJdbcUrl(), postgresContainer.getUsername(), postgresContainer.getPassword())) {
            // find department in submission schema
            var rs = con.createStatement().executeQuery("SELECT * FROM " + schemaPrefix + SqlSchemaService.SUFFIX_SUBMISSION + ".dept ORDER BY id");
            assertTrue(rs.next());
            assertEquals(1, rs.getInt("id"));
            assertEquals("IT", rs.getString("name"));
            assertTrue(rs.next());
            assertEquals(2, rs.getInt("id"));
            assertEquals("HR", rs.getString("name"));
            assertTrue(rs.next());
            assertEquals(3, rs.getInt("id"));
            assertEquals("Sales", rs.getString("name"));
            assertFalse(rs.next());

            // find employee in submission schema
            rs = con.createStatement().executeQuery("SELECT * FROM " + schemaPrefix + SqlSchemaService.SUFFIX_SUBMISSION + ".emp ORDER BY id");
            assertTrue(rs.next());
            assertEquals(1, rs.getInt("id"));
            assertEquals("Max", rs.getString("name"));
            assertEquals(1, rs.getInt("dept_id"));
            assertTrue(rs.next());
            assertEquals(2, rs.getInt("id"));
            assertEquals("Peter", rs.getString("name"));
            assertEquals(1, rs.getInt("dept_id"));
            assertTrue(rs.next());
            assertEquals(3, rs.getInt("id"));
            assertEquals("Hans", rs.getString("name"));
            assertEquals(2, rs.getInt("dept_id"));
            assertTrue(rs.next());
            assertEquals(4, rs.getInt("id"));
            assertEquals("Klaus", rs.getString("name"));
            assertEquals(3, rs.getInt("dept_id"));
            assertTrue(rs.next());
            assertEquals(5, rs.getInt("id"));
            assertEquals("Franz", rs.getString("name"));
            assertEquals(3, rs.getInt("dept_id"));
            assertFalse(rs.next());

            // find department in diagnose schema
            rs = con.createStatement().executeQuery("SELECT * FROM " + schemaPrefix + SqlSchemaService.SUFFIX_DIAGNOSE + ".dept ORDER BY id");
            assertTrue(rs.next());
            assertEquals(3, rs.getInt("id"));
            assertEquals("IT", rs.getString("name"));
            assertTrue(rs.next());
            assertEquals(4, rs.getInt("id"));
            assertEquals("HR", rs.getString("name"));
            assertTrue(rs.next());
            assertEquals(5, rs.getInt("id"));
            assertEquals("Sales", rs.getString("name"));
            assertFalse(rs.next());

            // find employee in diagnose schema
            rs = con.createStatement().executeQuery("SELECT * FROM " + schemaPrefix + SqlSchemaService.SUFFIX_DIAGNOSE + ".emp ORDER BY id");
            assertTrue(rs.next());
            assertEquals(1, rs.getInt("id"));
            assertEquals("Max", rs.getString("name"));
            assertEquals(3, rs.getInt("dept_id"));
            assertTrue(rs.next());
            assertEquals(2, rs.getInt("id"));
            assertEquals("Peter", rs.getString("name"));
            assertEquals(3, rs.getInt("dept_id"));
            assertTrue(rs.next());
            assertEquals(3, rs.getInt("id"));
            assertEquals("Hans", rs.getString("name"));
            assertEquals(4, rs.getInt("dept_id"));
            assertTrue(rs.next());
            assertEquals(4, rs.getInt("id"));
            assertEquals("Klaus", rs.getString("name"));
            assertEquals(5, rs.getInt("dept_id"));
            assertTrue(rs.next());
            assertEquals(5, rs.getInt("id"));
            assertEquals("Franz", rs.getString("name"));
            assertEquals(5, rs.getInt("dept_id"));
            assertFalse(rs.next());
        }
    }

    @Test
    void testFillTablesThrowsExceptionOnInvalidSchema() {
        // Arrange
        final String schemaPrefix = "testFillTablesThrowsExceptionOnInvalidSchema";

        // Act & Assert
        assertThrows(SQLException.class, () -> schemaService.fillTables(schemaPrefix, "INSERT INTO dept (id, name) VALUES (1, 'IT');", false));
    }

    @Test
    void testFillTablesThrowsExceptionOnInvalidDmlStatement() throws SQLException {
        // Arrange
        final String schemaPrefix = "testFillTablesThrowsExceptionOnInvalidDmlStatement";
        final String ddl_statements = """
            CREATE TABLE dept (
                id INT PRIMARY KEY,
                name VARCHAR(50) NOT NULL
            );

            CREATE TABLE emp (
                id INT PRIMARY KEY,
                name VARCHAR(50) NOT NULL,
                dept_id INT NOT NULL,
                FOREIGN KEY (dept_id) REFERENCES dept(id)
            );
            """;
        schemaService.createSchemas(schemaPrefix);
        schemaService.createTables(schemaPrefix, ddl_statements);
        schemaService.commit();

        // Act & Assert
        assertThrows(SQLException.class, () -> schemaService.fillTables(schemaPrefix, """
            INSERT INTO dept (id, name) VALUES (1, 'IT');
            INSERT INTO emp (id, name, dept_id) VALUES (5, 'Franz', 3);
            """, false));
    }
    //#endregion

    //#region --- grantReadAccess ---
    @Test
    void testGrantReadAccess() throws SQLException {
        // Arrange
        final String schemaPrefix = "testGrantReadAccess";
        final String ddl_statements = """
            CREATE TABLE dept (
                id INT PRIMARY KEY,
                name VARCHAR(50) NOT NULL
            );
            """;
        schemaService.createSchemas(schemaPrefix);
        schemaService.createTables(schemaPrefix, ddl_statements);

        // Act
        schemaService.fillTables(schemaPrefix, """
            INSERT INTO dept (id, name) VALUES (1, 'IT');
            INSERT INTO dept (id, name) VALUES (2, 'HR');
            INSERT INTO dept (id, name) VALUES (3, 'Sales');
            """, false);
        schemaService.commit();

        // Assert
        try (Connection con = DriverManager.getConnection(postgresContainer.getJdbcUrl(), "test_executor", "test_executor_pwd")) {
            var rs = con.createStatement().executeQuery("SELECT COUNT(*) FROM " + schemaPrefix + SqlSchemaService.SUFFIX_SUBMISSION + ".dept");
            assertTrue(rs.next());
            assertEquals(3, rs.getInt(1));
            assertFalse(rs.next());
        }
    }

    @Test
    void testGrantReadAccessCannotDelete() throws SQLException {
        // Arrange
        final String schemaPrefix = "testGrantReadAccessCannotDelete";
        final String ddl_statements = """
            CREATE TABLE dept (
                id INT PRIMARY KEY,
                name VARCHAR(50) NOT NULL
            );
            """;
        schemaService.createSchemas(schemaPrefix);
        schemaService.createTables(schemaPrefix, ddl_statements);

        // Act
        schemaService.fillTables(schemaPrefix, """
            INSERT INTO dept (id, name) VALUES (1, 'IT');
            INSERT INTO dept (id, name) VALUES (2, 'HR');
            INSERT INTO dept (id, name) VALUES (3, 'Sales');
            """, false);
        schemaService.commit();

        // Assert
        try (Connection con = DriverManager.getConnection(postgresContainer.getJdbcUrl(), "test_executor", "test_executor_pwd")) {
            assertThrows(SQLException.class, () -> con.createStatement().executeUpdate("DELETE FROM " + schemaPrefix + SqlSchemaService.SUFFIX_SUBMISSION + ".dept WHERE id = 1"));
        }
    }
    //#endregion

    //#region --- deleteSchemas ---
    @Test
    void testDeleteSchemas() throws SQLException {
        // Arrange
        final String schemaPrefix = "testDeleteSchemas";
        schemaService.createSchemas(schemaPrefix);

        // Act
        schemaService.deleteSchemas(schemaPrefix);
        schemaService.commit();

        // Assert
        boolean submissionFound = false;
        boolean diagnoseFound = false;

        try (Connection con = DriverManager.getConnection(postgresContainer.getJdbcUrl(), postgresContainer.getUsername(), postgresContainer.getPassword())) {
            ResultSet schemas = con.getMetaData().getSchemas();
            while (schemas.next()) {
                String schemaName = schemas.getString(1);
                if (schemaName.equalsIgnoreCase(schemaPrefix + SqlSchemaService.SUFFIX_SUBMISSION))
                    submissionFound = true;
                else if (schemaName.equalsIgnoreCase(schemaPrefix + SqlSchemaService.SUFFIX_DIAGNOSE))
                    diagnoseFound = true;
            }
        }

        assertFalse(submissionFound);
        assertFalse(diagnoseFound);
    }

    @Test
    void testDeleteSchemasInvalidSchemaName() {
        // Arrange
        final String schemaPrefix = "m.@3";

        // Act & Assert
        assertThrows(SQLException.class, () -> schemaService.deleteSchemas(schemaPrefix));
    }

    @Test
    void testDeleteSchemasNotThrowExceptionOnNotExistingSchema() {
        // Arrange
        final String schemaPrefix = "deleteSchemas_notExistingSchema_notThrowException";

        // Act & Assert
        assertDoesNotThrow(() -> schemaService.deleteSchemas(schemaPrefix));
    }
    //#endregion

    //#region --- getSchemaInfoDto ---
    @Test
    void getSchemaInfoDto() throws SQLException {
        // Arrange
        final String schemaPrefix = "getSchemaInfoDto";
        schemaService.createSchemas(schemaPrefix);

        final String statements = """
            CREATE TABLE dept (
                id INT PRIMARY KEY,
                name VARCHAR(50) NOT NULL
            );

            CREATE TABLE emp (
                id INT PRIMARY KEY,
                name VARCHAR(50) NOT NULL,
                dept_id INT NOT NULL,
                FOREIGN KEY (dept_id) REFERENCES dept(id)
            );
            """;
        schemaService.createTables(schemaPrefix, statements);
        schemaService.commit();

        // Act
        var schemaInfoDto = schemaService.getSchemaInfoDto(schemaPrefix + SqlSchemaService.SUFFIX_SUBMISSION);

        // Assert
        assertNotNull(schemaInfoDto);
        assertEquals(2, schemaInfoDto.tables().size());

        var tblDept = schemaInfoDto.tables().stream().filter(x -> x.name().equals("dept")).findFirst().orElseThrow();
        assertEquals(2, tblDept.columns().size());
        assertEquals(0, tblDept.foreignKeys().size());

        var tblEmp = schemaInfoDto.tables().stream().filter(x -> x.name().equals("emp")).findFirst().orElseThrow();
        assertEquals(3, tblEmp.columns().size());
        assertEquals(1, tblEmp.foreignKeys().size());
    }

    @Test
    void getSchemaInfoDtoCombinedFK() throws SQLException {
        // Arrange
        final String schemaPrefix = "getSchemaInfoDtoCombinedFK";
        schemaService.createSchemas(schemaPrefix);

        final String statements = """
            CREATE TABLE dept (
                id INT,
                name VARCHAR(50),
                PRIMARY KEY(id, name)
            );

            CREATE TABLE emp (
                id INT PRIMARY KEY,
                name VARCHAR(50) NOT NULL,
                dept_id INT NOT NULL,
                dept_name VARCHAR(50) NOT NULL,
                FOREIGN KEY (dept_id, dept_name) REFERENCES dept(id, name)
            );
            """;
        schemaService.createTables(schemaPrefix, statements);
        schemaService.commit();

        // Act
        var schemaInfoDto = schemaService.getSchemaInfoDto(schemaPrefix + SqlSchemaService.SUFFIX_SUBMISSION);

        // Assert
        assertNotNull(schemaInfoDto);
        assertEquals(2, schemaInfoDto.tables().size());

        var tblDept = schemaInfoDto.tables().stream().filter(x -> x.name().equals("dept")).findFirst().orElseThrow();
        assertEquals(2, tblDept.columns().size());
        assertEquals(0, tblDept.foreignKeys().size());

        var tblEmp = schemaInfoDto.tables().stream().filter(x -> x.name().equals("emp")).findFirst().orElseThrow();
        assertEquals(4, tblEmp.columns().size());
        assertEquals(1, tblEmp.foreignKeys().size());
    }
    //#endregion
}
