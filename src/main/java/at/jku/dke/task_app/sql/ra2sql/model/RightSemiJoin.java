package at.jku.dke.task_app.sql.ra2sql.model;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;

/**
 * Represents a right semi-join.
 */
public class RightSemiJoin extends BinaryOperatorImpl {
    /**
     * Creates a new instance of class {@link RightSemiJoin}.
     */
    public RightSemiJoin() {
    }

    @Override
    public void calculateSchema(SchemaInfoDto schemaInfo) {
        this.removeAllSchemaAttributes();

        var left = this.getLeftExpression();
        var right = this.getRightExpression();

        if (left != null)
            left.calculateSchema(schemaInfo);

        if (right != null) {
            right.calculateSchema(schemaInfo);
            right.getSchemaAttributes().forEach(this::addSchemaAttribute);
        }
    }

    @Override
    public String toString() {
        return '(' + this.getLeftExpression().toString() + " RIGHTSEMI " + this.getRightExpression().toString() + ')';
    }
}
