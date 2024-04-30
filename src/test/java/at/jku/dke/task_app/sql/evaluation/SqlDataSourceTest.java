package at.jku.dke.task_app.sql.evaluation;

import at.jku.dke.task_app.sql.AppPostgresContainer;
import at.jku.dke.task_app.sql.DatabaseSetupExtension;
import at.jku.dke.task_app.sql.config.JdbcConnectionParameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.DriverManager;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(DatabaseSetupExtension.class)
class SqlDataSourceTest {

    private static final int POOL_SIZE = 3;
    private static final JdbcConnectionParameters PARAMS = new JdbcConnectionParameters(
        AppPostgresContainer.INSTANCE.getJdbcUrl(),
        new JdbcConnectionParameters.UserCredentials(AppPostgresContainer.ETUTOR_USERNAME, AppPostgresContainer.ETUTOR_PASSWORD),
        new JdbcConnectionParameters.UserCredentials(AppPostgresContainer.EXECUTOR_USERNAME, AppPostgresContainer.EXECUTOR_PASSWORD),
        POOL_SIZE, 1800000, 5000
    );

    @Test
    void connect() throws SQLException {
        try (var ds = new SqlDataSource(PARAMS)) {
            // Act
            try (var conn = ds.connect()) {
                // Assert
                assertTrue(conn.getMetaData().allTablesAreSelectable());
            }
        }
    }

    @Test
    void connect_schema() throws SQLException {
        // Arrange
        try (var conn = DriverManager.getConnection(AppPostgresContainer.INSTANCE.getJdbcUrl(), AppPostgresContainer.USERNAME, AppPostgresContainer.PASSWORD)) {
            conn.setAutoCommit(false);
            try (var stmt = conn.createStatement()) {
                stmt.execute("CREATE SCHEMA connect_schema_test");
                stmt.execute("GRANT USAGE ON SCHEMA connect_schema_test TO " + AppPostgresContainer.EXECUTOR_USERNAME);
                stmt.execute("CREATE TABLE connect_schema_test.demo_table (id INTEGER PRIMARY KEY);");
                stmt.execute("GRANT SELECT ON TABLE connect_schema_test.demo_table TO " + AppPostgresContainer.EXECUTOR_USERNAME);
            }
            conn.commit();
        }

        // Act
        try (var ds = new SqlDataSource(PARAMS)) {
            try (var conn = ds.connect("connect_schema_test")) {
                assertEquals("connect_schema_test", conn.getSchema());

                try (var stmt = conn.createStatement()) {
                    // Assert search path
                    var rsSchema = stmt.executeQuery("SHOW search_path;");
                    assertTrue(rsSchema.next());
                    assertEquals("connect_schema_test", rsSchema.getString(1));

                    // Assert query
                    var rsTable = stmt.executeQuery("SELECT COUNT(*) FROM demo_table;");
                    assertTrue(rsTable.next());
                }
            }
        }
    }

    @Test
    void connect_more_than_pool_size() throws InterruptedException {
        try (var ds = new SqlDataSource(PARAMS)) {
            // Arrange
            var threads = new Thread[POOL_SIZE + 1];
            for (int i = 0; i < POOL_SIZE + 1; i++) {
                threads[i] = new Thread(new QueryThread(ds));
            }

            // Act
            long start = System.currentTimeMillis();
            for (var t : threads) {
                t.start();
            }
            for (var t : threads) {
                t.join();
            }
            long end = System.currentTimeMillis();

            // Assert
            assertThat(end - start).isGreaterThanOrEqualTo(2000);
        }
    }

    private static class QueryThread implements Runnable {

        private final SqlDataSource ds;

        public QueryThread(SqlDataSource ds) {
            this.ds = ds;
        }

        @Override
        public void run() {
            try (var conn = ds.connect()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignore) {
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
