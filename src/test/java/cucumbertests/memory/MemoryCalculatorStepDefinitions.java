package cucumbertests.memory;

import io.cucumber.java.en.And;
import memory.*;
import calculator.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class MemoryCalculatorStepDefinitions {

    private MemoryCalculator calc;
    private Caretaker caretaker;
    private Expression expression;
    private List<Snapshot> history;

    @BeforeEach
    public void setUp() {
        calc = new MemoryCalculator();
        caretaker = new Caretaker();
    }

    @Given("I initialise a MemoryCalculator")
    public void i_initialize_a_MemoryCalculator() {
        calc = new MemoryCalculator();
    }

    @Given("^I have an expression with value (\\d+)$")
    public void i_have_an_expression_with_value(int value) {
        expression = new MyNumber(value);
    }

    @When("^I evaluate the expression$")
    public void i_evaluate_the_expression() {
        calc.eval(expression);
    }

    @Then("^the expression is saved in the memory$")
    public void the_expression_is_saved_in_the_memory() {
        assertEquals(expression, calc.load());
    }

    @When("I undo the last expression")
    public void undoLastExpression() {
        calc.undo();
    }
    @When("I redo the last expression")
    public void redoLastExpression() {
        calc.redo();
    }

    @Then("the loaded expression should be (\\d+)$")
    public void checkLoadedExpression(int expected) throws IOException {
        Expression loaded = calc.load();
        Expression expectedExpr = new MyNumber(expected);
        assertEquals(expectedExpr, loaded);
    }
    /* Scenario: Save the history of expressions in a file
    Given I have an expression with value 2
    And I evaluate the expression
    And I have an expression with value 3
    And I evaluate the expression
    And I save the history of expressions in a file
    Then the file should contain the following expressions:
      | 2 |
      | 3 |*/
    @Given("I save the history of expressions in a file")
    public void saveHistory() throws IOException {
        calc.getHistory();
    }

    @Then("the file should contain the following expressions:")
    public void checkHistory(List<String> expected) throws IOException {
        List<String> actual = new ArrayList<>();
        for (Snapshot s : calc.getHistory()) {
            actual.add(s.getExpression().toString());
        }
        assertEquals(expected, actual);
    }
}
