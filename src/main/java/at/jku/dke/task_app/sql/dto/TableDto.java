package at.jku.dke.task_app.sql.dto;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Creates a new instance of class {@link TableDto}.
 *
 * @param name        The table name.
 * @param columns     The columns.
 * @param foreignKeys The foreign keys.
 * @param queryId     The query identifier.
 */
public record TableDto(String name, List<ColumnDto> columns, List<ForeignKeyDto> foreignKeys, UUID queryId) implements Serializable {
    /**
     * Creates a new instance of class {@link ForeignKeyDto}.
     *
     * @param table            The table name.
     * @param column           The column name.
     * @param referencedTable  The referenced table name.
     * @param referencedColumn The referenced column name.
     */
    public record ForeignKeyDto(String table, String column, String referencedTable, String referencedColumn) implements Serializable {
    }

    /**
     * Creates a new instance of class {@link ColumnDto}.
     *
     * @param name       The column name.
     * @param type       The column type.
     * @param nullable   Whether the column is nullable.
     * @param primaryKey Whether the column is part of the primary key.
     */
    public record ColumnDto(String name, String type, boolean nullable, boolean primaryKey) implements Serializable {
    }
}
