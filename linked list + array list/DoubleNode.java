/**
* This class represents a double node that has a reference to a next node and previous node.
@author Harshini Chaturvedula
@version 11/9/2021
*/
public class DoubleNode
{
    private Object value;
    private DoubleNode previous;
    private DoubleNode next;

    /**
     * This constructor initializes the value of the double node to be a given value, 
     * and sets previous to null and next to null.
     * @param v is the given value for the initial double node
     */
    public DoubleNode(Object v)
    {
        value = v;
        previous = null;
        next = null;
    }

    /**
     * This method returns the value of the double node.
     * @return the value (value) of the double node
     */
    public Object getValue()
    {
        return value;
    }

    /**
     * This method returns the previous double node.
     * @return previous (the previous double node)
     */
    public DoubleNode getPrevious()
    {
        return previous;
    }

    /**
     * This method returns the next double node.
     * @return next (the next double node)
     */
    public DoubleNode getNext()
    {
        return next;
    }

    /**
     * This method sets the value of the double node to a given value.
     * @param v is the new value that will replace the existing value of the double node
     */
    public void setValue(Object v)
    {
        value = v;
    }

    /**
     * This method sets the previous double node to a given double node.
     * @param p is the given double node that will replace the value of the previous double node
     */
    public void setPrevious(DoubleNode p)
    {
        previous = p;
    }

    /**
     * This method sets the next double node to a given double node
     * @param n is the new double node that will replace the value of the next double node
     */
    public void setNext(DoubleNode n)
    {
        next = n;
    }
}