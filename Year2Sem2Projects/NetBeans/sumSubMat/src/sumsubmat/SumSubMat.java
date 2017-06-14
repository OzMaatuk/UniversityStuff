/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sumsubmat;

/**
 *
 * @author Oz
 */
public class SumSubMat
{
    public static int[] kadane(int arr[])
    {
        int ans[] = new int[3];
        int max = arr[0];
        int sum = 0;
        int start = 0;
        int end = 0;
        
        for (int i = 0; i < arr.length; i++)
        {
            sum = sum + arr[i];
            if (sum < 0)
            {
                sum = 0;
                start = i;
            }
            if (sum > max)
            {
                max = sum;
                end = i;
            }
        }
        ans[0] = start;
        ans[1] = end;
        ans[2] = max;
        return ans;
    }
    
    public static int cyclKadane(int arr[])
    {
        int max1 = kadane(arr)[2];
        int sum = 0;
        for (int i = 0; i < arr.length; i++)
        {
            sum = sum + arr[i];
            arr[i] = -arr[i];
        }
        
        int max2 = sum + kadane(arr)[2];
        if (max1 > max2)
        {
            return max1;
        }
        return max2;
    }
    
    public static int[] sumCols(int mat[][], int jStart, int jEnd)
    {
        int ans[] = new int[mat[0].length];
        for (int i = 0; i < mat.length; i++)
        {
            for (int j = jStart; j < jEnd; j++)
            {
                ans[i] = ans[i] + mat[j][i];
            }
        }
        return ans;
    }
    
    public static int[] getMaxSubMat(int mat[][])
    {
        int max = mat[0][0];
        int iStart = 0;
        int iEnd = 0;
        int jStart = 0;
        int jEnd = 0;
        
        for (int i = 0; i < mat[0].length; i++)
        {
            for (int j = i; j < mat[0].length; j++)
            {
                int colSum[] = sumCols(mat, j, j);
                int res[] = kadane(colSum);
                
                if (max < res[2])
                {
                    max = res[2];
                    iStart = res[0];
                    iEnd = res[1];
                    jStart = i;
                    jEnd = j;
                }
            }
        }
        
        int ans[] = new int[5];
        ans[0] = max;
        ans[1] = iStart;
        ans[2] = iEnd;
        ans[3] = jStart;
        ans[4] = jEnd;
        
        return ans;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        
    }
    
}
