package calculatorParser;
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

// A string number is a sequence of digits.
Number         = [0-9]+

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
    "+"                { return symbol(sym.PLUS); }
    "*"                { return symbol(sym.TIMES); }
    "-"                { return symbol(sym.MINUS); }
    "/"                { return symbol(sym.DIVIDE); }
    "%"                { return symbol(sym.MODULO); }
    "%-1"              { return symbol(sym.INVERSEMODULO); }
    "("                { return symbol(sym.LPAREN); }
    ")"                { return symbol(sym.RPAREN); }

    // If an integer is found, return the token NUMBER that represents an integer and the value of
    // the integer that is held in the string yytext
    {Number}           { return symbol(sym.NUMBER, yytext()); }

    /* Don't do anything if whitespace is found */
    {WhiteSpace}       { /* do nothing with space */ }
}

// We have changed the default symbol in the bazel `cup()` rule from "sym" to "Calc", so we need to
// change how JFlex handles the end of file.
// See http://jflex.de/manual.html#custom-symbol-interface
<<EOF>>                { return symbol(sym.EOF); }
