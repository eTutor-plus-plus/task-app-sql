package at.jku.dke.task_app.sql.ra2sql.model;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ThetaJoinTest {

    @Test
    void addComparison() {
        // Arrange
        var join = new ThetaJoin();
        var comp = new Comparison();

        // Act
        var set = join.addComparison(comp);

        // Arrange
        assertTrue(set);
        assertThat(join.getComparisons()).containsExactly(comp);
    }

    @Test
    void addComparison_null() {
        // Arrange
        var join = new ThetaJoin();

        // Act
        var set = join.addComparison(null);

        // Arrange
        assertFalse(set);
    }

    @Test
    void calculateSchema() {
        // Arrange
        var operator = new ThetaJoin();
        operator.addSchemaAttribute("TO_REMOVE");

        var exprLeft = new TestExpression("E1", "E2");
        var exprRight = new TestExpression("E2", "E3");
        operator.setLeftExpression(exprLeft);
        operator.setRightExpression(exprRight);

        var schema = new SchemaInfoDto(List.of());

        // Act
        operator.calculateSchema(schema);

        // Assert
        assertEquals(1, exprLeft.calculateSchemaCallCount);
        assertEquals(1, exprRight.calculateSchemaCallCount);
        assertThat(operator.getSchemaAttributes()).containsExactly("LS.E1", "LS.E2", "RS.E2", "RS.E3");
    }

    @Test
    void calculateSchema_leftNull() {
        // Arrange
        var operator = new ThetaJoin();
        operator.addSchemaAttribute("TO_REMOVE");

        var expr = new TestExpression("E1", "E2");
        operator.setRightExpression(expr);

        var schema = new SchemaInfoDto(List.of());

        // Act
        operator.calculateSchema(schema);

        // Assert
        assertEquals(1, expr.calculateSchemaCallCount);
        assertThat(operator.getSchemaAttributes()).containsExactly("RS.E1", "RS.E2");
    }

    @Test
    void calculateSchema_rightNull() {
        // Arrange
        var operator = new ThetaJoin();
        operator.addSchemaAttribute("TO_REMOVE");

        var expr = new TestExpression("E1", "E2");
        operator.setLeftExpression(expr);

        var schema = new SchemaInfoDto(List.of());

        // Act
        operator.calculateSchema(schema);

        // Assert
        assertEquals(1, expr.calculateSchemaCallCount);
        assertThat(operator.getSchemaAttributes()).containsExactly("LS.E1", "LS.E2");
    }

}
