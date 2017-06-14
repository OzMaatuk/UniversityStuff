/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Student
 */
public class BFS
{
    static ArrayList<ArrayList<Integer>> G;
    static LinkedBlockingQueue Q;
    static int n;
    static int colors[];
    static int father[];
    static int dis[];
    static final int max = 99999;
    
    public static ArrayList getGraph()
    {
        int size = 7;
        G = new ArrayList<>();
        for (int i = 0; i <= size; i++)
        {
            G.add(new ArrayList<>());
        }
        G.get(0).add(1);
        G.get(1).add(0);
        G.get(1).add(2);
        G.get(2).add(1);
        G.get(2).add(3);
        G.get(3).add(2);
        G.get(3).add(4);
        G.get(3).add(5);
        G.get(4).add(3);
        G.get(4).add(5);
        G.get(4).add(6);
        G.get(5).add(3);
        G.get(5).add(4);
        G.get(5).add(5);
        G.get(5).add(7);
        G.get(6).add(4);
        G.get(6).add(5);
        G.get(6).add(7);
        G.get(7).add(5);
        G.get(7).add(6);
        
        return G;
    }
    
    public static void doBFS(int root)
    {
        Q.add(root);
        colors[root] = 1;
        dis[root] = 0;
        while (!Q.isEmpty())
        {
            int cur = (int) Q.poll();
            if (colors[cur] != 2)
            {
                for (int i = 0; i < G.get(cur).size(); i++)
                {
                    if (colors[G.get(cur).get(i)] == 0)
                    {
                        father[G.get(cur).get(i)] = cur;
                        if (dis[G.get(cur).get(i)] > dis[cur]+1)
                        {
                            dis[G.get(cur).get(i)] = dis[cur]+1;
                        }
                        colors[G.get(cur).get(i)] = 1;
                        Q.add(G.get(cur).get(i));
                    }
                }
                colors[cur] = 2;
            }
        }
        print();
    }
    
    public static void print(){
	System.out.println("\n pred: " + Arrays.toString(father));
	System.out.println("\n dist: " + Arrays.toString(dis));
	System.out.println("\n color: " + Arrays.toString(colors));
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        G = getGraph();
        Q = new LinkedBlockingQueue();
        n = G.size();
        colors = new int[n];
        father = new int [n];
        dis = new int[n];
        
        for (int i = 0; i < n; i++)
        {
            colors[i] = 0;
            father[i] = -1;
            dis[i] = max;
        }
        
        int root = 2;
        doBFS(root);
        
        System.out.println("\n ------------- DONE ------------- \n");
    }
    
}
