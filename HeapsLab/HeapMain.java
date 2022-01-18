import java.util.Arrays;

/**
 * This is the main class for the Heaps lab, and it acts as a tester,
 * checking the insert and heapSort methods. 
 * @author Harshini Chaturvedula
 * @version 1.11.22
 */
public class HeapMain 
{
    /**
     * This main method acts as a tester for the HeapUtils class. It tests
     * the prominent methods of HeapUtils: insert, remove, and heapSort.
     * Since heapSort already implements remove, I can just use heapSort to
     * test if removed worked as expected as well. 
     * After printing the values after insertion and sorting, I can manually (visibly)
     * check if it has worked accurately. 
     * 
     * @param args 
     */
    public static void main(String[] args) 
    {
        //New heap display
        HeapDisplay display = new HeapDisplay();

        //creates the randomized array
        Comparable[] arr = new Integer[12];
        arr[0]=-1;
        for(int i = 1; i<12; i++) arr[i] = (int)(Math.random()*100);
        System.out.println("original array: " +  Arrays.toString((arr)));

        //adds 50 to the randomized array
        arr = HeapUtils.insert(arr, 50, 11);
        //printing to test if insert worked properly:
        System.out.println("after insertion: " + Arrays.toString(arr));
        //sorts the modified array:
        arr = HeapUtils.heapSort(arr, 12);
        //printing to test if heapSort worked properly:
        System.out.println("after sorting: " + Arrays.toString(arr));
    }
}
