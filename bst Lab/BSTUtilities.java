/**
 * BSTUtilities is a collection of static methods for operating on binary search trees
 * @author Harshini Chaturvedula
 * @version 12.9.2021
 */
public abstract class BSTUtilities
{
    /**
     * This method checks whether a tree contains a certain value. 
     * @precondition t is a binary search tree in ascending order
     * @postcondition returns true if t contains the value x;
     *               otherwise, returns false
     * @param t points to the binary search tree
     * @param x is the value being searched for
     * @param display is the tree display
     * @return true if the tree contains the value x
     */
    public static boolean contains(TreeNode t, Comparable x, TreeDisplay display)
    {
        if(t==null) return false;
        display.visit(t);
        if(x.compareTo(t.getValue())==0) return true;
        else if(x.compareTo(t.getValue())<0) return contains(t.getLeft(), x, display);
        return contains(t.getRight(), x, display);
    }

    /**
     * This method inserts a value into a tree, as long as the value does not 
     * yet exist in the tree. if t is empty, returns a new tree containing x; 
     * otherwise, returns t, with x having been inserted at the appropriate 
     * position to maintain the binary search tree property; x is ignored if it 
     * is a duplicate of an element already in t; only one new TreeNode is 
     * created in the course of the traversal
     * @precondition t is in sorted ascending order
     * @param t points to the binary search tree (sorted in ascending order)
     * @param x is the value being inserted
     * @param display is the tree display
     * @return the tree node after inserting the value x into the tree
     */
    public static TreeNode insert(TreeNode t, Comparable x, TreeDisplay display)
    {
        if(t==null) return new TreeNode(x);
        display.visit(t);
        if(x.compareTo(t.getValue())==0) return t;
        else if(x.compareTo(t.getValue())<0)
        {
            t.setLeft(insert(t.getLeft(),x,display));
            return t;
        }
        else
        {
            t.setRight(insert(t.getRight(),x,display));
            return t;
        }
        //always a leaf node, no duplicate values (use contains method)
    }

    /**
     * This method deletes the node at TreeNode t. 
     * @param t is the treenode to be deleted sorted in ascending order
     * @param display is the tree display
     * @return a pointer to a binary search tree where the value at node t is deleted
     */
    private static TreeNode deleteNode(TreeNode t, TreeDisplay display)
    {
        display.visit(t);
        if(t.getLeft()==null && t.getRight()==null) return null;
        else if (t.getLeft()!=null && t.getRight() == null) return t.getLeft();
        else if (t.getRight()==null && t.getLeft() != null) return t.getRight();
        else 
        {
            t.setValue(TreeUtil.leftmost(t.getRight()));
            t.setRight(delete(t.getRight(),(Comparable)(t.getValue()),display));
            return t;
        }
    }

    /**
     * This method looks for a given value in a tree and deletes it.
     * @param t points to the desired BST sorted in ascending order
     * @param x is the value that is being searched for for deletion
     * @param display is the tree display
     * @return a pointer to a binary search tree where x has been deleted, if 
     * it was present in the tree originally
     */
    public static TreeNode delete(TreeNode t, Comparable x, TreeDisplay display)
    {
        display.visit(t);
        if(t==null) return t;
        else if(x.compareTo(t.getValue())==0)
        {
            t = deleteNode(t, display);
            return t;
        }
        else
        {
            if(x.compareTo(t.getValue())<0) // on the left 
            {
                t.setLeft(delete(t.getLeft(), x, display));
                return t;
            }
            else 
            {
                t.setRight(delete(t.getRight(), x, display));
                return t;
            }
        }
        //if tree does not have node, nothing to be done return original tree
        //3 cases: 1. node is a leaf, 2. node has one child, 3. node has 2 children
        //1. once found value, return null, set parents link to point to null
        //2. you want node's parent to point to child, once found value, return 
        //   child, set parents link to point to child
        //3. find a value close to the value being removed, go to right subtree 
        // and find smallest value (left-most), or go to left subtree and find 
        // largest value (right-most), replace the initial value (the one you 
        // wanted to remove) with the largest/smallest value u just found from 
        //the subtrees - then make sure to remove that large/small value from 
        //the subtree so you don't have repeats
    }
}