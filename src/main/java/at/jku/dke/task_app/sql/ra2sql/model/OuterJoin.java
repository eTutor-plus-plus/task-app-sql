package at.jku.dke.task_app.sql.ra2sql.model;

/**
 * Represents an outer join.
 */
public class OuterJoin extends BinaryOperatorImpl {
    /**
     * Creates a new instance of class {@link OuterJoin}.
     */
    public OuterJoin() {
    }

    @Override
    public String toString() {
        return '(' + this.getLeftExpression().toString() + " OUTER_JOIN " + this.getRightExpression().toString() + ')';
    }
}
