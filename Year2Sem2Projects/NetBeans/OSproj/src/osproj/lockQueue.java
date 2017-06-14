package osproj;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @course OS
 * @Exercise Main project
 * @author Oz 305181158, 
 */
public class lockQueue<T>
{
    private ReentrantLock lock;
    private LinkedBlockingQueue<T> queue;
    
    public lockQueue()
    {
        lock = new ReentrantLock(true);
        queue = new LinkedBlockingQueue<>();
    }
    
    public void push(T newObj) throws InterruptedException
    {
        queue.put(newObj);
    }
    
    public T pop()
    {
        lock.lock();
        try
        {
            if (isEmpty())
            {
                return null;
            }
            return queue.poll();
        }
        finally
        {
            lock.unlock();
        }
    }
    
    public boolean isEmpty()
    {
        lock.lock();
        try
        {
            return queue.isEmpty();
        }
        finally
        {
            lock.unlock();
        }
    }
}
