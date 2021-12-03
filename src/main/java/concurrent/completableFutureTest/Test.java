package concurrent.completableFutureTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Test {
    public static void main(String args[]) throws ExecutionException, InterruptedException {
        CompletableFuture<String> resultFuture = new CompletableFuture<>();
        resultFuture.complete("a");
        System.out.println(resultFuture.isDone());
        System.out.println(resultFuture.get());

        resultFuture = CompletableFuture.completedFuture("hello");
        System.out.println(resultFuture.get());
    }

    @org.junit.jupiter.api.Test
    public void t1() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> System.out.println("hello"));
        //System.out.println(future.get());
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "hello");
        //System.out.println(future1.get());
    }

    @org.junit.jupiter.api.Test
    public void t2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.completedFuture("hello ").
                thenApply(s -> s + "world").thenApply(s -> s + "aaaaa");
        //System.out.println(future.get());
        future = future.thenApply(s -> s + "next");
        System.out.println(future.get());
    }

    @org.junit.jupiter.api.Test
    public void t3() throws ExecutionException, InterruptedException {
        CompletableFuture.completedFuture("hello ")
                .thenApply(s -> s + "world!").thenApply(s -> s + "a").thenAccept(System.out::println);
        CompletableFuture.completedFuture("hello ")
                .thenApply(s -> s + "world!").thenApply(s -> s + "a").thenRun(() -> System.out.println("abcdefg"));
    }

    @org.junit.jupiter.api.Test
    public void t4() throws ExecutionException, InterruptedException {
        //ForkJoinPool的common pool的线程,非main thread
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                    //System.out.println(1);
                    System.out.println(1 / 0);//如果不future.get()，该异常不会实际抛出
                    return "hello ";
                })
                .whenComplete((res, ex) -> {
                    System.out.println(res);//result
                    System.out.println(ex);//throwable
                });
        Thread.sleep(1000);
        System.out.println(future.isDone());
        System.out.println(future.get());//在这里报异常
        // ExecutionException:Exception thrown when attempting to retrieve the result of a task that aborted by throwing an exception.
        // This exception can be inspected using the getCause() method.
    }

    @org.junit.jupiter.api.Test
    public void t5() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            //System.out.println(1/0);
            System.out.println(22222);
            return "123";
        }).handle((res, ex) -> {
            System.out.println(ex);
            //return res == null ? 1 : Integer.parseInt(res);
            return 321;
        });
        System.out.println(future.get());
    }

    /**
     * handle()无论如何都执行
     * exceptionally()只有在异常抛出时才执行
     * 详见CompletionStage的介绍
     * <p>
     * 且，handle()可以返回和上一阶段不同类型的值，而exceptionally()则不行
     */

    @org.junit.jupiter.api.Test
    public void t6() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(1 / 0);
            System.out.println(22222);
            return 0;
        }).exceptionally(ex -> {
            System.out.println(ex);
            return 1;
        });
        System.out.println(future.get());
    }

    @org.junit.jupiter.api.Test
    public void t7() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                    System.out.println(1 / 0);
                    return "hello ";
                })
                .whenComplete((res, ex) -> {
                    System.out.println(res);//result
                    System.out.println(ex);//throwable
                    int[] temp = new int[0];
                    System.out.println(temp[0]);
                });
        Thread.sleep(1000);
        System.out.println(future.isDone());
        System.out.println(future.get());
    }

    @org.junit.jupiter.api.Test
    public void t8() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        //System.out.println(future.complete("val"));
        System.out.println(future.completeExceptionally(new RuntimeException("test")));
        future.get();
    }

    @org.junit.jupiter.api.Test
    public void t9() throws ExecutionException, InterruptedException {
        CompletableFuture<Character> future = CompletableFuture.supplyAsync(() -> "hello")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s.charAt(0)));
        System.out.println(future.get());
    }

    @org.junit.jupiter.api.Test
    public void t10() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello")
                .thenCombine(CompletableFuture.supplyAsync(() -> " world!"),
                        (s1, s2) -> s1 + s2)
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " next!"));
        System.out.println(future.get());
    }

    /**
     * 一种常用的allOf()方法的使用方式是：
     * Spring的@Resource,@AutoWired 自动注入List，Map中
     * <p>
     * 定义一个接口 Interface1 代表 task
     *
     * @Resource ArrayList<Interface1> list;
     * 自动注入所有需要并行的tasks，用allOf()跑，控制阶段
     * 如feeds流的阶段加载依赖。
     * <p>
     * 用allOf(...).thenAccept()等方法，处理这些并行获取到的依赖。
     */
    @org.junit.jupiter.api.Test
    public void t11() throws Exception {
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task1");
            return "hello";
        });
        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task2");
            return " world";
        });
        CompletableFuture<Void> headerFuture = CompletableFuture.allOf(task1, task2);
        System.out.println(headerFuture.join());
        System.out.println("done");
        System.out.println(task1.join() + task2.join());

        headerFuture = CompletableFuture.allOf();
        System.out.println(headerFuture.get());
        //If no CompletableFutures are provided, returns a CompletableFuture completed with the value null.
    }

    @org.junit.jupiter.api.Test
    public void t12() throws Exception {
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task1");
            return "hello";
        });
        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task2");
            return " world";
        });
        CompletableFuture<Object> headerFuture = CompletableFuture.anyOf(task1, task2);
        System.out.println(headerFuture.join());
        System.out.println("done");
        System.out.println(task1.join() + task2.join());

        headerFuture = CompletableFuture.anyOf();//incomplete CompletableFuture
        System.out.println(headerFuture.get());//block in here
        System.out.println("end");//can't show that
        //If no CompletableFutures are provided, returns an incomplete CompletableFuture.
    }


}
