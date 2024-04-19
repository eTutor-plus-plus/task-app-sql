package at.jku.dke.task_app.sql.ra2sql;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IntendingStringBuilderTest {

    @Test
    void constructor() {
        // Act
        var intendingStringBuilder = new IntendingStringBuilder("test");

        // Act
        assertEquals("test", intendingStringBuilder.toString());
    }

    @Test
    void appendObject() {
        // Arrange
        var intendingStringBuilder = new IntendingStringBuilder();
        var obj = new Object();

        // Act
        var result = intendingStringBuilder.append(obj);

        // Assert
        assertEquals(intendingStringBuilder, result);
        assertEquals(obj.toString(), intendingStringBuilder.toString());
    }

    @Test
    void appendChar() {
        // Arrange
        var intendingStringBuilder = new IntendingStringBuilder();
        var obj = new Object();

        // Act
        var result = intendingStringBuilder.append('z');

        // Assert
        assertEquals(intendingStringBuilder, result);
        assertEquals("z", intendingStringBuilder.toString());
    }

    @Test
    void appendString() {
        // Arrange
        var intendingStringBuilder = new IntendingStringBuilder();
        var obj = new Object();

        // Act
        var result = intendingStringBuilder.append("Test");

        // Assert
        assertEquals(intendingStringBuilder, result);
        assertEquals("Test", intendingStringBuilder.toString());
    }

    @Test
    void newLine() {
        // Arrange
        var intendingStringBuilder = new IntendingStringBuilder();

        // Act
        var result = intendingStringBuilder.newLine();

        // Assert
        assertEquals(intendingStringBuilder, result);
        assertEquals(System.lineSeparator(), intendingStringBuilder.toString());
    }

    @Test
    void newLine_intended() {
        // Arrange
        var intendingStringBuilder = new IntendingStringBuilder();

        // Act
        var result = intendingStringBuilder.indent().newLine();

        // Assert
        assertEquals(intendingStringBuilder, result);
        assertEquals(System.lineSeparator() + "  ", intendingStringBuilder.toString());
    }

    @Test
    void indent() {
        // Arrange
        var intendingStringBuilder = new IntendingStringBuilder();

        // Act
        var result = intendingStringBuilder.indent().indent();
        intendingStringBuilder.newLine();

        // Assert
        assertEquals(intendingStringBuilder, result);
        assertEquals(System.lineSeparator() + "    ", intendingStringBuilder.toString());
    }

    @Test
    void outdent() {
        // Arrange
        var intendingStringBuilder = new IntendingStringBuilder();

        // Act
        var result = intendingStringBuilder.indent().outdent();
        intendingStringBuilder.newLine();

        // Assert
        assertEquals(intendingStringBuilder, result);
        assertEquals(System.lineSeparator(), intendingStringBuilder.toString());
    }

    @Test
    void outdent2() {
        // Arrange
        var intendingStringBuilder = new IntendingStringBuilder();

        // Act
        var result = intendingStringBuilder.indent().indent().outdent();
        intendingStringBuilder.newLine();

        // Assert
        assertEquals(intendingStringBuilder, result);
        assertEquals(System.lineSeparator() + "  ", intendingStringBuilder.toString());
    }

    @Test
    void outdent_max() {
        // Arrange
        var intendingStringBuilder = new IntendingStringBuilder();

        // Act
        var result = intendingStringBuilder.indent().indent().outdent().outdent().outdent();
        intendingStringBuilder.newLine();

        // Assert
        assertEquals(intendingStringBuilder, result);
        assertEquals(System.lineSeparator(), intendingStringBuilder.toString());
    }

}
