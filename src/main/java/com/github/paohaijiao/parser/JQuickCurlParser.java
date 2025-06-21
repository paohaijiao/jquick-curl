// Generated from D:/idea/jthornruleGrammer/QuickLang/JQuickCurl.g4 by ANTLR 4.13.2
package com.github.paohaijiao.parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class JQuickCurlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, HTTP_METHOD=35, URL=36, IDENTIFIER=37, STRING=38, 
		WS=39, LINE_CONTINUATION=40, UNKNOWN=41;
	public static final int
		RULE_curlCommand = 0, RULE_option = 1, RULE_requestMethod = 2, RULE_headerOption = 3, 
		RULE_proxryOption = 4, RULE_socketOption = 5, RULE_http2Option = 6, RULE_headOption = 7, 
		RULE_ignoreOption = 8, RULE_dataOption = 9, RULE_dataUrlEncodeOption = 10, 
		RULE_emptyData = 11, RULE_formData = 12, RULE_userOption = 13, RULE_locationOption = 14, 
		RULE_loption = 15, RULE_otherOption = 16, RULE_downloadOption = 17, RULE_uploadOption = 18, 
		RULE_url = 19, RULE_string = 20, RULE_variable = 21;
	private static String[] makeRuleNames() {
		return new String[] {
			"curlCommand", "option", "requestMethod", "headerOption", "proxryOption", 
			"socketOption", "http2Option", "headOption", "ignoreOption", "dataOption", 
			"dataUrlEncodeOption", "emptyData", "formData", "userOption", "locationOption", 
			"loption", "otherOption", "downloadOption", "uploadOption", "url", "string", 
			"variable"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'curl'", "'-X'", "'--request'", "'-H'", "'--header'", "'-x'", 
			"'--proxy'", "'--socks5-hostname'", "'--http2'", "'-I'", "'--head'", 
			"'-k'", "'-d'", "'--data'", "'--data-ascii'", "'--data-binary'", "'--data-raw'", 
			"'--data-urlencode'", "'-u'", "'--user'", "'-L'", "'--location'", "'--max-redirs'", 
			"'-v'", "'--verbose'", "'-s'", "'--silent'", "'--insecure'", "'-o'", 
			"'--output'", "'-F'", "'--form'", "'${'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, "HTTP_METHOD", 
			"URL", "IDENTIFIER", "STRING", "WS", "LINE_CONTINUATION", "UNKNOWN"
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
	public String getGrammarFileName() { return "JQuickCurl.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public JQuickCurlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CurlCommandContext extends ParserRuleContext {
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public List<UrlContext> url() {
			return getRuleContexts(UrlContext.class);
		}
		public UrlContext url(int i) {
			return getRuleContext(UrlContext.class,i);
		}
		public CurlCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_curlCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).enterCurlCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).exitCurlCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickCurlVisitor ) return ((JQuickCurlVisitor<? extends T>)visitor).visitCurlCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CurlCommandContext curlCommand() throws RecognitionException {
		CurlCommandContext _localctx = new CurlCommandContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_curlCommand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(T__0);
			setState(47); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(47);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__1:
				case T__2:
				case T__3:
				case T__4:
				case T__5:
				case T__6:
				case T__7:
				case T__8:
				case T__9:
				case T__10:
				case T__11:
				case T__12:
				case T__13:
				case T__14:
				case T__15:
				case T__16:
				case T__17:
				case T__18:
				case T__19:
				case T__20:
				case T__21:
				case T__23:
				case T__24:
				case T__25:
				case T__26:
				case T__27:
				case T__28:
				case T__29:
				case T__30:
				case T__31:
					{
					setState(45);
					option();
					}
					break;
				case T__32:
				case URL:
				case STRING:
					{
					setState(46);
					url();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(49); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 360768864252L) != 0) );
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
	public static class OptionContext extends ParserRuleContext {
		public RequestMethodContext requestMethod() {
			return getRuleContext(RequestMethodContext.class,0);
		}
		public HeaderOptionContext headerOption() {
			return getRuleContext(HeaderOptionContext.class,0);
		}
		public DataOptionContext dataOption() {
			return getRuleContext(DataOptionContext.class,0);
		}
		public DataUrlEncodeOptionContext dataUrlEncodeOption() {
			return getRuleContext(DataUrlEncodeOptionContext.class,0);
		}
		public UserOptionContext userOption() {
			return getRuleContext(UserOptionContext.class,0);
		}
		public LocationOptionContext locationOption() {
			return getRuleContext(LocationOptionContext.class,0);
		}
		public OtherOptionContext otherOption() {
			return getRuleContext(OtherOptionContext.class,0);
		}
		public DownloadOptionContext downloadOption() {
			return getRuleContext(DownloadOptionContext.class,0);
		}
		public UploadOptionContext uploadOption() {
			return getRuleContext(UploadOptionContext.class,0);
		}
		public ProxryOptionContext proxryOption() {
			return getRuleContext(ProxryOptionContext.class,0);
		}
		public SocketOptionContext socketOption() {
			return getRuleContext(SocketOptionContext.class,0);
		}
		public Http2OptionContext http2Option() {
			return getRuleContext(Http2OptionContext.class,0);
		}
		public IgnoreOptionContext ignoreOption() {
			return getRuleContext(IgnoreOptionContext.class,0);
		}
		public HeadOptionContext headOption() {
			return getRuleContext(HeadOptionContext.class,0);
		}
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).exitOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickCurlVisitor ) return ((JQuickCurlVisitor<? extends T>)visitor).visitOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_option);
		try {
			setState(65);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(51);
				requestMethod();
				}
				break;
			case T__3:
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(52);
				headerOption();
				}
				break;
			case T__12:
			case T__13:
			case T__14:
			case T__15:
			case T__16:
				enterOuterAlt(_localctx, 3);
				{
				setState(53);
				dataOption();
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 4);
				{
				setState(54);
				dataUrlEncodeOption();
				}
				break;
			case T__18:
			case T__19:
				enterOuterAlt(_localctx, 5);
				{
				setState(55);
				userOption();
				}
				break;
			case T__20:
			case T__21:
				enterOuterAlt(_localctx, 6);
				{
				setState(56);
				locationOption();
				}
				break;
			case T__23:
			case T__24:
			case T__25:
			case T__26:
			case T__27:
				enterOuterAlt(_localctx, 7);
				{
				setState(57);
				otherOption();
				}
				break;
			case T__28:
			case T__29:
				enterOuterAlt(_localctx, 8);
				{
				setState(58);
				downloadOption();
				}
				break;
			case T__30:
			case T__31:
				enterOuterAlt(_localctx, 9);
				{
				setState(59);
				uploadOption();
				}
				break;
			case T__5:
			case T__6:
				enterOuterAlt(_localctx, 10);
				{
				setState(60);
				proxryOption();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 11);
				{
				setState(61);
				socketOption();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 12);
				{
				setState(62);
				http2Option();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 13);
				{
				setState(63);
				ignoreOption();
				}
				break;
			case T__9:
			case T__10:
				enterOuterAlt(_localctx, 14);
				{
				setState(64);
				headOption();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class RequestMethodContext extends ParserRuleContext {
		public Token method;
		public TerminalNode HTTP_METHOD() { return getToken(JQuickCurlParser.HTTP_METHOD, 0); }
		public RequestMethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_requestMethod; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).enterRequestMethod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).exitRequestMethod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickCurlVisitor ) return ((JQuickCurlVisitor<? extends T>)visitor).visitRequestMethod(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RequestMethodContext requestMethod() throws RecognitionException {
		RequestMethodContext _localctx = new RequestMethodContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_requestMethod);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			_la = _input.LA(1);
			if ( !(_la==T__1 || _la==T__2) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(68);
			((RequestMethodContext)_localctx).method = match(HTTP_METHOD);
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
	public static class HeaderOptionContext extends ParserRuleContext {
		public StringContext headerValue;
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public HeaderOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_headerOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).enterHeaderOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).exitHeaderOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickCurlVisitor ) return ((JQuickCurlVisitor<? extends T>)visitor).visitHeaderOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeaderOptionContext headerOption() throws RecognitionException {
		HeaderOptionContext _localctx = new HeaderOptionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_headerOption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			_la = _input.LA(1);
			if ( !(_la==T__3 || _la==T__4) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(71);
			((HeaderOptionContext)_localctx).headerValue = string();
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
	public static class ProxryOptionContext extends ParserRuleContext {
		public StringContext proxy;
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public ProxryOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proxryOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).enterProxryOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).exitProxryOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickCurlVisitor ) return ((JQuickCurlVisitor<? extends T>)visitor).visitProxryOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProxryOptionContext proxryOption() throws RecognitionException {
		ProxryOptionContext _localctx = new ProxryOptionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_proxryOption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			_la = _input.LA(1);
			if ( !(_la==T__5 || _la==T__6) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(74);
			((ProxryOptionContext)_localctx).proxy = string();
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
	public static class SocketOptionContext extends ParserRuleContext {
		public StringContext proxy;
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public SocketOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_socketOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).enterSocketOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).exitSocketOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickCurlVisitor ) return ((JQuickCurlVisitor<? extends T>)visitor).visitSocketOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SocketOptionContext socketOption() throws RecognitionException {
		SocketOptionContext _localctx = new SocketOptionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_socketOption);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(76);
			match(T__7);
			}
			setState(77);
			((SocketOptionContext)_localctx).proxy = string();
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
	public static class Http2OptionContext extends ParserRuleContext {
		public Http2OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_http2Option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).enterHttp2Option(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).exitHttp2Option(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickCurlVisitor ) return ((JQuickCurlVisitor<? extends T>)visitor).visitHttp2Option(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Http2OptionContext http2Option() throws RecognitionException {
		Http2OptionContext _localctx = new Http2OptionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_http2Option);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(79);
			match(T__8);
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
	public static class HeadOptionContext extends ParserRuleContext {
		public HeadOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_headOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).enterHeadOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).exitHeadOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickCurlVisitor ) return ((JQuickCurlVisitor<? extends T>)visitor).visitHeadOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeadOptionContext headOption() throws RecognitionException {
		HeadOptionContext _localctx = new HeadOptionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_headOption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			_la = _input.LA(1);
			if ( !(_la==T__9 || _la==T__10) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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
	public static class IgnoreOptionContext extends ParserRuleContext {
		public IgnoreOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ignoreOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).enterIgnoreOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).exitIgnoreOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickCurlVisitor ) return ((JQuickCurlVisitor<? extends T>)visitor).visitIgnoreOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IgnoreOptionContext ignoreOption() throws RecognitionException {
		IgnoreOptionContext _localctx = new IgnoreOptionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_ignoreOption);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(83);
			match(T__11);
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
	public static class DataOptionContext extends ParserRuleContext {
		public StringContext dataValue;
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public DataOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).enterDataOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).exitDataOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickCurlVisitor ) return ((JQuickCurlVisitor<? extends T>)visitor).visitDataOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataOptionContext dataOption() throws RecognitionException {
		DataOptionContext _localctx = new DataOptionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_dataOption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 253952L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(86);
			((DataOptionContext)_localctx).dataValue = string();
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
	public static class DataUrlEncodeOptionContext extends ParserRuleContext {
		public EmptyDataContext emptyData() {
			return getRuleContext(EmptyDataContext.class,0);
		}
		public FormDataContext formData() {
			return getRuleContext(FormDataContext.class,0);
		}
		public DataUrlEncodeOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataUrlEncodeOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).enterDataUrlEncodeOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).exitDataUrlEncodeOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickCurlVisitor ) return ((JQuickCurlVisitor<? extends T>)visitor).visitDataUrlEncodeOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataUrlEncodeOptionContext dataUrlEncodeOption() throws RecognitionException {
		DataUrlEncodeOptionContext _localctx = new DataUrlEncodeOptionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_dataUrlEncodeOption);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(T__17);
			setState(91);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(89);
				emptyData();
				}
				break;
			case 2:
				{
				setState(90);
				formData();
				}
				break;
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
	public static class EmptyDataContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public EmptyDataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_emptyData; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).enterEmptyData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).exitEmptyData(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickCurlVisitor ) return ((JQuickCurlVisitor<? extends T>)visitor).visitEmptyData(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EmptyDataContext emptyData() throws RecognitionException {
		EmptyDataContext _localctx = new EmptyDataContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_emptyData);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			string();
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
	public static class FormDataContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public FormDataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formData; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).enterFormData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).exitFormData(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickCurlVisitor ) return ((JQuickCurlVisitor<? extends T>)visitor).visitFormData(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormDataContext formData() throws RecognitionException {
		FormDataContext _localctx = new FormDataContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_formData);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			string();
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
	public static class UserOptionContext extends ParserRuleContext {
		public StringContext userValue;
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public UserOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_userOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).enterUserOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).exitUserOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickCurlVisitor ) return ((JQuickCurlVisitor<? extends T>)visitor).visitUserOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UserOptionContext userOption() throws RecognitionException {
		UserOptionContext _localctx = new UserOptionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_userOption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			_la = _input.LA(1);
			if ( !(_la==T__18 || _la==T__19) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(98);
			((UserOptionContext)_localctx).userValue = string();
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
	public static class LocationOptionContext extends ParserRuleContext {
		public LoptionContext loption() {
			return getRuleContext(LoptionContext.class,0);
		}
		public LocationOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_locationOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).enterLocationOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).exitLocationOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickCurlVisitor ) return ((JQuickCurlVisitor<? extends T>)visitor).visitLocationOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocationOptionContext locationOption() throws RecognitionException {
		LocationOptionContext _localctx = new LocationOptionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_locationOption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			_la = _input.LA(1);
			if ( !(_la==T__20 || _la==T__21) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__22) {
				{
				setState(101);
				loption();
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

	@SuppressWarnings("CheckReturnValue")
	public static class LoptionContext extends ParserRuleContext {
		public StringContext loptionValue;
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public LoptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).enterLoption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).exitLoption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickCurlVisitor ) return ((JQuickCurlVisitor<? extends T>)visitor).visitLoption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoptionContext loption() throws RecognitionException {
		LoptionContext _localctx = new LoptionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_loption);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(104);
			match(T__22);
			}
			setState(105);
			((LoptionContext)_localctx).loptionValue = string();
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
	public static class OtherOptionContext extends ParserRuleContext {
		public OtherOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_otherOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).enterOtherOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).exitOtherOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickCurlVisitor ) return ((JQuickCurlVisitor<? extends T>)visitor).visitOtherOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OtherOptionContext otherOption() throws RecognitionException {
		OtherOptionContext _localctx = new OtherOptionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_otherOption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 520093696L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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
	public static class DownloadOptionContext extends ParserRuleContext {
		public StringContext file;
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public DownloadOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_downloadOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).enterDownloadOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).exitDownloadOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickCurlVisitor ) return ((JQuickCurlVisitor<? extends T>)visitor).visitDownloadOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DownloadOptionContext downloadOption() throws RecognitionException {
		DownloadOptionContext _localctx = new DownloadOptionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_downloadOption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			_la = _input.LA(1);
			if ( !(_la==T__28 || _la==T__29) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(110);
			((DownloadOptionContext)_localctx).file = string();
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
	public static class UploadOptionContext extends ParserRuleContext {
		public StringContext file;
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public UploadOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_uploadOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).enterUploadOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).exitUploadOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickCurlVisitor ) return ((JQuickCurlVisitor<? extends T>)visitor).visitUploadOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UploadOptionContext uploadOption() throws RecognitionException {
		UploadOptionContext _localctx = new UploadOptionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_uploadOption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			_la = _input.LA(1);
			if ( !(_la==T__30 || _la==T__31) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(113);
			((UploadOptionContext)_localctx).file = string();
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
	public static class UrlContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TerminalNode URL() { return getToken(JQuickCurlParser.URL, 0); }
		public UrlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_url; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).enterUrl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).exitUrl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickCurlVisitor ) return ((JQuickCurlVisitor<? extends T>)visitor).visitUrl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UrlContext url() throws RecognitionException {
		UrlContext _localctx = new UrlContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_url);
		try {
			setState(117);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__32:
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(115);
				string();
				}
				break;
			case URL:
				enterOuterAlt(_localctx, 2);
				{
				setState(116);
				match(URL);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class StringContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(JQuickCurlParser.STRING, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickCurlVisitor ) return ((JQuickCurlVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_string);
		try {
			setState(121);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(119);
				match(STRING);
				}
				break;
			case T__32:
				enterOuterAlt(_localctx, 2);
				{
				setState(120);
				variable();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class VariableContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(JQuickCurlParser.IDENTIFIER, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickCurlListener ) ((JQuickCurlListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickCurlVisitor ) return ((JQuickCurlVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(T__32);
			setState(124);
			match(IDENTIFIER);
			setState(125);
			match(T__33);
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
		"\u0004\u0001)\u0080\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0004\u00000\b\u0000\u000b\u0000"+
		"\f\u00001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001B\b\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\n\u0001\n\u0001\n\u0003\n\\\b\n\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0003\u000e"+
		"g\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0013\u0001\u0013\u0003\u0013v\b\u0013\u0001\u0014\u0001\u0014"+
		"\u0003\u0014z\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0000\u0000\u0016\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*\u0000\n\u0001\u0000"+
		"\u0002\u0003\u0001\u0000\u0004\u0005\u0001\u0000\u0006\u0007\u0001\u0000"+
		"\n\u000b\u0001\u0000\r\u0011\u0001\u0000\u0013\u0014\u0001\u0000\u0015"+
		"\u0016\u0001\u0000\u0018\u001c\u0001\u0000\u001d\u001e\u0001\u0000\u001f"+
		" |\u0000,\u0001\u0000\u0000\u0000\u0002A\u0001\u0000\u0000\u0000\u0004"+
		"C\u0001\u0000\u0000\u0000\u0006F\u0001\u0000\u0000\u0000\bI\u0001\u0000"+
		"\u0000\u0000\nL\u0001\u0000\u0000\u0000\fO\u0001\u0000\u0000\u0000\u000e"+
		"Q\u0001\u0000\u0000\u0000\u0010S\u0001\u0000\u0000\u0000\u0012U\u0001"+
		"\u0000\u0000\u0000\u0014X\u0001\u0000\u0000\u0000\u0016]\u0001\u0000\u0000"+
		"\u0000\u0018_\u0001\u0000\u0000\u0000\u001aa\u0001\u0000\u0000\u0000\u001c"+
		"d\u0001\u0000\u0000\u0000\u001eh\u0001\u0000\u0000\u0000 k\u0001\u0000"+
		"\u0000\u0000\"m\u0001\u0000\u0000\u0000$p\u0001\u0000\u0000\u0000&u\u0001"+
		"\u0000\u0000\u0000(y\u0001\u0000\u0000\u0000*{\u0001\u0000\u0000\u0000"+
		",/\u0005\u0001\u0000\u0000-0\u0003\u0002\u0001\u0000.0\u0003&\u0013\u0000"+
		"/-\u0001\u0000\u0000\u0000/.\u0001\u0000\u0000\u000001\u0001\u0000\u0000"+
		"\u00001/\u0001\u0000\u0000\u000012\u0001\u0000\u0000\u00002\u0001\u0001"+
		"\u0000\u0000\u00003B\u0003\u0004\u0002\u00004B\u0003\u0006\u0003\u0000"+
		"5B\u0003\u0012\t\u00006B\u0003\u0014\n\u00007B\u0003\u001a\r\u00008B\u0003"+
		"\u001c\u000e\u00009B\u0003 \u0010\u0000:B\u0003\"\u0011\u0000;B\u0003"+
		"$\u0012\u0000<B\u0003\b\u0004\u0000=B\u0003\n\u0005\u0000>B\u0003\f\u0006"+
		"\u0000?B\u0003\u0010\b\u0000@B\u0003\u000e\u0007\u0000A3\u0001\u0000\u0000"+
		"\u0000A4\u0001\u0000\u0000\u0000A5\u0001\u0000\u0000\u0000A6\u0001\u0000"+
		"\u0000\u0000A7\u0001\u0000\u0000\u0000A8\u0001\u0000\u0000\u0000A9\u0001"+
		"\u0000\u0000\u0000A:\u0001\u0000\u0000\u0000A;\u0001\u0000\u0000\u0000"+
		"A<\u0001\u0000\u0000\u0000A=\u0001\u0000\u0000\u0000A>\u0001\u0000\u0000"+
		"\u0000A?\u0001\u0000\u0000\u0000A@\u0001\u0000\u0000\u0000B\u0003\u0001"+
		"\u0000\u0000\u0000CD\u0007\u0000\u0000\u0000DE\u0005#\u0000\u0000E\u0005"+
		"\u0001\u0000\u0000\u0000FG\u0007\u0001\u0000\u0000GH\u0003(\u0014\u0000"+
		"H\u0007\u0001\u0000\u0000\u0000IJ\u0007\u0002\u0000\u0000JK\u0003(\u0014"+
		"\u0000K\t\u0001\u0000\u0000\u0000LM\u0005\b\u0000\u0000MN\u0003(\u0014"+
		"\u0000N\u000b\u0001\u0000\u0000\u0000OP\u0005\t\u0000\u0000P\r\u0001\u0000"+
		"\u0000\u0000QR\u0007\u0003\u0000\u0000R\u000f\u0001\u0000\u0000\u0000"+
		"ST\u0005\f\u0000\u0000T\u0011\u0001\u0000\u0000\u0000UV\u0007\u0004\u0000"+
		"\u0000VW\u0003(\u0014\u0000W\u0013\u0001\u0000\u0000\u0000X[\u0005\u0012"+
		"\u0000\u0000Y\\\u0003\u0016\u000b\u0000Z\\\u0003\u0018\f\u0000[Y\u0001"+
		"\u0000\u0000\u0000[Z\u0001\u0000\u0000\u0000\\\u0015\u0001\u0000\u0000"+
		"\u0000]^\u0003(\u0014\u0000^\u0017\u0001\u0000\u0000\u0000_`\u0003(\u0014"+
		"\u0000`\u0019\u0001\u0000\u0000\u0000ab\u0007\u0005\u0000\u0000bc\u0003"+
		"(\u0014\u0000c\u001b\u0001\u0000\u0000\u0000df\u0007\u0006\u0000\u0000"+
		"eg\u0003\u001e\u000f\u0000fe\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000"+
		"\u0000g\u001d\u0001\u0000\u0000\u0000hi\u0005\u0017\u0000\u0000ij\u0003"+
		"(\u0014\u0000j\u001f\u0001\u0000\u0000\u0000kl\u0007\u0007\u0000\u0000"+
		"l!\u0001\u0000\u0000\u0000mn\u0007\b\u0000\u0000no\u0003(\u0014\u0000"+
		"o#\u0001\u0000\u0000\u0000pq\u0007\t\u0000\u0000qr\u0003(\u0014\u0000"+
		"r%\u0001\u0000\u0000\u0000sv\u0003(\u0014\u0000tv\u0005$\u0000\u0000u"+
		"s\u0001\u0000\u0000\u0000ut\u0001\u0000\u0000\u0000v\'\u0001\u0000\u0000"+
		"\u0000wz\u0005&\u0000\u0000xz\u0003*\u0015\u0000yw\u0001\u0000\u0000\u0000"+
		"yx\u0001\u0000\u0000\u0000z)\u0001\u0000\u0000\u0000{|\u0005!\u0000\u0000"+
		"|}\u0005%\u0000\u0000}~\u0005\"\u0000\u0000~+\u0001\u0000\u0000\u0000"+
		"\u0007/1A[fuy";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}