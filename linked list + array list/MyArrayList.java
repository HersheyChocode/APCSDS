import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;

/** This object acts as an array list, which is a data structure 
 * that allows an individual to add and remove elements at any index, 
 * randomly access elements, and retrieve the size of the arraylist 
 * (how many elements have been added).
 * @author Harshini Chaturvedula
 * @version 10/18/2021
 * @param <E> is the type of the element
 */

public class MyArrayList<E>
{
    private int size;
    private Object[] values;  //(Java doesn't let us make an array of type E)
    private boolean modified;

    /**
     * This constructor initiates size to 0 and values to an array of objects of length one
     */
    public MyArrayList()
    {
        size = 0;
        values = new Object[1];
        //modified = false;
    }

    /**
     * This returns a string representation of the object, 
     * which in MyArrayList, will be the elements of the array list separated
     * by commas and enclosed between two square brackets. If there are no
     * elements, the method simply returns two square brackets with nothing in 
     * between. This method has no pre/postconditions. Runs in O(n) time. 
     * @return a string list of the values in myArrayList
     */
    public String toString()
    {
        if (size == 0)
            return "[]";

        String s = "[";
        for (int i = 0; i < size - 1; i++)
            s += values[i] + ", ";
        return s + values[size - 1] + "]";
    }

    /**
     * This method creates an array with double the size of values 
     * and copies over the values in the original array
    * @postcondition replaces the array with one that is
    *               twice as long, and copies all of the
    *               old elements into it
    */
    private void doubleCapacity()
    {
        Object[] values2 = new Object[values.length*2];
        for(int i = 0; i<values.length; i++)
        {
            values2[i] = values[i];
        }
        values = values2; 
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    /**
     * should return the length of the array of MyArrayList (this is the array 
     * which holds all the elements inserted and that will double its size when 
     * filled up). The only postcondition is that it returns the length of the 
     * array. Runs in O(1) time.
    * @postcondition returns the length of the array
    * @return the length of values
    */
    public int getCapacity()
    {
        return values.length;
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }
    
    /**
     * This  returns the number of elements inside MyArrayList, so any elements 
     * that have been added. This is different from getCapacity(), which 
     * returns the size of the array, size() simply returns how many elements 
     * have been inserted, so it will always be less than what getCapacity() 
     * returns. Runs in O(1) time. 
     * @return size, the variable for the representing size of myArrayList 
     */
    public int size()
    {
        return size;
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    /**
     * This returns the element at position index in the array of MyArrayList. 
     * A precondition is that index should be greater than or equal to 0 and 
     * less than the size of MyArrayList, but this can also be dealt with by 
     * throwing an exception error. A postcondition is that the returned value 
     * should be of type E. Runs in O(1) time. 
     * @param index represents the index of the array whose value will be returned
     * @return the value corresponding to values at the position index
     */
    public E get(int index)
    {
        if(index<0 || index>=size) throw new ArrayIndexOutOfBoundsException("INDEX OUT OF BOUNDS");
        return (E) values[index];
        //(You will need to promise the return value is of type E.)
    }

    /** 
     * This replaces the current value at position index with obj. It returns 
     * the previous value at position index. A precondition is that index 
     * should be greater than or equal to 0 and less than the size of 
     * MyArrayList, but again, this can also be dealt with by throwing an 
     * exception error. A precondition is that obj is of type E, and a 
     * postcondition is that the returned value is of type E and replaces the 
     * value successfully. Runs in O(1) time. 
     * @precondition index should be greater than 0 and less than size, obj is of type E
    * @postcondition replaces the element at position index with obj
    *               returns the element formerly at the specified position
    * @param index is the index at which the value should be replaced
    * @param obj is the new value that will replace the original value at position index
    * @return the original value at index
    */
    public E set(int index, E obj)
    {
        if(index<0 || index>=size) throw new ArrayIndexOutOfBoundsException("INDEX OUT OF BOUNDS");
        Object e = values[index];
        values[index] = obj;
        modified = true;
        return (E) e;

        //(You will need to promise the return value is of type E.)
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    /**
     * This  should add element obj to the end of the list. If the array is 
     * already filled, this method should also call for the array to double its 
     * size before adding obj. 
     * It should return true. Preconditions are that obj is of type E and a 
     * postcondition is that the size of MyArrayList will be updated. Runs in O(1) time. 
     * @precondition obj is of type E
    * @postcondition appends obj to end of list; returns true; size is incremented by 1
    * @return true 
    * @param obj is the element that will be added to the end of the list
    */
    public boolean add(E obj)
    {
        /* if values is already full, call doubleCapacity before adding */
        //if(index<0 || index>=size) return false;
        add(size, obj);
        return true;
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    /**
     * This removes the element at index, moving anything to the right of index 
     * left by one. The method should return the element that had been at 
     * index. A precondition is that index should be greater than or equal to 0 
     * and less than the size of MyArrayList, but once again, this may also be 
     * dealt with by throwing an exception error. A postcondition is that the 
     * size of the array is adjusted once the element has been removed. Runs in 
     * O(1) time. 
    * @precondition index >= 0 and <size
    * @postcondition removes element from position index, moving elements
    *               at position index + 1 and higher to the left
    *               (subtracts 1 from their indices) and adjusts size
    *               returns the element formerly at the specified position
    * @param index is the position of values where the element will be removed
    * @return the element previously at index in values
    */
    public E remove(int index)
    {
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        
        E obj = (E) values[index];
        values[index] = null;
        for(int i = index+1; i<size; i++) 
        {
            values[i-1] = values[i];
        }
        size-=1;
        modified = true;
        return obj;
        //(You will need to promise the return value is of type E.)
    }

    /**
     * This should return a new MyArrayListIterator().
     * @return a new MyArrayListIterator 
     */
    public Iterator<E> iterator()
    {
        modified = false;
        //System.out.println(modified);
        return new MyArrayListIterator();
    }

    /**
     * The method add(int index, E obj) should add element obj at the desired 
     * index, and all elements originally at and after index should be shifted 
     * over 1. A precondition is that index should be greater than or equal to 
     * 0 and less than the size of MyArrayList, but again, this can also be 
     * dealt with by throwing an exception error. Another precondition is that 
     * obj is of type E, and a postcondition is that the size of MyArrayList 
     * will be updated after the element is added. Runs in O(1) time. 
    * @precondition  0 <= index <= size
    * @postcondition inserts obj at position index,
    *               moving elements at position index and higher
    *               to the right (adds 1 to their indices) and adjusts size
    * @param index is the position at which the element will be added into values
    * @param obj is the element that will be added
    */
    public void add(int index, E obj)
    {
        
        if(values.length==size) 
        {
            doubleCapacity();
        }
        //System.out.println("index is: " + (index));
        
        for(int i = size-1; i>=index; i--) 
        {
            values[i+1] = values[i];
        }
        size+=1;
        values[index] = obj;
        modified = true;
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    /**
     * Returns a new listIterator for MyArrayList
     * @return a MyArrayListListIterator
     */
    public ListIterator<E> listIterator()
    {
        modified = false;
        return new MyArrayListListIterator();
    }

    /**
     * 
     */
    private class MyArrayListIterator implements Iterator<E>
    {
        //the index of the value that will be returned by next()
        private int nextIndex;

        /**
         * This implements the interface Iterator<E> which traverses through 
         * the elements of a list, keeping track of whether there is a next element.
         */
        public MyArrayListIterator()
        {
            nextIndex = 0; 
            //modified = false;
            //throw new RuntimeException("INSERT MISSING CODE HERE");
        }

        /**
         * This returns true if there is another element.
         */
        public boolean hasNext()
        {
            //System.out.println(nextIndex + "  " + size);
            //System.out.println(MyArrayList.this.modified);
            if(modified) throw new ConcurrentModificationException();
            if(!modified && nextIndex<size) return true;
            return false;
            //throw new RuntimeException("INSERT MISSING CODE HERE");
        }

        /**
         * This returns the next element in O(1) time.
         */
        public E next()
        {
            if(!modified && hasNext()) 
            {
                nextIndex++;
                return (E) values[nextIndex-1];
            } 
            else if(modified) throw new ConcurrentModificationException();
            else throw new ArrayIndexOutOfBoundsException("NO MORE ELEMENTS");

            //(You will need to promise the return value is of type E.)
        }
        /**
         * This can remove the next element in O(1) time.
         */
        //@postcondition removes the last element that was returned by next
        public void remove()
        {
            MyArrayList.this.remove(nextIndex);
            modified = false;
            //throw new RuntimeException("INSERT MISSING CODE HERE");
        }
    }

    /**
     * This implements the interface Listterator<E> and traverses through the elements of a list
     */
    private class MyArrayListListIterator extends MyArrayListIterator implements ListIterator<E>
    {
        // note the extends MyArrayListIterator 
        // Remember this class thus inherits the methods from the parent class.
        
        private int nextIndex;
        private int previousIndex;
        private boolean forward; //direction of traversal

        /**
         * Constructs a new MyArrayListListIterator.
         */
        public MyArrayListListIterator()
        {
            nextIndex = 0;
            super.nextIndex = nextIndex;
            previousIndex = -1;
            forward = true;
        }
        
        /**
         * Returns next element and moves pointer forward
         * @return next Object in MyArryaList
         */
        public E next()
        {
            if (modified)
            {
                throw new ConcurrentModificationException();
            }
            nextIndex++;
            super.nextIndex = nextIndex;
            previousIndex++;
            return ((E) values[nextIndex-1]);
        }

        /**
         * Adds element before element that would be returned by next
         * @param obj to add
         */
        public void add(E obj)
        {
            if(modified) throw new ConcurrentModificationException();
            add(nextIndex, obj);
            super.nextIndex = nextIndex;
        }

        /**
         * Determines whether there is another element in MyArrayList
         * while traversing in the backward direction
         * @return true if there is another element, false otherwise
         */
        public boolean hasPrevious()
        {
            if(modified) throw new ConcurrentModificationException();
            return previousIndex>=0;
        }

        /**
         * Returns previous element and moves pointer backwards
         * @return previous Object in MyArryaList
         */
        public E previous()
        {
            if(modified) throw new ConcurrentModificationException();
            forward = false;
            nextIndex--;
            super.nextIndex = nextIndex;
            previousIndex--;
            return (E) values[previousIndex+1];
        }

        /**
         * Returns index of the next element 
         * @return index of element that would be 
         *         returned by a call to next()
         */
        public int nextIndex()
        {
            if(modified) throw new ConcurrentModificationException();
            return nextIndex;
        }

        /**
         * Returns index of the previous element 
         * @return index of element that would be 
         *         returned by a call to previous()
         */
        public int previousIndex()
        {
            if(modified) throw new ConcurrentModificationException();
            return previousIndex;
        }

        /**
         * Removes element that was returned by next() or previous()
         * USE direction FOR THIS
         */
        public void remove()
        {
            if(modified) throw new ConcurrentModificationException();
            if(forward) remove(previousIndex);
            else remove(nextIndex);
            super.nextIndex = nextIndex;
            //System.out.println("INSERT MISSING CODE HERE");
        }

        /**
         * Sets the element returned by next() or previous() to obj
         */
        public void set(E obj)
        {
            if(modified) throw new ConcurrentModificationException();
            if(forward) values[previousIndex] = (E) obj;
            else values[nextIndex] = (E) obj;
        }
    }
}