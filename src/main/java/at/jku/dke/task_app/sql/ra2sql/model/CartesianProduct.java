package at.jku.dke.task_app.sql.ra2sql.model;

/**
 * Represents a cartesian product.
 */
public class CartesianProduct extends BinaryOperatorImpl {
    /**
     * Creates a new instance of class {@link CartesianProduct}.
     */
    public CartesianProduct() {
    }

    @Override
    public String toString() {
        return '(' + this.getLeftExpression().toString() + " CARTESIANPRODUCT " + this.getRightExpression().toString() + ')';
    }
}
