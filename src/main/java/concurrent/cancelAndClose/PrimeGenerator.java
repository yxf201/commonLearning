package concurrent.cancelAndClose;

import annotations.GuardedBy;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PrimeGenerator implements Runnable{
    @GuardedBy("this")
    private final List<BigInteger> primes = new ArrayList<>();

    private volatile boolean cancelled;

    public void run(){
        BigInteger p = BigInteger.ONE;
        while( !cancelled ){
            p = p.nextProbablePrime();
            synchronized (this){
                primes.add(p);
            }
        }
    }

    public void cancel(){
        cancelled = true;
    }

    public synchronized List<BigInteger> get(){
        return new ArrayList<>(primes);
    }

    static List<BigInteger> aSecondOfPrimes() throws InterruptedException {
        PrimeGenerator generator = new PrimeGenerator();
        Thread t = new Thread(generator);
        //The newly created thread is initially marked as being a daemon thread if and only if the thread creating it is currently marked as a daemon thread.
        //t.setDaemon(true);
        t.start();
        try{
            TimeUnit.SECONDS.sleep(1);
        }finally {
            generator.cancel();
        }
        return generator.get();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(aSecondOfPrimes());
    }
}
