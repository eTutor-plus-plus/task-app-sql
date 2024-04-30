package at.jku.dke.task_app.sql.ra2sql.model;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RightSemiJoinTest {

    @Test
    void calculateSchema() {
        // Arrange
        var operator = new RightSemiJoin();
        operator.addSchemaAttribute("TO_REMOVE");

        var exprLeft = new TestExpression("E1", "E2");
        var exprRight = new TestExpression("E2");
        operator.setLeftExpression(exprLeft);
        operator.setRightExpression(exprRight);

        var schema = new SchemaInfoDto(List.of());

        // Act
        operator.calculateSchema(schema);

        // Assert
        assertEquals(1, exprLeft.calculateSchemaCallCount);
        assertEquals(1, exprRight.calculateSchemaCallCount);
        assertThat(operator.getSchemaAttributes()).containsExactly("E2");
    }

    @Test
    void calculateSchema_leftNull() {
        // Arrange
        var operator = new RightSemiJoin();
        operator.addSchemaAttribute("TO_REMOVE");

        var expr = new TestExpression("E1", "E2");
        operator.setRightExpression(expr);

        var schema = new SchemaInfoDto(List.of());

        // Act
        operator.calculateSchema(schema);

        // Assert
        assertEquals(1, expr.calculateSchemaCallCount);
        assertThat(operator.getSchemaAttributes()).containsExactly("E1", "E2");
    }

    @Test
    void calculateSchema_rightNull() {
        // Arrange
        var operator = new RightSemiJoin();
        operator.addSchemaAttribute("TO_REMOVE");

        var expr = new TestExpression("E1", "E2");
        operator.setLeftExpression(expr);

        var schema = new SchemaInfoDto(List.of());

        // Act
        operator.calculateSchema(schema);

        // Assert
        assertEquals(1, expr.calculateSchemaCallCount);
        assertThat(operator.getSchemaAttributes()).isEmpty();
    }

}
