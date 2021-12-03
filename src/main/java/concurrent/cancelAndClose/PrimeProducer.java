package concurrent.cancelAndClose;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

public class PrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;

    PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted()) {//interrupted()是不是好一点
                queue.put(p = p.nextProbablePrime());
            }
        } catch (InterruptedException e) {
            //exit run() method
        }
    }

    public void cancel() {
        this.interrupt();
    }

    //传递InterruptedException，使这个方法也成为了一个可中断的阻塞方法
    public BigInteger getNextTask() throws InterruptedException {
        return queue.take();
    }

    public <T> T getNextTask(BlockingQueue<T> queue) {
        boolean interrupted = false;
        try {
            while (true) {
                try {
                    return queue.take();
                } catch (InterruptedException e) {
                    interrupted = true;
                }
            }
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
