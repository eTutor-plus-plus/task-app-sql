package at.jku.dke.task_app.sql.evaluation.analyzer;

import at.jku.dke.task_app.sql.evaluation.SqlEvaluationCriterion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class SqlAnalysisTest {

    @Test
    void getSetQueryResult() {
        // Arrange
        var analysis = new SqlAnalysis();
        var solution = new QueryResult();

        // Act
        analysis.setQueryResult(solution);
        var result = analysis.getQueryResult();

        // Assert
        assertSame(solution, result);
    }

    @Test
    void getSetAnalysisException() {
        // Arrange
        var analysis = new SqlAnalysis();
        var ex = new Exception();

        // Act
        analysis.setAnalysisException(ex);
        var result = analysis.getAnalysisException();

        // Assert
        assertSame(ex, result);
    }

    @Test
    void getCriterionAnalysis() {
        // Arrange
        var analysis = new SqlAnalysis();
        var criterion = new OrderCriterionAnalysis();
        analysis.addCriterionAnalysis(SqlEvaluationCriterion.CORRECT_ORDER, criterion);

        // Act
        var result = analysis.getCriterionAnalysis();

        // Assert
        assertEquals(1, result.size());
        assertSame(criterion, result.get(SqlEvaluationCriterion.CORRECT_ORDER));
    }

    @Test
    void getAddCriterionAnalysis() {
        // Arrange
        var analysis = new SqlAnalysis();
        var criterion = new OrderCriterionAnalysis();

        // Act
        analysis.addCriterionAnalysis(SqlEvaluationCriterion.CORRECT_ORDER, criterion);
        var result = analysis.getCriterionAnalysis(SqlEvaluationCriterion.CORRECT_ORDER);

        // Assert
        assertSame(criterion, result);
    }
}
