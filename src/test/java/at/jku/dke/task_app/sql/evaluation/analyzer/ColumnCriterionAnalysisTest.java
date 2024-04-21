package at.jku.dke.task_app.sql.evaluation.analyzer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ColumnCriterionAnalysisTest {

    @Test
    void isCriterionSatisfied_withException() {
        // Arrange
        var analysis = new ColumnCriterionAnalysis();
        analysis.setException(new AnalysisException(""));

        // Act
        var result = analysis.isCriterionSatisfied();

        // Assert
        assertFalse(result);
    }

    @Test
    void isCriterionSatisfied_withMissing() {
        // Arrange
        var analysis = new ColumnCriterionAnalysis();
        analysis.addMissingColumn("id");

        // Act
        var result = analysis.isCriterionSatisfied();

        // Assert
        assertFalse(result);
    }

    @Test
    void isCriterionSatisfied_withSuperfluous() {
        // Arrange
        var analysis = new ColumnCriterionAnalysis();
        analysis.addSuperfluousColumn("id");

        // Act
        var result = analysis.isCriterionSatisfied();

        // Assert
        assertFalse(result);
    }

    @Test
    void isCriterionSatisfied_default() {
        // Arrange
        var analysis = new ColumnCriterionAnalysis();

        // Act
        var result = analysis.isCriterionSatisfied();

        // Assert
        assertTrue(result);
    }

    @Test
    void hasMissingColumns() {
        // Arrange
        var analysis = new ColumnCriterionAnalysis();
        analysis.addMissingColumn("id");

        // Act
        var result = analysis.hasMissingColumns();

        // Assert
        assertTrue(result);
    }

    @Test
    void hasSuperfluousColumns() {
        // Arrange
        var analysis = new ColumnCriterionAnalysis();
        analysis.addSuperfluousColumn("id");

        // Act
        var result = analysis.hasSuperfluousColumns();

        // Assert
        assertTrue(result);
    }

    @Test
    void addGetMissingColumn() {
        // Arrange
        var analysis = new ColumnCriterionAnalysis();
        final String column = "id";

        // Act
        analysis.addMissingColumn(column);
        var result = analysis.getMissingColumns();

        // Assert
        assertThat(result).containsExactly(column);
    }

    @Test
    void addGetSuperfluousColumn() {
        // Arrange
        var analysis = new ColumnCriterionAnalysis();
        final String column = "id";

        // Act
        analysis.addSuperfluousColumn(column);
        var result = analysis.getSuperfluousColumns();

        // Assert
        assertThat(result).containsExactly(column);
    }
}
