package concurrent.taskExecute;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TaskExecutionWebServer {
    private static final int N_THREADS = 100;
    private static final Executor exec =
            Executors.newFixedThreadPool(N_THREADS);

    public static void main( String[] args ) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            Socket connection = socket.accept();
            Runnable task = () -> handleRequest(connection);
            exec.execute(task);
        }
    }

    private static void handleRequest(Socket connection) {
    }

    private class ThreadPerTaskExecutor implements Executor{
        @Override
        public void execute(Runnable command) {
            new Thread(command).start();
        }
    }

    private class WithinThreadExecutor implements Executor{
        @Override
        public void execute(Runnable r){
            r.run();
        }
    }
}
