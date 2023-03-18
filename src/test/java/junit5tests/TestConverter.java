package junit5tests;

//Import Junit5 libraries for unit testing:

import calculator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import unit_converter.CurrencyConverter;
import unit_converter.LengthConverter;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
