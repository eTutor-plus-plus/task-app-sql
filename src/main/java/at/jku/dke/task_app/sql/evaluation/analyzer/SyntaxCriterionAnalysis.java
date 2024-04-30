package at.jku.dke.task_app.sql.evaluation.analyzer;

/**
 * Describes the syntax analysis.
 *
 * @see at.jku.dke.task_app.sql.evaluation.SqlEvaluationCriterion#CORRECT_SYNTAX
 */
public class SyntaxCriterionAnalysis implements SqlCriterionAnalysis {

    private final Throwable exception;

    /**
     * Creates a new instance of class Syntax criterion analysis.
     *
     * @param exception The exception or {@code null} if the syntax is valid..
     */
    SyntaxCriterionAnalysis(Throwable exception) {
        this.exception = exception;
    }

    @Override
    public boolean isCriterionSatisfied() {
        return this.exception == null;
    }

    /**
     * Gets the exception that describes the syntax error.
     * <p>
     * Only returns a value if {@link #isCriterionSatisfied()} returns {@code false}.
     *
     * @return The exception.
     */
    public Throwable getException() {
        return exception;
    }

}
