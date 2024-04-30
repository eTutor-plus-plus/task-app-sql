package at.jku.dke.task_app.sql.ra2sql;

import at.jku.dke.task_app.sql.ra2sql.parser.RelationalAlgebraSyntaxBaseListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Listens the parser walking through the tree.
 */
class RelationalAlgebraParseTreeListener extends RelationalAlgebraSyntaxBaseListener {
    private final Parser parser;
    private String lastRule;

    /**
     * Creates a new instance of class {@link RelationalAlgebraParseTreeListener}.
     *
     * @param parser The parser this listener listens to.
     */
    public RelationalAlgebraParseTreeListener(Parser parser) {
        this.parser = parser;
    }

    /**
     * Called when a rule is entered.
     *
     * @param ctx The rule context.
     */
    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        this.lastRule = this.parser.getRuleNames()[ctx.getRuleIndex()];
    }

    /**
     * Gets the name of the last rule that was entered.
     *
     * @return The rule name.
     */
    public String getLastRule() {
        return lastRule;
    }
}
