package ex02;
/**
 * @course Object-oriented programming 
 * @Exercise 02, Part 1.
 * @author Oz
 */
public class Complex
{
    double a,b;
    
    public Complex(double a, double b)
    {
        this.a = a;
        this.b = b;
    }
    
    public double module()
    {
        return Math.sqrt((this.a)*(this.a) + (this.b)*(this.b));
    }
    
    public void add (Complex z)
    {
        this.a = this.a + z.a;
        this.b = this.b + z.b;
    }
    
    public void mul (Complex z)
    {
        this.a = ((this.a*z.a) - (this.b*z.b));
        this.b = ((this.a*z.b) + (this.b*z.a));
    }
    
    public int compare(Complex z)
    {
        if (this.module() > z.module())
        {
            return -1;
        }
        else if (this.module() < z.module())
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}
