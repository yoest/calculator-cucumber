package memory;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {
    private List<Snapshot> history = new ArrayList<>(); // List of the expressions stored in the memory

    // Simple constructor
    public Caretaker() {}

    // add a snapshot to the history
    public void add(Snapshot snapshot) {
        history.add(snapshot);
    }

    //remove a snapshot from the history
    public void remove(Snapshot snapshot) {
        history.remove(snapshot);
    }

    // get the history of the expressions stored in the memory
    public List<Snapshot> getHistory() {
        return history;
    }
}
