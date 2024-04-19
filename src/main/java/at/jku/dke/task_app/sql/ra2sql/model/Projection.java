package at.jku.dke.task_app.sql.ra2sql.model;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a projection.
 */
public class Projection extends UnaryOperatorImpl {
    private final List<String> projectionAttributes;

    /**
     * Creates a new instance of class {@link Projection}.
     */
    public Projection() {
        this.projectionAttributes = new ArrayList<>();
    }

    /**
     * Adds a new projection attribute.
     *
     * @param attribute The attribute name.
     * @return {@code true} if the attribute was added; {@code false} otherwise.
     */
    public boolean addProjectionAttribute(String attribute) {
        if (attribute != null) {
            attribute = attribute.toUpperCase().trim();
            return this.projectionAttributes.add(attribute);
        }
        return false;
    }

    @Override
    public void calculateSchema(SchemaInfoDto schemaInfo) {
        this.removeAllSchemaAttributes();

        if (this.getExpression() != null)
            this.getExpression().calculateSchema(schemaInfo);

        this.projectionAttributes.forEach(this::addSchemaAttribute);
    }
}
