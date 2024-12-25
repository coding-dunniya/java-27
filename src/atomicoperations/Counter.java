package atomicoperations;

import java.util.concurrent.atomic.AtomicInteger;

// the counter class
public class Counter {

    // the initial count value
    // we are using atomic integer class which supports atomic operations
    // incrementing a value is atomic operation
    private AtomicInteger count = new AtomicInteger(0);

    // increments the value of count by 1
    public void increment() {
        count.incrementAndGet();
    }

    // get the value of current count
    public int getCountValue() {
        return this.count.get();
    }
}
