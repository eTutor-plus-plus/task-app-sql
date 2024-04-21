package at.jku.dke.task_app.sql.ra2sql;

import at.jku.dke.task_app.sql.dto.SchemaInfoDto;
import at.jku.dke.task_app.sql.ra2sql.model.Expression;
import at.jku.dke.task_app.sql.ra2sql.parser.RelationalAlgebraSyntaxLexer;
import at.jku.dke.task_app.sql.ra2sql.parser.RelationalAlgebraSyntaxParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.AbstractMap;
import java.util.List;

/**
 * Converts relational algebra expressions to SQL.
 */
public final class RelationalAlgebraConverter {
    private static final Logger LOG = LoggerFactory.getLogger(RelationalAlgebraConverter.class);
    private static final List<Entry> REPLACEMENTS = List.of(
        new Entry("\\(", " LEFTPARENTHESES ", true),
        new Entry("\\)", " RIGHTPARENTHESES ", true),
        new Entry("\\[", " LEFTBRACKET ", true),
        new Entry("]", " RIGHTBRACKET ", true),
        new Entry(",", " COMMA ", true),
        new Entry(">", " GREATERTHAN ", true),
        new Entry("<", " LESSTHAN ", true),
        new Entry("=", " EQUAL ", true),
        new Entry("\\.", " DOT ", true),
        new Entry("'", " APOSTROPHE ", true),

        new Entry(new String(new char[]{960}), " PROJECTION ", true),
        new Entry(new String(new char[]{963}), " SELECTION ", true),
        new Entry(new String(new char[]{961}), " RENAMING ", true),
        new Entry(new String(new char[]{8904}), " JOIN ", true),
        new Entry(new String(new char[]{10199}), " OUTER_JOIN ", true),
        new Entry(new String(new char[]{8906}), " RIGHTSEMI ", true),
        new Entry(new String(new char[]{8905}), " LEFTSEMI ", true),
        new Entry(new String(new char[]{215}), " CARTESIANPRODUCT ", true),
        new Entry(new String(new char[]{8592}), " LEFTARROW ", true),
        new Entry(new String(new char[]{8746}), " UNION ", true),
        new Entry(new String(new char[]{8745}), " INTERSECTION ", true),
        new Entry(new String(new char[]{8722}), " MINUS ", true),
        new Entry("-", " MINUS ", false),
        new Entry(new String(new char[]{247}), " DIVISION ", true),

        new Entry(new String(new char[]{8847}), " LEFTCURLY ", true),
        new Entry("\\{", " LEFTCURLY ", false),
        new Entry(new String(new char[]{8848}), " RIGHTCURLY ", true),
        new Entry("\\}", " RIGHTCURLY ", false)
    );

    private RelationalAlgebraConverter() {
    }

    /**
     * Converts the relational expression to a SQL statement.
     *
     * @param schemaInfo        The schema information.
     * @param relationalAlgebra The relational algebra.
     * @return The SQL expression.
     * @throws RelationalAlgebraParseException If the relational algebra expression is invalid.
     */
    public static String convertToSql(SchemaInfoDto schemaInfo, String relationalAlgebra) throws RelationalAlgebraParseException {
        // Prepare
        relationalAlgebra = prepareForParsing(relationalAlgebra);

        // Parse
        Expression expression;
        try {
            expression = parse(relationalAlgebra);
        } catch (RelationalAlgebraParseException ex) {
            LOG.debug("Parsing of relational algebra failed: \"{}\", Rule: {}", relationalAlgebra, ex.getRuleName(), ex);
            throw ex;
        }

        // Convert
        return new SqlBuilder(schemaInfo).buildSql(expression);
    }

    /**
     * Converts the parser syntax back to relational algebra syntax.
     *
     * @param syntax The relational algebra expression with parser syntax.
     * @return relational algebra expression
     */
    public static String convertParserSyntaxToRaSyntax(String syntax) {
        for (var replacement : REPLACEMENTS) {
            if (replacement.isDefault())
                syntax = syntax.replaceAll(replacement.getValue().trim(), replacement.getKey().replace("\\", ""));
        }
        return syntax;
    }

    /**
     * Prepares the relational algebra expression by replacing special characters with those expected by the parser.
     *
     * @param relationalAlgebra The relational algebra expression.
     * @return The prepared expression.
     */
    private static String prepareForParsing(String relationalAlgebra) {
        LOG.debug("Preparing relational algebra expression for parsing: {}", relationalAlgebra);
        for (var replacement : REPLACEMENTS) {
            relationalAlgebra = relationalAlgebra.replaceAll(replacement.getKey(), replacement.getValue());
        }
        return relationalAlgebra;
    }

    /**
     * Parses the relational algebra expression.
     *
     * @param relationalAlgebra The expression.
     * @return The parsed expression.
     * @throws RelationalAlgebraParseException If the expression could not be parsed.
     */
    private static Expression parse(String relationalAlgebra) throws RelationalAlgebraParseException {
        LOG.debug("Parsing relational expression: {}", relationalAlgebra);

        var stream = CharStreams.fromString(relationalAlgebra);

        var lexer = new RelationalAlgebraSyntaxLexer(stream);
        lexer.removeErrorListeners();
        lexer.addErrorListener(new RelationalAlgebraSyntaxLexerErrorListener());

        var tokens = new CommonTokenStream(lexer);
        var parser = new RelationalAlgebraSyntaxParser(tokens);
        parser.removeErrorListeners();

        var treeListener = new RelationalAlgebraParseTreeListener(parser);
        var errorListener = new RelationalAlgebraSyntaxParserErrorListener(treeListener);
        parser.addParseListener(treeListener);
        parser.addErrorListener(errorListener);

        return parser.root().value;
    }

    private static class Entry extends AbstractMap.SimpleEntry<String, String> {

        private final boolean isDefault;

        /**
         * Creates a new instance of class {@link Entry}.
         *
         * @param key       The key represented by this entry.
         * @param value     The value represented by this entry.
         * @param isDefault Whether this is the default mapping for the element.
         */
        public Entry(String key, String value, boolean isDefault) {
            super(key, value);
            this.isDefault = isDefault;
        }

        public boolean isDefault() {
            return isDefault;
        }
    }
}
