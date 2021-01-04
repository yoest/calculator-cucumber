package calculator;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

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
		params.add(new MyNumber(val));
	}

	@Then("the sum is {int}")
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
