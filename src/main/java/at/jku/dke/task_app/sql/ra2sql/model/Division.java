package at.jku.dke.task_app.sql.ra2sql.model;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;

/**
 * Represents a division.
 */
public class Division extends BinaryOperatorImpl {
    /**
     * Creates a new instance of class {@link Division}.
     */
    public Division() {
    }

    @Override
    public void calculateSchema(SchemaInfoDto schemaInfo) {
        this.removeAllSchemaAttributes();

        var left = this.getLeftExpression();
        var right = this.getRightExpression();
        if (left != null && right != null) {
            left.calculateSchema(schemaInfo);
            right.calculateSchema(schemaInfo);
            for (var att : left.getSchemaAttributes()) {
                if (!right.containsSchemaAttribute(att))
                    this.addSchemaAttribute(att);
            }
        }
    }

    @Override
    public String toString() {
        return '(' + this.getLeftExpression().toString() + " DIVISION " + this.getRightExpression().toString() + ')';
    }
}
