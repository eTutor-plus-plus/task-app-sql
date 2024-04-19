package at.jku.dke.task_app.sql.ra2sql.model;

/**
 * Represents a join.
 */
public class Join extends BinaryOperatorImpl {
    /**
     * Creates a new instance of class {@link Join}.
     */
    public Join() {
    }

    @Override
    public String toString() {
        return '(' + this.getLeftExpression().toString() + " JOIN " + this.getRightExpression().toString() + ')';
    }
}
