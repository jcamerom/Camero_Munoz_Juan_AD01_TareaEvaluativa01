package te1.ejercicio4A;

/*
 * DAM. ACCESO A DATOS
 * UD01: INTRODUCCION Y MANEJO DE FICHEROS
 * Por: Juan Camero Munyoz
 * 
 * 4.Desde la editorial Marvel te han contratado para hacer una aplicacion que gestione 
 * los datos de sus superheroes y supervillanos. Para almacenar la informacion han decidido 
 * utilizar ficheros de distintas clases.
 * 
 * a) Realiza un programa en java para guardar datos de personajes en un fichero aleatorio, 
 * dale el nombre Marvel.dat. Introduce la informacion de los personajes a partir de los 
 * arrays que se te proporcionan en la plataforma Moodle. Cuando termine de realizar la carga 
 * de datos debera informar al usuario de que la carga se ha realizado satisfactoriamente o no. 
 * Los datos por cada personaje son: (1,5 puntos)
 * 
 * Id:					Numero Entero.
 * DNI: 				String [9].
 * Nombre: 				String[10].
 * Identidad secreta: 	String[20].
 * Tipo: 				String[10]
 * Peso: 				Numero Entero.
 * Altura: 				Numero Entero
 * 
 * Arrays con los datos a insertar en el fichero
 * ---------------------------------------------
 * SUPERHEROES
 * -----------
 * 	int [] ids= {1, 2, 3, 4, 5, 6, 7};
 *  String[] dnis= {"01010101A", "03030303C", "05050505E", "07070707G", "02020202B", "04040404D", "06060606F"};
 *  String[] noms= {"Spiderman", "Green Goblin", "Storm", "Wolverine", "Mystique", "IronMan", "Mandarin"};
 *  String[] identidades = {"Peter Parker", "Norman Osborn", "Ororo Munroe","James Howlett", "Raven Darkholme", "Tony Stark", "Zhang Tong"};
 *  String[] tipos = {"heroe","villano","heroe","heroe","villano","heroe","villano"};
 *  int[] pesos = {76,84,66,136,78,102,70};
 *  int[] alturas = {178,183,156,152,177,182,188};
 *  
 * 
*/

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class AccesoAleatorio {
    
    public static void main(String[] args) throws IOException {
        
    	final int long_registro = 4 + 9*2 + 10*2 + 20*2 + 10*2 + 4 + 4;
    	
        File fichero = new File("." + File.separator + "src" + File.separator + "te1" + File.separator + "ejercicio4A" + File.separator + "Marvel.dat");
        
        if (fichero.exists()) {
            fichero.delete();
        }
        
        try (RandomAccessFile file = new RandomAccessFile(fichero, "rw")) {
        	int[] ids = {1, 2, 3, 4, 5, 6, 7};
            String[] dnis = {"01010101A", "03030303C", "05050505E", "07070707G", "02020202B", "04040404D", "06060606F"};
            String[] noms = {"Spiderman", "Green Goblin", "Storm", "Wolverine", "Mystique", "IronMan", "Mandarin"};
            String[] identidades = {"Peter Parker", "Norman Osborn", "Ororo Munroe", "James Howlett", "Raven Darkholme", "Tony Stark", "Zhang Tong"};
            String[] tipos = {"heroe", "villano", "heroe", "heroe", "villano", "heroe", "villano"};
            int[] pesos = {76, 84, 66, 136, 78, 102, 70};
            int[] alturas = {178, 183, 156, 152, 177, 182, 188};

            StringBuffer bufferDni = null;
            StringBuffer bufferNom = null;
            StringBuffer bufferIdent = null;
            StringBuffer bufferTipo = null;
            int cuantos = dnis.length;
            int posicion = 0;

            for (int i = 0; i < cuantos; i++) {
                posicion = i * long_registro;
                file.seek(posicion);
                
                file.writeInt(ids[i]); 
                
                bufferDni = new StringBuffer(dnis[i]); 
                bufferDni.setLength(9);
                file.writeChars(bufferDni.toString());
                
                bufferNom = new StringBuffer(noms[i]);
                bufferNom.setLength(10);
                file.writeChars(bufferNom.toString());
                
                bufferIdent = new StringBuffer(identidades[i]);
                bufferIdent.setLength(20);
                file.writeChars(bufferIdent.toString());
                
                bufferTipo = new StringBuffer(tipos[i]); 
                bufferTipo.setLength(10);
                file.writeChars(bufferTipo.toString());
                
                file.writeInt(pesos[i]);
                file.writeInt(alturas[i]); 
            }
            
            System.out.println("La carga de los personajes ha terminado correctamente.");
            
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
        
    }

}

