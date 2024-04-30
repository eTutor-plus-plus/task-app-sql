package at.jku.dke.task_app.sql.ra2sql.model;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UnaryOperatorImplTest {

    @Test
    void getSetExpression() {
        // Arrange
        var operator = new UnaryOperatorTestImpl();
        var expr = new TestExpression();

        // Act
        var set = operator.setExpression(expr);
        var result = operator.getExpression();

        // Assert
        assertTrue(set);
        assertEquals(expr, result);
    }

    @Test
    void setExpression_null() {
        // Arrange
        var operator = new UnaryOperatorTestImpl();

        // Act
        var set = operator.setExpression(null);

        // Assert
        assertFalse(set);
    }

    @Test
    void calculateSchema() {
        // Arrange
        var operator = new UnaryOperatorTestImpl();
        operator.addSchemaAttribute("TO_REMOVE");
        var expr = new TestExpression("E1", "E2");
        operator.setExpression(expr);
        var schema = new SchemaInfoDto(List.of());

        // Act
        operator.calculateSchema(schema);

        // Assert
        assertEquals(1, expr.calculateSchemaCallCount);
        assertThat(operator.getSchemaAttributes()).containsExactly("E1", "E2");
    }

    @Test
    void calculateSchema_null() {
        // Arrange
        var operator = new UnaryOperatorTestImpl();
        operator.addSchemaAttribute("TO_REMOVE");
        var schema = new SchemaInfoDto(List.of());

        // Act
        operator.calculateSchema(schema);

        // Assert
        assertThat(operator.getSchemaAttributes()).isEmpty();
    }

    private static class UnaryOperatorTestImpl extends UnaryOperatorImpl {
    }
}
