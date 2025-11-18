package at.jku.dke.task_app.sql.ra2sql;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;
import at.jku.dke.task_app.sql.ra2sql.model.*;
import org.apache.commons.lang3.function.TriConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * Provides methods to create SQL statements from a parsed relational algebra expression.
 */
class SqlBuilder {

    private final SchemaInfoDto schemaInfo;

    /**
     * Creates a new instance of class {@link SqlBuilder}.
     *
     * @param schemaInfo The schema information.
     */
    public SqlBuilder(SchemaInfoDto schemaInfo) {
        this.schemaInfo = schemaInfo;
    }

    /**
     * Builds the SQL statement from the given expression.
     *
     * @param expression The expression.
     * @return The built SQL statement representing the expression.
     */
    public String buildSql(Expression expression) {
        if (expression == null)
            throw new IllegalArgumentException("expression must not be null");

        expression.calculateSchema(this.schemaInfo);
        return new IntendingStringBuilder("SELECT DISTINCT  *").newLine()
            .append("FROM (").indent().newLine()
            .append(this.generateSql(expression, 1)).outdent().newLine()
            .append(") AS subquery").toString();
    }

    /**
     * Generates the SQL expression for the given expression.
     *
     * @param expression The expression.
     * @param level      The indentation level (for formatting).
     * @return The SQL expression.
     */
    private String generateSql(Expression expression, int level) {
        if (expression == null)
            return "";

        IntendingStringBuilder sb = new IntendingStringBuilder();
        for (int i = 0; i < level; i++) {
            sb.indent();
        }

        switch (expression) {
            case Relation relation -> this.buildRelation(sb, relation);
            case UnaryOperator uop -> this.buildUnaryOperator(sb, uop, level);
            case BinaryOperator bop -> this.buildBinaryOperator(sb, bop, level);
            default -> {
            }
        }

        return sb.toString();
    }

    /**
     * Builds the query for the relation expression.
     *
     * @param sb       The string builder.
     * @param relation The relation expression.
     */
    private void buildRelation(IntendingStringBuilder sb, Relation relation) {
        sb.append("SELECT DISTINCT ").append(this.generateSelectColumns(relation))
            .newLine().append("FROM ").append(relation.getName());
    }

    /**
     * Appends the SQL expression for the given expression.
     *
     * @param sb         The string builder.
     * @param expression The unary operator expression.
     * @param level      The indentation level (for formatting).
     */
    private void buildUnaryOperator(IntendingStringBuilder sb, UnaryOperator expression, int level) {
        String query = this.generateSql(expression.getExpression(), level + 1);
        switch (expression) {
            case Selection sel -> {
                sb.append("SELECT DISTINCT ").append(this.generateSelectColumns(sel)).newLine()
                    .append("FROM (").indent().newLine()
                    .append(query).outdent().newLine()
                    .append(") AS selectionSubquery").newLine()
                    .append("WHERE ");

                var first = true;
                for (var comp : sel.getComparisons()) {
                    if (first) first = false;
                    else sb.append(" AND ");

                    sb.append(this.generateComparison(comp, null, null));
                }
            }
            case Projection proj -> {
                sb.append("SELECT DISTINCT ").append(this.generateSelectColumns(proj)).newLine();
                if (query.contains(" ")) {
                    sb.append("FROM (").indent().newLine()
                        .append(query).outdent().newLine()
                        .append(") AS projectionSubquery");
                } else
                    sb.append("FROM ").append(query);
            }
            case Renaming ren -> sb.append("SELECT DISTINCT ").append(this.generateSelectColumns(ren)).newLine()
                .append("FROM (").indent().newLine()
                .append(query).outdent().newLine()
                .append(") AS renamingSubquery");
            default -> {
            }
        }
    }

    /**
     * Appends the SQL expression for the given expression.
     *
     * @param sb         The string builder.
     * @param expression The binary operator expression.
     * @param level      The indentation level (for formatting).
     */
    private void buildBinaryOperator(IntendingStringBuilder sb, BinaryOperator expression, int level) {
        String left = this.generateSql(expression.getLeftExpression(), level + 1);
        String right = this.generateSql(expression.getRightExpression(), level + 1);

        if (expression instanceof Join || (expression instanceof LeftSemiJoin || expression instanceof RightSemiJoin)) {
            sb.append("SELECT DISTINCT ").append(this.generateSelectColumns(expression)).newLine()
                .append("FROM (").indent().newLine()
                .append(left).outdent().newLine()
                .append(") AS joinLeftSideSubquery NATURAL JOIN (").indent().newLine()
                .append(right).outdent().newLine()
                .append(") AS joinRightSideSubquery");
        } else if (expression instanceof Minus) {
            sb.append("SELECT DISTINCT ").append(this.generateSelectColumns(expression)).newLine()
                .append("FROM (").indent().newLine()
                .append(left).outdent().newLine()
                .append(") AS minusLeftSideSubquery EXCEPT (").indent().newLine()
                .append(right).outdent().newLine()
                .append(")");
        } else if (expression instanceof Division) {
            sb.append("SELECT DISTINCT ").append(this.generateSelectColumns(expression)).newLine()
                .append("FROM (").indent().newLine()
                .append(left).outdent().newLine()
                .append(") AS naJoinLeftSideSubquery NATURAL JOIN (").indent().newLine()
                .append(right).outdent().newLine()
                .append(") AS naJoinRightSideSubquery").newLine()
                .append("GROUP BY ").append(this.generateSelectColumns(expression)).newLine()
                .append("HAVING COUNT(*) = (SELECT COUNT(*) FROM (").indent().newLine()
                .append(right).outdent().newLine()
                .append(") AS havingSubquery)");
        } else if (expression instanceof OuterJoin) {
            sb.append("SELECT DISTINCT ").append(this.generateSelectColumns(expression)).newLine()
                .append("FROM (").indent().newLine()
                .append(left).outdent().newLine()
                .append(") AS naFullJoinLeftSideSubquery NATURAL FULL OUTER JOIN (").indent().newLine()
                .append(right).outdent().newLine()
                .append(") AS naFullJoinRightSideSubquery");
        } else if (expression instanceof CartesianProduct) {
            sb.append("SELECT DISTINCT ").append(this.generateSelectColumns(expression)).newLine()
                .append("FROM (").indent().newLine()
                .append(left).outdent().newLine()
                .append(") AS naFullOuterLeftSideSubQu NATURAL FULL OUTER JOIN (").indent().newLine()
                .append(right).outdent().newLine()
                .append(") AS naFullOuterRightSideSubQu");
        } else if (expression instanceof Intersection) {
            sb.append("SELECT DISTINCT ").append(this.generateSelectColumns(expression)).newLine()
                .append("FROM (").indent().newLine()
                .append(left).outdent().newLine()
                .append(") AS intersectLeftSubQu INTERSECT (").indent().newLine()
                .append(right).outdent().newLine()
                .append(")");
        } else if (expression instanceof Union) {
            sb.append("SELECT DISTINCT ").append(this.generateSelectColumns(expression)).newLine()
                .append("FROM (").indent().newLine()
                .append(left).outdent().newLine()
                .append(") AS unionLeftSubQu UNION (").indent().newLine()
                .append(right).outdent().newLine()
                .append(")");
        } else if (expression instanceof ThetaJoin tj) {
            sb.append("SELECT DISTINCT ").append(this.generateSelectColumns(expression)).newLine()
                .append("FROM (").indent().newLine()
                .append(left).outdent().newLine()
                .append(") AS ls, (").indent().newLine()
                .append(right).outdent().newLine()
                .append(") AS rs").newLine()
                .append("WHERE ");

            var first = true;
            for (var comp : tj.getComparisons()) {
                if (first) first = false;
                else sb.append(" AND ");

                sb.append(this.generateComparison(comp, ThetaJoin.LHS, ThetaJoin.RHS));
            }
        }
    }

    /**
     * Generates the comma-separated attribute list for the expression.
     *
     * @param ex The expression.
     * @return The attribute list as string.
     * @see Expression#getSchemaAttributes()
     */
    private String generateSelectColumns(Expression ex) {
        if (ex == null)
            return null;

        List<String> selection = new ArrayList<>();
        for (var attribute : ex.getSchemaAttributes()) {
            if (ex instanceof Renaming ren) {
                if (ren.isAlias(attribute)) {
                    selection.add(ren.getAttributeForAlias(attribute) + " AS " + attribute);
                } else
                    selection.add(attribute);
            } else {
                selection.add(attribute);
            }
        }

        return String.join(", ", selection);
    }

    /**
     * Generates the comparison SQL expression for the given comparison.
     *
     * @param comparison  The comparison.
     * @param leftPrefix  The prefix to prepend on the left side of the comparison.
     * @param rightPrefix The prefix to prepend on the right side of the comparison.
     * @return The comparison.
     */
    private String generateComparison(Comparison comparison, String leftPrefix, String rightPrefix) {
        if (comparison == null)
            return null;

        StringBuilder sb = new StringBuilder();
        BiConsumer<StringBuilder, String> appendPrefix = (s, p) -> {
            if (p != null && !p.isEmpty())
                s.append(p).append('.');
        };
        TriConsumer<ComparisonValueType, String, String> operand = (t, s, p) -> {
            switch (t) {
                case DATE, LITERAL -> {
                    sb.append("'");
                    appendPrefix.accept(sb, p);
                    sb.append(s);
                    sb.append("'");
                }
                case NUMBER, ATTRIBUTE -> {
                    appendPrefix.accept(sb, p);
                    sb.append(s);
                }
            }
        };

        // LEFT
        operand.accept(comparison.getLeftValueType(), comparison.getLeftValue(), leftPrefix);

        // OPERATOR
        sb.append(' ').append(comparison.getOperator().toString()).append(' ');

        // RIGHT
        operand.accept(comparison.getRightValueType(), comparison.getRightValue(), rightPrefix);

        return sb.toString();
    }
}
