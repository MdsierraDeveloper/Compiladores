package analizador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Analizador {
    public static void main(String[] args) {
        try (BufferedReader lector = new BufferedReader(new FileReader("C:\\Users\\manue\\Documents\\NetBeansProjects\\Analizador\\src\\analizador\\Test1.c"))) {
            StringBuilder constructorEntrada = new StringBuilder();
            String linea;
            while ((linea = lector.readLine()) != null) {
                constructorEntrada.append(linea).append("\n");
            }
            String entrada = constructorEntrada.toString();
            AnalizadorLexico analizadorLexico = new AnalizadorLexico(entrada);
            Token token = analizadorLexico.obtenerSiguienteToken();
            while (token.getTipo() != Token.Tipo.EOF) {
                System.out.println(token);
                token = analizadorLexico.obtenerSiguienteToken();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
