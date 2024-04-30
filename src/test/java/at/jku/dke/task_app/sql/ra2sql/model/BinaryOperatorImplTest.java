package at.jku.dke.task_app.sql.ra2sql.model;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BinaryOperatorImplTest {

    @Test
    void getSetLeftExpression() {
        // Arrange
        var operator = new BinaryOperatorTestImpl();
        var expr = new TestExpression();

        // Act
        var set = operator.setLeftExpression(expr);
        var result = operator.getLeftExpression();

        // Assert
        assertEquals(expr, result);
        assertTrue(set);
    }

    @Test
    void setLeftExpression_null() {
        // Arrange
        var operator = new BinaryOperatorTestImpl();
        var expr = new TestExpression();

        // Act
        var set = operator.setLeftExpression(null);

        // Assert
        assertFalse(set);
    }

    @Test
    void getSetRightExpression() {
        // Arrange
        var operator = new BinaryOperatorTestImpl();
        var expr = new TestExpression();

        // Act
        var set = operator.setRightExpression(expr);
        var result = operator.getRightExpression();

        // Assert
        assertEquals(expr, result);
        assertTrue(set);
    }

    @Test
    void setRightExpression_null() {
        // Arrange
        var operator = new BinaryOperatorTestImpl();
        var expr = new TestExpression();

        // Act
        var set = operator.setRightExpression(null);

        // Assert
        assertFalse(set);
    }

    @Test
    void calculateSchema() {
        // Arrange
        var operator = new BinaryOperatorTestImpl();

        var exprLeft = new TestExpression("E1");
        var exprRight = new TestExpression("E2");
        operator.setLeftExpression(exprLeft);
        operator.setRightExpression(exprRight);

        var schema = new SchemaInfoDto(List.of());

        // Act
        operator.calculateSchema(schema);

        // Assert
        assertEquals(1, exprLeft.calculateSchemaCallCount);
        assertEquals(1, exprRight.calculateSchemaCallCount);
        assertThat(operator.getSchemaAttributes()).containsExactly("E1", "E2");
    }

    @Test
    void calculateSchema_leftNull() {
        // Arrange
        var operator = new BinaryOperatorTestImpl();

        var expr = new TestExpression("E1");
        operator.setLeftExpression(expr);

        var schema = new SchemaInfoDto(List.of());

        // Act
        operator.calculateSchema(schema);

        // Assert
        assertEquals(1, expr.calculateSchemaCallCount);
        assertThat(operator.getSchemaAttributes()).containsExactly("E1");
    }

    @Test
    void calculateSchema_rightNull() {
        // Arrange
        var operator = new BinaryOperatorTestImpl();

        var expr = new TestExpression("E1");
        operator.setRightExpression(expr);

        var schema = new SchemaInfoDto(List.of());

        // Act
        operator.calculateSchema(schema);

        // Assert
        assertEquals(1, expr.calculateSchemaCallCount);
        assertThat(operator.getSchemaAttributes()).containsExactly("E1");
    }

    private static class BinaryOperatorTestImpl extends BinaryOperatorImpl {
    }
}
