package memory;

import calculator.MyNumber;

import java.io.Serializable;

public class MemoryNumber extends MyNumber implements Serializable {
    private String name; //TODO : Create a random but unique name for the expression
    /**
     * Constructor method
     *
     * @param v The integer value to be contained in the object
     */
    public MemoryNumber(int v) {
        super(v);
        this.name = generateName();
    }
    /**
        * Constructor method
        *
        * @param v The integer value to be contained in the object
        * @param name The name of the expression
        */
    public MemoryNumber(int v, String name) {
        super(v);
        this.name = name;
    }
    public String getName() {
        return name;
    }

    // To be printed
    public String toString() {
        return "Value=" + getValue() + ", Name of the save=" + name ;
    }

    private String generateName() {
        //TODO : Create a unique name that is the concatenation of the value and the hour, minute and second of the save
        String time = java.time.LocalTime.now().toString(); //TIME OF THE CREATION != TIME OF THE SAVE NEED TO BE CHANGED
        return getValue() + ":" + time;
    }
}
