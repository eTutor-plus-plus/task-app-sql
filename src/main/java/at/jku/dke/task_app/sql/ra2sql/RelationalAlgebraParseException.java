package at.jku.dke.task_app.sql.ra2sql;

/**
 * Thrown if the relational algebra expression is invalid.
 */
public class RelationalAlgebraParseException extends RuntimeException {
    private final int line;
    private final int column;
    private final String ruleName;

    /**
     * Creates a new instance of class {@link RelationalAlgebraParseException}.
     *
     * @param message The detail message.
     * @param cause   The cause.
     * @param line    The line number in the input where the error occurred.
     * @param column  The character position within that line where the error occurred.
     * @param ruleName The name of the rule that caused the error.
     */
    public RelationalAlgebraParseException(String message, Throwable cause, int line, int column, String ruleName) {
        super(message, cause);
        this.line = line;
        this.column = column;
        this.ruleName = ruleName;
    }

    /**
     * Gets the line number in the input where the error occurred.
     *
     * @return The line.
     */
    public int getLine() {
        return line;
    }

    /**
     * Gets the character position within that line where the error occurred.
     *
     * @return The column.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Gets the name of the rule that caused the error.
     *
     * @return The rule name.
     */
    public String getRuleName() {
        return ruleName;
    }
}
