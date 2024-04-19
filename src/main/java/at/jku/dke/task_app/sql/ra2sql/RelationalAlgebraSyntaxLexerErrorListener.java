package at.jku.dke.task_app.sql.ra2sql;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * Throws exceptions on syntax errors in the lexer.
 */
public class RelationalAlgebraSyntaxLexerErrorListener extends BaseErrorListener {

    /**
     * Creates a new instance of class {@link RelationalAlgebraSyntaxLexerErrorListener}.
     */
    public RelationalAlgebraSyntaxLexerErrorListener() {
    }

    /**
     * Upon syntax error, notify any interested parties.
     *
     * @param recognizer         What parser got the error.
     * @param offendingSymbol    The offending token in the input token stream, unless recognizer is a lexer (then it's null).
     * @param line               The line number in the input where the error occurred.
     * @param charPositionInLine The character position within that line where the error occurred.
     * @param msg                The message to emit.
     * @param ex                 The exception generated by the parser that led to the reporting of an error.
     */
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException ex) {
        throw new RelationalAlgebraParseException(msg, ex, line, charPositionInLine, null);
    }
}
