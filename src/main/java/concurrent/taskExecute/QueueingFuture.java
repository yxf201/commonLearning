package concurrent.taskExecute;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class QueueingFuture<V> extends FutureTask<V> {
    QueueingFuture(Callable<V> c, BlockingQueue<FutureTask<V>> completionQueue){
        super(c);
        this.completionQueue = completionQueue;
    }

    QueueingFuture(Runnable t, V r, BlockingQueue<FutureTask<V>> completionQueue){
        super(t, r);
        this.completionQueue = completionQueue;
    }

    private BlockingQueue<FutureTask<V>> completionQueue;

    @Override
    protected void done(){
        completionQueue.add(this);
    }
}
