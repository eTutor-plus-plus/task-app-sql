package at.jku.dke.task_app.sql.ra2sql.model;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;

/**
 * Represents a left semi-join.
 */
public class LeftSemiJoin extends BinaryOperatorImpl {
    /**
     * Creates a new instance of class {@link LeftSemiJoin}.
     */
    public LeftSemiJoin() {
    }

    @Override
    public void calculateSchema(SchemaInfoDto schemaInfo) {
        this.removeAllSchemaAttributes();

        var left = this.getLeftExpression();
        var right = this.getRightExpression();

        if (right != null)
            right.calculateSchema(schemaInfo);

        if (left != null) {
            left.calculateSchema(schemaInfo);
            left.getSchemaAttributes().forEach(this::addSchemaAttribute);
        }
    }

    @Override
    public String toString() {
        return '(' + this.getLeftExpression().toString() + " LEFTSEMI " + this.getRightExpression().toString() + ')';
    }
}
