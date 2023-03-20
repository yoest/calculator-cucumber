package cucumbertests;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import unit_converter.*;

import static junit.framework.TestCase.assertEquals;


public class ConverterSteps {

    private double value;
    private String converterType;
    private MeasureConverter converter;
    private double result;

    @Before
    public void resetMemoryBeforeEachScenario() {
        converter = null;
        converterType = null;
    }

    @Given("I initialise a value at 1")
    public void givenIInitialiseAValue() {
        value = 1;
    }

    @Given("a type of converter {string} and a first unit {string}")
    public void givenAnTypeOfConverter(String s, String unit) {
        converterType = s;

        switch (converterType) {
            case "Length" -> converter = new LengthConverter(value, LengthConverter.LengthUnits.valueOf(unit));
            case "Currency" -> converter = new CurrencyConverter(value, CurrencyConverter.CurrencyUnits.valueOf(unit));
            case "Temperature" -> converter = new TemperatureConverter(value, TemperatureConverter.TemperatureUnits.valueOf(unit));
            case "Time" -> converter = new TimeConverter(value, TimeConverter.TimeUnits.valueOf(unit));
            case "Energy" -> converter = new EnergyConverter(value, EnergyConverter.EnergyUnits.valueOf(unit));
            case "Mass" -> converter = new MassConverter(value, MassConverter.MassUnits.valueOf(unit));
        }
    }

    @When("I provide a second unit {string}")
    public void whenIProvideASecondUnit(String unit) {
        switch (converterType) {
            case "Length" -> result = converter.getAs(LengthConverter.LengthUnits.valueOf(unit));
            case "Currency" -> result = converter.getAs(CurrencyConverter.CurrencyUnits.valueOf(unit));
            case "Temperature" -> result = converter.getAs(TemperatureConverter.TemperatureUnits.valueOf(unit));
            case "Time" -> result = converter.getAs(TimeConverter.TimeUnits.valueOf(unit));
            case "Energy" -> result = converter.getAs(EnergyConverter.EnergyUnits.valueOf(unit));
            case "Mass" -> result = converter.getAs(MassConverter.MassUnits.valueOf(unit));
        }
    }

    @Then("the unit conversion evaluates to {double}")
    public void thenTheOperationEvaluatesTo(double val) {
        assertEquals(val, result);
    }
}
