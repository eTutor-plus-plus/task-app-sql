package at.jku.dke.task_app.sql.ra2sql.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Default implementation of the {@link Expression} interface.
 */
public class ExpressionImpl implements Expression {
    private final List<String> schemaAttributes;

    /**
     * Creates a new instance of class {@link ExpressionImpl}.
     */
    public ExpressionImpl() {
        this.schemaAttributes = new ArrayList<>();
    }

    @Override
    public List<String> getSchemaAttributes() {
        return Collections.unmodifiableList(this.schemaAttributes);
    }

    @Override
    public boolean addSchemaAttribute(String attribute) {
        if (attribute == null)
            return false;

        attribute = attribute.toUpperCase().trim();
        if (!this.schemaAttributes.contains(attribute))
            return schemaAttributes.add(attribute);
        return false;
    }

    @Override
    public void addSchemaAttributes(Collection<String> attributes) {
        if (attributes == null)
            return;
        attributes.forEach(this::addSchemaAttribute);
    }

    @Override
    public boolean removeSchemaAttribute(String attribute) {
        if (attribute == null)
            return false;
        return schemaAttributes.remove(attribute.toUpperCase().trim());
    }

    @Override
    public void removeAllSchemaAttributes() {
        this.schemaAttributes.clear();
    }

    @Override
    public boolean containsSchemaAttribute(String attribute) {
        return schemaAttributes.contains(attribute.toUpperCase().trim());
    }
}
