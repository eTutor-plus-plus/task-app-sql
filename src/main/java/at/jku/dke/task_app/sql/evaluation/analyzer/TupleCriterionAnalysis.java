package at.jku.dke.task_app.sql.evaluation.analyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Describes the tuple analysis.
 *
 * @see at.jku.dke.task_app.sql.evaluation.SqlEvaluationCriterion#CORRECT_TUPLES
 */
public class TupleCriterionAnalysis extends AbstractSqlCriterionAnalysis {
    private final List<List<String>> missingTuples;
    private final List<List<String>> superfluousTuples;
    private List<String> columnLabels;

    /**
     * Creates a new instance of class {@link TupleCriterionAnalysis}.
     */
    TupleCriterionAnalysis() {
        this.missingTuples = new ArrayList<>();
        this.superfluousTuples = new ArrayList<>();
        this.columnLabels = new ArrayList<>();
    }

    @Override
    public boolean isCriterionSatisfied() {
        return super.isCriterionSatisfied() && !this.hasMissingTuples() && !this.hasSuperfluousTuples();
    }

    /**
     * Returns whether there are missing tuples.
     *
     * @return {@code true} if there are missing tuples; {@code false} otherwise.
     */
    public boolean hasMissingTuples() {
        return !this.missingTuples.isEmpty();
    }

    /**
     * Returns whether there are superfluous tuples.
     *
     * @return {@code true} if there are superfluous tuples; {@code false} otherwise.
     */
    public boolean hasSuperfluousTuples() {
        return !this.superfluousTuples.isEmpty();
    }

    /**
     * Gets the missing tuples.
     *
     * @return The missing tuples.
     */
    public List<List<String>> getMissingTuples() {
        return Collections.unmodifiableList(this.missingTuples);
    }

    /**
     * Gets the superfluous tuples.
     *
     * @return The superfluous tuples.
     */
    public List<List<String>> getSuperfluousTuples() {
        return Collections.unmodifiableList(this.superfluousTuples);
    }

    /**
     * Adds a missing tuple.
     *
     * @param tuple The missing tuple.
     */
    void addMissingTuple(List<String> tuple) {
        this.missingTuples.add(Collections.unmodifiableList(tuple));
    }

    /**
     * Adds a superfluous tuple.
     *
     * @param tuple The superfluous tuple.
     */
    void addSuperfluousTuple(List<String> tuple) {
        this.superfluousTuples.add(Collections.unmodifiableList(tuple));
    }

    /**
     * Gets the column labels.
     *
     * @return The column labels.
     */
    public List<String> getColumnLabels() {
        return Collections.unmodifiableList(columnLabels);
    }

    /**
     * Sets the column labels.
     *
     * @param columnLabels The column labels.
     */
    void setColumnLabels(List<String> columnLabels) {
        this.columnLabels = columnLabels;
    }

    /**
     * Appends a new column name.
     *
     * @param column The column to be added.
     */
    void addColumnLabel(String column) {
        this.columnLabels.add(column);
    }
}
