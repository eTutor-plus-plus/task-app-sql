package at.jku.dke.task_app.sql.ra2sql.model;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RenamingTest {

    @Test
    void addAttributeAlias() {
        // Arrange
        var renaming = new Renaming();

        // Act
        renaming.addAttributeAlias("att", "ali");

        // Assert
        assertTrue(renaming.isAlias("ali"));
    }

    @Test
    void addAttributeAlias_attributeNull() {
        // Arrange
        var renaming = new Renaming();

        // Act
        renaming.addAttributeAlias(null, "ali");

        // Assert
        assertFalse(renaming.isAlias("ali"));
    }

    @Test
    void addAttributeAlias_aliasNull() {
        // Arrange
        var renaming = new Renaming();

        // Act
        renaming.addAttributeAlias("att", null);

        // Assert
        assertNull(renaming.getAliasForAttribute("att"));
    }

    @Test
    void addAttributeAlias_alreadyExists() {
        // Arrange
        var renaming = new Renaming();
        renaming.addAttributeAlias("att", "ali");

        // Act
        renaming.addAttributeAlias("att", "ali2 ");

        // Assert
        assertEquals("ALI2", renaming.getAliasForAttribute("att"));
    }

    @Test
    void isAlias_null() {
        // Arrange
        var renaming = new Renaming();

        // Act
        var result = renaming.isAlias(null);

        // Assert
        assertFalse(result);
    }

    @Test
    void getAliasForAttribute_null() {
        // Arrange
        var renaming = new Renaming();

        // Act
        var result = renaming.getAliasForAttribute(null);

        // Assert
        assertNull(result);
    }

    @Test
    void getAttributeForAlias() {
        // Arrange
        var renaming = new Renaming();
        renaming.addAttributeAlias("att ", "ali");

        // Act
        var result = renaming.getAttributeForAlias("ali");

        // Assert
        assertEquals("ATT", result);
    }

    @Test
    void calculateSchema() {
        // Arrange
        var operator = new Renaming();
        operator.addSchemaAttribute("TO_REMOVE");
        operator.addAttributeAlias("att", "ali");

        var expression = new TestExpression("ATT");
        operator.setExpression(expression);

        var schema = new SchemaInfoDto(List.of());

        // Act
        operator.calculateSchema(schema);

        // Assert
        assertEquals(1, expression.calculateSchemaCallCount);
        assertThat(operator.getSchemaAttributes()).containsExactly("ALI");
    }

}
