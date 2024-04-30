package at.jku.dke.task_app.sql.ra2sql;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RelationalAlgebraParseTreeListenerTest {

    @Test
    void enterEveryRule() {
        // Arrange
        var parser = mock(Parser.class);
        when(parser.getRuleNames()).thenReturn(new String[]{"test"});
        var listener = new RelationalAlgebraParseTreeListener(parser);

        // Act
        listener.enterEveryRule(new TestRuleContext());
        var result = listener.getLastRule();

        // Assert
        assertEquals("test", result);
    }

    private static class TestRuleContext extends ParserRuleContext {
        @Override
        public int getRuleIndex() {
            return 0;
        }
    }

}
