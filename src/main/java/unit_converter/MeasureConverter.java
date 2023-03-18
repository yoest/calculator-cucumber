package unit_converter;

public abstract class MeasureConverter {

    protected double value;
    protected final String TYPE;

    /** General class representing a converter for a specific measure.
     *
     * @param value: value to converter to another unit
     * @param unit: current unit of the value
     * @param type: type of the converter (i.e. the specific measure name such as 'Length', 'Currency', ...)
     */
    public MeasureConverter(double value, Unit unit, String type) {
        this.TYPE = type;
        this.checkCondition(unit);
        this.value = value / unit.getRatio();
    }

    /** Get the current value as a specific unit.
     *
     * @param unit: unit in which to convert the value
     * @return the converted value
     */
    public double getAs(Unit unit) {
        this.checkCondition(unit);
        return this.value * unit.getRatio();
    }

    /** Set a new value in a specified unit.
     *
     * @param value: new value to set as the current value
     * @param unit: unit of the value
     */
    public void setAs(double value, Unit unit) {
        this.checkCondition(unit);
        this.value = value / unit.getRatio();
    }

    /** Check if the asked unit is similar to the measure to be sure that nobody use the units of two
     *  different things (e.g. dollars to meters).
     *
     * @param unit: requested unit
     */
    protected void checkCondition(Unit unit) {
        if(!unit.getType().equals(this.TYPE))
            throw new RuntimeException("This unit measure should be '" + this.TYPE + "' and not " + unit.getType());
    }
}
