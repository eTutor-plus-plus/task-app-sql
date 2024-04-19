package at.jku.dke.task_app.sql.ra2sql.model;

/**
 * Represents a value type.
 */
public enum ComparisonValueType {
    /**
     * Value type: date
     */
    DATE("DATE"),

    /**
     * Value type: number
     */
    NUMBER("NUMBER"),

    /**
     * Value type: literal
     */
    LITERAL("LITERAL"),

    /**
     * Value type: attribute
     */
    ATTRIBUTE("ATTRIBUTE");

    private final String name;

    /**
     * Creates a new instance of class {@link ComparisonValueType}.
     *
     * @param name The name of the type.
     */
    ComparisonValueType(String name) {
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
}
