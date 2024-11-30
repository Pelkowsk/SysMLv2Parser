grammar sysmlv2beispiel;

NUMBER : '-'? [0-9]+ ;

WS : [ \t\r\n]+ -> skip ;

INVALID : . ;

start : expression ;

expression : NUMBER (additiveOperator NUMBER)* ;

additiveOperator : '+'
                  | '-' ;

