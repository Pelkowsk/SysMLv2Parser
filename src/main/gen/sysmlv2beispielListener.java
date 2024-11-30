// Generated from /Users/janpelkowski/Documents/TestMitGradle2/src/main/java/sysmlv2beispiel.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link sysmlv2beispielParser}.
 */
public interface sysmlv2beispielListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link sysmlv2beispielParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(sysmlv2beispielParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link sysmlv2beispielParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(sysmlv2beispielParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link sysmlv2beispielParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(sysmlv2beispielParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link sysmlv2beispielParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(sysmlv2beispielParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link sysmlv2beispielParser#additiveOperator}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveOperator(sysmlv2beispielParser.AdditiveOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link sysmlv2beispielParser#additiveOperator}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveOperator(sysmlv2beispielParser.AdditiveOperatorContext ctx);
}