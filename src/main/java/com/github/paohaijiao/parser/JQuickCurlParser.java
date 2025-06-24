// Generated from D:/idea/jthornruleGrammer/QuickRest/JQuickCurl.g4 by ANTLR 4.13.2
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
		T__31=32, HTTP_METHOD=33, STRING=34, IDENTIFIER=35, URL=36, WS=37, LINE_CONTINUATION=38, 
		UNKNOWN=39, STRING_EMPTY=40;
	public static final int
		RULE_curlCommand = 0, RULE_option = 1, RULE_requestMethod = 2, RULE_headerOption = 3, 
		RULE_proxryOption = 4, RULE_socketOption = 5, RULE_http2Option = 6, RULE_ignoreOption = 7, 
		RULE_dataOption = 8, RULE_dataUrlEncodeOption = 9, RULE_emptyData = 10, 
		RULE_formData = 11, RULE_userOption = 12, RULE_locationOption = 13, RULE_loption = 14, 
		RULE_otherOption = 15, RULE_downloadOption = 16, RULE_uploadOption = 17, 
		RULE_url = 18, RULE_string = 19, RULE_variable = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"curlCommand", "option", "requestMethod", "headerOption", "proxryOption", 
			"socketOption", "http2Option", "ignoreOption", "dataOption", "dataUrlEncodeOption", 
			"emptyData", "formData", "userOption", "locationOption", "loption", "otherOption", 
			"downloadOption", "uploadOption", "url", "string", "variable"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'curl'", "'-X'", "'--request'", "'-H'", "'--header'", "'-x'", 
			"'--proxy'", "'--socks5-hostname'", "'--http2'", "'-k'", "'-d'", "'--data'", 
			"'--data-ascii'", "'--data-binary'", "'--data-raw'", "'--data-urlencode'", 
			"'-u'", "'--user'", "'-L'", "'--location'", "'--max-redirs'", "'-v'", 
			"'--verbose'", "'-s'", "'--silent'", "'--insecure'", "'-o'", "'--output'", 
			"'-F'", "'--form'", "'${'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, "HTTP_METHOD", 
			"STRING", "IDENTIFIER", "URL", "WS", "LINE_CONTINUATION", "UNKNOWN", 
			"STRING_EMPTY"
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
			setState(42);
			match(T__0);
			setState(45); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(45);
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
				case T__21:
				case T__22:
				case T__23:
				case T__24:
				case T__25:
				case T__26:
				case T__27:
				case T__28:
				case T__29:
					{
					setState(43);
					option();
					}
					break;
				case T__30:
				case STRING:
				case URL:
					{
					setState(44);
					url();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(47); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 90192216060L) != 0) );
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
			setState(62);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(49);
				requestMethod();
				}
				break;
			case T__3:
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(50);
				headerOption();
				}
				break;
			case T__10:
			case T__11:
			case T__12:
			case T__13:
			case T__14:
				enterOuterAlt(_localctx, 3);
				{
				setState(51);
				dataOption();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 4);
				{
				setState(52);
				dataUrlEncodeOption();
				}
				break;
			case T__16:
			case T__17:
				enterOuterAlt(_localctx, 5);
				{
				setState(53);
				userOption();
				}
				break;
			case T__18:
			case T__19:
				enterOuterAlt(_localctx, 6);
				{
				setState(54);
				locationOption();
				}
				break;
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case T__25:
				enterOuterAlt(_localctx, 7);
				{
				setState(55);
				otherOption();
				}
				break;
			case T__26:
			case T__27:
				enterOuterAlt(_localctx, 8);
				{
				setState(56);
				downloadOption();
				}
				break;
			case T__28:
			case T__29:
				enterOuterAlt(_localctx, 9);
				{
				setState(57);
				uploadOption();
				}
				break;
			case T__5:
			case T__6:
				enterOuterAlt(_localctx, 10);
				{
				setState(58);
				proxryOption();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 11);
				{
				setState(59);
				socketOption();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 12);
				{
				setState(60);
				http2Option();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 13);
				{
				setState(61);
				ignoreOption();
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
			setState(64);
			_la = _input.LA(1);
			if ( !(_la==T__1 || _la==T__2) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(65);
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
			setState(67);
			_la = _input.LA(1);
			if ( !(_la==T__3 || _la==T__4) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(68);
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
			setState(70);
			_la = _input.LA(1);
			if ( !(_la==T__5 || _la==T__6) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(71);
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
			setState(73);
			match(T__7);
			}
			setState(74);
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
			setState(76);
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
		enterRule(_localctx, 14, RULE_ignoreOption);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(78);
			match(T__9);
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
		public Token dataValue;
		public TerminalNode STRING() { return getToken(JQuickCurlParser.STRING, 0); }
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
		enterRule(_localctx, 16, RULE_dataOption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 63488L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(81);
			((DataOptionContext)_localctx).dataValue = match(STRING);
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
		enterRule(_localctx, 18, RULE_dataUrlEncodeOption);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(T__15);
			setState(86);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING_EMPTY:
				{
				setState(84);
				emptyData();
				}
				break;
			case STRING:
				{
				setState(85);
				formData();
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
	public static class EmptyDataContext extends ParserRuleContext {
		public TerminalNode STRING_EMPTY() { return getToken(JQuickCurlParser.STRING_EMPTY, 0); }
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
		enterRule(_localctx, 20, RULE_emptyData);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(STRING_EMPTY);
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
		public TerminalNode STRING() { return getToken(JQuickCurlParser.STRING, 0); }
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
		enterRule(_localctx, 22, RULE_formData);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(STRING);
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
		public Token userValue;
		public TerminalNode STRING() { return getToken(JQuickCurlParser.STRING, 0); }
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
		enterRule(_localctx, 24, RULE_userOption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			_la = _input.LA(1);
			if ( !(_la==T__16 || _la==T__17) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(93);
			((UserOptionContext)_localctx).userValue = match(STRING);
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
		enterRule(_localctx, 26, RULE_locationOption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			_la = _input.LA(1);
			if ( !(_la==T__18 || _la==T__19) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__20) {
				{
				setState(96);
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
		public Token loptionValue;
		public TerminalNode STRING() { return getToken(JQuickCurlParser.STRING, 0); }
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
		enterRule(_localctx, 28, RULE_loption);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(99);
			match(T__20);
			}
			setState(100);
			((LoptionContext)_localctx).loptionValue = match(STRING);
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
		enterRule(_localctx, 30, RULE_otherOption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 130023424L) != 0)) ) {
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
		enterRule(_localctx, 32, RULE_downloadOption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			_la = _input.LA(1);
			if ( !(_la==T__26 || _la==T__27) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(105);
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
		enterRule(_localctx, 34, RULE_uploadOption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			_la = _input.LA(1);
			if ( !(_la==T__28 || _la==T__29) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(108);
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
		enterRule(_localctx, 36, RULE_url);
		try {
			setState(112);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__30:
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(110);
				string();
				}
				break;
			case URL:
				enterOuterAlt(_localctx, 2);
				{
				setState(111);
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
		enterRule(_localctx, 38, RULE_string);
		try {
			setState(116);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				match(STRING);
				}
				break;
			case T__30:
				enterOuterAlt(_localctx, 2);
				{
				setState(115);
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
		enterRule(_localctx, 40, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(T__30);
			setState(119);
			match(IDENTIFIER);
			setState(120);
			match(T__31);
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
		"\u0004\u0001({\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002\u0002"+
		"\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002\u0005"+
		"\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002\b\u0007"+
		"\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002\f\u0007"+
		"\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f\u0002"+
		"\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012\u0002"+
		"\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0004\u0000.\b\u0000\u000b\u0000\f\u0000/\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"?\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\t\u0001\t\u0001\t\u0003\tW\b\t\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0003\rb\b"+
		"\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012"+
		"\u0001\u0012\u0003\u0012q\b\u0012\u0001\u0013\u0001\u0013\u0003\u0013"+
		"u\b\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0000\u0000\u0015\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014"+
		"\u0016\u0018\u001a\u001c\u001e \"$&(\u0000\t\u0001\u0000\u0002\u0003\u0001"+
		"\u0000\u0004\u0005\u0001\u0000\u0006\u0007\u0001\u0000\u000b\u000f\u0001"+
		"\u0000\u0011\u0012\u0001\u0000\u0013\u0014\u0001\u0000\u0016\u001a\u0001"+
		"\u0000\u001b\u001c\u0001\u0000\u001d\u001ew\u0000*\u0001\u0000\u0000\u0000"+
		"\u0002>\u0001\u0000\u0000\u0000\u0004@\u0001\u0000\u0000\u0000\u0006C"+
		"\u0001\u0000\u0000\u0000\bF\u0001\u0000\u0000\u0000\nI\u0001\u0000\u0000"+
		"\u0000\fL\u0001\u0000\u0000\u0000\u000eN\u0001\u0000\u0000\u0000\u0010"+
		"P\u0001\u0000\u0000\u0000\u0012S\u0001\u0000\u0000\u0000\u0014X\u0001"+
		"\u0000\u0000\u0000\u0016Z\u0001\u0000\u0000\u0000\u0018\\\u0001\u0000"+
		"\u0000\u0000\u001a_\u0001\u0000\u0000\u0000\u001cc\u0001\u0000\u0000\u0000"+
		"\u001ef\u0001\u0000\u0000\u0000 h\u0001\u0000\u0000\u0000\"k\u0001\u0000"+
		"\u0000\u0000$p\u0001\u0000\u0000\u0000&t\u0001\u0000\u0000\u0000(v\u0001"+
		"\u0000\u0000\u0000*-\u0005\u0001\u0000\u0000+.\u0003\u0002\u0001\u0000"+
		",.\u0003$\u0012\u0000-+\u0001\u0000\u0000\u0000-,\u0001\u0000\u0000\u0000"+
		"./\u0001\u0000\u0000\u0000/-\u0001\u0000\u0000\u0000/0\u0001\u0000\u0000"+
		"\u00000\u0001\u0001\u0000\u0000\u00001?\u0003\u0004\u0002\u00002?\u0003"+
		"\u0006\u0003\u00003?\u0003\u0010\b\u00004?\u0003\u0012\t\u00005?\u0003"+
		"\u0018\f\u00006?\u0003\u001a\r\u00007?\u0003\u001e\u000f\u00008?\u0003"+
		" \u0010\u00009?\u0003\"\u0011\u0000:?\u0003\b\u0004\u0000;?\u0003\n\u0005"+
		"\u0000<?\u0003\f\u0006\u0000=?\u0003\u000e\u0007\u0000>1\u0001\u0000\u0000"+
		"\u0000>2\u0001\u0000\u0000\u0000>3\u0001\u0000\u0000\u0000>4\u0001\u0000"+
		"\u0000\u0000>5\u0001\u0000\u0000\u0000>6\u0001\u0000\u0000\u0000>7\u0001"+
		"\u0000\u0000\u0000>8\u0001\u0000\u0000\u0000>9\u0001\u0000\u0000\u0000"+
		">:\u0001\u0000\u0000\u0000>;\u0001\u0000\u0000\u0000><\u0001\u0000\u0000"+
		"\u0000>=\u0001\u0000\u0000\u0000?\u0003\u0001\u0000\u0000\u0000@A\u0007"+
		"\u0000\u0000\u0000AB\u0005!\u0000\u0000B\u0005\u0001\u0000\u0000\u0000"+
		"CD\u0007\u0001\u0000\u0000DE\u0003&\u0013\u0000E\u0007\u0001\u0000\u0000"+
		"\u0000FG\u0007\u0002\u0000\u0000GH\u0003&\u0013\u0000H\t\u0001\u0000\u0000"+
		"\u0000IJ\u0005\b\u0000\u0000JK\u0003&\u0013\u0000K\u000b\u0001\u0000\u0000"+
		"\u0000LM\u0005\t\u0000\u0000M\r\u0001\u0000\u0000\u0000NO\u0005\n\u0000"+
		"\u0000O\u000f\u0001\u0000\u0000\u0000PQ\u0007\u0003\u0000\u0000QR\u0005"+
		"\"\u0000\u0000R\u0011\u0001\u0000\u0000\u0000SV\u0005\u0010\u0000\u0000"+
		"TW\u0003\u0014\n\u0000UW\u0003\u0016\u000b\u0000VT\u0001\u0000\u0000\u0000"+
		"VU\u0001\u0000\u0000\u0000W\u0013\u0001\u0000\u0000\u0000XY\u0005(\u0000"+
		"\u0000Y\u0015\u0001\u0000\u0000\u0000Z[\u0005\"\u0000\u0000[\u0017\u0001"+
		"\u0000\u0000\u0000\\]\u0007\u0004\u0000\u0000]^\u0005\"\u0000\u0000^\u0019"+
		"\u0001\u0000\u0000\u0000_a\u0007\u0005\u0000\u0000`b\u0003\u001c\u000e"+
		"\u0000a`\u0001\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000b\u001b\u0001"+
		"\u0000\u0000\u0000cd\u0005\u0015\u0000\u0000de\u0005\"\u0000\u0000e\u001d"+
		"\u0001\u0000\u0000\u0000fg\u0007\u0006\u0000\u0000g\u001f\u0001\u0000"+
		"\u0000\u0000hi\u0007\u0007\u0000\u0000ij\u0003&\u0013\u0000j!\u0001\u0000"+
		"\u0000\u0000kl\u0007\b\u0000\u0000lm\u0003&\u0013\u0000m#\u0001\u0000"+
		"\u0000\u0000nq\u0003&\u0013\u0000oq\u0005$\u0000\u0000pn\u0001\u0000\u0000"+
		"\u0000po\u0001\u0000\u0000\u0000q%\u0001\u0000\u0000\u0000ru\u0005\"\u0000"+
		"\u0000su\u0003(\u0014\u0000tr\u0001\u0000\u0000\u0000ts\u0001\u0000\u0000"+
		"\u0000u\'\u0001\u0000\u0000\u0000vw\u0005\u001f\u0000\u0000wx\u0005#\u0000"+
		"\u0000xy\u0005 \u0000\u0000y)\u0001\u0000\u0000\u0000\u0007-/>Vapt";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}