package at.jku.dke.task_app.sql.data.entities;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SqlRaTaskGroupQueryTest {

    @Test
    void testConstructor() {
        // Arrange
        final SqlRaTaskGroup tg = new SqlRaTaskGroup();
        final String tableName = "tableName";
        final String query = "solutionQuery";

        // Act
        var SqlRaTaskGroupQuery = new SqlRaTaskGroupQuery(tg, tableName, query);

        // Assert
        assertEquals(tg, SqlRaTaskGroupQuery.getTaskGroup());
        assertEquals(tableName, SqlRaTaskGroupQuery.getTableName());
        assertEquals(query, SqlRaTaskGroupQuery.getQuery());
    }

    @Test
    void testGetSetId() {
        // Arrange
        var SqlRaTaskGroupQuery = new SqlRaTaskGroupQuery();
        final UUID value = UUID.randomUUID();

        // Act
        SqlRaTaskGroupQuery.setId(value);

        // Assert
        assertEquals(value, SqlRaTaskGroupQuery.getId());
    }

    @Test
    void testGetSetTaskGroup() {
        // Arrange
        var SqlRaTaskGroupQuery = new SqlRaTaskGroupQuery();
        final SqlRaTaskGroup value = new SqlRaTaskGroup();

        // Act
        SqlRaTaskGroupQuery.setTaskGroup(value);

        // Assert
        assertEquals(value, SqlRaTaskGroupQuery.getTaskGroup());
    }

    @Test
    void testGetSetTableName() {
        // Arrange
        var SqlRaTaskGroupQuery = new SqlRaTaskGroupQuery();
        final String value = "tableName";

        // Act
        SqlRaTaskGroupQuery.setTableName(value);

        // Assert
        assertEquals(value, SqlRaTaskGroupQuery.getTableName());
    }

    @Test
    void testGetSetQuery() {
        // Arrange
        var SqlRaTaskGroupQuery = new SqlRaTaskGroupQuery();
        final String value = "solutionQuery";

        // Act
        SqlRaTaskGroupQuery.setQuery(value);

        // Assert
        assertEquals(value, SqlRaTaskGroupQuery.getQuery());
    }

    @Test
    void testEquals() {
        // Arrange
        var SqlRaTaskGroupQuery1 = new SqlRaTaskGroupQuery();
        var SqlRaTaskGroupQuery2 = new SqlRaTaskGroupQuery();

        var id = UUID.randomUUID();
        var SqlRaTaskGroupQuery3 = new SqlRaTaskGroupQuery();
        SqlRaTaskGroupQuery3.setId(id);

        var SqlRaTaskGroupQuery4 = new SqlRaTaskGroupQuery();
        SqlRaTaskGroupQuery4.setId(id);
        SqlRaTaskGroupQuery4.setTableName("tableName");

        var SqlRaTaskGroupQuery5 = new SqlRaTaskGroupQuery();
        SqlRaTaskGroupQuery5.setId(id);
        SqlRaTaskGroupQuery5.setTableName("tableName");
        SqlRaTaskGroupQuery5.setQuery("solutionQuery");

        var SqlRaTaskGroupQuery6 = new SqlRaTaskGroupQuery();
        SqlRaTaskGroupQuery6.setId(id);
        SqlRaTaskGroupQuery6.setTableName("tableName");
        SqlRaTaskGroupQuery6.setQuery("solutionQuery");
        SqlRaTaskGroupQuery6.setTaskGroup(new SqlRaTaskGroup());

        // Act
        var result1 = SqlRaTaskGroupQuery1.equals(SqlRaTaskGroupQuery2);
        var result2 = SqlRaTaskGroupQuery2.equals(SqlRaTaskGroupQuery3);
        var result3 = SqlRaTaskGroupQuery3.equals(SqlRaTaskGroupQuery4);
        var result4 = SqlRaTaskGroupQuery4.equals(SqlRaTaskGroupQuery5);
        var result5 = SqlRaTaskGroupQuery5.equals(SqlRaTaskGroupQuery6);

        // Assert
        assertFalse(result1);
        assertFalse(result2);
        assertTrue(result3);
        assertTrue(result4);
        assertTrue(result5);
    }

    @Test
    void testEqualsSame() {
        // Arrange
        var SqlRaTaskGroupQuery = new SqlRaTaskGroupQuery();

        // Act
        var result = SqlRaTaskGroupQuery.equals(SqlRaTaskGroupQuery);

        // Assert
        assertTrue(result);
    }

    @Test
    void testEqualsNull() {
        // Arrange
        var SqlRaTaskGroupQuery = new SqlRaTaskGroupQuery();

        // Act
        var result = SqlRaTaskGroupQuery.equals(null);

        // Assert
        assertFalse(result);
    }

    @Test
    void testEqualsOtherClass() {
        // Arrange
        var SqlRaTaskGroupQuery = new SqlRaTaskGroupQuery();

        // Act
        var result = SqlRaTaskGroupQuery.equals(new Object());

        // Assert
        assertFalse(result);
    }

    @Test
    void testHashCode() {
        // Arrange
        var SqlRaTaskGroupQuery1 = new SqlRaTaskGroupQuery();
        var SqlRaTaskGroupQuery2 = new SqlRaTaskGroupQuery();

        var id = UUID.randomUUID();
        var SqlRaTaskGroupQuery3 = new SqlRaTaskGroupQuery();
        SqlRaTaskGroupQuery3.setId(id);

        var SqlRaTaskGroupQuery4 = new SqlRaTaskGroupQuery();
        SqlRaTaskGroupQuery4.setId(id);
        SqlRaTaskGroupQuery4.setTableName("tableName");

        var SqlRaTaskGroupQuery5 = new SqlRaTaskGroupQuery();
        SqlRaTaskGroupQuery5.setId(id);
        SqlRaTaskGroupQuery5.setTableName("tableName");
        SqlRaTaskGroupQuery5.setQuery("solutionQuery");

        var SqlRaTaskGroupQuery6 = new SqlRaTaskGroupQuery();
        SqlRaTaskGroupQuery6.setId(id);
        SqlRaTaskGroupQuery6.setTableName("tableName");
        SqlRaTaskGroupQuery6.setQuery("solutionQuery");
        SqlRaTaskGroupQuery6.setTaskGroup(new SqlRaTaskGroup());

        // Act
        var result1 = SqlRaTaskGroupQuery1.hashCode() == SqlRaTaskGroupQuery2.hashCode();
        var result2 = SqlRaTaskGroupQuery2.hashCode() == SqlRaTaskGroupQuery3.hashCode();
        var result3 = SqlRaTaskGroupQuery3.hashCode() == SqlRaTaskGroupQuery4.hashCode();
        var result4 = SqlRaTaskGroupQuery4.hashCode() == SqlRaTaskGroupQuery5.hashCode();
        var result5 = SqlRaTaskGroupQuery5.hashCode() == SqlRaTaskGroupQuery6.hashCode();

        // Assert
        assertTrue(result1);
        assertTrue(result2);
        assertTrue(result3);
        assertTrue(result4);
        assertTrue(result5);
    }

    @Test
    void testToString() {
        // Arrange
        var SqlRaTaskGroupQuery = new SqlRaTaskGroupQuery();
        SqlRaTaskGroupQuery.setId(UUID.randomUUID());
        SqlRaTaskGroupQuery.setTableName("tableName");
        SqlRaTaskGroupQuery.setQuery("solutionQuery");
        SqlRaTaskGroupQuery.setTaskGroup(new SqlRaTaskGroup());

        // Act
        var result = SqlRaTaskGroupQuery.toString();

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("tableName"));
        assertTrue(result.contains("solutionQuery"));
    }
}
