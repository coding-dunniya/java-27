package locks;

// counter thread
// objects of this class acts as threads
// each thread has a job of calling increment method 10,000 times
public class CounterThread extends Thread {

    // the counter reference
    private Counter counter;

    // constructor - take counter as parameter
    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    // entry point of thread
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            counter.increment();
        }
    }
}
