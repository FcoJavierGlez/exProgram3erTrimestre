package exMayo2019FranciscoJavierGonzálezSabariego.fecha;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * Clase encargada de validar, gestionar (incrementar/decrementar un día)
 * y comparar (con la fecha del sistema) la fecha de entrada (String).
 * 
 * @author Francisco Javier González Sabariego
 *
 */
public class Fechas {
  
  private static int [] dias = {31,28,31,30,31,30,31,31,30,31,30,31}; 
  
  private static int dia;
  private static int mes;
  private static int anno;
  
  private static java.util.Date date = new Date();
  
  private static final int DIA_ACTUAL = date.getDate();
  private static final int MES_ACTUAL = (date.getMonth()+1);
  private static final int ANNO_ACTUAL = (date.getYear()+1900);
  
  private static int cuentaDias;
  
  /**
   * Se introduce una fecha (String) y comprueba si la fecha es correcta según día, mes y año
   * en el formato dd/mm/aaa.
   * 
   * @param fecha  Fecha (String) que se introduce como parámetro.
   * @return  true si la fecha es válida.
   * 
   * @throws fechaInvalida Si la fecha es inválida
   * @throws insertaFecha Si la fecha introducida es una cadena vacía lanza esta excepción.
   */
  public boolean validaFecha(String fecha) throws fechaInvalida, insertaFecha {
    if (fecha.equals(""))
      throw new insertaFecha();
    if (validaFormato(fecha) && validaDigitos(fecha))
      return true;
    else
      throw new fechaInvalida();
  }

  /**
   * Valida que la fecha cumpla con el siguiente formato (dd/mm/aaaa)
   * 
   * @param fecha Fecha (String) pasada como parámetro.
   * @return true si el formato dd/mm/aaaa es verdadero, false si no lo es.
   */
  private boolean validaFormato(String fecha) {
    return Pattern.matches("^[0-9]{2}/[0-9]{2}/[0-9]{4}$", fecha);
  }

  /**
   * Valida que el valor de los días y los meses sea correcto.
   * 
   * @param fecha  Fecha (String) pasada como parámetro.
   * 
   * @return true si los valores son correctos, false si no lo es
   */
  private boolean validaDigitos(String fecha) { 
    return compruebaAnno(fecha) && compruebaMes(fecha) && compruebaDia(fecha);
  }
  
  /**
   * Comprueba que el año introducido no sea inferior a 1000.
   * 
   * @param fecha Fecha (String) pasada como parámetro.
   * @return  true si el año correcto, false si no lo es.
   */
  private boolean compruebaAnno(String fecha) {
    return Integer.parseInt(fecha.substring(6, 10))>999;
  }

  /**
   * Valida que el valor de los meses sea correcto.
   * 
   * @param fecha  Fecha (String) pasada como parámetro.
   * @return  true si es correcto, false si no lo es.
   */
  private boolean compruebaMes(String fecha) {
    return (Integer.parseInt(fecha.substring(3, 5))>0 && Integer.parseInt(fecha.substring(3, 5))<13);
  }

  /**
   * Comprueba si el día es válido para el mes y el año introducido
   * 
   * @param fecha Fecha (String) que se pasa como parámetro
   */
  private boolean compruebaDia(String fecha) {
    ajustaFebrero(fecha);
    return Integer.parseInt(fecha.substring(0, 2))>0 
        && Integer.parseInt(fecha.substring(0, 2))<=(dias[Integer.parseInt(fecha.substring(3, 5))-1]);
  }
  
  /**
   * Comprueba si el año introducido es bisiesto y retorna true o false.
   * 
   * @param anno  Año (int) que se introduce como parámetro
   * @return  true si el año es bisiesto, false si no lo es.
   */
  private boolean esBisiesto(int anno) {
    if ((anno%4)!=0 && (anno%100)!=0)
      return false;
    else if ((anno%4)==0 && (anno%100)!=0)
      return true;
    else if ((anno%4)==0 && (anno%100)==0 && (anno%400)!=0)
      return false;
    return true;
  }
  
  /**
   * Este método comprueba si el año de la fecha introducida es o no bisiesto,
   * en caso de serlo el mes de febrero se ajusta a 29 días pero, 
   * en caso de no serlo el mes se reajusta a 28 días.
   * 
   * @param fecha Fecha (String) que se pasa como parámetro
   */
  private void ajustaFebrero(String fecha) {
    dias[1]=esBisiesto(Integer.parseInt(fecha.substring(6, 10))) ? 29 : 28;
  }
  
  /**
   * Este método comprueba si el año de la fecha almacenada es o no bisiesto,
   * en caso de serlo el mes de febrero se ajusta a 29 días pero, 
   * en caso de no serlo el mes se reajusta a 28 días.
   * 
   */
  private void ajustaFebrero() {
    dias[1]=esBisiesto(anno) ? 29 : 28;
  }
  
  /**
   * Incrementa la fecha insertada (String) como parámetro en un día.
   * 
   * @param fecha Fecha (String) que se pasa como parámetro
   * @return La nueva fecha (String) en formato dd/mm/aaaa.
   * 
   * @throws fechaInvalida Se lanza esta excepción cuando la fecha introducida como parámetro es inválida
   * @throws insertaFecha Si la fecha introducida es una cadena vacía lanza esta excepción.
   */
  public String incrementaDia(String fecha) throws fechaInvalida, insertaFecha {
    if (validaFecha(fecha)) {
      asignaValores(fecha);
      ajustaFebrero(fecha);
      sumaDia();
      return salidaFecha();
    } else 
      throw new fechaInvalida();
  }

  /**
   * Suma un día a la fecha introducida.
   */
  private void sumaDia() {
    if (dia+1>dias[mes-1]) {
      mes+=1;
      if (mes>12) {
        anno+=1;
        mes=1;
        ajustaFebrero();
      }
      dia=1;
    } else 
      dia+=1;
  }
  
  /**
   * Decrementa la fecha insertada (String) como parámetro en un día.
   * 
   * @param fecha Fecha (String) que se pasa como parámetro
   * @return  La nueva fecha (String) en formato dd/mm/aaaa.
   * 
   * @throws fechaInvalida Se lanza esta excepción cuando la fecha introducida como parámetro es inválida
   * @throws insertaFecha Si la fecha introducida es una cadena vacía lanza esta excepción.
   */
  public String decrementaDia(String fecha) throws fechaInvalida, insertaFecha { 
    if (validaFecha(fecha)) {
      asignaValores(fecha);
      ajustaFebrero(fecha);
      restaDia();
      return salidaFecha();
    } else 
      throw new fechaInvalida();
  }

  /**
   * Resta un día a la fecha introducida.
   */
  private void restaDia() {
    if (dia-1<1) {
      mes-=1;
      if (mes<1) {
        anno-=1;
        mes=12;
        ajustaFebrero();
      }
      dia=dias[mes-1];
    } else 
      dia-=1;
  }

  /**
   * Devuelve la nueva fecha con formato dd/mm/aaaa.
   * 
   * @return Resultado nueva fecha (String) dd/mm/aaaa.
   */
  private String salidaFecha() {
    return salidaDia()+salidaMes()+anno;
  }

  /**
   * Si el mes es inferior a 10, se le asigna un 0 delante del 
   * caracter numérico para conservar el formato dd/mm/aaaa.
   * 
   * @return Salida del mes (String)
   */
  private String salidaMes() {
    if (mes<10)
      return "0"+String.valueOf(mes)+"/";
    return String.valueOf(mes)+"/";
  }

  /**
   * Si el día es inferior a 10, se le asigna un 0 delante del 
   * aracter numérico para conservar el formato dd/mm/aaaa.
   * 
   * @return Salida del día (String)
   */
  private String salidaDia() {
    if (dia<10)
      return "0"+String.valueOf(dia)+"/";
    return String.valueOf(dia)+"/";
  }

  /**
   * Asigna a los campos estáticos el día, el mes y el año
   * (siempre que la fecha sea válida) de la fecha que se
   * le pasa como parámetro (String)
   * 
   * @param fecha Fecha (String) que se pasa como parámetro
   */
  private void asignaValores(String fecha) {
    dia = Integer.parseInt(fecha.substring(0, 2));
    mes = Integer.parseInt(fecha.substring(3, 5));
    anno = Integer.parseInt(fecha.substring(6, 10));
  }
  
  /**
   * Devuelve la cantidad de días de diferencia entre la fecha
   * introducida como parámetro (String) y la fecha actual
   * del sistema.
   * 
   * @param fecha Fecha (String) que se pasa como parámetro
   * @return  Total de días de diferencia (int).
   * 
   * @throws fechaInvalida Lanza esta excepción si la fecha
   *                        introducida es inválida.
   * @throws insertaFecha Si la fecha introducida es una cadena
   *                        vacía lanza esta excepción.
   */
  public int diasHastaHoy(String fecha) throws fechaInvalida, insertaFecha {
    if (!validaFecha(fecha))
      throw new fechaInvalida();
    asignaValores(fecha);
    contarDias();
    return cuentaDias;
  }

  /**
   * Cuenta los días que hay de diferencia entre la fecha que
   * se desea comparar y la fecha del sistema.
   */
  private void contarDias() {
    cuentaDias = 0;
    while (comparaFechas()!=0) {
      if (comparaFechas()<0) {
        sumaDia();
        cuentaDias+=1;
      } else if (comparaFechas()>0) {
        restaDia();
        cuentaDias+=1;
      }
    }
  }
  
  /**
   * Compara la fecha introducida con la fecha del sistema y
   * devuelve un valor numérico (int).
   * 
   * @return 0 si ambas son iguales.
   *        &lt;0 si la fecha introducida es anterior a la del sistema.
   *        &gt;0 si la fecha introducida es posterior a la del sistema.
   */
  public int comparaFechas() {
    if (anno==ANNO_ACTUAL) {
      if (mes==MES_ACTUAL) {
        if (dia==DIA_ACTUAL)
          return 0;
        else
          return dia-DIA_ACTUAL;
      } else
        return mes-MES_ACTUAL;
    } else
      return anno-ANNO_ACTUAL;
  }
  
}
