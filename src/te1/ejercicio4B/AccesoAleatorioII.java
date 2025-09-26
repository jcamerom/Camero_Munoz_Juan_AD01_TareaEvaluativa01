package te1.ejercicio4B;

/*
 * DAM. ACCESO A DATOS
 * UD01: INTRODUCCION Y MANEJO DE FICHEROS
 * Por: Juan Camero Munyoz
 * 
 * 4.Desde la editorial Marvel te han contratado para hacer una aplicacion que gestione 
 * los datos de sus superheroes y supervillanos. Para almacenar la informacion han decidido 
 * utilizar ficheros de distintas clases.
 * 
 * b) Desde la editorial quieren tener controlado el peso de sus personajes, ya que ultimamente 
 * han hecho algun exceso que otro. Realiza un programa en java que te permita modificar 
 * los datos de un personaje. El programa recibe desde la linea de comandos el dni y el peso del 
 * ultimo mes. Si el personaje no existe devolvera un mensaje de error, sino mostrara en la consola 
 * el nombre del personaje y cuantos kilos ha engordado, adelgazado o si se mantiene en su peso. (1,5 puntos)
 *  
 * 
*/

import java.io.BufferedReader;
import java.io.Console;

/*
 * DAM. ACCESO A DATOS
 * UD01: INTRODUCCION Y MANEJO DE FICHEROS
 * Por: Juan Camero Munyoz
 * 
 * 4.Desde la editorial Marvel te han contratado para hacer una aplicacion que gestione 
 * los datos de sus superheroes y supervillanos. Para almacenar la informacion han decidido 
 * utilizar ficheros de distintas clases.
 * 
 * b) Desde la editorial quieren tener controlado el peso de sus personajes, 
 * ya que ultimamente han hecho algun exceso que otro. Realiza un programa en java 
 * que te permita modificar los datos de un personaje. El programa recibe desde la linea 
 * de comandos el dni y el peso del ultimo mes. Si el personaje no existe devolvera
 * un mensaje de error, sino mostrara en la consola el nombre del personaje y cuantos 
 * kilos ha engordado, adelgazado o si se mantiene en su peso. (1,5 puntos)
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class AccesoAleatorioII {
	
	private static final BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
        final int long_registro = 4 + 9*2 + 10*2 + 20*2 + 10*2 + 4 + 4;
        
        // La ruta sera la misma que en el ejercicio anterior porque se guardaron los datos alli
        File fichero = new File("." + File.separator + "src" + File.separator + "te1" + File.separator + "ejercicio4A" + File.separator + "Marvel.dat");
        
        try (RandomAccessFile file = new RandomAccessFile(fichero, "rw")){
            String dniConsola;
            int pesoUltimoMes, pesoFichero, diferenciaPeso, posicion;
            String nombreFichero;
            char[] aux = new char[20]; // La identidad secreta puede ocupar hasta 20 caracteres

            System.out.println("Introduzca el DNI (con letra) del personaje para el control de peso:");
            dniConsola = readString(); // Llama a un metodo privado para hacer la lectura del dni
            System.out.println("Introduzca su peso actual:");
            pesoUltimoMes = readInt();
            
            boolean encontrado = false;
            for (int i = 0; i < file.length() / long_registro; i++) {
                posicion = i * long_registro;
                file.seek(posicion);
                
                // Leer Dni
                StringBuilder bufferDni = new StringBuilder();
                for (int j = 0; j < 11; j++) {
                    bufferDni.append(file.readChar());
                }
                String dniFichero = bufferDni.toString().trim();
                System.out.println(dniFichero);

                // Leer Nombre
                StringBuilder bufferNom = new StringBuilder();
                for (int j = 0; j < 10; j++) {
                    bufferNom.append(file.readChar());
                }
                nombreFichero = bufferNom.toString().trim();
                
                pesoFichero = file.readInt();
                
                // Comprobar si el DNI coincide
                if (dniFichero.equals(dniConsola)) {
                    encontrado = true;
                    diferenciaPeso = pesoUltimoMes - pesoFichero;

                    // Mostrar resultados
                    String resultado;
                    if (diferenciaPeso > 0) {
                        resultado = "ha engordado " + diferenciaPeso + " kilos.";
                    } else if (diferenciaPeso < 0) {
                        resultado = "ha adelgazado " + Math.abs(diferenciaPeso) + " kilos.";
                    } else {
                        resultado = "se mantiene en su peso anterior.";
                    }

                    System.out.println(nombreFichero + " " + resultado);
                    break;
                }
            }
            
            if (!encontrado) {
                System.out.println("Error: El personaje con DNI " + dniConsola + " no existe.");
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: Archivo no encontrado.");
        }
        
   	}
	
	public static String readString() throws IOException {
		
		return entrada.readLine();
	}
	
	public static int readInt() throws IOException {
				
		return Integer.valueOf(entrada.readLine().trim());
	}
}
