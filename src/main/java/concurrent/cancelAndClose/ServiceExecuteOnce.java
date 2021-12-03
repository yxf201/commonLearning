package concurrent.cancelAndClose;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServiceExecuteOnce {
    boolean checkMail(Set<String> hosts, long timeout, TimeUnit unit) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicBoolean hasNewMail = new AtomicBoolean(false);
        try {
            for (String host : hosts) {
                exec.execute(()->{
                   if( checkMail(host) ){
                       hasNewMail.set(true);
                   }
                });
            }
        }finally {
            exec.shutdown();
            exec.awaitTermination(timeout, unit);
        }
        return hasNewMail.get();
    }

    private boolean checkMail(String host) {
        return true;
    }
}
