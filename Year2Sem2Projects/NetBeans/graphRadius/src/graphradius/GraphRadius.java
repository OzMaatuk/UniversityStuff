/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphradius;

import java.util.Vector;

/**
 *
 * @author Oz
 */
public class GraphRadius {

    public static Vector[]  initTree0()
    {
	int length = 8;
	Vector[] tree = new Vector[length];//graph (tree)array of vectors
	for (int i=0; i<length; i++){
            tree[i] = new Vector(length);
	}
        //*****  vertex 0  *****
	tree[0].add(1);
        tree[0].add(2);
	//*****  vertex 1  *****
        tree[1].add(0);
	tree[1].add(6);
	tree[1].add(7);
	//*****  vertex 2  *****
        tree[2].add(0);
	tree[2].add(3);
	//*****  vertex 3  *****
        tree[3].add(2);
	tree[3].add(4);
	//*****  vertex 4  *****
        tree[4].add(3);
	tree[4].add(5);
        //*****  vertex 5  *****
	tree[5].add(4);       
        //*****  vertex 6  *****
        tree[6].add(1);
        //*****  vertex 7  *****
	tree[7].add(1);
	
	return tree;
    }

    public static int[] buildDeg(Vector a[])
    {
        int ans[] = new int[a.length];
        
        for (int i = 0; i < a.length; i++)
        {
                ans[i] = a[i].size();
        }
        
        return ans;
    }

    public static Vector buildLea(Vector a[])
    {
        Vector ans = new Vector();
        
        for (int i = 0; i < a.length; i++)
        {
                if (a[i].size() == 1)
                {
                    ans.add(i);
                }
        }
        
        return ans;
    }
    
    public static int rank(Vector a[])
    {
        int r = 0;
        for (int i = 0; i < a.length; i++)
        {
            if (1 < a[i].size())
            {
                r++;
            }
        }
        return r;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        int radius = 0;
        int center[] = new int[8];
        
        Vector[] vec = initTree0();
        Vector leaves = buildLea(vec);
        
        while (rank(vec) > 1)
        {
            System.out.println(leaves.toString());
            for (int i = 0; i < leaves.size(); i++)
            {
                int index = (int) leaves.get(i);
                int aba = (int) vec[index].get(0);
                System.out.println("remove "+vec[index].get(0)+" from "+index);
                vec[index].remove(0);
                
                for (int j = 0; j < vec[aba].size(); j++)
                {
                    if (vec[aba].get(j) == leaves.get(i))
                    {
                        System.out.println("remove "+vec[aba].get(j)+" from "+aba);
                        vec[aba].remove(j);
                    }
                }
            }
            radius++;
            leaves = buildLea(vec);
        }
        radius ++;
        
        int centerIndex = 0;
        for (int i = 0; i < vec.length; i++)
        {
            if (vec[i].size() != 0)
            {
                center[centerIndex] = i;
                centerIndex++;
            }
        }
        
        System.out.println("radius: " + radius);
        System.out.println("center: " + center[0] +","+ center[1]);
    }
    
}
