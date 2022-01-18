import java.util.Stack;

/**
 * String Util provides utilities for a string, such as reversing it 
 * and checking if it is a palindrome. 
 * 
 * @author Anu Datar, Harshini Chaturvedula
 * @version 11/9/2021
 */
public class StringUtil
{
    /**
     * This method returns the reversed version of a given string.
     * @param str is the string that will be reversed
     * @return a new string that is the characters of str in reverse.
     */
    public static String reverseString(String str)
    {
        Stack<String> stacker = new Stack<String>();
        for(int i = 0; i<str.length(); i++)
        {
            stacker.push(str.substring(i, i+1));
        }
        String ret = "";
        while(!stacker.isEmpty())
        {
            ret += stacker.pop();
        }
        return ret;
    }

    /**
     * This method determines whether a given string is a palindrome.
     * @param s is the given string that is being checked for if it is a palindrome
     * @return true if s is a palindrome (reversed version of s is the same as s)
     */
    public static boolean isPalindrome(String s)
    {
        boolean is = true;
        String reversed = reverseString(s);
        for(int i = 0; i<s.length(); i++)
        {
            if(!s.substring(i, i+1).equals(reversed.substring(i,i+1))) 
                is = false;
        }
        return is;
    }

    /**
     * The main method is a tester for checking that reverse and isPalindrome work well.
     * @param args 
     */
    public static void main(String[] args)
    {
        String test =  "racecar";
        String test2 = "notapalindrome";

        if ( !("".equalsIgnoreCase(reverseString(""))) )
            System.out.println("** Oops Something went wrong. Check your reverse method **");

        if ( !("a".equalsIgnoreCase(reverseString("a"))) )
            System.out.println("** Oops Something went wrong. Check your reverse method **");

        if (!test.equalsIgnoreCase(reverseString(test)))
            System.out.println("** Oops Something went wrong. Check your reverse method **");
        else
            System.out.println("Success " + test + " matched " + reverseString(test));
            
        if (test2.equalsIgnoreCase(reverseString(test2)))
            System.out.println("** Oops Something went wrong. Check your reverse method **");

    }
}
