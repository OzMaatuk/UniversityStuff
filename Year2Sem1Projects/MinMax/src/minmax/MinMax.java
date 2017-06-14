/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minmax;

/**
 *
 * @author Oz
 */
public class MinMax {

    public static int numOfComprs(int[] arr) {
        int compars = 0;
        int len = arr.length;
        int max, min;

        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return 1;
        }

        compars++;
        if (arr[0] > arr[1]) {
            max = arr[0];
            min = arr[1];
        } else {
            max = arr[1];
            min = arr[0];
        }

        for (int i = 2; i < len - 1; i = i + 2) {
            compars++;
            if (arr[i] > arr[i + 1]) {
                compars++;
                if (arr[i] > max) {
                    max = arr[i];
                }

                compars++;
                if (arr[i + 1] < min) {
                    min = arr[i + 1];
                }
            } else {
                compars++;
                if (arr[i + 1] > max) {
                    max = arr[i + 1];
                }

                compars++;
                if (arr[i] < min) {
                    min = arr[i];
                }
            }
        }

        if (len % 2 == 1) {
            compars++;
            if (arr[len - 1] > max) {
                max = arr[len - 1];
            } else if (arr[len - 1] < min) {
                min = arr[len - 1];
            }
        }
        System.out.println("min: " + min + "max: " + max);
        return compars;
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 50, 4, 22, 44, 9, 29, 49, 21, 1, 36, 18, 38, 58, 26, 24, 42, 15, 35, 25, 6, 26, 46, 51, 59, 11, 37, 23, 43, 12, 32, 52, 41, 55, 8, 20, 40, 60, 17, 3, 57, 14, 34, 54, 19, 45, 7, 31, 27, 47, 28, 33, 53, 39, 13, 48, 16, 30, 10};
        int x = numOfComprs(arr);
        System.out.println(x);
    }
}
