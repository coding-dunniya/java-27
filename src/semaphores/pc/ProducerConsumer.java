package semaphores.pc;

import java.util.concurrent.Semaphore;

// the driver program for producer consumer problem
public class ProducerConsumer {

    // main method - jvm calls this method in main thread
    public static void main(String[] args) {
        Semaphore semaphoreConsumer = new Semaphore(0);
        Semaphore semaphoreProducer = new Semaphore(1);
        PcQueue queue = new PcQueue(semaphoreConsumer, semaphoreProducer);

        // the producer thread
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    queue.produce();
                }
            }
        });

        // the consumer thread
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    queue.consume();
                }
            }
        });

        producer.start();
        consumer.start();
    }
}
