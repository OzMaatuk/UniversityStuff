
import java.util.Arrays;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Student
 */
public class Q2
{
    public static void printMat(int mat[][])
    {
        System.out.println("matrix: ");
        for (int i = 0; i < mat.length; i++)
        {
            for (int j = 0; j < mat[0].length; j++)
            {
                System.out.print(mat[i][j] + ", ");
            }
            System.out.println("");
        }
    }
    
    /*
    public static int arrBSearch(int arr[], int num, int down, int up)
    {
        if (down < up)
        {
            int cur = (down+up)/2;
            if (arr[cur] > num)
            {
                arrBSearch(arr, num, down, cur-1);
            }
            else if (arr[cur] < num)
            {
                arrBSearch(arr, num, cur+1, up);
            }
            else
            {
                return cur;
            }
        }
        return down;
    }
    */
    
    public static int arrSearch(int mat[][], int arr[], int index) //O(N)
    {
        int cur = 0;
        for (int i = 0; i < index; i++)
        {
            if (arr[cur] == mat[i][i])
            {
                cur++;
            }
        }
        
        return cur;
    }
    
    public static int bSearch(int mat[][], int num, int down, int up)
    {
        if (down < up)
        {
            int cur = (down+up)/2;
            if (mat[cur][cur] > num)
            {
                bSearch(mat, num, down, cur-1);
            }
            else if (mat[cur][cur] < num)
            {
                bSearch(mat, num, cur+1, up);
            }
            else
            {
                return cur;
            }
        }
        return down+1;
    }
    
    public static int search(int mat[][], int num, int lim)
    {
        if (num > mat[lim][lim])
        {
            return lim+1;
        }
        else if (num <= mat[0][0])
        {
            return 0;
        }
        else
        {
            return bSearch(mat, num, 0, lim);
        }
    }
    
    public static int lis(int[] arr) //O(N*logN)
    {
        int mat[][] = new int[arr.length][arr.length];
        mat[0][0] = arr[0];
        int max = 1;
        
        for (int i = 1; i < arr.length; i++) //O(N)
        {
            int line = search(mat, arr[i], max-1); //O(logN)
            
            if (line == max)
            {
                mat[max][max] = arr[i];
                max++;
            }
            else
            {
                if (mat[line][line] > arr[i])
                {
                    mat[line][line] = arr[i];
                }
            }
        }
        
        printMat(mat);
        return max;
    }
    
    public static int lds(int[] arr) //O(N*logN)
    {
        int newArr[] = new int[arr.length];
        
        for (int i = 0; i < arr.length; i++) //O(N)
        {
            newArr[i] = -1 * arr[i];
        }
        
        return lis(newArr); //O(NlogN)
    }
    
    public static int lbs(int[] arr)
    {
        int lisMat[][] = new int[arr.length][arr.length];
        lisMat[0][0] = arr[0];
        int lisMax = 1;
        
        for (int i = 1; i < arr.length; i++)
        {
            int line = search(lisMat, arr[i], lisMax-1);
            
            if (line == lisMax)
            {
                lisMat[lisMax][lisMax] = arr[i];
                lisMax++;
            }
            else
            {
                if (lisMat[line][line] > arr[i])
                {
                    lisMat[line][line] = arr[i];
                }
            }
        }
        
        int newArr[] = new int[arr.length];
        
        for (int i = 0; i < arr.length; i++)
        {
            newArr[i] = -1 * arr[i];
        }
        
        int ldsMat[][] = new int[newArr.length][newArr.length];
        ldsMat[0][0] = newArr[0];
        int ldsMax = 1;
        
        for (int i = 1; i < newArr.length; i++)
        {
            int line = search(ldsMat, newArr[i], ldsMax-1);
            
            if (line == ldsMax)
            {
                ldsMat[ldsMax][ldsMax] = newArr[i];
                ldsMax++;
            }
            else
            {
                if (ldsMat[line][line] > newArr[i])
                {
                    ldsMat[line][line] = newArr[i];
                }
            }
        }
        
        int lisInx = lisMax-1;
        int ldsInx = 0;
        
        while (lisInx >= 0 && ldsInx <= ldsMax)
        {
            int upTill = arrSearch(lisMat, arr, lisInx);
            int downFrom = arrSearch(ldsMat, newArr, ldsInx);
            if (upTill < downFrom)
            {
                if (arr[upTill] >= newArr[downFrom])
                {
                    break;
                }
            }
            else
            {
                if (lisMat[lisInx][lisInx] > ldsMat[ldsInx][ldsInx])
                {
                    lisInx--;
                }
                else if (lisMat[lisInx][lisInx] <= ldsMat[ldsInx][ldsInx])
                {
                    ldsInx++;
                }
            }
        }
        
        return (lisInx + (ldsMax - ldsInx) + 1);
    }

    public static void main(String[] args) 
    {
        int arr[] = { 1,11,2,10,4,5,2,1};
        
        System.out.println("array: " + Arrays.toString(arr));
        System.out.println("");
        System.out.println("LIS: " + lis(arr));
        System.out.println("");
        System.out.println("LDS: " + lds(arr));
        System.out.println("");
        System.out.println("LBS: " + lbs(arr));
    }
}
