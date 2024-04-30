package at.jku.dke.task_app.sql.ra2sql;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;
import at.jku.dke.task_app.sql.dto.TableDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RelationalAlgebraConverterTest {

    @Test
    void convertToSql_projection() {
        // Arrange
        var schema = new SchemaInfoDto(List.of(
            new TableDto("emp", List.of(
                new TableDto.ColumnDto("emp_no", "", false, true),
                new TableDto.ColumnDto("emp_name", "", false, false),
                new TableDto.ColumnDto("dept_no", "", false, false)
            ), List.of(), null),
            new TableDto("dept", List.of(
                new TableDto.ColumnDto("dept_no", "", false, true),
                new TableDto.ColumnDto("dept_name", "", false, false)
            ), List.of(), null)
        ));

        // Act
        var result = RelationalAlgebraConverter.convertToSql(schema, "PROJECTION[emp_no, emp_name](emp)");

        // Assert
        assertThat(result).contains("SELECT EMP_NO, EMP_NAME");
    }

    @Test
    void convertToSql_join() {
        // Arrange
        var schema = new SchemaInfoDto(List.of(
            new TableDto("emp", List.of(
                new TableDto.ColumnDto("emp_no", "", false, true),
                new TableDto.ColumnDto("emp_name", "", false, false),
                new TableDto.ColumnDto("dept_no", "", false, false)
            ), List.of(), null),
            new TableDto("dept", List.of(
                new TableDto.ColumnDto("dept_no", "", false, true),
                new TableDto.ColumnDto("dept_name", "", false, false)
            ), List.of(), null)
        ));

        // Act
        var result = RelationalAlgebraConverter.convertToSql(schema, "(emp ⋈ dept)");

        // Assert
        assertThat(result).contains("NATURAL JOIN");
    }

    @Test
    void convertToSql_invalidSyntax() {
        // Arrange
        var schema = new SchemaInfoDto(List.of(
            new TableDto("emp", List.of(
                new TableDto.ColumnDto("emp_no", "", false, true),
                new TableDto.ColumnDto("emp_name", "", false, false),
                new TableDto.ColumnDto("dept_no", "", false, false)
            ), List.of(), null),
            new TableDto("dept", List.of(
                new TableDto.ColumnDto("dept_no", "", false, true),
                new TableDto.ColumnDto("dept_name", "", false, false)
            ), List.of(), null)
        ));

        // Act & Assert
        assertThrows(RelationalAlgebraParseException.class, () -> RelationalAlgebraConverter.convertToSql(schema, "(emp ⋈ dept"));
    }

    @Test
    void convertParserSyntaxToRaSyntax() {
        // Act
        var result = RelationalAlgebraConverter.convertParserSyntaxToRaSyntax("PROJECTION LEFTBRACKET a COMMA b RIGHTBRACKET LEFTPARENTHESES LEFTPARENTHESES emp JOIN dept RIGHTPARENTHESES RIGHTPARENTHESES");

        // Assert
        assertEquals("π [ a , b ] ( ( emp ⋈ dept ) )", result);
    }
}
