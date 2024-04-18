package at.jku.dke.task_app.sql.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * The JDBC configuration properties.
 *
 * @param url      The connection string.
 * @param admin    The administrator credentials (user which is allowed to create/drop tables).
 * @param executor The executor credentials (user which is allowed to query the tables).
 */
@Validated
@ConfigurationProperties(prefix = "jdbc")
public record JdbcConnectionParameters(@NotNull String url, @NotNull UserCredentials admin, @NotNull UserCredentials executor) {
    /**
     * The user credentials.
     *
     * @param username The username.
     * @param password The password.
     */
    public record UserCredentials(@NotNull String username, @NotNull String password) {
    }
}
