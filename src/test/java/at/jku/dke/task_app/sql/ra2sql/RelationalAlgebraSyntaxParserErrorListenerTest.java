package at.jku.dke.task_app.sql.ra2sql;

import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RelationalAlgebraSyntaxParserErrorListenerTest {

    @Test
    void syntaxError() {
        // Arrange
        var parser = mock(Parser.class);

        String msg = "msg";
        String rule = "rule";
        RecognitionException cause = new InputMismatchException(parser);
        int line = 1;
        int column = 10;

        when(parser.getRuleNames()).thenReturn(new String[]{rule});

        var treeListener = new RelationalAlgebraParseTreeListener(parser);
        var listener = new RelationalAlgebraSyntaxParserErrorListener(treeListener);
        treeListener.enterEveryRule(new TestRuleContext());

        // Assert
        try {
            listener.syntaxError(null, null, line, column, msg, cause);
            fail("Expected RelationalAlgebraParseException was not thrown");
        } catch (RelationalAlgebraParseException ex) {
            assertEquals(rule, ex.getRuleName());
            assertEquals(msg, ex.getMessage());
            assertEquals(line, ex.getLine());
            assertEquals(column, ex.getColumn());
            assertEquals(cause, ex.getCause());
        }
    }

    private static class TestRuleContext extends ParserRuleContext {
        @Override
        public int getRuleIndex() {
            return 0;
        }
    }

}
