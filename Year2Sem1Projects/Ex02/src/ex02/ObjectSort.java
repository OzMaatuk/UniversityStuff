package ex02;

import sun.misc.Sort;

/**
 * @course Object-oriented programming
 * @Exercise 02, Part 1.
 * @author Oz
 */
public class ObjectSort {

    private int partition(Complex arr[], int left, int right)
    {
        int i = left, j = right;
        Complex tmp;
        Complex pivot = arr[(left + right) / 2];
        while (i <= j) {
            while (arr[i].compare(pivot) == 1) {
                i++;
            }

            while (arr[j].compare(pivot) == -1) {
                j--;
            }

            if (i <= j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }
        return i;
    }

    private void quickSort(Complex arr[], int left, int right)
    {
        int index = partition(arr, left, right);
        if (left < index - 1) {
            quickSort(arr, left, index - 1);
        }

        if (index < right) {
            quickSort(arr, index, right);
        }
    }
    
    public static void sort(Sortable item)
    {
        if (item instanceof SortComplex)
        {
            quickSort();
        }
    }

    public static boolean checkSort(Sortable item) {
        for (int i = 1; i < item.size(); i++) {
            if (item.compare(item.valueAt(i - 1), item.valueAt(i)) != 1) {
                return false;
            }
        }
        return true;
    }
}
