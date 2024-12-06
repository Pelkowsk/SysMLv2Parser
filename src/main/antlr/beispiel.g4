/*****************************************************************************
 * SysML 2 Pilot Implementation
 * Copyright (c) 2018-2024 Model Driven Solutions, Inc.
 * Copyright (c) 2018 IncQuery Labs Ltd.
 * Copyright (c) 2019 Maplesoft (Waterloo Maple, Inc.)
 * Copyright (c) 2019 Mgnite Inc.
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
/*"agpl-1.0-only", "agpl-1.0-or-later", "agpl-3.0-only", "agpl-3.0-or-later",
    "bittorrent-1.0", "bittorrent-1.1",
    "cc-by-nc-1.0", "cc-by-nc-2.0", "cc-by-nc-2.5", "cc-by-nc-3.0", "cc-by-nc-4.0",
    "cc-by-nc-nd-1.0", "cc-by-nc-nd-2.0", "cc-by-nc-nd-2.5", "cc-by-nc-nd-3.0", "cc-by-nc-nd-4.0",
    "cc-by-nc-sa-1.0", "cc-by-nc-sa-2.0", "cc-by-nc-sa-2.5", "cc-by-nc-sa-3.0", "cc-by-nc-sa-4.0",
    "cpal-1.0", "epl-1.0", "epl-2.0", "eupl-1.1", "eupl-1.2",
    "ipl-1.0", "ms-pl", "mpl-1.0", "mpl-1.1", "mpl-2.0",
    "osl-3.0", "sspl-1.0",
    "unlicense", "wtfpl", "zlib-acknowledgement"*/

grammar beispiel;

start : expression ;

expression : NUMBER (additiveOperator NUMBER)* ;

additiveOperator : '+'
                  | '-' ;

NUMBER : '-'? [0-9]+ ;

WS : [ \t\r\n]+ -> skip ;

INVALID : . ;