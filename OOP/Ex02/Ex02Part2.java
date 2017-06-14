package ex02.part2;

import java.io.IOException;
import static java.lang.Math.sqrt;
import java.util.Scanner;

/**
 * @course Object-oriented programming 
 * @Exercise 02, Part 2
 * @author Oz Maatuk 305181158
 */
public class Ex02Part2 {

    /**
     * static main function
     * @param args the command line arguments
     * the program ask for parameter a, b, c, to the equation  aX^2+bX+c=0
     * it prints out the number of solutions for the equation, and the solutions it selves.
     * if there is no solution, or infinity solutions, it prints proper exception.
     */
    public static void main(String[] args) throws SquareEquationException {
        //creat scanner
        Scanner sc = new Scanner(System.in);

        //answare for continue the run
        int ans = 1;

        while (ans == 1) {

            System.out.println("please enter 3 numbers of Polynomial coefficients in order");
            System.out.println("aX^2+bX+c=0: enter a, b, c");

            //create parameters a, b, c
            double x = 0;
            double y = 0;
            double z = 0;

            //scan parameter a, b, c
            System.out.println("enter a:");
            x = sc.nextDouble();
            System.out.println("enter b:");
            y = sc.nextDouble();
            System.out.println("enter c:");
            z = sc.nextDouble();

            System.out.println();
            System.out.print(x);
            System.out.print("X^2+");
            System.out.print(y);
            System.out.print("X+");
            System.out.print(z);
            System.out.print(" = 0");
            System.out.println();
            
            //if the equaltion is 0=0
            //throws proper exception
            if (x == 0 && y == 0 && z == 0)
            {
                throw new SquareEquationException("there is infinity roots for the polynom");
            }
            
            //calculat delta and solutions for each case
            double delta = (y * y) - (4 * x * z);
            //there is two solutions
            if (delta > 0) {
                System.out.println("there is two answares for the polynom");
                
                double x1 = ((-y) + sqrt((y * y) - (4 * x * z))) / 2*x;
                double x2 = ((-y) - sqrt((y * y) - (4 * x * z))) / 2*x;
                
                System.out.println();
                System.out.print("X1 = ");
                System.out.print(x1 * -1);
                System.out.println();
                System.out.print("X2 = ");
                System.out.print(x2 * -1);
                System.out.println();
            
            } 
            //there is one solution
            else if (delta == 0) {
                System.out.println("there is one answare for the polynom");
                
                double x1 = ((-y) + sqrt((y * y) - (4 * x * z))) / 2*x;
                
                System.out.println();
                System.out.print("X1 = ");
                System.out.print(x1 * -1);
                System.out.println();
                
            }
            //there is no solutions
            else {
                throw new SquareEquationException("there is no real roots for the polynom");
            }
            
            //scan if to continue
            System.out.println("Enter 0 or any number to Exit or 1 to solve aX^2+bX+c=0:");
            ans = sc.nextInt();
        }
    }
}
