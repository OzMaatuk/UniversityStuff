package gametree;

public class GameTree
{
    public static void main(String[] args)
    {
        int arr[] = {};
        Node root = new Node(0, arr.length, new Node());
        Node tmp = root;
        int i = 0;
        int j = arr.length - 1;
        buildTree(i, j, tmp);
        
        tmp = root;
        
    }
    
    public static void buildTree(int i, int j, Node tmp)
    {
        if (i != j)
        {
            tmp.setSon1(new Node(i+1, j, tmp));
            tmp.setSon2(new Node(i, j+1, tmp));
            buildTree(i+1, j, tmp.son1);
            buildTree(i, j+1, tmp.son2);
        }
    }
    
    public static void setHefresh(Node tmp, int[] arr)
    {
        int i = tmp.low;
        int j = tmp.high;
        int sumP1 = 0;
        int sumP2 = 0;
        if (arr[i] == arr[j])
        {
            sumP2 = sumP2 + arr[i];
        }
        else if (arr[i] > arr[j])
        {
            
        }
        else
        {
            
        }
    }
}
