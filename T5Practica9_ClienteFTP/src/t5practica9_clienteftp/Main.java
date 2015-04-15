/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
****************************************************** 
Modifica el ejemplo del cliente FTP 
(Proyecto java Cliente FTP, apartado 4.3.Programación de un cliente FTP de los contenidos) 
para tener un cliente FTP básico en modo texto 
que realice las siguientes funciones:
*******************************************************
 */



package t5practica9_clienteftp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;


/**
 * Clase para recuperar un fichero de un 
 * Servidor FTP. 
 * El fichero se deposita en
 * el primer nivel de la carpeta del proyecto
 *
 * ¡¡¡IMPORTANTE!!!
 *
 * Para probar el ejemplo puede que tengas que 
 * configurar adecuamente o
 * deshabilitar cualquier cortafuegos que 
 * tengas activado
 *
 * ¡¡¡NO OLVIDARSE DE DESHACER LOS CAMBIOS 
 * en caso de desactivar el firewall!!!
 * La seguridad del Sistema podría verse comprometida
 *
 */


public class Main {
//Objeto de la clase FTPClient de Apache , diversos metodos para 
// interactuar y recuperar un archivo de un servidor FTP
 private static FTPClient clienteFTP;
// flujo de salida para la escritura de datos en un fichero
 private static FileOutputStream ficheroObtenido;
// URL del servidor
 private static String servidorURL = "ftp.rediris.es";
// Ruta relativa ( en Servidor FTP ) de la carpeta que contiene
// el fichero que vamos a descargar
 private static String rutaFichero = "debian";
// Nombre del fichero (aunque carece de extension 
// se trata de un fichero de texto que puede abrirse
// con el bloc de notas
 private static String nombreFichero = "README";
// usuario 
 private static String usuario = "anonymous";
// contraseña
 private static String password = "";
// array de carpetas disponibles
 private static String [] nombreCarpeta;
 
 /*
 Recupera el contenido de un fichero desde un Servidor FTP
 y lo deposita en un nuevo fichero en el directorio de nuestro
 proyecto 
 */
 
 public static void main(String[] args) throws IOException {

//  INTENTA 
  try {
   int reply;
//   creacion del objeto cliente FTP
   clienteFTP = new FTPClient();
//   conexion del cliente al servidor FTP
   clienteFTP.connect("ftp.rediris.es");
//   comprobacion de la conexion
   reply = clienteFTP.getReplyCode();
//  Bucle
//   Si ► Conexion es satisfactoria 
   if (FTPReply.isPositiveCompletion(reply)) {
//    Abre una sesion con el usuario anonimo
//    necesita identificarse  
    clienteFTP.login(usuario, password);
//    Lista las carpetas de 1º nivel del servidor FTP
    System.out.println("Carpetas disponibles en el Servidor");
//  Arrays de String para almacenar la lista de nombres 
    nombreCarpeta = clienteFTP.listNames();
//  Bucle
//    Para ► Recorrer toda la carpeta del Servidor FTP
    for (int i = 0; i < nombreCarpeta.length ; i++) {
//    Mostrar por pantalla cada espacio de memoria 
//     que posee un dato
     System.out.println(nombreCarpeta[i]);
    }
//    Nombre con el que se se va a recuperarse el fichero
//    remoto del servidor FTP
    ficheroObtenido = new FileOutputStream(nombreFichero);
//    Mensaje para mostrar el fichero descargado
    System.out.println("\n Descargando el fichero "+nombreFichero 
            +" de la carpeta : "+rutaFichero );
//    Recupera el contenido del fichero en el Servidor y lo escribe
//    en el fichero del directorio del proyecto
    clienteFTP.retrieveFile("/"+rutaFichero+"/"+nombreFichero,ficheroObtenido);
//    Cierra el nuevo fichero
    ficheroObtenido.close();
//    Cierra la conexion con el Servidor
    clienteFTP.disconnect();
//  Mostrar por pantalla 
    System.out.println("");
    System.out.println("Descarga finalizada correctamente");
    System.out.println("");
   }else{
//    desconectado
    clienteFTP.disconnect();
    System.out.println("");
    System.out.println("FTP ha rechazado la conexion establecida");
    System.out.println("");
//  Salida incondicional 
    System.out.println(1);
   }
//   Captura la excepcion
  }catch(SocketException ex){
//   error de Socket
   System.out.println("Error Socket ► "+ex.toString());
  }catch(IOException ex){
   System.out.println("Error I/O ► "+ex.toString());
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
 }
}

















































