package unit_converter;

public class EnergyConverter extends MeasureConverter {

    public enum EnergyUnits implements Unit {

        JOULE(1),
        KILO_JOULE(1 / 1000.),
        MEGA_JOULE(1 / 1000000.),
        WATT_HOUR(1 / 3600.),
        KILOWATT_HOUR(1 / 3600000.),
        CALORIE(1 / 4184.);

        private final double RATIO;

        /** Units related to energy (joules, watts, ...)
         *
         * @param ratio: ratio from the baseline measure to this unit
         */
        EnergyUnits(double ratio) {
            this.RATIO = ratio;
        }

        @Override
        public String getType() {
            return "Energy";
        }

        @Override
        public double getRatio() {
            return RATIO;
        }
    }

    /** Converter from one energy to another.
     *
     * @param value: current value in a specified unit
     * @param unit: unit of the current value
     */
    public EnergyConverter(double value, Unit unit) {
        super(value, unit, "Energy");
    }
}
