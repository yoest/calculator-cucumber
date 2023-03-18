package unit_converter;

public class TemperatureConverter extends MeasureConverter {

    public enum TemperatureUnits implements Unit {

        CELSIUS(1, 0),
        FAHRENHEIT(9/5., 32),
        KELVIN(1, 273.15);

        private final double RATIO;
        private final double ADDONS;

        /** Units related to temperature (degree, kelvin, ...)
         *
         * @param ratio: ratio from the baseline measure to this unit
         * @param addons: value to add to obtain the conversion (specific for temperature)
         */
        TemperatureUnits(double ratio, double addons) {
            this.RATIO = ratio;
            this.ADDONS = addons;
        }

        @Override
        public String getType() {
            return "Temperature";
        }

        @Override
        public double getRatio() {
            return RATIO;
        }
    }

    /** Converter from one length to another.
     *
     * @param value: current value in a specified unit
     * @param unit: unit of the current value
     */
    public TemperatureConverter(double value, Unit unit) {
        super(value, unit, "Temperature");
    }

    @Override
    public double getAs(Unit unit) {
        super.checkCondition(unit);
        return value * unit.getRatio() + ((TemperatureUnits) unit).ADDONS;
    }

    @Override
    public void setAs(double value, Unit unit) {
        super.checkCondition(unit);
        this.value = (value - ((TemperatureUnits) unit).ADDONS) / unit.getRatio();
    }
}
