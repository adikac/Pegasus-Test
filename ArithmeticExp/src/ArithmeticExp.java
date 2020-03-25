import java.util.Scanner;
import java.util.Stack;

public class ArithmeticExp 
{ 
    public static int evaluate(String expression) 
    { 
        char[] tokens = expression.toCharArray(); 
  
        Stack<Integer> values = new Stack<Integer>(); 
  
        Stack<Character> ops = new Stack<Character>(); 
  
        for (int i = 0; i < tokens.length; i++) 
        { 
            
            if (tokens[i] >= '0' && tokens[i] <= '9') 
            { 
                values.push(Integer.parseInt(String.valueOf(tokens[i]))); 
            } 
  
            else if (tokens[i] == '(') 
                ops.push(tokens[i]); 
  
            else if (tokens[i] == ')') 
            { 
                while (ops.peek() != '(') 
                  values.push(applyOp(ops.pop(), values.pop(), values.pop())); 
                ops.pop(); 
            } 
  
            else if (tokens[i] == '+' || tokens[i] == '-' || 
                     tokens[i] == '*' || tokens[i] == '/') 
            { 
               
                while (!ops.empty() && checkOrder(tokens[i], ops.peek())) 
                  values.push(applyOp(ops.pop(), values.pop(), values.pop())); 
  
                ops.push(tokens[i]); 
            } 
        } 
  

        while (!ops.empty()) 
            values.push(applyOp(ops.pop(), values.pop(), values.pop())); 
  
        return values.pop(); 
    } 
  
    
    public static boolean checkOrder(char op1, char op2) 
    { 
        if (op2 == '(' || op2 == ')') 
            return false; 
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) 
            return false; 
        else
            return true; 
    } 
  
    
    public static int applyOp(char op, int b, int a) 
    { 
        switch (op) 
        { 
        case '+': 
            return a + b; 
        case '-': 
            return a - b; 
        case '*': 
            return a * b; 
        case '/': 
            if (b == 0) 
                throw new
                UnsupportedOperationException("Cannot divide by zero"); 
            return a / b; 
        } 
        return 0; 
    } 
  
    public static void main(String[] args) 
    { 
    	Scanner input = new Scanner(System.in);

        System.out.print("Enter an expression to compute: ");
        String userInput = input.nextLine();
        //String result = userInput+"="+ArithmeticExp.evaluate(userInput);
        System.out.println(userInput+"="+ArithmeticExp.evaluate(userInput)); 
    }
   
}