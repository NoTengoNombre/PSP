/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pag19clientesmtp_2;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Libreria IO
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
// Libreria NET
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author InTylerWeTrust
 */
public class Pag19ClienteSMTP_2 {

 /**
  * @param args the command line arguments
  * @throws java.io.IOException
  */
 
 public static void main(String[] args) throws IOException {
//  ◘ Clases
//  Nuestro propio cliente
  Socket smtpSocket = null;
//  Flujo de entrada
  DataInputStream socketIn = null;
//  Flujo de salida
  DataOutputStream socketOut = null;
  
// ►  Abrimos una conexion con beogran  en el puerto 25 
//     que es el puerto por defecto para el protocolo smtp
//     intentamos abrir los streams de entrada y salida
  
// ↕ Intenta 
//  Abrir el socket del puerto 25
//  Abrir los flujos de entrada y salida
   try {
//   Crea un socket con "beogran" y el puerto 25
    smtpSocket = new Socket("smtp.gmail.com",25);
//Entrada de flujo -> con el objeto socket obteniendo la entrada flujo de un canal 
    socketIn = new DataInputStream(smtpSocket.getInputStream());
//Salida de flujo -> con el objeto socket obteniendo la salida del flujo de un canal
    socketOut = new DataOutputStream(smtpSocket.getOutputStream());
    }catch(UnknownHostException e){
     System.out.println("No conozco el host : "+e);
    }catch(IOException e){
     System.out.println("No podría obtener la entra y salida desde la conexión al host : "+e);
    }
   
// ► Si todo esta inicializado correctamente , vamos a escribir
//   algunos datos en el canal de salida que se han establecido
//   con el puerto del protocolo smtp del servidor
   if ( smtpSocket != null && socketIn != null && socketOut != null){

// ↕ Intenta
    try {
// Tenemos que respetar la especificacion SMTP dada 
// en el RFC1822/3 de forma que lo que va en mayusculas 
// antes de los dos puntos tiene un significado especial
// en el protocolo
    socketOut.writeBytes("HELO\n");
    socketOut.writeBytes( "MAIL From : raulvelasalas@hotmail.es\n" );
    socketOut.writeBytes( "RCPT to : raulvelasalas@hotmail.es\n" );
    socketOut.writeBytes( "DATA\n" );
    socketOut.writeBytes( "From : raulvelasalas@hotmail.es\n" );
    socketOut.writeBytes( "Subject: Pruebas\n" );
//  Ahora el cuerpo del mensaje
    socketOut.writeBytes("Hola, desde el Tutorial de Java\n");
    socketOut.writeBytes("\n.\n");
    socketOut.writeBytes("QUIT");
    
// ►  Nos quedamos a la espera de recibir el "Ok" del servidor
//    servidor para saber que ha recibido el mensaje 
//    correctamente momento en el cual cortamos
    String respuesta;
// ☼ Mientras → respuesta igual entrada de linea distinta de null
    while ((respuesta = socketIn.readLine()) != null){
//   Mostrar por pantalla respuesta
     System.out.println("Servidor : "+respuesta);
//    Si respuesta tiene indice Ok distinto a 1
     if (respuesta.indexOf("Ok") != -1) {
      break;
     }
    }
//     Cerramos todo lo que hemos abierto
     socketOut.close();
     socketIn.close();
     smtpSocket.close();
    }catch(UnknownHostException e){
            System.out.println("Intentando conectar "+e);
    }catch(IOException e){
            System.out.println(e);
            }
  }
 }
}





















