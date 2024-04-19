package at.jku.dke.task_app.sql.ra2sql.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComparisonTest {

    @Test
    void getSetLeftValue() {
        // Arrange
        var comparison = new Comparison();
        final String value = "test";

        // Act
        var set = comparison.setLeftValue(value);
        var result = comparison.getLeftValue();

        // Assert
        assertEquals(value, result);
        assertTrue(set);
    }

    @Test
    void setLeftValue_null() {
        // Arrange
        var comparison = new Comparison();

        // Act
        var set = comparison.setLeftValue(null);

        // Assert
        assertFalse(set);
    }

    @Test
    void setLeftValue_empty() {
        // Arrange
        var comparison = new Comparison();

        // Act
        var set = comparison.setLeftValue("");

        // Assert
        assertFalse(set);
    }

    @Test
    void getSetRightValue() {
        // Arrange
        var comparison = new Comparison();
        final String value = "test";

        // Act
        var set = comparison.setRightValue(value);
        var result = comparison.getRightValue();

        // Assert
        assertEquals(value, result);
        assertTrue(set);
    }

    @Test
    void setRightValue_null() {
        // Arrange
        var comparison = new Comparison();

        // Act
        var set = comparison.setRightValue(null);

        // Assert
        assertFalse(set);
    }

    @Test
    void setRightValue_empty() {
        // Arrange
        var comparison = new Comparison();

        // Act
        var set = comparison.setRightValue("");

        // Assert
        assertFalse(set);
    }

    @Test
    void getSetOperator() {
        // Arrange
        var comparison = new Comparison();
        final ComparisonOperator value = ComparisonOperator.NOT_EQUAL;

        // Act
        var set = comparison.setOperator(value);
        var result = comparison.getOperator();

        // Assert
        assertEquals(value, result);
        assertTrue(set);
    }

    @Test
    void setOperator_null() {
        // Arrange
        var comparison = new Comparison();

        // Act
        var set = comparison.setOperator(null);

        // Assert
        assertFalse(set);
    }

    @Test
    void getSetLeftValueType() {
        // Arrange
        var comparison = new Comparison();
        final ComparisonValueType value = ComparisonValueType.ATTRIBUTE;

        // Act
        var set = comparison.setLeftValueType(value);
        var result = comparison.getLeftValueType();

        // Assert
        assertEquals(value, result);
        assertTrue(set);
    }

    @Test
    void setLeftValueType_null() {
        // Arrange
        var comparison = new Comparison();

        // Act
        var set = comparison.setLeftValueType(null);

        // Assert
        assertFalse(set);
    }

    @Test
    void getRightValueType() {
        // Arrange
        var comparison = new Comparison();
        final ComparisonValueType value = ComparisonValueType.ATTRIBUTE;

        // Act
        var set = comparison.setRightValueType(value);
        var result = comparison.getRightValueType();

        // Assert
        assertEquals(value, result);
        assertTrue(set);
    }

    @Test
    void setRightValueType() {
        // Arrange
        var comparison = new Comparison();

        // Act
        var set = comparison.setRightValue(null);

        // Assert
        assertFalse(set);
    }

}
