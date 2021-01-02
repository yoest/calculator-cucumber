package calculator;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;

public class CalculatorSteps {

	private ArrayList<Expression> params;
	private Operation op;

	@Given("an arithmetic expression")
	public void givenAnArithmeticExpression() {
		params = new ArrayList<>(); // create an empty set of parameters to be filled in
		op = null; // reset the operation to null before executing each scenario
	}

	@Given("^the sum of two numbers (\\d+) and (\\d+)$")
	public void givenTheSum(int n1, int n2) {
		try {
			params = new ArrayList<>();
		    params.add(new MyNumber(n1));
		    params.add(new MyNumber(n2));
		    op = new Plus(params);}
		catch(IllegalConstruction e) { fail(); }
	}

	@Then("^its (.*) notation is (.*)$")
	public void thenItsNotationIs(String notation, String s) {
		if (notation.equals("PREFIX")||notation.equals("POSTFIX")||notation.equals("INFIX")) {
			op.notation = Notation.valueOf(notation);
			assertEquals(s, op.toString());
		}
		else fail(notation + " is not a correct notation! ");
	}

	@When("^I provide a (.*) number (\\d+)$")
	public void whenIProvideANumber(String s, int val) {
		// The first string s is never used, it is just for allowing for more textual patterns to match the when clause
		params.add(new MyNumber(val));
	}

	@Then("^the sum is (\\d+)$")
	public void thenTheSumIs(int val) {
		try {
			op = new Plus(params);
			assertEquals(Integer.valueOf(val), op.compute());
		} catch (IllegalConstruction e) {
			fail();
		}
	}

	@Then("^the difference is (\\d+)$")
	public void thenTheDifferenceIs(int val) {
		try {
			op = new Minus(params);
			assertEquals(Integer.valueOf(val), op.compute());
		} catch (IllegalConstruction e) {
			fail();
		}
	}

	@Then("^the product is (\\d+)$")
	public void thenTheProductIs(int val) {
		try {
			op = new Times(params);
			assertEquals(Integer.valueOf(val), op.compute());
		} catch (IllegalConstruction e) {
			fail();
		}
	}

	@Then("^the quotient is (\\d+)$")
	public void thenTheQuotientIs(int val) {
		try {
			op = new Divides(params);
			assertEquals(Integer.valueOf(val), op.compute());
		} catch (IllegalConstruction e) {
			fail();
		}
	}

}
