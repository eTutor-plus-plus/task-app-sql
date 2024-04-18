package at.jku.dke.task_app.sql.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Represents the database schema.
 *
 * @param tables The tables.
 */
public record SchemaInfoDto(List<TableDto> tables) implements Serializable {
}
