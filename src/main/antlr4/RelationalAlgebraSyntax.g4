grammar RelationalAlgebraSyntax;

@header {
    package at.jku.dke.task_app.sql.ra2sql.parser;

    import java.util.GregorianCalendar;
    import at.jku.dke.task_app.sql.ra2sql.model.*;
}

// GENERAL
root returns [Expression value]
    : ex=expression EOF {$value = $ex.value;};

expression returns [Expression value]
    : (e1=operator {$value = $e1.value;} | e2=relation {$value = $e2.value;});

relation returns [Relation value]
    @init {
        $value = new Relation();
    } : name {$value.setName($name.text);};

operator returns [Expression value]
    : (e1=unary {$value = $e1.value;} | e2=binary {$value = $e2.value;});

// UNARY
unary returns [Expression value]
    : (
        sel=selection 'LEFTPARENTHESES' expr1=expression 'RIGHTPARENTHESES' {$sel.value.setExpression($expr1.value); $value = $sel.value;} |
        proj=projection 'LEFTPARENTHESES' expr2=expression 'RIGHTPARENTHESES' {$proj.value.setExpression($expr2.value); $value = $proj.value;} |
        ren=renaming 'LEFTPARENTHESES' expr3=expression 'RIGHTPARENTHESES' {$ren.value.setExpression($expr3.value); $value = $ren.value;}
    );

selection returns [Selection value]
    @init {
        $value = new Selection();
        Comparison comp = new Comparison();
    } : 'SELECTION' 'LEFTBRACKET' (
        v1=attribute {
            comp.setLeftValue($v1.value);
            comp.setLeftValueType(ComparisonValueType.ATTRIBUTE);
        }
        |
        v2=date {
            comp.setLeftValue($v2.value);
            comp.setLeftValueType(ComparisonValueType.DATE);
        }
        |
        v3=literal {
            comp.setLeftValue($v3.value);
            comp.setLeftValueType(ComparisonValueType.LITERAL);
        }
        |
        v4=number {
            comp.setLeftValue($v4.value);
            comp.setLeftValueType(ComparisonValueType.NUMBER);
        }
    ) op=comparisonOperator {comp.setOperator($op.value);} (
        w1=attribute {
            comp.setRightValue($w1.value);
            comp.setRightValueType(ComparisonValueType.ATTRIBUTE);
        }
        |
        w2=date {
            comp.setRightValue($w2.value);
            comp.setRightValueType(ComparisonValueType.DATE);
        }
        |
        w3=literal {
            comp.setRightValue($w3.value);
            comp.setRightValueType(ComparisonValueType.LITERAL);
        }
        |
        w4=number {
            comp.setRightValue($w4.value);
            comp.setRightValueType(ComparisonValueType.NUMBER);
        }
    ) {$value.addComparison(comp);} (
        'COMMA' {comp = new Comparison();}
        (
            x1=attribute {
                comp.setLeftValue($x1.value);
                comp.setLeftValueType(ComparisonValueType.ATTRIBUTE);
            }
            |
            x2=date {
                comp.setLeftValue($x2.value);
                comp.setLeftValueType(ComparisonValueType.DATE);
            }
            |
            x3=literal {
                comp.setLeftValue($x3.value);
                comp.setLeftValueType(ComparisonValueType.LITERAL);
            }
            |
            x4=number {
                comp.setLeftValue($x4.value);
                comp.setLeftValueType(ComparisonValueType.NUMBER);
            }
        ) op=comparisonOperator {comp.setOperator($op.value);} (
            y1=attribute {
                comp.setRightValue($y1.value);
                comp.setRightValueType(ComparisonValueType.ATTRIBUTE);
            }
            |
            y2=date {
                comp.setRightValue($y2.value);
                comp.setRightValueType(ComparisonValueType.DATE);
            }
            |
            y3=literal {
                comp.setRightValue($y3.value);
                comp.setRightValueType(ComparisonValueType.LITERAL);
            }
            |
            y4=number {
                comp.setRightValue($y4.value);
                comp.setRightValueType(ComparisonValueType.NUMBER);
            }
        ) {$value.addComparison(comp);}
    )* 'RIGHTBRACKET';

projection returns [Projection value]
    @init {
        $value = new Projection();
        String att;
    } : 'PROJECTION' 'LEFTBRACKET' att=attribute {$value.addProjectionAttribute($att.value);}
        ('COMMA' att=attribute {$value.addProjectionAttribute($att.value);})* 'RIGHTBRACKET';

renaming returns [Renaming value]
    @init {
        $value = new Renaming();
    } : 'RENAMING' 'LEFTBRACKET' att=attribute 'LEFTARROW' alias=attribute {$value.addAttributeAlias($att.value, $alias.value);}
        ('COMMA' att=attribute 'LEFTARROW' alias=attribute {$value.addAttributeAlias($att.value, $alias.value);})* 'RIGHTBRACKET';

// BINARY
binary returns [BinaryOperator value]
    : 'LEFTPARENTHESES' leftExp=expression (
            vj=join {$value = $vj.value;} |
            vi=intersection {$value = $vi.value;} |
            vu=union {$value = $vu.value;} |
            vd=division {$value = $vd.value;} |
            vm=minus {$value = $vm.value;} |
            vc=cartesianProduct {$value = $vc.value;} |
            vl=leftSemiJoin {$value = $vl.value;} |
            vr=rightSemiJoin {$value = $vr.value;} |
            vo=outerJoin {$value = $vo.value;} |
            vt=thetaJoin {$value = $vt.value;}
        )
        rightExp=expression 'RIGHTPARENTHESES' {$value.setLeftExpression($leftExp.value); $value.setRightExpression($rightExp.value);};

join returns [Join value]
    : 'JOIN' {$value = new Join();};

intersection returns [Intersection value]
    : 'INTERSECTION' {$value = new Intersection();};

union returns [Union value]
    : 'UNION' {$value = new Union();};

outerJoin returns [OuterJoin value]
    : 'OUTER_JOIN' {$value = new OuterJoin();};

division returns [Division value]
    : 'DIVISION' {$value = new Division();};

minus returns [Minus value]
    : 'MINUS' {$value = new Minus();};

cartesianProduct returns [CartesianProduct value]
    : 'CARTESIANPRODUCT' {$value = new CartesianProduct();};

leftSemiJoin returns [LeftSemiJoin value]
    : 'LEFTSEMI' {$value = new LeftSemiJoin();};

rightSemiJoin returns [RightSemiJoin value]
    : 'RIGHTSEMI' {$value = new RightSemiJoin();};

thetaJoin returns [ThetaJoin value]
    @init {
        Comparison comp = new Comparison();
        $value = new ThetaJoin();
    } : 'LEFTCURLY' val=attribute {
        comp.setLeftValue($val.value);
        comp.setLeftValueType(ComparisonValueType.ATTRIBUTE);
    } op=comparisonOperator {
        comp.setOperator($op.value);
    } val=attribute {
        comp.setRightValue($val.value);
        comp.setRightValueType(ComparisonValueType.ATTRIBUTE);
        $value.addComparison(comp);
    } ('COMMA' {comp = new Comparison();}
        val=attribute {
            comp.setLeftValue($val.value);
            comp.setLeftValueType(ComparisonValueType.ATTRIBUTE);
        } op=comparisonOperator {
            comp.setOperator($op.value);
        } val=attribute {
            comp.setRightValue($val.value);
            comp.setRightValueType(ComparisonValueType.ATTRIBUTE);
            $value.addComparison(comp);
        })* 'RIGHTCURLY';

// COMPARISON
comparisonOperator returns [ComparisonOperator value]
    : (
        'LESSTHAN' {$value = ComparisonOperator.LESS_THAN;}
        (
            'EQUAL' {$value = ComparisonOperator.LESS_OR_EQUAL;} |
            'GREATERTHAN' {$value = ComparisonOperator.NOT_EQUAL;}
        )? |
        'GREATERTHAN' {$value = ComparisonOperator.GREATER_THAN;}
        (
            'EQUAL' {$value = ComparisonOperator.GREATER_OR_EQUAL;}
        )? |
        'EQUAL' {$value = ComparisonOperator.EQUAL;}
      );

// LITERALS
literal returns [String value]
    : 'APOSTROPHE' STRING {$value = $STRING.text;} (STRING {$value = $value + " " + $STRING.text;}|NUMBER {$value = $value + " " + $NUMBER.text;})* 'APOSTROPHE';

date returns [String value]
    @init {
        GregorianCalendar gc = null;
    } : 'APOSTROPHE' day 'DOT' month 'DOT' year 'APOSTROPHE' {
        $value = $day.text + "." + $month.text + "." + $year.text;

        // Parse date
        gc = new GregorianCalendar(Integer.parseInt($year.text), Integer.parseInt($month.text) - 1, Integer.parseInt($day.text));
        gc.setLenient(false);
        gc.getTime();
    };

year returns [String value]
    : NUMBER {$value = $NUMBER.text;};

month returns [String value]
    : NUMBER {$value = $NUMBER.text;};

day returns [String value]
    : NUMBER {$value = $NUMBER.text;};

attribute returns [String value]
    : STRING {$value = $STRING.text;};

name returns [String value]
    : STRING {$value = $STRING.text;};

number returns [String value]
    : NUMBER {$value = $NUMBER.text;} ('DOT' NUMBER {$value = $value + "." + $NUMBER.text;})?;

// LEXER
WS : (' ' | '\t' | '\n' | '\r')+ -> skip;
STRING : ('a'..'z'|'A'..'Z') ('a'..'z' | 'A'..'Z' | ('0'..'9') | '_' | '-')*;
NUMBER : ('0'..'9') ('0'..'9')*;
