package at.jku.dke.task_app.sql.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * The caching configuration.
 */
@Configuration
@EnableCaching
public class CacheConfig {
    /**
     * Creates a new instance of class {@link CacheConfig}.
     */
    public CacheConfig() {
    }
}
