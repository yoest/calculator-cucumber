package junit5tests;

//Import Junit5 libraries for unit testing:

import calculator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import unit_converter.CurrencyConverter;
import unit_converter.LengthConverter;
import unit_converter.TemperatureConverter;
import unit_converter.TimeConverter;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestConverter {

    @Test
    void testLengthConverter() {
        LengthConverter converter = new LengthConverter(1, LengthConverter.LengthUnits.METER);

        assertEquals(converter.getAs(LengthConverter.LengthUnits.CENTIMETER), 100);
        assertEquals(converter.getAs(LengthConverter.LengthUnits.INCHES), 39.3701);
        assertEquals(converter.getAs(LengthConverter.LengthUnits.YARDS), 1.09361);

        converter.setAs(1000, LengthConverter.LengthUnits.METER);

        assertEquals(converter.getAs(LengthConverter.LengthUnits.MILES), 0.621371);
        assertEquals(converter.getAs(LengthConverter.LengthUnits.NAUTICAL_MILES), 0.539957);
    }

    @Test
    void testCurrencyConverter() {
        CurrencyConverter converter = new CurrencyConverter(1, CurrencyConverter.CurrencyUnits.EURO);

        assertEquals(converter.getAs(CurrencyConverter.CurrencyUnits.DOLLARS), 1.08);
        assertEquals(converter.getAs(CurrencyConverter.CurrencyUnits.YEN), 142.10);

        converter.setAs(1000, CurrencyConverter.CurrencyUnits.EURO);

        assertEquals(converter.getAs(CurrencyConverter.CurrencyUnits.BRITISH_POUND), 880);
    }

    @Test
    void testTemperatureConverter() {
        TemperatureConverter converter = new TemperatureConverter(1, TemperatureConverter.TemperatureUnits.CELSIUS);

        assertEquals(converter.getAs(TemperatureConverter.TemperatureUnits.KELVIN), 274.15);
        assertEquals(converter.getAs(TemperatureConverter.TemperatureUnits.FAHRENHEIT), 33.8);

        converter.setAs(100, TemperatureConverter.TemperatureUnits.CELSIUS);

        assertEquals(converter.getAs(TemperatureConverter.TemperatureUnits.KELVIN), 373.15);
        assertEquals(converter.getAs(TemperatureConverter.TemperatureUnits.FAHRENHEIT), 212);
    }

    @Test
    void testTimeConverter() {
        TimeConverter converter = new TimeConverter(1, TimeConverter.TimeUnits.HOUR);

        assertEquals(converter.getAs(TimeConverter.TimeUnits.MINUTE), 60);
        assertEquals(converter.getAs(TimeConverter.TimeUnits.SECOND), 3600);
        assertEquals(converter.getAs(TimeConverter.TimeUnits.DAY), 1 / 24.);

        converter.setAs(24, TimeConverter.TimeUnits.HOUR);

        assertEquals(converter.getAs(TimeConverter.TimeUnits.MINUTE), 1440);
        assertEquals(converter.getAs(TimeConverter.TimeUnits.SECOND), 86400);
        assertEquals(converter.getAs(TimeConverter.TimeUnits.DAY), 1);
    }

    @Test
    void testWrongUnits() {
        assertThrows(RuntimeException.class, () -> new LengthConverter(1, CurrencyConverter.CurrencyUnits.EURO));
        assertThrows(RuntimeException.class, () -> new CurrencyConverter(1, LengthConverter.LengthUnits.METER));
        assertThrows(RuntimeException.class, () -> new TemperatureConverter(1, LengthConverter.LengthUnits.METER));
        assertThrows(RuntimeException.class, () -> new TimeConverter(1, LengthConverter.LengthUnits.METER));
    }
}
