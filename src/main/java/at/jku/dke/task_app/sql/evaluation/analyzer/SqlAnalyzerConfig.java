package at.jku.dke.task_app.sql.evaluation.analyzer;

import at.jku.dke.task_app.sql.evaluation.SqlEvaluationCriterion;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Configuration for the SQL analyzer.
 */
public class SqlAnalyzerConfig {
    private int feedbackLevel;
    private final Set<SqlEvaluationCriterion> criteria;
    private final String solution;
    private final String schema;

    /**
     * Creates a new instance of class {@link SqlAnalyzerConfig}.
     *
     * @param solution The solution.
     * @param schema   The schema.
     */
    public SqlAnalyzerConfig(String solution, String schema) {
        this.schema = schema;
        this.criteria = new HashSet<>();
        this.solution = solution.endsWith(";") ? solution.substring(0, solution.length() - 1) : solution;
    }

    /**
     * Gets the solution.
     *
     * @return The solution.
     */
    String getSolution() {
        return this.solution;
    }

    /**
     * Gets the schema.
     *
     * @return The schema.
     */
    String getSchema() {
        return this.schema;
    }

    /**
     * Gets the feedback level.
     *
     * @return The feedback level.
     */
    int getFeedbackLevel() {
        return this.feedbackLevel;
    }

    /**
     * Sets the feedback level.
     *
     * @param feedbackLevel The feedback level.
     */
    public void setFeedbackLevel(int feedbackLevel) {
        this.feedbackLevel = feedbackLevel;
    }

    /**
     * Gets the criteria.
     *
     * @return The criteria.
     */
    Set<SqlEvaluationCriterion> getCriteria() {
        return Collections.unmodifiableSet(this.criteria);
    }

    /**
     * Adds a criterion.
     *
     * @param criterion The criterion.
     */
    public void addCriterion(SqlEvaluationCriterion criterion) {
        this.criteria.add(criterion);
    }

    /**
     * Returns whether the configuration contains the specified criterion.
     *
     * @param criterion The criterion.
     * @return {@code true} if the configuration contains the criterion; {@code false} otherwise.
     */
    boolean containsCriterion(SqlEvaluationCriterion criterion) {
        return criteria.contains(criterion);
    }
}
