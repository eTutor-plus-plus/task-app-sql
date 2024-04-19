package at.jku.dke.task_app.sql.ra2sql.model;

/**
 * Represents a unary operator.
 */
public interface UnaryOperator extends Expression {
    /**
     * Gets the operand expression.
     *
     * @return The expression.
     */
    Expression getExpression();

    /**
     * Sets the operand expression.
     *
     * @param expression The expression.
     * @return {@code true} if the expression was set; {@code false} otherwise.
     */
    boolean setExpression(Expression expression);
}
