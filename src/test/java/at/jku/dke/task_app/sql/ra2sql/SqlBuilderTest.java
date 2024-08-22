package at.jku.dke.task_app.sql.ra2sql;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;
import at.jku.dke.task_app.sql.dto.TableDto;
import at.jku.dke.task_app.sql.ra2sql.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SqlBuilderTest {

    private static final SchemaInfoDto SCHEMA = new SchemaInfoDto(List.of(
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

    private static SqlBuilder builder;

    @BeforeAll
    static void setUp() {
        builder = new SqlBuilder(SCHEMA);
    }

    @Test
    void buildSql_null() {
        assertThrows(IllegalArgumentException.class, () -> builder.buildSql(null));
    }

    @Test
    void buildSql_relation() {
        // Arrange
        var expression = new Relation();
        expression.setName("emp");

        // Act
        var result = builder.buildSql(expression);

        // Assert
        assertEquals("""
            SELECT DISTINCT *
            FROM (
              SELECT EMP_NO, EMP_NAME, DEPT_NO
              FROM EMP
            ) AS subquery""".replaceAll("\\s+", " ").trim(), result.replaceAll("\\s+", " ").trim());
    }

    @Test
    void buildSql_selection() {
        // Arrange
        var relation = new Relation();
        relation.setName("emp");

        var comp1 = new Comparison();
        comp1.setLeftValue("dept_no");
        comp1.setLeftValueType(ComparisonValueType.ATTRIBUTE);
        comp1.setRightValue("10");
        comp1.setRightValueType(ComparisonValueType.NUMBER);
        comp1.setOperator(ComparisonOperator.EQUAL);

        var comp2 = new Comparison();
        comp2.setLeftValue("emp_no");
        comp2.setLeftValueType(ComparisonValueType.ATTRIBUTE);
        comp2.setRightValue("30");
        comp2.setRightValueType(ComparisonValueType.NUMBER);
        comp2.setOperator(ComparisonOperator.GREATER_OR_EQUAL);

        var expression = new Selection();
        expression.setExpression(relation);
        expression.addComparison(comp1);
        expression.addComparison(comp2);

        // Act
        var result = builder.buildSql(expression);

        // Assert
        assertEquals("""
            SELECT DISTINCT *
            FROM (
              SELECT EMP_NO, EMP_NAME, DEPT_NO
              FROM (
                SELECT EMP_NO, EMP_NAME, DEPT_NO
                FROM EMP
              ) AS selectionSubquery
              WHERE dept_no = 10 AND emp_no >= 30
            ) AS subquery""".replaceAll("\\s+", " ").trim(), result.replaceAll("\\s+", " ").trim());
    }

    @Test
    void buildSql_projection() {
        // Arrange
        var relation = new Relation();
        relation.setName("emp");

        var expression = new Projection();
        expression.setExpression(relation);
        expression.addProjectionAttribute("emp_name");

        // Act
        var result = builder.buildSql(expression);

        // Assert
        assertEquals("""
            SELECT DISTINCT *
            FROM (
              SELECT EMP_NAME
              FROM (
                SELECT EMP_NO, EMP_NAME, DEPT_NO
                FROM EMP
              ) AS projectionSubquery
            ) AS subquery""".replaceAll("\\s+", " ").trim(), result.replaceAll("\\s+", " ").trim());
    }

    @Test
    void buildSql_renaming() {
        // Arrange
        var relation = new Relation();
        relation.setName("emp");

        var expression = new Renaming();
        expression.setExpression(relation);
        expression.addAttributeAlias("emp_name", "name");

        // Act
        var result = builder.buildSql(expression);

        // Assert
        assertEquals("""
            SELECT DISTINCT *
            FROM (
              SELECT EMP_NO, DEPT_NO, EMP_NAME AS NAME
              FROM (
                SELECT EMP_NO, EMP_NAME, DEPT_NO
                FROM EMP
              ) AS renamingSubquery
            ) AS subquery""".replaceAll("\\s+", " ").trim(), result.replaceAll("\\s+", " ").trim());
    }

    @Test
    void buildSql_unaryNested() {
        // Arrange
        var relation = new Relation();
        relation.setName("emp");

        var comp = new Comparison();
        comp.setLeftValue("dept_no");
        comp.setLeftValueType(ComparisonValueType.ATTRIBUTE);
        comp.setRightValue("10");
        comp.setRightValueType(ComparisonValueType.NUMBER);
        comp.setOperator(ComparisonOperator.EQUAL);

        var selection = new Selection();
        selection.setExpression(relation);
        selection.addComparison(comp);

        var projection = new Projection();
        projection.setExpression(selection);
        projection.addProjectionAttribute("emp_name");

        var expression = new Renaming();
        expression.setExpression(projection);
        expression.addAttributeAlias("emp_name", "name");

        // Act
        var result = builder.buildSql(expression);

        // Assert
        assertEquals("""
            SELECT DISTINCT *
            FROM (
              SELECT EMP_NAME AS NAME
              FROM (
                SELECT EMP_NAME
                FROM (
                  SELECT EMP_NO, EMP_NAME, DEPT_NO
                  FROM (
                    SELECT EMP_NO, EMP_NAME, DEPT_NO
                    FROM EMP
                  ) AS selectionSubquery
                  WHERE dept_no = 10
                ) AS projectionSubquery
              ) AS renamingSubquery
            ) AS subquery""".replaceAll("\\s+", " ").trim(), result.replaceAll("\\s+", " ").trim());
    }

    @Test
    void buildSql_naturalJoin() {
        // Arrange
        var relation1 = new Relation();
        relation1.setName("emp");

        var relation2 = new Relation();
        relation2.setName("dept");

        var expression = new Join();
        expression.setLeftExpression(relation1);
        expression.setRightExpression(relation2);

        // Act
        var result = builder.buildSql(expression);

        // Assert
        assertEquals("""
            SELECT DISTINCT *
            FROM (
              SELECT EMP_NO, EMP_NAME, DEPT_NO, DEPT_NAME
              FROM (
                SELECT EMP_NO, EMP_NAME, DEPT_NO
                FROM EMP
              ) AS joinLeftSideSubquery NATURAL JOIN (
                SELECT DEPT_NO, DEPT_NAME
                FROM DEPT
              ) AS joinRightSideSubquery
            ) AS subquery""".replaceAll("\\s+", " ").trim(), result.replaceAll("\\s+", " ").trim());
    }

    @Test
    void buildSql_leftSemiJoin() {
        // Arrange
        var relation1 = new Relation();
        relation1.setName("emp");

        var relation2 = new Relation();
        relation2.setName("dept");

        var expression = new LeftSemiJoin();
        expression.setLeftExpression(relation1);
        expression.setRightExpression(relation2);

        // Act
        var result = builder.buildSql(expression);

        // Assert
        assertEquals("""
            SELECT DISTINCT *
            FROM (
              SELECT EMP_NO, EMP_NAME, DEPT_NO
              FROM (
                SELECT EMP_NO, EMP_NAME, DEPT_NO
                FROM EMP
              ) AS joinLeftSideSubquery NATURAL JOIN (
                SELECT DEPT_NO, DEPT_NAME
                FROM DEPT
              ) AS joinRightSideSubquery
            ) AS subquery""".replaceAll("\\s+", " ").trim(), result.replaceAll("\\s+", " ").trim());
    }

    @Test
    void buildSql_rightSemiJoin() {
        // Arrange
        var relation1 = new Relation();
        relation1.setName("emp");

        var relation2 = new Relation();
        relation2.setName("dept");

        var expression = new RightSemiJoin();
        expression.setLeftExpression(relation1);
        expression.setRightExpression(relation2);

        // Act
        var result = builder.buildSql(expression);

        // Assert
        assertEquals("""
            SELECT DISTINCT *
            FROM (
              SELECT DEPT_NO, DEPT_NAME
              FROM (
                SELECT EMP_NO, EMP_NAME, DEPT_NO
                FROM EMP
              ) AS joinLeftSideSubquery NATURAL JOIN (
                SELECT DEPT_NO, DEPT_NAME
                FROM DEPT
              ) AS joinRightSideSubquery
            ) AS subquery""".replaceAll("\\s+", " ").trim(), result.replaceAll("\\s+", " ").trim());
    }

    @Test
    void buildSql_minus() {
        // Arrange
        var relation1 = new Relation();
        relation1.setName("emp");

        var relation2 = new Relation();
        relation2.setName("dept");

        var expression = new Minus();
        expression.setLeftExpression(relation1);
        expression.setRightExpression(relation2);

        // Act
        var result = builder.buildSql(expression);

        // Assert
        assertEquals("""
            SELECT DISTINCT *
            FROM (
              SELECT EMP_NO, EMP_NAME, DEPT_NO
              FROM (
                SELECT EMP_NO, EMP_NAME, DEPT_NO
                FROM EMP
              ) AS minusLeftSideSubquery EXCEPT (
                SELECT DEPT_NO, DEPT_NAME
                FROM DEPT
              )
            ) AS subquery""".replaceAll("\\s+", " ").trim(), result.replaceAll("\\s+", " ").trim()); // I know this query won't work. But this is irrelevant here.
    }

    @Test
    void buildSql_division() {
        // Arrange
        var relation1 = new Relation();
        relation1.setName("emp");

        var relation2 = new Relation();
        relation2.setName("dept");

        var expression = new Division();
        expression.setLeftExpression(relation1);
        expression.setRightExpression(relation2);

        // Act
        var result = builder.buildSql(expression);

        // Assert
        assertEquals("""
            SELECT DISTINCT *
            FROM (
              SELECT EMP_NO, EMP_NAME
              FROM (
                SELECT EMP_NO, EMP_NAME, DEPT_NO
                FROM EMP
              ) AS naJoinLeftSideSubquery NATURAL JOIN (
                SELECT DEPT_NO, DEPT_NAME
                FROM DEPT
              ) AS naJoinRightSideSubquery
              GROUP BY EMP_NO, EMP_NAME
              HAVING COUNT(*) = (SELECT COUNT(*) FROM (
                SELECT DEPT_NO, DEPT_NAME
                FROM DEPT
              ) AS havingSubquery)
            ) AS subquery""".replaceAll("\\s+", " ").trim(), result.replaceAll("\\s+", " ").trim());
    }

    @Test
    void buildSql_outerJoin() {
        // Arrange
        var relation1 = new Relation();
        relation1.setName("emp");

        var relation2 = new Relation();
        relation2.setName("dept");

        var expression = new OuterJoin();
        expression.setLeftExpression(relation1);
        expression.setRightExpression(relation2);

        // Act
        var result = builder.buildSql(expression);

        // Assert
        assertEquals("""
            SELECT DISTINCT *
            FROM (
              SELECT EMP_NO, EMP_NAME, DEPT_NO, DEPT_NAME
              FROM (
                SELECT EMP_NO, EMP_NAME, DEPT_NO
                FROM EMP
              ) AS naFullJoinLeftSideSubquery NATURAL FULL OUTER JOIN (
                SELECT DEPT_NO, DEPT_NAME
                FROM DEPT
              ) AS naFullJoinRightSideSubquery
            ) AS subquery""".replaceAll("\\s+", " ").trim(), result.replaceAll("\\s+", " ").trim());
    }

    @Test
    void buildSql_cartesianProduct() {
        // Arrange
        var relation1 = new Relation();
        relation1.setName("emp");

        var relation2 = new Relation();
        relation2.setName("dept");

        var expression = new CartesianProduct();
        expression.setLeftExpression(relation1);
        expression.setRightExpression(relation2);

        // Act
        var result = builder.buildSql(expression);

        // Assert
        assertEquals("""
            SELECT DISTINCT *
            FROM (
              SELECT EMP_NO, EMP_NAME, DEPT_NO, DEPT_NAME
              FROM (
                SELECT EMP_NO, EMP_NAME, DEPT_NO
                FROM EMP
              ) AS naFullOuterLeftSideSubQu NATURAL FULL OUTER JOIN (
                SELECT DEPT_NO, DEPT_NAME
                FROM DEPT
              ) AS naFullOuterRightSideSubQu
            ) AS subquery""".replaceAll("\\s+", " ").trim(), result.replaceAll("\\s+", " ").trim());
    }

    @Test
    void buildSql_intersection() {
        // Arrange
        var relation1 = new Relation();
        relation1.setName("emp");

        var relation2 = new Relation();
        relation2.setName("dept");

        var expression = new Intersection();
        expression.setLeftExpression(relation1);
        expression.setRightExpression(relation2);

        // Act
        var result = builder.buildSql(expression);

        // Assert
        assertEquals("""
            SELECT DISTINCT *
            FROM (
              SELECT EMP_NO, EMP_NAME, DEPT_NO
              FROM (
                SELECT EMP_NO, EMP_NAME, DEPT_NO
                FROM EMP
              ) AS intersectLeftSubQu INTERSECT (
                SELECT DEPT_NO, DEPT_NAME
                FROM DEPT
              )
            ) AS subquery""".replaceAll("\\s+", " ").trim(), result.replaceAll("\\s+", " ").trim()); // I know this query won't work. But this is irrelevant here.
    }

    @Test
    void buildSql_union() {
        // Arrange
        var relation1 = new Relation();
        relation1.setName("emp");

        var relation2 = new Relation();
        relation2.setName("dept");

        var expression = new Union();
        expression.setLeftExpression(relation1);
        expression.setRightExpression(relation2);

        // Act
        var result = builder.buildSql(expression);

        // Assert
        assertEquals("""
            SELECT DISTINCT *
            FROM (
              SELECT EMP_NO, EMP_NAME, DEPT_NO
              FROM (
                SELECT EMP_NO, EMP_NAME, DEPT_NO
                FROM EMP
              ) AS unionLeftSubQu UNION (
                SELECT DEPT_NO, DEPT_NAME
                FROM DEPT
              )
            ) AS subquery""".replaceAll("\\s+", " ").trim(), result.replaceAll("\\s+", " ").trim()); // I know this query won't work. But this is irrelevant here.
    }

    @Test
    void buildSql_thetaJoin() {
        // Arrange
        var relation1 = new Relation();
        relation1.setName("emp");

        var relation2 = new Relation();
        relation2.setName("dept");

        var comp = new Comparison();
        comp.setLeftValue("emp_no");
        comp.setLeftValueType(ComparisonValueType.ATTRIBUTE);
        comp.setRightValue("dept_no");
        comp.setRightValueType(ComparisonValueType.ATTRIBUTE);
        comp.setOperator(ComparisonOperator.EQUAL);

        var expression = new ThetaJoin();
        expression.setLeftExpression(relation1);
        expression.setRightExpression(relation2);
        expression.addComparison(comp);

        // Act
        var result = builder.buildSql(expression);

        // Assert
        assertEquals("""
            SELECT DISTINCT *
            FROM (
              SELECT LS.EMP_NO, LS.EMP_NAME, LS.DEPT_NO, RS.DEPT_NO, RS.DEPT_NAME
              FROM (
                SELECT EMP_NO, EMP_NAME, DEPT_NO
                FROM EMP
              ) AS ls, (
                SELECT DEPT_NO, DEPT_NAME
                FROM DEPT
              ) AS rs
              WHERE ls.emp_no = rs.dept_no
            ) AS subquery""".replaceAll("\\s+", " ").trim(), result.replaceAll("\\s+", " ").trim());
    }

    @Test
    void buildSql_nested() {
        // Arrange
        var relation1 = new Relation();
        relation1.setName("emp");

        var relation2 = new Relation();
        relation2.setName("dept");

        var join = new Join();
        join.setLeftExpression(relation1);
        join.setRightExpression(relation2);

        var projection1 = new Projection();
        projection1.setExpression(join);
        projection1.addProjectionAttribute("emp_name");

        var projection2 = new Projection();
        projection2.setExpression(relation2);
        projection2.addProjectionAttribute("dept_name");

        var expression = new Union();
        expression.setLeftExpression(projection1);
        expression.setRightExpression(projection2);

        // Act
        var result = builder.buildSql(expression);

        // Assert
        assertEquals("""
            SELECT DISTINCT *
            FROM (
              SELECT EMP_NAME
              FROM (
                SELECT EMP_NAME
                FROM (
                  SELECT EMP_NO, EMP_NAME, DEPT_NO, DEPT_NAME
                  FROM (
                    SELECT EMP_NO, EMP_NAME, DEPT_NO
                    FROM EMP
                  ) AS joinLeftSideSubquery NATURAL JOIN (
                    SELECT DEPT_NO, DEPT_NAME
                    FROM DEPT
                  ) AS joinRightSideSubquery
                ) AS projectionSubquery
              ) AS unionLeftSubQu UNION (
                SELECT DEPT_NAME
                FROM (
                  SELECT DEPT_NO, DEPT_NAME
                  FROM DEPT
                ) AS projectionSubquery
              )
            ) AS subquery""".replaceAll("\\s+", " ").trim(), result.replaceAll("\\s+", " ").trim());
    }
}
