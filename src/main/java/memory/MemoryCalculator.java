package memory;

import calculator.*;

import java.io.IOException;

public class MemoryCalculator extends Calculator {
    public void save(Expression e, String name) throws IOException {
        Snapshot snapshot = new Snapshot(e);
        snapshot.store(name);
    }

    public Expression load(String name) throws IOException, ClassNotFoundException {
        Snapshot snapshot = new Snapshot(null);
        return snapshot.load(name);
    }

}
