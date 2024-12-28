package org.example;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/*****************************************************************************
 * SysML 2 Pilot Implementation (Modifizierte Version)
 * Copyright (c) 2018-2024 Model Driven Solutions, Inc.
 * Copyright (c) 2018 IncQuery Labs Ltd.
 * Copyright (c) 2019 Maplesoft (Waterloo Maple, Inc.)
 * Copyright (c) 2019 Mgnite Inc.
 * Copyright (c) 2024 [Dein Name / Deine Organisation] (für Modifikationen)
 *
 * Dieses Programm ist freie Software: Sie können es unter den Bedingungen
 * der GNU Lesser General Public License, wie von der Free Software Foundation
 * veröffentlicht, weiterverteilen und/oder modifizieren, entweder gemäß Version 3
 * der Lizenz oder (nach Ihrer Wahl) jeder späteren Version.
 *
 * Dieses Programm wird in der Hoffnung verbreitet, dass es nützlich sein wird,
 * jedoch ohne jegliche Garantie; sogar ohne die implizite Garantie der
 * Marktgängigkeit oder Eignung für einen bestimmten Zweck. Siehe die
 * GNU Lesser General Public License für weitere Details.
 *
 * Sie sollten eine Kopie der GNU Lesser General Public License zusammen mit
 * diesem Programm erhalten haben. Wenn nicht, siehe <https://www.gnu.org/licenses/>.
 *
 * @license LGPL-3.0-or-later <http://spdx.org/licenses/LGPL-3.0-or-later>
 *
 * Ursprüngliche Beitragende:
 *  - Ed Seidewitz, MDS
 *  - Zoltan Kiss, IncQuery
 *  - Balazs Grill, IncQuery
 *  - Hisashi Miyashita, Maplesoft/Mgnite
 *
 * Beitragende für Modifikationen:
 *  - [Dein Name / Deine Organisation] (2024)
 *****************************************************************************/




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
