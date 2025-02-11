import Model.Components.Sentence;
import java.util.Scanner;
import Process.Parser;

public class Main {
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
}