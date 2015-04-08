/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabecera_date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author InTylerWeTrust
 */
public class Cabecera_Date {

 /**
  * @param args the command line arguments
 */
 
 String getDefaultValue(){
 DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z",Locale.ENGLISH);
 df.setTimeZone(TimeZone.getTimeZone("GMT"));
 return df.format(new Date());
 }
 
 
 public static void main(String[] args) {
  // TODO code application logic here

  Cabecera_Date nueva = new Cabecera_Date();
   
  System.out.println(nueva.getDefaultValue());

 }
}
