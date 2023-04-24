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
        exp = new Times(
                new ArrayList<>(
                        Arrays.asList(
                                new Plus(
                                        new ArrayList<>(Arrays.asList(
                                                new MyNumber(4), new MyNumber(5)))
                                ),
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
        exp = new Times(
                new ArrayList<>(
                        Arrays.asList(
                                new Plus(
                                        new ArrayList<>(Arrays.asList(
                                                new MyNumber(4), new MyNumber(5))),
                                        Notation.PREFIX
                                ),
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
        exp = new Times(
                new ArrayList<>(
                        Arrays.asList(
                                new Plus(
                                        new ArrayList<>(Arrays.asList(
                                                new MyNumber(4), new MyNumber(5))),
                                        Notation.POSTFIX
                                ),
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
        //verify there is an exception
        assertThrows(IllegalConstruction.class, () -> {
            exp = new Times(
                    new ArrayList<>(
                            Arrays.asList(
                                    new Plus(
                                            new ArrayList<>(Arrays.asList(
                                                    new MyNumber(4), new MyNumber(5))),
                                            Notation.POSTFIX
                                    ),
                                    new MyNumber(6)
                            )
                    ),
                    Notation.INFIX
            );
        });

        assertThrows(IllegalConstruction.class, () -> {
            exp = new Times(
                    new ArrayList<>(
                            Arrays.asList(
                                    new Plus(
                                            new ArrayList<>(Arrays.asList(
                                                    new MyNumber(4), new MyNumber(5))),
                                            Notation.PREFIX
                                    ),
                                    new MyNumber(6)
                            )
                    ),
                    Notation.INFIX
            );
        });

        assertThrows(IllegalConstruction.class, () -> {
            exp = new Times(
                    new ArrayList<>(
                            Arrays.asList(
                                    new Plus(
                                            new ArrayList<>(Arrays.asList(
                                                    new MyNumber(4), new MyNumber(5))),
                                            Notation.INFIX
                                    ),
                                    new MyNumber(6)
                            )
                    ),
                    Notation.PREFIX
            );
        });

        assertThrows(IllegalConstruction.class, () -> {
            exp = new Times(
                    new ArrayList<>(
                            Arrays.asList(
                                    new Plus(
                                            new ArrayList<>(Arrays.asList(
                                                    new MyNumber(4), new MyNumber(5))),
                                            Notation.INFIX
                                    ),
                                    new MyNumber(6)
                            )
                    ),
                    Notation.POSTFIX
            );
        });

        assertThrows(IllegalConstruction.class, () -> {
            exp = new Times(
                    new ArrayList<>(
                            Arrays.asList(
                                    new Plus(
                                            new ArrayList<>(Arrays.asList(
                                                    new MyNumber(4), new MyNumber(5))),
                                            Notation.POSTFIX
                                    ),
                                    new MyNumber(6)
                            )
                    ),
                    Notation.PREFIX
            );
        });

        assertThrows(IllegalConstruction.class, () -> {
            exp = new Times(
                    new ArrayList<>(
                            Arrays.asList(
                                    new Plus(
                                            new ArrayList<>(Arrays.asList(
                                                    new MyNumber(4), new MyNumber(5))),
                                            Notation.PREFIX
                                    ),
                                    new MyNumber(6)
                            )
                    ),
                    Notation.POSTFIX
            );
        });
    }
}
