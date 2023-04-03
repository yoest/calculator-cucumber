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
    private int counter; // the number of snapshots saved in the memory, used to generate the name of the file
    private int maxSize = 1000; //bytes, the maximum size of the file that is saved in the memory
    /** Simple constructor
     *
     */
    public MemoryCalculator() {
        super();
        this.caretaker = new Caretaker();
    }

    /** Simple constructor, taking a Caretaker (the class that stores the history of the expressions) as argument
     *
     * @param caretaker : the caretaker of the memory,
     * */
    public MemoryCalculator(Caretaker caretaker) {
        super();
        this.caretaker = caretaker;
    }

   /** Evaluate an expression and save it in the memory
    *
    * @param e : the expression to be evaluated, saved in the memory and in the history
    * @return the value of the expression
    */
   @Override
    public Number eval(Expression e) {
        int res = (int) super.eval(e);
        Expression computed = new MyNumber(res);
        save(e, computed);
        return res;
    }

    /** Public version of the save method
     *
     * @param e : the expression to be saved
     */
    public void save(Expression e) throws IOException {
        Expression computed = new MyNumber((Integer) eval(e)) ;
        Snapshot snapshot = new Snapshot(e, computed);
        snapshot.store(generateName(), maxSize);
        caretaker.add(snapshot);
        lastSnapshot = snapshot; //useful for the undo method
    }

    /** Private version the save method, used by the eval method
     *
     * @param e : the expression to be saved
     * @param computed : the value of the expression
     */
    private void save(Expression e,
                      Expression computed) {
        Snapshot snapshot = new Snapshot(e, computed);
        try {
            snapshot.store(generateName(), maxSize);
        } catch (IOException ex) {
            throw new RuntimeException(ex); //Can happen if the file is too big
        }
        caretaker.add(snapshot);
        lastSnapshot = snapshot;
    }

    /** Load a snapshot using its name
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

    // Print the history of the expressions stored in the memory
    public void printHistory() {
        List<Snapshot> history = getHistory();
        for (Snapshot snapshot : history) {
            System.out.println(snapshot);
            // print the expression and the name of the expression
            System.out.println(snapshot.getExpression());
        }
    }
    // generate the name of the file that will store the snapshot
    private String generateName() {
        String time = java.time.LocalTime.now().toString(); //TIME OF THE CREATION != TIME OF THE SAVE NEED TO BE CHANGED
        counter += 1;
        return counter + time;
        }

    // undo the last snapshot
    public void undo() {
        if (lastSnapshot != null) {
            lastErased = caretaker.remove(lastSnapshot);
            erased.push(lastErased);
            updateLastSnapshot();
        }
    }

    // redo the last snapshot
    public void redo() {
        try {
            if (lastErased != null) {
                caretaker.add(lastErased);
                erased.pop();
                updateLastSnapshot();
            }
        } catch (Exception e) {
            System.out.println("No snapshot to redo");
            throw new RuntimeException(e);
        }
    }

    // update the last snapshot by getting it and the end of the list
    public void updateLastSnapshot() {
        List<Snapshot> history = getHistory();
        if (history.size() > 0) {
            lastSnapshot = history.get(history.size() - 1);
        }
    }
    // set max size of the memory
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    /** Clear the history of the memory
     * This method is here only for testing purposes, for now, it may cause problems if used
     */
    public void clearHistory() {
        caretaker.clearHistory();
    }

}
