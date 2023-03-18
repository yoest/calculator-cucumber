package unit_converter;

public class CurrencyConverter extends MeasureConverter {

    public enum CurrencyUnits implements Unit {

        EURO(1),
        DOLLARS(1.08),
        YEN(142.10),
        BRITISH_POUND(0.88),
        SWISS_FRANC(0.99);

        private final double RATIO;

        /** Units related to currency (euro, dollar, ...)
         *
         * @param ratio: ratio from the baseline measure to this unit
         */
        CurrencyUnits(double ratio) {
            this.RATIO = ratio;
        }

        @Override
        public String getType() {
            return "Currency";
        }

        @Override
        public double getRatio() {
            return RATIO;
        }
    }

    /** Converter from one currency to another.
     *
     * @param value: current value in a specified unit
     * @param unit: unit of the current value
     */
    public CurrencyConverter(double value, Unit unit) {
        super(value, unit, "Currency");
    }
}
