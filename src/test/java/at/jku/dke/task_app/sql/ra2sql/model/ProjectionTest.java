package at.jku.dke.task_app.sql.ra2sql.model;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProjectionTest {

    @Test
    void addProjectionAttribute() {
        // Arrange
        var projection = new Projection();

        // Act
        var result = projection.addProjectionAttribute("TesT ");

        // Assert
        assertTrue(result);
        assertThat(projection.getProjectionAttributes()).containsExactly("TEST");
    }

    @Test
    void addProjectionAttribute_null() {
        // Arrange
        var projection = new Projection();

        // Act
        var result = projection.addProjectionAttribute(null);

        // Assert
        assertFalse(result);
    }

    @Test
    void calculateSchema() {
        // Arrange
        var operator = new Projection();
        operator.addSchemaAttribute("TO_REMOVE");
        operator.addProjectionAttribute("P1");
        var expr = new TestExpression("E1", "E2");
        operator.setExpression(expr);
        var schema = new SchemaInfoDto(List.of());

        // Act
        operator.calculateSchema(schema);

        // Assert
        assertEquals(1, expr.calculateSchemaCallCount);
        assertThat(operator.getSchemaAttributes()).containsExactly("P1");
    }

    @Test
    void calculateSchema_null() {
        // Arrange
        var operator = new Projection();
        operator.addSchemaAttribute("TO_REMOVE");
        operator.addProjectionAttribute("P1");
        var schema = new SchemaInfoDto(List.of());

        // Act
        operator.calculateSchema(schema);

        // Assert
        assertThat(operator.getSchemaAttributes()).containsExactly("P1");
    }
}
