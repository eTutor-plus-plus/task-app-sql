package at.jku.dke.task_app.sql.data.entities;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SqlRaTaskTest {

    @Test
    void testGetSetSolution() {
        // Arrange
        var task = new SqlRaTask();
        final String expected = "SELECT";

        // Act
        task.setSolution(expected);
        final String actual = task.getSolution();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void testGetSetRelationalAlgebraSolution() {
        // Arrange
        var task = new SqlRaTask();
        final String expected = "PROJECTION";

        // Act
        task.setRelationalAlgebraSolution(expected);
        final String actual = task.getRelationalAlgebraSolution();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void testGetSetWrongOrderPenalty() {
        // Arrange
        var task = new SqlRaTask();
        final BigDecimal expected = BigDecimal.TEN;

        // Act
        task.setWrongOrderPenalty(expected);
        final BigDecimal actual = task.getWrongOrderPenalty();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void testGetSetSuperfluousColumnsPenalty() {
        // Arrange
        var task = new SqlRaTask();
        final BigDecimal expected = BigDecimal.TEN;

        // Act
        task.setSuperfluousColumnsPenalty(expected);
        final BigDecimal actual = task.getSuperfluousColumnsPenalty();

        // Assert
        assertEquals(expected, actual);
    }

}
