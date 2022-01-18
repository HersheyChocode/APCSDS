import java.util.Stack;

/**
 * The class expressions can convert an infix expression to a postfix expression, 
 * as well as evaluate a postfix expression. 
 * It also can check if an expression is balanced.
 * 
 * @author Anu Datar, Harshini Chaturvedula
 * @version 12/9/2021
 */

public class Expressions
{
    /**  parenthesis matching : An expression is said to be balanced if
     every opener has a corresponding closer, in the right order
     {, [ or ( are the only types of brackets allowed
     @param expression containing operands operators 
              and any of the 3 supportedbrackets
     @return  true is the parenthesis are balanced         
              false otherwise
    **/
    public static boolean matchParenthesis(String expression)
    {
        Stack<String> stacker = new Stack<>();
        for(int i = 0; i<expression.length(); i++)
        {
            String a = expression.substring(i, i+1);
            if(a.equals("(")||a.equals("[")||a.equals("{"))
            {
                stacker.add(a);
            }
            else if(a.equals(")"))
            {
                if(stacker.isEmpty()) return false;
                if(!stacker.peek().equals("(")) return false;
                else stacker.pop();
            }
            else if(a.equals("]"))
            {
                if(stacker.isEmpty()) return false;
                if(!stacker.peek().equals("[")) return false;
                else stacker.pop();
            }
            else if(a.equals("}"))
            {
                if(stacker.isEmpty()) return false;
                if(!stacker.peek().equals("{")) return false;
                else stacker.pop();
            }
        }
        if(!stacker.isEmpty()) return false;
        return true;
    }
    /**  This returns a string in postfix form 
     if given an expression in infix form as a parameter
     does this conversion using a Stack.
     @param expr valid expression in infix form
     @return equivalent expression in postfix form
    **/
    public static String infixToPostfix(String expr) 
    {
        Stack<String> postFix = new Stack<String>();
        String strPostfix = "";
        for(String a: expr.split(" "))
        {
            if(a.equals("(") || a.equals("{") || a.equals("[")) postFix.push(a);
            else if(a.equals("+") || a.equals("-"))
            {
                if(!postFix.isEmpty())
                {
                    while(!postFix.isEmpty() && 
                            (postFix.peek().equals("+")||
                            postFix.peek().equals("-")||
                            postFix.peek().equals("*") ||
                            postFix.peek().equals("%") ||
                            postFix.peek().equals("/")))
                    {
                        strPostfix+=postFix.pop() + " ";
                    }
                } 
                postFix.push(a);
            }
            else if(a.equals("/") || a.equals("*") || a.equals("%"))
            {
                if(!postFix.isEmpty())
                {
                    while(!postFix.isEmpty() && 
                            (postFix.peek().equals("*") ||
                            postFix.peek().equals("%") ||
                            postFix.peek().equals("/")))
                    {
                        strPostfix+=postFix.pop() + " ";
                    }
                } 
                postFix.push(a);
            }
            else if(a.equals(")")) 
            {
                while(!postFix.isEmpty() && !postFix.peek().equals("(")) 
                {
                    strPostfix += postFix.pop() + " ";
                }
                postFix.pop();
            }
            else if(a.equals("}")) 
            {
                while(!postFix.isEmpty() && !postFix.peek().equals("{")) 
                {
                    strPostfix += postFix.pop() + " ";
                }
                postFix.pop();
            }
            else if(a.equals("]")) 
            {
                while(!postFix.isEmpty() && !postFix.peek().equals("[")) 
                {
                    strPostfix += postFix.pop() + " ";
                }
                postFix.pop();
            }
            else
            {
                strPostfix+=a + " ";
            }
        }
        while(!postFix.isEmpty()) strPostfix+=postFix.pop() + " ";

        return strPostfix;
    }

    /**  This returns the value of an expression in postfix form
     does this computation using a Stack.
     @param expr valid expression in postfix form
     @return value of the expression
     @precondition postfix expression  
                   contains numbers and operators + - * / and %
                   and that operands and operators are separated by spaces
    **/
    public static double evalPostfix(String expr)
    {
        Stack<Integer> postfixOperands = new Stack<Integer>();
        for(String a: expr.split(" "))
        {
            if(a.equals("*"))
            {
                postfixOperands.push(postfixOperands.pop()*postfixOperands.pop());
            } 
            else if(a.equals("/"))
            {
                int div = postfixOperands.pop();
                postfixOperands.push(postfixOperands.pop()/div);
            }  
            else if(a.equals("%"))
            {
                int div = postfixOperands.pop();
                postfixOperands.push(postfixOperands.pop()%div);
            } 
            else if(a.equals("+"))
            {
                postfixOperands.push(postfixOperands.pop()+postfixOperands.pop());
            } 
            else if(a.equals("-"))
            {
                int sub = postfixOperands.pop();
                postfixOperands.push(postfixOperands.pop()-sub);
            } 
            else postfixOperands.push(Integer.parseInt(a));
        }
        return postfixOperands.pop();
    }

    /**
     * The main method acts as a tester to check if infix to postfix and evaluate postfix work well.
     * @param args 
     **/ 
    public static void main(String[] args)
    {
        String exp = "2 + 3 * 4";
        test(exp, 14);

        exp = "8 * 12 / 2";
        test(exp, 48);

        exp = "5 % 2 + 3 * 2 - 4 / 2";
        test(exp, 5);   

        exp = "( 2 + 3 ) * 5";
        test(exp, 25);

        //System.out.println(infixToPostfix(exp));

        // test balanced expressions
        testBalanced("{ 2 + 3 } * ( 4 + 3 )", true);
        testBalanced("} 4 + 4 { * ( 4 + 3 )", false);
        testBalanced("[ [ [ ] ]", false);
        testBalanced("{ ( } )", false);
        testBalanced("( ( ( ) ) )", true);
    }


    /**
     * This method compares an infix expression to its expected value by 
     * converting the expression to a postfix expression and evaluating that postfix expression, 
     * and then finally checking the evaluated expression value with the given expected value.
     * @param expr is the infix expression
     * @param expect is the expected evaluated result of the expression
     */
    public static void test(String expr, double expect)
    {
        String post = infixToPostfix(expr);        
        double val = evalPostfix(post);

        System.out.println("Infix: " + expr);
        System.out.println("Postfix: " + post);
        System.out.println("Value: " + val);
        if (val == expect)
        {
            System.out.print("** Success! Great Job **");
        }
        else
        {
            System.out.print("** Oops! Something went wrong. ");
            System.out.println("Check your postfix and eval methods **");
        }
    }

    /**
     * This method tests if an expression is balanced or not by calling matchParenthesis() 
     * and checks the return value with a given expected boolean value.
     * @param ex is the expression
     * @param expected is the expected boolean value (representing if the 
     *        expression is balanced or not)
     */
    public static void testBalanced(String ex, boolean expected)
    {
        boolean act = matchParenthesis(ex);
        if (act == expected)
            System.out.println("** Success!: matchParenthesis(" + ex + ") returned " + act);
        else
        {
            System.out.print("** Oops! Something went wrong check : matchParen(" + ex + ")");
            System.out.println(" returned " + act + " but should have returned " + expected);
        }
    }
}
