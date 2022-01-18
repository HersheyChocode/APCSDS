import java.util.*;
/**
 * TreeUtil contains the following methods for manipulating binary trees:
 * maxDepth, createRandom, leftMost, rightMost, countNodes, countLeaves, preOrder,
 * inOrder, postOrder, fillList, saveTree, buildTree, loadTree, getUserInput ,twentyQuestionsRound 
 * twentyQuestions, copy, sameShape, createDecodingTree, insertMorse, decodeMorse
 * @author Harshini Chaturvedula
 * @version 12.01.2021
 *
 */
public class TreeUtil
{
    //used to prompt for command line input
    private static Scanner in = new Scanner(System.in);
    
    private static final boolean debug = false;


    /**
     * This method returns the left-most leaf node.
     * @param t t is the tree node being explord
     * @return the left-most leaf node in the tree t
     */
    public static Object leftmost(TreeNode t)
    {
        // implement with a loop
        if(t==null) return null;
        while(t.getLeft()!=null)
        {
            t = t.getLeft();
        }
        return t.getValue();
    }

    /**
     * This method returns the right-most leaf node.
     * @param t t is the tree node being explord
     * @return the right-most leaf node in the tree t
     */
    public static Object rightmost(TreeNode t)
    {
        if(t==null) return null;
        else if(t.getRight()==null) return t.getValue();
        return rightmost(t.getRight());
    }
    /**
     * This method returns the maximum depth of a tree.
     * @param t t is the tree node being explord
     * @return the value of the maximum depth of tree node t
     */
    public static int maxDepth(TreeNode t)
    {
        if(t==null) return 0;
        return 1+Math.max(maxDepth(t.getRight()),maxDepth(t.getLeft())); 
    }

    /**
     * This method create a random tree of the specified depth.  No attempt to balance the tree
     * is provided.
     * @param depth of the tree
     * @return TreeNode object that points to the generated tree
     */
    public static TreeNode createRandom(int depth)
    {
        if (Math.random() * Math.pow(2, depth) < 1)
            return null;
        return new TreeNode(((int)(Math.random() * 10)),
            createRandom(depth - 1),
            createRandom(depth - 1));
    }
    /**
     * This method counts the number of nodes in a tree.
     * @param t is the given tree
     * @return an integer representing the number of nodes in t
     */
    public static int countNodes(TreeNode t)
    {
        if(t==null) return 0;
        return 1 + countNodes(t.getLeft()) + countNodes(t.getRight());
    }
    /**
     * This method counts the number of leaves in a tree.
     * @param t is the given tree
     * @return an integer representing the number of leaves in t
     */
    public static int countLeaves(TreeNode t)
    {
        if(t==null) return 0;
        if(t.getRight()==null) return 1;
        return countLeaves(t.getLeft()) + countLeaves(t.getRight());
    }
    /**
     * This method traverses a tree in a pre-order method of traversal.
     * @param t is the given tree node
     * @param display is a tree display that will show which nodes have been visited
     */
    public static void preOrder(TreeNode t, TreeDisplay display)
    {
        //root, left, right
        if(t!=null) 
        {
            display.visit(t);
            preOrder(t.getLeft(), display);
            preOrder(t.getRight(), display);
        }
    }
    /**
     * This method traverses a tree in a in-order method of traversal.
     * @param t is the given tree node
     * @param display is a tree display that will show which nodes have been visited
     */
    public static void inOrder(TreeNode t, TreeDisplay display)
    {
        //left, root, right
        if(t!=null) 
        {
            inOrder(t.getLeft(), display);
            display.visit(t);
            inOrder(t.getRight(), display);
        }
    }
    /**
     * This method traverses a tree in a in-order method of traversal.
     * @param t is the given tree node
     * @param display is a tree display that will show which nodes have been visited
     */
    public static void postOrder(TreeNode t, TreeDisplay display)
    {
        //left, right, root
        if(t!=null) 
        {
            postOrder(t.getLeft(), display);
            postOrder(t.getRight(), display);
            display.visit(t);
        }
    }
    /**
     * This method generates a string list representing a tree, following preorder traversal.
     * @param t is the given tree
     * @param list is the list of strings that will be updated
     */
    public static void fillList(TreeNode t, List<String> list)
    {
        //root, left, right
        if(t==null) list.add("$");
        else 
        {
            list.add(String.valueOf(t.getValue()));
            fillList(t.getLeft(), list);
            fillList(t.getRight(), list);
        }
    }
    /**
     * saveTree uses the FileUtil utility class to save the tree rooted at t
     * as a file with the given file name.
     * @param fileName is the name of the file to create which will hold the data
     *        values in the tree
     * @param t is the root of the tree to save
     */
    public static void saveTree(String fileName, TreeNode t)
    {
        List<String> list = new ArrayList<String>();
        fillList(t,list);
        FileUtil.saveFile(fileName, list.iterator());
    }
    /**
     * buildTree takes in an iterator which will iterate through a valid description of
     * a binary tree with String values.  Null nodes are indicated by "$" markers.
     * @param it the iterator which will iterate over the tree description
     * @return a pointer to the root of the tree built by the iteration
     */
    public static TreeNode buildTree(Iterator<String> it)
    {
        if(it.hasNext()) 
        {
            String c = it.next();
            if(c.equals("$")) 
            {
                return null;
            }
            else
            {
                TreeNode t = new TreeNode(c);
                TreeNode t2 = buildTree(it);
                t.setLeft(t2);
                t.setRight(buildTree(it));
                return t;
            }
        } 
        return null;

    }
    /**
     * loadTree reads a file description of a tree and then builds the tree.
     * @param fileName is a valid file name for a file that describes a binary tree
     * @return a pointer to the root of the tree
     */
    public static TreeNode loadTree(String fileName)
    {
        return buildTree(FileUtil.loadFile(fileName));
    }
    /**
     * This method is a utility method that waits for a user to type text 
     * into Std Input and then press enter.
     * @return the string entered by the user
     */
    private static String getUserInput()
    {
        return in.nextLine();
    }
    /**
     * This method plays a single round of 20 questions
     * postcondition:  plays a round of twenty questions, asking the user questions as it
     *                 walks down the given knowledge tree, lighting up the display as it goes;
     *                 modifies the tree to include information learned.
     * @param t a pointer to the root of the game tree
     * @param display which will show the progress of the game
     */
    private static void twentyQuestionsRound(TreeNode t, TreeDisplay display)
    {	
        display.displayTree(t);
        boolean quit = false;
        TreeNode t2 = t;
        for(int i=0; i<20 && !quit; i++)
        {
            display.visit(t2);
            System.out.println("Is it a " + t2.getValue() +"?");
            boolean answer = Boolean.valueOf(getUserInput().equals("yes"));
            if(answer)
            {
                if(t2.getLeft()==null && t2.getRight()==null)
                {
                    System.out.println("You win!!");
                    quit = true;
                }
                else t2 = t2.getLeft();
            }
            else 
            {
                if(t2.getLeft()==null && t2.getRight()==null)
                {
                    System.out.println("I give up. What is it?");
                    String actual = getUserInput();
                    System.out.println("What differentiates a " + actual + " from a " 
                            + t2.getValue()+"?");
                    String difference = getUserInput();
                    Object existing = t2.getValue();
                    t2.setValue(difference);
                    t2.setLeft(new TreeNode(actual));
                    t2.setRight(new TreeNode(existing));
                    quit = true;
                    display.visit(t2);
                }
                else t2 = t2.getRight();
            }
        }
    }
    /** 
     * This method plays a game of 20 questions until the user decides to quit
     * Begins by reading in a starting file and then plays multiple rounds
     * until the user enters "quit".  Then the final tree is saved.
     */
    public static void twentyQuestions()
    {
        String answer = "";
        while(!answer.equals("quit"))
        {
            TreeNode t = loadTree("knowledge.txt");
            twentyQuestionsRound(t, new TreeDisplay());
            saveTree("knowledge.txt", t);
            t = loadTree("knowledge.txt");
            System.out.println("Do you want to continue playing? Say 'quit' if not.");
            answer = getUserInput();
        }
    }
    /**
     * This method copyies a binary tree.
     * @param t the root of the tree to copy
     * @return a new tree, which is a complete copy
     *         of t with all new TreeNode objects
     *         pointing to the same values as t (in the same order, shape, etc)
     */
    public static TreeNode copy(TreeNode t)
    {
        if(t==null) return null;
        TreeNode t2 = new TreeNode(t.getValue());
        t2.setLeft(copy(t.getLeft()));
        t2.setRight(copy(t.getRight()));
        return t2;
    }
    
    /**
     * This method tests to see if two trees have the same shape, but not necessarily the
     * same values.  Two trees have the same shape if they have TreeNode objects
     * in the same locations relative to the root.
     * @param t1 pointer to the root of the first tree
     * @param t2 pointer to the root of the second tree
     * @return true if t1 and t2 describe trees having the same shape, false otherwise
     */
    public static boolean sameShape(TreeNode t1, TreeNode t2)
    {
        if(t1==null && t2==null) return true;
        else if(t1==null || t2==null) return false;
        if(t1.getLeft()==null && t2.getLeft()==null)
        {
            if(t1.getRight()==null)
            {
                if(t2.getRight()==null) return true;
            }
            else if(t2.getRight()!=null && 
                    t1.getRight().getValue()==t2.getRight().getValue()) return true;
        }
        else if(t2.getRight()==null && t2.getLeft()!=null && 
                t2.getLeft().getValue()==t1.getLeft().getValue()) return true;
        else if((t1.getLeft().getValue()==t2.getLeft().getValue() && 
                t1.getRight().getValue() ==t2.getRight().getValue()))
        {
            return sameShape(t1.getLeft(),t2.getLeft()) && sameShape(t1.getRight(), t2.getRight());
        }
        return false;
    }
    /**
     * This method generates a tree for decoding Morse code.
     * @param display the display that will show the decoding tree
     * @return the decoding tree
     */
    public static TreeNode createDecodingTree(TreeDisplay display)
    {
        TreeNode tree = new TreeNode("Morse Tree");
        display.displayTree(tree);
        insertMorse(tree, "a", ".-", display);
        insertMorse(tree, "b", "-...", display);
        insertMorse(tree, "c", "-.-.", display);
        insertMorse(tree, "d", "-..", display);
        insertMorse(tree, "e", ".", display);
        insertMorse(tree, "f", "..-.", display);
        insertMorse(tree, "g", "--.", display);
        insertMorse(tree, "h", "....", display);
        insertMorse(tree, "i", "..", display);
        insertMorse(tree, "j", ".---", display);
        insertMorse(tree, "k", "-.-", display);
        insertMorse(tree, "l", ".-..", display);
        insertMorse(tree, "m", "--", display);
        insertMorse(tree, "n", "-.", display);
        insertMorse(tree, "o", "---", display);
        insertMorse(tree, "p", ".--.", display);
        insertMorse(tree, "q", "--.-", display);
        insertMorse(tree, "r", ".-.", display);
        insertMorse(tree, "s", "...", display);
        insertMorse(tree, "t", "-", display);
        insertMorse(tree, "u", "..-", display);
        insertMorse(tree, "v", "...-", display);
        insertMorse(tree, "w", ".--", display);
        insertMorse(tree, "x", "-..-", display);
        insertMorse(tree, "y", "-.--", display);
        insertMorse(tree, "z", "--..", display);
        return tree;
    }
    /**
     * This method is a helper method for building a Morse code decoding tree.
     * postcondition:  inserts the given letter into the decodingTree,
     *                 in the appropriate position, as determined by
     *                 the given Morse code sequence; lights up the display
     *                 as it walks down the tree
     * @param decodingTree is the partial decoding tree
     * @param letter is the letter to add
     * @param code is the Morse code for letter
     * @param display is the display that will show progress as the method walks 
     *        down the tree
     */
    private static void insertMorse(TreeNode decodingTree, String letter,
                                    String code, TreeDisplay display)
    {
        TreeNode t = decodingTree;
        TreeNode t2 = t;
        for(int i = 0; i<code.length(); i++) 
        {
            String c = code.substring(i, i+1);
            if(c.equals("."))
            {
                if(t2.getLeft()==null) 
                {
                    t2.setLeft(new TreeNode(""));
                }
                t2 = t2.getLeft();
            }
            else if(c.equals("-"))
            {
                if(t2.getRight()==null) 
                {
                    t2.setRight(new TreeNode(""));
                }
                t2 = t2.getRight();
            }
            //display.visit(t2);
        }
        t2.setValue(letter);            
    }
    /**
     * This method decodes Morse code by walking the decoding tree according to the input code.
     * @param decodingTree is the Morse code decoding tree
     * @param cipherText is Morse code consisting of dots, dashes, and spaces
     * @param display is the display object that will show the decoding progress
     * @return the string represented by cipherText
     */
    public static String decodeMorse(TreeNode decodingTree, String cipherText, TreeDisplay display)
    {
        String[] letters = cipherText.split(" ");
        String word = "";
        for(String l: letters)
        {
            TreeNode t = decodingTree;
            for(int i = 0; i<l.length(); i++)
            {
                String c = l.substring(i, i+1);
                if (c.equals("."))
                {
                    t = t.getLeft();
                }
                else t = t.getRight();
            }
            word+=t.getValue();
            display.visit(t);
        }
        return word;
    }

    /**
     * This method debugs printout.
     * postcondition: out is printed to System.out
     * @param out the string to send to System.out
     */
    
    private static void debugPrint(String out)
    {
        if(debug) System.out.println("debug: " + out);
    }
}