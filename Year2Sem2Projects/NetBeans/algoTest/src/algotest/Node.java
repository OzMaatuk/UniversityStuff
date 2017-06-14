/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Student
 */
public class Node
{
    int x, y, price, numOfPaths;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.price = 0;
        this.numOfPaths = 0;
    }

    public String toString() {
        return "x=" + x + " y=" + y + " price=" + price + " np=" + numOfPaths + ";";
    }
}
