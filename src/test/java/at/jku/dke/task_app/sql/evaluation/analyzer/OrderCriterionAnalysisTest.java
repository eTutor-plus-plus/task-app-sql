package at.jku.dke.task_app.sql.evaluation.analyzer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderCriterionAnalysisTest {

    @Test
    void isSetOrderingIncorrect() {
        // Arrange
        var analysis = new OrderCriterionAnalysis();

        // Act
        analysis.setOrderingIncorrect(true);
        var result = analysis.isOrderingIncorrect();

        // Assert
        assertTrue(result);
    }

    @Test
    void isCriterionSatisfied() {
        // Arrange
        var analysis = new OrderCriterionAnalysis();
        analysis.setOrderingIncorrect(true);

        // Act
        var result = analysis.isCriterionSatisfied();

        // Assert
        assertFalse(result);
    }

    @Test
    void isCriterionSatisfied_withException() {
        // Arrange
        var analysis = new OrderCriterionAnalysis();
        analysis.setException(new AnalysisException(""));

        // Act
        var result = analysis.isCriterionSatisfied();

        // Assert
        assertFalse(result);
    }

    @Test
    void isCriterionSatisfied_default() {
        // Arrange
        var analysis = new OrderCriterionAnalysis();

        // Act
        var result = analysis.isCriterionSatisfied();

        // Assert
        assertTrue(result);
    }
}
