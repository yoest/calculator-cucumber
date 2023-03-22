package memory;

import calculator.*;

import java.io.IOException;
import java.util.List;
import java.time.LocalTime;
import java.util.ArrayList;

public class MemoryCalculator extends Calculator {
    // Constructor
    private Caretaker caretaker = new Caretaker();
    private Snapshot lastSnapshot = null;
    public MemoryCalculator(Caretaker caretaker) {
        super();
        this.caretaker = caretaker;
    }
    public void save(Expression e, String name) throws IOException {
        Snapshot snapshot = new Snapshot(e);
        snapshot.store(name);
        caretaker.add(snapshot);
    }

    public void save(Expression e, String name, int maxSize) throws IOException {
        Snapshot snapshot = new Snapshot(e);
        snapshot.store(name, maxSize);
        caretaker.add(snapshot);
        lastSnapshot = snapshot;
    }
    public Expression load(String name) throws IOException, ClassNotFoundException {
        Snapshot snapshot = new Snapshot(null);
        return snapshot.load(name);
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

    //get the time of all the expressions stored in the memory
    public List<LocalTime> getTimeOfAllSaves() {
        List<Snapshot> history = getHistory();
        List<LocalTime> time = new ArrayList<>();
        for (Snapshot snapshot : history) {
            time.add(snapshot.getTime());
        }
        return time;
    }

    // undo the last snapshot
    public void undo() {
        if (lastSnapshot != null) {
            caretaker.remove(lastSnapshot);
            updateLastSnapshot();
        }
    }

    // update the last snapshot by getting it and the end of the list
    public void updateLastSnapshot() {
        List<Snapshot> history = getHistory();
        if (history.size() > 0) {
            lastSnapshot = history.get(history.size() - 1);
        }
    }


    // redo the last snapshot
    public void redo() {
        if (lastSnapshot != null) {
            caretaker.add(lastSnapshot);
        }
    }


}
