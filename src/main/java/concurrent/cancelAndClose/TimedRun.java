package concurrent.cancelAndClose;

import java.util.concurrent.*;

public class TimedRun {
    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(1);

    public static void timedRun1(Runnable r, long timeout, TimeUnit unit) {
        final Thread taskThread = Thread.currentThread();
        cancelExec.schedule(() -> {
            try {
                throw new RuntimeException("aaa");
            } catch (Exception e) {
                taskThread.interrupt();
            }
        }, timeout, unit);
        r.run();
    }

    /**
     * 在Java中，线程中的异常是不能抛出到调用该线程的外部方法中捕获的。
     */
    public static void main(String[] args) throws InterruptedException {
        /** timedRun1(() -> {
         System.out.println("task run");
         }, 1, TimeUnit.SECONDS);
         System.out.println(1);
         //Thread.sleep(2000);
         System.out.println(2);
         Thread t = new Thread(()->{
         throw new RuntimeException("bbb");
         });
         t.setUncaughtExceptionHandler((thread, throwable)-> System.out.println(throwable));
         t.start();
         System.out.println(3);
         System.out.println(4);
         System.out.println(5); */

        /*timedRun2(() -> {
            while (true) {
                System.out.println(1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 2, TimeUnit.SECONDS);*/

        timedRun3(() -> {
            while (true) {
                System.out.println(1);
                try {
                    //System.out.println(1/0);
                    Thread.sleep(3000);
                    System.out.println(2);
                } catch (InterruptedException e) {
                    System.out.println(e);
                    throw new RuntimeException(e);
                }
            }
        }, 1, TimeUnit.SECONDS, Executors.newSingleThreadExecutor());
    }

    public static void timedRun2(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        class RethrowableTask implements Runnable {
            private volatile Throwable t;

            @Override
            public void run() {
                try {
                    r.run();
                } catch (Throwable t) {
                    this.t = t;
                }
            }

            void rethrow() {
                if (t != null) {
                    throw new RuntimeException(t);
                }
            }
        }

        RethrowableTask task = new RethrowableTask();
        final Thread taskThread = new Thread(task);
        taskThread.start();

        cancelExec.schedule(taskThread::interrupt, timeout, unit);

        taskThread.join(unit.toMillis(timeout + 1));
        task.rethrow();
    }

    public static void timedRun3(Runnable r, long timeout, TimeUnit timeUnit, ExecutorService taskExec)
            throws InterruptedException {
        Future<?> task = taskExec.submit(r);
        try{
            task.get(timeout, timeUnit);
        }catch(TimeoutException e){
            //接下来任务将被取消,finally中
        }catch(ExecutionException e){
            throw new RuntimeException(e.getCause());
        }finally {
            task.cancel(true);
        }
    }
}
