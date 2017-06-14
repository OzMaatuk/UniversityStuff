
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Student
 */
public class Q3
{
    Node[][] nodes;
    Point p1, p2;

    public Q3(Node[][] nodes2, Point p1, Point p2)
    {
        nodes = nodes2;
        buildMat(nodes, p1, p2);
        System.out.println("cheapestPathPrice: " + cheapestPathPrice());
        System.out.println("numOfCheapestPaths: " + numOfCheapestPaths());
    }

    public int cheapestPathPrice() //O(1)
    {
        return nodes[nodes.length-1][nodes[0].length-1].price;
    }

    public int numOfCheapestPaths() //O(1)
    {
        //return nodes[nodes.length-2][nodes[0].length-2].numOfPaths;
        return nodes[nodes.length-1][nodes[0].length-1].numOfPaths;
    }

    public static void main(String[] args)
    {
        Point p1 = new Point(1,1);
        Point p2 = new Point(5,4);
        Node nodes[][] = initMatOfNodes2();
        
        Q3 q3 = new Q3(nodes, p1, p2);
        
        printMat(nodes);
        System.out.println("");
        //System.out.println(nodes[nodes.length-1][nodes[0].length-1].toString());
    }
    
    public static Node[][] initMatOfNodes2()
    {
		Node mat[][] = new Node[5][6];

                /*
		// the 1-st row
		mat[0][0] = new Node(1,3);
		mat[0][1] = new Node(8,4);
		mat[0][2] = new Node(3,8);
		mat[0][3] = new Node(0,4);
		// the 2-nd row
		mat[1][0] = new Node(2,5);
		mat[1][1] = new Node(5,11);
		mat[1][2] = new Node(3,1);
		mat[1][3] = new Node(0,2);
		// the 3-d row
		mat[2][0] = new Node(4,10);
		mat[2][1] = new Node(3,1);
		mat[2][2] = new Node(1,4);
		mat[2][3] = new Node(0,8);
		// the 4-th row
		mat[3][0] = new Node(2,0);
		mat[3][1] = new Node(3,0);
		mat[3][2] = new Node(5,0);
		mat[3][3] = new Node(0,0);
                */
                /*
                // the 1-st row
		mat[0][0] = new Node(10,1);
		mat[0][1] = new Node(2,4);
		mat[0][2] = new Node(12,5);
		mat[0][3] = new Node(9,1);
                mat[0][4] = new Node(1,2);
                mat[0][5] = new Node(0,5);
		// the 2-nd row
		mat[1][0] = new Node(3,10);
		mat[1][1] = new Node(4,4);
		mat[1][2] = new Node(2,2);
		mat[1][3] = new Node(8,5);
                mat[1][4] = new Node(4,6);
                mat[1][5] = new Node(0,7);
		// the 3-d row
		mat[2][0] = new Node(2,5);
		mat[2][1] = new Node(8,1);
		mat[2][2] = new Node(8,5);
		mat[2][3] = new Node(13,4);
                mat[2][4] = new Node(8,5);
                mat[2][5] = new Node(0,5);
		// the 4-th row
		mat[3][0] = new Node(2,3);
		mat[3][1] = new Node(7,10);
		mat[3][2] = new Node(4,8);
		mat[3][3] = new Node(3,2);
                mat[3][4] = new Node(4,2);
                mat[3][5] = new Node(0,3);
                // the 5-th row
		mat[4][0] = new Node(1,10);
		mat[4][1] = new Node(2,4);
		mat[4][2] = new Node(9,7);
		mat[4][3] = new Node(5,11);
                mat[4][4] = new Node(1,3);
                mat[4][5] = new Node(0,4);
                // the 5-th row
		mat[5][0] = new Node(8,0);
		mat[5][1] = new Node(5,0);
		mat[5][2] = new Node(15,0);
		mat[5][3] = new Node(4,0);
                mat[5][4] = new Node(10,0);
                mat[5][5] = new Node(0,0);
                */
                                    
               for (int i = 0; i < mat.length; i++)
               {
                   for (int j = 0; j < mat[0].length; j++)
                   {
                       mat[i][j] = new Node(1,1);
                   }
               }
               
               return mat;
	}
    
    public static void buildMat(Node[][] nodes, Point p1, Point p2) //O(N*M)
    {
        nodes[0][0].numOfPaths = 0;
        nodes[0][0].price = 0;
        
        for (int i = 1; i < nodes.length; i++) //O(N)
        {
            if (i >= p1.y() && i < p2.y() && 0 >= p1.x() && 0 < p2.x())
            {
                nodes[i][0].price = 1000000;
                nodes[i][0].numOfPaths = 0;
            }
            else
            {
                nodes[i][0].price = nodes[i-1][0].price + nodes[i-1][0].y;
                nodes[i][0].numOfPaths = 1;
            }
        }
        
        for (int j = 1; j < nodes[0].length; j++) //O(M)
        {
            if (j >= p1.x() && j < p2.x() && 0 >= p1.y() && 0 <= p2.y())
            {
                nodes[0][j].price = 1000000;
                nodes[0][j].numOfPaths = 0;
            }
            else
            {
                nodes[0][j].price = nodes[0][j-1].price + nodes[0][j-1].x;
                nodes[0][j].numOfPaths = 1;
            }
        }
        
        for (int i = 1; i < nodes.length; i++) //O((M-1)*(N-1))
        {
            for (int j = 1; j < nodes[0].length; j++)
            {
                if (j >= p1.x() && j < p2.x() &&  i >= p1.y() && i < p2.y())
                {
                    nodes[i][j].price = 1000000;
                    nodes[i][j].numOfPaths = 0;
                }
                else
                {
                    int left = nodes[i][j-1].price + nodes[i][j-1].x;
                    int down = nodes[i-1][j].price + nodes[i-1][j].y;

                    if (down < left)
                    {
                        nodes[i][j].price = down;
                        nodes[i][j].numOfPaths = nodes[i-1][j].numOfPaths;
                    }
                    else if (left < down)
                    {
                        nodes[i][j].price = left;
                        nodes[i][j].numOfPaths = nodes[i][j-1].numOfPaths;
                    }
                    else
                    {
                        nodes[i][j].price = left;
                        nodes[i][j].numOfPaths = nodes[i][j-1].numOfPaths + nodes[i-1][j].numOfPaths;
                    }
                }
            }
        }
    }
    
    public static void setBlack(Node[][] nodes, Point p1, Point p2) //O(M*N)
    {
        for (int i = p1.y(); i < p2.y(); i++)
        {
            for (int j = p1.x(); j < p2.x(); j++)
            {
                nodes[i][j].price = Integer.MAX_VALUE;
            }
        }
    }

    public static void printMat(Node[][] nodes)
    {
        System.out.println("prices mat");
        for (int i = 0; i < nodes.length; i++)
        {
            for (int j = 0; j < nodes[0].length; j++)
            {
                System.out.print(nodes[i][j].price + ", ");
            }
            System.out.println("");
        }
        System.out.println("");
        
        System.out.println("paths mat");
        for (int i = 0; i < nodes.length; i++)
        {
            for (int j = 0; j < nodes[0].length; j++)
            {
                System.out.print(nodes[i][j].numOfPaths + ", ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
