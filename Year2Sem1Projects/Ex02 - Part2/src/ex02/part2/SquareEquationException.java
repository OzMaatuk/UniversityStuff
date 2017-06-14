package ex02.part2;
/**
 * @course Object-oriented programming 
 * @Exercise 02, Part 2
 * @author Oz Maatuk 305181158
 */
public class SquareEquationException extends Exception
{
    /**
    * SquareEquationException class extends for exception at calculating solutions for aX^2+bX+c=0 equation 
    */
    /**
    * Default Constructor SquareEquationException
    *     * @param msg - the message for exception
    */
    public SquareEquationException(String msg)
    {
        //sends msg to Exaption constractor
        super(msg);
    }
}
