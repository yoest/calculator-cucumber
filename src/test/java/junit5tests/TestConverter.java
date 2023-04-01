package junit5tests;

//Import Junit5 libraries for unit testing:

import org.junit.jupiter.api.Test;
import unit_converter.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestConverter {

    private final double DELTA = 0.0001;

    @Test
    void testLengthConverter() {
        LengthConverter converter = new LengthConverter(1, LengthConverter.LengthUnits.METER);

        assertEquals(converter.getAs(LengthConverter.LengthUnits.CENTIMETER), 100, DELTA);
        assertEquals(converter.getAs(LengthConverter.LengthUnits.INCH), 39.3701, DELTA);
        assertEquals(converter.getAs(LengthConverter.LengthUnits.YARD), 1.09361, DELTA);

        converter.setAs(1000, LengthConverter.LengthUnits.METER);

        assertEquals(converter.getAs(LengthConverter.LengthUnits.MILE), 0.621371, DELTA);
        assertEquals(converter.getAs(LengthConverter.LengthUnits.NAUTICAL_MILE), 0.539957, DELTA);
    }

    @Test
    void testCurrencyConverter() {
        CurrencyConverter converter = new CurrencyConverter(1, CurrencyConverter.CurrencyUnits.EURO);

        assertEquals(converter.getAs(CurrencyConverter.CurrencyUnits.DOLLAR), 1.08, DELTA);
        assertEquals(converter.getAs(CurrencyConverter.CurrencyUnits.YEN), 142.10, DELTA);

        converter.setAs(1000, CurrencyConverter.CurrencyUnits.EURO);

        assertEquals(converter.getAs(CurrencyConverter.CurrencyUnits.BRITISH_POUND), 880, DELTA);
    }

    @Test
    void testTemperatureConverter() {
        TemperatureConverter converter = new TemperatureConverter(1, TemperatureConverter.TemperatureUnits.CELSIUS);

        assertEquals(converter.getAs(TemperatureConverter.TemperatureUnits.KELVIN), 274.15, DELTA);
        assertEquals(converter.getAs(TemperatureConverter.TemperatureUnits.FAHRENHEIT), 33.8, DELTA);

        converter.setAs(100, TemperatureConverter.TemperatureUnits.CELSIUS);

        assertEquals(converter.getAs(TemperatureConverter.TemperatureUnits.KELVIN), 373.15, DELTA);
        assertEquals(converter.getAs(TemperatureConverter.TemperatureUnits.FAHRENHEIT), 212, DELTA);
    }

    @Test
    void testTimeConverter() {
        TimeConverter converter = new TimeConverter(1, TimeConverter.TimeUnits.HOUR);

        assertEquals(converter.getAs(TimeConverter.TimeUnits.MINUTE), 60, DELTA);
        assertEquals(converter.getAs(TimeConverter.TimeUnits.SECOND), 3600, DELTA);
        assertEquals(converter.getAs(TimeConverter.TimeUnits.DAY), 1 / 24., DELTA);

        converter.setAs(24, TimeConverter.TimeUnits.HOUR);

        assertEquals(converter.getAs(TimeConverter.TimeUnits.MINUTE), 1440, DELTA);
        assertEquals(converter.getAs(TimeConverter.TimeUnits.SECOND), 86400, DELTA);
        assertEquals(converter.getAs(TimeConverter.TimeUnits.DAY), 1, DELTA);
    }

    @Test
    void testEnergyConverter() {
        EnergyConverter converter = new EnergyConverter(1, EnergyConverter.EnergyUnits.JOULE);

        assertEquals(converter.getAs(EnergyConverter.EnergyUnits.KILO_JOULE), 0.001, DELTA);
        assertEquals(converter.getAs(EnergyConverter.EnergyUnits.WATT_HOUR), 1 / 3600., DELTA);
        assertEquals(converter.getAs(EnergyConverter.EnergyUnits.CALORIE), 1 / 4184., DELTA);

        converter.setAs(1000000, EnergyConverter.EnergyUnits.JOULE);

        assertEquals(converter.getAs(EnergyConverter.EnergyUnits.MEGA_JOULE), 1, DELTA);
        assertEquals(converter.getAs(EnergyConverter.EnergyUnits.KILOWATT_HOUR), 10 / 36., DELTA);
    }

    @Test
    void testMassConverter() {
        MassConverter converter = new MassConverter(1, MassConverter.MassUnits.KILOGRAM);

        assertEquals(converter.getAs(MassConverter.MassUnits.GRAM), 1000, DELTA);
        assertEquals(converter.getAs(MassConverter.MassUnits.OUNCE), 35.274, DELTA);

        converter.setAs(1000, MassConverter.MassUnits.KILOGRAM);

        assertEquals(converter.getAs(MassConverter.MassUnits.TON), 1, DELTA);
        assertEquals(converter.getAs(MassConverter.MassUnits.POUND), 2204.62, DELTA);
        assertEquals(converter.getAs(MassConverter.MassUnits.OUNCE), 35274, DELTA);
    }

    @Test
    void testWrongUnits() {
        assertThrows(RuntimeException.class, () -> new LengthConverter(1, CurrencyConverter.CurrencyUnits.EURO));
        assertThrows(RuntimeException.class, () -> new CurrencyConverter(1, LengthConverter.LengthUnits.METER));
        assertThrows(RuntimeException.class, () -> new TemperatureConverter(1, LengthConverter.LengthUnits.METER));
        assertThrows(RuntimeException.class, () -> new TimeConverter(1, LengthConverter.LengthUnits.METER));
        assertThrows(RuntimeException.class, () -> new EnergyConverter(1, LengthConverter.LengthUnits.METER));
        assertThrows(RuntimeException.class, () -> new MassConverter(1, LengthConverter.LengthUnits.METER));
    }
}
