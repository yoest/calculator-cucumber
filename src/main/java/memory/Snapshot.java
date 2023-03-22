package memory;

import calculator.*;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class Snapshot {
    // The calculator should allow the user to store in memory specific expressions that he would like to reuse later.
    // The size of the memory to do so can be finite, but should be configurable by the user through some option in the application's interface.

    // Create a constructor for the Snapshot class that takes an Expression as input parameter.

    private Expression e;
    private final String destinationFolder = "saves/";
    private LocalTime time;
    public  Snapshot(Expression e) {
        // TODO
        this.e = e;
    }

    // Method to store the expression in a file
    public void store(String name) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(destinationFolder + name + ".ser");
        // Create object output stream to write objects to file
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        // Write object to file
        objectOut.writeObject(e);
        // Close object output stream
        objectOut.close();
        // get the time of the save
        this.time = java.time.LocalTime.now();
    }

    public void store(String name, int maxSize) throws IOException {
        // TODO : check if the size of the file is bigger than maxSize
        FileOutputStream fileOut = new FileOutputStream(destinationFolder + name + ".ser");
        // Create object output stream to write objects to file
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        // Get the size of the file
        File file = new File(destinationFolder + name + ".ser");
        long fileSize = file.length(); // in bytes
        // If the size of the file is bigger than maxSize, delete the file and throw an exception
        if (fileSize > maxSize) {
            file.delete();
            throw new IOException("The memory is too small to store the expression");
        }

        // Write object to file
        objectOut.writeObject(e);
        // Close object output stream
        objectOut.close();
        // get the time of the save
        LocalTime time = java.time.LocalTime.now();
        this.time = time;
    }
    // Method to load the expression from a file
    public Expression load(String name) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(destinationFolder + name + ".ser");
        // Create object input stream to read objects from file
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        // Read object from file
        Expression e = (Expression) objectIn.readObject();
        // Close object input stream
        objectIn.close();
        return e;
    }

    // Getter
    public Expression getExpression() {
        return e;
    }

    // get the time of the save
    public LocalTime getTime() {
        return time;
    }
}