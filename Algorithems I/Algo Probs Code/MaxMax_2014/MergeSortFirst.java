import java.util.Arrays;


public class MergeSortFirst {
	public static void mergeSort(int arr[]){
		mergeSort(arr, 0, arr.length);
	}
	public static void mergeSort(int arr[], int low, int high){
		// sort a[low, high)
		int n = high - low;
		if (n <= 1) return;
		int mid = (low + high)/2;
		mergeSort(arr, low, mid);
		mergeSort(arr, mid, high);
		int [] temp = new int[n];
		int i = low, j = mid, k=0;
		// merge two arrays: arr[low, mid), arr[mid, high)
		while(i<mid && j<high){
			if (arr[j] < arr[i]) temp[k++] = arr[j++];
			else  temp[k++] = arr[i++];
		}
		while (i<mid) temp[k++] = arr[i++];
		while (j<high) temp[k++] = arr[j++];

		for(int p=0; p<n; p++) arr[low + p] = temp[p];
	}
	private static boolean isSorted(int[] a) {
		for (int i = 1; i < a.length; i++)
			if (a[i] < a[i-1]) return false;
		return true;
	}
	public static void main(String[] args) {
		int size = 10000000;
		//int a[] = {5,4,8,1};
		int[] a = MyLibrary.randomIntegerArray(size);
		//MyLibrary.printIntegerArray(a);
		long  timeBefore = System.currentTimeMillis();
		mergeSort(a);
		long timeAfter = System.currentTimeMillis();
		double elapse = (timeAfter-timeBefore)/1000.0;;
		System.out.println("MergeSort time = " +  + elapse+" seconds");	
		System.out.println("is sorted? "+isSorted(a));
		//MyLibrary.printIntegerArray(a);
		// java Sort
		a = MyLibrary.randomIntegerArray(size);
		timeBefore = System.currentTimeMillis();
		Arrays.sort(a);
		timeAfter = System.currentTimeMillis();
		elapse = (timeAfter-timeBefore)/1000.0;
		System.out.println("Java Sort time = " + elapse+" seconds");		
		System.out.println("is sorted? "+isSorted(a));
	}

}
