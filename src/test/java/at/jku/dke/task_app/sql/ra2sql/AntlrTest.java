package at.jku.dke.task_app.sql.ra2sql;

import at.jku.dke.task_app.sql.ra2sql.parser.RelationalAlgebraSyntaxLexer;
import at.jku.dke.task_app.sql.ra2sql.parser.RelationalAlgebraSyntaxParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AntlrTest {
    @Test
    void test() {
        CharStream stream = CharStreams.fromString("LEFTPARENTHESES x JOIN y RIGHTPARENTHESES");
//        CharStream stream = CharStreams.fromString("PROJECTION LEFTBRACKET x COMMA y COMMA z RIGHTBRACKET LEFTPARENTHESES test RIGHTPARENTHESES");
//        CharStream stream = CharStreams.fromString("test");
        RelationalAlgebraSyntaxLexer lexer = new RelationalAlgebraSyntaxLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RelationalAlgebraSyntaxParser parser = new RelationalAlgebraSyntaxParser(tokens);
        var tmp = parser.root().value;
        System.out.println(tmp);
    }
}
