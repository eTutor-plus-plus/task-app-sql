// Generated from /Users/martin/Development/dke/etutor_neu/task-app-sql/src/main/antlr4/RelationalAlgebraSyntax.g4 by ANTLR 4.13.1

    package at.jku.dke.task_app.sql.ra2sql.parser;

    import java.util.GregorianCalendar;
    import at.jku.dke.task_app.sql.ra2sql.model.*;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RelationalAlgebraSyntaxParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RelationalAlgebraSyntaxVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#root}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoot(RelationalAlgebraSyntaxParser.RootContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(RelationalAlgebraSyntaxParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#relation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelation(RelationalAlgebraSyntaxParser.RelationContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(RelationalAlgebraSyntaxParser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#unary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary(RelationalAlgebraSyntaxParser.UnaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#selection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelection(RelationalAlgebraSyntaxParser.SelectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#projection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProjection(RelationalAlgebraSyntaxParser.ProjectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#renaming}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRenaming(RelationalAlgebraSyntaxParser.RenamingContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#binary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary(RelationalAlgebraSyntaxParser.BinaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#join}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoin(RelationalAlgebraSyntaxParser.JoinContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#intersection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntersection(RelationalAlgebraSyntaxParser.IntersectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#union}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnion(RelationalAlgebraSyntaxParser.UnionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#outerJoin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOuterJoin(RelationalAlgebraSyntaxParser.OuterJoinContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#division}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivision(RelationalAlgebraSyntaxParser.DivisionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#minus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinus(RelationalAlgebraSyntaxParser.MinusContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#cartesianProduct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCartesianProduct(RelationalAlgebraSyntaxParser.CartesianProductContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#leftSemiJoin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeftSemiJoin(RelationalAlgebraSyntaxParser.LeftSemiJoinContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#rightSemiJoin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRightSemiJoin(RelationalAlgebraSyntaxParser.RightSemiJoinContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#thetaJoin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThetaJoin(RelationalAlgebraSyntaxParser.ThetaJoinContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonOperator(RelationalAlgebraSyntaxParser.ComparisonOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(RelationalAlgebraSyntaxParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#date}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDate(RelationalAlgebraSyntaxParser.DateContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#year}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYear(RelationalAlgebraSyntaxParser.YearContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#month}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMonth(RelationalAlgebraSyntaxParser.MonthContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#day}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDay(RelationalAlgebraSyntaxParser.DayContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribute(RelationalAlgebraSyntaxParser.AttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(RelationalAlgebraSyntaxParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link RelationalAlgebraSyntaxParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(RelationalAlgebraSyntaxParser.NumberContext ctx);
}