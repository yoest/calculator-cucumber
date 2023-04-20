package junit5tests;

import calculator.*;
import memory.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class MemoryCalculatorTest {
    private MemoryCalculator calc;
    private Caretaker caretaker;

    @BeforeEach
    void setUp() {
        calc = new MemoryCalculator(500);
        caretaker = new Caretaker();
    }

    @Test
    void eval() {
        // Simple evaluation
        Expression e = new MyNumber(2);
        //create a 2 big integer
        BigInteger two = new BigInteger("2");

        assertEquals(two, calc.eval(e));
        // Evaluation with a plus
        List<Expression> params_ = new ArrayList<>();
        Collections.addAll(params_, new MyNumber(3), new MyNumber(4));
        try {
            Expression f = new Plus(params_, Notation.PREFIX);
            assertEquals(new BigInteger("7"), calc.eval(f));
        } catch (IllegalConstruction ex) {
            throw new RuntimeException(ex);
        }
    }

    @Test
        // Test the load save method
    void saveAndload() throws IOException {
        Expression e = new MyNumber(2);
        calc.save(e);
        Expression loaded = calc.load();
        assertEquals(e, loaded);
    }

    @Test
    void getHistory() {
        caretaker.clearExpressions();
        Expression e1 = new MyNumber(2);
        Expression e2 = new MyNumber(3);
        calc.eval(e1);
        calc.eval(e2);
        List<Snapshot> history = calc.getHistory();
        assertEquals(2, history.size());
        assertEquals(e1, history.get(0).getExpression());
        assertEquals(e2, history.get(1).getExpression());
    }

    @Test
    void undo() throws IOException {
        Expression e1 = new MyNumber(2);
        Expression e2 = new MyNumber(3);
        calc.eval(e1);
        calc.eval(e2);
        calc.undo();
        Expression loaded = calc.load();
        assertEquals(e1, loaded);
    }

    @Test
    void redo() throws IOException {
        Expression e1 = new MyNumber(2);
        Expression e2 = new MyNumber(3);
        calc.eval(e1);
        calc.eval(e2);
        calc.undo();
        calc.redo();
        Expression loaded = calc.load();
        assertEquals(e2, loaded);
    }
}
