package osproj;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Very basic implementation of a thread pool.
 */
public class swmPool
{
    private lockQueue queue = new lockQueue();
    private boolean closed = true;
    private int curSize = 0;
    private int poolSize = 0;
    private ReentrantLock lock;
    
    public swmPool(int poolSize) 
    {
        this.poolSize = poolSize;
        closed = false;
        if (!closed)
        {
            throw new IllegalStateException("Pool already started.");
        }
        closed = false;
        for (int i = 0; i < poolSize; ++i)
        {
            new PooledThread().start();
        }
    }

    public void execute(Runnable job) throws InterruptedException
    {
        lock.lock();
        try {
            if (closed) {
                throw new PoolClosedException();
            }
            queue.push(job);
        } finally {
            lock.unlock();
        }
    }
    
    private class PooledThread extends Thread {
        
        @Override
        public void run() {
            while (true) {
                if (!queue.isEmpty())
                {
                    Runnable job = (Runnable) queue.pop();
                    try {
                        job.run();
                    } catch (Throwable t) {
                        // ignore
                    }
                }
            }
        }
    }
    
    private static class PoolClosedException extends RuntimeException {
        PoolClosedException() { 
            super ("PoolClosedException.");
        }
    }
}