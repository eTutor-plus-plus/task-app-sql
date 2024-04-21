package at.jku.dke.task_app.sql.evaluation.analyzer;

/**
 * Describes the order analysis.
 *
 * @see at.jku.dke.task_app.sql.evaluation.SqlEvaluationCriterion#CORRECT_ORDER
 */
public class OrderCriterionAnalysis extends AbstractSqlCriterionAnalysis {

    private boolean orderingIncorrect;

    /**
     * Creates a new instance of class {@link OrderCriterionAnalysis}.
     */
    OrderCriterionAnalysis() {
        this.orderingIncorrect = false;
    }

    /**
     * Returns whether the ordering is incorrect.
     *
     * @return {@code true} if the ordering is incorrect; {@code false} otherwise.
     */
    public boolean isOrderingIncorrect() {
        return orderingIncorrect;
    }

    /**
     * Sets whether the ordering is incorrect.
     *
     * @param orderingIncorrect {@code true} if the ordering is incorrect; {@code false} otherwise.
     */
    void setOrderingIncorrect(boolean orderingIncorrect) {
        this.orderingIncorrect = orderingIncorrect;
    }

    @Override
    public boolean isCriterionSatisfied() {
        return super.isCriterionSatisfied() && !this.orderingIncorrect;
    }

}
