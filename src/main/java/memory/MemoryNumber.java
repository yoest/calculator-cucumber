package memory;

import calculator.MyNumber;

import java.io.Serializable;

public class MemoryNumber extends MyNumber implements Serializable {

    /**
     * Constructor method
     *
     * @param v The integer value to be contained in the object
     */
    public MemoryNumber(int v) {
        super(v);
    }
}
