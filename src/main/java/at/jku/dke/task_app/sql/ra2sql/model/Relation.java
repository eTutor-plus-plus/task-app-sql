package at.jku.dke.task_app.sql.ra2sql.model;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;

/**
 * Represents a relation.
 */
public class Relation extends ExpressionImpl {

    private String name;

    /**
     * Creates a new instance of class {@link Relation}.
     */
    public Relation() {
        this.name = "";
    }

    /**
     * Gets the name of the relation.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the relation.
     *
     * @param name The name.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void calculateSchema(SchemaInfoDto schemaInfo) {
        this.removeAllSchemaAttributes();

        var table = schemaInfo.findTable(this.name);
        if (table.isEmpty())
            throw new IllegalArgumentException("Relation " + this.name + " does not exist!");

        table.get().columns().forEach(x -> this.addSchemaAttribute(x.name()));
    }
}
