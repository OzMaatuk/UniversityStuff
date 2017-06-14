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
public class node {
    int num;
	ArrayList<node> arr = new ArrayList<node>();
	public node()
	{
		
	}
	
	public node(int x)
	{
		num = x;
	}
	
	public node(int x, ArrayList<node> a)
	{
		num = x;
		arr = a;
	}
	
	public void addSun(node n)
	{
		arr.add(n);
                n.getArr().add(this);
	}
	
	public ArrayList<node> getArr()
	{
		return arr;
	}
	
	public int getVal()
	{
		return num;
	}
        
        public String toString()
        {
            return " < " + num + " - " + arr.toString() + " > ";
        }
}
