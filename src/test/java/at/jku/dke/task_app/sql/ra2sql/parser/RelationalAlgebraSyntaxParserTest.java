package at.jku.dke.task_app.sql.ra2sql.parser;

import at.jku.dke.task_app.sql.ra2sql.RelationalAlgebraSyntaxLexerErrorListener;
import at.jku.dke.task_app.sql.ra2sql.model.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RelationalAlgebraSyntaxParserTest {

    private Expression compile(String input) {
        return (Expression) this.compile(input, p -> p.root().value);
    }

    private Object compile(String input, Function<RelationalAlgebraSyntaxParser, Object> expr) {
        var stream = CharStreams.fromString(input);

        var lexer = new RelationalAlgebraSyntaxLexer(stream);
        lexer.addErrorListener(new RelationalAlgebraSyntaxLexerErrorListener());
        var tokens = new CommonTokenStream(lexer);
        var parser = new RelationalAlgebraSyntaxParser(tokens);
        parser.addErrorListener(new RelationalAlgebraSyntaxLexerErrorListener());

        return expr.apply(parser);
    }

    //#region --- ROOT ---
    @Test
    void parse_relation_expression() {
        // Act
        var expression = this.compile("emp");

        // Assert
        assertThat(expression)
            .isInstanceOf(Relation.class)
            .extracting("name").asInstanceOf(InstanceOfAssertFactories.STRING).isEqualTo("EMP");
    }

    @Test
    void parse_selection_expression() {
        // Act
        var expression = this.compile("SELECTION LEFTBRACKET id GREATERTHAN EQUAL 10 COMMA name EQUAL APOSTROPHE ixi APOSTROPHE RIGHTBRACKET LEFTPARENTHESES emp RIGHTPARENTHESES");

        // Assert
        assertThat(expression).isInstanceOf(Selection.class);

        var sel = (Selection) expression;
        assertThat(sel.getExpression())
            .isInstanceOf(Relation.class)
            .extracting("name").asInstanceOf(InstanceOfAssertFactories.STRING).isEqualTo("EMP");
        assertThat(sel.getComparisons()).hasSize(2);
    }

    @Test
    void parse_projection_expression() {
        // Act
        var expression = this.compile("PROJECTION LEFTBRACKET id COMMA name RIGHTBRACKET LEFTPARENTHESES emp RIGHTPARENTHESES");

        // Assert
        assertThat(expression).isInstanceOf(Projection.class);
        var proj = (Projection) expression;
        assertThat(proj.getExpression())
            .isInstanceOf(Relation.class)
            .extracting("name").asInstanceOf(InstanceOfAssertFactories.STRING).isEqualTo("EMP");
        assertThat(proj.getProjectionAttributes()).containsExactly("ID", "NAME");
    }

    @Test
    void parse_renaming_expression() {
        // Act
        var expression = this.compile("RENAMING LEFTBRACKET id LEFTARROW no COMMA name LEFTARROW alias RIGHTBRACKET LEFTPARENTHESES emp RIGHTPARENTHESES");

        // Assert
        assertThat(expression).isInstanceOf(Renaming.class);
        var ren = (Renaming) expression;
        assertThat(ren.getExpression())
            .isInstanceOf(Relation.class)
            .extracting("name").asInstanceOf(InstanceOfAssertFactories.STRING).isEqualTo("EMP");
        assertThat(ren.getAliasForAttribute("id")).isEqualTo("NO");
        assertThat(ren.getAliasForAttribute("name")).isEqualTo("ALIAS");
    }

    @Test
    void parse_join_expression() {
        // Act
        var expression = this.compile("LEFTPARENTHESES emp JOIN dept RIGHTPARENTHESES");

        // Assert
        assertThat(expression).isInstanceOf(Join.class);
        var bin = (BinaryOperator) expression;
        assertThat(bin.getLeftExpression())
            .isInstanceOf(Relation.class)
            .extracting("name").asInstanceOf(InstanceOfAssertFactories.STRING).isEqualTo("EMP");
        assertThat(bin.getRightExpression())
            .isInstanceOf(Relation.class)
            .extracting("name").asInstanceOf(InstanceOfAssertFactories.STRING).isEqualTo("DEPT");
    }

    @Test
    void parse_intersection_expression() {
        // Act
        var expression = this.compile("LEFTPARENTHESES emp INTERSECTION dept RIGHTPARENTHESES");

        // Assert
        assertThat(expression).isInstanceOf(Intersection.class);
        var bin = (BinaryOperator) expression;
        assertThat(bin.getLeftExpression())
            .isInstanceOf(Relation.class)
            .extracting("name").asInstanceOf(InstanceOfAssertFactories.STRING).isEqualTo("EMP");
        assertThat(bin.getRightExpression())
            .isInstanceOf(Relation.class)
            .extracting("name").asInstanceOf(InstanceOfAssertFactories.STRING).isEqualTo("DEPT");
    }

    @Test
    void parse_union_expression() {
        // Act
        var expression = this.compile("LEFTPARENTHESES emp UNION dept RIGHTPARENTHESES");

        // Assert
        assertThat(expression).isInstanceOf(Union.class);
        var bin = (BinaryOperator) expression;
        assertThat(bin.getLeftExpression())
            .isInstanceOf(Relation.class)
            .extracting("name").asInstanceOf(InstanceOfAssertFactories.STRING).isEqualTo("EMP");
        assertThat(bin.getRightExpression())
            .isInstanceOf(Relation.class)
            .extracting("name").asInstanceOf(InstanceOfAssertFactories.STRING).isEqualTo("DEPT");
    }

    @Test
    void parse_outerJoin_expression() {
        // Act
        var expression = this.compile("LEFTPARENTHESES emp OUTER_JOIN dept RIGHTPARENTHESES");

        // Assert
        assertThat(expression).isInstanceOf(OuterJoin.class);
        var bin = (BinaryOperator) expression;
        assertThat(bin.getLeftExpression())
            .isInstanceOf(Relation.class)
            .extracting("name").asInstanceOf(InstanceOfAssertFactories.STRING).isEqualTo("EMP");
        assertThat(bin.getRightExpression())
            .isInstanceOf(Relation.class)
            .extracting("name").asInstanceOf(InstanceOfAssertFactories.STRING).isEqualTo("DEPT");
    }

    @Test
    void parse_division_expression() {
        // Act
        var expression = this.compile("LEFTPARENTHESES emp DIVISION dept RIGHTPARENTHESES");

        // Assert
        assertThat(expression).isInstanceOf(Division.class);
        var bin = (BinaryOperator) expression;
        assertThat(bin.getLeftExpression())
            .isInstanceOf(Relation.class)
            .extracting("name").asInstanceOf(InstanceOfAssertFactories.STRING).isEqualTo("EMP");
        assertThat(bin.getRightExpression())
            .isInstanceOf(Relation.class)
            .extracting("name").asInstanceOf(InstanceOfAssertFactories.STRING).isEqualTo("DEPT");
    }

    @Test
    void parse_minus_expression() {
        // Act
        var expression = this.compile("LEFTPARENTHESES emp MINUS dept RIGHTPARENTHESES");

        // Assert
        assertThat(expression).isInstanceOf(Minus.class);
        var bin = (BinaryOperator) expression;
        assertThat(bin.getLeftExpression())
            .isInstanceOf(Relation.class)
            .extracting("name").asInstanceOf(InstanceOfAssertFactories.STRING).isEqualTo("EMP");
        assertThat(bin.getRightExpression())
            .isInstanceOf(Relation.class)
            .extracting("name").asInstanceOf(InstanceOfAssertFactories.STRING).isEqualTo("DEPT");
    }

    @Test
    void parse_cartesianProduct_expression() {
        // Act
        var expression = this.compile("LEFTPARENTHESES emp CARTESIANPRODUCT dept RIGHTPARENTHESES");

        // Assert
        assertThat(expression).isInstanceOf(CartesianProduct.class);
        var bin = (BinaryOperator) expression;
        assertThat(bin.getLeftExpression())
            .isInstanceOf(Relation.class)
            .extracting("name").asInstanceOf(InstanceOfAssertFactories.STRING).isEqualTo("EMP");
        assertThat(bin.getRightExpression())
            .isInstanceOf(Relation.class)
            .extracting("name").asInstanceOf(InstanceOfAssertFactories.STRING).isEqualTo("DEPT");
    }

    @Test
    void parse_leftSemiJoin_expression() {
        // Act
        var expression = this.compile("LEFTPARENTHESES emp LEFTSEMI dept RIGHTPARENTHESES");

        // Assert
        assertThat(expression).isInstanceOf(LeftSemiJoin.class);
        var bin = (BinaryOperator) expression;
        assertThat(bin.getLeftExpression())
            .isInstanceOf(Relation.class)
            .extracting("name").asInstanceOf(InstanceOfAssertFactories.STRING).isEqualTo("EMP");
        assertThat(bin.getRightExpression())
            .isInstanceOf(Relation.class)
            .extracting("name").asInstanceOf(InstanceOfAssertFactories.STRING).isEqualTo("DEPT");
    }

    @Test
    void parse_rightSemiJoin_expression() {
        // Act
        var expression = this.compile("LEFTPARENTHESES emp RIGHTSEMI dept RIGHTPARENTHESES");

        // Assert
        assertThat(expression).isInstanceOf(RightSemiJoin.class);
        var bin = (BinaryOperator) expression;
        assertThat(bin.getLeftExpression())
            .isInstanceOf(Relation.class)
            .extracting("name").asInstanceOf(InstanceOfAssertFactories.STRING).isEqualTo("EMP");
        assertThat(bin.getRightExpression())
            .isInstanceOf(Relation.class)
            .extracting("name").asInstanceOf(InstanceOfAssertFactories.STRING).isEqualTo("DEPT");
    }

    @Test
    void parse_thetaJoin_expression() {
        // Act
        var expression = this.compile("LEFTPARENTHESES emp LEFTCURLY deptno EQUAL deptno COMMA company EQUAL id RIGHTCURLY dept RIGHTPARENTHESES");

        // Assert
        assertThat(expression).isInstanceOf(ThetaJoin.class);
        var bin = (ThetaJoin) expression;
        assertThat(bin.getLeftExpression())
            .isInstanceOf(Relation.class)
            .extracting("name").asInstanceOf(InstanceOfAssertFactories.STRING).isEqualTo("EMP");
        assertThat(bin.getRightExpression())
            .isInstanceOf(Relation.class)
            .extracting("name").asInstanceOf(InstanceOfAssertFactories.STRING).isEqualTo("DEPT");
        assertThat(bin.getComparisons()).hasSize(2);
    }
    //#endregion

    //#region --- UNARY ---
    @Test
    void parse_unary() {
        // Act
        var expression = this.compile("SELECTION LEFTBRACKET id GREATERTHAN EQUAL 10 RIGHTBRACKET LEFTPARENTHESES emp RIGHTPARENTHESES", p -> p.unary().value);

        // Assert
        var result = (UnaryOperator) expression;
        assertNotNull(result);
        assertThat(result.getExpression()).isInstanceOf(Relation.class);
        assertThat(result).isInstanceOf(Selection.class);
    }

    @Test
    void parse_selection() {
        // Act
        var expression = this.compile("SELECTION LEFTBRACKET id GREATERTHAN EQUAL 10 COMMA APOSTROPHE ixi APOSTROPHE EQUAL name RIGHTBRACKET", p -> p.selection().value);

        // Assert
        var result = (Selection) expression;
        assertNotNull(result);
        assertThat(result.getComparisons())
            .hasSize(2)
            .filteredOn(x -> x.getLeftValue().equals("id") && x.getRightValue().equals("10") && x.getLeftValueType() == ComparisonValueType.ATTRIBUTE && x.getRightValueType() == ComparisonValueType.NUMBER && x.getOperator().equals(ComparisonOperator.GREATER_OR_EQUAL)).hasSize(1);
        assertThat(result.getComparisons())
            .filteredOn(x -> x.getRightValue().equals("name") && x.getLeftValue().equals("ixi") && x.getRightValueType() == ComparisonValueType.ATTRIBUTE && x.getLeftValueType() == ComparisonValueType.LITERAL && x.getOperator().equals(ComparisonOperator.EQUAL)).hasSize(1);
    }

    @Test
    void parse_projection() {
        // Act
        var expression = this.compile("PROJECTION LEFTBRACKET id COMMA name RIGHTBRACKET", p -> p.projection().value);

        // Assert
        var result = (Projection) expression;
        assertNotNull(result);
        assertThat(result.getProjectionAttributes())
            .hasSize(2)
            .containsExactly("ID", "NAME");
    }

    @Test
    void parse_renaming() {
        // Act
        var expression = this.compile("RENAMING LEFTBRACKET id LEFTARROW no COMMA name LEFTARROW alias RIGHTBRACKET", p -> p.renaming().value);

        // Assert
        var result = (Renaming) expression;
        assertNotNull(result);
        assertThat(result.getAliasForAttribute("id")).isEqualTo("NO");
        assertThat(result.getAliasForAttribute("name")).isEqualTo("ALIAS");
    }
    //#endregion

    //#region --- UNARY ---
    @Test
    void parse_join() {
        // Act
        var expression = this.compile("JOIN", p -> p.join().value);

        // Assert
        assertThat(expression).isInstanceOf(Join.class);
    }

    @Test
    void parse_intersection() {
        // Act
        var expression = this.compile("INTERSECTION", p -> p.intersection().value);

        // Assert
        assertThat(expression).isInstanceOf(Intersection.class);
    }

    @Test
    void parse_union() {
        // Act
        var expression = this.compile("UNION", p -> p.union().value);

        // Assert
        assertThat(expression).isInstanceOf(Union.class);
    }

    @Test
    void parse_outerJoin() {
        // Act
        var expression = this.compile("OUTER_JOIN", p -> p.outerJoin().value);

        // Assert
        assertThat(expression).isInstanceOf(OuterJoin.class);
    }

    @Test
    void parse_division() {
        // Act
        var expression = this.compile("DIVISION", p -> p.division().value);

        // Assert
        assertThat(expression).isInstanceOf(Division.class);
    }

    @Test
    void parse_minus() {
        // Act
        var expression = this.compile("MINUS", p -> p.minus().value);

        // Assert
        assertThat(expression).isInstanceOf(Minus.class);
    }

    @Test
    void parse_cartesianProduct() {
        // Act
        var expression = this.compile("CARTESIANPRODUCT", p -> p.cartesianProduct().value);

        // Assert
        assertThat(expression).isInstanceOf(CartesianProduct.class);
    }

    @Test
    void parse_leftSemiJoin() {
        // Act
        var expression = this.compile("LEFTSEMI", p -> p.leftSemiJoin().value);

        // Assert
        assertThat(expression).isInstanceOf(LeftSemiJoin.class);
    }

    @Test
    void parse_rightSemiJoin() {
        // Act
        var expression = this.compile("RIGHTSEMI", p -> p.rightSemiJoin().value);

        // Assert
        assertThat(expression).isInstanceOf(RightSemiJoin.class);
    }

    @Test
    void parse_thetaJoin() {
        // Act
        var expression = this.compile("LEFTCURLY x EQUAL y COMMA z LESSTHAN GREATERTHAN a RIGHTCURLY", p -> p.thetaJoin().value);

        // Assert
        assertThat(expression).isInstanceOf(ThetaJoin.class);
        var theta = (ThetaJoin) expression;
        assertThat(theta.getComparisons())
            .hasSize(2)
            .filteredOn(x -> x.getLeftValue().equals("x") && x.getRightValue().equals("y") && x.getLeftValueType() == ComparisonValueType.ATTRIBUTE && x.getRightValueType() == ComparisonValueType.ATTRIBUTE && x.getOperator().equals(ComparisonOperator.EQUAL)).hasSize(1);
        assertThat(theta.getComparisons())
            .filteredOn(x -> x.getLeftValue().equals("z") && x.getRightValue().equals("a") && x.getLeftValueType() == ComparisonValueType.ATTRIBUTE && x.getRightValueType() == ComparisonValueType.ATTRIBUTE && x.getOperator().equals(ComparisonOperator.NOT_EQUAL)).hasSize(1);
    }
    //#endregion

    //#region --- COMPARISON ---
    @Test
    void parse_comparison_lte() {
        // Act
        var expression = this.compile("LESSTHAN EQUAL", p -> p.comparisonOperator().value);

        // Assert
        assertThat(expression).isInstanceOf(ComparisonOperator.class).isEqualTo(ComparisonOperator.LESS_OR_EQUAL);
    }

    @Test
    void parse_comparison_lt() {
        // Act
        var expression = this.compile("LESSTHAN", p -> p.comparisonOperator().value);

        // Assert
        assertThat(expression).isInstanceOf(ComparisonOperator.class).isEqualTo(ComparisonOperator.LESS_THAN);
    }

    @Test
    void parse_comparison_gte() {
        // Act
        var expression = this.compile("GREATERTHAN EQUAL", p -> p.comparisonOperator().value);

        // Assert
        assertThat(expression).isInstanceOf(ComparisonOperator.class).isEqualTo(ComparisonOperator.GREATER_OR_EQUAL);
    }

    @Test
    void parse_comparison_gt() {
        // Act
        var expression = this.compile("GREATERTHAN", p -> p.comparisonOperator().value);

        // Assert
        assertThat(expression).isInstanceOf(ComparisonOperator.class).isEqualTo(ComparisonOperator.GREATER_THAN);
    }

    @Test
    void parse_comparison_eq() {
        // Act
        var expression = this.compile("EQUAL", p -> p.comparisonOperator().value);

        // Assert
        assertThat(expression).isInstanceOf(ComparisonOperator.class).isEqualTo(ComparisonOperator.EQUAL);
    }

    @Test
    void parse_comparison_neq() {
        // Act
        var expression = this.compile("LESSTHAN GREATERTHAN", p -> p.comparisonOperator().value);

        // Assert
        assertThat(expression).isInstanceOf(ComparisonOperator.class).isEqualTo(ComparisonOperator.NOT_EQUAL);
    }
    //#endregion

    //#region --- LITERALS ---
    @Test
    void parse_literal() {
        // Act
        final String s = "APOSTROPHE a Literal APOSTROPHE";
        var obj = this.compile(s, p -> p.literal().value);

        // Assert
        assertThat(obj).isInstanceOf(String.class).isEqualTo("a Literal");
    }

//    @Test
//    void parse_literal_withWhitespace() {
//        // Act
//        final String s = "APOSTROPHE 27 students - passed! APOSTROPHE";
//        var obj = this.compile(s, p -> p.literal().value);
//
//        // Assert
//        assertThat(obj).isInstanceOf(String.class).isEqualTo("27 students - passed!");
//    }

    @Test
    void parse_number() {
        // Act
        final String s = "1234567890";
        var obj = this.compile(s, p -> p.number().value);

        // Assert
        assertThat(obj).isInstanceOf(String.class).isEqualTo(s);
    }

    @Test
    void parse_number_decimal() {
        // Act
        final String s = "12345 DOT 67890";
        var obj = this.compile(s, p -> p.number().value);

        // Assert
        assertThat(obj).isInstanceOf(String.class).isEqualTo("12345.67890");
    }

    @Test
    void parse_name() {
        // Act
        final String s = "a_Name";
        var obj = this.compile(s, p -> p.name().value);

        // Assert
        assertThat(obj).isInstanceOf(String.class).isEqualTo(s);
    }

    @Test
    void parse_attribute() {
        // Act
        final String s = "my1stAttribute";
        var obj = this.compile(s, p -> p.attribute().value);

        // Assert
        assertThat(obj).isInstanceOf(String.class).isEqualTo(s);
    }

    @Test
    void parse_day() {
        // Act
        final String s = "1";
        var obj = this.compile(s, p -> p.day().value);

        // Assert
        assertThat(obj).isInstanceOf(String.class).isEqualTo(s);
    }

    @Test
    void parse_month() {
        // Act
        final String s = "1";
        var obj = this.compile(s, p -> p.month().value);

        // Assert
        assertThat(obj).isInstanceOf(String.class).isEqualTo(s);
    }

    @Test
    void parse_year() {
        // Act
        final String s = "1";
        var obj = this.compile(s, p -> p.year().value);

        // Assert
        assertThat(obj).isInstanceOf(String.class).isEqualTo(s);
    }

    @Test
    void parse_date() {
        // Act
        final String s = "APOSTROPHE 1 DOT 10 DOT 2001 APOSTROPHE";
        var obj = this.compile(s, p -> p.date().value);

        // Assert
        assertThat(obj).isInstanceOf(String.class).isEqualTo("1.10.2001");
    }

    @Test
    void parse_date_invalid() {
        // Act
        final String s = "APOSTROPHE 1 DOT 15 DOT 2001 APOSTROPHE";

        // Assert
        assertThrows(IllegalArgumentException.class, () -> this.compile(s, p -> p.date().value));
    }

    @Test
    void parse_ignore_tab() {
        // Act
        final String s = "APOSTROPHE aName\tx APOSTROPHE";
        var obj = this.compile(s, p -> p.literal().value);

        // Assert
        assertThat(obj).isInstanceOf(String.class).isEqualTo("aName x");
    }

    @Test
    void parse_ignore_newLine() {
        // Act
        final String s = "APOSTROPHE aName\nx APOSTROPHE";
        var obj = this.compile(s, p -> p.literal().value);

        // Assert
        assertThat(obj).isInstanceOf(String.class).isEqualTo("aName x");
    }

    @Test
    void parse_ignore_carriageReturn() {
        // Act
        final String s = "APOSTROPHE aName\rx APOSTROPHE";
        var obj = this.compile(s, p -> p.literal().value);

        // Assert
        assertThat(obj).isInstanceOf(String.class).isEqualTo("aName x");
    }
    //#endregion
}
