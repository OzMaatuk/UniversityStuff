package ex02;
/**
 * @course Object-oriented programming
 * @Exercise 02, Part 1.
 * @author Oz
 */
public class Ex02
{
    public static void main(String[] args) {
        Complex[] c = new Complex[10];
        for (int i = 0; i < c.length; i++) {
            double a = Math.random() * c.length;
            double b = Math.random() * c.length;
            c[i] = new Complex(a, b);
        }
        SortComplex sc = new SortComplex(c);
        ObjectSort.sort(sc);
        System.out.println("is sorted: " + ObjectSort.checkSort(sc));
        for (int i = 0; i < c.length; i++) {
            System.out.println(((Complex) sc.valueAt(i)).module());
        }
        System.out.println();
    }
}
