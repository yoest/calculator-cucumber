package memory;

import calculator.*;

import java.io.IOException;
import java.util.List;

import java.util.Stack;

public class MemoryCalculator extends Calculator {
    // Constructor
    private final Caretaker caretaker; // the class that stores the history of the expressions
    private Snapshot lastSnapshot = null; // the last snapshot saved in the memory
    private Snapshot lastErased = null; // the last snapshot erased from the memory
    private final Stack erased = new Stack();
    private int maxMemorySize; //bytes, the maximum size of the memory

    /** Simple constructor
     *
     * @param maxMemorySize : the maximum size of the memory
     *
     */
    public MemoryCalculator(int maxMemorySize) {
        super();
        this.maxMemorySize = maxMemorySize;
        this.caretaker = new Caretaker(maxMemorySize);
    }

   /** Evaluate an expression and save it in the memory
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

    /** Method used to save an expression in the memory
     *
     * @param e : the expression to be saved
     */
    public void save(Expression e) throws IOException {
        Expression computed = new MyNumber(eval(e).toString());
        Snapshot snapshot = new Snapshot(e, computed);
        snapshot.store(generateName());
        caretaker.add(snapshot);
        lastSnapshot = snapshot; //useful for the undo method
    }

    public void save(Expression e, Expression computed) throws IOException {
        Snapshot snapshot = new Snapshot(e, computed);
        snapshot.store(generateName());
        lastSnapshot = snapshot; //useful for the undo method
       while (!caretaker.checkSize(snapshot.getSize())) {caretaker.remove(caretaker.getHistory().get(0));}
        caretaker.add(snapshot);
    }

    /** Load a snapshot using its name
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

    // load the last snapshot
    public Expression load() {
        return load(lastSnapshot.getName());
    }

    // get the history of the expressions stored in the memory
    public List<Snapshot> getHistory() {
        return caretaker.getHistory();
    }

    // Save the history of the expressions stored in the memory
    public String saveHistory() {
        return caretaker.serializeHistory();
    }

    public String exportHistory() {
        return caretaker.saveHistoryTxt();
    }

    // generate the name of the file that will store the snapshot
    private String generateName() {
        String time = java.time.LocalTime.now().toString();
        return time.substring(0, time.length() - 4); // remove the milliseconds to make the name more readable
        }

    // undo the last snapshot
    public Snapshot undo() {
        updateLastSnapshot();
        if (lastSnapshot != null) {
            lastErased = caretaker.remove(lastSnapshot);
            erased.push(lastErased);
            updateLastSnapshot();
            return lastErased;
        } else {
            System.out.println("No snapshot to undo");
            return null;
        }
    }

    // redo the last snapshot
    public Snapshot redo() {
        try {
            if (lastErased != null) {
                Snapshot elem = (Snapshot) erased.pop();
                caretaker.add(elem);

                updateLastSnapshot();
                return elem;
            }
        } catch (Exception e) {
            System.out.println("No snapshot to redo");
            throw new RuntimeException(e);
        }
        return null;
    }

    // update the last snapshot by getting it and the end of the list
    public void updateLastSnapshot() {
        List<Snapshot> history = getHistory();
        //Get history
        if (history.size() > 0) {
            lastSnapshot = history.get(history.size() - 1);
        }
    }

    /** Clear the history of the memory
     * This method is here only for testing purposes, for now, it may cause problems if used
     */
    public void clearHistory() {
        caretaker.clearHistory();
    }

    public void loadHistory(String name) {
        caretaker.deserializeHistory(name);
    }

    public void adaptSizeOfHistory() {
        List<Snapshot> history = getHistory();
        // If > maxHistorySize, remove the first element until the size is ok
        int size = 0;
        for (Snapshot snapshot : history) {
            size += snapshot.getSize();
        }
        if (size > maxMemorySize) {
            int i = 0;
            while (size > maxMemorySize) {
                size -= history.get(i).getSize();
                history.remove(0);
                i++;
            }
        }
    }
    public Snapshot getLastErased() {
        return lastErased;
    }

}
