package designPattern.singleton;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author yxf
 * @date 2021/12/1 10:55
 */
public class LoadBalancer {
    private static LoadBalancer instance = null;

    private List<String> serverList = null;

    private LoadBalancer() throws InterruptedException {
        serverList = new LinkedList<>();
    }

    public static LoadBalancer getInstance() throws InterruptedException {
        if (instance == null) {
            instance = new LoadBalancer();
        }
        return instance;
    }

    public void addServer(String server) {
        this.serverList.add(server);
    }

    public void removeServer(String server){
        this.serverList.remove(server);
    }

    public String getServer(){
        Random random = new Random();
        int i = random.nextInt(serverList.size());
        return serverList.get(i);
    }

    public static void main(String[] args) throws InterruptedException {
        LoadBalancer balancer1,balancer2,balancer3,balancer4;
        balancer1 = LoadBalancer.getInstance();
        balancer2 = LoadBalancer.getInstance();
        balancer3 = LoadBalancer.getInstance();
        balancer4 = LoadBalancer.getInstance();

        if( balancer1 == balancer2 && balancer2 == balancer3 && balancer3 == balancer4 ){
            System.out.println(true);
        }

        balancer1.addServer("Server 1");
        balancer1.addServer("Server 2");
        balancer1.addServer("Server 3");
        balancer1.addServer("Server 4");

        for( int i = 0; i < 10; i++ ){
            System.out.println(balancer1.getServer());
        }
    }

}


