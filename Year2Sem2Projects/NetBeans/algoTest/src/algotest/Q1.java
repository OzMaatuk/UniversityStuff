/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Student
 */
public class Q1
{
    int[]x;
    int a;

    public Q1(int[]x2, int a2) //O(2(N^0.5))
    {
        x = x2;
        a = a2;
        
        System.out.println("numberOfBreaks: " + numberOfBreaks());
        System.out.println("numberOfChecking: " + numberOfChecking());
        System.out.println("floorIndex: " + (floorIndex() + 1));
    }

    public int numberOfBreaks() //O(2(N^0.5))
    {
        int balssNum = 2;
        int down = 0;
        int up = x.length-1;
        boolean found = false;
        int ans = 0;
        int count = 0;
        
        while (!found && balssNum > 0)
        {
            int cur = (down+up)/2;
            if (balssNum == 1)
            {
                for (int i = down; i <= up; i++)
                {
                    if (x[i] == a)
                    {
                        ans = i;
                        found = true;
                        break;
                    }
                    else if (x[i] > a)
                    {
                        count++;
                        ans = i-1;
                        found = true;
                        break;
                    }
                }
            }
            else
            {
                if (x[cur] > a)
                {
                    count++;
                    balssNum--;
                    up = cur-1;
                }
                else if (x[cur] < a)
                {
                    down = cur+1;
                }
                else
                {
                    found = true;
                    ans = cur;
                    break;
                }
            }
        }
        
        return count;
    }

    public int numberOfChecking() //O(2(N^0.5))
    {
        int balssNum = 2;
        int down = 0;
        int up = x.length-1;
        boolean found = false;
        int ans = 0;
        int count = 0;
        
        while (!found && balssNum > 0)
        {
            int cur = (down+up)/2;
            if (balssNum == 1)
            {
                for (int i = down; i <= up; i++)
                {
                    if (x[i] == a)
                    {
                        count++;
                        ans = i;
                        found = true;
                        break;
                    }
                    else if (x[i] > a)
                    {
                        count++;
                        ans = i-1;
                        found = true;
                        break;
                    }
                }
            }
            else
            {
                if (x[cur] > a)
                {
                    count++;
                    balssNum--;
                    up = cur-1;
                }
                else if (x[cur] < a)
                {
                    count++;
                    down = cur+1;
                }
                else
                {
                    found = true;
                    ans = cur;
                    break;
                }
            }
        }
        
        return count;
    }

    public int floorIndex() //O(2(N^0.5))
    {
        int balssNum = 2;
        int down = 0;
        int up = x.length-1;
        boolean found = false;
        int ans = 0;
        
        while (!found && balssNum > 0)
        {
            int cur = (down+up)/2;
            if (balssNum == 1)
            {
                for (int i = down; i <= up; i++)
                {
                    if (x[i] == a)
                    {
                        ans = i;
                        found = true;
                        break;
                    }
                    else if (x[i] > a)
                    {
                        ans = i-1;
                        found = true;
                        break;
                    }
                }
            }
            else
            {
                if (x[cur] > a)
                {
                    balssNum--;
                    up = cur-1;
                }
                else if (x[cur] < a)
                {
                    down = cur+1;
                }
                else
                {
                    found = true;
                    ans = cur;
                    break;
                }
            }
        }
        
        return ans;
    }
    
    public static void main(String[] args)
    {
        int arr[] = {2,4,6,8,10};
        int strong = 11;
        
        Q1 q1 = new Q1(arr, strong);
    }
}
