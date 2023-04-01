package unit_converter;

public interface Unit {

    /** Get the type of the unit. This is used to be sure that nobody use the units of two
     *  different things (e.g. dollars to meters).
     *
     * @return type of the converter
     */
    String getType();

    /** Get the ratio of this unit (e.g. the ratio from meter to centimeter is 100 because 1 meter is equal
     *  to 100 centimeters).
     *
     * @return ratio from the baseline to one unit
     */
    double getRatio();
}
