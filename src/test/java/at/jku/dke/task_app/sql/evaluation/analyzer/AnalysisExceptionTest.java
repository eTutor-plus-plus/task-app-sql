package at.jku.dke.task_app.sql.evaluation.analyzer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnalysisExceptionTest {
    @Test
    void constructor1() {
        // Arrange
        final String msg = "test";

        // Act
        var ex = new AnalysisException(msg);

        // Assert
        assertEquals(msg, ex.getMessage());
    }

    @Test
    void constructor2() {
        // Arrange
        final String msg = "test";
        final Throwable cause = new RuntimeException(msg);

        // Act
        var ex = new AnalysisException(msg, cause);

        // Assert
        assertEquals(msg, ex.getMessage());
        assertEquals(cause, ex.getCause());
    }
}
