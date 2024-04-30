package at.jku.dke.task_app.sql.ra2sql.model;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;

import java.util.HashMap;
import java.util.Map;

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
     */
    public void addAttributeAlias(String attribute, String alias) {
        if (attribute == null || alias == null)
            return;

        attribute = attribute.toUpperCase().trim();
        alias = alias.toUpperCase().trim();
        this.attributeAliases.put(attribute, alias);
    }

    @Override
    public void calculateSchema(SchemaInfoDto schemaInfo) {
        super.calculateSchema(schemaInfo);

        this.attributeAliases.keySet().forEach(this::removeSchemaAttribute);
        this.attributeAliases.values().forEach(this::addSchemaAttribute);
    }

    /**
     * Returns whether the specified name is an alias.
     *
     * @param name The attribute name.
     * @return {@code true} if the name is an alias; {@code false} if the name is an attribute name.
     */
    public boolean isAlias(String name) {
        if (name == null)
            return false;
        return this.attributeAliases.containsValue(name.toUpperCase());
    }

    /**
     * Returns the attribute name for the specified alias.
     *
     * @param name The alias.
     * @return The attribute name, if found.
     */
    public String getAttributeForAlias(String name) {
        var result = this.attributeAliases.entrySet().stream().filter(x -> x.getValue().equalsIgnoreCase(name)).findFirst();
        return result.map(Map.Entry::getKey).orElse(null);
    }

    /**
     * Returns the alias for the specified attribute.
     *
     * @param name The attribute name.
     * @return The alias, if found.
     */
    public String getAliasForAttribute(String name) {
        if (name == null)
            return null;
        return this.attributeAliases.get(name.toUpperCase());
    }

    @Override
    public String toString() {
        return "RENAMING[" + String.join(",", this.attributeAliases.entrySet().stream().map(k -> k.getKey() + " <- " + k.getValue()).toList()) + "}(" + this.getExpression() + ")";
    }
}
