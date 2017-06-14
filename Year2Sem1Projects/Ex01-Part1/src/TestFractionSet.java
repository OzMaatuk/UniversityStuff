
import java.util.Random;

public class TestFractionSet {

    public static void timeTest() {
        FractionSet fs1 = new FractionSet();
        Random gen = new Random(123);
        int len = 10000, to = 1000;
        for (int i = 0; i < len; i++) {
            fs1.add(new Fraction(gen.nextInt(to) + 1, gen.nextInt(to) + 1));
        }
        System.out.println("size fs1: " + fs1.size());
        FractionSet fs2 = new FractionSet();
        for (int i = 0; i < len; i++) {
            fs2.add(new Fraction(gen.nextInt(to) + 1, gen.nextInt(to) + 1));
        }
        System.out.println("size: fs2: " + fs2.size());
        fs2.xor(fs1);
        System.out.println("fs2 xor fs1 size: " + fs2.size());
    }

public static void smallTest() {
        FractionSet t1 = new FractionSet();
        t1.add(new Fraction(1, 2));
        t1.add(new Fraction(1, 3));
        t1.add(new Fraction(2, 4));
        t1.add(new Fraction(1, 4));
        t1.add(new Fraction(2, 8));
        t1.add(new Fraction(1, 5));
        t1.add(new Fraction(1, 6));
        System.out.println("t1: " + t1);
        FractionSet t2 = new FractionSet();
        t2.add(new Fraction(5, 2));
        t2.add(new Fraction(1, 3));
        t2.add(new Fraction(2, 4));
        t2.add(new Fraction(7, 4));
        t2.add(new Fraction(3, 7));
        t2.add(new Fraction(3, 5));
        t2.add(new Fraction(5, 6));
        System.out.println("t2: " + t2);
        System.out.println("t1 EQUALS t2: " + t1.equals(t2));
        System.out.println("t1 EQUALS t1: " + t1.equals(t1));
        FractionSet t3 = new FractionSet(t1);
        FractionSet t4 = new FractionSet(t2);
        System.out.println("t3: " + t3);
        System.out.println("t4: " + t4);
        t3.union(t2);
        System.out.println("t3 UNION t2: " + t3);
        System.out.println("t3 contains t2: " + t3.containAll(t2));
        System.out.println("t2 contains t3: " + t2.containAll(t3));
        t3 = new FractionSet(t1);
        t1.intersection(t2);
        System.out.println("t1 INTERSECRION t2: " + t1);
        System.out.println("t3: " + t3);
        System.out.println("t4: " + t4);
        t3.xor(t4);
        System.out.println("t3 xor t4 : " + t3);
    }

    public static void main(String[] args) {
        System.out.println("************** smallTest ************************");
        smallTest();
        System.out.println("\n************** timeTest ************************");
        long start = System.currentTimeMillis();
        timeTest();
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start) + " msc");
    }
}
