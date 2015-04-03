/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

* *****************************************************************************
 * Servidor HTTP que atiende peticiones de tipo 'GET' 
recibidas por el puerto 
 8066
 *
 * NOTA: para probar este código, comprueba primero de que no tienes ningún otro
 * servicio por el puerto 8066 (por ejemplo, con el comando 'netstat' si estás
 * utilizando Windows)
 * **************************************************************************
*/
package t5pag21servidorhttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author InTylerWeTrust
 */
public class ServidorHTTP {

 /* Procedimiento principal que asigna a cada peticion entrante 
    un socket cliente
*   por donde se enviará la respuesta una vez procesada 
*/
 
 public static void main(String[] args) throws IOException {
  
//  Asociamos al servidor el puerto 8066
  ServerSocket socServidor = new ServerSocket(8066);
//  imprimeDisponible();
  Socket socCliente;
//Ante una peticion entrada , procesa la peticion pro el socket Cliente
//por donde la recibe

//  ♠ MIENTRAS
  while(true){
//   A la espera de peticiones
   socCliente = socServidor.accept();
//   Atiendo un cliente
   System.out.println("Atendiendo al cliente");
//   La peticion que queramos 
   procesaPeticion(socCliente);
//   Cierra la conexion entrante
   socCliente.close();
   System.out.println("Cliente Atendido");
  }
 }
 
// Procesa la peticion recibida
// @Throws IOException
 
// ♦ METODO
 private static void procesaPeticion(Socket socketCliente) throws IOException{
//  Variables Locales
  String peticion;
  String html;

// ♥ Clase  
//  Socket del Cliente
// Flujo de entrada
InputStreamReader inSR = new InputStreamReader(socketCliente.getInputStream());

// ♥ Clase  
// Espacio en memoria para la entrada de peticiones
// lo envuelve en el BufferedReader el inSR
BufferedReader bufLeer = new BufferedReader(inSR);

// ♥ Clase  
// Objeto de java.io que entre otras caracteristicas 
// permite escribir 
// "linea a linea" en un flujo de salida
//PrintWriter printWrite = new PrintWriter(socketCliente.getOutputStream(),true);
PrintWriter printWrite = new PrintWriter(socketCliente.getOutputStream(),true);

//► Le estoy añadiendo habilidades al objeto ♣ peticion

// Definicion flujo de entrada
// LEER UNA LINEA Y SE ALMACENA LA PETICION
//GET - GET/LO QUE SEA DEL PROTOCOLO 1  
// EL GET IMPORTA POCO NECESITA TENERLO
  peticion = bufLeer.readLine();
 
// QUITAMOS LOS ESPACIOS EN BLANCO Y LO COMPRIMIMOS 
// TENEMOS GET /HTTP/1.1 -> PETICION DEL CLIENTE
// MEJOR QUITAR LOS ESPACIOS EN BLANCO          
  
//  Para compactar la peticion y facilitar asi su analisis
//  suprimimos todos los espacios en blanco que contenga
  peticion = peticion.replaceAll(" ", "");
  
//  GET/moodelHTTP/1.1
//  BARRA SECA LE DAMOS LA DIRECCION

//  ☼ BUCLE
//  Si realmente se trata de una peticion 'GET' ( que es la única que vamos a 
//  implementar en nuestro Servidor )
//  SI LA PETICION EMPIEZA NO GET DARA ERROR 
//  LA SUBCADENA DEL CARACTER 3
  if (peticion.startsWith("GET")) {
// ☻ Extrae la subcadena entre 'GET' y 'HTTP/1.1'
//                               Comienzo      Final
   peticion = peticion.substring(    3   ,peticion.lastIndexOf("HTTP"));
  
// ☼ BUCLE
// Si corresponde a la pagina de inicio
//   VAMOS A SERVIR LA PAGINA PRINCIPAL
   if (peticion.length()== 0 || peticion.equals("/")) {
//  Sirve la pagina
//    TODA LA PAGINA SE GUARDA EN EL STRING HTML
    html = Paginas.html_index;
//    LE VAMOS A MANDAR EL 200 OK Y DEMAS
    printWrite.println(Mensajes.lineaInicial_OK);
//    PASARLE LA 1º CABECERA Y LA LONGITUD
    printWrite.println(Paginas.primeraCabecera);
    printWrite.println("Content-Length: "+html.length()+1);
    printWrite.println("\n");
    printWrite.println(html);
   }
//  SINO SI
//   Corresponde a la pagina Quijote
   else if(peticion.equals("/quijote")){
// Sirve la Pagina
    html = Paginas.html_quijote;
    printWrite.println(Mensajes.lineaInicial_OK);
    printWrite.println(Paginas.primeraCabecera);
//    EL +1 ES PORQUE NECESITA UNO MAS EL PROFE NO SABE PORQUE
    printWrite.println("Content-Length: "+html.length()+1);
    printWrite.println("\n");
//    CUERPO DE LA PAGINA WEB SE LO PASAMOS
    printWrite.println(html);
   } // En cualquier otro caso
// ☼ BUCLE
//  En cualquier otro caso
// SINO 
   else{
//    Sirve la pagina
    html = Paginas.html_noEncontrado;
    printWrite.println(Mensajes.lineaInicial_NotFound);
    printWrite.println(Paginas.primeraCabecera);
    printWrite.println("Content-Length"+html.length()+1);
    printWrite.println("\n");
    printWrite.println(html);
   } // en cualquier otro caso
  }
 }
 
  private static void imprimeDisponible(){
   System.out.println(
           "El Servidor WEB se esta ejecutando y permanece a la escucha"
                   +"por el puerto 8066. "
                   + "\nEscribe en la barra de direcciones "
                   + "del explorador -> "
                   + "\n\nhttp://localhost:8066\n"
                   + "Para solicitar la pagina de bienvenida"
                   + "\n\nhttp://localhost:8066/"
                   + "quijote\n para solicitar una pagina del Quijote,\n\nhttp://"
                   + "localhost:8066/q\n para simular un error");
  }
 }













