import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;

/** This class is a doubly-linked list that can connect a series of double nodes.  
@author Harshini Chaturvedula
@version 10.26.2021
@param <E> is the type of the elements
*/
public class MyLinkedList<E>
{
    private DoubleNode first;
    private DoubleNode last;
    private int size;
    private boolean modified;

    /**
     * This sets first and last to null, size to 0, and modified to false.
     */
    public MyLinkedList()
    {
        first = null;
        last = null;
        size = 0;
        modified = false;
    }

    /**
     * This returns a string representation of the linked list.
     * @return values of the list seperated by commas and spaces, enclosed by square brackets
     */
    public String toString()
    {
        DoubleNode node = first;
        if (node == null)
            return "[]";
        String s = "[";
        while (node.getNext() != null)
        {
            s += node.getValue() + ", ";
            node = node.getNext();
        }
        return s + node.getValue() + "]";
    }

    /** 
     * This method returns the node at a certain index when looping from the first node.
    * @precondition  0 <= index <= size / 2
    * @postcondition starting from first, returns the node
    *               with given index (where index 0
    *               returns first)
    * @param index is the index of the linked list which has the element desired 
    * @return the double node at position index
    */
    private DoubleNode getNodeFromFirst(int index)
    {
        DoubleNode x = first;
        for(int i = 0; i<index; i++) 
        {
            System.out.println(x + " " + x.getNext());
            x = x.getNext();
        }
        return x;
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    /** 
     * This method returns the node at a certain index when looping from the last node.
    * @precondition  size / 2 <= index < size
    * @postcondition starting from last, returns the node
    *               with given index (where index size-1
    *               returns last)
    * @param index is the index of the linked list which has the element desired 
    * @return the double node at position index
    */
    private DoubleNode getNodeFromLast(int index)
    {
        DoubleNode x = last;
        for(int i = size-1; i>index; i--) 
        {
            //System.out.println(x + " " + x.getPrevious());
            x = x.getPrevious();
        }
        return x;
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    /** 
     * This method returns the node at a certain index. 
    * @precondition  0 <= index < size
    * @postcondition starting from first or last (whichever
    *               is closer), returns the node with given
    *               index
    * @param index is the index of the linked list which has the element desired 
    * @return the double node at position index
    */
    private DoubleNode getNode(int index)
    {
        if(index<size/2) return getNodeFromFirst(index);
        else return getNodeFromLast(index);
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    /**
     * The method size() will return the size of the linked list, 
     * which is how many non-null nodes there are. 
     * This runs in O(n) time and has no pre/postconditions. 
     * @return the size of the linked list
     */
    public int size()
    {
        return size;
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }
    /**
     * The method get(int index) returns the element at position index in the
     * linked list. A postcondition could be that the element returned is 
     * of type E, and there are no preconditions. This runs in O(n) time. 
     * @postcondition the element returned is of type E. e
     * @param index is the position of the linked list whose element is being returned
     * @return the element at the index'th position in the linked list
     */
    public E get(int index)
    {
        return (E) getNode(index).getValue();
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        //(You will need to promise the return value is of type E.)
    }

    /**
     * The method set(int index, E obj) replaces the element at position index 
     * with obj, as well as returning the original element at the position. The 
     * only postcondition is the return statement, and a precondition could be 
     * that index is greater than or equal to 0 and less than the size of the 
     * linked list, but this can be taken care of by throwing an error. This 
     * runs in O(n) time. 

    * @postcondition replaces the element at position index with obj
                   returns the element formerly at the specified position
     * @param index is the position where the new object will be inserted 
     * @param obj is the new element to replace the old one
     * @return the element currently at position index
     */
    public E set(int index, E obj)
    {
        modified = true;
        E val = get(index);
        DoubleNode node1 = getNode(index);
        node1.setValue(obj);
        return val;
        //throw new RuntimeException("INSERT MISSING CODE HERE");

        //(You will need to promise the return value is of type E.)
    }

    /**
     * The method add(E obj) adds the element obj to the end of the linked list 
     * and returns true. The only postcondition is the return statement, and 
     * there are no preconditions. This runs in O(1) time. 
     * 
    * @return true
    * @postcondition appends obj to end of list; returns true
    * @param obj is the element to be added to the linked list
    */
    public boolean add(E obj)
    {
        add(size, obj);
        return true;
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    /** 
     * The method remove(int index) returns the current element at position 
     * index, and then removes it by moving the elements after it left by one.  
     * A postcondition is the return statement as well as the fact that the 
     * size  must change. This runs in O(n) time.
    * @postcondition removes element from position index, moving elements
    *               at position index + 1 and higher to the left
    *               (subtracts 1 from their indices) and adjusts size
                   returns the element formerly at the specified position
    * @return the element at position index before removal
    * @param index is the position of the linked list desired
    */
    public E remove(int index)
    {
        modified = true;
        E element = null;
        //System.out.println(size);
        if(size==1) 
        {
            element = (E) first.getValue();
            first = null; last = null;
        }
        else if(index==size-1) 
        {
            element = (E) last.getValue();
            last.getPrevious().setNext(null);
            last = last.getPrevious();
            
        } 
        else if(index ==0) 
        {
            element = (E) first.getValue();
            first = first.getNext();
            first.setPrevious(null);
        } 
        else 
        {
            element = get(index);
            DoubleNode node1 = getNode(index+1);
            node1.setPrevious(getNode(index-1));
            node1.getPrevious().setNext(node1);
        }
        size-=1;
        return (E) element;
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        
        //(You will need to promise the return value is of type E.)
    }

    /** 
     * The method add(int index, E obj) adds an element to the linked list at 
     * position index. A precondition is that index is greater than or equal to 
     * 0 and less than the size of the linked list, and a postcondition is that 
     * size will be adjusted. This runs in O(n) time. 
    * @precondition  0 <= index <= size
    * @postcondition inserts obj at position index,
    *                moving elements at position index and higher
    *                to the right (adds 1 to their indices) and adjusts size
    * @param index is the position of the linked list desired
    * @param obj is the element to be inserted 
    */
    public void add(int index, E obj)
    {

        modified = true;
        DoubleNode node1 = new DoubleNode(obj);
        if(index==0 && first!= null) 
        {
            node1.setNext(first);
            first.setPrevious(node1);
            first = node1;
        } 
        else if (index==0 && first==null)
        {
            first = new DoubleNode(obj);
            last = first;
        }
        else if (index==size) 
        {
            node1.setPrevious(last);
            last.setNext(node1);
            last = node1;
        } 
        else 
        {
            DoubleNode nodePrev = getNode(index-1);
            DoubleNode nodeNext = getNode(index);
            //System.out.println(node1.getValue());
            node1.setPrevious(nodePrev);
            nodePrev.setNext(node1);
            node1.setNext(nodeNext);
            nodeNext.setPrevious(node1);
        }
        size+=1;
    }

    /**
     * The method addFirst(E obj) adds element obj to the front of the linked 
     * list. There are no pre/postconditions, and this runs in O(1) time. 
     * @param obj is the element to be added
     */
    public void addFirst(E obj)
    {
        add(0, obj);
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    /**
     * The method addLast(E obj) adds element obj to the end of the linked 
     * list. There are no pre/postconditions, and this runs in O(1) time. 
     * @param obj is the element to be added
     */
    public void addLast(E obj)
    {
        add(size, obj);
    }

    /**
     * The method getFirst() returns the first element in the linked list.  
     * There are no pre/postconditions, and this runs in O(1) time. 
     * @return the first element
     */
    public E getFirst()
    {
        return (E) first;
        //(You will need to promise the return value is of type E.)
    }

    /**
     * The method getLast() returns the last element in the linked list.  There 
     * are no pre/postconditions, and this runs in O(1) time. 
     * @return the last element
     */
    public E getLast()
    {
        return (E) last;
        //(You will need to promise the return value is of type E.)
    }

    /**
     * The method removeFirst() returns the first element in the linked list 
     * and removes it.  There are no pre/postconditions, and this runs in O(1) time. 
     * @return the first element before removal
     */
    public E removeFirst()
    {
        return (E) remove(0);
        //(You will need to promise the return value is of type E.)
    }

    /**
     * The method removeLast() returns the last element in the linked list and 
     * removes it.  There are no pre/postconditions, and this runs in O(1) time. 
     * @return the last element before removal
     */
    public E removeLast()
    {
        return (E) remove(size-1);
        //(You will need to promise the return value is of type E.)
    }

    /**
     * The method iterator() returns a new MyLinkedListIterator and sets modified to false
     * @return a new MyLinkedListIterator
     */
    public Iterator<E> iterator()
    {
        modified = false;
        return new MyLinkedListIterator();
    }

    /**This class implements an iterator for MyLinkedList
    @author Harshini Chaturvedula
    @version 10.26.2021
    */
    private class MyLinkedListIterator implements Iterator<E>
    {
        private DoubleNode nextNode;

        /**
         * This initializes nextNode to be the first node
         */
        public MyLinkedListIterator()
        {
            nextNode = first;
        }

        /**
         * This determines if there is an element left in the linked list and runs in O(1) time
         * @return true if there are still elements left
         */
        public boolean hasNext()
        {
            if(modified) throw new ConcurrentModificationException();
            if (nextNode!=null) return true;
            return false;
        }

        /**
         * This returns the element at the next position of the linked list and runs in O(1) time
         * @return nextNode's element
         */
        public E next()
        {
            if(modified) throw new ConcurrentModificationException();
            DoubleNode node1 = nextNode;
            nextNode = nextNode.getNext();
            return (E) node1.getValue();
            //throw new RuntimeException("INSERT MISSING CODE HERE");

            //(You will need to promise the return value is of type E.)
        }

        /**This emoves the last returned element by method next() and runs in O(1) time.
         * @postcondition removes the last element that was returned by next
        */
        public void remove()
        {	
            DoubleNode node1;
            if(modified) throw new ConcurrentModificationException();
            if(nextNode!=null) 
            {
                node1 = nextNode.getPrevious(); //last returned node
            } 
            else node1 = last;
            if(node1.getPrevious()==null) 
            {
                removeFirst(); modified = false; 
            }
            if(node1.getNext()==null) 
            {  
                removeLast(); modified = false; 
            }
            else 
            {
                if(node1.getPrevious()==null) 
                {
                    node1.getNext().setPrevious(null);
                } 
                else 
                { 
                    node1.getPrevious().setNext(node1.getNext());
                    node1.getNext().setPrevious(node1.getPrevious());
                } 
            }
        }
    }
}