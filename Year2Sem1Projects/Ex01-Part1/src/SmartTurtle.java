import Turtle.SimpleTurtle;

/**
 *  * @course Object-oriented programming 
 * @Exercise 01, Part 3
 * this class crate a SmartTurtle with a specific Abilities.
 * @author Oz Maatuk 305181158, Avishalom Jan 308481423.
 *
 */
public class SmartTurtle extends SimpleTurtle {
	
	/**
	 * Function drawSquare
	 * Includes the possibility of movement according to the capabilities of
	 * @param size get the Size desired traffic
	 */
	public  void drawSquare (double size){
		for (int i = 0; i < 4; i++) {
			this.moveForward(size);
			this.turnRight(90);
		}
		
	}
	/**
	 * Function drawSquare
	 * Includes the possibility of movement according to the capabilities of
	 * @param size - get the Size desired traffic
	 * @param sides - get the number of Polygon sides.
	 */
	public  void drawPolygon (int sides, double size){
		int mov=360/sides;
		for (int i = 0; i < sides; i++) {
			this.moveForward(size);
			this.turnRight(mov);
		}
	}
	public String name(){
		return "Smart";
	}

	public static void main(String[] args) {

		
		
	}
}
