package at.jku.dke.task_app.sql.evaluation.analyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Describes the column analysis.
 *
 * @see at.jku.dke.task_app.sql.evaluation.SqlEvaluationCriterion#CORRECT_COLUMNS
 */
public class ColumnCriterionAnalysis extends AbstractSqlCriterionAnalysis {

    private final List<String> missingColumns;
    private final List<String> superfluousColumns;

    /**
     * Creates a new instance of class {@link ColumnCriterionAnalysis}.
     */
    ColumnCriterionAnalysis() {
        this.missingColumns = new ArrayList<>();
        this.superfluousColumns = new ArrayList<>();
    }

    @Override
    public boolean isCriterionSatisfied() {
        return super.isCriterionSatisfied() && !this.hasMissingColumns() && !this.hasSuperfluousColumns();
    }

    /**
     * Returns whether there are missing columns.
     *
     * @return {@code true} if there are missing columns; {@code false} otherwise.
     */
    public boolean hasMissingColumns() {
        return !this.missingColumns.isEmpty();
    }

    /**
     * Returns whether there are superfluous columns.
     *
     * @return {@code true} if there are superfluous columns; {@code false} otherwise.
     */
    public boolean hasSuperfluousColumns() {
        return !this.superfluousColumns.isEmpty();
    }

    /**
     * Gets the missing columns.
     *
     * @return The missing columns.
     */
    public List<String> getMissingColumns() {
        return Collections.unmodifiableList(this.missingColumns);
    }

    /**
     * Gets the superfluous columns.
     *
     * @return The superfluous columns.
     */
    public List<String> getSuperfluousColumns() {
        return Collections.unmodifiableList(this.superfluousColumns);
    }

    /**
     * Adds a missing column.
     *
     * @param c The missing column.
     */
    void addMissingColumn(String c) {
        this.missingColumns.add(c);
    }

    /**
     * Adds a superfluous column.
     *
     * @param c The superfluous column.
     */
    void addSuperfluousColumn(String c) {
        this.superfluousColumns.add(c);
    }

}
