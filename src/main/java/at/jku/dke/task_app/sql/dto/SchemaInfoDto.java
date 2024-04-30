package at.jku.dke.task_app.sql.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Represents the database schema.
 *
 * @param tables The tables.
 */
public record SchemaInfoDto(List<TableDto> tables) implements Serializable {
    /**
     * Returns the table information for the requested table.
     *
     * @param name The table name.
     * @return The table information, if available.
     */
    public Optional<TableDto> findTable(String name) {
        return this.tables.stream().filter(x -> x.name().equalsIgnoreCase(name)).findFirst();
    }
}
