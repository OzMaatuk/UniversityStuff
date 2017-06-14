package osproj;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @course OS
 * @Exercise Main project
 * @author Oz 305181158, 
 */
public class cacheClass
{
    // define cache memory var
    private ArrayList<cacheObj> cacheMemory;
    private int cacheCount;
    private int size;
    private int floor;
    private int lowestIndex;
    private ReentrantLock lock;
    private cacheWriterTh cacheWriter;
    
    // cache writer - writes the count for object in the cache
    private class cacheWriterTh extends Thread
    {
        // queue of writing tasks
        private lockQueue<cacheObj> Q = new lockQueue<>();
        
        // add writing task
        public void addToQ(cacheObj newObj) throws InterruptedException
        {
            Q.push(newObj);
        }
        
        // if there is objects to write on in the queue,
        //check if there is a retzef
        // if does meahed otam
        // if not jusr increase by one
        @Override
        public void run()
        {
            cacheObj tmp1 = null;
            cacheObj tmp2 = null;
            int toAdd = 0;
            while (true)
            {
                if (!Q.isEmpty())
                {
                    tmp1 = Q.pop();
                    toAdd++;
                    if (!Q.isEmpty())
                    {
                        tmp2 = Q.pop();
                        while (tmp1.getReq() == tmp2.getReq())
                        {
                            toAdd++;
                        }
                    }
                    tmp1.addCount(toAdd);
                }
            }
        }
    }
    
    private class cacheReader implements Runnable
    {
        private cacheObj reqObj;
        
        public cacheReader(cacheObj newObj)
        {
            reqObj = newObj;
        }
        
        public void run()
        {
            // find in array
            int index = find(reqObj.getReq(), 0, cacheCount);
            if (index != -1)
            {
                try {
                    // if found, replace
                    reqObj.setCopy(cacheMemory.get(index));
                    // add for cache writing queue
                    cacheWriter.addToQ(cacheMemory.get(index));
                    // sends writer to DB
                } catch (InterruptedException ex) {
                    Logger.getLogger(cacheClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                // if not found, update
                reqObj.setAct("NOTINCACHE");
            }
        }
    }
    
    private class lowestCountTh implements Runnable
    {
        @Override
        public void run()
        {
            while (true)
            {
                for (int i = 0; i < cacheCount; i++)
                {
                    if (cacheMemory.get(i).getCount() < cacheMemory.get(lowestIndex).getCount())
                    {
                        lowestIndex = i;
                    }
                }
            }
        }
    }
    
    public cacheClass(int sNum, int cSize, int fNum, swmPool fPool)
    {
        // init variables
        size = cSize;
        floor = fNum;
        cacheMemory = new ArrayList<cacheObj>(size);
        cacheCount = 0;
        cacheWriter = new cacheWriterTh();
        cacheWriter.start();
        new Thread(new lowestCountTh()).start();
    }
    
    // find request OBJ in the cache
    public void getAns(cacheObj reqObj)
    {
        new Thread(new cacheReader(reqObj));
    }

    // push a new cacheObject to the cacheMemory
    public void updateToCache(cacheObj newObj) throws InterruptedException
    {
        // maybe without lock
        lock.lock();
        try {
            // if it not exist already
            if (find(newObj.getReq(), 0, cacheCount) == -1)
            {
                if (cacheMemory.get(lowestIndex).getCount() < newObj.getCount())
                {
                    cacheMemory.set(lowestIndex, newObj);
                }
            }
        } finally {
            lock.unlock();
        }
    }
	
    // binary search for cacheMemory by the reqNum
    private int find(int reqNum, int up, int down)
    {
        while (true)
        {
                if (reqNum >= cacheMemory.get(down).getReq() && reqNum <= cacheMemory.get(up).getReq())
                {
                        if (reqNum == cacheMemory.get(down).getReq())
                        {
                                return down;
                        }
                        if (reqNum == cacheMemory.get(up).getReq())
                        {
                                return up;
                        }
                        if ( (up+down)/2 != down)
                        {
                                if (reqNum > cacheMemory.get((up+down)/2).getReq())
                                {
                                        down = (up+down)/2;
                                }
                                else
                                {
                                        up = (up+down)/2;
                                }
                        }
                        else
                        {
                            break;
                        }
                }
        }
        return -1;
    }
}
