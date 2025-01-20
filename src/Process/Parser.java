package Process;

import Model.Components.Element;
import Model.SyntaxTree.Node;
import Model.SyntaxTree.Tree;

import java.util.ArrayList;

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

    public static Node GenerateElement(String value){
        if
    }
}
