package Process;

import Model.Components.Sentence;

import java.util.Scanner;
import java.util.Stack;

public class Parser {
    Stack<String> operators = new Stack<>();
    Stack<Sentence> values = new Stack<>();
    String regex;
    Integer i;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Parser p = new Parser();
        System.out.print("Digite a expressão regular: ");
        String input = s.nextLine();

        try {
            Sentence resultado = p.process_expression(input);
            resultado.ToString();                   //imprime automatos

        } catch (Exception e) {
            System.out.println("Erro ao calcular a expressão: " + e.getMessage());
        }
    }

    public Sentence process_expression(String input) {
        regex = input;

        for (i = 0; i < regex.length(); i++) {
            char ch = regex.charAt(i);

            if (ch == ' ') continue;

            if (ch == '\\'){
                processBar();
            }

            if (isSymbol(ch)) {

                if (isSymbol(regex, i+symbolLenght())){     //caso o proximo caractere represente uma concatenação

                    while (i <= regex.length()-symbolLenght() && isSymbol(regex, i) && isSymbol(regex, i+symbolLenght())) {

                        values.push(getSentence());
                        i += symbolLenght();
                        values.push(getSentence());
                        i += symbolLenght();

                        operators.push("c");
                    }
                    i--; // Ajuste para o incremento no for loop
                    continue;
                }

                values.push(getSentence());
                i += symbolLenght() -1;
                continue;
            }

            if (ch == '(') {
                operators.push("(");
            } else if (ch == ')') {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    apply_operator(operators, values);
                }
                operators.pop(); // Remove o '(' da pilha

                if (regex.length()-2 > i || (regex.length()-1 > i && isSymbol(regex, i+1))) {   //verifica se há mais valores após o parenteses, pois se houver, é necessario concatenar
                    operators.add("c");
                }
            } else {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(String.valueOf(ch))) {
                    apply_operator(operators, values);
                }
                operators.push(String.valueOf(ch));


                if (ch == '*'){
                    if (regex.length()-2 > i || (regex.length()-1 > i && isSymbol(regex, i+1))) {   //verifica se há mais valores após a operação star, pois se houver, é necessario concatenar
                        operators.add("c");
                    }
                }
            }
        }

        while (!operators.isEmpty()) {
            apply_operator(operators, values);
        }

        return values.pop();
    }

    private int precedence(String operator) {
        if (operator.equals("|")) return 1;
        if (operator.equals("c")) return 2; //concat
        if (operator.equals("*")) return 3;
        return 0;
    }

    private void apply_operator(Stack<String> operators, Stack<Sentence> values) {
        String op = operators.pop();

        switch (op) {
            case "|":
                Sentence right = values.pop();
                Sentence left = values.pop();

                if (verifyNullElement(op, right, left)) return;

                left.union(right);
                values.push(left);
                break;

            case "c":
                if (values.size() < 2) return;
                right = values.pop();
                left = values.pop();

                if (verifyNullElement(op, right, left)) return;

                left.concat(right);
                values.push(left);
                break;

            case "*":
                Sentence sentence = values.pop();
                sentence.star();

                if (verifyNullElement(op, sentence, null)) return;

                values.push(sentence);
                break;
        }
    }

    private boolean verifyNullElement(String op,Sentence right,Sentence left) {
        switch (op) {
            case "|":
                boolean rightIsNull = right.Initial.Links.getFirst().Symbol.equals("\\null");
                boolean leftIsNull = left.Initial.Links.getFirst().Symbol.equals("\\null");

                if (rightIsNull || leftIsNull) {
                    if (rightIsNull){
                        values.push(left);
                    }

                    if (leftIsNull){
                        values.push(right);
                    }

                    return true;
                }
                break;


            case "c":
                rightIsNull = right.Initial.Links.getFirst().Symbol.equals("\\null");
                leftIsNull = left.Initial.Links.getFirst().Symbol.equals("\\null");

                if (rightIsNull || leftIsNull) {
                    values.push(new Sentence("\\null"));
                    return true;
                }
                break;

            case "*":
                rightIsNull = right.Initial.Links.getFirst().Symbol.equals("\\null");

                if (rightIsNull) {
                    values.push(new Sentence("\\null"));
                    return true;
                }

                break;

        }
        return false;
    }


    private Sentence getSentence(){

        if (regex.charAt(i) == '\\'){
            return processEspecialSymbols();
        }

        return new Sentence(regex, i);
    }

    private Sentence processEspecialSymbols(){
        //sabendo que regex[i] == '\', resta saber se é a expressão '\null' ou '\empty'
        char ch = regex.charAt(i+1);

        if (ch == 'e'){     //      \empty
            return new Sentence("\\empty");
        }

        //      \null
        return  new Sentence("\\null");
    }

    private int symbolLenght(){

        // Caso seja um caracter especial iniciado com '\'
        if (regex.charAt(i) == '\\'){
        char ch = regex.charAt(i+1);

        if (ch == 'e'){     //      \empty
            return 6;
        }

        //      \null
        return 5;
        }

        //caso não, é um caractere comum
        return 1;
    }

    private void processBar(){

    }

    private boolean isSymbol(String regex, int pos){

        if (regex.length() <= pos) return false;

        if (Character.isDigit(regex.charAt(pos))) return true;
        if (Character.isLetter(regex.charAt(pos))) return true;
        return regex.charAt(pos) == '\\';
    }

    private boolean isSymbol(char ch){

        if (Character.isDigit(ch)) return true;
        if (Character.isLetter(ch)) return  true;
        return  ch == '\\';
    }
}