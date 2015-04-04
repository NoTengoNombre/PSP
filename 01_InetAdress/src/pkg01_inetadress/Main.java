/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg01_inetadress;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author InTylerWeTrust
 */
public class Main {

 /**
  * @param args the command line arguments
  */
 public static void main(String[] args) throws IOException {
  // TODO code application logic here
//  Intenta
  try {
//   Red Local 
   System.out.println();
   System.out.println("------ Red Local -------");
//   Obtiene el objeto InetAddress de localhost
   InetAddress objetoLocahost = InetAddress.getByName("localhost");
 
   System.out.println("-Ip de localhost");
// Muestra por pantalla la dirección IP localhost   
   System.out.println(objetoLocahost.getHostAddress());

//   Obtiene dirección de mi Equipo y  puedo utilizarla
//   getLocalHost() o getByName("nombreDeMiEquipo")
//   Class       Objeto       Class        Metodo
   InetAddress miEquipoLan = InetAddress.getLocalHost();
   
//   Mostrar el nombre del Equipo
   System.out.println("-Nombre de mi Equipo en la Red Local");
   System.out.println(miEquipoLan.getHostName());
   
//   Mostrar el nombre del Equipo LocalHost  
   System.out.println("-Nombre de mi Equipo Localhost");
   System.out.println(objetoLocahost.getHostName());
//   Mostrar el nombre del Servidor
   System.out.println("-IP de mi Equipo en la Red Local");
   System.out.println(miEquipoLan.getHostAddress());
   System.out.println();

//   En internet 
   System.out.println("--------- INTERNET ------------");
//  Obtener objeto InetAddress de www.iesalandalus.org
InetAddress objetoAlandalus_1 = InetAddress.getByName("www.iesalandalus.org");
// Mostrar el objeto InetAddress de iesalandalus 
System.out.println("\n IP de www.iesalandalus.org : ");
   System.out.println(objetoAlandalus_1.getHostAddress());

//  Obtener objeto InetAddress de ftp.iesalandalus.org
 InetAddress objetoAlandalus_2 = InetAddress.getByName("ftp.iesalandalus.org");
//  Mostrar el objeto InetAddress de ftp de iesalandalus
 System.out.println("\n IP de ftp.iesalandalus.org : ");
   System.out.println(objetoAlandalus_2.getHostAddress());
   
//  Encapsula google.com con Arrays de objetos tipo InetAddress
   InetAddress [] matrizAddress = InetAddress.getAllByName("google.com");
   
//  Obtiene y muestra todas las IP asociadas a ese Host
   System.out.println("Imprime todas las IP disponibles para google.com");
//   Recorrer Bucle para mostrar
   for (int i = 0; i < matrizAddress.length ; i++) {
    System.out.println("IP asociadas a google : "+matrizAddress[i].getHostAddress());
    System.out.println();
//    Tiempo alcanzado en milisegundos
    System.out.println("El tiempo alcanzado es : "+objetoAlandalus_2.isReachable(2000));
    
   }
//  Captura las excepciones
  } catch (UnknownHostException e) {
//   Muestra la excepcion 
   System.out.println(e);
//   Mensaje en caso de error 
   System.out.println("Parece que no estas conectado o que el servidor DNS no ha podido trasmitir tu solicitud");
  }
 }
}






















