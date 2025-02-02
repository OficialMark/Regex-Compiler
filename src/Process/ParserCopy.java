package Process;

import Model.Components.Sentence;

import java.util.Scanner;
import java.util.Stack;

public class ParserCopy {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Digite a expressão matemática: ");
        String expression = s.nextLine();

        try {
            int resultado = process_expression(expression);
            System.out.println("Resultado: " + resultado);
        } catch (Exception e) {
            System.out.println("Erro ao calcular a expressão: " + e.getMessage());
        }
    }

    private static int process_expression(String expression) {
        Stack<String> operators = new Stack<>();
        Stack<Integer> values = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (ch == ' ') continue;

            if (isSymbol(ch)) {
                int value = 0;
                while (i < expression.length() && isSymbol(expression, i)) {
                    value = value * 10 + (expression.charAt(i) - '0'); //conversão para inteiro
                    i++;
                }
                values.push(value);
                i--; // Ajuste para o incremento no for loop
                continue;
            }

            if (ch == '(') {
                operators.push(String.valueOf(ch));
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
        if (operator.equals("+") || operator.equals("-")) return 1;
        if (operator.equals("*") || operator.equals("/")) return 2;
        if (operator.equals("^")) return 3;
        return 0;
    }

    private static void apply_operator(Stack<String> operators, Stack<Integer> values) {
        int right = values.pop();
        int left = values.pop();
        String op = operators.pop();

        switch (op) {
            case "+":
                values.push(left + right);
                break;
            case "-":
                values.push(left - right);
                break;
            case "*":
                values.push(left * right);
                break;
            case "/":
                if (right == 0) {
                    throw new ArithmeticException("Divisão por zero.");
                }
                values.push(left / right);
                break;
            case "^":
                values.push((int) Math.pow(left, right));
                break;
        }
    }

    private static boolean isSymbol(String expression, int pos){

        if (Character.isDigit(expression.charAt(pos))) return true;
        return Character.isLetter(expression.charAt(pos));
    }

    private static boolean isSymbol(char ch){

        if (Character.isDigit(ch)) return true;
        return Character.isLetter(ch);
    }
}