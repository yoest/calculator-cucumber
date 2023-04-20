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
// Number can be any number in radix 1 to 36 or can be decimal number
Number     = [0-9]+|[0-9a-zA-Z]+|[0-9]+[.][0-9]+

LineTerminator = \r|\n|\r\n

WhiteSpace     = {LineTerminator} | [ \t\f]

%%

<YYINITIAL> {

    "+"                { return symbol(sym.PLUS); }
    "*"                { return symbol(sym.TIMES); }
    "-"                { return symbol(sym.MINUS); }
    "/"                { return symbol(sym.DIVIDE); }
    "%"                { return symbol(sym.MODULO); }
    "$"              { return symbol(sym.INVERSEMODULO); }
    "("                { return symbol(sym.LPAREN); }
    ")"                { return symbol(sym.RPAREN); }


    {Number}           { return symbol(sym.NUMBER, yytext()); }

    {WhiteSpace}       { /* do nothing with space */ }
}

<<EOF>>                { return symbol(sym.EOF); }
