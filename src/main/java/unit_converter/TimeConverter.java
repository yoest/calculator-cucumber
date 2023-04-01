package unit_converter;

public class TimeConverter  extends MeasureConverter {

    public enum TimeUnits implements Unit {

        SECOND(3600),
        MINUTE(60),
        HOUR(1),
        DAY(1 / 24.);

        private final double RATIO;

        /** Units related to time (seconds, minutes, ...)
         *
         * @param ratio: ratio from the baseline measure to this unit
         */
        TimeUnits(double ratio) {
            this.RATIO = ratio;
        }

        @Override
        public String getType() {
            return "Time";
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
    public TimeConverter(double value, Unit unit) {
        super(value, unit, "Time");
    }

}
