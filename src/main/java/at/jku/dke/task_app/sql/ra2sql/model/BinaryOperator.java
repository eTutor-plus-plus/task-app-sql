package at.jku.dke.task_app.sql.ra2sql.model;

/**
 * Represents a binary operator.
 */
public interface BinaryOperator extends Expression {
    /**
     * Returns the expression on the left side of the binary operator.
     *
     * @return The left expression.
     */
    Expression getLeftExpression();

    /**
     * Returns the expression on the right side of the binary operator.
     *
     * @return The right expression.
     */
    Expression getRightExpression();

    /**
     * Sets the expression on the left side of the binary operator.
     *
     * @param expression The left expression.
     * @return {@code true} if the expression was set; {@code false} otherwise.
     */
    boolean setLeftExpression(Expression expression);

    /**
     * Sets the expression on the right side of the binary operator.
     *
     * @param expression The right expression.
     * @return {@code true} if the expression was set; {@code false} otherwise.
     */
    boolean setRightExpression(Expression expression);
}
