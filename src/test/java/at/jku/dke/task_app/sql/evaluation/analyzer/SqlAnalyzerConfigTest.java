package at.jku.dke.task_app.sql.evaluation.analyzer;

import at.jku.dke.task_app.sql.evaluation.SqlEvaluationCriterion;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SqlAnalyzerConfigTest {

    @Test
    void getSolution() {
        // Arrange
        final String solution = "solution";
        var config = new SqlAnalyzerConfig(solution, "");

        // Act
        var result = config.getSolution();

        // Assert
        assertEquals(solution, result);
    }

    @Test
    void getSchema() {
        // Arrange
        final String schema = "solution";
        var config = new SqlAnalyzerConfig("", schema);

        // Act
        var result = config.getSchema();

        // Assert
        assertEquals(schema, result);
    }

    @Test
    void getAddCriteria() {
        // Arrange
        final SqlEvaluationCriterion criterion = SqlEvaluationCriterion.CARTESIAN_PRODUCT;
        var config = new SqlAnalyzerConfig("", "");

        // Act
        config.addCriterion(criterion);
        var result = config.getCriteria();

        // Assert
        assertThat(result).containsExactly(criterion);
    }

    @Test
    void containsCriterion_existing() {
        // Arrange
        final SqlEvaluationCriterion criterion = SqlEvaluationCriterion.CARTESIAN_PRODUCT;
        var config = new SqlAnalyzerConfig("", "");
        config.addCriterion(criterion);

        // Act
        var result = config.containsCriterion(criterion);

        // Assert
        assertTrue(result);
    }

    @Test
    void containsCriterion_notExisting() {
        // Arrange
        final SqlEvaluationCriterion criterion = SqlEvaluationCriterion.CARTESIAN_PRODUCT;
        var config = new SqlAnalyzerConfig("", "");

        // Act
        var result = config.containsCriterion(criterion);

        // Assert
        assertFalse(result);
    }

}
