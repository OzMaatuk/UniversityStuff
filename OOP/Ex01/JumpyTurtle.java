import Turtle.SimpleTurtle;;

/**
 *  * @course Object-oriented programming 
 * @Exercise 01, Part 3
 * this class crate a JumpyTurtle with a specific Abilities.
 * @author Oz Maatuk 305181158, Avishalom Jan 308481423.
 *
 */
public class JumpyTurtle extends SmartTurtle {
	
	/**
	 * OverRide to mvoeForward function
	 */
	public void moveForward (double distance){
		for (int i = 0; i < distance/10; i++) {
			this.tailDown();
			super.moveForward(5);
			this.tailUp();
			super.moveForward(5);
			
		}
	}
	/**
	 * OverRide to mvoeBackward function
	 */
	public void moveBackward (double distance){
		for (int i = 0; i < distance/10; i++) {
			this.tailDown();
			super.moveBackward(5);
			this.tailUp();
			super.moveBackward(5);
		}
	}
	


}
