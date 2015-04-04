/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.


 ► Con esta sentencia dentro del metodo me dice la hora actual del sistema
 (ZoneId.systemDefault())
 */
package t5cabeceradate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author InTylerWeTrust
 */
public class T5CabeceraDate {

 /**
  * @param args the command line arguments
  * @return
  */
 public static String getDateValue() {
  DateFormat df = new SimpleDateFormat("EEE,d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
//                                    Como dice los Apuntes
//  df.setTimeZone(TimeZone.getTimeZone("GTM"));
//                                     Hora actual del sistema
  df.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
  return df.format(new Date());
 }

 public static void main(String[] args) {
  // TODO code application logic here

//  Intento de crear un bucle que se pare cuando llega la fecha actual
//  do {
//   System.out.println(getDateValue());
// }while(getDateValue().compareTo(ZoneId.systemDefault().toString()) 
//          != getDateValue().compareTo("Wed,1 Apr 2015 14:35:10 CEST"));
//  System.out.println(getDateValue().compareTo("EEE,d MMM yyyy HH:mm:ss z"));
 }
}
