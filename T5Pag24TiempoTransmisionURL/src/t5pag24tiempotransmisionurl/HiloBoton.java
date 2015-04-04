/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 ***********************************************
►Hilo para medir el tiempo que tarda en transmitirse un recurso URL tecleado
por el usuario , desde el servidor hasta el cliente

►Se basa en el encabezado 'Date' donde el servidor le envía 
al cliente el tiempo transcurrido ( en milisegundos ) 
desde el 1 de enero 1970 GMT
hasta el inicio de la transmision
*************************************************
*/
package t5pag24tiempotransmisionurl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author InTylerWeTrust
 */
public class HiloBoton extends Thread {

// variables locales
 private final String cadenaURL;

//  Constructor
 public HiloBoton(String cadenaURL) {
  this.cadenaURL = cadenaURL;
 }
 
 
//  Código del Hilo
 @Override
 public void run(){
//  
  try{
// Crea un objeto de la clase URL en base a la cadena URL
   URL url = new URL(cadenaURL);
   
// Devuelve un objeto URLConnection -> representa una nueva conexion
//   con el recurso remoto al que se refiere la URL
   URLConnection conexion = url.openConnection();
   
// Establece una conexion entre la aplicacion(cliente) y el recurso(servidor)
//   Permite interactuar con el recurso y consultar los tipos de cabeceras
//    y contenidos para determinar el tipo de recurso que se trata
   conexion.connect();
   
// Fuerza la transmision del recurso mediante su lectura byte a byte 
   InputStream inputStream = conexion.getInputStream();
   while(inputStream.read()> -1){}
   
// Instante 'fin de transmision' con respecto al 1 de enero de 1970 GTM
   long tiempoCliente = System.currentTimeMillis();
   
// Instante 'inicio de transmision' con respecto al 1 de enero 1970 GTM
   long tiempoServidor = conexion.getDate();
   
// Tiempo Transcurrido
   System.out.println(String.format("El tiempo de transmision del recurso "
           +"ha sido de %sms",Math.round(tiempoCliente - tiempoServidor)));
  }catch(MalformedURLException e){
//   
   System.err.println("URL sin sentido");
  }catch(IOException e){
//   
   System.err.println("Error de lectura/escritura");
  }finally{
// termina la aplicacion
   System.exit(0);
  }
 }
}





























