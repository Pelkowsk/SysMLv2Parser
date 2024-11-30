grammar demo;



// Lexer-Regeln (Tokens)
IF: 'if';
ELSE: 'else';
PLUS: '+';
MINUS: '-';
MUL: '*';
DIV: '/';
MOD: '%';
QUESTION: '?';
COLON: ':';
GT: '>';
LT: '<';
GE: '>=';
LE: '<=';
EQ: '==';
NEQ: '!=';
AND: 'and';
OR: 'or';
XOR: 'xor';
IMPLIES: 'implies';
NUMBER: [0-9]+;
ID: [a-zA-Z_][a-zA-Z_0-9]*;
WS: [ \t\r\n]+ -> skip;

// Parser-Regeln
expr: conditionalExpr;

conditionalExpr:
    nullCoalescingExpr
    | IF nullCoalescingExpr QUESTION ownedExprRef ELSE ownedExprRef
;

nullCoalescingExpr:
    impliesExpr ('??' impliesExpr)*
;

ownedExprRef:
    ID // Vereinfachte Regel, falls `OwnedExpressionReference` nur ein Identifier ist
;

impliesExpr:
    orExpr (IMPLIES orExpr)*
;

orExpr:
    xorExpr ((OR | '|') xorExpr)*
;

xorExpr:
    andExpr (XOR andExpr)*
;

andExpr:
    equalityExpr ((AND | '&') equalityExpr)*
;

equalityExpr:
    relationalExpr ((EQ | NEQ) relationalExpr)*
;

relationalExpr:
    additiveExpr ((LT | GT | LE | GE) additiveExpr)*
;

additiveExpr:
    multiplicativeExpr ((PLUS | MINUS) multiplicativeExpr)*
;

multiplicativeExpr:
    primaryExpr ((MUL | DIV | MOD) primaryExpr)*
;

primaryExpr:
    NUMBER
    | ID
    | '(' expr ')'
;