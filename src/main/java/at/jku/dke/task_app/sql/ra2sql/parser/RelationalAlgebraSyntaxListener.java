// Generated from /Users/martin/Development/dke/etutor_neu/task-app-sql/src/main/antlr4/RelationalAlgebraSyntax.g4 by ANTLR 4.13.1

    package at.jku.dke.task_app.sql.ra2sql.parser;

    import java.util.GregorianCalendar;
    import at.jku.dke.task_app.sql.ra2sql.model.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RelationalAlgebraSyntaxParser}.
 */
public interface RelationalAlgebraSyntaxListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(RelationalAlgebraSyntaxParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(RelationalAlgebraSyntaxParser.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(RelationalAlgebraSyntaxParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(RelationalAlgebraSyntaxParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#relation}.
	 * @param ctx the parse tree
	 */
	void enterRelation(RelationalAlgebraSyntaxParser.RelationContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#relation}.
	 * @param ctx the parse tree
	 */
	void exitRelation(RelationalAlgebraSyntaxParser.RelationContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(RelationalAlgebraSyntaxParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(RelationalAlgebraSyntaxParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#unary}.
	 * @param ctx the parse tree
	 */
	void enterUnary(RelationalAlgebraSyntaxParser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#unary}.
	 * @param ctx the parse tree
	 */
	void exitUnary(RelationalAlgebraSyntaxParser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#selection}.
	 * @param ctx the parse tree
	 */
	void enterSelection(RelationalAlgebraSyntaxParser.SelectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#selection}.
	 * @param ctx the parse tree
	 */
	void exitSelection(RelationalAlgebraSyntaxParser.SelectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#projection}.
	 * @param ctx the parse tree
	 */
	void enterProjection(RelationalAlgebraSyntaxParser.ProjectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#projection}.
	 * @param ctx the parse tree
	 */
	void exitProjection(RelationalAlgebraSyntaxParser.ProjectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#renaming}.
	 * @param ctx the parse tree
	 */
	void enterRenaming(RelationalAlgebraSyntaxParser.RenamingContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#renaming}.
	 * @param ctx the parse tree
	 */
	void exitRenaming(RelationalAlgebraSyntaxParser.RenamingContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#binary}.
	 * @param ctx the parse tree
	 */
	void enterBinary(RelationalAlgebraSyntaxParser.BinaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#binary}.
	 * @param ctx the parse tree
	 */
	void exitBinary(RelationalAlgebraSyntaxParser.BinaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#join}.
	 * @param ctx the parse tree
	 */
	void enterJoin(RelationalAlgebraSyntaxParser.JoinContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#join}.
	 * @param ctx the parse tree
	 */
	void exitJoin(RelationalAlgebraSyntaxParser.JoinContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#intersection}.
	 * @param ctx the parse tree
	 */
	void enterIntersection(RelationalAlgebraSyntaxParser.IntersectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#intersection}.
	 * @param ctx the parse tree
	 */
	void exitIntersection(RelationalAlgebraSyntaxParser.IntersectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#union}.
	 * @param ctx the parse tree
	 */
	void enterUnion(RelationalAlgebraSyntaxParser.UnionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#union}.
	 * @param ctx the parse tree
	 */
	void exitUnion(RelationalAlgebraSyntaxParser.UnionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#outerJoin}.
	 * @param ctx the parse tree
	 */
	void enterOuterJoin(RelationalAlgebraSyntaxParser.OuterJoinContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#outerJoin}.
	 * @param ctx the parse tree
	 */
	void exitOuterJoin(RelationalAlgebraSyntaxParser.OuterJoinContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#division}.
	 * @param ctx the parse tree
	 */
	void enterDivision(RelationalAlgebraSyntaxParser.DivisionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#division}.
	 * @param ctx the parse tree
	 */
	void exitDivision(RelationalAlgebraSyntaxParser.DivisionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#minus}.
	 * @param ctx the parse tree
	 */
	void enterMinus(RelationalAlgebraSyntaxParser.MinusContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#minus}.
	 * @param ctx the parse tree
	 */
	void exitMinus(RelationalAlgebraSyntaxParser.MinusContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#cartesianProduct}.
	 * @param ctx the parse tree
	 */
	void enterCartesianProduct(RelationalAlgebraSyntaxParser.CartesianProductContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#cartesianProduct}.
	 * @param ctx the parse tree
	 */
	void exitCartesianProduct(RelationalAlgebraSyntaxParser.CartesianProductContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#leftSemiJoin}.
	 * @param ctx the parse tree
	 */
	void enterLeftSemiJoin(RelationalAlgebraSyntaxParser.LeftSemiJoinContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#leftSemiJoin}.
	 * @param ctx the parse tree
	 */
	void exitLeftSemiJoin(RelationalAlgebraSyntaxParser.LeftSemiJoinContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#rightSemiJoin}.
	 * @param ctx the parse tree
	 */
	void enterRightSemiJoin(RelationalAlgebraSyntaxParser.RightSemiJoinContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#rightSemiJoin}.
	 * @param ctx the parse tree
	 */
	void exitRightSemiJoin(RelationalAlgebraSyntaxParser.RightSemiJoinContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#thetaJoin}.
	 * @param ctx the parse tree
	 */
	void enterThetaJoin(RelationalAlgebraSyntaxParser.ThetaJoinContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#thetaJoin}.
	 * @param ctx the parse tree
	 */
	void exitThetaJoin(RelationalAlgebraSyntaxParser.ThetaJoinContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(RelationalAlgebraSyntaxParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(RelationalAlgebraSyntaxParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(RelationalAlgebraSyntaxParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(RelationalAlgebraSyntaxParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#date}.
	 * @param ctx the parse tree
	 */
	void enterDate(RelationalAlgebraSyntaxParser.DateContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#date}.
	 * @param ctx the parse tree
	 */
	void exitDate(RelationalAlgebraSyntaxParser.DateContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#year}.
	 * @param ctx the parse tree
	 */
	void enterYear(RelationalAlgebraSyntaxParser.YearContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#year}.
	 * @param ctx the parse tree
	 */
	void exitYear(RelationalAlgebraSyntaxParser.YearContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#month}.
	 * @param ctx the parse tree
	 */
	void enterMonth(RelationalAlgebraSyntaxParser.MonthContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#month}.
	 * @param ctx the parse tree
	 */
	void exitMonth(RelationalAlgebraSyntaxParser.MonthContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#day}.
	 * @param ctx the parse tree
	 */
	void enterDay(RelationalAlgebraSyntaxParser.DayContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#day}.
	 * @param ctx the parse tree
	 */
	void exitDay(RelationalAlgebraSyntaxParser.DayContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(RelationalAlgebraSyntaxParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(RelationalAlgebraSyntaxParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(RelationalAlgebraSyntaxParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(RelationalAlgebraSyntaxParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link RelationalAlgebraSyntaxParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(RelationalAlgebraSyntaxParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link RelationalAlgebraSyntaxParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(RelationalAlgebraSyntaxParser.NumberContext ctx);
}