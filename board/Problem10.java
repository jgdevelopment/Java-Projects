import java.util.Stack;
import java.util.ArrayList;
/**
 *  A class that evaluate expressions by converting them from infix to postfix
 * 
 * @author Jason Ginsberg
 * @version Feb 2015
 */
public class Problem10
{
    private static String operators = "+*("; 
    public static int parseExpression (String infix){
        Stack<Character> stack = new Stack();
        ArrayList<String> output = new ArrayList();
        int num = 0;
        for (int x=0;x<infix.length();x++){
            Character digit = infix.charAt(x);
            if (Character.getNumericValue(digit)==-1){ //opeartor
                if (num>0){
                    output.add(Integer.toString(num));
                }
                num = 0;
                if (stack.empty()){
                    stack.push(digit);
                }
                else{
                    if (digit == ')'){
                        while (!stack.empty()){
                            if (stack.peek()=='('){
                                stack.pop();
                                break;
                            }
                            output.add(Character.toString(stack.pop()));
                        }
                    }
                    else{
                        if (precendenceOf(stack.peek())<precendenceOf(digit)){
                            stack.push(digit);
                        }
                        else{
                            while (!stack.empty()&&
                            precendenceOf(stack.peek())>precendenceOf(digit)){
                                if (stack.peek()=='('){
                                    break;
                                }
                                else{
                                    output.add(Character.toString(stack.pop()));
                                }
                            }
                            stack.push(digit);
                        }
                    }
                }
            }
            else{ //operand
                num = Character.getNumericValue(digit)+num*10;
                if (x==infix.length()-1){
                    output.add(""+num);
                }
            }
        }
        while (!stack.empty()){
            if (stack.peek()==')'){
                stack.pop();
                while (stack.peek()!='('){
                    output.add(Character.toString(stack.pop()));
                }
                stack.pop();
            }
            else{
                output.add(Character.toString(stack.pop()));
            }
        }
        return evaluate(output);
    }

    private static int evaluate(ArrayList<String> output){
        Stack<String> eval = new Stack();
        String tot = "";
        for (String s : output){
            s = s.replaceAll("\\s","");
            if (!operators.contains(s)){
                eval.push(s); 
            }
            else {
                if (s.equals("*")){
                    tot = ""+
                    Integer.parseInt(eval.pop())*
                    Integer.parseInt(eval.pop());
                    eval.push(tot);
                }
                else if (s.equals("+")){
                    tot = ""+
                    (Integer.parseInt(eval.pop())+
                        Integer.parseInt(eval.pop()));
                    eval.push(tot);
                }
                else if (s.equals("(")){
                }
            }
        }
        return Integer.parseInt(eval.pop());
    }

    private static int precendenceOf(char op){
        return operators.indexOf(op);
    }
}