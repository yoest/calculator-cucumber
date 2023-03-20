package unit_converter;

public class MassConverter extends MeasureConverter {

    public enum MassUnits implements Unit {

        GRAM(1000),
        KILOGRAM(1),
        TON(0.001),
        POUND(2.20462),
        OUNCE(35.274);

        private final double RATIO;

        /** Units related to mass (grams, kilograms, ...)
         *
         * @param ratio: ratio from the baseline measure to this unit
         */
        MassUnits(double ratio) {
            this.RATIO = ratio;
        }

        @Override
        public String getType() {
            return "Mass";
        }

        @Override
        public double getRatio() {
            return RATIO;
        }
    }

    /** Converter from one mass to another.
     *
     * @param value: current value in a specified unit
     * @param unit: unit of the current value
     */
    public MassConverter(double value, Unit unit) {
        super(value, unit, "Mass");
    }
}

