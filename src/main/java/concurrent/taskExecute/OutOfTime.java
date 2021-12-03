package concurrent.taskExecute;

import java.util.Timer;
import java.util.TimerTask;

public class OutOfTime {
    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(), 1);
        Thread.sleep(1);
        System.out.println(1);
        timer.schedule(new ThrowTask(), 5);
        //Exception in thread "Timer-0" Exception in thread "main" java.lang.IllegalStateException: Timer already cancelled.
        Thread.sleep(5);
        System.out.println(2);
    }

    private static class ThrowTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("run");
            throw new RuntimeException();
            //Exception in thread "Timer-0" java.lang.RuntimeException
        }
    }
}
