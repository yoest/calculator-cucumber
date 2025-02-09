package real;

import java.math.RoundingMode;

/**
 * All the rounding modes supported by the calculator.
 */
public enum Rounding {
    ROUND_HALF_UP,
    ROUND_HALF_DOWN,
    ROUND_HALF_EVEN,
    ROUND_CEILING,
    ROUND_FLOOR,
    ROUND_UP,
    ROUND_DOWN,
    ROUND_UNNECESSARY;


    /**
     * Converts the Rounding enum to the RoundingMode enum (java.math.RoundingMode).
     * @return the RoundingMode enum.
     */
    public RoundingMode toRoundingMode() {
        return switch (this) {
            case ROUND_HALF_UP -> RoundingMode.HALF_UP;
            case ROUND_HALF_DOWN -> RoundingMode.HALF_DOWN;
            case ROUND_HALF_EVEN -> RoundingMode.HALF_EVEN;
            case ROUND_CEILING -> RoundingMode.CEILING;
            case ROUND_FLOOR -> RoundingMode.FLOOR;
            case ROUND_UP -> RoundingMode.UP;
            case ROUND_DOWN -> RoundingMode.DOWN;
            case ROUND_UNNECESSARY -> RoundingMode.UNNECESSARY;
            default -> null;
        };
    }

}
