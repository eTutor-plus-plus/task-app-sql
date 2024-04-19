header
	{
		package etutor.modules.ra2sql;

		import antlr.*;
		import java.util.*;
		import etutor.modules.ra2sql.model.*;
	}

class RAParser extends Parser;
options
	{
		defaultErrorHandler = false;
		k = 2;
	}

	{
		public Expression parse() throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException, InvalidAtomException {
			Expression expression = null;

			try {
				expression = this.process();
			} catch (RecognitionException e) {
				e.printStackTrace();
			} catch (TokenStreamException e) {
				e.printStackTrace();
			}

			return expression;
		}
	}


private process returns [Expression expression] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException, InvalidAtomException
	{
		expression = null;
	}
	: expression=expression EOF!
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("process", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("process", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("process", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("process", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("process", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("process", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("process", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("process", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("process", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

private expression returns [Expression expression] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException, InvalidAtomException
	{
		expression = null;
	}
	:(expression=operator |expression=relation)
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("expression", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("expression", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("expression", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("expression", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("expression", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("expression", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("expression", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("expression", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("expression", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

private relation returns [Relation relation] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException
	{
		String name;
		relation = new Relation();
	}
	: name=name
	{
		relation.setName(name);
	}
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("relation", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("relation", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("relation", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("relation", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("relation", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("relation", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("relation", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("relation", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("relation", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

private operator returns [Expression expression] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException, InvalidAtomException
	{
		expression = null;
	}
	: (expression=unary | expression=binary)
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("operator", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("operator", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("operator", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("operator", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("operator", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("operator", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("operator", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("operator", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("operator", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

private unary returns [UnaryOperator uop] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException, InvalidAtomException
	{
		uop = null;
		Expression expression;
	}
	:(uop=selection | uop=projection | uop=renaming) "LEFTPARENTHESES" expression=expression "RIGHTPARENTHESES"
	{
		uop.setExpression(expression);
	}
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("unary operator", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("unary operator", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("unary operator", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("unary operator", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("unary operator", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("unary operator", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("unary operator", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("unary operator", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("unary operator", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

private selection returns [Selection sel] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException, InvalidAtomException
	{
		String value;
		ComparisonOperator op;

		sel = new Selection();
		Comparison comp = new Comparison();
	}
	: "SELECTION" "LEFTBRACKET"
	( value=attribute
	{
		comp.setLeftValue(value);
		comp.setLeftValueType(ComparisonValueType.ATTRIBUTE);
	}
	| value=date
	{
		comp.setLeftValue(value);
		comp.setLeftValueType(ComparisonValueType.DATE);
	}
	| value=literal
	{
		comp.setLeftValue(value);
		comp.setLeftValueType(ComparisonValueType.LITERAL);
	}
	| value=number
	{
		comp.setLeftValue(value);
		comp.setLeftValueType(ComparisonValueType.NUMBER);
	}
	) op=comparisonOperator
	{
		comp.setOperator(op);
	}
	( value=attribute
	{
		comp.setRightValue(value);
		comp.setRightValueType(ComparisonValueType.ATTRIBUTE);
	}
	| value=date
	{
		comp.setRightValue(value);
		comp.setRightValueType(ComparisonValueType.DATE);
	}
	| value=literal
	{
		comp.setRightValue(value);
		comp.setRightValueType(ComparisonValueType.LITERAL);
	}
	| value=number
	{
		comp.setRightValue(value);
		comp.setRightValueType(ComparisonValueType.NUMBER);
	}
	)
	{
		sel.addComparison(comp);
	}
	( "COMMA"
	{
		comp = new Comparison();
	}
	( value=attribute
	{
		comp.setLeftValue(value);
		comp.setLeftValueType(ComparisonValueType.ATTRIBUTE);
	}
	| value=date
	{
		comp.setLeftValue(value);
		comp.setLeftValueType(ComparisonValueType.DATE);
	}
	| value=literal
	{
		comp.setLeftValue(value);
		comp.setLeftValueType(ComparisonValueType.LITERAL);
	}
	| value=number
	{
		comp.setLeftValue(value);
		comp.setLeftValueType(ComparisonValueType.NUMBER);
	}
	) op=comparisonOperator
	{
		comp.setOperator(op);
	}
	( value=attribute
	{
		comp.setRightValue(value);
		comp.setRightValueType(ComparisonValueType.ATTRIBUTE);
	}
	| value=literal
	{
		comp.setRightValue(value);
		comp.setRightValueType(ComparisonValueType.LITERAL);
	}
	| value=date
	{
		comp.setRightValue(value);
		comp.setRightValueType(ComparisonValueType.DATE);
	}
	| value=number
	{
		comp.setRightValue(value);
		comp.setRightValueType(ComparisonValueType.NUMBER);
	}
	)
	{
		sel.addComparison(comp);
	}
	)* "RIGHTBRACKET"
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("selection", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("selection", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("selection", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("selection", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("selection", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("selection", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("selection", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("selection", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("selection", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}


private projection returns [Projection pro] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException
	{
		String att;
		pro = new Projection();
	}
	: "PROJECTION" "LEFTBRACKET" att=attribute
	{
		pro.addProjectionAttribute(att);
	}
	( "COMMA" att=attribute
	{
		pro.addProjectionAttribute(att);
	}
	)* "RIGHTBRACKET"
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("projection", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("projection", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("projection", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("projection", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("projection", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("projection", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("projection", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("projection", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("projection", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

private renaming returns [Renaming ren] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException
	{
		String att;
		String alias;
		ren = new Renaming();
	}
	: "RENAMING" "LEFTBRACKET" att=attribute "LEFTARROW" alias=attribute
	{
		ren.addAttributeAlias(att,alias);
	}
	( "COMMA" att=attribute "LEFTARROW" alias=attribute
	{
		ren.addAttributeAlias(att,alias);
	}
	)* "RIGHTBRACKET"
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("renaming", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("renaming", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("renaming", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("renaming", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("renaming", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("renaming", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("renaming", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("renaming", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("renaming", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

private binary returns [BinaryOperator bop] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException, InvalidAtomException
	{
		bop = null;
		Expression leftExp;
		Expression rightExp;
	}
	: "LEFTPARENTHESES" leftExp=expression
	( bop=join
	| bop=intersection
	| bop=union
	| bop=division
	| bop=minus
	| bop=cartesianProduct
	| bop=leftSemiJoin
	| bop=rightSemiJoin
	| bop=thetaJoin
	| bop=outerJoin
	) rightExp=expression "RIGHTPARENTHESES"
	{
		bop.setLeftExpression(leftExp);
		bop.setRightExpression(rightExp);
	}
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("binary operator", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("binary operator", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("binary operator", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("binary operator", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("binary operator", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("binary operator", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("binary operator", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("binary operator", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("binary operator", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

private join returns [Join join] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException
	{
		join = null;
	}
	: "JOIN"
	{
		join = new Join();
	}
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("join", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("join", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("join", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("join", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("join", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("join", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("join", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("join", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("join", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

private intersection returns [Intersection intersection] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException
	{
		intersection = null;
	}
	: "INTERSECTION"
	{
		intersection = new Intersection();
	}
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("intersection", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("intersection", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("intersection", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("intersection", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("intersection", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("intersection", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("intersection", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("intersection", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("intersection", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

private union returns [Union union] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException
	{
		union = null;
	}
	: "UNION"
	{
		union = new Union();
	}
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("union", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("union", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("union", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("union", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("union", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("union", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("union", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("union", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("union", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

private outerJoin returns [OuterJoin outerJoin] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException
	{
		outerJoin = null;
	}
	: "OUTER_JOIN"
	{
		outerJoin = new OuterJoin();
	}
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("outer join", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("outer join", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("outer join", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("outer join", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("outer join", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("outer join", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("outer join", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("outer join", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("outer join", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

private division returns [Division division] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException
	{
		division = null;
	}
	: "DIVISION"
	{
		division = new Division();
	}
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("division", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("division", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("division", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("division", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("division", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("division", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("division", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("division", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("division", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

private minus returns [Minus minus] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException
	{
		minus = null;
	}
	: "MINUS"
	{
		minus = new Minus();
	}
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("minus", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("minus", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("minus", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("minus", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("minus", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("minus", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("minus", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("minus", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("minus", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

private cartesianProduct returns [CartesianProduct cartesianProduct] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException
	{
		cartesianProduct = null;
	}
	: "CARTESIANPRODUCT"
	{
		cartesianProduct = new CartesianProduct();
	}
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("cartesian product", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("cartesian product", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("cartesian product", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("cartesian product", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("cartesian product", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("cartesian product", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("cartesian product", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("cartesian product", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("cartesian product", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

private leftSemiJoin returns [LeftSemiJoin leftSemiJoin] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException
	{
		leftSemiJoin = null;
	}
	: "LEFTSEMI"
	{
		leftSemiJoin = new LeftSemiJoin();
	}
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("left semi join", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("left semi join", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("left semi join", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("left semi join", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("left semi join", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("left semi join", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("left semi join", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("left semi join", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("left semi join", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

private rightSemiJoin returns [RightSemiJoin rightSemiJoin] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException
	{
		rightSemiJoin = null;
	}
	: "RIGHTSEMI"
	{
		rightSemiJoin = new RightSemiJoin();
	}
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("right semi join", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("right semi join", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("right semi join", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("right semi join", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("right semi join", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("right semi join", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("right semi join", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("right semi join", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("right semi join", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

private thetaJoin returns [ThetaJoin tj] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException
	{
		String value;
		Comparison comp;
		ComparisonOperator op;

		value = null;
		tj = new ThetaJoin();
		comp = new Comparison();
	}
	: "LEFTCURLY" value=attribute
	{
		comp.setLeftValue(value);
		comp.setLeftValueType(ComparisonValueType.ATTRIBUTE);
	}
	  op=comparisonOperator
	{
		comp.setOperator(op);
	}
	  value=attribute
	{
		comp.setRightValue(value);
		comp.setRightValueType(ComparisonValueType.ATTRIBUTE);
		tj.addComparison(comp);
	}

	( "COMMA"
	{
		comp = new Comparison();
	}
	  value=attribute
	{
		comp.setLeftValue(value);
		comp.setLeftValueType(ComparisonValueType.ATTRIBUTE);
	}
	  op=comparisonOperator
	{
		comp.setOperator(op);
	}
	  value=attribute
	{
		comp.setRightValue(value);
		comp.setRightValueType(ComparisonValueType.ATTRIBUTE);
		tj.addComparison(comp);
	}
	)* "RIGHTCURLY"
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("theta join", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("theta join", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("theta join", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("theta join", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("theta join", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("theta join", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("theta join", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("theta join", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("theta join", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}


private comparisonOperator returns [ComparisonOperator operator] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException
	{
		operator = null;
	}
	:
	( "LESSTHAN"
        {
            operator = ComparisonOperator.LESS_THAN;
        }
        ( "EQUAL"
            {
                operator = ComparisonOperator.LESS_OR_EQUAL;
            }
            | "GREATERTHAN"
            {
                operator = ComparisonOperator.NOT_EQUAL;
            }
        )?
        | "GREATERTHAN"
        {
            operator = ComparisonOperator.GREATER_THAN;
        }
        ( "EQUAL"
            {
                operator = ComparisonOperator.GREATER_OR_EQUAL;
            }
        )?
        | "EQUAL"
        {
            operator = ComparisonOperator.EQUAL;
        }
	);
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("comparison operator", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("comparison operator", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("comparison operator", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("comparison operator", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("comparison operator", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("comparison operator", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("comparison operator", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("comparison operator", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("comparison operator", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}


private literal returns [String literal] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException
	{
		literal = null;
	}
	: "APOSTROPHE" leadingLiteral:STRING^
	{
		literal=leadingLiteral.getText();
	}
	(
	  followingLiteral:STRING^
	{
		literal=literal.concat(" " + followingLiteral.getText());
	}
	)* "APOSTROPHE"
	;

	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("literal", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("literal", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("literal", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("literal", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("literal", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("literal", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("literal", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("literal", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("literal", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

private attribute returns [String attribute] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException
	{
		attribute = null;
	}
	: tempAttribute:STRING^
	{
		attribute = tempAttribute.getText();
	}
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("attribute", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("attribute", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("attribute", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("attribute", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("attribute", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("attribute", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("attribute", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("attribute", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("attribute", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

private name returns [String name] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException
	{
		name = null;
	}
	: tempName:STRING^
	{
		name = tempName.getText();
	}
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("name", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("name", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("name", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("name", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("name", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("name", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("name", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("name", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("name", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

private number returns [String number] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException
	{
		number = null;
	}
	: tempNumber:NUMBER^
	{
		number = tempNumber.getText();
	}
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("number", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("number", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("number", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("number", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("number", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("number", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("number", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("number", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("number", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

private date returns [String date] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException, InvalidAtomException
	{
		int column = 0;
		date = new String();
		String day = new String();
		String year = new String();
		String month = new String();
		GregorianCalendar gc = null;
	}
	: "APOSTROPHE"
	{
		column = this.LT(1).getColumn();
	}
	  day=day "DOT" month=month "DOT" year=year "APOSTROPHE"
	{
		date = 	day + "." + month + "." + year;
		gc = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day));
		gc.setLenient(false);
		gc.getTime();
	}
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("date", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("date", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("date", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("date", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("date", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("date", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("date", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("date", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("date", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [IllegalArgumentException ex] {
		InvalidAtomException iae = new InvalidAtomException();
		iae.setRule("date");
		iae.setLine(this.LT(1).getLine());
		iae.setColumn(column);
		iae.setInvalidAtom(date);
		throw iae;
	}

private year returns [String year] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException
	{
		year = null;
	}
	: tempYear:NUMBER^
	{
		year = tempYear.getText();
	}
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("year", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("year", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("year", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("year", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("year", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("year", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("year", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("year", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("year", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

private month returns [String month] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException
	{
		month = null;
	}
	: tempMonth:NUMBER^
	{
		month = tempMonth.getText();
	}
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("month", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("month", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("month", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("month", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("month", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("month", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("month", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("month", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("month", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

private day returns [String day] throws UnexpectedAtomException, MismatchedAtomException, AtomStreamIOException
	{
		day = null;
	}
	: tempDay:NUMBER^
	{
		day = tempDay.getText();
	}
	;
	exception
	catch [RecognitionException ex] {
		if (ex instanceof MismatchedCharException) {
			throw new MismatchedAtomException("day", (MismatchedCharException)ex);
		}
		if (ex instanceof MismatchedTokenException) {
			throw new MismatchedAtomException("day", (MismatchedTokenException)ex, this.tokenNames);
		}
		if (ex instanceof NoViableAltException) {
			throw new UnexpectedAtomException("day", (NoViableAltException)ex);
		}
		if (ex instanceof NoViableAltForCharException) {
			throw new UnexpectedAtomException("day", (NoViableAltForCharException)ex);
		}
		if (ex instanceof SemanticException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

	catch [TokenStreamException ex] {
		if (ex instanceof TokenStreamIOException) {
			throw new AtomStreamIOException("day", (TokenStreamIOException)ex);
		}
		if (ex instanceof TokenStreamRecognitionException) {
			RecognitionException re = ((TokenStreamRecognitionException)ex).recog;
			if (re instanceof MismatchedCharException) {
				throw new MismatchedAtomException("day", (MismatchedCharException)re);
			}
			if (re instanceof MismatchedTokenException) {
				throw new MismatchedAtomException("day", (MismatchedTokenException)re, this.tokenNames);
			}
			if (re instanceof NoViableAltException) {
				throw new UnexpectedAtomException("day", (NoViableAltException)re);
			}
			if (re instanceof NoViableAltForCharException) {
				throw new UnexpectedAtomException("day", (NoViableAltForCharException)re);
			}
			if (re instanceof SemanticException) {
				//Impossible to happen in current grammar!
				re.printStackTrace();
			}
		}
		if (ex instanceof TokenStreamRetryException) {
			//Impossible to happen in current grammar!
			ex.printStackTrace();
		}
	}

class RALexer extends Lexer;
options
	{
		defaultErrorHandler = false;
		k = 2;
	}

	{
		private int tabSize = 2;

		public RALexer(Reader in, int tabSize) {
			this(new CharBuffer(in));
			this.tabSize = tabSize;
		}

	}
	WS
		:
		( ' '
		| '\t'
		{
			this.setColumn(this.getColumn() + this.tabSize);
		}
		| '\n'
		{
			this.newline();
		}
		| '\r'
		) + { $setType(Token.SKIP); }
		;

	STRING
	options
		{
			paraphrase = "a string";
		}
		: ('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|('0'..'9')|'_'|'-')*
		;

	NUMBER
	options
		{
			paraphrase = "a number";
		}
		: ('0'..'9') ('0'..'9')*
		;
