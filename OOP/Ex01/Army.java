import Turtle.SimpleTurtle;
import Turtle.Turtle;
import java.util.*;

/**
 * class of Turtel-Army.
 *  * @course Object-oriented programming 
 * @Exercise 01, Part 3
 * @author Oz Maatuk 305181158, Avishalom Jan 308481423.
 *
 */
public class Army {
	/**
	 * the main function that open array with 5 turtle,get the type of each, and doing whit them 6 functions.
	 */
public static void main(String[] args) {
	Turtle t[]=new Turtle [5];
	int temp[]=new int [5];
	Scanner in=new Scanner(System.in);
	int num;
	System.out.println("Choose the type of a turtle:\n1. Simple\n2. Smart\n3. Drunk\n4. Jumpy");
	for (int i = 0; i < t.length; i++) {
		System.out.println("Enter the " + (i+1) + "st Turtle");
		num=in.nextInt();
		if(num<0||num>4){
			while(num<0||num>4){
				System.out.println("Erorr number! plase enter new number:");
				num=in.nextInt();
			}
		}
			
		temp[i]=num;
		if(num==1)
			t[i]=new SimpleTurtle();
		if(num==2)
			t[i]= new SmartTurtle();
		if(num==3)
			t[i]=new DrunkTurtle();
		if(num==4)
			t[i]= new JumpyTurtle();
	}
	
	for (int i =0; i < t.length; i++) {
		t[i].tailDown();
	}
	for (int i = 0; i < t.length; i++) {
		t[i].moveForward(100);
	}
	
	for (int i = 0; i < t.length; i++) {
		t[i].turnRight(90);
	}
	
	for (int i = 0; i < t.length; i++) {
		t[i].moveForward(100);
	}
	for (int i = 0; i < t.length; i++) {
		if(temp[i]==2 || temp[i]==4)
			((SmartTurtle) t[i]).drawPolygon(6,70);
	}
	for (int i = 0; i < t.length; i++) {
		t[i].hide();
	}
		
	}
}


