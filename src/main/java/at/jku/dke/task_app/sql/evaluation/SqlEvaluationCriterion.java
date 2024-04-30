package at.jku.dke.task_app.sql.evaluation;

/**
 * The SQL evaluation criteria.
 */
public enum SqlEvaluationCriterion {
    /**
     * Tuples must be correct
     */
    CORRECT_TUPLES,

    /**
     * Tuples must be in correct order
     */
    CORRECT_ORDER,

    /**
     * Column count must be correct
     */
    CORRECT_COLUMNS,

    /**
     * Query must not contain syntax errors
     */
    CORRECT_SYNTAX,

    /**
     * Tries to detect whether a query produces a cartesian product
     */
    CARTESIAN_PRODUCT
}
