package at.jku.dke.task_app.sql.evaluation.analyzer;

/**
 * Describes the cartesian product analysis.
 *
 * @see at.jku.dke.task_app.sql.evaluation.SqlEvaluationCriterion#CARTESIAN_PRODUCT
 */
public class CartesianProductCriterionAnalysis extends AbstractSqlCriterionAnalysis {

    private boolean cartesianProductSuspected;

    /**
     * Creates a new instance of class {@link CartesianProductCriterionAnalysis}.
     */
    CartesianProductCriterionAnalysis() {
        this.cartesianProductSuspected = false;
    }

    /**
     * Returns whether a cartesian product is suspected.
     *
     * @return {@code true}  whether a cartesian product is suspected; {@code false} otherwise.
     */
    public boolean isCartesianProductSuspected() {
        return cartesianProductSuspected;
    }

    /**
     * Sets whether a cartesian product is suspected.
     *
     * @param cartesianProductSuspected {@code true} whether a cartesian product is suspected; {@code false} otherwise.
     */
    void setCartesianProductSuspected(boolean cartesianProductSuspected) {
        this.cartesianProductSuspected = cartesianProductSuspected;
    }

    @Override
    public boolean isCriterionSatisfied() {
        return super.isCriterionSatisfied() && !this.cartesianProductSuspected;
    }

}
