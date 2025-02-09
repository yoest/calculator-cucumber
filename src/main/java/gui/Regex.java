package gui;

/**
 * Regex class contains all the regex used in the program.
 * There are regex for decimal, time, and integer of different bases.
 */
public class Regex {

    public static final String DECIMAL = "[0-9\\|%$+\\-*/()\\s\\.eE]*";

    public static final String TIME = "[0-9\\|.eEX%$+\\-*/()\\s:AMP]*";
    public static final String INTEGER_BASE_2 = "[01\\|%$+\\-/()\\s\\*]*";
    public static final String INTEGER_BASE_3 = "[0-2\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_4 = "[0-3\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_5 = "[0-4\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_6 = "[0-5\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_7 = "[0-6\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_8 = "[0-7\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_9 = "[0-8\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_10 = "[0-9\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_11 = "[0-9Aa\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_12 = "[0-9A-Ba-b\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_13 = "[0-9A-Ca-c\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_14 = "[0-9A-Da-d\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_15 = "[0-9A-Ea-e\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_16 = "[0-9A-Fa-f\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_17 = "[0-9A-Ga-g\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_18 = "[0-9A-Ha-h\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_19 = "[0-9A-Ia-i\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_20 = "[0-9A-Ja-j\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_21 = "[0-9A-Ka-k\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_22 = "[0-9A-La-l\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_23 = "[0-9A-Ma-m\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_24 = "[0-9A-Na-n\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_25 = "[0-9A-Oa-o\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_26 = "[0-9A-Pa-p\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_27 = "[0-9A-Qa-q\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_28 = "[0-9A-Ra-r\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_29 = "[0-9A-Sa-s\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_30 = "[0-9A-Ta-t\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_31 = "[0-9A-Ua-u\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_32 = "[0-9A-Va-v\\|%$+\\-/()\\s\\*]*";

    public  static  final String INTEGER_BASE_33 = "[0-9A-Wa-w\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_34 = "[0-9A-Xa-x\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_35 = "[0-9A-Ya-y\\|%$+\\-/()\\s\\*]*";

    public static final String INTEGER_BASE_36 = "[0-9A-Za-z\\|%$+\\-/()\\s\\*]*";

    /**
     * Get the regex for integer of different bases
     * @param base the base of the integer
     * @return the regex for integer of the base
     */
    public static String getRegexInt(int base){
        switch(base){
            case 2:
                return INTEGER_BASE_2;
            case 3:
                return INTEGER_BASE_3;
            case 4:
                return INTEGER_BASE_4;
            case 5:
                return INTEGER_BASE_5;
            case 6:
                return INTEGER_BASE_6;
            case 7:
                return INTEGER_BASE_7;
            case 8:
                return INTEGER_BASE_8;
            case 9:
                return INTEGER_BASE_9;
            case 10:
                return INTEGER_BASE_10;
            case 11:
                return INTEGER_BASE_11;
            case 12:
                return INTEGER_BASE_12;
            case 13:
                return INTEGER_BASE_13;
            case 14:
                return INTEGER_BASE_14;
            case 15:
                return INTEGER_BASE_15;
            case 16:
                return INTEGER_BASE_16;
            case 17:
                return INTEGER_BASE_17;
            case 18:
                return INTEGER_BASE_18;
            case 19:
                return INTEGER_BASE_19;
            case 20:
                return INTEGER_BASE_20;
            case 21:
                return INTEGER_BASE_21;
            case 22:
                return INTEGER_BASE_22;
            case 23:
                return INTEGER_BASE_23;
            case 24:
                return INTEGER_BASE_24;
            case 25:
                return INTEGER_BASE_25;
            case 26:
                return INTEGER_BASE_26;
            case 27:
                return INTEGER_BASE_27;
            case 28:
                return INTEGER_BASE_28;
            case 29:
                return INTEGER_BASE_29;
            case 30:
                return INTEGER_BASE_30;
            case 31:
                return INTEGER_BASE_31;
            case 32:
                return INTEGER_BASE_32;
            case 33:
                return INTEGER_BASE_33;
            case 34:
                return INTEGER_BASE_34;
            case 35:
                return INTEGER_BASE_35;
            case 36:
                return INTEGER_BASE_36;
            default:
                return null;
        }
    }

    /**
     * If a string does not match a regex, you can apply the method replaceAll() to it with the regex returned by this method
     * @param regex the regex that the string does not match
     * @return the regex that the string matches
     */
    public static String negateRegex(String regex) {
        return "[^" + regex.substring(1, regex.length() - 2) + "]";
    }
}
