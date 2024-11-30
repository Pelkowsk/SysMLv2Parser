// Generated from /Users/janpelkowski/Documents/TestMitGradle2/src/grammar/demo.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link demoParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface demoVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link demoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(demoParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link demoParser#conditionalExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalExpr(demoParser.ConditionalExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link demoParser#nullCoalescingExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullCoalescingExpr(demoParser.NullCoalescingExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link demoParser#ownedExprRef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOwnedExprRef(demoParser.OwnedExprRefContext ctx);
	/**
	 * Visit a parse tree produced by {@link demoParser#impliesExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImpliesExpr(demoParser.ImpliesExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link demoParser#orExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(demoParser.OrExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link demoParser#xorExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXorExpr(demoParser.XorExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link demoParser#andExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(demoParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link demoParser#equalityExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpr(demoParser.EqualityExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link demoParser#relationalExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpr(demoParser.RelationalExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link demoParser#additiveExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpr(demoParser.AdditiveExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link demoParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpr(demoParser.MultiplicativeExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link demoParser#primaryExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpr(demoParser.PrimaryExprContext ctx);
}