// Generated from D:/idea/jthornruleGrammer/QuickRest/JQuickCurl.g4 by ANTLR 4.13.2
package com.github.paohaijiao.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JQuickCurlParser}.
 */
public interface JQuickCurlListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JQuickCurlParser#curlCommand}.
	 * @param ctx the parse tree
	 */
	void enterCurlCommand(JQuickCurlParser.CurlCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickCurlParser#curlCommand}.
	 * @param ctx the parse tree
	 */
	void exitCurlCommand(JQuickCurlParser.CurlCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickCurlParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(JQuickCurlParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickCurlParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(JQuickCurlParser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickCurlParser#requestMethod}.
	 * @param ctx the parse tree
	 */
	void enterRequestMethod(JQuickCurlParser.RequestMethodContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickCurlParser#requestMethod}.
	 * @param ctx the parse tree
	 */
	void exitRequestMethod(JQuickCurlParser.RequestMethodContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickCurlParser#headerOption}.
	 * @param ctx the parse tree
	 */
	void enterHeaderOption(JQuickCurlParser.HeaderOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickCurlParser#headerOption}.
	 * @param ctx the parse tree
	 */
	void exitHeaderOption(JQuickCurlParser.HeaderOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickCurlParser#proxryOption}.
	 * @param ctx the parse tree
	 */
	void enterProxryOption(JQuickCurlParser.ProxryOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickCurlParser#proxryOption}.
	 * @param ctx the parse tree
	 */
	void exitProxryOption(JQuickCurlParser.ProxryOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickCurlParser#socketOption}.
	 * @param ctx the parse tree
	 */
	void enterSocketOption(JQuickCurlParser.SocketOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickCurlParser#socketOption}.
	 * @param ctx the parse tree
	 */
	void exitSocketOption(JQuickCurlParser.SocketOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickCurlParser#http2Option}.
	 * @param ctx the parse tree
	 */
	void enterHttp2Option(JQuickCurlParser.Http2OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickCurlParser#http2Option}.
	 * @param ctx the parse tree
	 */
	void exitHttp2Option(JQuickCurlParser.Http2OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickCurlParser#ignoreOption}.
	 * @param ctx the parse tree
	 */
	void enterIgnoreOption(JQuickCurlParser.IgnoreOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickCurlParser#ignoreOption}.
	 * @param ctx the parse tree
	 */
	void exitIgnoreOption(JQuickCurlParser.IgnoreOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickCurlParser#dataOption}.
	 * @param ctx the parse tree
	 */
	void enterDataOption(JQuickCurlParser.DataOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickCurlParser#dataOption}.
	 * @param ctx the parse tree
	 */
	void exitDataOption(JQuickCurlParser.DataOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickCurlParser#dataUrlEncodeOption}.
	 * @param ctx the parse tree
	 */
	void enterDataUrlEncodeOption(JQuickCurlParser.DataUrlEncodeOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickCurlParser#dataUrlEncodeOption}.
	 * @param ctx the parse tree
	 */
	void exitDataUrlEncodeOption(JQuickCurlParser.DataUrlEncodeOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickCurlParser#emptyData}.
	 * @param ctx the parse tree
	 */
	void enterEmptyData(JQuickCurlParser.EmptyDataContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickCurlParser#emptyData}.
	 * @param ctx the parse tree
	 */
	void exitEmptyData(JQuickCurlParser.EmptyDataContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickCurlParser#formData}.
	 * @param ctx the parse tree
	 */
	void enterFormData(JQuickCurlParser.FormDataContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickCurlParser#formData}.
	 * @param ctx the parse tree
	 */
	void exitFormData(JQuickCurlParser.FormDataContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickCurlParser#userOption}.
	 * @param ctx the parse tree
	 */
	void enterUserOption(JQuickCurlParser.UserOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickCurlParser#userOption}.
	 * @param ctx the parse tree
	 */
	void exitUserOption(JQuickCurlParser.UserOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickCurlParser#credentials}.
	 * @param ctx the parse tree
	 */
	void enterCredentials(JQuickCurlParser.CredentialsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickCurlParser#credentials}.
	 * @param ctx the parse tree
	 */
	void exitCredentials(JQuickCurlParser.CredentialsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickCurlParser#locationOption}.
	 * @param ctx the parse tree
	 */
	void enterLocationOption(JQuickCurlParser.LocationOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickCurlParser#locationOption}.
	 * @param ctx the parse tree
	 */
	void exitLocationOption(JQuickCurlParser.LocationOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickCurlParser#loption}.
	 * @param ctx the parse tree
	 */
	void enterLoption(JQuickCurlParser.LoptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickCurlParser#loption}.
	 * @param ctx the parse tree
	 */
	void exitLoption(JQuickCurlParser.LoptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickCurlParser#otherOption}.
	 * @param ctx the parse tree
	 */
	void enterOtherOption(JQuickCurlParser.OtherOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickCurlParser#otherOption}.
	 * @param ctx the parse tree
	 */
	void exitOtherOption(JQuickCurlParser.OtherOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickCurlParser#downloadOption}.
	 * @param ctx the parse tree
	 */
	void enterDownloadOption(JQuickCurlParser.DownloadOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickCurlParser#downloadOption}.
	 * @param ctx the parse tree
	 */
	void exitDownloadOption(JQuickCurlParser.DownloadOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickCurlParser#uploadOption}.
	 * @param ctx the parse tree
	 */
	void enterUploadOption(JQuickCurlParser.UploadOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickCurlParser#uploadOption}.
	 * @param ctx the parse tree
	 */
	void exitUploadOption(JQuickCurlParser.UploadOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickCurlParser#url}.
	 * @param ctx the parse tree
	 */
	void enterUrl(JQuickCurlParser.UrlContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickCurlParser#url}.
	 * @param ctx the parse tree
	 */
	void exitUrl(JQuickCurlParser.UrlContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickCurlParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(JQuickCurlParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickCurlParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(JQuickCurlParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link JQuickCurlParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(JQuickCurlParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link JQuickCurlParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(JQuickCurlParser.VariableContext ctx);
}