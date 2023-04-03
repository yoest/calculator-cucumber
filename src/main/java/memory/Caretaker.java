package memory;

import calculator.Expression;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Caretaker implements Serializable {
    private List<Snapshot> history = new ArrayList<>(); // List of the expressions stored in the memory

    // Simple constructor
    public Caretaker() {}

    // add a snapshot to the history
    public void add(Snapshot snapshot) {
        history.add(snapshot);
    }

    //remove a snapshot from the history
    public Snapshot remove(Snapshot snapshot) {
        if(history.remove(snapshot)) return snapshot;
        else return null;
    }

    // get the history of the expressions stored in the memory
    public List<Snapshot> getHistory() {
        return history;
    }

    ///Save the history
    public void serializeHistory() { //to call when the user wants to save the history, at the end of the program
        try {
            String outputFolder = "saves/history/ser/";
            String time = LocalTime.now().toString();
            String fileName = outputFolder + time + ".ser";

            // If a file already exists in this folder, delete it
            File file = new File(fileName);
            if(file.exists()){
                file.delete();
            }
                // TODO : check if the size of the file is bigger than maxSize
                FileOutputStream fileOut = new FileOutputStream(outputFolder + time + ".ser");
                // Create object output stream to write objects to file
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                // Get the size of the file
                File file_ = new File(outputFolder + time + ".ser");
                long fileSize = file.length(); // in bytes

                // Write object to file
                objectOut.writeObject(history);
                // Close object output stream
                objectOut.close();

            } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public List<Snapshot> deserializeHistory() { // TO call when the user wants to load the history, at the beginning of the program
        List<Snapshot> snapshots = new ArrayList<>();

        try {
            String outputFolder = "saves/history/ser/";

            // Find the history file in the folder
            File folder = new File("saves/history/ser/");
            File[] files = folder.listFiles();
            File historyFile = null;
            if (files.length > 0) {
                historyFile = files[0];
            } else {
                throw new FileNotFoundException("No history file found");
            }

            // Read the contents of the file and deserialize the snapshots
            FileInputStream fileInputStream = new FileInputStream(historyFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            snapshots = (List<Snapshot>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return snapshots;
    }

    // Save the history in a text file
    public void saveHistoryTxt() {
        // for snapshot in history
        String outputString = "";
        for (Snapshot snapshot : history) {
            outputString += "For snapshot : " + snapshot + "\n";
            // save the snapshot
            String name = snapshot.getName();
            Expression e = snapshot.getExpression();
            // if snapshot has a computed value
                System.out.println("The expression has a " + snapshot.getComputed().toString());
                // get the computed value
                Expression e_ = snapshot.getComputed();
                outputString += "The expression is " + e.toString() + " has a value of " +e_.toString()
                        + " and was saved under the name " + name + " at " + snapshot.getTime() + "\n";
            }

        try {
            String time = LocalTime.now().toString();
            FileWriter writer = new FileWriter("saves/history/txt/+ " + time + ".txt");
            writer.write(outputString);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file.");
            e.printStackTrace();
        }
    }

    //Set history
    public void setHistory(List<Snapshot> history) {
        this.history = history;
    }

    //Clear the history
    public void clearHistory() {
        // for all files in folder "saves/history/ser/"
        File folder = new File("saves/history/ser/");
        File[] files = folder.listFiles();
        for (File file : files) {
            file.delete();
        }
        // for all files in folder "saves/history/txt/"
        File folder_ = new File("saves/history/txt/");
        File[] files_ = folder_.listFiles();
        for (File file : files_) {
            file.delete();
        }
    }

    //Clear expressions
    public void clearExpressions() {
        // for all files in folder "saves/expressions/ser/"
        File folder = new File("saves/expressions/");
        File[] files = folder.listFiles();
        for (File file : files) {
            file.delete();
        }
    }

    //Get names of all the snapshots
    public List<String> getNames() {
        List<String> names = new ArrayList<>();
        for (Snapshot snapshot : history) {
            names.add(snapshot.getName());
        }
        return names;
    }
}
