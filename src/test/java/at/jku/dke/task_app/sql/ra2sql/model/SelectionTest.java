package at.jku.dke.task_app.sql.ra2sql.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SelectionTest {

    @Test
    void addComparison() {
        // Arrange
        var selection = new Selection();
        var comp = new Comparison();

        // Act
        var set = selection.addComparison(comp);

        // Arrange
        assertTrue(set);
        assertThat(selection.getComparisons()).containsExactly(comp);
    }

    @Test
    void addComparison_null() {
        // Arrange
        var selection = new Selection();

        // Act
        var set = selection.addComparison(null);

        // Arrange
        assertFalse(set);
    }

}
