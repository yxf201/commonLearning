package concurrent.cancelAndClose;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BrokenPrimeProducer extends Thread{
    private final BlockingQueue<BigInteger> queue;

    private volatile boolean cancelled = false;

    BrokenPrimeProducer(BlockingQueue<BigInteger> queue){
        this.queue = queue;
    }

    @Override
    public void run(){
        try{
            BigInteger p = BigInteger.ONE;
            while( !cancelled ){
                System.out.println("put start");
                queue.put(p = p.nextProbablePrime());//block in here
                System.out.println("put end");
            }
        } catch (InterruptedException e) {
            //do nothing
        }
    }

    public void cancel(){
        this.cancelled = true;
    }

    static void consumePrimes() throws InterruptedException {
        BlockingQueue<BigInteger> primes = new ArrayBlockingQueue<>(10);
        BrokenPrimeProducer producer = new BrokenPrimeProducer(primes);
        producer.start();
        try{
            need = 3;
            while (needMorePrimes()){
                System.out.println(need);
                consume(primes.take());
            }
        }finally {
            producer.cancel();
        }
    }

    private static int need = 3;
    private static boolean needMorePrimes() {
        if( need-- < 0 ){
            return false;
        }
        return true;
    }

    private static void consume(BigInteger prime) throws InterruptedException {
        Thread.sleep(1000);
    }

    public static void main(String[] args) throws InterruptedException {
        consumePrimes();
    }
}
