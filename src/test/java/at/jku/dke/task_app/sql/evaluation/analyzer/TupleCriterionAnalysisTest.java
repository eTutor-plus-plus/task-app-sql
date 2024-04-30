package at.jku.dke.task_app.sql.evaluation.analyzer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TupleCriterionAnalysisTest {
    @Test
    void isCriterionSatisfied_withException() {
        // Arrange
        var analysis = new TupleCriterionAnalysis();
        analysis.setException(new AnalysisException(""));

        // Act
        var result = analysis.isCriterionSatisfied();

        // Assert
        assertFalse(result);
    }

    @Test
    void isCriterionSatisfied_withMissing() {
        // Arrange
        var analysis = new TupleCriterionAnalysis();
        analysis.addMissingTuple(List.of());

        // Act
        var result = analysis.isCriterionSatisfied();

        // Assert
        assertFalse(result);
    }

    @Test
    void isCriterionSatisfied_withSuperfluous() {
        // Arrange
        var analysis = new TupleCriterionAnalysis();
        analysis.addSuperfluousTuple(List.of());

        // Act
        var result = analysis.isCriterionSatisfied();

        // Assert
        assertFalse(result);
    }

    @Test
    void isCriterionSatisfied_default() {
        // Arrange
        var analysis = new TupleCriterionAnalysis();

        // Act
        var result = analysis.isCriterionSatisfied();

        // Assert
        assertTrue(result);
    }

    @Test
    void hasMissingTuples() {
        // Arrange
        var analysis = new TupleCriterionAnalysis();
        analysis.addMissingTuple(new ArrayList<>(0));

        // Act
        var result = analysis.hasMissingTuples();

        // Assert
        assertTrue(result);
    }

    @Test
    void hasSuperfluousTuples() {
        // Arrange
        var analysis = new TupleCriterionAnalysis();
        analysis.addSuperfluousTuple(new ArrayList<>(0));

        // Act
        var result = analysis.hasSuperfluousTuples();

        // Assert
        assertTrue(result);
    }

    @Test
    void addGetMissingTuples() {
        // Arrange
        var analysis = new TupleCriterionAnalysis();
        final List<String> tuple = List.of("1", "Name");

        // Act
        analysis.addMissingTuple(tuple);
        var result = analysis.getMissingTuples();

        // Assert
        assertThat(result).containsExactly(tuple);
    }

    @Test
    void addGetSuperfluousTuples() {
        // Arrange
        var analysis = new TupleCriterionAnalysis();
        final List<String> tuple = List.of("1", "Name");

        // Act
        analysis.addSuperfluousTuple(tuple);
        var result = analysis.getSuperfluousTuples();

        // Assert
        assertThat(result).containsExactly(tuple);
    }

    @Test
    void getSetColumnLabels() {
        // Arrange
        var analysis = new TupleCriterionAnalysis();
        final List<String> columns = List.of("Id", "Name");

        // Act
        analysis.setColumnLabels(columns);
        var result = analysis.getColumnLabels();

        // Assert
        assertEquals(columns, result);
    }

    @Test
    void addColumnLabel() {
        // Arrange
        var analysis = new TupleCriterionAnalysis();
        final String column = "id";

        // Act
        analysis.addColumnLabel(column);
        var result = analysis.getColumnLabels();

        // Assert
        assertThat(result).containsExactly(column);
    }
}
