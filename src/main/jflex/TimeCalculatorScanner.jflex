package timeCalculatorParser;
import java_cup.runtime.Symbol;
%%


%public
%class lexer
// Use CUP compatibility mode to interface with a CUP parser.
%cup

%unicode

%{
    /** Creates a new {@link Symbol} of the given type. */
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }

    /** Creates a new {@link Symbol} of the given type and value. */
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

// A string time is a sequence of digit that can contain the symbol +, -, :, AM, PM or underscore
Time         = ([0-9=]|-|:|AM|PM|\+)+

// A line terminator is a \r (carriage return), \n (line feed), or \r\n. */
LineTerminator = \r|\n|\r\n

/* White space is a line terminator, space, tab, or line feed. */
WhiteSpace     = {LineTerminator} | [ \t\f]


%%

// This section contains regular expressions and actions, i.e. Java code, that will be executed when
// the scanner matches the associated regular expression.


// YYINITIAL is the initial state at which the lexer begins scanning.
<YYINITIAL> {

    /* Create a new parser symbol for the lexem. */
    "_+_"                { return symbol(sym.PLUS); }
    "_-_"                { return symbol(sym.MINUS); }
    "("                { return symbol(sym.LPAREN); }
    ")"                { return symbol(sym.RPAREN); }

    // If a time is found, return the token TIME that represents a time and the value of
    // the time that is held in the string yytext
    {Time}           { return symbol(sym.TIME, yytext()); }

    /* Don't do anything if whitespace is found */
    {WhiteSpace}       { /* do nothing with space */ }
}

// We have changed the default symbol in the bazel `cup()` rule from "sym" to "Calc", so we need to
// change how JFlex handles the end of file.
// See http://jflex.de/manual.html#custom-symbol-interface
<<EOF>>                { return symbol(sym.EOF); }
