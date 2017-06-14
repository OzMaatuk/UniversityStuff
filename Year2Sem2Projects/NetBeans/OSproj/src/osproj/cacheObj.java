package osproj;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @course OS
 * @Exercise Main project
 * @author Oz 305181158, 
 */
public class cacheObj
{
    private int quest = 0;
    private int ans = 0;
    private int count = 0;
    private String act = "";
    private ReentrantLock lock;
    
    public cacheObj(String newAct, int a, int q, int c)
    {
        act = newAct;
        ans = a;
        quest = q;
        count = c;
    }
    
    public cacheObj(cacheObj newObj)
    {
        act = newObj.getAct();
        ans = newObj.getAns();
        quest = newObj.getReq();
        count = newObj.getCount();
    }
    
    public cacheObj(String newAct, int q)
    {
        act = newAct;
        quest = q;
    }
    
    public void setCopy(cacheObj newObj)
    {
        act = newObj.getAct();
        ans = newObj.getAns();
        quest = newObj.getReq();
        count = newObj.getCount();
    }
    
    public void addCount()
    {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }
    
    public void addCount(int x)
    {
        lock.lock();
        try {
            count = count + x;
        } finally {
            lock.unlock();
        }
    }
    
    public int getAns()
    {
        return ans;
    }
    
    public int getReq()
    {
        return quest;
    }
    
    public int getCount()
    {
        return count;
    }
    
    public String getAct()
    {
        return act;
    }
    
    public int[] getArr()
    {
        int ansArr[] = new int[3];
        ansArr[0] = quest;
        ansArr[1] = ans;
        ansArr[2] = count;
        return ansArr;
    }
    
    public void setAct(String newAct)
    {
        act = newAct;
    }
    
    public void setAns(int newAns)
    {
        ans = newAns;
    }
}
