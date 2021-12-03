package concurrent.cancelAndClose;

import annotations.GuardedBy;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogService {
    private final BlockingQueue<String> queue;
    private final LoggerThread loggerThread;

    LogService(Writer writer, int capacity) {
        this.loggerThread = new LoggerThread(writer);
        this.queue = new LinkedBlockingQueue<>(capacity);
        this.isShutdown = false;
        this.reservations = 0;
    }

    @GuardedBy("this")
    private boolean isShutdown;
    @GuardedBy("this")
    private int reservations;

    public void start() {
        loggerThread.start();
    }

    public void stop() {
        synchronized (this) {
            isShutdown = true;
        }
        loggerThread.interrupt();//假如其在 queue.take() 处阻塞了
    }

    public void log(String msg) throws InterruptedException {
        synchronized (this) {
            if (isShutdown) {
                throw new IllegalStateException("log service already stopped");
            }
            ++reservations;
        }
        queue.put(msg);
    }

    private class LoggerThread extends Thread {
        private final PrintWriter writer;

        private LoggerThread(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        @Override
        public void run() {
            try {
                while (true) {
                    synchronized (LogService.this) {
                        if (isShutdown && reservations == 0) {
                            break;
                        }
                    }
                    String msg = queue.take();
                    synchronized (LogService.this) {
                        --reservations;
                    }
                    writer.println(msg);
                }
            } catch (InterruptedException e) {
                run(); // retry
            } finally {
                writer.close();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LogService log = new LogService(new OutputStreamWriter(System.out), 3);
        log.start();
        try {
            for (int i = 0; i < 100; i++) {
                log.log(String.valueOf(i));
                if (i == 90) {
                    log.stop();
                }
            }
        } catch (IllegalStateException e) {
            System.out.println(e);
        }
    }
}
