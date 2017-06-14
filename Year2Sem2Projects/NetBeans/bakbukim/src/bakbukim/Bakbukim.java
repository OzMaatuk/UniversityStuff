/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bakbukim;

/**
 *
 * @author Oz
 */
public class Bakbukim
{
    public static int max(int x, int y)
    {
        if (x > y)
        {
            return x;
        }
        return y;
    }
    
    public static int min(int x, int y)
    {
        if (x < y)
        {
            return x;
        }
        return y;
    }
    
    public static int indexOf(int i, int j, int n)
    {
        return (n+1)*i+j;
    }
    
    public static void FW(int mat[][])
    {
        int n = mat.length;
        int m = mat[0].length;
        
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                for (int k = 0; k < j; k++)
                {
                    if (mat[i][j] == 0 || mat[i][j] > mat[i][k] + mat[k][j])
                    {
                        if (mat[i][k] != 0 && mat[k][j] != 0)
                        {
                            mat[i][j] = mat[i][k] + mat[k][j];
                        }
                    }
                }
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        int n = 4;
        int m = 3;
        int mat[][] = new int[n+1][m+1];
        String paths[][] = new String[n+1][m+1];
        
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                mat[indexOf(i, j, n)][indexOf(i, m, n)] = 1;
                mat[indexOf(i, j, n)][indexOf(n, j, n)] = 1;
                mat[indexOf(i, j, n)][indexOf(i, 0, n)] = 1;
                mat[indexOf(i, j, n)][indexOf(0, j, n)] = 1;
                mat[indexOf(i, j, n)][indexOf(max(0,m-i-j),min(m,i+j),n)] = 1;
                mat[indexOf(i, j, n)][indexOf(min(n,i+j),max(n-i-j,0),n)] = 1;
            }
        }
        
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                paths[indexOf(i, j, n)][indexOf(i, m, n)] = "("+i+","+j+")->("+i+","+m+")";
                paths[indexOf(i, j, n)][indexOf(n, j, n)] = "("+i+","+j+")->("+n+","+j+")";;
                paths[indexOf(i, j, n)][indexOf(i, 0, n)] = "("+i+","+j+")->("+i+","+0+")";;
                paths[indexOf(i, j, n)][indexOf(0, j, n)] = "("+i+","+j+")->("+0+","+m+")";;
                paths[indexOf(i, j, n)][indexOf(max(0,m-i-j),min(m,i+j),n)] = "("+i+","+j+")->("+max(0,m-i-j)+","+min(m,i+j)+")";;
                paths[indexOf(i, j, n)][indexOf(min(n,i+j),max(n-i-j,0),n)] = "("+i+","+j+")->("+min(n,i+j)+","+max(n-i-j,0)+")";;
            }
        }
    }
    
}
