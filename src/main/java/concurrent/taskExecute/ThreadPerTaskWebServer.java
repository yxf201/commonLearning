package concurrent.taskExecute;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class ThreadPerTaskWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            Socket connection = socket.accept();
            Runnable task = () -> handleRequest(connection);
            new Thread(task).start();
        }
    }

    private static void handleRequest(Socket connection) {
    }

    @Test
    public void t1() throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> System.out.println(1));
        //executor.shutdown();
        boolean b = executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
        System.out.println(b);
        System.out.println(executor.isTerminated());
    }
}
