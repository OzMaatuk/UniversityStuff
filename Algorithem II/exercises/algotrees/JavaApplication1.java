/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.ArrayList;

/**
 *
 * @author Student
 */
public class JavaApplication1 {

public static node buildTree()
    {
            node n0 = new node(0);
            node n1 = new node(1);
            node n2 = new node(2);
            node n3 = new node(3);
            node n4 = new node(4);
            node n5 = new node(5);

            n0.addSun(n1);
            n0.addSun(n2);
            n1.addSun(n5);
            n2.addSun(n3);
            n2.addSun(n4);

            return n0;
    }

    public static void buildArray(node n0, ArrayList<node> arr)
    {
            arr.add(new node(n0.getVal(), n0.getArr()));
            for (node x : n0.getArr())
            {
                    buildArray(x, arr);
            }
    }

    public static void buildDeg(ArrayList<Integer> d, ArrayList<node> a)
    {
            for (node x : a)
            {
                    d.add(x.getArr().size());
            }
    }

    public static void buildLea(ArrayList<Integer> l, ArrayList<node> a)
    {
            for (int i = 0; i < a.size(); i++)
            {
                    if (a.get(i).getArr().size() == 1)
                    {
                            l.add(i);
                    }
            }
    }

/**
 * @param args the command line arguments
 */
public static void main(String[] args) {
            node n0 = buildTree();
            ArrayList<node> arr = new ArrayList<>();
            buildArray(n0, arr);
            //System.out.println(arr.toString());
            
            //ArrayList<Integer> deg = new ArrayList<Integer>();
            //buildDeg(deg, arr);

            ArrayList<Integer> leaves = new ArrayList<>();
            buildLea(leaves, arr);
            System.out.println(leaves.toString());

            while (arr.size() > 2)
            {
                    for (int i = 0; i < leaves.size(); i++)
                    {
                            int dad = arr.get(leaves.get(i)).getArr().get(0).getVal();
                            for (int j = 0; j < arr.get(dad).getArr().size(); j++)
                            {
                                if (arr.get(dad).getArr().get(j).getVal() == arr.get(leaves.get(i)).getVal())
                                {
                                    arr.get(dad).getArr().remove(j);
                                }
                            }
                    }
                    buildLea(leaves, arr);
            }

            System.out.println(arr.toString());
    }
}

