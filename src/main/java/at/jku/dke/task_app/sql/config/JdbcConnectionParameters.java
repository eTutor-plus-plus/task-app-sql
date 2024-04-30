package at.jku.dke.task_app.sql.config;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * The JDBC configuration properties.
 *
 * @param url               The connection string.
 * @param admin             The administrator credentials (user which is allowed to create/drop tables).
 * @param executor          The executor credentials (user which is allowed to query the tables).
 * @param connectionTimeout The maximum number of milliseconds that a client (that's you) will wait for a connection from the pool.
 * @param maxLifetime       The maximum lifetime of a connection in the pool.
 * @param maxPoolSize       The maximum size that the pool is allowed to reach, including both idle and in-use connections.
 */
@Validated
@ConfigurationProperties(prefix = "jdbc")
public record JdbcConnectionParameters(
    @NotNull String url,
    @NotNull UserCredentials admin,
    @NotNull UserCredentials executor,
    @NotNull @Positive int maxPoolSize,
    @NotNull @Positive long maxLifetime,
    @NotNull @Positive long connectionTimeout) {
    /**
     * The user credentials.
     *
     * @param username The username.
     * @param password The password.
     */
    public record UserCredentials(@NotNull String username, @NotNull String password) {
    }
}
