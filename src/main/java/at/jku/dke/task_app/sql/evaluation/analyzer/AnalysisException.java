package at.jku.dke.task_app.sql.evaluation.analyzer;

/**
 * Represents an exception that gets thrown during the analysis.
 */
public class AnalysisException extends Exception {
    /**
     * Creates a new instance of class {@link AnalysisException}.
     *
     * @param message The detail message.
     */
    public AnalysisException(String message) {
        super(message);
    }

    /**
     * Creates a new instance of class {@link AnalysisException}.
     *
     * @param message The detail message.
     * @param cause   The cause. (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public AnalysisException(String message, Throwable cause) {
        super(message, cause);
    }
}
