/*Testheader für Sicherheit*/


/*bittorrent-1.0*/

/*MPL-1.0*/

grammar beispiel;

start : expression ;

expression : NUMBER (additiveOperator NUMBER)* ;

additiveOperator : '+'
                  | '-' ;

NUMBER : '-'? [0-9]+ ;

WS : [ \t\r\n]+ -> skip ;

INVALID : . ;