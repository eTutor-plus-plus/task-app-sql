package at.jku.dke.task_app.sql.evaluation.analyzer;

/**
 * Interface defining methods for an analysis with regard to an {@link at.jku.dke.task_app.sql.evaluation.SqlEvaluationCriterion}.
 */
public interface SqlCriterionAnalysis {
    /**
     * Returns whether the criterion is satisfied.
     *
     * @return {@code true} if the criterion is satisfied; {@code false} otherwise.
     */
    boolean isCriterionSatisfied();
}
