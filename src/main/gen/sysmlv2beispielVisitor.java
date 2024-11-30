// Generated from /Users/janpelkowski/Documents/TestMitGradle2/src/main/java/sysmlv2beispiel.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link sysmlv2beispielParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface sysmlv2beispielVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link sysmlv2beispielParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(sysmlv2beispielParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link sysmlv2beispielParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(sysmlv2beispielParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link sysmlv2beispielParser#additiveOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveOperator(sysmlv2beispielParser.AdditiveOperatorContext ctx);
}