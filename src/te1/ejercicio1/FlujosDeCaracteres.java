package te1.ejercicio1;

/*
 * DAM. ACCESO A DATOS
 * UD01: INTRODUCCION Y MANEJO DE FICHEROS
 * Por: Juan Camero Munyoz
 * 
 * 1.	Flujos de caracteres: (FileReader, FileWriter) (1,5 puntos)
Escribe un programa en Java que lea una linea de texto desde un archivo y 
devuelva la misma linea, pero con las palabras invertidas individualmente. 
Es decir, si el archivo contiene "Hola Mundo", el archivo de salida deberia 
contener "aloH odnuM".
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FlujosDeCaracteres {

    public static void main(String[] args) {
        
    	File inputFile = new File("." + File.separator + "src" + File.separator + "te1" + File.separator + "ejercicio1" + File.separator + "input.txt");
        File outputFile = new File("." + File.separator + "src" + File.separator + "te1" +File.separator + "ejercicio1" + File.separator + "output.txt");
        
        // Los recursos reader y writer se cierren automaticamente al final del bloque
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            
            // Mientras line no obtenga un null continuara ejecutandose el bucle
            while ((line = reader.readLine()) != null) {
                String invertedLine = invertirPalabras(line);
                writer.write(invertedLine);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String invertirPalabras(String line) {
        
    	String[] words = line.split(" ");
        StringBuilder invertedLine = new StringBuilder();

        for (String word : words) {
        	// Anyade cada palabra del array words a la linea invertida, pero en sentido inverso, y le suma un espacio final
            invertedLine.append(new StringBuilder(word).reverse()).append(" ");
        }
        
        // En el return de la linea invertida se asegura que no devuelva espacios finales
        return invertedLine.toString().trim();
    }
}