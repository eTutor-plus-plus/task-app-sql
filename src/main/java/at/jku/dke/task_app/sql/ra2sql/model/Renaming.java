package at.jku.dke.task_app.sql.ra2sql.model;

import java.util.HashMap;

/**
 * Represents a renaming operation.
 */
public class Renaming extends UnaryOperatorImpl {
    private final HashMap<String, String> attributeAliases;

    /**
     * Creates a new instance of class {@link Renaming}.
     */
    public Renaming() {
        this.attributeAliases = new HashMap<>();
    }

    /**
     * Adds a new attribute alias.
     *
     * @param attribute The attribute name.
     * @param alias     The alias of the attribute.
     * @return {@code true} if the alias was added; {@code false} otherwise.
     */
    public boolean addAttributeAlias(String attribute, String alias) {
        if (attribute != null && alias != null) {
            attribute = attribute.toUpperCase().trim();
            alias = alias.toUpperCase().trim();
            return this.attributeAliases.put(attribute, alias) != null;
        }
        return false;
    }
}
