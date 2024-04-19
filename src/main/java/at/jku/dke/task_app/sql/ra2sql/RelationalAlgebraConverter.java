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
import java.util.Map;

/**
 * Converts relational algebra expressions to SQL.
 */
public final class RelationalAlgebraConverter {
    private static final Logger LOG = LoggerFactory.getLogger(RelationalAlgebraConverter.class);
    private static final Map<String, String> REPLACEMENTS = Map.ofEntries(
        new AbstractMap.SimpleEntry<>("\\(", " LEFTPARENTHESES "),
        new AbstractMap.SimpleEntry<>("\\)", " RIGHTPARENTHESES "),
        new AbstractMap.SimpleEntry<>("\\[", " LEFTBRACKET "),
        new AbstractMap.SimpleEntry<>("]", " RIGHTBRACKET "),
        new AbstractMap.SimpleEntry<>(",", " COMMA "),
        new AbstractMap.SimpleEntry<>(">", " GREATERTHAN "),
        new AbstractMap.SimpleEntry<>("<", " LESSTHAN "),
        new AbstractMap.SimpleEntry<>("=", " EQUAL "),
        new AbstractMap.SimpleEntry<>("\\.", " DOT "),
        new AbstractMap.SimpleEntry<>("'", " APOSTROPHE "),

        new AbstractMap.SimpleEntry<>(new String(new char[]{960}), " PROJECTION "),
        new AbstractMap.SimpleEntry<>(new String(new char[]{963}), " SELECTION "),
        new AbstractMap.SimpleEntry<>(new String(new char[]{961}), " RENAMING "),
        new AbstractMap.SimpleEntry<>(new String(new char[]{8904}), " JOIN "),
        new AbstractMap.SimpleEntry<>(new String(new char[]{10199}), " OUTER_JOIN "),
        new AbstractMap.SimpleEntry<>(new String(new char[]{8906}), " RIGHTSEMI "),
        new AbstractMap.SimpleEntry<>(new String(new char[]{8905}), " LEFTSEMI "),
        new AbstractMap.SimpleEntry<>(new String(new char[]{215}), " CARTESIANPRODUCT "),
        new AbstractMap.SimpleEntry<>(new String(new char[]{8592}), " LEFTARROW "),
        new AbstractMap.SimpleEntry<>(new String(new char[]{8746}), " UNION "),
        new AbstractMap.SimpleEntry<>(new String(new char[]{8745}), " INTERSECTION "),
        new AbstractMap.SimpleEntry<>(new String(new char[]{8722}), " MINUS "),
        new AbstractMap.SimpleEntry<>("-", " MINUS "),
        new AbstractMap.SimpleEntry<>(new String(new char[]{247}), " DIVISION "),

        new AbstractMap.SimpleEntry<>(new String(new char[]{8847}), " LEFTCURLY "),
        new AbstractMap.SimpleEntry<>("\\{", " LEFTCURLY "),
        new AbstractMap.SimpleEntry<>(new String(new char[]{8848}), " RIGHTCURLY "),
        new AbstractMap.SimpleEntry<>("\\}", " RIGHTCURLY ")
    );

    private RelationalAlgebraConverter() {
    }

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
     * Prepares the relational algebra expression by replacing special characters with those expected by the parser.
     *
     * @param relationalAlgebra The relational algebra expression.
     * @return The prepared expression.
     */
    private static String prepareForParsing(String relationalAlgebra) {
        LOG.debug("Preparing relational algebra expression for parsing: {}", relationalAlgebra);
        for (var replacement : REPLACEMENTS.entrySet()) {
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
}
