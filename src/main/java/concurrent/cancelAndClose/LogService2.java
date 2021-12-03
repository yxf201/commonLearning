package concurrent.cancelAndClose;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

public class LogService2 {
    private static final long TIMEOUT = 1;
    private static final TimeUnit UNIT = TimeUnit.SECONDS;
    private final ExecutorService exec = Executors.newSingleThreadExecutor();

    private final Writer writer;

    LogService2(Writer writer){
        this.writer = writer;
    }

    public void stop() throws InterruptedException, IOException {
        try{
            exec.shutdown();
            exec.awaitTermination(TIMEOUT, UNIT);
        }finally {
            writer.close();
        }
    }

    public void log(String msg){
        try{
            exec.execute(new WriterTask(msg));
        }catch (RejectedExecutionException ignored){}
    }

    class WriterTask implements Runnable {
        private final String msg;
        public WriterTask(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            try {
                writer.write(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
