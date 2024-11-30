// Generated from /Users/janpelkowski/Documents/TestMitGradle2/src/grammar/demo.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class demoParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, IF=6, ELSE=7, PLUS=8, MINUS=9, 
		MUL=10, DIV=11, MOD=12, QUESTION=13, COLON=14, GT=15, LT=16, GE=17, LE=18, 
		EQ=19, NEQ=20, AND=21, OR=22, XOR=23, IMPLIES=24, NUMBER=25, ID=26, WS=27;
	public static final int
		RULE_expr = 0, RULE_conditionalExpr = 1, RULE_nullCoalescingExpr = 2, 
		RULE_ownedExprRef = 3, RULE_impliesExpr = 4, RULE_orExpr = 5, RULE_xorExpr = 6, 
		RULE_andExpr = 7, RULE_equalityExpr = 8, RULE_relationalExpr = 9, RULE_additiveExpr = 10, 
		RULE_multiplicativeExpr = 11, RULE_primaryExpr = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"expr", "conditionalExpr", "nullCoalescingExpr", "ownedExprRef", "impliesExpr", 
			"orExpr", "xorExpr", "andExpr", "equalityExpr", "relationalExpr", "additiveExpr", 
			"multiplicativeExpr", "primaryExpr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'??'", "'|'", "'&'", "'('", "')'", "'if'", "'else'", "'+'", "'-'", 
			"'*'", "'/'", "'%'", "'?'", "':'", "'>'", "'<'", "'>='", "'<='", "'=='", 
			"'!='", "'and'", "'or'", "'xor'", "'implies'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "IF", "ELSE", "PLUS", "MINUS", "MUL", 
			"DIV", "MOD", "QUESTION", "COLON", "GT", "LT", "GE", "LE", "EQ", "NEQ", 
			"AND", "OR", "XOR", "IMPLIES", "NUMBER", "ID", "WS"
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
	public String getGrammarFileName() { return "demo.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public demoParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ConditionalExprContext conditionalExpr() {
			return getRuleContext(ConditionalExprContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof demoVisitor ) return ((demoVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			conditionalExpr();
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
	public static class ConditionalExprContext extends ParserRuleContext {
		public NullCoalescingExprContext nullCoalescingExpr() {
			return getRuleContext(NullCoalescingExprContext.class,0);
		}
		public TerminalNode IF() { return getToken(demoParser.IF, 0); }
		public TerminalNode QUESTION() { return getToken(demoParser.QUESTION, 0); }
		public List<OwnedExprRefContext> ownedExprRef() {
			return getRuleContexts(OwnedExprRefContext.class);
		}
		public OwnedExprRefContext ownedExprRef(int i) {
			return getRuleContext(OwnedExprRefContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(demoParser.ELSE, 0); }
		public ConditionalExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).enterConditionalExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).exitConditionalExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof demoVisitor ) return ((demoVisitor<? extends T>)visitor).visitConditionalExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionalExprContext conditionalExpr() throws RecognitionException {
		ConditionalExprContext _localctx = new ConditionalExprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_conditionalExpr);
		try {
			setState(36);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
			case NUMBER:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(28);
				nullCoalescingExpr();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(29);
				match(IF);
				setState(30);
				nullCoalescingExpr();
				setState(31);
				match(QUESTION);
				setState(32);
				ownedExprRef();
				setState(33);
				match(ELSE);
				setState(34);
				ownedExprRef();
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
	public static class NullCoalescingExprContext extends ParserRuleContext {
		public List<ImpliesExprContext> impliesExpr() {
			return getRuleContexts(ImpliesExprContext.class);
		}
		public ImpliesExprContext impliesExpr(int i) {
			return getRuleContext(ImpliesExprContext.class,i);
		}
		public NullCoalescingExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nullCoalescingExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).enterNullCoalescingExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).exitNullCoalescingExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof demoVisitor ) return ((demoVisitor<? extends T>)visitor).visitNullCoalescingExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NullCoalescingExprContext nullCoalescingExpr() throws RecognitionException {
		NullCoalescingExprContext _localctx = new NullCoalescingExprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_nullCoalescingExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			impliesExpr();
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(39);
				match(T__0);
				setState(40);
				impliesExpr();
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class OwnedExprRefContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(demoParser.ID, 0); }
		public OwnedExprRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ownedExprRef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).enterOwnedExprRef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).exitOwnedExprRef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof demoVisitor ) return ((demoVisitor<? extends T>)visitor).visitOwnedExprRef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OwnedExprRefContext ownedExprRef() throws RecognitionException {
		OwnedExprRefContext _localctx = new OwnedExprRefContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ownedExprRef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(ID);
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
	public static class ImpliesExprContext extends ParserRuleContext {
		public List<OrExprContext> orExpr() {
			return getRuleContexts(OrExprContext.class);
		}
		public OrExprContext orExpr(int i) {
			return getRuleContext(OrExprContext.class,i);
		}
		public List<TerminalNode> IMPLIES() { return getTokens(demoParser.IMPLIES); }
		public TerminalNode IMPLIES(int i) {
			return getToken(demoParser.IMPLIES, i);
		}
		public ImpliesExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_impliesExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).enterImpliesExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).exitImpliesExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof demoVisitor ) return ((demoVisitor<? extends T>)visitor).visitImpliesExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImpliesExprContext impliesExpr() throws RecognitionException {
		ImpliesExprContext _localctx = new ImpliesExprContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_impliesExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			orExpr();
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IMPLIES) {
				{
				{
				setState(49);
				match(IMPLIES);
				setState(50);
				orExpr();
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class OrExprContext extends ParserRuleContext {
		public List<XorExprContext> xorExpr() {
			return getRuleContexts(XorExprContext.class);
		}
		public XorExprContext xorExpr(int i) {
			return getRuleContext(XorExprContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(demoParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(demoParser.OR, i);
		}
		public OrExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).exitOrExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof demoVisitor ) return ((demoVisitor<? extends T>)visitor).visitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrExprContext orExpr() throws RecognitionException {
		OrExprContext _localctx = new OrExprContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_orExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			xorExpr();
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1 || _la==OR) {
				{
				{
				setState(57);
				_la = _input.LA(1);
				if ( !(_la==T__1 || _la==OR) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(58);
				xorExpr();
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class XorExprContext extends ParserRuleContext {
		public List<AndExprContext> andExpr() {
			return getRuleContexts(AndExprContext.class);
		}
		public AndExprContext andExpr(int i) {
			return getRuleContext(AndExprContext.class,i);
		}
		public List<TerminalNode> XOR() { return getTokens(demoParser.XOR); }
		public TerminalNode XOR(int i) {
			return getToken(demoParser.XOR, i);
		}
		public XorExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xorExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).enterXorExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).exitXorExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof demoVisitor ) return ((demoVisitor<? extends T>)visitor).visitXorExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final XorExprContext xorExpr() throws RecognitionException {
		XorExprContext _localctx = new XorExprContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_xorExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			andExpr();
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==XOR) {
				{
				{
				setState(65);
				match(XOR);
				setState(66);
				andExpr();
				}
				}
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class AndExprContext extends ParserRuleContext {
		public List<EqualityExprContext> equalityExpr() {
			return getRuleContexts(EqualityExprContext.class);
		}
		public EqualityExprContext equalityExpr(int i) {
			return getRuleContext(EqualityExprContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(demoParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(demoParser.AND, i);
		}
		public AndExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).exitAndExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof demoVisitor ) return ((demoVisitor<? extends T>)visitor).visitAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndExprContext andExpr() throws RecognitionException {
		AndExprContext _localctx = new AndExprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_andExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			equalityExpr();
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2 || _la==AND) {
				{
				{
				setState(73);
				_la = _input.LA(1);
				if ( !(_la==T__2 || _la==AND) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(74);
				equalityExpr();
				}
				}
				setState(79);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class EqualityExprContext extends ParserRuleContext {
		public List<RelationalExprContext> relationalExpr() {
			return getRuleContexts(RelationalExprContext.class);
		}
		public RelationalExprContext relationalExpr(int i) {
			return getRuleContext(RelationalExprContext.class,i);
		}
		public List<TerminalNode> EQ() { return getTokens(demoParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(demoParser.EQ, i);
		}
		public List<TerminalNode> NEQ() { return getTokens(demoParser.NEQ); }
		public TerminalNode NEQ(int i) {
			return getToken(demoParser.NEQ, i);
		}
		public EqualityExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalityExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).enterEqualityExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).exitEqualityExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof demoVisitor ) return ((demoVisitor<? extends T>)visitor).visitEqualityExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualityExprContext equalityExpr() throws RecognitionException {
		EqualityExprContext _localctx = new EqualityExprContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_equalityExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			relationalExpr();
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EQ || _la==NEQ) {
				{
				{
				setState(81);
				_la = _input.LA(1);
				if ( !(_la==EQ || _la==NEQ) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(82);
				relationalExpr();
				}
				}
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class RelationalExprContext extends ParserRuleContext {
		public List<AdditiveExprContext> additiveExpr() {
			return getRuleContexts(AdditiveExprContext.class);
		}
		public AdditiveExprContext additiveExpr(int i) {
			return getRuleContext(AdditiveExprContext.class,i);
		}
		public List<TerminalNode> LT() { return getTokens(demoParser.LT); }
		public TerminalNode LT(int i) {
			return getToken(demoParser.LT, i);
		}
		public List<TerminalNode> GT() { return getTokens(demoParser.GT); }
		public TerminalNode GT(int i) {
			return getToken(demoParser.GT, i);
		}
		public List<TerminalNode> LE() { return getTokens(demoParser.LE); }
		public TerminalNode LE(int i) {
			return getToken(demoParser.LE, i);
		}
		public List<TerminalNode> GE() { return getTokens(demoParser.GE); }
		public TerminalNode GE(int i) {
			return getToken(demoParser.GE, i);
		}
		public RelationalExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).enterRelationalExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).exitRelationalExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof demoVisitor ) return ((demoVisitor<? extends T>)visitor).visitRelationalExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationalExprContext relationalExpr() throws RecognitionException {
		RelationalExprContext _localctx = new RelationalExprContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_relationalExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			additiveExpr();
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 491520L) != 0)) {
				{
				{
				setState(89);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 491520L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(90);
				additiveExpr();
				}
				}
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class AdditiveExprContext extends ParserRuleContext {
		public List<MultiplicativeExprContext> multiplicativeExpr() {
			return getRuleContexts(MultiplicativeExprContext.class);
		}
		public MultiplicativeExprContext multiplicativeExpr(int i) {
			return getRuleContext(MultiplicativeExprContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(demoParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(demoParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(demoParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(demoParser.MINUS, i);
		}
		public AdditiveExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).enterAdditiveExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).exitAdditiveExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof demoVisitor ) return ((demoVisitor<? extends T>)visitor).visitAdditiveExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveExprContext additiveExpr() throws RecognitionException {
		AdditiveExprContext _localctx = new AdditiveExprContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_additiveExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			multiplicativeExpr();
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS || _la==MINUS) {
				{
				{
				setState(97);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(98);
				multiplicativeExpr();
				}
				}
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class MultiplicativeExprContext extends ParserRuleContext {
		public List<PrimaryExprContext> primaryExpr() {
			return getRuleContexts(PrimaryExprContext.class);
		}
		public PrimaryExprContext primaryExpr(int i) {
			return getRuleContext(PrimaryExprContext.class,i);
		}
		public List<TerminalNode> MUL() { return getTokens(demoParser.MUL); }
		public TerminalNode MUL(int i) {
			return getToken(demoParser.MUL, i);
		}
		public List<TerminalNode> DIV() { return getTokens(demoParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(demoParser.DIV, i);
		}
		public List<TerminalNode> MOD() { return getTokens(demoParser.MOD); }
		public TerminalNode MOD(int i) {
			return getToken(demoParser.MOD, i);
		}
		public MultiplicativeExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).enterMultiplicativeExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).exitMultiplicativeExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof demoVisitor ) return ((demoVisitor<? extends T>)visitor).visitMultiplicativeExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicativeExprContext multiplicativeExpr() throws RecognitionException {
		MultiplicativeExprContext _localctx = new MultiplicativeExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_multiplicativeExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			primaryExpr();
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 7168L) != 0)) {
				{
				{
				setState(105);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 7168L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(106);
				primaryExpr();
				}
				}
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class PrimaryExprContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(demoParser.NUMBER, 0); }
		public TerminalNode ID() { return getToken(demoParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrimaryExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).enterPrimaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof demoListener ) ((demoListener)listener).exitPrimaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof demoVisitor ) return ((demoVisitor<? extends T>)visitor).visitPrimaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryExprContext primaryExpr() throws RecognitionException {
		PrimaryExprContext _localctx = new PrimaryExprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_primaryExpr);
		try {
			setState(118);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(112);
				match(NUMBER);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
				match(ID);
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 3);
				{
				setState(114);
				match(T__3);
				setState(115);
				expr();
				setState(116);
				match(T__4);
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

	public static final String _serializedATN =
		"\u0004\u0001\u001by\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"%\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002*\b\u0002\n\u0002"+
		"\f\u0002-\t\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0005\u00044\b\u0004\n\u0004\f\u00047\t\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0005\u0005<\b\u0005\n\u0005\f\u0005?\t\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0005\u0006D\b\u0006\n\u0006\f\u0006G\t"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007L\b\u0007\n\u0007"+
		"\f\u0007O\t\u0007\u0001\b\u0001\b\u0001\b\u0005\bT\b\b\n\b\f\bW\t\b\u0001"+
		"\t\u0001\t\u0001\t\u0005\t\\\b\t\n\t\f\t_\t\t\u0001\n\u0001\n\u0001\n"+
		"\u0005\nd\b\n\n\n\f\ng\t\n\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b"+
		"l\b\u000b\n\u000b\f\u000bo\t\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0003\fw\b\f\u0001\f\u0000\u0000\r\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u0000\u0006\u0002\u0000\u0002"+
		"\u0002\u0016\u0016\u0002\u0000\u0003\u0003\u0015\u0015\u0001\u0000\u0013"+
		"\u0014\u0001\u0000\u000f\u0012\u0001\u0000\b\t\u0001\u0000\n\fw\u0000"+
		"\u001a\u0001\u0000\u0000\u0000\u0002$\u0001\u0000\u0000\u0000\u0004&\u0001"+
		"\u0000\u0000\u0000\u0006.\u0001\u0000\u0000\u0000\b0\u0001\u0000\u0000"+
		"\u0000\n8\u0001\u0000\u0000\u0000\f@\u0001\u0000\u0000\u0000\u000eH\u0001"+
		"\u0000\u0000\u0000\u0010P\u0001\u0000\u0000\u0000\u0012X\u0001\u0000\u0000"+
		"\u0000\u0014`\u0001\u0000\u0000\u0000\u0016h\u0001\u0000\u0000\u0000\u0018"+
		"v\u0001\u0000\u0000\u0000\u001a\u001b\u0003\u0002\u0001\u0000\u001b\u0001"+
		"\u0001\u0000\u0000\u0000\u001c%\u0003\u0004\u0002\u0000\u001d\u001e\u0005"+
		"\u0006\u0000\u0000\u001e\u001f\u0003\u0004\u0002\u0000\u001f \u0005\r"+
		"\u0000\u0000 !\u0003\u0006\u0003\u0000!\"\u0005\u0007\u0000\u0000\"#\u0003"+
		"\u0006\u0003\u0000#%\u0001\u0000\u0000\u0000$\u001c\u0001\u0000\u0000"+
		"\u0000$\u001d\u0001\u0000\u0000\u0000%\u0003\u0001\u0000\u0000\u0000&"+
		"+\u0003\b\u0004\u0000\'(\u0005\u0001\u0000\u0000(*\u0003\b\u0004\u0000"+
		")\'\u0001\u0000\u0000\u0000*-\u0001\u0000\u0000\u0000+)\u0001\u0000\u0000"+
		"\u0000+,\u0001\u0000\u0000\u0000,\u0005\u0001\u0000\u0000\u0000-+\u0001"+
		"\u0000\u0000\u0000./\u0005\u001a\u0000\u0000/\u0007\u0001\u0000\u0000"+
		"\u000005\u0003\n\u0005\u000012\u0005\u0018\u0000\u000024\u0003\n\u0005"+
		"\u000031\u0001\u0000\u0000\u000047\u0001\u0000\u0000\u000053\u0001\u0000"+
		"\u0000\u000056\u0001\u0000\u0000\u00006\t\u0001\u0000\u0000\u000075\u0001"+
		"\u0000\u0000\u00008=\u0003\f\u0006\u00009:\u0007\u0000\u0000\u0000:<\u0003"+
		"\f\u0006\u0000;9\u0001\u0000\u0000\u0000<?\u0001\u0000\u0000\u0000=;\u0001"+
		"\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000>\u000b\u0001\u0000\u0000"+
		"\u0000?=\u0001\u0000\u0000\u0000@E\u0003\u000e\u0007\u0000AB\u0005\u0017"+
		"\u0000\u0000BD\u0003\u000e\u0007\u0000CA\u0001\u0000\u0000\u0000DG\u0001"+
		"\u0000\u0000\u0000EC\u0001\u0000\u0000\u0000EF\u0001\u0000\u0000\u0000"+
		"F\r\u0001\u0000\u0000\u0000GE\u0001\u0000\u0000\u0000HM\u0003\u0010\b"+
		"\u0000IJ\u0007\u0001\u0000\u0000JL\u0003\u0010\b\u0000KI\u0001\u0000\u0000"+
		"\u0000LO\u0001\u0000\u0000\u0000MK\u0001\u0000\u0000\u0000MN\u0001\u0000"+
		"\u0000\u0000N\u000f\u0001\u0000\u0000\u0000OM\u0001\u0000\u0000\u0000"+
		"PU\u0003\u0012\t\u0000QR\u0007\u0002\u0000\u0000RT\u0003\u0012\t\u0000"+
		"SQ\u0001\u0000\u0000\u0000TW\u0001\u0000\u0000\u0000US\u0001\u0000\u0000"+
		"\u0000UV\u0001\u0000\u0000\u0000V\u0011\u0001\u0000\u0000\u0000WU\u0001"+
		"\u0000\u0000\u0000X]\u0003\u0014\n\u0000YZ\u0007\u0003\u0000\u0000Z\\"+
		"\u0003\u0014\n\u0000[Y\u0001\u0000\u0000\u0000\\_\u0001\u0000\u0000\u0000"+
		"][\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000^\u0013\u0001\u0000"+
		"\u0000\u0000_]\u0001\u0000\u0000\u0000`e\u0003\u0016\u000b\u0000ab\u0007"+
		"\u0004\u0000\u0000bd\u0003\u0016\u000b\u0000ca\u0001\u0000\u0000\u0000"+
		"dg\u0001\u0000\u0000\u0000ec\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000"+
		"\u0000f\u0015\u0001\u0000\u0000\u0000ge\u0001\u0000\u0000\u0000hm\u0003"+
		"\u0018\f\u0000ij\u0007\u0005\u0000\u0000jl\u0003\u0018\f\u0000ki\u0001"+
		"\u0000\u0000\u0000lo\u0001\u0000\u0000\u0000mk\u0001\u0000\u0000\u0000"+
		"mn\u0001\u0000\u0000\u0000n\u0017\u0001\u0000\u0000\u0000om\u0001\u0000"+
		"\u0000\u0000pw\u0005\u0019\u0000\u0000qw\u0005\u001a\u0000\u0000rs\u0005"+
		"\u0004\u0000\u0000st\u0003\u0000\u0000\u0000tu\u0005\u0005\u0000\u0000"+
		"uw\u0001\u0000\u0000\u0000vp\u0001\u0000\u0000\u0000vq\u0001\u0000\u0000"+
		"\u0000vr\u0001\u0000\u0000\u0000w\u0019\u0001\u0000\u0000\u0000\u000b"+
		"$+5=EMU]emv";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}