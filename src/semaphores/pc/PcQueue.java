package semaphores.pc;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

// the producer-consumer queue
// both producer and consumer threads works on an object of this class
public class PcQueue {

    // the underlying data structure
    private Queue<Integer> queue = new LinkedList<>();

    // capacity of queue
    // you can change this capacity to any value you want
    private int capacity = 1;

    private Semaphore semaphoreConsumer;

    private Semaphore semaphoreProducer;

    public PcQueue(Semaphore semaphoreConsumer, Semaphore semaphoreProducer) {
        this.semaphoreConsumer = semaphoreConsumer;
        this.semaphoreProducer = semaphoreProducer;
    }

    // produce a value
    // called from producer thread
    public void produce() {
        try {
            semaphoreProducer.acquire();
            int value = (int) (Math.random() * 100);
            queue.add(value);
            System.out.println("value produced ==> " + value);
            Thread.sleep(2000);
            semaphoreConsumer.release();
        } catch (InterruptedException ie) {
            System.out.println("Exception: " + ie.getMessage());
        }
    }

    // consume from the queue
    // called by consumer thread
    public void consume() {
        try {
            semaphoreConsumer.acquire();
            while (!queue.isEmpty()) {
                int value = queue.poll();
                System.out.println("consumer consumed ==> " + value);
            }
            Thread.sleep(2000);
            semaphoreProducer.release();
        } catch (InterruptedException ie) {
            System.out.println("Exception: " + ie.getMessage());
        }
    }
}
