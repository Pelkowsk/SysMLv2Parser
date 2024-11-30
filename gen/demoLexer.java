// Generated from /Users/janpelkowski/Documents/TestMitGradle2/src/grammar/demo.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class demoLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, IF=6, ELSE=7, PLUS=8, MINUS=9, 
		MUL=10, DIV=11, MOD=12, QUESTION=13, COLON=14, GT=15, LT=16, GE=17, LE=18, 
		EQ=19, NEQ=20, AND=21, OR=22, XOR=23, IMPLIES=24, NUMBER=25, ID=26, WS=27;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "IF", "ELSE", "PLUS", "MINUS", 
			"MUL", "DIV", "MOD", "QUESTION", "COLON", "GT", "LT", "GE", "LE", "EQ", 
			"NEQ", "AND", "OR", "XOR", "IMPLIES", "NUMBER", "ID", "WS"
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


	public demoLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "demo.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u001b\u008e\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0004"+
		"\u0018}\b\u0018\u000b\u0018\f\u0018~\u0001\u0019\u0001\u0019\u0005\u0019"+
		"\u0083\b\u0019\n\u0019\f\u0019\u0086\t\u0019\u0001\u001a\u0004\u001a\u0089"+
		"\b\u001a\u000b\u001a\f\u001a\u008a\u0001\u001a\u0001\u001a\u0000\u0000"+
		"\u001b\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006"+
		"\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e"+
		"\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017"+
		"/\u00181\u00193\u001a5\u001b\u0001\u0000\u0004\u0001\u000009\u0003\u0000"+
		"AZ__az\u0004\u000009AZ__az\u0003\u0000\t\n\r\r  \u0090\u0000\u0001\u0001"+
		"\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001"+
		"\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000"+
		"\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000"+
		"\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000"+
		"\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000"+
		"\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000"+
		"\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000"+
		"\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000"+
		"\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'"+
		"\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000"+
		"\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000"+
		"\u00001\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005"+
		"\u0001\u0000\u0000\u0000\u00017\u0001\u0000\u0000\u0000\u0003:\u0001\u0000"+
		"\u0000\u0000\u0005<\u0001\u0000\u0000\u0000\u0007>\u0001\u0000\u0000\u0000"+
		"\t@\u0001\u0000\u0000\u0000\u000bB\u0001\u0000\u0000\u0000\rE\u0001\u0000"+
		"\u0000\u0000\u000fJ\u0001\u0000\u0000\u0000\u0011L\u0001\u0000\u0000\u0000"+
		"\u0013N\u0001\u0000\u0000\u0000\u0015P\u0001\u0000\u0000\u0000\u0017R"+
		"\u0001\u0000\u0000\u0000\u0019T\u0001\u0000\u0000\u0000\u001bV\u0001\u0000"+
		"\u0000\u0000\u001dX\u0001\u0000\u0000\u0000\u001fZ\u0001\u0000\u0000\u0000"+
		"!\\\u0001\u0000\u0000\u0000#_\u0001\u0000\u0000\u0000%b\u0001\u0000\u0000"+
		"\u0000\'e\u0001\u0000\u0000\u0000)h\u0001\u0000\u0000\u0000+l\u0001\u0000"+
		"\u0000\u0000-o\u0001\u0000\u0000\u0000/s\u0001\u0000\u0000\u00001|\u0001"+
		"\u0000\u0000\u00003\u0080\u0001\u0000\u0000\u00005\u0088\u0001\u0000\u0000"+
		"\u000078\u0005?\u0000\u000089\u0005?\u0000\u00009\u0002\u0001\u0000\u0000"+
		"\u0000:;\u0005|\u0000\u0000;\u0004\u0001\u0000\u0000\u0000<=\u0005&\u0000"+
		"\u0000=\u0006\u0001\u0000\u0000\u0000>?\u0005(\u0000\u0000?\b\u0001\u0000"+
		"\u0000\u0000@A\u0005)\u0000\u0000A\n\u0001\u0000\u0000\u0000BC\u0005i"+
		"\u0000\u0000CD\u0005f\u0000\u0000D\f\u0001\u0000\u0000\u0000EF\u0005e"+
		"\u0000\u0000FG\u0005l\u0000\u0000GH\u0005s\u0000\u0000HI\u0005e\u0000"+
		"\u0000I\u000e\u0001\u0000\u0000\u0000JK\u0005+\u0000\u0000K\u0010\u0001"+
		"\u0000\u0000\u0000LM\u0005-\u0000\u0000M\u0012\u0001\u0000\u0000\u0000"+
		"NO\u0005*\u0000\u0000O\u0014\u0001\u0000\u0000\u0000PQ\u0005/\u0000\u0000"+
		"Q\u0016\u0001\u0000\u0000\u0000RS\u0005%\u0000\u0000S\u0018\u0001\u0000"+
		"\u0000\u0000TU\u0005?\u0000\u0000U\u001a\u0001\u0000\u0000\u0000VW\u0005"+
		":\u0000\u0000W\u001c\u0001\u0000\u0000\u0000XY\u0005>\u0000\u0000Y\u001e"+
		"\u0001\u0000\u0000\u0000Z[\u0005<\u0000\u0000[ \u0001\u0000\u0000\u0000"+
		"\\]\u0005>\u0000\u0000]^\u0005=\u0000\u0000^\"\u0001\u0000\u0000\u0000"+
		"_`\u0005<\u0000\u0000`a\u0005=\u0000\u0000a$\u0001\u0000\u0000\u0000b"+
		"c\u0005=\u0000\u0000cd\u0005=\u0000\u0000d&\u0001\u0000\u0000\u0000ef"+
		"\u0005!\u0000\u0000fg\u0005=\u0000\u0000g(\u0001\u0000\u0000\u0000hi\u0005"+
		"a\u0000\u0000ij\u0005n\u0000\u0000jk\u0005d\u0000\u0000k*\u0001\u0000"+
		"\u0000\u0000lm\u0005o\u0000\u0000mn\u0005r\u0000\u0000n,\u0001\u0000\u0000"+
		"\u0000op\u0005x\u0000\u0000pq\u0005o\u0000\u0000qr\u0005r\u0000\u0000"+
		"r.\u0001\u0000\u0000\u0000st\u0005i\u0000\u0000tu\u0005m\u0000\u0000u"+
		"v\u0005p\u0000\u0000vw\u0005l\u0000\u0000wx\u0005i\u0000\u0000xy\u0005"+
		"e\u0000\u0000yz\u0005s\u0000\u0000z0\u0001\u0000\u0000\u0000{}\u0007\u0000"+
		"\u0000\u0000|{\u0001\u0000\u0000\u0000}~\u0001\u0000\u0000\u0000~|\u0001"+
		"\u0000\u0000\u0000~\u007f\u0001\u0000\u0000\u0000\u007f2\u0001\u0000\u0000"+
		"\u0000\u0080\u0084\u0007\u0001\u0000\u0000\u0081\u0083\u0007\u0002\u0000"+
		"\u0000\u0082\u0081\u0001\u0000\u0000\u0000\u0083\u0086\u0001\u0000\u0000"+
		"\u0000\u0084\u0082\u0001\u0000\u0000\u0000\u0084\u0085\u0001\u0000\u0000"+
		"\u0000\u00854\u0001\u0000\u0000\u0000\u0086\u0084\u0001\u0000\u0000\u0000"+
		"\u0087\u0089\u0007\u0003\u0000\u0000\u0088\u0087\u0001\u0000\u0000\u0000"+
		"\u0089\u008a\u0001\u0000\u0000\u0000\u008a\u0088\u0001\u0000\u0000\u0000"+
		"\u008a\u008b\u0001\u0000\u0000\u0000\u008b\u008c\u0001\u0000\u0000\u0000"+
		"\u008c\u008d\u0006\u001a\u0000\u0000\u008d6\u0001\u0000\u0000\u0000\u0004"+
		"\u0000~\u0084\u008a\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}