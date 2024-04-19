package at.jku.dke.task_app.sql.ra2sql.model;

/**
 * Default implementation of {@link BinaryOperator}.
 */
public class BinaryOperatorImpl extends ExpressionImpl implements BinaryOperator {

    private Expression left;
    private Expression right;

    /**
     * Creates a new instance of class {@link BinaryOperatorImpl}.
     */
    public BinaryOperatorImpl() {
    }

    @Override
    public Expression getLeftExpression() {
        return this.left;
    }

    @Override
    public Expression getRightExpression() {
        return right;
    }

    @Override
    public boolean setLeftExpression(Expression expression) {
        if (expression == null)
            return false;
        this.left = expression;
        return true;
    }

    @Override
    public boolean setRightExpression(Expression expression) {
        if (expression == null)
            return false;
        this.right = expression;
        return true;
    }
}
