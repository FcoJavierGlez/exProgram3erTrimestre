package exMayo2019FranciscoJavierGonzálezSabariego.fichero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase que lee un fichero de texto y modifica línea a línea cada caracter que coincida 
 * con los siguientes caracteres en mayúscula o minúscula: "A", "B", "E", "I", "O", "S", "T"
 * por los siguientes caracteres numéricos: "4", "8", "3", "1", "0", "5", "7".
 * 
 * Además se podrá exportar el resultado a un fichero si así se desea.
 * 
 * 
 * @author Francisco Javier González Sabariego
 *
 */
public class CambiaCaracteres {
  private static BufferedReader r;
  private static BufferedWriter w;
  
  private static String salida = "";
  private static String linea = "";
  
  private static final String [][] CARACTERES = {{"A", "B", "E", "I", "O", "S", "T"},
                                            {"4", "8", "3", "1", "0", "5", "7"}};
  
  private static String ruta1 = "";
  
  
  /**
   * Crea el puntero que lee de cada línea del fichero cuya ruta absoluta se le pasa como parámetro.
   * 
   * @param rutaEntrada Ruta absoluta del fichero a leer.
   * 
   * @throws FileNotFoundException  Se lanza esta excepción si no se encuentra el fichero.
   */
  private static void creaLector(String rutaEntrada) throws FileNotFoundException {
    ruta1 = rutaEntrada;
    r = new BufferedReader(new FileReader(ruta1));
  }
  
  /**
   * Crea el puntero que escribe cada línea en el nuevo fichero.
   * 
   * @param rutaSalida  Ruta absoluta donde se guardará o sobreescribirá el fichero.
   * 
   * @throws IOException Se lanza esta excepción si hubiese algún conflicto al crear el fichero.
   */
  private static void creaEscritor(String rutaSalida) throws IOException {
    w = new BufferedWriter(new FileWriter(rutaSalida));
  }
  
  /**
   * Lee las lineas del fichero y las devuelve modificadas tal como se
   * especifica en el método modificaLineas().
   * 
   * @return  Línea de salida
   * 
   * @throws IOException 
   */
  private static void leeLineas() throws IOException { 
    salida="";
    while (true) {
      linea = r.readLine();
      if (linea==null) 
        break;
      salida+=modificaLinea(linea);
    }
    r.close();
  }  
  
  /**
   * Modifica la línea que recibe como parámetro, caracter a caracter,
   * haciendo uso del método alteraCaracter().
   * 
   * @param entrada Línea de entrada
   * @return  Línea modificada
   */
  private static String modificaLinea(String entrada) { 
    String cadena = "";
    for (int i=0; i<entrada.length(); i++) {
      cadena += alteraCaracter(entrada.substring(i, i+1));
    }
    return cadena+"\n";
  }
  
  /**
   * Devuelve el valor correspondiente al caracter de entrada si coincide 
   * con alguna de las letras: "ABEIOST" por "4831057" respectivamente. 
   * 
   * Si dicho caracter no coincide con ningún valor se retorna el propio caracter.
   * 
   * @param entrada Caracter de entrada (String)
   * @return  Caracter de salida (String)
   */
  private static String alteraCaracter(String entrada) {
    for (int i=0; i<CARACTERES[0].length; i++) {
      if (entrada.toUpperCase().equals(CARACTERES[0][i]))
        return CARACTERES[1][i];
    }
    return entrada;
  }
  
  /**
   * Muestra por pantalla el resultado de haber leído un fichero, que pasamos como parámetro 
   * y que modifica si en el proceso de lectura encuentra alguna ocurrencia de las letras:
   * "ABEIOST" por "4831057" respectivamente. 
   * 
   * @param rutaFichero1  Ruta del fichero de entrada.
   * @throws IOException Excepción si no se encuentra el fichero.
   */
  public static String muestraResultado(String rutaFichero1) throws IOException {
    creaLector(rutaFichero1);
    leeLineas();
    return salida;
  }

  /**
   * Crea el fichero con el resultado de haber modificado las líneas del fichero de entrada
   * 
   * @param rutaFichero2  Ruta del fichero de salida.
   * 
   * @throws IOException  Excepción si no se encuentra el fichero.
   */
  public static void creaFichero(String rutaFichero2) throws IOException {
    creaEscritor(rutaFichero2);
    escribeFichero();
  }
  
  /**
   * Escribe en un fichero el resultado de haber modificado las líneas
   * del fichero de entrada.
   * 
   * @throws IOException  Excepción si no se encuentra el fichero.
   */
  private static void escribeFichero() throws IOException {
    creaLector(ruta1);
    while (true) {
      linea = r.readLine();
      if (linea==null) 
        break;
      w.write(modificaLinea(linea));
    }
    w.close();
  }
  
}
