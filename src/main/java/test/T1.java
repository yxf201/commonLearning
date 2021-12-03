package test;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author yxf
 * @date 2021/12/1 11:48
 */
public class T1 {
    private static T1 a = null;
    private static int count = 0;

    T1() throws InterruptedException {
        System.out.println(this == null);
        System.out.println("init start");
        a = this;
        Thread.sleep(2000);
        System.out.println(++count);
        System.out.println("init finished in: " + Thread.currentThread());
    }

    @Test
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*A newA = CompletableFuture.supplyAsync(()-> {
            try {
                return new A();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }).get();*/

        CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("run");
                return new T1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
        Thread.sleep(1000);
        System.out.println(T1.a);

        Thread.sleep(3000);
        //System.out.println(A.count);
        //System.out.println(A.a);
    }
}
