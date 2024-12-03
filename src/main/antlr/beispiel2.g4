grammar beispiel2;

start : expression ;

expression : NUMBER (additiveOperator NUMBER)* ;

additiveOperator : '+'
                  | '-' ;

NUMBER : '-'? [0-9]+ ;

WS : [ \t\r\n]+ -> skip ;

INVALID : . ;