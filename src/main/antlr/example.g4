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


//include license text in generated files
@header {
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
}


grammar example;




start : expression;

expression : NUMBER (additiveOperator NUMBER)* ;

additiveOperator : '+'
                  | '-' ;

NUMBER : '-'? [0-9]+ ;

WS : [ \t\r\n]+ -> skip ;

INVALID : . ;