package ex02;
/**
 * @course Object-oriented programming 
 * @Exercise 02, Part 1.
 * @author Oz
 */
public interface Sortable
{    
    int compare(Object left, Object right);
    Object valueAt(int position);
    void setValue(Object value, int position);
    int size();
}
