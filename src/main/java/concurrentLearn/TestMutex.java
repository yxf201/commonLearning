package concurrentLearn;

import java.util.concurrent.CyclicBarrier;

public class TestMutex {
    private static CyclicBarrier barrier = new CyclicBarrier(31);
    private static int a = 0;
    private static Mutex mutex = new Mutex();

    public static void increment1() {
        a++;
    }

    public static void increment2() {
        mutex.lock();
        a++;
        mutex.unLock();
    }

    public static void main(String args[]) throws Exception {
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increment1();
                }
                try {
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        barrier.await();
        System.out.println("a: " + a);
        barrier.reset();
        a = 0;
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increment2();
                }
                try {
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        barrier.await();
        System.out.println("a: " + a);
    }
}
