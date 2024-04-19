package at.jku.dke.task_app.sql.ra2sql.model;

/**
 * Represents a comparison operator.
 */
public enum ComparisonOperator {
    /**
     * Comparison: equal
     */
    EQUAL("EQUAL"),

    /**
     * Comparison: not equal
     */
    NOT_EQUAL("NOT_EQUAL"),

    /**
     * Comparison: less than
     */
    LESS_THAN("LESS_THAN"),

    /**
     * Comparison: greater than
     */
    GREATER_THAN("GREATER_THAN"),

    /**
     * Comparison: less than or equal
     */
    LESS_OR_EQUAL("LT_OR_EQUAL"),

    /**
     * Comparison: greater than or equal
     */
    GREATER_OR_EQUAL("GT_OR_EQUAL");

    private final String name;

    /**
     * Creates a new instance of class {@link ComparisonOperator}.
     *
     * @param name The name of the operator.
     */
    ComparisonOperator(String name) {
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
