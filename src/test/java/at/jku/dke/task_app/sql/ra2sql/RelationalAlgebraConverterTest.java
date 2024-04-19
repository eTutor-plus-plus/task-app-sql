package at.jku.dke.task_app.sql.ra2sql;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;
import at.jku.dke.task_app.sql.dto.TableDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RelationalAlgebraConverterTest {

    @Test
    void convertToSql() {
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
//        var result = RelationalAlgebraConverter.convertToSql(schema, "PROJECTION[emp_no, emp_name](emp)");
//        var result = RelationalAlgebraConverter.convertToSql(schema, "emp");
        var result = RelationalAlgebraConverter.convertToSql(schema, "(emp JOIN dept)");
        System.out.println(result);
        assertNotNull(result);
    }

}
