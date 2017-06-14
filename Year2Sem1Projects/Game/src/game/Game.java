/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Oz
 */
public class Game
{
    public static void main(String[] args)
    {
        int arr[] = {1, 3, 6, 1, 3, 6};
        int sumZugi = 0;
        int sumEZugi = 0;
        int sumP1 = 0;
        int sumP2 = 0;

        for (int i = 0; i < arr.length; i = i + 2)
        {
            sumZugi = sumZugi + arr[i];
            sumEZugi = sumEZugi + arr[i + 1];
        }
        int i = 0;
        int j = arr.length - 1;
        while ((sumZugi != 0) && (sumEZugi != 0))
        {
            if (sumZugi > sumEZugi)
            {
                System.out.println("Player 1 choose: " + i);
                sumP1 = sumP1 + arr[i];
                sumZugi = sumZugi - arr[i];
                i++;
                if (arr[i] < arr[j])
                {
                    System.out.println("Player 2 choose: " + j);
                    sumP2 = sumP2 + arr[j];
                    sumEZugi = sumEZugi - arr[j];
                    int tmp = i;
                    i = j - 1;
                    j = tmp;
                }
                else
                {
                    System.out.println("Player 2 choose: " + i);
                    sumP2 = sumP2 + arr[i];
                    sumEZugi = sumEZugi - arr[i];
                    i++;
                }
            }
            else
            {
                System.out.println("Player 1 choose: " + j);
                sumP1 = sumP1 + arr[j];
                sumEZugi = sumEZugi - arr[j];
                j--;
                if (arr[j] > arr[i])
                {
                    System.out.println("Player 2 choose: " + j);
                    sumP2 = sumP2 + arr[j];
                    sumZugi = sumZugi - arr[j];
                    j--;
                }
                else
                {
                    System.out.println("Player 2 choose: " + i);
                    sumP2 = sumP2 + arr[i];
                    sumZugi = sumZugi - arr[i];
                    i++;
                    int tmp = j;
                    j = i;
                    i = j;
                }
            }
        }
        System.out.println("Player 1 sum = " + sumP1);
        System.out.println("Player 2 sum = " + sumP2);
        System.out.println("Hefresh = " + (sumP1 - sumP2));
    }
}
