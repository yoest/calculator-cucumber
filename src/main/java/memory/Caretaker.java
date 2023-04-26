package memory;

import calculator.Expression;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Caretaker implements Serializable {
    private List<Snapshot> history = new ArrayList<>(); // List of the expressions stored in the memory
    private int maxSize = 1000; //bytes, the maximum size of the file that is saved in the memory
    private int remainingSize;
    private final String OUTPUTFOLDER = "saves/history/ser/";

    /**
     * Simple constructor
     */
    public Caretaker() {}

    public Caretaker(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     *  Add a snapshot to the history
     */
    public void add(Snapshot snapshot) {
        history.add(snapshot);
    }

    /**
     * Remove a snapshot from the history
     * @param snapshot : the snapshot to be removed
     * @return the snapshot removed
     */
    public Snapshot remove(Snapshot snapshot) {
        if(history.remove(snapshot)) return snapshot;
        else return null;
    }

    /**
     * Compute the size remaining in the memory
     */
    public void computeRemainingSize() {
        int size = 0;
        for (Snapshot snapshot : history) size += snapshot.getSize();
        this.remainingSize = maxSize - size;
    }

    /**
     * Get the history of the expressions stored in the memory
     * @return the history
     */
    public List<Snapshot> getHistory() {
        return history;
    }

    /**
     * Save the history in a file
     *
     * @return the name of the file
     */
    public String serializeHistory() {
        try {
            String time = java.time.LocalTime.now().toString();
            time = time.replace(":", "_");
            String fileName = OUTPUTFOLDER + time.substring(0, time.length() - 4) + ".ser";
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(history);
            objectOut.close();
            return fileName;
        } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    /**
     * Load the history from a file
     *
     * @param name : the name of the file
     */
    public void deserializeHistory(String name) {
        List<Snapshot> snapshots = new ArrayList<>();
        try {
            File historyFile = new File(OUTPUTFOLDER + name);
            FileInputStream fileInputStream = new FileInputStream(historyFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            snapshots = (List<Snapshot>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {e.printStackTrace();}
        setHistory(snapshots);
    }

    /**
     * Save the history in a txt file
     * @return the name of the file
     */
    public String saveHistoryTxt() throws IOException {
        // for snapshot in history
        StringBuilder outputString = new StringBuilder();
        for (Snapshot snapshot : history) {
            outputString.append("For snapshot : ").append(snapshot).append("\n");
            // save the snapshot
            String name = snapshot.getName();
            Expression e = snapshot.getExpression();
                Expression e_ = snapshot.getComputed();
                outputString.append("The expression is ").append(e.toString()).append(" has a value of ").append(e_.toString()).append(" and was saved under the name ").append(name).append(" at ").append(snapshot.getTime()).append("\n");
            }

            String time = java.time.LocalTime.now().toString();
            time = time.replace(":", "_");
            String fileName = time.substring(0, time.length() - 4);
            FileWriter writer = new FileWriter("saves/history/txt/+ " + fileName + ".txt");
            writer.write(outputString.toString());
            writer.close();
            return fileName + ".txt";

    }

    /**
     * Set the history
     * @param history : the new history
     */
    public void setHistory(List<Snapshot> history) {
        this.history = history;
    }


    /**
     * Clear the history
     */
    public void clearExpressions() {
        File folder = new File("saves/expressions/");
        File[] files = folder.listFiles();
        assert files != null;
        for (File file : files) {
            file.delete();
        }
    }

    /**
     *  Check remaining size
     */
    public boolean checkSize(int size) {
        computeRemainingSize();
        return remainingSize - size >= 0;
    }
}
