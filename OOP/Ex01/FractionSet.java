import java.util.Arrays;

/**
 * @course Object-oriented programming 
 * @Exercise 01, Part 2
 * @author Oz Maatuk 305181158
 */

public class FractionSet
{
    private static final int def = 10;
    private int len = 0;
    private Fraction[] arr;
    /**
    * default constructor initial size of the array is 10
    */
    public FractionSet()
    {
        /**
        this.len = def;
        this.arr = new Fraction[def];
        for (int i = 0; i < def; i++)
        {
            this.arr[i] = new Fraction();
        }
        **/
    }
    
    /**
    * copy constructor
    * @param
    * other - - the Fraction
    */
    public FractionSet(FractionSet other)
    {
        this.len = other.size();
        this.arr = new Fraction[other.getArray().length];
        for (int i = 0; i < other.getArray().length; i++)
        {
            this.arr[i] = new Fraction(other.getArray()[i]);
        }
    }
    
    /**
    * constructor
    * @param
    * size - - the initial size of the array
    */
    public FractionSet(int size)
    {
        this.len = size;
        this.arr = new Fraction[this.len];
    }
    
    /**
    * Getter of this size
    * @return
    * number of the fractions in this set
    */
    public int size()
    {
        return this.len;
    }
    
    /**
    * Getter of this arr
    * @return this array
    */
    public Fraction[] getArray()
    {
        return this.arr;
    }
    
    /**
    * Setter of this len
    * @param newLen - the array length
    */
    public void setLength(int newLen)
    {
        this.len = newLen;
    }
    
    /**
    * Setter of this arr
    * @param newArr - the new array
    */
    public void setArray(Fraction[] newArr)
    {
        this.arr = newArr;
    }
    
    /**
     * find the index of f if the set contains f, otherwise return -1
     * @param f Fraction
    * @return
    * index of f if the set contains f, otherwise return -1
    */
    public int contains(Fraction f)
    {
        int i = 0;
        while ((i < this.size()) && (!(this.arr[i].equals(f))))
        {
            i++;
        }
        if (i == this.size())
        {
            return -1;
        }
        else
        {
            return i;
        }
    }
    
    /**
    * Appends the specified fraction (elem) to the end of this set
    * @param elem - Fraction array
    */  
    public void add(Fraction elem)
    {
        for (int i = 0; i < this.size(); i++)
        {
            if (this.arr[i].equals(elem))
            {
                return;
            }
        }
        
        Fraction[] newArr = new Fraction[this.size()+1];
        for (int i = 0; i < this.size(); i++)
        {
            newArr[i] = this.getElemInIndex(i);
        }
        this.setLength(this.size() + 1);
        newArr[this.size()-1] = elem;
        this.setArray(newArr);
    }
    
    /**
    * Removes the specified fraction (elem) from this set
    * @param elem - Fraction array
    */  
    public void remove(Fraction elem)
    {
        int i = this.contains(elem);
        if (i != -1)
        {
            while ( i+1 < this.size())
            {
                this.arr[i] = this.arr[i+1];
                i++;
            }
            this.setLength(this.size() - 1);
            this.arr  = Arrays.copyOf(this.arr, this.size());
        }
    }
    
    /**
    * Removes the element in place i
    * @param i - number of element to remove
    */  
    private void removeByIndex(int i)
    {
        while ( i+1 < this.size())
        {
            this.arr[i] = this.arr[i+1];
            i++;
        }
        this.setLength(this.size() - 1);
        this.arr  = Arrays.copyOf(this.arr, this.size());
    }
    
    /**
    * Adds all of the elements in the specified collection (other) to this set
    * @param other - - Fraction array
    */  
    public void union(FractionSet other)
    {
        for (int i = 0; i < other.size(); i++)
        {
            this.add(other.getElemInIndex(i));
        }
    }
    
    /**
    * Getter of specific element
    * @param i - - index of requier element
    * @return the element in the index i
    */ 
    private Fraction getElemInIndex(int i)
    {
        return (this.arr[i]);
    }
    
    /**
    * Removes from this set all of its elements that are contained in the specified (other) set
    * @param other - - FractionSet to remove
    */ 
    public void difference(FractionSet other)
    {
        for (int i = 0; i < other.size(); i++)
        {
            this.remove(other.getElemInIndex(i));
        }
    }
    
    /**
    * Retains only the elements in this set that are contained in the specified (other) set 
    * @param other - - FractionSet to retain
    */ 
    public void intersection(FractionSet other)
    {
        for (int i = 0; i < this.size(); i++)
        {
            int index = other.contains(this.getElemInIndex(i));
            if (index == -1)
            {
                this.removeByIndex(i);
                i--;
            }
        }
    }
    
    /**
    * check if this set contains all the elements of the specified (other) set
    * @param
    * other - - the specified set
    * @return
    * true if this set contains all the elements of the specified (other) set, otherwise returns false
    */ 
    public boolean containAll(FractionSet other)
    {
        for (int i = 0; i < other.size(); i++)
        {
            if (this.contains(other.getElemInIndex(i)) == -1)
            {
                return false;
            }
        }
        return true;
    }
    
    /**
    * Compares the specified set (other) with this set for equality.
    * @param
    * other -
    * @return
    * true if the two sets have the same size,
    * and the two sets consist the same elements (the order is not significant)
    * otherwise returns false
    */ 
    public boolean equals(FractionSet other)
    {
        if (this.size() == other.size())
        {
            for (int i = 0; i < other.size(); i++)
            {
                if (this.contains(other.getElemInIndex(i)) == -1)
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    /**
    * creates the result of the XOR operation of this and other sets
    * @param
    * other - - FractionSet
    */ 
    public void xor(FractionSet other)
    {
        for (int i = 0; i < other.size(); i++)
        {
            if ((this.contains(other.getElemInIndex(i))) == -1)
            {
                this.add(other.getElemInIndex(i));
            }
            else
            {
                this.remove(other.getElemInIndex(i));
            }
        }
    }
    
    /**
    * @return
    * a string representation of the the set
    * @override
    * toString in class java.lang.Object
    */ 
    @Override
    public java.lang.String toString()
    {
        String str = "";
        for (int i = 0; i < this.size(); i++)
        {
            str = str + this.getElemInIndex(i).toString() + ", ";
        }
        return str;
    }
}
