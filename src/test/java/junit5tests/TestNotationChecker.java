package junit5tests;
import org.junit.jupiter.api.Test;

import calculator.*;
import calculatorParser.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestNotationChecker {
    Expression exp;


    @Test
    /**
     * Test that the expression (4+5)*6 is in infix notation
     */
    void testInfix() throws IllegalConstruction {
        //create the expression (4+5)*6
        Plus plus = new Plus(
                new ArrayList<>(Arrays.asList(
                        new MyNumber(4), new MyNumber(5)))
        );
        exp = new Times(
                new ArrayList<>(
                        Arrays.asList(
                                plus,
                                new MyNumber(6)
                        )
                )
        );

        //check that the expression is in infix notation
        assertTrue(NotationChecker.isInfix(exp));
        assertFalse(NotationChecker.isPrefix(exp));
        assertFalse(NotationChecker.isPostfix(exp));

        //print the expression in infix notation
        assertEquals("( ( 4 + 5 ) * 6 )", exp.toString());
    }

    @Test
    /**
     * Test that the expression (4+5)*6 is in prefix notation
     */
    void testPrefix() throws IllegalConstruction {
        //create the expression (4+5)*6
        Plus plus = new Plus(
                new ArrayList<>(Arrays.asList(
                        new MyNumber(4), new MyNumber(5))),
                Notation.PREFIX
        );
        exp = new Times(
                new ArrayList<>(
                        Arrays.asList(
                                plus,
                                new MyNumber(6)
                        )
                ),
                Notation.PREFIX
        );

        //check that the expression is in prefix notation
        assertFalse(NotationChecker.isInfix(exp));
        assertTrue(NotationChecker.isPrefix(exp));
        assertFalse(NotationChecker.isPostfix(exp));

        //print the expression in prefix notation
        assertEquals("* (+ (4, 5), 6)", exp.toString());
    }

    @Test
    /**
     * Test that the expression (4+5)*6 is in postfix notation
     */
    void testPostfix() throws IllegalConstruction {
        //create the expression (4+5)*6
        Plus plus = new Plus(
                new ArrayList<>(Arrays.asList(
                        new MyNumber(4), new MyNumber(5))),
                Notation.POSTFIX
        );
        exp = new Times(
                new ArrayList<>(
                        Arrays.asList(
                                plus,
                                new MyNumber(6)
                        )
                ),
                Notation.POSTFIX
        );

        //check that the expression is in postfix notation
        assertFalse(NotationChecker.isInfix(exp));
        assertFalse(NotationChecker.isPrefix(exp));
        assertTrue(NotationChecker.isPostfix(exp));

        //print the expression in postfix notation
        assertEquals("((4, 5) +, 6) *", exp.toString());
    }

    @Test
    /**
     * Test that the expression (4+5)*6 cannot contain multiple notations (error thrown during construction of expression)
     */
    void testMultiNotations() throws IllegalConstruction {
        //create the expression (4+5)*6
        Plus plus1 = new Plus(
                new ArrayList<>(Arrays.asList(
                        new MyNumber(4), new MyNumber(5))),
                Notation.POSTFIX
        );
        //verify there is an exception
        assertThrows(IllegalConstruction.class, () -> {
            exp = new Times(
                    new ArrayList<>(
                            Arrays.asList(
                                    plus1,
                                    new MyNumber(6)
                            )
                    ),
                    Notation.INFIX
            );
        });

        Plus plus2 = new Plus(
                new ArrayList<>(Arrays.asList(
                        new MyNumber(4), new MyNumber(5))),
                Notation.PREFIX
        );
        assertThrows(IllegalConstruction.class, () -> {
            exp = new Times(
                    new ArrayList<>(
                            Arrays.asList(
                                    plus2,
                                    new MyNumber(6)
                            )
                    ),
                    Notation.INFIX
            );
        });

        Plus plus3 = new Plus(
                new ArrayList<>(Arrays.asList(
                        new MyNumber(4), new MyNumber(5))),
                Notation.INFIX
        );
        assertThrows(IllegalConstruction.class, () -> {
            exp = new Times(
                    new ArrayList<>(
                            Arrays.asList(
                                    plus3,
                                    new MyNumber(6)
                            )
                    ),
                    Notation.PREFIX
            );
        });

        Plus plus4 = new Plus(
                new ArrayList<>(Arrays.asList(
                        new MyNumber(4), new MyNumber(5))),
                Notation.INFIX
        );
        assertThrows(IllegalConstruction.class, () -> {
            exp = new Times(
                    new ArrayList<>(
                            Arrays.asList(
                                    plus4,
                                    new MyNumber(6)
                            )
                    ),
                    Notation.POSTFIX
            );
        });

        Plus plus5 = new Plus(
                new ArrayList<>(Arrays.asList(
                        new MyNumber(4), new MyNumber(5))),
                Notation.POSTFIX
        );
        assertThrows(IllegalConstruction.class, () -> {
            exp = new Times(
                    new ArrayList<>(
                            Arrays.asList(
                                    plus5,
                                    new MyNumber(6)
                            )
                    ),
                    Notation.PREFIX
            );
        });

        Plus plus6 = new Plus(
                new ArrayList<>(Arrays.asList(
                        new MyNumber(4), new MyNumber(5))),
                Notation.PREFIX
        );
        assertThrows(IllegalConstruction.class, () -> {
            exp = new Times(
                    new ArrayList<>(
                            Arrays.asList(
                                    plus6,
                                    new MyNumber(6)
                            )
                    ),
                    Notation.POSTFIX
            );
        });
    }
}
