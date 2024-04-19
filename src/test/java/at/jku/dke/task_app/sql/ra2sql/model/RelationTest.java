package at.jku.dke.task_app.sql.ra2sql.model;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;
import at.jku.dke.task_app.sql.dto.TableDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RelationTest {

    @Test
    void getSetName() {
        // Arrange
        Relation relation = new Relation();

        // Act
        relation.setName("test ");
        var result = relation.getName();

        // Assert
        assertEquals("TEST", result);
    }

    @Test
    void getSetName_null() {
        // Arrange
        Relation relation = new Relation();
        relation.setName("Test");

        // Act
        relation.setName(null);
        var result = relation.getName();

        // Assert
        assertEquals("TEST", result);
    }

    @Test
    void calculateSchema() {
        // Arrange
        var operator = new Relation();
        operator.setName("test");
        operator.addSchemaAttribute("TO_REMOVE");
        var schema = new SchemaInfoDto(List.of(
            new TableDto("test", List.of(
                new TableDto.ColumnDto("id", "INT", false, true),
                new TableDto.ColumnDto("name", "TEXT", false, false)
            ), List.of(), null)
        ));

        // Act
        operator.calculateSchema(schema);

        // Assert
        assertThat(operator.getSchemaAttributes()).containsExactly("ID", "NAME");
    }

    @Test
    void calculateSchema_noSchema() {
        // Arrange
        var operator = new Relation();
        operator.setName("test");
        operator.addSchemaAttribute("TO_REMOVE");
        var schema = new SchemaInfoDto(List.of());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> operator.calculateSchema(schema));
    }

}
