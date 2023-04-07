package calculatorParser;


public class ParserMain {
    public static void main(String[] argv) throws Exception{
        try {
            parser p = new parser(new lexer(new java.io.StringReader("1+2*3")));
            Object result = p.parse().value;
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
