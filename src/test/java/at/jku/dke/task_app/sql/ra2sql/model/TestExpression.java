package at.jku.dke.task_app.sql.ra2sql.model;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;

class TestExpression extends ExpressionImpl {
    int calculateSchemaCallCount = 0;

    public TestExpression() {
    }

    public TestExpression(String... schemaAttributes) {
        for (String schemaAttribute : schemaAttributes) {
            this.addSchemaAttribute(schemaAttribute);
        }
    }

    @Override
    public void calculateSchema(SchemaInfoDto schemaInfo) {
        this.calculateSchemaCallCount++;
    }
}
