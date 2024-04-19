package at.jku.dke.task_app.sql.ra2sql.model;

/**
 * Default implementation of {@link UnaryOperator}.
 */
public class UnaryOperatorImpl extends ExpressionImpl implements UnaryOperator {

    private Expression expression;

    /**
     * Creates a new instance of class {@link UnaryOperatorImpl}.
     */
    public UnaryOperatorImpl() {
    }

    @Override
    public Expression getExpression() {
        return this.expression;
    }

    @Override
    public boolean setExpression(Expression expression) {
        if (expression == null)
            return false;
        this.expression = expression;
        return true;
    }
}
