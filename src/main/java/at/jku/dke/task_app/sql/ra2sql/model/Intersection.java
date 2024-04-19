package at.jku.dke.task_app.sql.ra2sql.model;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;

/**
 * Represents an intersection.
 */
public class Intersection extends BinaryOperatorImpl {
    /**
     * Creates a new instance of class {@link Intersection}.
     */
    public Intersection() {
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
}
