package at.jku.dke.task_app.sql.ra2sql;

/**
 * An extended string this.builder.
 * <p>
 * If you want to create a new line call {@link #newLine()} and do not manually add a new line, otherwise indentation won't work.
 */
public class IntendingStringBuilder {
    private final StringBuilder builder;

    private int indent;

    /**
     * Creates a new instance of class {@link IntendingStringBuilder}.
     */
    public IntendingStringBuilder() {
        this.builder = new StringBuilder();
        this.indent = 0;
    }

    /**
     * Creates a new instance of class {@link IntendingStringBuilder}.
     *
     * @param s The initial contents.
     */
    public IntendingStringBuilder(String s) {
        this.builder = new StringBuilder(s);
    }

    /**
     * Appends the string representation of the {@code Object} argument.
     *
     * @param obj An {@code Object}.
     * @return A reference to this object.
     */
    public IntendingStringBuilder append(Object obj) {
        this.builder.append(obj);
        return this;
    }

    /**
     * Appends the string representation of the {@code char} argument to this sequence.
     *
     * @param c A {@code char}.
     * @return A reference to this object.
     */
    public IntendingStringBuilder append(char c) {
        this.builder.append(c);
        return this;
    }

    /**
     * Appends the specified string to this character sequence.
     *
     * @param str A string.
     * @return A reference to this object.
     */
    public IntendingStringBuilder append(String str) {
        this.builder.append(str);
        return this;
    }

    /**
     * Appends a new line char to this character sequence.
     *
     * @return A reference to this object.
     * @see System#lineSeparator()
     */
    public IntendingStringBuilder newLine() {
        this.builder.append(System.lineSeparator());
        this.builder.append(" ".repeat(this.indent * 2));
        return this;
    }

    /**
     * Increases the indent.
     * Call before appending or creating a new line.
     *
     * @return A reference to this object.
     */
    public IntendingStringBuilder indent() {
        this.indent++;
        return this;
    }

    /**
     * Decreases the indent.
     * Call before appending or creating a new line.
     *
     * @return A reference to this object.
     */
    public IntendingStringBuilder outdent() {
        if (this.indent >= 1)
            this.indent--;
        return this;
    }

    /**
     * Returns a string representing the data in this sequence.
     * <p>
     * A new {@code String} object is allocated and initialized to contain the character sequence currently represented by this
     * object. This {@code String} is then returned. Subsequent changes to this sequence do not affect the contents of the {@code String}.
     *
     * @return A string representation of this sequence of characters.
     */
    @Override
    public String toString() {
        return this.builder.toString();
    }
}
