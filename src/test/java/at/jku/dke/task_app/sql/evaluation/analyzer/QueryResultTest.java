package at.jku.dke.task_app.sql.evaluation.analyzer;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QueryResultTest {

    @Test
    void getColumnNames() {
        // Arrange
        var columnNames = List.of("id");

        // Act
        var qr = new QueryResult(columnNames, null);
        var result = qr.getColumnNames();

        // Assert
        assertEquals(columnNames, result);
    }

    @Test
    void getTuples() {
        // Arrange
        var tuples = List.of(List.of("1", "Java"));

        // Act
        var qr = new QueryResult(null, tuples);
        var result = qr.getTuples();

        // Assert
        assertEquals(tuples, result);
    }

    @Test
    void addColumn() {
        // Arrange
        final String column = "name ";
        var qr = new QueryResult();

        // Act
        qr.addColumn(column);

        // Assert
        assertThat(qr.getColumnNames()).containsExactly("NAME");
    }

    @Test
    void addTuple() {
        // Arrange
        final List<String> tuple = List.of("1", "Java");
        var qr = new QueryResult();

        // Act
        qr.addTuple(tuple);

        // Assert
        assertThat(qr.getTuples()).containsExactly(tuple);
    }

}
