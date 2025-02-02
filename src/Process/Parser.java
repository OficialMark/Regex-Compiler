package Process;

import Model.Components.Sentence;

import java.util.Scanner;
import java.util.Stack;

public class Parser {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Digite a expressão regular: ");
        String regex = s.nextLine();

        try {
            Sentence resultado = process_expression(regex);
            System.out.println("Resultado: " + resultado);
            resultado.ToString();                   //imprime automatos

        } catch (Exception e) {
            System.out.println("Erro ao calcular a expressão: " + e.getMessage());
        }
    }

    private static Sentence process_expression(String regex) {
        Stack<String> operators = new Stack<>();
        Stack<Sentence> values = new Stack<>();

        for (int i = 0; i < regex.length(); i++) {
            char ch = regex.charAt(i);

            if (ch == ' ') continue;

            if (isSymbol(ch)) {
                values.push(new Sentence(regex, i));

                if (isSymbol(regex, i+1)){     //caso o proximo caractere represente uma concatenação
                    i++;                            //tomar cuidado, pode resultar em fora de range

                    while (i < regex.length() && isSymbol(regex, i)) {
                        operators.push("c");
                        values.push(new Sentence(regex, i));

                        i++;
                    }
                }
                //i--; // Ajuste para o incremento no for loop
                continue;
            }

            if (ch == '(') {
                operators.push("(");
            } else if (ch == ')') {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    apply_operator(operators, values);
                }
                operators.pop(); // Remove o '(' da pilha
            } else {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(String.valueOf(ch))) {
                    apply_operator(operators, values);
                }
                operators.push(String.valueOf(ch));
            }
        }

        while (!operators.isEmpty()) {
            apply_operator(operators, values);
        }

        return values.pop();
    }

    private static int precedence(String operator) {
        if (operator.equals("|")) return 1;
        if (operator.equals("c")) return 2; //concat
        if (operator.equals("*")) return 3;
        return 0;
    }

    private static void apply_operator(Stack<String> operators, Stack<Sentence> values) {
        String op = operators.pop();

        switch (op) {
            case "|":
                Sentence right = values.pop();
                Sentence left = values.pop();

                left.union(right);
                values.push(left);
                break;

            case "c":
                right = values.pop();
                left = values.pop();

                left.concat(right);
                values.push(left);
                break;

            case "*":
                Sentence sentence = values.pop();
                sentence.star();

                values.push(sentence);
                break;
        }
    }


    private static boolean isSymbol(String regex, int pos){

        if (regex.length() <= pos) return false;

        if (Character.isDigit(regex.charAt(pos))) return true;
        return Character.isLetter(regex.charAt(pos));
    }

    private static boolean isSymbol(char ch){

        if (Character.isDigit(ch)) return true;
        return Character.isLetter(ch);
    }
}