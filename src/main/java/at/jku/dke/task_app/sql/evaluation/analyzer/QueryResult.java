package at.jku.dke.task_app.sql.evaluation.analyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a query result.
 */
public class QueryResult {
    private final List<String> columnNames;
    private final List<List<String>> tuples;

    /**
     * Creates a new instance of class {@link QueryResult}.
     */
    QueryResult() {
        this.columnNames = new ArrayList<>();
        this.tuples = new ArrayList<>();
    }

    /**
     * Creates a new instance of class {@link QueryResult}.
     *
     * @param columnNames The column names.
     * @param tuples      The tuples.
     */
    public QueryResult(List<String> columnNames, List<List<String>> tuples) {
        this.columnNames = columnNames;
        this.tuples = tuples;
    }

    /**
     * Gets the column names.
     *
     * @return The column names.
     */
    public List<String> getColumnNames() {
        return Collections.unmodifiableList(columnNames);
    }

    /**
     * Gets the tuples.
     *
     * @return The tuples.
     */
    public List<List<String>> getTuples() {
        return Collections.unmodifiableList(this.tuples);
    }

    /**
     * Adds a column.
     *
     * @param columnName The column name.
     */
    void addColumn(String columnName) {
        this.columnNames.add(columnName.toUpperCase().trim());
    }

    /**
     * Adds a new tuple.
     *
     * @param data The tuple data.
     */
    void addTuple(List<String> data) {
        this.tuples.add(Collections.unmodifiableList(data));
    }
}
