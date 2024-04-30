package at.jku.dke.task_app.sql.ra2sql;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RelationalAlgebraParseExceptionTest {
    @Test
    void constructor() {
        // Arrange
        String msg = "msg";
        Throwable cause = new IllegalArgumentException();
        int line = 1;
        int column = 10;
        String ruleName = "rule";

        // Act
        var ex = new RelationalAlgebraParseException(msg, cause, line, column, ruleName);

        // Assert
        assertEquals(msg, ex.getMessage());
        assertEquals(cause, ex.getCause());
        assertEquals(line, ex.getLine());
        assertEquals(column, ex.getColumn());
        assertEquals(ruleName, ex.getRuleName());
    }
}
