package at.jku.dke.task_app.sql.ra2sql;

import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RelationalAlgebraSyntaxLexerErrorListenerTest {

    @Test
    void syntaxError() {
        // Arrange
        var listener = new RelationalAlgebraSyntaxLexerErrorListener();
        String msg = "msg";
        RecognitionException cause = new InputMismatchException(mock(Parser.class));
        int line = 1;
        int column = 10;

        // Assert
        try {
            listener.syntaxError(null, null, line, column, msg, cause);
            fail("Expected RelationalAlgebraParseException was not thrown");
        } catch (RelationalAlgebraParseException ex) {
            assertNull(ex.getRuleName());
            assertEquals(msg, ex.getMessage());
            assertEquals(line, ex.getLine());
            assertEquals(column, ex.getColumn());
            assertEquals(cause, ex.getCause());
        }
    }

}
