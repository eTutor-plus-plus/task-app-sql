package at.jku.dke.task_app.sql.evaluation;

import at.jku.dke.task_app.sql.config.JdbcConnectionParameters;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Provides a connection to the exercise database for analyzing SQL queries.
 */
@Service
public class SqlDataSource implements AutoCloseable {
    private static final Logger LOG = LoggerFactory.getLogger(SqlDataSource.class);

    private final HikariDataSource dataSource;

    /**
     * Creates a new instance of class {@link SqlDataSource}.
     *
     * @param parameters The JDBC connection parameters.
     */
    public SqlDataSource(JdbcConnectionParameters parameters) {
        var config = new HikariConfig();
        config.setJdbcUrl(parameters.url());
        config.setUsername(parameters.executor().username());
        config.setPassword(parameters.executor().password());
        config.setMaximumPoolSize(parameters.maxPoolSize());
        config.setMaxLifetime(parameters.maxLifetime());
        config.setConnectionTimeout(parameters.connectionTimeout());
        config.setPoolName("jdbc-executor-pool");
        config.setReadOnly(true);
        config.setAutoCommit(false);
        config.addDataSourceProperty("ApplicationName", "etutor Task-App: SQL/RA");
        this.dataSource = new HikariDataSource(config);
    }

    /**
     * Returns a new database connection.
     *
     * @return The database connection.
     * @throws SQLException If the connection could not be established.
     */
    public Connection connect() throws SQLException {
        LOG.info("Connecting to database");
        return this.dataSource.getConnection();
    }

    /**
     * Returns a new database connection.
     *
     * @param schema The schema to connect to.
     * @return The database connection.
     * @throws SQLException If the connection could not be established.
     */
    public Connection connect(String schema) throws SQLException {
        var conn = this.connect();
        LOG.info("Setting schema to '{}'", schema);
        conn.setSchema(schema);
        return conn;
    }

    /**
     * Closes the data source.
     */
    @Override
    public void close() {
        this.dataSource.close();
    }
}
