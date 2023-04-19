package memory;

import calculator.*;

import java.io.*;
import java.time.LocalTime;


public class Snapshot implements Serializable {
      private final Expression e;
    private final Expression computed;
    private final String destinationFolder = "saves/expressions/";
    private String name;
    private LocalTime time;
    private int size;
    public Snapshot(Expression e) {
        // TODO
        this.e = e;
        this.computed = null;
    }
    public Snapshot(Expression e, Expression computed) {
        // TODO
        this.e = e;
        this.computed = computed;

    }

    public void store(String name) throws IOException {
        this.name = name;
        FileOutputStream fileOut = new FileOutputStream(destinationFolder + name + ".ser");
        // Create object output stream to write objects to file
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        // Get the size of the file
        File file = new File(destinationFolder + name + ".ser");
        long fileSize = file.length(); // in bytes

        // If the size of the file is bigger than maxSize, delete the file and throw an exception
        if (computed != null) {
            objectOut.writeObject(computed);
        }

        // Write object to file
        objectOut.writeObject(e);
        // Close object output stream
        objectOut.close();
        this.size = (int) fileSize;
        // get the time of the save
        this.time = LocalTime.now();
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

    public Expression getComputed() {
        return computed;
    }

    // get the time of the save
    public LocalTime getTime() {
        return time;
    }

    // get the name of the file
    public String getName() {
        return name;
    }

    // get the size of the file
    public int getSize() {
        return size;
    }
}