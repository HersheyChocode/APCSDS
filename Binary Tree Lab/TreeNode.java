
/**
 * This class represents a tree node which can point to a left and right tree 
 * node, thereby creating a linked relationship between many tree nodes,
 * forming a tree-structure. 
 * @author Ms. Datar, javadoc-commented by Harshini Chaturvedula :)
 * @version 12.1.2021
 */

public class TreeNode
{
    private Object value;
    private TreeNode left;
    private TreeNode right;

    /**
     * This constructor takes in an initial value for a tree node and sets its 
     * children to null values.
     * @param initValue is the value of the tree node
     */
    public TreeNode(Object initValue)
    { 
        this(initValue, null, null);
    }

    /**
     * This constructor takes in arguments for the value of the tree node and 
     * its corresponding two children.
     * @param initValue is the value of the tree node
     * @param initLeft is the left child's tree node
     * @param initRight is the right child's tree node
     */
    public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
    { 
        value = initValue; 
        left = initLeft; 
        right = initRight; 
    }

    /**
     * This method returns the value of the tree node.
     * @return value (the value of the tree node)
     */
    public Object getValue() 
    { return value; }
    /**
     * This method returns the left child of the tree node.
     * @return left (the left tree node child)
     */
    public TreeNode getLeft() 
    { return left; }
    /**
     * This method returns the right child of the tree node.
     * @return right (the right tree node child)
     */
    public TreeNode getRight() 
    { return right; }

    /**
     * This method sets the tree node to have a new value.
     * @param theNewValue is the new value that the tree node will take on
     */
    public void setValue(Object theNewValue) 
    { value = theNewValue; }
    /**
     * This method sets the left child tree node to a new tree node.
     * @param theNewLeft is the new tree node that the left child will point to
     */
    public void setLeft(TreeNode theNewLeft) 
    { left = theNewLeft; }
    /**
     * This method sets the right child tree node to a new tree node.
     * @param theNewRight is the new tree node that the right child will point to
     */
    public void setRight(TreeNode theNewRight) 
    { right = theNewRight; }
}