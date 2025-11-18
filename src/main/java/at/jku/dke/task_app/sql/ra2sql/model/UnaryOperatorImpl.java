package at.jku.dke.task_app.sql.ra2sql.model;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;

/**
 * Default implementation of {@link UnaryOperator}.
 */
public abstract class UnaryOperatorImpl extends ExpressionImpl implements UnaryOperator {

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

    @Override
    public void calculateSchema(SchemaInfoDto schemaInfo) {
        this.removeAllSchemaAttributes();

        if (this.expression != null) {
            this.expression.calculateSchema(schemaInfo);
            this.expression.getSchemaAttributes().forEach(this::addSchemaAttribute);
        }
    }

    @Override
    public String toString() {

        return this.expression.toString();
    }
}
