import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: java Main <archivo_entrada> <archivo_salida>");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        Lexer lexer = new Lexer();

        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))
        ) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                List<Token> tokens = lexer.analyzeLine(line, lineNumber);
                String outputLine = lexer.tokensToString(line, tokens);

                writer.write(outputLine);
                writer.newLine();
            }

            System.out.println("Análisis léxico completado.");
            System.out.println("Archivo de salida generado en: " + outputFile);

        } catch (IOException e) {
            System.err.println("Error de archivo: " + e.getMessage());
        }
    }
}