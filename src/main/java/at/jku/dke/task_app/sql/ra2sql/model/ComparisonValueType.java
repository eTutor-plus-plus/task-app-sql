package at.jku.dke.task_app.sql.ra2sql.model;

import java.util.Objects;

/**
 * Represents a value type.
 */
public class ComparisonValueType {
    /**
     * Value type: date
     */
    public static final ComparisonValueType DATE = new ComparisonValueType("DATE");

    /**
     * Value type: number
     */
    public static final ComparisonValueType NUMBER = new ComparisonValueType("NUMBER");

    /**
     * Value type: literal
     */
    public static final ComparisonValueType LITERAL = new ComparisonValueType("LITERAL");

    /**
     * Value type: attribute
     */
    public static final ComparisonValueType ATTRIBUTE = new ComparisonValueType("ATTRIBUTE");

    private final String name;

    /**
     * Creates a new instance of class {@link ComparisonValueType}.
     *
     * @param name The name of the type.
     */
    protected ComparisonValueType(String name) {
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
        ComparisonValueType that = (ComparisonValueType) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
