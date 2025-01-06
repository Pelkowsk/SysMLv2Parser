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




grammar example;

//include license text in generated files
@header {
/*****************************************************************************
 * This file contains classes automatically generated from the SysML 2 grammar.
 * Copyright (c) 2024-2025 RPTU Kaiserslautern
 *
 * This code is based on the SysML 2 Pilot Implementation and adheres to the
 * terms of the GNU Lesser General Public License version 3 or later.
 *
 * The original license header of the SysML 2 Pilot Implementation can be found
 * at: <URL to the original license header or repository>.
 *
 * Note: This header is intentionally different from the official project license
 * header to enable custom compliance checks.
 *
 * SPDX-License-Identifier: LGPL-3.0-or-later
 *****************************************************************************/
}


start : expression;

expression : NUMBER (additiveOperator NUMBER)* ;

additiveOperator : '+'
                  | '-' ;

NUMBER : '-'? [0-9]+ ;

WS : [ \t\r\n]+ -> skip ;

INVALID : . ;