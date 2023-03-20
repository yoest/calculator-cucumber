package unit_converter;

public class LengthConverter extends MeasureConverter {

    public enum LengthUnits implements Unit {

        MILLIMETER(1000),
        CENTIMETER(100),
        DECIMETER(10),
        METER(1),
        KILOMETER(0.001),
        FEET(3.28084),
        INCH(39.3701),
        MILE(0.000621371),
        NAUTICAL_MILE(0.000539957),
        YARD(1.09361);

        private final double RATIO;

        /** Units related to length (meter, centimeter, ...)
         *
         * @param ratio: ratio from the baseline measure to this unit
         */
        LengthUnits(double ratio) {
            this.RATIO = ratio;
        }

        @Override
        public String getType() {
            return "Length";
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
    public LengthConverter(double value, Unit unit) {
        super(value, unit, "Length");
    }
}
