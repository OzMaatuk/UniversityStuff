package maxmax;

import java.util.Arrays;
/**
   Three algorithms search for 2 largest elements of an array. 
   Each function returns the number of comparisons
   A={arr[0], arr[1], . . . ,arr[arr.length]}
   max1 = maximum{A}
   max2 = maximum{A\max1}
   Remark: the start values of max1, max2 must be different.
   Assumptions: 
   				1)elements of the array are different
   				2) array length > 2
 **/
public class MaxMax {
	/**
	 * The function twoMaxGreatThanMax1 
	 * searches two largest elements of the an array
	 * the first check is: a[i]>max1 
	 * @param arr - the array
	 * @return variable count the number of comparisons
	 */
	public static int twoMaxGreatThanMax1(int arr[]){//2n comparisons
		// initialization
		int max1 = arr[0];
		int max2 = arr[1];
		if (max1 < max2){
			max1 = arr[1];
			max2 = arr[0];
		}
		// the main loop
		int comparisons = arr.length - 2;
		for (int i=2; i<arr.length; i++){
			// the first comparison
			if(arr[i] > max1){
				max2=max1;
				max1=arr[i];
			}
			else{
				comparisons++;
				if(arr[i]>max2) max2=arr[i];
			}
		}
		// the result:
		//System.out.println("max1 = "+max1+", max2 = "+max2);
		return comparisons;
	}
	/**
	 * The function twoMaxGreatThanMax2 
	 * searches two largest elements of the an array
	 * the first check is: a[i]>max2 
	 * @param arr - the array
	 * @return variable count the number of comparisons
	 */
	public static int twoMaxGreatThanMax2(int arr[]){//n comparisons
		// initialization
		int max1 = arr[0];
		int max2 = arr[1];
		if (max1 < max2){
			max1 = arr[1];
			max2 = arr[0];
		}
		int comparisons = arr.length - 2;
		// the main loop
		for (int i=2; i<arr.length; i++){
			// the first comparison
			if(arr[i] > max2){ 
				comparisons++;
				if (arr[i] < max1) max2 = arr[i];
				else{
					max2 = max1;
					max1 = arr[i];
				}
			}
		}
		// print the result:
		//System.out.println("max1 = "+max1+", max2 = "+max2);
		return comparisons;
	}
	public static int twoMaxPairs1(int arr[]){//3/2n comparisons
		// initialization
		int max1 = arr[0];
		int max2 = arr[1];
		if (max1 < max2){
			max1 = arr[1];
			max2 = arr[0];
		}
		int comparisons = arr.length/2 - 1;
		// the main loop
		for (int i=2; i<arr.length-1; i = i + 2){
			if (arr[i] > arr[i+1]){
				comparisons++;
				if (arr[i] > max1){
					max2 = max1;
					max1 = arr[i];
				}
				else{
					comparisons++;
					if (arr[i] > max2) max2 = arr[i];
				}
			}
			else{
				comparisons++;
				if (arr[i+1] > max1){
					max2 = max1;
					max1 = arr[i+1];
				}
				else{
					comparisons++;
					if (arr[i+1] > max2) max2 = arr[i+1];
				}
			}
		}
		comparisons++;
		if (arr.length%2 != 0){
			comparisons++;
			if (arr[arr.length] > max1){
				max2 = max1;
				max1 = arr[arr.length];
			}
			else {
				comparisons++;
				if (arr[arr.length] > max2) max2 = arr[arr.length];
			}
		}
		// print the result:
		//System.out.println("max1 = "+max1+", max2 = "+max2);
		return comparisons;
	}
	public static int twoMaxPairs2(int arr[]){//n comparisons
		// initialization
		int max1 = arr[0];
		int max2 = arr[1];
		if (max1 < max2){
			max1 = arr[1];
			max2 = arr[0];
		}
		int comparisons = arr.length/2 - 1;
		// the main loop
		for (int i=2; i<arr.length-1; i = i + 2){
			if (arr[i] > arr[i+1]){
				comparisons++;
				if (arr[i] > max2){
					comparisons++;
					if (arr[i] > max1){
						max2 = max1;
						max1 = arr[i];
					}
					else{
						max2 = arr[i];
					}
				}
			}
			else{
				comparisons++;
				if (arr[i+1] > max2){
					comparisons++;
					if (arr[i+1] > max1){
						max2 = max1;
						max1 = arr[i+1];
					}
					else{
						max2 = arr[i+1];
					}
				}
			}
		}
		comparisons++;
		if (arr.length%2 != 0){
			comparisons++;
			if (arr[arr.length] > max1){
				max2 = max1;
				max1 = arr[arr.length];
			}
			else {
				comparisons++;
				if (arr[arr.length] > max2) max2 = arr[arr.length];
			}
		}
		// print the result:
		//System.out.println("max1 = "+max1+", max2 = "+max2);
		return comparisons;
	}
	public static int[] twoMaxRecurs(int arr[]){
		int[] ans = twoMaxRecurs(arr, 0, arr.length);
		//System.out.println("max1 = "+ans[0]+", max2 = "+ans[1]);
		return ans;
	}
	public static int[] twoMaxRecurs(int arr[], int low, int high){
		if (high == low+2){
			int max1 = arr[low];
			int max2 = arr[low+1];
			if (max1 < max2){
				max1 = arr[low+1];
				max2 = arr[low];
			}
			int maxArray[] = {max1, max2, 1};
			return maxArray;
		}
		else if (high == low +1){
			int max1 = arr[low];
			int maxArray[] = {max1, Integer.MIN_VALUE, 0};
			return maxArray;
		}
		else{
			int mid =(low + high)/2; 
			int [] mL = twoMaxRecurs(arr, low, mid);// [low, mid)
			int [] mH = twoMaxRecurs(arr, mid, high);//[mid, high)
			//maxArray[0] = max1, maxArray[1] = max2, maxArray[2] = comp
			int maxArray[] = new int[3];
			int comp = mL[2] + mH[2];
			int i = 0, j = 0;
			comp++;
			if (mL[i] > mH[j]){
				maxArray[0] = mL[i++];
			}
			else{
				maxArray[0] = mH[j++];
			}
			comp++;
			if (mL[i] > mH[j]){
				maxArray[1] = mL[i];
			}
			else{
				maxArray[1] = mH[j];
			}			
			maxArray[2] = comp;
			return maxArray;
		}
	}	
	// 
	public static void main(String[] args) {
		// two largest elements		
		int size = 1000000;
		long start, end, loop = 100;
		int comp1 = 0, comp2 = 0, comp3 = 0, comp4 = 0, comp5 = 0;
		double diff1 = 0, diff2 = 0, diff3 = 0, diff4 = 0, diff5 = 0;
		System.out.println("size = " + size + ";  loop = " + loop);
		for (int i = 1; i<=loop; i++){
			int arr[] = MyLibrary.randomIntArrayOfDiffNumbers(size);
			
			// two max recursive 
			start = System.currentTimeMillis();
			comp1 = comp1 + twoMaxRecurs(arr)[2];
			end = System.currentTimeMillis();
			diff1 = diff1 + ((double)(end - start))/1000.0;
			
			/// twoMaxGreatThanMax1 
			start = System.currentTimeMillis();
			comp2 = comp2 + twoMaxGreatThanMax1(arr);
			end = System.currentTimeMillis();
			diff2 = diff2 + ((double)(end - start))/1000.0;

			/// twoMaxGreatThanMax2 
			start = System.currentTimeMillis();
			comp3 = comp3 + twoMaxGreatThanMax2(arr);
			end = System.currentTimeMillis();
			diff3 = diff3 + ((double)(end - start))/1000.0;

			/// twoMaxPairs1 
			start = System.currentTimeMillis();
			comp4 = comp4 + twoMaxPairs1(arr);
			end = System.currentTimeMillis();
			diff4 = diff4 + ((double)(end - start))/1000.0;

			/// twoMaxPairs2 
			start = System.currentTimeMillis();
			comp5 = comp5 + twoMaxPairs2(arr);
			end = System.currentTimeMillis();
			diff5 = diff5 + ((double)(end - start))/1000.0;
		}
		System.out.println("twoMaxRecurs");
		System.out.printf("time twoMaxRecurs: %7.3f", diff1/loop);
		System.out.println(";  number of comparisons: " + comp1/loop);
		System.out.println();
		
		System.out.println("twoMaxGreatThanMax1");
		System.out.printf("time twoMaxGreatThanMax1: %7.3f", diff2/loop);
		System.out.println(";  number of comparisons: "+comp2/loop);
		System.out.println();

		System.out.println("twoMaxGreatThanMax2");
		System.out.printf("time twoMaxGreatThanMax2: %7.3f", diff3/loop);
		System.out.println(";  number of comparisons: "+comp3/loop);
		System.out.println();

		System.out.println("twoMaxPairs1 (great than max1)");
		System.out.printf("time twoMaxPairs1: %7.3f", diff4/loop);
		System.out.println(";  number of comparisons: "+comp4/loop);
		System.out.println();
		
		System.out.println("twoMaxPairs2 (great than max2)");
		System.out.printf("time twoMaxPairs2: %7.3f", diff5/loop);
		System.out.println(";  number of comparisons: "+comp5/loop);
		System.out.println();			
	}
}
/** the results:
	size = 10000000
	max1 = 10000000, max2 = 9999999
	time twoMaxGreatThanMax1: 0.031
	number of comparisons: 19999978

	max1 = 10000000, max2 = 9999999
	time twoMaxLessThanMax2: 0.032
	number of comparisons: 10000043
 **/
