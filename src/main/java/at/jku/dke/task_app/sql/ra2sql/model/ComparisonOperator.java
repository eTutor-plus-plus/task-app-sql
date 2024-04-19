package at.jku.dke.task_app.sql.ra2sql.model;

import java.util.Objects;

/**
 * Represents a comparison operator.
 */
public class ComparisonOperator {
    /**
     * Comparison: equal
     */
    public static final ComparisonOperator EQUAL = new ComparisonOperator("EQUAL");

    /**
     * Comparison: not equal
     */
    public static final ComparisonOperator NOT_EQUAL = new ComparisonOperator("NOT_EQUAL");

    /**
     * Comparison: less than
     */
    public static final ComparisonOperator LESS_THAN = new ComparisonOperator("LESS_THAN");

    /**
     * Comparison: greater than
     */
    public static final ComparisonOperator GREATER_THAN = new ComparisonOperator("GREATER_THAN");

    /**
     * Comparison: less than or equal
     */
    public static final ComparisonOperator LESS_OR_EQUAL = new ComparisonOperator("LT_OR_EQUAL");

    /**
     * Comparison: greater than or equal
     */
    public static final ComparisonOperator GREATER_OR_EQUAL = new ComparisonOperator("GT_OR_EQUAL");

    private final String name;

    /**
     * Creates a new instance of class {@link ComparisonOperator}.
     *
     * @param name The name of the operator.
     */
    protected ComparisonOperator(String name) {
        this.name = name;
    }

    /**
     * Gets the name.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComparisonOperator that = (ComparisonOperator) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return switch (this.name) {
            case "EQUAL" -> "=";
            case "NOT_EQUAL" -> "<>";
            case "LESS_THAN" -> "<";
            case "GREATER_THAN" -> ">";
            case "GT_OR_EQUAL" -> ">=";
            case "LT_OR_EQUAL" -> "<=";
            default -> this.name;
        };
    }
}
