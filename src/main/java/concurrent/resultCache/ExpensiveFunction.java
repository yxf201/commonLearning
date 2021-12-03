package concurrent.resultCache;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ExpensiveFunction implements Computable<String, BigInteger>{
    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        // a long time compute
        Thread.sleep(new Random().nextInt(2000));
        return new BigInteger(arg);
    }
}
