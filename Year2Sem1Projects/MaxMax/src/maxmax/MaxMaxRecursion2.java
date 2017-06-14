package maxmax;

import java.util.Stack;
class NodeRec{                                   // (could be other items)
	int num;
	Stack<Integer> st;
	public NodeRec(int num){
		this.num = num;
		st = new Stack<Integer>();
	}
}
public class MaxMaxRecursion2 {
	// Recursive function
	static int comparisons = 0;
	private static int maxMax2(NodeRec arr[], int low, int high){
		if (low < high){
			int index = 0;
			int middle = (low + high)/2;
			int i = maxMax2(arr, low, middle);
			int j = maxMax2(arr, middle+1, high);
			comparisons++;
			if (arr[i].num>arr[j].num){
				arr[i].st.push(arr[j].num);
				index = i;
			}
			else{
				arr[j].st.push(arr[i].num);
				index = j;
			}
			return index;
		}
		else return low;		   
	}
	public static void maxMax2(int []a){// External function
		int size = a.length;
		comparisons = 0;
		NodeRec []arr = new NodeRec[size];
		for (int i=0; i<size; i++) arr[i] = new NodeRec(a[i]);
		int index = maxMax2(arr, 0, size-1);
		//System.out.println("index = "+index+"  max1 = "+arr[index].num);
		int max2 = arr[index].st.pop();
		while(!arr[index].st.empty()){
			int temp = arr[index].st.pop();
			comparisons++;
			if (temp>max2) max2=temp;
		}
		//System.out.println("max2 = "+max2);
		//System.out.println("comparisons = "+comparisons);
	}
	public static void main(String[] args) {
		int size = 1000000, loop = 100;
		double diff = 0;
		int comp = 0;
		//int a[] = {1,10,8,9,5,20,11,18,19};
		System.out.println("size = " + size + ";  loop = " + loop);
		for (int i = 1; i<=loop; i++){
			int [] a = MyLibrary.randomIntArrayOfDiffNumbers(size);
			long start = System.currentTimeMillis();
			maxMax2(a);
			long end = System.currentTimeMillis();
			diff = diff + ((double)(end - start))/1000.0;
			comp = comp + comparisons;
		}
		System.out.println("twoMaxRecurs (comp = n + log(n))");
		System.out.printf("time twoMaxRecurs: %7.3f", diff/loop);
		System.out.println(";  number of comparisons: " + comp/loop);
	}
}
/**
	index = 964998  max1 = 1000000
	max2 = 999999
	comparisons = 999999
	size = 1000000, time MaxMaxRecursion2: 0.889
 */
