package maxmax;
import java.util.Arrays;


public class Merge {

	public static int[] merge(int[] a1, int[]a2){
		int comp = 0;
		int [] res = new int[a1.length + a2.length];
		int i = 0, j = 0, k = 0;
		while(i<a1.length && j<a2.length){
			comp++;
			if (a1[i] < a2[j]) res[k++] = a1[i++];
			else  res[k++] = a2[j++];
		}
		while (i<a1.length) res[k++] = a1[i++];
		while (j<a2.length) res[k++] = a2[j++];
		System.out.println("merge comp = "+ comp);
		return res;
	}
	public static int[] mergeRecurs2(int[] a1, int[]a2){
		int [] res = new int[a1.length + a2.length];
		int comp = mergeRecurs2(a1, a2, 0, 0, 0, 0, res);
		System.out.println("mergeRecurs2 comp = "+ comp);
		return res;
	}
	public static int mergeRecurs2(int[] a1, int[]a2, int i, int j, int k, int comp, int res[]){
		if (i<a1.length || j<a2.length){
			comp++;
			if (i<a1.length && j<a2.length){
				if (a1[i] < a2[j]){
					res[k++] = a1[i++];
				}
				else{
					res[k++] = a2[j++];
				}
			}
			else if (j == a2.length){
				res[k++] = a1[i++];
			}
			else if (i == a2.length){
				res[k++] = a2[j++];
			}
			comp = mergeRecurs2(a1, a2, i, j, k, comp, res);
			return comp;
		}
		return comp;
	}
	
	private static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] < a[i-1]) return false;
        return true;
    }
	public static void main(String[] args) {
		int size = 1000;
		int a1[] = MyLibrary.randomIntegerArray(size);
		int a2[] = MyLibrary.randomIntegerArray(size);
		Arrays.sort(a1);
		Arrays.sort(a2);
		int res1[] = merge(a1, a2);
		System.out.println("is sorted res1? "+isSorted(res1));
		int res2[] = mergeRecurs2(a1, a2);
		System.out.println("is sorted res1? "+isSorted(res2));
	}

}
