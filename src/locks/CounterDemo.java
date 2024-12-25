package locks;

// driver class for thread synchronization
// to see it in action run main method below
public class CounterDemo {

    // main method - jvm calls this method in main thread
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        CounterThread t1 = new CounterThread(counter);
        CounterThread t2 = new CounterThread(counter);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        int value = counter.getCountValue();

        System.out.println("count value ==> " + value);
    }
}
