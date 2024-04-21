package at.jku.dke.task_app.sql.evaluation.analyzer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractSqlCriterionAnalysisTest {

    @Test
    void getSetException() {
        // Arrange
        AbstractSqlCriterionAnalysis analysis = new Impl();
        AnalysisException ex = new AnalysisException("Test");

        // Act
        analysis.setException(ex);
        var result = analysis.getException();

        // Assert
        assertEquals(ex, result);
    }

    @Test
    void isCriterionSatisfied_withException() {
        // Arrange
        AbstractSqlCriterionAnalysis analysis = new Impl();
        analysis.setException(new AnalysisException("Test"));

        // Act
        var result = analysis.isCriterionSatisfied();

        // Assert
        assertFalse(result);
    }

    @Test
    void isCriterionSatisfied_withoutException() {
        // Arrange
        AbstractSqlCriterionAnalysis analysis = new Impl();

        // Act
        var result = analysis.isCriterionSatisfied();

        // Assert
        assertTrue(result);
    }

    private static class Impl extends AbstractSqlCriterionAnalysis {
    }
}
