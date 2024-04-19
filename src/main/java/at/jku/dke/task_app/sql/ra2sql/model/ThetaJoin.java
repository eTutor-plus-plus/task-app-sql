package at.jku.dke.task_app.sql.ra2sql.model;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a theta join.
 */
public class ThetaJoin extends BinaryOperatorImpl {
    /**
     * Prefix for the left and side comparison.
     */
    public static final String LHS = "ls";

    /**
     * Prefix for the right hand side comparison.
     */
    public static final String RHS = "rs";

    private final List<Comparison> comparisons;

    /**
     * Creates a new instance of class {@link ThetaJoin}.
     */
    public ThetaJoin() {
        this.comparisons = new ArrayList<>();
    }

    /**
     * Adds a new comparison.
     *
     * @param comparison The comparison.
     * @return {@code true} if the comparison was added; {@code false} otherwise.
     */
    public boolean addComparison(Comparison comparison) {
        if (comparison != null) {
            return this.comparisons.add(comparison);
        }
        return false;
    }

    /**
     * Returns the comparisons of the theta join.
     *
     * @return The comparisons.
     */
    public List<Comparison> getComparisons() {
        return Collections.unmodifiableList(comparisons);
    }

    @Override
    public void calculateSchema(SchemaInfoDto schemaInfo) {
        this.removeAllSchemaAttributes();

        var left = this.getLeftExpression();
        var right = this.getRightExpression();

        if (left != null) {
            left.calculateSchema(schemaInfo);
            left.getSchemaAttributes().forEach(a -> this.addSchemaAttribute(LHS + "." + a));
        }

        if (right != null) {
            right.calculateSchema(schemaInfo);
            right.getSchemaAttributes().forEach(a -> this.addSchemaAttribute(RHS + "." + a));
        }
    }

    @Override
    public String toString() {
        return "(" + this.getLeftExpression() + " {" + String.join(",", this.comparisons.stream().map(Comparison::toString).toString()) + "} " + this.getRightExpression() + ')';
    }
}
