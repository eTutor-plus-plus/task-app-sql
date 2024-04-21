package at.jku.dke.task_app.sql.evaluation.analyzer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SyntaxCriterionAnalysisTest {

    @Test
    void isCriterionSatisfied_withException() {
        // Arrange
        var analysis = new SyntaxCriterionAnalysis(new AnalysisException(""));

        // Act
        var result = analysis.isCriterionSatisfied();

        // Assert
        assertFalse(result);
    }

    @Test
    void isCriterionSatisfied_default() {
        // Arrange
        var analysis = new SyntaxCriterionAnalysis(null);

        // Act
        var result = analysis.isCriterionSatisfied();

        // Assert
        assertTrue(result);
    }

    @Test
    void getException() {
        // Arrange
        var ex = new AnalysisException("");

        // Act
        var analysis = new SyntaxCriterionAnalysis(ex);
        var result = analysis.getException();

        // Assert
        assertEquals(ex, result);
    }

}
