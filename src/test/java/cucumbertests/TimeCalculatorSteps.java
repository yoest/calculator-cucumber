package cucumbertests;

import calculator.*;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class TimeCalculatorSteps {

	private ArrayList<Expression> params;
	private Operation op;
	private TimeCalculator c;

	@Before
    public void resetMemoryBeforeEachScenario() {
		params = null;
		op = null;
	}

	@Given("I initialise a time calculator")
	public void givenIInitialiseATimeCalculator() {
		c = new TimeCalculator();
	}

	@Given("a time operation {string}")
	public void givenATimeOperation(String s) {
		params = new ArrayList<>(); // create an empty set of parameters to be filled in
		try {
			switch (s) {
				case "+"	->	op = new Plus(params);
				case "-"	->	op = new Minus(params);
				default		->	fail();
			}
		} catch (IllegalConstruction e) {
			fail();
		}
	}

	@Given("the following list of time events representing hours")
	public void givenTheFollowingListOfTime(List<List<String>> numbers) {
		params = new ArrayList<>();
		// Since we only use one line of input, we use get(0) to take the first line of the list,
		// which is a list of strings, that we will manually convert to integers:
		numbers.get(0).forEach(n -> params.add(MyTime.getAsHours(n)));
	    params.forEach(n -> System.out.println("value ="+ n));
		op = null;
	}

	@Given("^the sum of one number of days (\\d+) and a second number of days (\\d+)$")
	public void givenTheSumOfTimes(int n1, int n2) {
		try {
			params = new ArrayList<>();
		    params.add(MyTime.getAsDays(n1));
		    params.add(MyTime.getAsDays(n2));
		    op = new Plus(params);}
		catch(IllegalConstruction e) { fail(); }
	}

	@Then("^for the given times its (.*) notation is (.*)$")
	public void thenItsTimeNotationIs(String notation, String s) {
		if (notation.equals("PREFIX") || notation.equals("POSTFIX") || notation.equals("INFIX")) {
			op.notation = Notation.valueOf(notation);
			assertEquals(s, op.toString());
		}
		else fail(notation + " is not a correct notation! ");
	}

	@When("^I provide a (.*) number of days (\\d+)$")
	public void whenIProvideANumberDays(String s, int nDays) {
		//add extra parameter to the operation
		params = new ArrayList<>();
		params.add(MyTime.getAsDays(nDays));
		op.addMoreParams(params);
	}

	@Then("^for the given times the (.*) is (\\d+)$")
	public void thenTheTimeOperationIs(String s, int val) {
		try {
			switch (s) {
				case "sum"			->	op = new Plus(params);
				case "difference"	->	op = new Minus(params);
				default -> fail();
			}
			assertEquals(val, c.eval(op));
		} catch (IllegalConstruction e) {
			fail();
		}
	}

	@Then("the time operation evaluates to {int}")
	public void thenTheTimeOperationEvaluatesTo(int val) {
		assertEquals(val, c.eval(op));
	}

}
