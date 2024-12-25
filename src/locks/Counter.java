package locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// the counter class
public class Counter {

    // the initial count value
    private int count = 0;

    // lock object
    private final Lock lock = new ReentrantLock();

    // increments the value of count by 1
    public void increment() {
        lock.lock();
        count = count + 1;
        lock.unlock();
    }

    // get the value of current count
    public int getCountValue() {
        return this.count;
    }
}
