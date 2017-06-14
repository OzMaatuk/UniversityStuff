/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Student
 */
public class Point
{
    private int x, y;
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	public Point(){
		x = 0;
		y = 0;
	}
	public int x(){ return x; }
	public int y(){ return y; }
	public String toString(){
		return "("+x+","+y+")";
	}
}
