package te1.ejercicio4C;

/*
 * DAM. ACCESO A DATOS
 * UD01: INTRODUCCION Y MANEJO DE FICHEROS
 * Por: Juan Camero Munyoz
 * 
 * 4.Desde la editorial Marvel te han contratado para hacer una aplicacion que gestione 
 * los datos de sus superheroes y supervillanos. Para almacenar la informacion han decidido 
 * utilizar ficheros de distintas clases.
 * 
 * c) Realiza un programa en Java que te permita visualizar los personajes de un tipo concreto 
 * (heroe o villano). El programa recibe desde la linea de comandos el tipo de personaje y visualiza 
 * cuantos personajes hay de dicho tipo y todos los datos de dichos personajes. Verifica que exista dicho 
 * tipo en el fichero, si no existe saca un mensaje de error por pantalla. (1,5 puntos)
 * Nota: Hay que pensar que el fichero puede crecer en un futuro y aparecer nuevos tipos.  
 * 
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
	
	class AccesoAleatorioIII {
	    
    private static final BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        final int long_registro = 11 * 2 + 10 * 2 + 4 + 4; // DNI, Nombre, Peso, Altura
        
        // Ruta del archivo
        File fichero = new File("." + File.separator + "src" + File.separator + "te1" + File.separator + "ejercicio4A" + File.separator + "Marvel.dat");
        
        System.out.println("Introduce un tipo de personaje: ");
        String tipoConsola = readString().trim().toLowerCase();
        
        // Validar tipo
        if (!tipoConsola.equals("heroe") && !tipoConsola.equals("villano")) {
            System.out.println("Error: Tipo de personaje no válido. Debe ser 'heroe' o 'villano'.");
            return;
        }
        
        try (RandomAccessFile file = new RandomAccessFile(fichero, "r")) {
            int contador = 0;
            String nombreFichero;
            String tipoFichero;
            StringBuilder personajes = new StringBuilder();

            for (int i = 0; i < file.length() / long_registro; i++) {
                long posicion = i * long_registro;
                file.seek(posicion);
                
                // Leer DNI
                StringBuilder bufferDni = new StringBuilder();
                for (int j = 0; j < 11; j++) {
                    bufferDni.append(file.readChar());
                }
                String dniFichero = bufferDni.toString().trim();

                // Leer Nombre
                StringBuilder bufferNom = new StringBuilder();
                for (int j = 0; j < 10; j++) {
                    bufferNom.append(file.readChar());
                }
                nombreFichero = bufferNom.toString().trim();

                // Leer Peso
                int pesoFichero = file.readInt();

                // Leer Altura
                int alturaFichero = file.readInt();

                // Leer Tipo (suponiendo que el tipo se almacena como un String de 10 caracteres)
                StringBuilder bufferTipo = new StringBuilder();
                for (int j = 0; j < 10; j++) {
                    bufferTipo.append(file.readChar());
                }
                tipoFichero = bufferTipo.toString().trim();

                // Comprobar si el tipo coincide
                if (tipoFichero.equals(tipoConsola)) {
                    contador++;
                    personajes.append("DNI: ").append(dniFichero)
                              .append(", Nombre: ").append(nombreFichero)
                              .append(", Peso: ").append(pesoFichero)
                              .append(", Altura: ").append(alturaFichero)
                              .append(", Tipo: ").append(tipoFichero)
                              .append("\n");
                }
            }

            // Mostrar resultados
            if (contador > 0) {
                System.out.println("Número de personajes de tipo '" + tipoConsola + "': " + contador);
                System.out.println(personajes.toString());
            } else {
                System.out.println("Error: No se encontraron personajes de tipo '" + tipoConsola + "'.");
            }

        } catch (IOException e) {
            System.err.println("Error: Archivo no encontrado o no se puede leer.");
        }
    }
    
    public static String readString() throws IOException {
        return entrada.readLine();
    }
}
