// Generated from D:/idea/jthornruleGrammer/QuickRest/JQuickCurl.g4 by ANTLR 4.13.2
package com.github.paohaijiao.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JQuickCurlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JQuickCurlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JQuickCurlParser#curlCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurlCommand(JQuickCurlParser.CurlCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickCurlParser#option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption(JQuickCurlParser.OptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickCurlParser#requestMethod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequestMethod(JQuickCurlParser.RequestMethodContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickCurlParser#headerOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeaderOption(JQuickCurlParser.HeaderOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickCurlParser#proxryOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProxryOption(JQuickCurlParser.ProxryOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickCurlParser#socketOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSocketOption(JQuickCurlParser.SocketOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickCurlParser#http2Option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHttp2Option(JQuickCurlParser.Http2OptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickCurlParser#ignoreOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIgnoreOption(JQuickCurlParser.IgnoreOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickCurlParser#dataOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataOption(JQuickCurlParser.DataOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickCurlParser#dataUrlEncodeOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataUrlEncodeOption(JQuickCurlParser.DataUrlEncodeOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickCurlParser#emptyData}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyData(JQuickCurlParser.EmptyDataContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickCurlParser#formData}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormData(JQuickCurlParser.FormDataContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickCurlParser#userOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserOption(JQuickCurlParser.UserOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickCurlParser#credentials}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCredentials(JQuickCurlParser.CredentialsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickCurlParser#locationOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocationOption(JQuickCurlParser.LocationOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickCurlParser#loption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoption(JQuickCurlParser.LoptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickCurlParser#otherOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOtherOption(JQuickCurlParser.OtherOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickCurlParser#downloadOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDownloadOption(JQuickCurlParser.DownloadOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickCurlParser#uploadOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUploadOption(JQuickCurlParser.UploadOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickCurlParser#url}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUrl(JQuickCurlParser.UrlContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickCurlParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(JQuickCurlParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickCurlParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(JQuickCurlParser.VariableContext ctx);
}