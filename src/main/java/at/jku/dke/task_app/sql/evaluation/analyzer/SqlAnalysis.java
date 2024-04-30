package at.jku.dke.task_app.sql.evaluation.analyzer;

import at.jku.dke.task_app.sql.evaluation.SqlEvaluationCriterion;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the result of the analysis.
 */
public class SqlAnalysis {
    private QueryResult queryResult;
    private Throwable analysisException;
    private final Map<SqlEvaluationCriterion, SqlCriterionAnalysis> criterionAnalysis;

    /**
     * Creates a new instance of class {@link SqlAnalysis}.
     */
    public SqlAnalysis() {
        this.criterionAnalysis = new HashMap<>();
    }

    /**
     * Gets the query result of the submission.
     *
     * @return The query result of the submission.
     */
    public QueryResult getQueryResult() {
        return this.queryResult;
    }

    /**
     * Sets the query result of the submission.
     *
     * @param queryResult The query result of the submission.
     */
    void setQueryResult(QueryResult queryResult) {
        this.queryResult = queryResult;
    }

    /**
     * Gets the analysis exception.
     *
     * @return The analysis exception.
     */
    public Throwable getAnalysisException() {
        return this.analysisException;
    }

    /**
     * Sets the analysis exception.
     *
     * @param analysisException The analysis exception.
     */
    public void setAnalysisException(Throwable analysisException) {
        this.analysisException = analysisException;
    }

    /**
     * Gets the criterion analysis.
     *
     * @return The criterion analysis.
     */
    public Map<SqlEvaluationCriterion, SqlCriterionAnalysis> getCriterionAnalysis() {
        return Collections.unmodifiableMap(this.criterionAnalysis);
    }

    /**
     * Gets the criterion analysis for the given criterion.
     *
     * @param criterion The criterion.
     * @return The criterion analysis.
     */
    public SqlCriterionAnalysis getCriterionAnalysis(SqlEvaluationCriterion criterion) {
        return this.criterionAnalysis.get(criterion);
    }

    /**
     * Adds a new criterion analysis.
     *
     * @param key   The criterion.
     * @param value The analysis.
     */
    void addCriterionAnalysis(SqlEvaluationCriterion key, SqlCriterionAnalysis value) {
        this.criterionAnalysis.put(key, value);
    }
}
