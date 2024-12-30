package org.example;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/*****************************************************************************
 * SysML 2 Pilot Implementation
 * Copyright (c) 2018-2024 Model Driven Solutions, Inc.
 * Copyright (c) 2018-2024 IncQuery Labs Ltd.
 * Copyright (c) 2019-2023 Maplesoft (Waterloo Maple, Inc.)
 * Copyright (c) 2019-2023 Mgnite Inc.
 * Copyright (c) 2024-2025 [Your Organization]
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * @license LGPL-3.0-or-later <http://spdx.org/licenses/LGPL-3.0-or-later>
 *
 * Contributors:
 *  Ed Seidewitz, MDS
 *  Zoltan Kiss, IncQuery
 *  Balazs Grill, IncQuery
 *  Hisashi Miyashita, Maplesoft/Mgnite
 *
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
