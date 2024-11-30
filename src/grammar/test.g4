grammar KerMLExpressions;

// Rules for expressions

expr
    : conditionalExpression
    ;

conditionalExpression
    : nullCoalescingExpression
    | 'if' nullCoalescingExpression '?' expression 'else' expression
    ;

nullCoalescingExpression
    : impliesExpression ( '??' impliesExpression )*
    ;

impliesExpression
    : orExpression ( 'implies' orExpression )*
    ;

orExpression
    : xorExpression ( ('|' | 'or') xorExpression )*
    ;

xorExpression
    : andExpression ( 'xor' andExpression )*
    ;

andExpression
    : equalityExpression ( ('&' | 'and') equalityExpression )*
    ;

equalityExpression
    : classificationExpression ( ('==' | '!=' | '===' | '!==') classificationExpression )*
    ;

classificationExpression
    : relationalExpression ( ('hastype' | 'istype' | '@') typeReference )?
    | selfReferenceExpression
    | metadataReference
    ;

relationalExpression
    : rangeExpression ( ('<' | '>' | '<=' | '>=') rangeExpression )*
    ;

rangeExpression
    : additiveExpression ( '..' additiveExpression )?
    ;

additiveExpression
    : multiplicativeExpression ( ('+' | '-') multiplicativeExpression )*
    ;

multiplicativeExpression
    : exponentiationExpression ( ('*' | '/' | '%') exponentiationExpression )*
    ;

exponentiationExpression
    : unaryExpression ( ('**' | '^') exponentiationExpression )?
    ;

unaryExpression
    : ('+' | '-' | '~' | 'not') extentExpression
    | extentExpression
    ;

extentExpression
    : 'all' typeReference
    | primaryExpression
    ;

primaryExpression
    : nullExpression
    | literalExpression
    | featureReferenceExpression
    | metadataAccessExpression
    | invocationExpression
    | '(' sequenceExpression ')'
    ;

sequenceExpression
    : expression ( ',' expression )*
    ;

nullExpression
    : 'null' | '(' ')'
    ;

literalExpression
    : literalBoolean | literalString | literalInteger | literalReal | literalInfinity
    ;

literalBoolean
    : 'true' | 'false'
    ;

literalString
    : STRING_VALUE
    ;

literalInteger
    : DECIMAL_VALUE
    ;

literalReal
    : RealValue
    ;

literalInfinity
    : '*'
    ;

// Feature references and metadata

featureReferenceExpression
    : featureReferenceMember
    ;

metadataAccessExpression
    : qualifiedName '.' 'metadata'
    ;

invocationExpression
    : ownedFeatureTyping argumentList
    ;

ownedFeatureTyping
    : qualifiedName
    | ownedFeatureChain
    ;

// Argument list rules

argumentList
    : '(' ( positionalArgumentList | namedArgumentList )? ')'
    ;

positionalArgumentList
    : argumentMember ( ',' argumentMember )*
    ;

namedArgumentList
    : namedArgumentMember ( ',' namedArgumentMember )*
    ;

argumentMember
    : argumentValue
    ;

namedArgumentMember
    : parameterRedefinition '=' argumentValue
    ;

parameterRedefinition
    : qualifiedName
    ;

argumentValue
    : expression
    ;

// Terminal rules

STRING_VALUE
    : '"' ( '\\' . | ~('\\' | '"') )* '"'
    ;

DECIMAL_VALUE
    : '0'..'9' ('0'..'9')*
    ;

RealValue
    : DECIMAL_VALUE? '.' ( DECIMAL_VALUE | EXP_VALUE ) | EXP_VALUE
    ;

EXP_VALUE
    : DECIMAL_VALUE ('e' | 'E') ('+' | '-')? DECIMAL_VALUE
    ;

// Whitespace and comments

WS
    : [ \t\r\n]+ -> skip
    ;

COMMENT
    : '/*' .*? '*/' -> skip
    ;

LINE_COMMENT
    : '//' ~[\r\n]* -> skip
    ;