package at.jku.dke.task_app.sql.ra2sql.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a theta join.
 */
public class ThetaJoin extends BinaryOperatorImpl {
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
}
