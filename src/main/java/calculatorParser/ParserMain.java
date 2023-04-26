package calculatorParser;


import calculator.Calculator;
import calculator.Expression;

public class ParserMain {
    public static void main(String[] argv) throws Exception{
        try {
            parser p = new parser(new lexer(new java.io.StringReader("+ * + 1 2 3 4")));
            Object result = p.parse().value;
            Expression e = (Expression) result;
            Calculator c = new Calculator();
            System.out.println(c.eval(e));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
