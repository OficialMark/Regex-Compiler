package Process;

import Model.Components.Element;
import Model.SyntaxTree.Node;
import Model.SyntaxTree.Tree;

import java.util.ArrayList;
import java.util.Stack;

public class Parser {
    static int openParentheses = 0;
    static int closeParentheses = 0;

    public static Tree GenerateTree(ArrayList<String> expressao){
        Tree tree = new Tree();

        for (int i = 0; i < expressao.size(); i++){
            String x = expressao.get(i);

            if (x.equals("(")){ openParentheses++;}

            if (x.equals(")")){ closeParentheses++;}
        }



        return tree;
    }

    private int Precedence(String operator){
        if ( operator.equals("+") || operator.equals("-")) return 1;
        if ( operator.equals("*") || operator.equals("/")) return 2;
        if ( operator.equals("^")) return 3;
        return 0;
    }

    private void apply_operator(Stack<String> operators, Stack<Integer> values){
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
