package at.jku.dke.task_app.sql.data.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SqlRaTaskGroupTest {

    @Test
    void testGetSetDdlStatements() {
        // Arrange
        var task = new SqlRaTaskGroup();
        final String value = "ddlStatements";

        // Act
        task.setDdlStatements(value);
        var result = task.getDdlStatements();

        // Assert
        assertEquals(value, result);
    }

    @Test
    void testGetSetDiagnoseDmlStatements() {
        // Arrange
        var task = new SqlRaTaskGroup();
        final String value = "diagnoseDmlStatements";

        // Act
        task.setDiagnoseDmlStatements(value);
        var result = task.getDiagnoseDmlStatements();

        // Assert
        assertEquals(value, result);
    }

    @Test
    void testGetSetSubmitDmlStatements() {
        // Arrange
        var task = new SqlRaTaskGroup();
        final String value = "submitDmlStatements";

        // Act
        task.setSubmitDmlStatements(value);
        var result = task.getSubmitDmlStatements();

        // Assert
        assertEquals(value, result);
    }

    @Test
    void testGetSetSchemaName() {
        // Arrange
        var task = new SqlRaTaskGroup();
        final String value = "schemaName";

        // Act
        task.setSchemaName(value);
        var result = task.getSchemaName();

        // Assert
        assertEquals(value, result);
    }

    @Test
    void testGetSetSchemaDescription() {
        // Arrange
        var task = new SqlRaTaskGroup();
        final String value = "description";

        // Act
        task.setSchemaDescription(value);
        var result = task.getSchemaDescription();

        // Assert
        assertEquals(value, result);
    }

}
