package concurrent.taskExecute;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.http.HttpRequest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class LifecycleWebServer {
    private final ExecutorService exec;

    public LifecycleWebServer(ExecutorService exec){
        this.exec = exec;
    }

    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while( !exec.isShutdown() ){
            try{
                Socket connection = socket.accept();
                exec.execute(() -> handleRequest(connection));
            }catch(RejectedExecutionException e){
                if( !exec.isShutdown() ){
                    log("task submission rejected", e);
                }
            }
        }
    }

    public void stop(){
        exec.shutdown();
    }

    private void handleRequest(Socket connection) {
        HttpRequest req = readRequest(connection);
        if( isShutdownRequest(req) ){
            stop();
        }else{
            dispatchRequest(req);
        }
    }

    private void dispatchRequest(HttpRequest req) {
    }

    private boolean isShutdownRequest(HttpRequest req) {
        return false;
    }

    private HttpRequest readRequest(Socket connection) {
        return null;
    }

    private void log(String task_submission_rejected, Exception e) {
    }

}
