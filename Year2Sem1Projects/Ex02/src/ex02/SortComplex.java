package ex02;
/**
 * @course Object-oriented programming
 * @Exercise 02, Part 1.
 * @author Oz
 */
public class SortComplex implements Sortable
{
    private Complex[] cArr;
    
    public SortComplex(Complex[] c)
    {
        this.cArr = new Complex[c.length];
        for (int i = 0; i < c.length; i++)
        {
            this.cArr[i] = new Complex(c[i].a, c[i].b);
        }
    }
    
    @Override
    public int compare(Object left, Object right)
    {
        if (left instanceof Complex && right instanceof Complex)
        {
            return ((Complex)left).compare((Complex)right);
        }
        else
        {
            throw new UnknownError("Not a SortComplex");
        }
    }
    @Override
    public Object valueAt(int position)
    {
        if (position < this.cArr.length)
        {
            return this.cArr[position];
        }
        else
        {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void setValue(Object value, int position)
    {
        if (value instanceof Complex)
        {
            this.cArr[position] = new Complex(position, position);
        }
        else
        {
            throw new UnknownError("Not a SortComplex");
        }
    }

    @Override
    public int size()
    {
        return this.cArr.length;
    }
    
    public Complex[] getArr()
    {
        return this.cArr;
    }
}