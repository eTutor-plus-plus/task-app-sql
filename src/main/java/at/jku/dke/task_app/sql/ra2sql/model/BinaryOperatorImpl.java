package at.jku.dke.task_app.sql.ra2sql.model;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;

/**
 * Default implementation of {@link BinaryOperator}.
 */
public abstract class BinaryOperatorImpl extends ExpressionImpl implements BinaryOperator {

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

    @Override
    public void calculateSchema(SchemaInfoDto schemaInfo) {
        this.removeAllSchemaAttributes();

        if (this.left != null) {
            this.left.calculateSchema(schemaInfo);
            this.left.getSchemaAttributes().forEach(this::addSchemaAttribute);
        }
        if (this.right != null) {
            this.right.calculateSchema(schemaInfo);
            this.right.getSchemaAttributes().forEach(this::addSchemaAttribute);
        }
    }

    @Override
    public String toString() {
        return '(' + left.toString() + " " + right.toString() + ')';
    }
}
