package view;

import java.util.ArrayList;
import java.util.Scanner;

public class Entrada {

    public static ArrayList<String> Menu(){
        ArrayList<String> elementos = new ArrayList<>();
        Scanner s = new Scanner(System.in);

        System.out.println("Bem vindo ao Regex Compiler");
        System.out.println("Digite a expressão regular para gerar seu atomato:");

        String texto = s.nextLine();

        for(char x : texto.toCharArray()){
            if (x == '\\'){
                elementos.add("\\" + x);
            }
            else {
                elementos.add(Character.toString(x));
            }
        }

        return elementos;
    }
}
