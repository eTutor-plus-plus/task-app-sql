package at.jku.dke.task_app.sql.ra2sql.model;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExpressionImplTest {

    @Test
    void addSchemaAttribute() {
        // Arrange
        var expr = new ExpressionTestImpl();

        // Act
        var result = expr.addSchemaAttribute("test ");

        // Assert
        assertTrue(result);
        assertThat(expr.getSchemaAttributes()).containsExactly("TEST");
    }

    @Test
    void addSchemaAttribute_nullName() {
        // Arrange
        var expr = new ExpressionTestImpl();

        // Act
        var result = expr.addSchemaAttribute(null);

        // Assert
        assertFalse(result);
    }

    @Test
    void addSchemaAttribute_alreadyExists() {
        // Arrange
        var expr = new ExpressionTestImpl();
        expr.addSchemaAttribute("test");

        // Act
        var result = expr.addSchemaAttribute("test ");

        // Assert
        assertFalse(result);
    }

    @Test
    void addSchemaAttributes() {
        // Arrange
        var expr = new ExpressionTestImpl();
        var list = List.of("TeSt1", " test2");

        // Act
        expr.addSchemaAttributes(list);

        // Assert
        assertThat(expr.getSchemaAttributes()).containsExactly("TEST1", "TEST2");
    }

    @Test
    void addSchemaAttributes_nullValue() {
        // Arrange
        var expr = new ExpressionTestImpl();

        // Act
        expr.addSchemaAttributes(null);

        // Assert
        // no Exception thrown
    }

    @Test
    void removeSchemaAttribute() {
        // Arrange
        var expr = new ExpressionTestImpl();
        expr.addSchemaAttribute("test");

        // Act
        var result = expr.removeSchemaAttribute(" TESt");

        // Assert
        assertTrue(result);
        assertThat(expr.getSchemaAttributes()).doesNotContain("TEST");
    }

    @Test
    void removeSchemaAttribute_notExists() {
        // Arrange
        var expr = new ExpressionTestImpl();

        // Act
        var result = expr.removeSchemaAttribute(" TESt");

        // Assert
        assertFalse(result);
    }

    @Test
    void removeSchemaAttribute_nullValue() {
        // Arrange
        var expr = new ExpressionTestImpl();

        // Act
        var result = expr.removeSchemaAttribute(null);

        // Assert
        assertFalse(result);
    }

    @Test
    void removeAllSchemaAttributes() {
        // Arrange
        var expr = new ExpressionTestImpl();
        expr.addSchemaAttribute("test");

        // Act
        expr.removeAllSchemaAttributes();

        // Assert
        assertThat(expr.getSchemaAttributes()).isEmpty();
    }

    @Test
    void containsSchemaAttribute_true() {
        // Arrange
        var expr = new ExpressionTestImpl();
        expr.addSchemaAttribute("test");

        // Act
        var result = expr.containsSchemaAttribute("TesT ");

        // Assert
        assertTrue(result);
    }

    @Test
    void containsSchemaAttribute_false() {
        // Arrange
        var expr = new ExpressionTestImpl();
        expr.addSchemaAttribute("test");

        // Act
        var result = expr.containsSchemaAttribute("TesT_2");

        // Assert
        assertFalse(result);
    }

    private static class ExpressionTestImpl extends ExpressionImpl {
        @Override
        public void calculateSchema(SchemaInfoDto schemaInfo) {
        }
    }
}
