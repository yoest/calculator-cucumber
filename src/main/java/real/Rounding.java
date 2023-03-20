package real;

import java.math.RoundingMode;

public enum Rounding {
    ROUND_HALF_UP,
    ROUND_HALF_DOWN,
    ROUND_HALF_EVEN,
    ROUND_CEILING,
    ROUND_FLOOR,
    ROUND_UP,
    ROUND_DOWN,
    ROUND_UNNECESSARY;

    public static Rounding fromString(String s) {
        switch (s) {
            case "ROUND_HALF_UP":
                return ROUND_HALF_UP;
            case "ROUND_HALF_DOWN":
                return ROUND_HALF_DOWN;
            case "ROUND_HALF_EVEN":
                return ROUND_HALF_EVEN;
            case "ROUND_CEILING":
                return ROUND_CEILING;
            case "ROUND_FLOOR":
                return ROUND_FLOOR;
            case "ROUND_UP":
                return ROUND_UP;
            case "ROUND_DOWN":
                return ROUND_DOWN;
            case "ROUND_UNNECESSARY":
                return ROUND_UNNECESSARY;
            default:
                return null;
        }
    }

    public RoundingMode toRoundingMode() {
        switch (this) {
            case ROUND_HALF_UP:
                return RoundingMode.HALF_UP;
            case ROUND_HALF_DOWN:
                return RoundingMode.HALF_DOWN;
            case ROUND_HALF_EVEN:
                return RoundingMode.HALF_EVEN;
            case ROUND_CEILING:
                return RoundingMode.CEILING;
            case ROUND_FLOOR:
                return RoundingMode.FLOOR;
            case ROUND_UP:
                return RoundingMode.UP;
            case ROUND_DOWN:
                return RoundingMode.DOWN;
            case ROUND_UNNECESSARY:
                return RoundingMode.UNNECESSARY;
            default:
                return null;
        }
    }
}
