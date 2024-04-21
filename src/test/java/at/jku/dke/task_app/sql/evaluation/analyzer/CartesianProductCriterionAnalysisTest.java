package at.jku.dke.task_app.sql.evaluation.analyzer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartesianProductCriterionAnalysisTest {

    @Test
    void isSetCartesianProductSuspected() {
        // Arrange
        var analysis = new CartesianProductCriterionAnalysis();

        // Act
        analysis.setCartesianProductSuspected(true);
        var result = analysis.isCartesianProductSuspected();

        // Assert
        assertTrue(result);
    }

    @Test
    void isCriterionSatisfied() {
        // Arrange
        var analysis = new CartesianProductCriterionAnalysis();
        analysis.setCartesianProductSuspected(true);

        // Act
        var result = analysis.isCriterionSatisfied();

        // Assert
        assertFalse(result);
    }

    @Test
    void isCriterionSatisfied_withException() {
        // Arrange
        var analysis = new CartesianProductCriterionAnalysis();
        analysis.setException(new AnalysisException(""));

        // Act
        var result = analysis.isCriterionSatisfied();

        // Assert
        assertFalse(result);
    }

    @Test
    void isCriterionSatisfied_default() {
        // Arrange
        var analysis = new CartesianProductCriterionAnalysis();

        // Act
        var result = analysis.isCriterionSatisfied();

        // Assert
        assertTrue(result);
    }
}
