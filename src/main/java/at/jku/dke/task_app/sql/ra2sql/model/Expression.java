package at.jku.dke.task_app.sql.ra2sql.model;

import java.util.Collection;
import java.util.List;

/**
 * Base for all expressions.
 */
public interface Expression {
    /**
     * Returns the schema attributes.
     *
     * @return The schema attributes.
     */
    List<String> getSchemaAttributes();

    /**
     * Adds a new schema attribute.
     *
     * @param attribute The attribute to add.
     * @return {@code true} if the attribute was added; {@code false} otherwise.
     */
    boolean addSchemaAttribute(String attribute);

    /**
     * Adds a list of schema attributes.
     *
     * @param attributes The attributes to add.
     * @see #addSchemaAttribute(String)
     */
    void addSchemaAttributes(Collection<String> attributes);

    /**
     * Removes the schema attribute.
     *
     * @param attribute The attribute to remove.
     * @return {@code true} if the attribute was removed; {@code false} otherwise.
     */
    boolean removeSchemaAttribute(String attribute);

    /**
     * Removes all schema attributes.
     */
    void removeAllSchemaAttributes();

    /**
     * Returns {@code true} if this expression contains the specified attribute.
     *
     * @param attribute The attribute whose presence in this expression is to be tested.
     * @return {@code true} if this list contains the specified element; {@code false} otherwise.
     */
    boolean containsSchemaAttribute(String attribute);
}
