package te1.ejercicio3;

/*
 * DAM. ACCESO A DATOS
 * UD01: INTRODUCCION Y MANEJO DE FICHEROS
 * Por: Juan Camero Munyoz
 * 
 * 3. Flujos binarios: (InputStream) (1,5 puntos)
Realiza un programa en Java que lea la cabecera de un fichero PDF y verifique si 
realmente se trata de un archivo PDF valido. Para que un archivo sea un PDF valido, 
debe comenzar con la siguiente secuencia de bytes: {37, 80, 68, 70}. Si la cabecera 
no coincide con esta secuencia, el programa debe informar al usuario de que el 
archivo no es valido (1 punto)
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FlujosBinarios {

    public static void main(String[] args) {
        
    	File inputFile = new File("." + File.separator + "src" + File.separator + "te1" + File.separator + "ejercicio3" + File.separator + "documentoPDF.pdf");
        
        byte[] cabeceraPDF = { 37, 80, 68, 70 };
        
        try (FileInputStream fis = new FileInputStream(inputFile)) {
            byte[] bytesLeidos = new byte[4];
            
            // Leer los primeros 4 bytes del archivo
            int bytesLeidosCount = fis.read(bytesLeidos);
            
            // Verificar si se leyeron 4 bytes
            if (bytesLeidosCount == 4) {
                boolean esPDFValido = true;
                
                // Comparar los bytes leidos con la cabecera de un PDF
                for (int i = 0; i < cabeceraPDF.length; i++) {
                    if (bytesLeidos[i] != cabeceraPDF[i]) {
                        esPDFValido = false;
                        break;
                    }
                }
                
                // Informar al usuario si el archivo es un PDF valido o no
                if (esPDFValido) {
                    System.out.println("El archivo es un PDF valido.");
                } else {
                    System.out.println("El archivo no es un PDF valido.");
                }
            } else {
                System.out.println("No se pudieron leer los primeros 4 bytes del archivo.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}