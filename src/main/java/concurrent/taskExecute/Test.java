package concurrent.taskExecute;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> System.out.println(1));
        //executor.shutdown();
        boolean b = executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
        System.out.println(b);
        System.out.println(executor.isTerminated());
        //不会退出
    }
}
