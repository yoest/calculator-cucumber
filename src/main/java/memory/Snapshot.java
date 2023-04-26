package memory;

import calculator.*;

import java.io.*;
import java.time.LocalTime;


public class Snapshot implements Serializable {
    private final Expression e;
    private final MyNumber computed;
    private final String destinationFolder = "saves/expressions/";
    private String name;
    private LocalTime time;
    private int size;

    /**
     * Simple constructor
     * @param e : the expression to be saved
     */
    public Snapshot(Expression e) {
        this.e = e;
        this.computed = null;
    }

    /**
     * More complex constructor
     * @param e : the expression to be saved
     * @param computed : the result of the evaluation of the expression
     */
    public Snapshot(Expression e, MyNumber computed) {
        this.e = e;
        this.computed = computed;
    }

    /**
     * Store the expression in a file
     * @param name : the name of the file
     */
    public void store(String name) throws IOException {
        this.name = name;
        FileOutputStream fileOut = new FileOutputStream(destinationFolder + name + ".ser");
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        File file = new File(destinationFolder + name + ".ser");
        long fileSize = file.length(); // in bytes
        objectOut.writeObject(e);
        objectOut.close();
        this.size = (int) fileSize;
        this.time = LocalTime.now();
    }

    /**
     * Load the expression from a file
     * @param name : the name of the file
     * @return the expression
     */
    public Expression load(String name) throws IOException, ClassNotFoundException {
        if (!new File(destinationFolder + name + ".ser").exists()) return null;
        FileInputStream fileIn = new FileInputStream(destinationFolder + name + ".ser");
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        Expression e = (Expression) objectIn.readObject();
        objectIn.close();
        return e;
    }

    /**
     * Get the expression
     * @return the expression
     */
    public Expression getExpression() {
        return e;
    }

    /**
     * Get the computed result
     * @return the computed result
     */
    public MyNumber getComputed() {
        return computed;
    }

    /**
     * Get the time of the save
      */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Get the time of the save
     * @return the time of the save
     */
    public String getName() {
        return name;
    }

    /**
     * Get the size of the file
     * @return the size of the file
     */
    public int getSize() {
        return size;
    }
}