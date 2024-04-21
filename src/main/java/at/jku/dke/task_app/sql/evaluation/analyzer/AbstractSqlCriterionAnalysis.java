package at.jku.dke.task_app.sql.evaluation.analyzer;

/**
 * Base class for criterion analysis.
 */
public abstract class AbstractSqlCriterionAnalysis implements SqlCriterionAnalysis {
    private AnalysisException exception;

    /**
     * Creates a new instance of class {@link AnalysisException}.
     */
    protected AbstractSqlCriterionAnalysis() {
    }

    /**
     * Gets the exception thrown by the analysis.
     *
     * @return The exception.
     */
    public AnalysisException getException() {
        return exception;
    }

    /**
     * Sets the exception thrown by the analysis.
     *
     * @param exception The exception.
     */
    public void setException(AnalysisException exception) {
        this.exception = exception;
    }

    /**
     * Returns whether the criterion is satisfied.
     *
     * @return {@code true} if the criterion is satisfied; {@code false} otherwise.
     */
    @Override
    public boolean isCriterionSatisfied() {
        return this.exception == null;
    }
}
