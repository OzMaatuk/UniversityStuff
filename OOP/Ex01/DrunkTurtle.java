import Turtle.SimpleTurtle;;

/**
 *  * @course Object-oriented programming 
 * @Exercise 01, Part 3
 * this class crate a DrunkTurtle with a specific Abilities.
 * @author Oz Maatuk 305181158, Avishalom Jan 308481423.
 *
 */
public class DrunkTurtle extends SimpleTurtle {
	
	/**
	 * OverRide the moveForward function
	 */
	public void moveForward (double distance){
		int rand=(int)(Math.random() *(2 * distance+1));
		super.moveForward(rand);
		
		// random whit minus?!
		rand=(int)(Math.random() *61-30);
		this.turnRight(rand);
	}
	
	
public static void main(String[] args) {


}
}
