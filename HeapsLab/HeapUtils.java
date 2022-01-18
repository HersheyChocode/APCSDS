import java.util.Arrays;

/**
 * This class provides methods useful to a heap, such as insert, remove, 
 * and heapsort. The purpose of this class is to provide the functionality of 
 * sorting  a list of (comparable) values using a heap data structure. 
 * @author Harshini Chaturvedula
 * @version 1.11.22
 */
public class HeapUtils 
{
    /**
     * This method looks at the index of a heap that will be seen as the root of the heap.
     * It calculates the indeces of it's children, and 
     * (provided the indeces are less than the size of the heap)
     * if any of it's children are greater than it, the root's value
     * is swapped with the greatest child's value. If a value is swapped,
     * heapify must be called on the child as well. 
     * This method runs in O(log(n)) time, where n is the size of the heap.
     * @param heap is the array of values that are in the heap
     * @param index is the index of the array representing the node 
     * @param heapSize is the size of the heap
     * being considered the root of the tree to be heapified
     */
    public static void heapify(Comparable[] heap, int index, int heapSize)
    {
        //children = i*2, i*2+1
        int largestIndex = 0;
        if(index * 2 <= heapSize) //left child
        {
            if(heap[index*2].compareTo(heap[index])>0) largestIndex = index*2;
        }
        if(index * 2 + 1 <= heapSize) //right child
        {
            if(heap[index*2+1].compareTo(heap[index])>0)
            {
                if(heap[index*2+1].compareTo(heap[index*2])>0)
                    largestIndex = index * 2 + 1;
            }
        }
        if(largestIndex!=0)
        {
            swap(heap, index, largestIndex);
            heapify(heap,largestIndex,heapSize);
        }
    }    

    /**
     * First, the method will call on the last non-leaf node, which is the heap size/2, 
     * then "heapify" that node.
     * Next, heapify each node from index heap size/2 to 1.
     * If following these steps carefully, the original array should have been
     *  converted to a max heap properly. 
     * This method runs in O(n log(n)) time, where n is the size of the heap.
     * @param heap is the array of values that are in the heap
     * @param heapSize is the size of the heap
     */
    public static void buildHeap(Comparable[] heap, int heapSize)
    {
        for(int i = (int)heap[0]/2; i>=1; i--)
            heapify(heap, i, (int)heap[0]);
    }

    /**
     * This method removes and returns the root of the heap given, but it will maintain
     * the max heap property in the complete binary train that remains. 
     * The root will be swapped with the last leaf, the size will be decreased by 1, 
     * and it will be converted to a max heap at the end.
     * This method runs in O(n log(n)) time, where n is the size of the heap.
     * @return the root of the initial complete binary tree representing the heap
     * @param heap is the array of values that are in the heap
     * @param heapSize is the size of the heap
     */
    public static Comparable remove(Comparable[] heap, int heapSize)
    {
        //heap[0] = heapSize-1;
        swap(heap, 1, heapSize);
        heap[0]=heapSize-1;
        heapSize--;
        heapify(heap, 1, heapSize);
        return heap[(int)heap[0]+1];
        //System.out.println(Arrays.toString(heap));
    }

    /**
     * This method inserts a given item into a heap, and the resulting 
     * complete binary tree must still maintain its heap form. It adds on to
     * the end of the heap, and it comes bubbling up by checking with its parent:
     * if it's greater than its parent, it swaps values, and it keeps doing so
     * until its parent is greater than it. 
     * This method runs in O(n) time, where n is the size of the heap.
     * @param item is the item that will be inserted into the heap.
     * @param heap is the array of values that are in the heap
     * @param heapSize is the size of the heap
     * @return the new max heap after insertion
     */
    public static Comparable[] insert(Comparable[] heap, Comparable item, int heapSize)
    {
        Comparable[] heapInserted = new Comparable[heapSize+2];
        for(int i = 1; i<=heapSize; i++) heapInserted[i] = heap[i];
        heapInserted[0] = heapSize+1;
        heapInserted[heapSize+1] = item;
        buildHeap(heapInserted, heapSize+1);
        heap = heapInserted;
        return heapInserted;
    }

    /**
     * This method sorts a given array by using the heapsort algorithm:
     * First, it will build a heap out of the array of values given.
     * Then, repeatedly remove the root node and replace it with 
     * the last value in the array, decrementing the heap size by 1. 
     * It then heapifies this new tree, and once again replaces the root node,
     * decrements the size, and repeats this process until the heap size is 0.
     * This method runs in O(n log(n)), where n is the size of the heap.
     * @param heap is the array of values that are in the binary heap
     * @param heapSize is the size of the heap initially
     * @return an array of the sorted values initially in the heap
     */
    public static Comparable[] heapSort(Comparable[] heap, int heapSize)
    {
        heap[0] = heapSize;
        //Comparable[] sorted = new Comparable[heapSize+1];
        buildHeap(heap, heapSize);
        //int index = heapSize;
        while((int)heap[0]>=1)
        {
            remove(heap,(int) heap[0]);
        }
        return heap;
    }

    /**
     * This method swaps two values in the heap array with the given indeces.
     * @param ind1 is the index of the first value
     * @param ind2 is the index of the second value
     * @param heap is the array of values that are in the heap
     * @return the array after swapping the desired values
     */
    public static Comparable[] swap(Comparable[] heap, int ind1, int ind2)
    {
        Comparable temp = heap[ind1];
        heap[ind1] = heap[ind2];
        heap[ind2] = temp;
        return heap;
    }
}