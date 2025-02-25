// Generated from C:/Users/dke/Documents/GitHub/task-app-sql/src/main/antlr4/RelationalAlgebraSyntax.g4 by ANTLR 4.13.2

    package at.jku.dke.task_app.sql.ra2sql.parser;

    import java.util.GregorianCalendar;
    import at.jku.dke.task_app.sql.ra2sql.model.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class RelationalAlgebraSyntaxParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, WS=26, STRING=27, NUMBER=28;
	public static final int
		RULE_root = 0, RULE_expression = 1, RULE_relation = 2, RULE_operator = 3, 
		RULE_unary = 4, RULE_selection = 5, RULE_projection = 6, RULE_renaming = 7, 
		RULE_binary = 8, RULE_join = 9, RULE_intersection = 10, RULE_union = 11, 
		RULE_outerJoin = 12, RULE_division = 13, RULE_minus = 14, RULE_cartesianProduct = 15, 
		RULE_leftSemiJoin = 16, RULE_rightSemiJoin = 17, RULE_thetaJoin = 18, 
		RULE_comparisonOperator = 19, RULE_literal = 20, RULE_date = 21, RULE_year = 22, 
		RULE_month = 23, RULE_day = 24, RULE_attribute = 25, RULE_name = 26, RULE_number = 27;
	private static String[] makeRuleNames() {
		return new String[] {
			"root", "expression", "relation", "operator", "unary", "selection", "projection", 
			"renaming", "binary", "join", "intersection", "union", "outerJoin", "division", 
			"minus", "cartesianProduct", "leftSemiJoin", "rightSemiJoin", "thetaJoin", 
			"comparisonOperator", "literal", "date", "year", "month", "day", "attribute", 
			"name", "number"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'LEFTPARENTHESES'", "'RIGHTPARENTHESES'", "'SELECTION'", "'LEFTBRACKET'", 
			"'COMMA'", "'RIGHTBRACKET'", "'PROJECTION'", "'RENAMING'", "'LEFTARROW'", 
			"'JOIN'", "'INTERSECTION'", "'UNION'", "'OUTER_JOIN'", "'DIVISION'", 
			"'MINUS'", "'CARTESIANPRODUCT'", "'LEFTSEMI'", "'RIGHTSEMI'", "'LEFTCURLY'", 
			"'RIGHTCURLY'", "'LESSTHAN'", "'EQUAL'", "'GREATERTHAN'", "'APOSTROPHE'", 
			"'DOT'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "WS", "STRING", "NUMBER"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "RelationalAlgebraSyntax.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RelationalAlgebraSyntaxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RootContext extends ParserRuleContext {
		public Expression value;
		public ExpressionContext ex;
		public TerminalNode EOF() { return getToken(RelationalAlgebraSyntaxParser.EOF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitRoot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitRoot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			((RootContext)_localctx).ex = expression();
			setState(57);
			match(EOF);
			((RootContext)_localctx).value =  ((RootContext)_localctx).ex.value;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public Expression value;
		public OperatorContext e1;
		public RelationContext e2;
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public RelationContext relation() {
			return getRuleContext(RelationContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case T__2:
			case T__6:
			case T__7:
				{
				setState(60);
				((ExpressionContext)_localctx).e1 = operator();
				((ExpressionContext)_localctx).value =  ((ExpressionContext)_localctx).e1.value;
				}
				break;
			case STRING:
				{
				setState(63);
				((ExpressionContext)_localctx).e2 = relation();
				((ExpressionContext)_localctx).value =  ((ExpressionContext)_localctx).e2.value;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RelationContext extends ParserRuleContext {
		public Relation value;
		public NameContext name;
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public RelationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterRelation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitRelation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitRelation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationContext relation() throws RecognitionException {
		RelationContext _localctx = new RelationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_relation);

		        ((RelationContext)_localctx).value =  new Relation();
		    
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			((RelationContext)_localctx).name = name();
			_localctx.value.setName((((RelationContext)_localctx).name!=null?_input.getText(((RelationContext)_localctx).name.start,((RelationContext)_localctx).name.stop):null));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OperatorContext extends ParserRuleContext {
		public Expression value;
		public UnaryContext e1;
		public BinaryContext e2;
		public UnaryContext unary() {
			return getRuleContext(UnaryContext.class,0);
		}
		public BinaryContext binary() {
			return getRuleContext(BinaryContext.class,0);
		}
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_operator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
			case T__6:
			case T__7:
				{
				setState(71);
				((OperatorContext)_localctx).e1 = unary();
				((OperatorContext)_localctx).value =  ((OperatorContext)_localctx).e1.value;
				}
				break;
			case T__0:
				{
				setState(74);
				((OperatorContext)_localctx).e2 = binary();
				((OperatorContext)_localctx).value =  ((OperatorContext)_localctx).e2.value;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnaryContext extends ParserRuleContext {
		public Expression value;
		public SelectionContext sel;
		public ExpressionContext expr1;
		public ProjectionContext proj;
		public ExpressionContext expr2;
		public RenamingContext ren;
		public ExpressionContext expr3;
		public SelectionContext selection() {
			return getRuleContext(SelectionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ProjectionContext projection() {
			return getRuleContext(ProjectionContext.class,0);
		}
		public RenamingContext renaming() {
			return getRuleContext(RenamingContext.class,0);
		}
		public UnaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterUnary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitUnary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitUnary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryContext unary() throws RecognitionException {
		UnaryContext _localctx = new UnaryContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_unary);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				{
				setState(79);
				((UnaryContext)_localctx).sel = selection();
				setState(80);
				match(T__0);
				setState(81);
				((UnaryContext)_localctx).expr1 = expression();
				setState(82);
				match(T__1);
				((UnaryContext)_localctx).sel.value.setExpression(((UnaryContext)_localctx).expr1.value); ((UnaryContext)_localctx).value =  ((UnaryContext)_localctx).sel.value;
				}
				break;
			case T__6:
				{
				setState(85);
				((UnaryContext)_localctx).proj = projection();
				setState(86);
				match(T__0);
				setState(87);
				((UnaryContext)_localctx).expr2 = expression();
				setState(88);
				match(T__1);
				((UnaryContext)_localctx).proj.value.setExpression(((UnaryContext)_localctx).expr2.value); ((UnaryContext)_localctx).value =  ((UnaryContext)_localctx).proj.value;
				}
				break;
			case T__7:
				{
				setState(91);
				((UnaryContext)_localctx).ren = renaming();
				setState(92);
				match(T__0);
				setState(93);
				((UnaryContext)_localctx).expr3 = expression();
				setState(94);
				match(T__1);
				((UnaryContext)_localctx).ren.value.setExpression(((UnaryContext)_localctx).expr3.value); ((UnaryContext)_localctx).value =  ((UnaryContext)_localctx).ren.value;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SelectionContext extends ParserRuleContext {
		public Selection value;
		public AttributeContext v1;
		public DateContext v2;
		public LiteralContext v3;
		public NumberContext v4;
		public ComparisonOperatorContext op;
		public AttributeContext w1;
		public DateContext w2;
		public LiteralContext w3;
		public NumberContext w4;
		public AttributeContext x1;
		public DateContext x2;
		public LiteralContext x3;
		public NumberContext x4;
		public AttributeContext y1;
		public DateContext y2;
		public LiteralContext y3;
		public NumberContext y4;
		public List<ComparisonOperatorContext> comparisonOperator() {
			return getRuleContexts(ComparisonOperatorContext.class);
		}
		public ComparisonOperatorContext comparisonOperator(int i) {
			return getRuleContext(ComparisonOperatorContext.class,i);
		}
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public List<DateContext> date() {
			return getRuleContexts(DateContext.class);
		}
		public DateContext date(int i) {
			return getRuleContext(DateContext.class,i);
		}
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public SelectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterSelection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitSelection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitSelection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectionContext selection() throws RecognitionException {
		SelectionContext _localctx = new SelectionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_selection);

		        ((SelectionContext)_localctx).value =  new Selection();
		        Comparison comp = new Comparison();
		    
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(T__2);
			setState(100);
			match(T__3);
			setState(113);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(101);
				((SelectionContext)_localctx).v1 = attribute();

				            comp.setLeftValue(((SelectionContext)_localctx).v1.value);
				            comp.setLeftValueType(ComparisonValueType.ATTRIBUTE);
				        
				}
				break;
			case 2:
				{
				setState(104);
				((SelectionContext)_localctx).v2 = date();

				            comp.setLeftValue(((SelectionContext)_localctx).v2.value);
				            comp.setLeftValueType(ComparisonValueType.DATE);
				        
				}
				break;
			case 3:
				{
				setState(107);
				((SelectionContext)_localctx).v3 = literal();

				            comp.setLeftValue(((SelectionContext)_localctx).v3.value);
				            comp.setLeftValueType(ComparisonValueType.LITERAL);
				        
				}
				break;
			case 4:
				{
				setState(110);
				((SelectionContext)_localctx).v4 = number();

				            comp.setLeftValue(((SelectionContext)_localctx).v4.value);
				            comp.setLeftValueType(ComparisonValueType.NUMBER);
				        
				}
				break;
			}
			setState(115);
			((SelectionContext)_localctx).op = comparisonOperator();
			comp.setOperator(((SelectionContext)_localctx).op.value);
			setState(129);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(117);
				((SelectionContext)_localctx).w1 = attribute();

				            comp.setRightValue(((SelectionContext)_localctx).w1.value);
				            comp.setRightValueType(ComparisonValueType.ATTRIBUTE);
				        
				}
				break;
			case 2:
				{
				setState(120);
				((SelectionContext)_localctx).w2 = date();

				            comp.setRightValue(((SelectionContext)_localctx).w2.value);
				            comp.setRightValueType(ComparisonValueType.DATE);
				        
				}
				break;
			case 3:
				{
				setState(123);
				((SelectionContext)_localctx).w3 = literal();

				            comp.setRightValue(((SelectionContext)_localctx).w3.value);
				            comp.setRightValueType(ComparisonValueType.LITERAL);
				        
				}
				break;
			case 4:
				{
				setState(126);
				((SelectionContext)_localctx).w4 = number();

				            comp.setRightValue(((SelectionContext)_localctx).w4.value);
				            comp.setRightValueType(ComparisonValueType.NUMBER);
				        
				}
				break;
			}
			_localctx.value.addComparison(comp);
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(132);
				match(T__4);
				comp = new Comparison();
				setState(146);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(134);
					((SelectionContext)_localctx).x1 = attribute();

					                comp.setLeftValue(((SelectionContext)_localctx).x1.value);
					                comp.setLeftValueType(ComparisonValueType.ATTRIBUTE);
					            
					}
					break;
				case 2:
					{
					setState(137);
					((SelectionContext)_localctx).x2 = date();

					                comp.setLeftValue(((SelectionContext)_localctx).x2.value);
					                comp.setLeftValueType(ComparisonValueType.DATE);
					            
					}
					break;
				case 3:
					{
					setState(140);
					((SelectionContext)_localctx).x3 = literal();

					                comp.setLeftValue(((SelectionContext)_localctx).x3.value);
					                comp.setLeftValueType(ComparisonValueType.LITERAL);
					            
					}
					break;
				case 4:
					{
					setState(143);
					((SelectionContext)_localctx).x4 = number();

					                comp.setLeftValue(((SelectionContext)_localctx).x4.value);
					                comp.setLeftValueType(ComparisonValueType.NUMBER);
					            
					}
					break;
				}
				setState(148);
				((SelectionContext)_localctx).op = comparisonOperator();
				comp.setOperator(((SelectionContext)_localctx).op.value);
				setState(162);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					{
					setState(150);
					((SelectionContext)_localctx).y1 = attribute();

					                comp.setRightValue(((SelectionContext)_localctx).y1.value);
					                comp.setRightValueType(ComparisonValueType.ATTRIBUTE);
					            
					}
					break;
				case 2:
					{
					setState(153);
					((SelectionContext)_localctx).y2 = date();

					                comp.setRightValue(((SelectionContext)_localctx).y2.value);
					                comp.setRightValueType(ComparisonValueType.DATE);
					            
					}
					break;
				case 3:
					{
					setState(156);
					((SelectionContext)_localctx).y3 = literal();

					                comp.setRightValue(((SelectionContext)_localctx).y3.value);
					                comp.setRightValueType(ComparisonValueType.LITERAL);
					            
					}
					break;
				case 4:
					{
					setState(159);
					((SelectionContext)_localctx).y4 = number();

					                comp.setRightValue(((SelectionContext)_localctx).y4.value);
					                comp.setRightValueType(ComparisonValueType.NUMBER);
					            
					}
					break;
				}
				_localctx.value.addComparison(comp);
				}
				}
				setState(170);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(171);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProjectionContext extends ParserRuleContext {
		public Projection value;
		public AttributeContext att;
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public ProjectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_projection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterProjection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitProjection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitProjection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProjectionContext projection() throws RecognitionException {
		ProjectionContext _localctx = new ProjectionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_projection);

		        ((ProjectionContext)_localctx).value =  new Projection();
		        String att;
		    
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			match(T__6);
			setState(174);
			match(T__3);
			setState(175);
			((ProjectionContext)_localctx).att = attribute();
			_localctx.value.addProjectionAttribute(((ProjectionContext)_localctx).att.value);
			setState(183);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(177);
				match(T__4);
				setState(178);
				((ProjectionContext)_localctx).att = attribute();
				_localctx.value.addProjectionAttribute(((ProjectionContext)_localctx).att.value);
				}
				}
				setState(185);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(186);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RenamingContext extends ParserRuleContext {
		public Renaming value;
		public AttributeContext att;
		public AttributeContext alias;
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public RenamingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_renaming; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterRenaming(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitRenaming(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitRenaming(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RenamingContext renaming() throws RecognitionException {
		RenamingContext _localctx = new RenamingContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_renaming);

		        ((RenamingContext)_localctx).value =  new Renaming();
		    
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			match(T__7);
			setState(189);
			match(T__3);
			setState(190);
			((RenamingContext)_localctx).att = attribute();
			setState(191);
			match(T__8);
			setState(192);
			((RenamingContext)_localctx).alias = attribute();
			_localctx.value.addAttributeAlias(((RenamingContext)_localctx).att.value, ((RenamingContext)_localctx).alias.value);
			setState(202);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(194);
				match(T__4);
				setState(195);
				((RenamingContext)_localctx).att = attribute();
				setState(196);
				match(T__8);
				setState(197);
				((RenamingContext)_localctx).alias = attribute();
				_localctx.value.addAttributeAlias(((RenamingContext)_localctx).att.value, ((RenamingContext)_localctx).alias.value);
				}
				}
				setState(204);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(205);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BinaryContext extends ParserRuleContext {
		public BinaryOperator value;
		public ExpressionContext leftExp;
		public JoinContext vj;
		public IntersectionContext vi;
		public UnionContext vu;
		public DivisionContext vd;
		public MinusContext vm;
		public CartesianProductContext vc;
		public LeftSemiJoinContext vl;
		public RightSemiJoinContext vr;
		public OuterJoinContext vo;
		public ThetaJoinContext vt;
		public ExpressionContext rightExp;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public JoinContext join() {
			return getRuleContext(JoinContext.class,0);
		}
		public IntersectionContext intersection() {
			return getRuleContext(IntersectionContext.class,0);
		}
		public UnionContext union() {
			return getRuleContext(UnionContext.class,0);
		}
		public DivisionContext division() {
			return getRuleContext(DivisionContext.class,0);
		}
		public MinusContext minus() {
			return getRuleContext(MinusContext.class,0);
		}
		public CartesianProductContext cartesianProduct() {
			return getRuleContext(CartesianProductContext.class,0);
		}
		public LeftSemiJoinContext leftSemiJoin() {
			return getRuleContext(LeftSemiJoinContext.class,0);
		}
		public RightSemiJoinContext rightSemiJoin() {
			return getRuleContext(RightSemiJoinContext.class,0);
		}
		public OuterJoinContext outerJoin() {
			return getRuleContext(OuterJoinContext.class,0);
		}
		public ThetaJoinContext thetaJoin() {
			return getRuleContext(ThetaJoinContext.class,0);
		}
		public BinaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterBinary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitBinary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitBinary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryContext binary() throws RecognitionException {
		BinaryContext _localctx = new BinaryContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_binary);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			match(T__0);
			setState(208);
			((BinaryContext)_localctx).leftExp = expression();
			setState(239);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				{
				setState(209);
				((BinaryContext)_localctx).vj = join();
				((BinaryContext)_localctx).value =  ((BinaryContext)_localctx).vj.value;
				}
				break;
			case T__10:
				{
				setState(212);
				((BinaryContext)_localctx).vi = intersection();
				((BinaryContext)_localctx).value =  ((BinaryContext)_localctx).vi.value;
				}
				break;
			case T__11:
				{
				setState(215);
				((BinaryContext)_localctx).vu = union();
				((BinaryContext)_localctx).value =  ((BinaryContext)_localctx).vu.value;
				}
				break;
			case T__13:
				{
				setState(218);
				((BinaryContext)_localctx).vd = division();
				((BinaryContext)_localctx).value =  ((BinaryContext)_localctx).vd.value;
				}
				break;
			case T__14:
				{
				setState(221);
				((BinaryContext)_localctx).vm = minus();
				((BinaryContext)_localctx).value =  ((BinaryContext)_localctx).vm.value;
				}
				break;
			case T__15:
				{
				setState(224);
				((BinaryContext)_localctx).vc = cartesianProduct();
				((BinaryContext)_localctx).value =  ((BinaryContext)_localctx).vc.value;
				}
				break;
			case T__16:
				{
				setState(227);
				((BinaryContext)_localctx).vl = leftSemiJoin();
				((BinaryContext)_localctx).value =  ((BinaryContext)_localctx).vl.value;
				}
				break;
			case T__17:
				{
				setState(230);
				((BinaryContext)_localctx).vr = rightSemiJoin();
				((BinaryContext)_localctx).value =  ((BinaryContext)_localctx).vr.value;
				}
				break;
			case T__12:
				{
				setState(233);
				((BinaryContext)_localctx).vo = outerJoin();
				((BinaryContext)_localctx).value =  ((BinaryContext)_localctx).vo.value;
				}
				break;
			case T__18:
				{
				setState(236);
				((BinaryContext)_localctx).vt = thetaJoin();
				((BinaryContext)_localctx).value =  ((BinaryContext)_localctx).vt.value;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(241);
			((BinaryContext)_localctx).rightExp = expression();
			setState(242);
			match(T__1);
			_localctx.value.setLeftExpression(((BinaryContext)_localctx).leftExp.value); _localctx.value.setRightExpression(((BinaryContext)_localctx).rightExp.value);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JoinContext extends ParserRuleContext {
		public Join value;
		public JoinContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterJoin(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitJoin(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitJoin(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JoinContext join() throws RecognitionException {
		JoinContext _localctx = new JoinContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_join);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			match(T__9);
			((JoinContext)_localctx).value =  new Join();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IntersectionContext extends ParserRuleContext {
		public Intersection value;
		public IntersectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intersection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterIntersection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitIntersection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitIntersection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntersectionContext intersection() throws RecognitionException {
		IntersectionContext _localctx = new IntersectionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_intersection);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			match(T__10);
			((IntersectionContext)_localctx).value =  new Intersection();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnionContext extends ParserRuleContext {
		public Union value;
		public UnionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_union; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterUnion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitUnion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitUnion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnionContext union() throws RecognitionException {
		UnionContext _localctx = new UnionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_union);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			match(T__11);
			((UnionContext)_localctx).value =  new Union();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OuterJoinContext extends ParserRuleContext {
		public OuterJoin value;
		public OuterJoinContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outerJoin; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterOuterJoin(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitOuterJoin(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitOuterJoin(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OuterJoinContext outerJoin() throws RecognitionException {
		OuterJoinContext _localctx = new OuterJoinContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_outerJoin);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			match(T__12);
			((OuterJoinContext)_localctx).value =  new OuterJoin();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DivisionContext extends ParserRuleContext {
		public Division value;
		public DivisionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_division; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterDivision(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitDivision(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitDivision(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DivisionContext division() throws RecognitionException {
		DivisionContext _localctx = new DivisionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_division);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			match(T__13);
			((DivisionContext)_localctx).value =  new Division();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MinusContext extends ParserRuleContext {
		public Minus value;
		public MinusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_minus; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitMinus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitMinus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MinusContext minus() throws RecognitionException {
		MinusContext _localctx = new MinusContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_minus);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			match(T__14);
			((MinusContext)_localctx).value =  new Minus();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CartesianProductContext extends ParserRuleContext {
		public CartesianProduct value;
		public CartesianProductContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cartesianProduct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterCartesianProduct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitCartesianProduct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitCartesianProduct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CartesianProductContext cartesianProduct() throws RecognitionException {
		CartesianProductContext _localctx = new CartesianProductContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_cartesianProduct);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			match(T__15);
			((CartesianProductContext)_localctx).value =  new CartesianProduct();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LeftSemiJoinContext extends ParserRuleContext {
		public LeftSemiJoin value;
		public LeftSemiJoinContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftSemiJoin; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterLeftSemiJoin(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitLeftSemiJoin(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitLeftSemiJoin(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LeftSemiJoinContext leftSemiJoin() throws RecognitionException {
		LeftSemiJoinContext _localctx = new LeftSemiJoinContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_leftSemiJoin);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			match(T__16);
			((LeftSemiJoinContext)_localctx).value =  new LeftSemiJoin();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RightSemiJoinContext extends ParserRuleContext {
		public RightSemiJoin value;
		public RightSemiJoinContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightSemiJoin; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterRightSemiJoin(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitRightSemiJoin(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitRightSemiJoin(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RightSemiJoinContext rightSemiJoin() throws RecognitionException {
		RightSemiJoinContext _localctx = new RightSemiJoinContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_rightSemiJoin);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			match(T__17);
			((RightSemiJoinContext)_localctx).value =  new RightSemiJoin();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ThetaJoinContext extends ParserRuleContext {
		public ThetaJoin value;
		public AttributeContext val;
		public ComparisonOperatorContext op;
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public List<ComparisonOperatorContext> comparisonOperator() {
			return getRuleContexts(ComparisonOperatorContext.class);
		}
		public ComparisonOperatorContext comparisonOperator(int i) {
			return getRuleContext(ComparisonOperatorContext.class,i);
		}
		public ThetaJoinContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thetaJoin; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterThetaJoin(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitThetaJoin(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitThetaJoin(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ThetaJoinContext thetaJoin() throws RecognitionException {
		ThetaJoinContext _localctx = new ThetaJoinContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_thetaJoin);

		        Comparison comp = new Comparison();
		        ((ThetaJoinContext)_localctx).value =  new ThetaJoin();
		    
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			match(T__18);
			setState(273);
			((ThetaJoinContext)_localctx).val = attribute();

			        comp.setLeftValue(((ThetaJoinContext)_localctx).val.value);
			        comp.setLeftValueType(ComparisonValueType.ATTRIBUTE);
			    
			setState(275);
			((ThetaJoinContext)_localctx).op = comparisonOperator();

			        comp.setOperator(((ThetaJoinContext)_localctx).op.value);
			    
			setState(277);
			((ThetaJoinContext)_localctx).val = attribute();

			        comp.setRightValue(((ThetaJoinContext)_localctx).val.value);
			        comp.setRightValueType(ComparisonValueType.ATTRIBUTE);
			        _localctx.value.addComparison(comp);
			    
			setState(290);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(279);
				match(T__4);
				comp = new Comparison();
				setState(281);
				((ThetaJoinContext)_localctx).val = attribute();

				            comp.setLeftValue(((ThetaJoinContext)_localctx).val.value);
				            comp.setLeftValueType(ComparisonValueType.ATTRIBUTE);
				        
				setState(283);
				((ThetaJoinContext)_localctx).op = comparisonOperator();

				            comp.setOperator(((ThetaJoinContext)_localctx).op.value);
				        
				setState(285);
				((ThetaJoinContext)_localctx).val = attribute();

				            comp.setRightValue(((ThetaJoinContext)_localctx).val.value);
				            comp.setRightValueType(ComparisonValueType.ATTRIBUTE);
				            _localctx.value.addComparison(comp);
				        
				}
				}
				setState(292);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(293);
			match(T__19);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonOperatorContext extends ParserRuleContext {
		public ComparisonOperator value;
		public ComparisonOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterComparisonOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitComparisonOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitComparisonOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonOperatorContext comparisonOperator() throws RecognitionException {
		ComparisonOperatorContext _localctx = new ComparisonOperatorContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_comparisonOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(311);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__20:
				{
				setState(295);
				match(T__20);
				((ComparisonOperatorContext)_localctx).value =  ComparisonOperator.LESS_THAN;
				setState(301);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__21:
					{
					setState(297);
					match(T__21);
					((ComparisonOperatorContext)_localctx).value =  ComparisonOperator.LESS_OR_EQUAL;
					}
					break;
				case T__22:
					{
					setState(299);
					match(T__22);
					((ComparisonOperatorContext)_localctx).value =  ComparisonOperator.NOT_EQUAL;
					}
					break;
				case T__23:
				case STRING:
				case NUMBER:
					break;
				default:
					break;
				}
				}
				break;
			case T__22:
				{
				setState(303);
				match(T__22);
				((ComparisonOperatorContext)_localctx).value =  ComparisonOperator.GREATER_THAN;
				setState(307);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__21) {
					{
					setState(305);
					match(T__21);
					((ComparisonOperatorContext)_localctx).value =  ComparisonOperator.GREATER_OR_EQUAL;
					}
				}

				}
				break;
			case T__21:
				{
				setState(309);
				match(T__21);
				((ComparisonOperatorContext)_localctx).value =  ComparisonOperator.EQUAL;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public String value;
		public Token STRING;
		public Token NUMBER;
		public List<TerminalNode> STRING() { return getTokens(RelationalAlgebraSyntaxParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(RelationalAlgebraSyntaxParser.STRING, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(RelationalAlgebraSyntaxParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(RelationalAlgebraSyntaxParser.NUMBER, i);
		}
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			match(T__23);
			setState(314);
			((LiteralContext)_localctx).STRING = match(STRING);
			((LiteralContext)_localctx).value =  (((LiteralContext)_localctx).STRING!=null?((LiteralContext)_localctx).STRING.getText():null);
			setState(322);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING || _la==NUMBER) {
				{
				setState(320);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case STRING:
					{
					setState(316);
					((LiteralContext)_localctx).STRING = match(STRING);
					((LiteralContext)_localctx).value =  _localctx.value + " " + (((LiteralContext)_localctx).STRING!=null?((LiteralContext)_localctx).STRING.getText():null);
					}
					break;
				case NUMBER:
					{
					setState(318);
					((LiteralContext)_localctx).NUMBER = match(NUMBER);
					((LiteralContext)_localctx).value =  _localctx.value + " " + (((LiteralContext)_localctx).NUMBER!=null?((LiteralContext)_localctx).NUMBER.getText():null);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(324);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(325);
			match(T__23);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DateContext extends ParserRuleContext {
		public String value;
		public DayContext day;
		public MonthContext month;
		public YearContext year;
		public DayContext day() {
			return getRuleContext(DayContext.class,0);
		}
		public MonthContext month() {
			return getRuleContext(MonthContext.class,0);
		}
		public YearContext year() {
			return getRuleContext(YearContext.class,0);
		}
		public DateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_date; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterDate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitDate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitDate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DateContext date() throws RecognitionException {
		DateContext _localctx = new DateContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_date);

		        GregorianCalendar gc = null;
		    
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(327);
			match(T__23);
			setState(328);
			((DateContext)_localctx).day = day();
			setState(329);
			match(T__24);
			setState(330);
			((DateContext)_localctx).month = month();
			setState(331);
			match(T__24);
			setState(332);
			((DateContext)_localctx).year = year();
			setState(333);
			match(T__23);

			        ((DateContext)_localctx).value =  (((DateContext)_localctx).day!=null?_input.getText(((DateContext)_localctx).day.start,((DateContext)_localctx).day.stop):null) + "." + (((DateContext)_localctx).month!=null?_input.getText(((DateContext)_localctx).month.start,((DateContext)_localctx).month.stop):null) + "." + (((DateContext)_localctx).year!=null?_input.getText(((DateContext)_localctx).year.start,((DateContext)_localctx).year.stop):null);

			        // Parse date
			        gc = new GregorianCalendar(Integer.parseInt((((DateContext)_localctx).year!=null?_input.getText(((DateContext)_localctx).year.start,((DateContext)_localctx).year.stop):null)), Integer.parseInt((((DateContext)_localctx).month!=null?_input.getText(((DateContext)_localctx).month.start,((DateContext)_localctx).month.stop):null)) - 1, Integer.parseInt((((DateContext)_localctx).day!=null?_input.getText(((DateContext)_localctx).day.start,((DateContext)_localctx).day.stop):null)));
			        gc.setLenient(false);
			        gc.getTime();
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class YearContext extends ParserRuleContext {
		public String value;
		public Token NUMBER;
		public TerminalNode NUMBER() { return getToken(RelationalAlgebraSyntaxParser.NUMBER, 0); }
		public YearContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_year; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterYear(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitYear(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitYear(this);
			else return visitor.visitChildren(this);
		}
	}

	public final YearContext year() throws RecognitionException {
		YearContext _localctx = new YearContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_year);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(336);
			((YearContext)_localctx).NUMBER = match(NUMBER);
			((YearContext)_localctx).value =  (((YearContext)_localctx).NUMBER!=null?((YearContext)_localctx).NUMBER.getText():null);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MonthContext extends ParserRuleContext {
		public String value;
		public Token NUMBER;
		public TerminalNode NUMBER() { return getToken(RelationalAlgebraSyntaxParser.NUMBER, 0); }
		public MonthContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_month; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterMonth(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitMonth(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitMonth(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MonthContext month() throws RecognitionException {
		MonthContext _localctx = new MonthContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_month);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(339);
			((MonthContext)_localctx).NUMBER = match(NUMBER);
			((MonthContext)_localctx).value =  (((MonthContext)_localctx).NUMBER!=null?((MonthContext)_localctx).NUMBER.getText():null);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DayContext extends ParserRuleContext {
		public String value;
		public Token NUMBER;
		public TerminalNode NUMBER() { return getToken(RelationalAlgebraSyntaxParser.NUMBER, 0); }
		public DayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_day; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterDay(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitDay(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitDay(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DayContext day() throws RecognitionException {
		DayContext _localctx = new DayContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_day);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342);
			((DayContext)_localctx).NUMBER = match(NUMBER);
			((DayContext)_localctx).value =  (((DayContext)_localctx).NUMBER!=null?((DayContext)_localctx).NUMBER.getText():null);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AttributeContext extends ParserRuleContext {
		public String value;
		public Token STRING;
		public TerminalNode STRING() { return getToken(RelationalAlgebraSyntaxParser.STRING, 0); }
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitAttribute(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_attribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345);
			((AttributeContext)_localctx).STRING = match(STRING);
			((AttributeContext)_localctx).value =  (((AttributeContext)_localctx).STRING!=null?((AttributeContext)_localctx).STRING.getText():null);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NameContext extends ParserRuleContext {
		public String value;
		public Token STRING;
		public TerminalNode STRING() { return getToken(RelationalAlgebraSyntaxParser.STRING, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(348);
			((NameContext)_localctx).STRING = match(STRING);
			((NameContext)_localctx).value =  (((NameContext)_localctx).STRING!=null?((NameContext)_localctx).STRING.getText():null);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumberContext extends ParserRuleContext {
		public String value;
		public Token NUMBER;
		public List<TerminalNode> NUMBER() { return getTokens(RelationalAlgebraSyntaxParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(RelationalAlgebraSyntaxParser.NUMBER, i);
		}
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RelationalAlgebraSyntaxListener ) ((RelationalAlgebraSyntaxListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RelationalAlgebraSyntaxVisitor ) return ((RelationalAlgebraSyntaxVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(351);
			((NumberContext)_localctx).NUMBER = match(NUMBER);
			((NumberContext)_localctx).value =  (((NumberContext)_localctx).NUMBER!=null?((NumberContext)_localctx).NUMBER.getText():null);
			setState(356);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__24) {
				{
				setState(353);
				match(T__24);
				setState(354);
				((NumberContext)_localctx).NUMBER = match(NUMBER);
				((NumberContext)_localctx).value =  _localctx.value + "." + (((NumberContext)_localctx).NUMBER!=null?((NumberContext)_localctx).NUMBER.getText():null);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u001c\u0167\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007"+
		"\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007"+
		"\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007"+
		"\u001b\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001C\b"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003N\b\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003"+
		"\u0004b\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005r\b\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0003\u0005\u0082\b\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0003\u0005\u0093\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u00a3"+
		"\b\u0005\u0001\u0005\u0001\u0005\u0005\u0005\u00a7\b\u0005\n\u0005\f\u0005"+
		"\u00aa\t\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006"+
		"\u00b6\b\u0006\n\u0006\f\u0006\u00b9\t\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0005"+
		"\u0007\u00c9\b\u0007\n\u0007\f\u0007\u00cc\t\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u00f0\b\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r"+
		"\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u0121"+
		"\b\u0012\n\u0012\f\u0012\u0124\t\u0012\u0001\u0012\u0001\u0012\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013"+
		"\u012e\b\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013"+
		"\u0134\b\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u0138\b\u0013\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0005\u0014\u0141\b\u0014\n\u0014\f\u0014\u0144\t\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0003\u001b\u0165\b\u001b\u0001\u001b\u0000\u0000\u001c\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \""+
		"$&(*,.0246\u0000\u0000\u016f\u00008\u0001\u0000\u0000\u0000\u0002B\u0001"+
		"\u0000\u0000\u0000\u0004D\u0001\u0000\u0000\u0000\u0006M\u0001\u0000\u0000"+
		"\u0000\ba\u0001\u0000\u0000\u0000\nc\u0001\u0000\u0000\u0000\f\u00ad\u0001"+
		"\u0000\u0000\u0000\u000e\u00bc\u0001\u0000\u0000\u0000\u0010\u00cf\u0001"+
		"\u0000\u0000\u0000\u0012\u00f5\u0001\u0000\u0000\u0000\u0014\u00f8\u0001"+
		"\u0000\u0000\u0000\u0016\u00fb\u0001\u0000\u0000\u0000\u0018\u00fe\u0001"+
		"\u0000\u0000\u0000\u001a\u0101\u0001\u0000\u0000\u0000\u001c\u0104\u0001"+
		"\u0000\u0000\u0000\u001e\u0107\u0001\u0000\u0000\u0000 \u010a\u0001\u0000"+
		"\u0000\u0000\"\u010d\u0001\u0000\u0000\u0000$\u0110\u0001\u0000\u0000"+
		"\u0000&\u0137\u0001\u0000\u0000\u0000(\u0139\u0001\u0000\u0000\u0000*"+
		"\u0147\u0001\u0000\u0000\u0000,\u0150\u0001\u0000\u0000\u0000.\u0153\u0001"+
		"\u0000\u0000\u00000\u0156\u0001\u0000\u0000\u00002\u0159\u0001\u0000\u0000"+
		"\u00004\u015c\u0001\u0000\u0000\u00006\u015f\u0001\u0000\u0000\u00008"+
		"9\u0003\u0002\u0001\u00009:\u0005\u0000\u0000\u0001:;\u0006\u0000\uffff"+
		"\uffff\u0000;\u0001\u0001\u0000\u0000\u0000<=\u0003\u0006\u0003\u0000"+
		"=>\u0006\u0001\uffff\uffff\u0000>C\u0001\u0000\u0000\u0000?@\u0003\u0004"+
		"\u0002\u0000@A\u0006\u0001\uffff\uffff\u0000AC\u0001\u0000\u0000\u0000"+
		"B<\u0001\u0000\u0000\u0000B?\u0001\u0000\u0000\u0000C\u0003\u0001\u0000"+
		"\u0000\u0000DE\u00034\u001a\u0000EF\u0006\u0002\uffff\uffff\u0000F\u0005"+
		"\u0001\u0000\u0000\u0000GH\u0003\b\u0004\u0000HI\u0006\u0003\uffff\uffff"+
		"\u0000IN\u0001\u0000\u0000\u0000JK\u0003\u0010\b\u0000KL\u0006\u0003\uffff"+
		"\uffff\u0000LN\u0001\u0000\u0000\u0000MG\u0001\u0000\u0000\u0000MJ\u0001"+
		"\u0000\u0000\u0000N\u0007\u0001\u0000\u0000\u0000OP\u0003\n\u0005\u0000"+
		"PQ\u0005\u0001\u0000\u0000QR\u0003\u0002\u0001\u0000RS\u0005\u0002\u0000"+
		"\u0000ST\u0006\u0004\uffff\uffff\u0000Tb\u0001\u0000\u0000\u0000UV\u0003"+
		"\f\u0006\u0000VW\u0005\u0001\u0000\u0000WX\u0003\u0002\u0001\u0000XY\u0005"+
		"\u0002\u0000\u0000YZ\u0006\u0004\uffff\uffff\u0000Zb\u0001\u0000\u0000"+
		"\u0000[\\\u0003\u000e\u0007\u0000\\]\u0005\u0001\u0000\u0000]^\u0003\u0002"+
		"\u0001\u0000^_\u0005\u0002\u0000\u0000_`\u0006\u0004\uffff\uffff\u0000"+
		"`b\u0001\u0000\u0000\u0000aO\u0001\u0000\u0000\u0000aU\u0001\u0000\u0000"+
		"\u0000a[\u0001\u0000\u0000\u0000b\t\u0001\u0000\u0000\u0000cd\u0005\u0003"+
		"\u0000\u0000dq\u0005\u0004\u0000\u0000ef\u00032\u0019\u0000fg\u0006\u0005"+
		"\uffff\uffff\u0000gr\u0001\u0000\u0000\u0000hi\u0003*\u0015\u0000ij\u0006"+
		"\u0005\uffff\uffff\u0000jr\u0001\u0000\u0000\u0000kl\u0003(\u0014\u0000"+
		"lm\u0006\u0005\uffff\uffff\u0000mr\u0001\u0000\u0000\u0000no\u00036\u001b"+
		"\u0000op\u0006\u0005\uffff\uffff\u0000pr\u0001\u0000\u0000\u0000qe\u0001"+
		"\u0000\u0000\u0000qh\u0001\u0000\u0000\u0000qk\u0001\u0000\u0000\u0000"+
		"qn\u0001\u0000\u0000\u0000rs\u0001\u0000\u0000\u0000st\u0003&\u0013\u0000"+
		"t\u0081\u0006\u0005\uffff\uffff\u0000uv\u00032\u0019\u0000vw\u0006\u0005"+
		"\uffff\uffff\u0000w\u0082\u0001\u0000\u0000\u0000xy\u0003*\u0015\u0000"+
		"yz\u0006\u0005\uffff\uffff\u0000z\u0082\u0001\u0000\u0000\u0000{|\u0003"+
		"(\u0014\u0000|}\u0006\u0005\uffff\uffff\u0000}\u0082\u0001\u0000\u0000"+
		"\u0000~\u007f\u00036\u001b\u0000\u007f\u0080\u0006\u0005\uffff\uffff\u0000"+
		"\u0080\u0082\u0001\u0000\u0000\u0000\u0081u\u0001\u0000\u0000\u0000\u0081"+
		"x\u0001\u0000\u0000\u0000\u0081{\u0001\u0000\u0000\u0000\u0081~\u0001"+
		"\u0000\u0000\u0000\u0082\u0083\u0001\u0000\u0000\u0000\u0083\u00a8\u0006"+
		"\u0005\uffff\uffff\u0000\u0084\u0085\u0005\u0005\u0000\u0000\u0085\u0092"+
		"\u0006\u0005\uffff\uffff\u0000\u0086\u0087\u00032\u0019\u0000\u0087\u0088"+
		"\u0006\u0005\uffff\uffff\u0000\u0088\u0093\u0001\u0000\u0000\u0000\u0089"+
		"\u008a\u0003*\u0015\u0000\u008a\u008b\u0006\u0005\uffff\uffff\u0000\u008b"+
		"\u0093\u0001\u0000\u0000\u0000\u008c\u008d\u0003(\u0014\u0000\u008d\u008e"+
		"\u0006\u0005\uffff\uffff\u0000\u008e\u0093\u0001\u0000\u0000\u0000\u008f"+
		"\u0090\u00036\u001b\u0000\u0090\u0091\u0006\u0005\uffff\uffff\u0000\u0091"+
		"\u0093\u0001\u0000\u0000\u0000\u0092\u0086\u0001\u0000\u0000\u0000\u0092"+
		"\u0089\u0001\u0000\u0000\u0000\u0092\u008c\u0001\u0000\u0000\u0000\u0092"+
		"\u008f\u0001\u0000\u0000\u0000\u0093\u0094\u0001\u0000\u0000\u0000\u0094"+
		"\u0095\u0003&\u0013\u0000\u0095\u00a2\u0006\u0005\uffff\uffff\u0000\u0096"+
		"\u0097\u00032\u0019\u0000\u0097\u0098\u0006\u0005\uffff\uffff\u0000\u0098"+
		"\u00a3\u0001\u0000\u0000\u0000\u0099\u009a\u0003*\u0015\u0000\u009a\u009b"+
		"\u0006\u0005\uffff\uffff\u0000\u009b\u00a3\u0001\u0000\u0000\u0000\u009c"+
		"\u009d\u0003(\u0014\u0000\u009d\u009e\u0006\u0005\uffff\uffff\u0000\u009e"+
		"\u00a3\u0001\u0000\u0000\u0000\u009f\u00a0\u00036\u001b\u0000\u00a0\u00a1"+
		"\u0006\u0005\uffff\uffff\u0000\u00a1\u00a3\u0001\u0000\u0000\u0000\u00a2"+
		"\u0096\u0001\u0000\u0000\u0000\u00a2\u0099\u0001\u0000\u0000\u0000\u00a2"+
		"\u009c\u0001\u0000\u0000\u0000\u00a2\u009f\u0001\u0000\u0000\u0000\u00a3"+
		"\u00a4\u0001\u0000\u0000\u0000\u00a4\u00a5\u0006\u0005\uffff\uffff\u0000"+
		"\u00a5\u00a7\u0001\u0000\u0000\u0000\u00a6\u0084\u0001\u0000\u0000\u0000"+
		"\u00a7\u00aa\u0001\u0000\u0000\u0000\u00a8\u00a6\u0001\u0000\u0000\u0000"+
		"\u00a8\u00a9\u0001\u0000\u0000\u0000\u00a9\u00ab\u0001\u0000\u0000\u0000"+
		"\u00aa\u00a8\u0001\u0000\u0000\u0000\u00ab\u00ac\u0005\u0006\u0000\u0000"+
		"\u00ac\u000b\u0001\u0000\u0000\u0000\u00ad\u00ae\u0005\u0007\u0000\u0000"+
		"\u00ae\u00af\u0005\u0004\u0000\u0000\u00af\u00b0\u00032\u0019\u0000\u00b0"+
		"\u00b7\u0006\u0006\uffff\uffff\u0000\u00b1\u00b2\u0005\u0005\u0000\u0000"+
		"\u00b2\u00b3\u00032\u0019\u0000\u00b3\u00b4\u0006\u0006\uffff\uffff\u0000"+
		"\u00b4\u00b6\u0001\u0000\u0000\u0000\u00b5\u00b1\u0001\u0000\u0000\u0000"+
		"\u00b6\u00b9\u0001\u0000\u0000\u0000\u00b7\u00b5\u0001\u0000\u0000\u0000"+
		"\u00b7\u00b8\u0001\u0000\u0000\u0000\u00b8\u00ba\u0001\u0000\u0000\u0000"+
		"\u00b9\u00b7\u0001\u0000\u0000\u0000\u00ba\u00bb\u0005\u0006\u0000\u0000"+
		"\u00bb\r\u0001\u0000\u0000\u0000\u00bc\u00bd\u0005\b\u0000\u0000\u00bd"+
		"\u00be\u0005\u0004\u0000\u0000\u00be\u00bf\u00032\u0019\u0000\u00bf\u00c0"+
		"\u0005\t\u0000\u0000\u00c0\u00c1\u00032\u0019\u0000\u00c1\u00ca\u0006"+
		"\u0007\uffff\uffff\u0000\u00c2\u00c3\u0005\u0005\u0000\u0000\u00c3\u00c4"+
		"\u00032\u0019\u0000\u00c4\u00c5\u0005\t\u0000\u0000\u00c5\u00c6\u0003"+
		"2\u0019\u0000\u00c6\u00c7\u0006\u0007\uffff\uffff\u0000\u00c7\u00c9\u0001"+
		"\u0000\u0000\u0000\u00c8\u00c2\u0001\u0000\u0000\u0000\u00c9\u00cc\u0001"+
		"\u0000\u0000\u0000\u00ca\u00c8\u0001\u0000\u0000\u0000\u00ca\u00cb\u0001"+
		"\u0000\u0000\u0000\u00cb\u00cd\u0001\u0000\u0000\u0000\u00cc\u00ca\u0001"+
		"\u0000\u0000\u0000\u00cd\u00ce\u0005\u0006\u0000\u0000\u00ce\u000f\u0001"+
		"\u0000\u0000\u0000\u00cf\u00d0\u0005\u0001\u0000\u0000\u00d0\u00ef\u0003"+
		"\u0002\u0001\u0000\u00d1\u00d2\u0003\u0012\t\u0000\u00d2\u00d3\u0006\b"+
		"\uffff\uffff\u0000\u00d3\u00f0\u0001\u0000\u0000\u0000\u00d4\u00d5\u0003"+
		"\u0014\n\u0000\u00d5\u00d6\u0006\b\uffff\uffff\u0000\u00d6\u00f0\u0001"+
		"\u0000\u0000\u0000\u00d7\u00d8\u0003\u0016\u000b\u0000\u00d8\u00d9\u0006"+
		"\b\uffff\uffff\u0000\u00d9\u00f0\u0001\u0000\u0000\u0000\u00da\u00db\u0003"+
		"\u001a\r\u0000\u00db\u00dc\u0006\b\uffff\uffff\u0000\u00dc\u00f0\u0001"+
		"\u0000\u0000\u0000\u00dd\u00de\u0003\u001c\u000e\u0000\u00de\u00df\u0006"+
		"\b\uffff\uffff\u0000\u00df\u00f0\u0001\u0000\u0000\u0000\u00e0\u00e1\u0003"+
		"\u001e\u000f\u0000\u00e1\u00e2\u0006\b\uffff\uffff\u0000\u00e2\u00f0\u0001"+
		"\u0000\u0000\u0000\u00e3\u00e4\u0003 \u0010\u0000\u00e4\u00e5\u0006\b"+
		"\uffff\uffff\u0000\u00e5\u00f0\u0001\u0000\u0000\u0000\u00e6\u00e7\u0003"+
		"\"\u0011\u0000\u00e7\u00e8\u0006\b\uffff\uffff\u0000\u00e8\u00f0\u0001"+
		"\u0000\u0000\u0000\u00e9\u00ea\u0003\u0018\f\u0000\u00ea\u00eb\u0006\b"+
		"\uffff\uffff\u0000\u00eb\u00f0\u0001\u0000\u0000\u0000\u00ec\u00ed\u0003"+
		"$\u0012\u0000\u00ed\u00ee\u0006\b\uffff\uffff\u0000\u00ee\u00f0\u0001"+
		"\u0000\u0000\u0000\u00ef\u00d1\u0001\u0000\u0000\u0000\u00ef\u00d4\u0001"+
		"\u0000\u0000\u0000\u00ef\u00d7\u0001\u0000\u0000\u0000\u00ef\u00da\u0001"+
		"\u0000\u0000\u0000\u00ef\u00dd\u0001\u0000\u0000\u0000\u00ef\u00e0\u0001"+
		"\u0000\u0000\u0000\u00ef\u00e3\u0001\u0000\u0000\u0000\u00ef\u00e6\u0001"+
		"\u0000\u0000\u0000\u00ef\u00e9\u0001\u0000\u0000\u0000\u00ef\u00ec\u0001"+
		"\u0000\u0000\u0000\u00f0\u00f1\u0001\u0000\u0000\u0000\u00f1\u00f2\u0003"+
		"\u0002\u0001\u0000\u00f2\u00f3\u0005\u0002\u0000\u0000\u00f3\u00f4\u0006"+
		"\b\uffff\uffff\u0000\u00f4\u0011\u0001\u0000\u0000\u0000\u00f5\u00f6\u0005"+
		"\n\u0000\u0000\u00f6\u00f7\u0006\t\uffff\uffff\u0000\u00f7\u0013\u0001"+
		"\u0000\u0000\u0000\u00f8\u00f9\u0005\u000b\u0000\u0000\u00f9\u00fa\u0006"+
		"\n\uffff\uffff\u0000\u00fa\u0015\u0001\u0000\u0000\u0000\u00fb\u00fc\u0005"+
		"\f\u0000\u0000\u00fc\u00fd\u0006\u000b\uffff\uffff\u0000\u00fd\u0017\u0001"+
		"\u0000\u0000\u0000\u00fe\u00ff\u0005\r\u0000\u0000\u00ff\u0100\u0006\f"+
		"\uffff\uffff\u0000\u0100\u0019\u0001\u0000\u0000\u0000\u0101\u0102\u0005"+
		"\u000e\u0000\u0000\u0102\u0103\u0006\r\uffff\uffff\u0000\u0103\u001b\u0001"+
		"\u0000\u0000\u0000\u0104\u0105\u0005\u000f\u0000\u0000\u0105\u0106\u0006"+
		"\u000e\uffff\uffff\u0000\u0106\u001d\u0001\u0000\u0000\u0000\u0107\u0108"+
		"\u0005\u0010\u0000\u0000\u0108\u0109\u0006\u000f\uffff\uffff\u0000\u0109"+
		"\u001f\u0001\u0000\u0000\u0000\u010a\u010b\u0005\u0011\u0000\u0000\u010b"+
		"\u010c\u0006\u0010\uffff\uffff\u0000\u010c!\u0001\u0000\u0000\u0000\u010d"+
		"\u010e\u0005\u0012\u0000\u0000\u010e\u010f\u0006\u0011\uffff\uffff\u0000"+
		"\u010f#\u0001\u0000\u0000\u0000\u0110\u0111\u0005\u0013\u0000\u0000\u0111"+
		"\u0112\u00032\u0019\u0000\u0112\u0113\u0006\u0012\uffff\uffff\u0000\u0113"+
		"\u0114\u0003&\u0013\u0000\u0114\u0115\u0006\u0012\uffff\uffff\u0000\u0115"+
		"\u0116\u00032\u0019\u0000\u0116\u0122\u0006\u0012\uffff\uffff\u0000\u0117"+
		"\u0118\u0005\u0005\u0000\u0000\u0118\u0119\u0006\u0012\uffff\uffff\u0000"+
		"\u0119\u011a\u00032\u0019\u0000\u011a\u011b\u0006\u0012\uffff\uffff\u0000"+
		"\u011b\u011c\u0003&\u0013\u0000\u011c\u011d\u0006\u0012\uffff\uffff\u0000"+
		"\u011d\u011e\u00032\u0019\u0000\u011e\u011f\u0006\u0012\uffff\uffff\u0000"+
		"\u011f\u0121\u0001\u0000\u0000\u0000\u0120\u0117\u0001\u0000\u0000\u0000"+
		"\u0121\u0124\u0001\u0000\u0000\u0000\u0122\u0120\u0001\u0000\u0000\u0000"+
		"\u0122\u0123\u0001\u0000\u0000\u0000\u0123\u0125\u0001\u0000\u0000\u0000"+
		"\u0124\u0122\u0001\u0000\u0000\u0000\u0125\u0126\u0005\u0014\u0000\u0000"+
		"\u0126%\u0001\u0000\u0000\u0000\u0127\u0128\u0005\u0015\u0000\u0000\u0128"+
		"\u012d\u0006\u0013\uffff\uffff\u0000\u0129\u012a\u0005\u0016\u0000\u0000"+
		"\u012a\u012e\u0006\u0013\uffff\uffff\u0000\u012b\u012c\u0005\u0017\u0000"+
		"\u0000\u012c\u012e\u0006\u0013\uffff\uffff\u0000\u012d\u0129\u0001\u0000"+
		"\u0000\u0000\u012d\u012b\u0001\u0000\u0000\u0000\u012d\u012e\u0001\u0000"+
		"\u0000\u0000\u012e\u0138\u0001\u0000\u0000\u0000\u012f\u0130\u0005\u0017"+
		"\u0000\u0000\u0130\u0133\u0006\u0013\uffff\uffff\u0000\u0131\u0132\u0005"+
		"\u0016\u0000\u0000\u0132\u0134\u0006\u0013\uffff\uffff\u0000\u0133\u0131"+
		"\u0001\u0000\u0000\u0000\u0133\u0134\u0001\u0000\u0000\u0000\u0134\u0138"+
		"\u0001\u0000\u0000\u0000\u0135\u0136\u0005\u0016\u0000\u0000\u0136\u0138"+
		"\u0006\u0013\uffff\uffff\u0000\u0137\u0127\u0001\u0000\u0000\u0000\u0137"+
		"\u012f\u0001\u0000\u0000\u0000\u0137\u0135\u0001\u0000\u0000\u0000\u0138"+
		"\'\u0001\u0000\u0000\u0000\u0139\u013a\u0005\u0018\u0000\u0000\u013a\u013b"+
		"\u0005\u001b\u0000\u0000\u013b\u0142\u0006\u0014\uffff\uffff\u0000\u013c"+
		"\u013d\u0005\u001b\u0000\u0000\u013d\u0141\u0006\u0014\uffff\uffff\u0000"+
		"\u013e\u013f\u0005\u001c\u0000\u0000\u013f\u0141\u0006\u0014\uffff\uffff"+
		"\u0000\u0140\u013c\u0001\u0000\u0000\u0000\u0140\u013e\u0001\u0000\u0000"+
		"\u0000\u0141\u0144\u0001\u0000\u0000\u0000\u0142\u0140\u0001\u0000\u0000"+
		"\u0000\u0142\u0143\u0001\u0000\u0000\u0000\u0143\u0145\u0001\u0000\u0000"+
		"\u0000\u0144\u0142\u0001\u0000\u0000\u0000\u0145\u0146\u0005\u0018\u0000"+
		"\u0000\u0146)\u0001\u0000\u0000\u0000\u0147\u0148\u0005\u0018\u0000\u0000"+
		"\u0148\u0149\u00030\u0018\u0000\u0149\u014a\u0005\u0019\u0000\u0000\u014a"+
		"\u014b\u0003.\u0017\u0000\u014b\u014c\u0005\u0019\u0000\u0000\u014c\u014d"+
		"\u0003,\u0016\u0000\u014d\u014e\u0005\u0018\u0000\u0000\u014e\u014f\u0006"+
		"\u0015\uffff\uffff\u0000\u014f+\u0001\u0000\u0000\u0000\u0150\u0151\u0005"+
		"\u001c\u0000\u0000\u0151\u0152\u0006\u0016\uffff\uffff\u0000\u0152-\u0001"+
		"\u0000\u0000\u0000\u0153\u0154\u0005\u001c\u0000\u0000\u0154\u0155\u0006"+
		"\u0017\uffff\uffff\u0000\u0155/\u0001\u0000\u0000\u0000\u0156\u0157\u0005"+
		"\u001c\u0000\u0000\u0157\u0158\u0006\u0018\uffff\uffff\u0000\u01581\u0001"+
		"\u0000\u0000\u0000\u0159\u015a\u0005\u001b\u0000\u0000\u015a\u015b\u0006"+
		"\u0019\uffff\uffff\u0000\u015b3\u0001\u0000\u0000\u0000\u015c\u015d\u0005"+
		"\u001b\u0000\u0000\u015d\u015e\u0006\u001a\uffff\uffff\u0000\u015e5\u0001"+
		"\u0000\u0000\u0000\u015f\u0160\u0005\u001c\u0000\u0000\u0160\u0164\u0006"+
		"\u001b\uffff\uffff\u0000\u0161\u0162\u0005\u0019\u0000\u0000\u0162\u0163"+
		"\u0005\u001c\u0000\u0000\u0163\u0165\u0006\u001b\uffff\uffff\u0000\u0164"+
		"\u0161\u0001\u0000\u0000\u0000\u0164\u0165\u0001\u0000\u0000\u0000\u0165"+
		"7\u0001\u0000\u0000\u0000\u0012BMaq\u0081\u0092\u00a2\u00a8\u00b7\u00ca"+
		"\u00ef\u0122\u012d\u0133\u0137\u0140\u0142\u0164";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}