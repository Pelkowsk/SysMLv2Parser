package org.example;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;




public class GrammarTest {
//Checks whether the input text contains syntax errors. testValidInput() -> must only contain valid input data, testInvalidInput() -> must contain invalid input.
    @Test
    public void testValidInput() {
        String input = "-1+2";


        omg.exampleLexer lexer = new omg.exampleLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        omg.exampleParser parser = new omg.exampleParser(tokens);

        ParseTree tree = parser.start();
        assertEquals(0, parser.getNumberOfSyntaxErrors(), "There shouldn't be any syntax errors");
    }

    @Test
    public void testInvalidInput() {
        String input = "1+2+a";
        omg.exampleLexer lexer = new omg.exampleLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        omg.exampleParser parser = new omg.exampleParser(tokens);

        parser.start();
        assertTrue(parser.getNumberOfSyntaxErrors() > 0, "There should be syntax errors");
    }
}
