package at.jku.dke.task_app.sql.data.converters;

import at.jku.dke.task_app.sql.data.entities.TaskType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTypeConverterTest {

    //#region --- convertToDatabaseColumn ---
    @Test
    void testConvertToDatabaseColumn() {
        // Arrange
        var converter = new TaskTypeConverter();
        var type = TaskType.SQL;

        // Act
        var result = converter.convertToDatabaseColumn(type);

        // Assert
        assertEquals("sql", result);
    }

    @Test
    void testConvertToDatabaseColumnNullValue() {
        // Arrange
        var converter = new TaskTypeConverter();

        // Act
        var result = converter.convertToDatabaseColumn(null);

        // Assert
        assertNull(result);
    }
    //#endregion

    //#region --- convertToEntityAttribute ---
    @Test
    void convertToEntityAttribute() {
        // Arrange
        var converter = new TaskTypeConverter();
        var type = TaskType.RELALG;

        // Act
        var result = converter.convertToEntityAttribute("relalg");

        // Assert
        assertEquals(type, result);
    }

    @Test
    void convertToEntityAttributeNullValue() {
        // Arrange
        var converter = new TaskTypeConverter();

        // Act
        var result = converter.convertToEntityAttribute(null);

        // Assert
        assertNull(result);
    }

    @Test
    void convertToEntityAttributeInvalidValueThrowsException() {
        // Arrange
        var converter = new TaskTypeConverter();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntityAttribute("invalid"));
    }
    //#endregion
}
