package te1.ejercicio2;

/*
 * DAM. ACCESO A DATOS
 * UD01: INTRODUCCION Y MANEJO DE FICHEROS
 * Por: Juan Camero Munyoz
 * 
 * 2.	Flujos de caracteres: (BufferedReader, BufferedWriter) (1,5 puntos)
Crea un programa en Java que lea un archivo de texto y determine cual es la palabra 
mas larga que contiene. El programa debe mostrar esa palabra por pantalla.
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FlujosDeCaracteresII {
	
    public static void main(String[] args) {
        
    	File inputFile = new File("." + File.separator + "src" + File.separator + "te1" + File.separator + "ejercicio2" + File.separator + "input.txt");
        
        String palabraMasLarga = "";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            
        	String line;
            
            while ((line = reader.readLine()) != null) {
                String[] palabras = line.split(" ");
                
                for (String palabra : palabras) {
                	if (palabra.length() > palabraMasLarga.length()) {
                        palabraMasLarga = palabra;
                    }
                }
            }

            System.out.println(palabraMasLarga);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}