/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametree;

/**
 *
 * @author Oz
 */
public class Node
{
    int low;
    int high;
    Node father = null;
    Node son1 = null;
    Node son2 = null;
    int Hefresh = 0;
    
    public Node()
    {
        this.low = 0;
        this.high = 0;
    }
    
    public Node(int i, int j, Node aba)
    {
        this.low = i;
        this.high = j;
        this.father = aba;
    }
    
    public void setSon1(Node s)
    {
        this.son1 = s;
    }
    
    public void setSon2(Node s)
    {
        this.son2 = s;
    }
    
    public void setFather(Node f)
    {
        this.father = f;
    }
}
