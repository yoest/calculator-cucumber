package calculatorParser;


import calculator.Expression;

public class ParserMain {
    public static void main(String[] argv) throws Exception{
        try {
            parser p = new parser(new lexer(new java.io.StringReader("((1+2)*3)/2*3")));
            Object result = p.parse().value;
            Expression e = (Expression) result;
            System.out.println(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
