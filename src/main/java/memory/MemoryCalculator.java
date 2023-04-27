package memory;

import calculator.*;
import gui.MainCalculatorPane;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import java.util.Stack;

public class MemoryCalculator extends Calculator {
    private final Caretaker caretaker; // the class that stores the history of the expressions
    private Snapshot lastSnapshot = null; // the last snapshot saved in the memory
    private Snapshot lastErased = null; // the last snapshot erased from the memory
    private final Stack<Object> erased = new Stack<>();
    private final int maxMemorySize; //bytes, the maximum size of the memory

    /**
     * Simple constructor
     *
     * @param maxMemorySize : the maximum size of the memory
     */
    public MemoryCalculator(int maxMemorySize) {
        super();
        this.maxMemorySize = maxMemorySize;
        this.caretaker = new Caretaker(maxMemorySize);
    }

    /**
     * Evaluate an expression and save it in the memory
     *
     * @param e : the expression to be evaluated, saved in the memory and in the history
     * @return the value of the expression
     */
    @Override
    public Number eval(Expression e) {
        Number res = super.eval(e);
        Expression computed = new MyNumber(res.toString());
        try {
            save(e, computed);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return res;
    }

    /**
     * Method used to save an expression in the memory
     *
     * @param e : the expression to be saved
     */
    public void save(Expression e) throws IOException {
        //cast the result of the evaluation to a MyNumber
        MyNumber n = new MyNumber(eval(e).toString());
        if (MainCalculatorPane.IS_INTEGER_MODE) {
            n = new MyNumber(n.getValue().toString(), 10);
            n.setRadix(MainCalculatorPane.OUTPUT_RADIX);
        }
        Snapshot snapshot = new Snapshot(e, n);
        snapshot.store(generateName());
        caretaker.add(snapshot);
        lastSnapshot = snapshot; //useful for the undo method
    }

    public void save(Expression e, Expression computed) throws IOException {
        MyNumber n = (MyNumber) computed;
        if (MainCalculatorPane.IS_INTEGER_MODE) {
            n = new MyNumber(n.getValue().toString(), 10);
            n.setRadix(MainCalculatorPane.OUTPUT_RADIX);
        }
        Snapshot snapshot = new Snapshot(e, n);
        snapshot.store(generateName());
        lastSnapshot = snapshot; //useful for the undo method
        while (!caretaker.checkSize(snapshot.getSize())) {
            caretaker.remove(caretaker.getHistory().get(0));
        }
        caretaker.add(snapshot);
    }

    /**
     * Load a snapshot using its name
     *
     * @param name : the name of the snapshot to be loaded
     */
    public Expression load(String name) {
        Snapshot snapshot = new Snapshot(null);
        try {
            return snapshot.load(name);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Load the last snapshot saved in the memory
     *
     * @return the expression stored in the last snapshot
     */
    public Expression load() {
        return (lastSnapshot.getExpression());
    }

    /**
     * Get the history of the expressions stored in the memory
     *
     * @return the history of the expressions stored in the memory
     */
    public List<Snapshot> getHistory() {
        return caretaker.getHistory();
    }

    /**
     * Save the history of the expressions stored in the memory
     *
     * @return the history of the expressions stored in the memory
     */
    public String saveHistory() {
        return caretaker.serializeHistory();
    }

    /**
     * Export the history of the expressions stored in the memory
     *
     * @return the history of the expressions stored in the memory
     */
    public String exportHistory() {
        try {
            return caretaker.saveHistoryTxt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generate a name for the snapshot
     *
     * @return the name of the snapshot
     */
    private String generateName() {
        String time = java.time.LocalTime.now().toString();
        time = time.replace(":", "_");
        return time.substring(0, time.length() - 4); // remove the milliseconds to make the name more readable
    }

    /**
     * Undo the last snapshot
     *
     * @return the last snapshot
     */
    public Snapshot undo() throws IOException {
        updateLastSnapshot();
        if (lastSnapshot != null) {
            lastErased = caretaker.remove(lastSnapshot);
            erased.push(lastErased);
            updateLastSnapshot();
            return lastErased;
        } else {
            throw new IOException("No snapshot to undo");
        }
    }

    /**
     * Redo the last snapshot
     *
     * @return the last snapshot
     */
    public Snapshot redo() throws IOException {
        try {
            if (lastErased != null) {
                Snapshot elem = (Snapshot) erased.pop();
                caretaker.add(elem);
                updateLastSnapshot();
                return elem;
            }
        } catch (Exception e) {
            System.out.println("No snapshot to redo");
            throw new IOException(e);
        }
        return null;
    }

    public Boolean redoable() {
        return !erased.empty();
    }

    /**
     * Update the last snapshot
     */
    public void updateLastSnapshot() {
        List<Snapshot> history = getHistory();
        //Get history
        if (history.size() > 0) {
            lastSnapshot = history.get(history.size() - 1);
        }
    }


    /**
     * Load a history
     *
     * @param name : the name of the history to be loaded
     */
    public void loadHistory(String name) {
        caretaker.deserializeHistory(name);
    }

    /**
     * Adapt the size of the history to the maximum size of the memory
     */
    public void adaptSizeOfHistory() {
        List<Snapshot> history = getHistory();
        int size = 0;
        for (Snapshot snapshot : history) {
            size += snapshot.getSize();
        }
        if (size > maxMemorySize) {
            int i = 0;
            while (size >= maxMemorySize) {
                size -= history.get(i).getSize();
                history.remove(0);
                i++;
            }
        }
    }

    public void createSavesFolder() {
        List<String> folderPaths = Arrays.asList(
                "saves",
                "saves/history",
                "saves/history/ser",
                "saves/history/txt",
                "saves/expressions"
        );

        for (String path : folderPaths) {
            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdir();
            }
        }
    }
}