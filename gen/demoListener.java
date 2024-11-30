// Generated from /Users/janpelkowski/Documents/TestMitGradle2/src/grammar/demo.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link demoParser}.
 */
public interface demoListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link demoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(demoParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link demoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(demoParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link demoParser#conditionalExpr}.
	 * @param ctx the parse tree
	 */
	void enterConditionalExpr(demoParser.ConditionalExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link demoParser#conditionalExpr}.
	 * @param ctx the parse tree
	 */
	void exitConditionalExpr(demoParser.ConditionalExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link demoParser#nullCoalescingExpr}.
	 * @param ctx the parse tree
	 */
	void enterNullCoalescingExpr(demoParser.NullCoalescingExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link demoParser#nullCoalescingExpr}.
	 * @param ctx the parse tree
	 */
	void exitNullCoalescingExpr(demoParser.NullCoalescingExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link demoParser#ownedExprRef}.
	 * @param ctx the parse tree
	 */
	void enterOwnedExprRef(demoParser.OwnedExprRefContext ctx);
	/**
	 * Exit a parse tree produced by {@link demoParser#ownedExprRef}.
	 * @param ctx the parse tree
	 */
	void exitOwnedExprRef(demoParser.OwnedExprRefContext ctx);
	/**
	 * Enter a parse tree produced by {@link demoParser#impliesExpr}.
	 * @param ctx the parse tree
	 */
	void enterImpliesExpr(demoParser.ImpliesExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link demoParser#impliesExpr}.
	 * @param ctx the parse tree
	 */
	void exitImpliesExpr(demoParser.ImpliesExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link demoParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(demoParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link demoParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(demoParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link demoParser#xorExpr}.
	 * @param ctx the parse tree
	 */
	void enterXorExpr(demoParser.XorExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link demoParser#xorExpr}.
	 * @param ctx the parse tree
	 */
	void exitXorExpr(demoParser.XorExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link demoParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(demoParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link demoParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(demoParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link demoParser#equalityExpr}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpr(demoParser.EqualityExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link demoParser#equalityExpr}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpr(demoParser.EqualityExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link demoParser#relationalExpr}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpr(demoParser.RelationalExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link demoParser#relationalExpr}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpr(demoParser.RelationalExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link demoParser#additiveExpr}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpr(demoParser.AdditiveExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link demoParser#additiveExpr}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpr(demoParser.AdditiveExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link demoParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpr(demoParser.MultiplicativeExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link demoParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpr(demoParser.MultiplicativeExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link demoParser#primaryExpr}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpr(demoParser.PrimaryExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link demoParser#primaryExpr}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpr(demoParser.PrimaryExprContext ctx);
}