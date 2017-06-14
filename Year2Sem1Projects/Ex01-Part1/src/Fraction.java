
/**
 * @course Object-oriented programming 
 * @Exercise 01, Part 1
 * @author Oz Maatuk 305181158, Avishalom Jan 308481423.
 */
public class Fraction
{
    /**
    * @parameters
    * up - numerator
    * down - denominator
    */
    protected int up, down;
    
    /**
    * Default Constructor fraction: 0/1
    */
    public Fraction()
    {
        this.up = 0;
        this.down = 1;
    }
    
    /**
    * Copy Constructor
    * @param 
    * f - - fraction
    */
    public Fraction(Fraction f)
    {
        this.up = f.getUp();
        this.down = f.getDown();
    }
    
    /**
    * Constructor 2
    * @param n - - numerator 
    * @param d - - denominator
    * if d == 0 then the Fraction will be 0/1
    */
    public Fraction(int n, int d)
    {
        this.up = n;
        this.down = d;
        if (d == 0)
        {
            this.up = 0;
            this.down = 1;
        }
    }
    
    /**
    * Getter of numerator,
    * @return this up
    */
    public int getUp()
    {
        return this.up;
    }
    
    /**
    * Getter of denominator,
    * @return this down
    */
    public int getDown()
    {
        return this.down;
    }
    
    /**
    * Setter of numerator,
    * @param num - will replace this up
    */
    public void setUp(int num)
    {
        this.up = num;
    }
    
    /**
    * Setter of denominator,
    * @param num - will replace this down
    */
    public void setDown(int num)
    {
        this.down = num;
    }
    
    /**
    * GCD algorithm - find Greatest Common Divisor of two numbers
    * @param p - - first number
    * @param q - - second number
    * @return Greatest Common Divisor of two numbers
    */
    public static int gcd(int p, int q)
    {
        if (q == 0) return p;
        else return gcd(q, p % q);
    }
    
    /**
    * Addition of two fractions
    * @param f - - fraction
    */
    public void add(Fraction f)
    {
        int gcdNum = gcd(f.getDown(), this.down);
        this.setUp((this.getUp() * (gcdNum / this.getDown())) + (f.getUp() * (gcdNum / f.getDown())));
        this.setDown(gcdNum);
        
        zamZem();
    }
    
    /**
    * Subtraction of two fractions
    * @param f - - fraction
    */
    public void sub(Fraction f)
    {
        int gcdNum = gcd(f.getDown(), this.down);
        this.setUp((this.getUp() * (gcdNum / this.getDown())) - (f.getUp() * (gcdNum / f.getDown())));
        this.setDown(gcdNum);
        
        zamZem();
    }
    
    /**
    * multiplication of two fractions
    * @param f - - fraction
    */
    public void mul(Fraction f)
    {
        this.setUp(this.up * f.getUp());
        this.setDown(this.getDown() * f.getDown());
        
        zamZem();
    }
    
    /**
    * division of two fractions
    * @param f - - fraction
    */
    public void dev(Fraction f)
    {
        this.setUp(this.up * f.getDown());
        this.setDown(this.getDown() * f.getUp());
        
        zamZem();
    }
    
    /**
    * compare two fractions
    * @param
    * f - - fraction
    * @return
    * true if the fractions are equals otherwise return false
    */
    public boolean equals(Fraction f)
    {
        if ((this.getDown() == f.getDown()) && (this.getUp() == f.getUp()))
        {
            return true;
        }
        
        return ((this.getUp() * f.getDown()) == (f.getUp() * this.getDown()));
    }
    
    /**
    * Compares its two arguments for order.
    * @param f - - fraction
    * @return a negative integer, zero, or a positive integer
    * as the first argument is less than, equal to, or greater than the second.
    */
    public int compare(Fraction f)
    {
        if (this.equals(f))
        {
            return 0;
        }
        else
        {
            int gcdNum = gcd(f.getDown(), this.down);
        
            if ((this.getUp() * (gcdNum / this.getDown())) > (f.getUp() * (gcdNum / f.getDown())))
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
    }
    
    /**
    * @return
    * a string representation of the the fraction: (n/d)
    * @verrides
    * toString in class java.lang.Object
    */
    @Override
    public java.lang.String toString()
    {
        return (this.up + "/" + this.down);
    }
    
    /**
    * Reduction of the fraction
    */
    private void zamZem()
    {
        int tmp = 0;
        if (this.getUp() > this.getDown())
        {
            tmp = this.getUp() / this.getDown();
        }
        else
        {
            tmp = this.getDown() / this.getUp();
        }
        
        this.setDown(this.getDown() / tmp);
        this.setUp(this.getUp() / tmp);
    }
}