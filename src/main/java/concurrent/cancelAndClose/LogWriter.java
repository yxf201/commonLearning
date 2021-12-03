package concurrent.cancelAndClose;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogWriter {
    private final BlockingQueue<String> queue;
    private final LoggerThread logger;

    LogWriter(Writer writer, int capacity){
        this.queue = new LinkedBlockingQueue<>(capacity);
        this.logger = new LoggerThread(writer);
    }

    public void start(){
        this.logger.start();
    }

    public void log(String msg) throws InterruptedException {
        queue.put(msg);
    }

    private class LoggerThread extends Thread{
        private final PrintWriter writer;

        LoggerThread(Writer writer){
            this.writer = new PrintWriter(writer);
        }

        public void run(){
            try{
                while (true){
                    writer.println(queue.take());
                }
            }catch(InterruptedException ignored){}
            finally {
                writer.close();
            }
        }
    }
}
