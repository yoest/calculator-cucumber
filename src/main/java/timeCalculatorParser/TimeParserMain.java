package timeCalculatorParser;


import calculator.Expression;
import calculator.TimeCalculator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeParserMain {
    public static void main(String[] argv) throws Exception{
        /*
        try {
            String selectedText = "2020-03-03 +02:00 AM_+_10:20";

            // Handle unary request
            if(selectedText.length() > 0) {
                String first = selectedText.substring(0, 3);

                boolean firstIsOperator = first.equals("_+_") || first.equals("_-_");
                boolean singleParameter = !selectedText.contains(" ");
                if(firstIsOperator && singleParameter) {
                    String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss", Locale.UK));
                    selectedText = currentDate + selectedText;
                }
            }
            Pattern spaceInDatePattern = Pattern.compile("(\\d{4}-\\d{2}-\\d{2} )|(\\d A)|(\\d P)");
            Matcher matcher = spaceInDatePattern.matcher(selectedText);
            while(matcher.find()) {
                String match = matcher.group(0);
                String replacement = match.replace(" ", "=");
                selectedText = selectedText.replace(match, replacement);

            }

            System.out.println(selectedText);


            parser p = new parser(new lexer(new java.io.StringReader(selectedText)));
            Object result = p.parse().value;
            Expression e = (Expression) result;
            TimeCalculator c = new TimeCalculator();
            c.print(e);
            long res = c.eval(e);
            System.out.println(c.getResultAsCompleteString(res));
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }
}
