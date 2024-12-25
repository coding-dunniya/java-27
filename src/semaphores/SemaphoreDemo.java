package semaphores;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) throws Exception {
        Semaphore semaphore = new Semaphore(1);

        Thread incrementThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread t = Thread.currentThread();
                try {
                    System.out.println(t.getName() + " waiting for a permit");
                    semaphore.acquire();
                    System.out.println(t.getName() + " gets a permit");
                    for (int i = 0; i < 5; i++) {
                        SharedResource.count = SharedResource.count + 1;
                        System.out.println(t.getName() + " resource value: " + SharedResource.count);
                        Thread.sleep(1000);
                    }
                } catch (Exception ex) {
                    System.out.println("Exception in increment thread ==> " + ex.getMessage());
                }
                System.out.println(t.getName() + " releases the permit");
                semaphore.release();
            }
        });
        incrementThread.setName("increment-thread");

        Thread decrementThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread t = Thread.currentThread();
                try {
                    System.out.println(t.getName() + " waiting for a permit");
                    semaphore.acquire();
                    System.out.println(t.getName() + " gets a permit");
                    for (int i = 0; i < 5; i++) {
                        SharedResource.count = SharedResource.count - 1;
                        System.out.println(t.getName() + " resource value: " + SharedResource.count);
                        Thread.sleep(1000);
                    }
                } catch (Exception ex) {
                    System.out.println("Exception in decrement thread ==> " + ex.getMessage());
                }
                System.out.println(t.getName() + " releases the permit");
                semaphore.release();
            }
        });
        decrementThread.setName("decrement-thread");

        incrementThread.start();
        decrementThread.start();

        incrementThread.join();
        decrementThread.join();

        System.out.println("main thread complete");
    }
}
