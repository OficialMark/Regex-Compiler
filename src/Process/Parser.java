package Process;

import Model.Components.Element;
import Model.SyntaxTree.Node;
import Model.SyntaxTree.Tree;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Parser {
    static int openParentheses = 0;
    static int closeParentheses = 0;

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String regex = s.nextLine();

        int resultado = process_expression(regex);
    }

    private static int process_expression(String expression){
        Stack<String> operators = new Stack<>();
        Stack<Integer> values   = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {

            if( expression.charAt(i) == ' ') continue;

            if (Character.isDigit(expression.charAt(i))){
                int value = 0;
                while ( i < expression.length() && Character.isDigit(expression.charAt(i))){ //enquanto a cadeia não acabar e o próximo valor for um digito
                    value = value * 10 + Integer.parseInt(String.valueOf(expression.charAt(i)));
                    i++;
                }
                values.add(value);
                continue;
            }

            if (expression.charAt(i) == '('){
                operators.add(String.valueOf(expression.charAt(i)));
            }

            else if (expression.charAt(i) == ')'){
                while (!operators.isEmpty() && operators.get(1).equals(")")){
                    apply_operator(operators, values);
                operators.pop();
                }
            }

            else {
                while (!operators.isEmpty() &&
                        precedence(operators.get(1)) >= precedence(String.valueOf(expression.charAt(i)))){
                            apply_operator(operators, values);
                        }
                operators.add(String.valueOf(expression.charAt(i)));
            }
        }

        while (!operators.isEmpty()){
            apply_operator(operators, values);
        }

        return values.getFirst();
    }

    private static int precedence(String operator){
        if ( operator.equals("+") || operator.equals("-")) return 1;
        if ( operator.equals("*") || operator.equals("/")) return 2;
        if ( operator.equals("^")) return 3;
        return 0;
    }

    private static void apply_operator(Stack<String> operators, Stack<Integer> values){
        int right = values.pop();
        int left  = values.pop();
        String op = operators.pop();

        if ( op.equals("+")){
            values.add( left + right);
        }

        if ( op.equals("-")){
            values.add( left - right);
        }

        if ( op.equals("*")){
            values.add( left * right);
        }

        if ( op.equals("/")){
            values.add( left / right);
        }

        if ( op.equals("^")){
            values.add((int) Math.pow(left, right));
        }
    }
}
