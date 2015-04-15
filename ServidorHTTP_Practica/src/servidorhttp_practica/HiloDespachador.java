/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

 Hay que incluir la cláusula finally
 y dentro de esta el close que necesitará
 un try.....catch.
 */
package servidorhttp_practica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
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
class HiloDespachador extends Thread {

// ¿ Estoy ejecutando un nuevo Socket o esta
// usando el Socket de la otra clase ?
 private Socket socketCliente;
// ¿ Estoy ejecutando un nuevo ServerSocket o esta
// usando el ServerSocket de la otra clase ?

 public HiloDespachador(Socket socketCliente) {
  this.socketCliente = socketCliente;
 }

// Metodo run
 public void run() {
  try {
   System.out.println("");
   System.out.println("Atendiendo al cliente");

// ♦ Metodo
   procesaPeticion(socketCliente);

  } catch (IOException ex) {
   System.out.println("Error I/O : " + ex);
    System.out.println("");
  } finally {
    System.out.println("cliente atendido");
   try {
    socketCliente.close();
   } catch (Exception e) {
    System.out.println("Error "+e);
    System.out.println("cliente no cerrado");
   }
  }
 }

 // Este Metodo tiene que ir en la clase del HILO DESPACHADOR
// ♦ Metodo
 public static void procesaPeticion(Socket socketCliente) throws IOException {
  //variables locales
  String peticion;
  String html;

  //  Tiempo Inicial
  long time_start = System.currentTimeMillis();

  //Flujo de entrada
  // socket del cliente
  InputStreamReader inSR = new InputStreamReader(socketCliente.getInputStream());
  //espacio en memoria para la entrada de peticiones
  //lo envuelve en el BufferedReader el inSR
  BufferedReader bufLeer = new BufferedReader(inSR);
  //objeto de java.io que entre otras características, permite escribir 
  //'línea a línea' en un flujo de salida
  PrintWriter printWriter = new PrintWriter(socketCliente.getOutputStream(), true);

  //mensaje petición cliente
  // definido el flujo de entrada
  //LEER UNA LINEA Y SE ALMACENA LA PETICION GET - GET/LO QUE SEA DEL PROTOCOLO 1 
  //EL GET IMPORTA POCO NECESITA TENERLO
  peticion = bufLeer.readLine();

  //QUITAMOS LOS EPSACIOS EN BLANCO Y LO COMPRIMIMOS
  //TENEMOS GET /HTTP/1.1 -> PETICION DEL CIENTE
  //MEJOR QUITAR LOS ESPACIOS EN BLANCO 
  //para compactar la petición y facilitar así su análisis, suprimimos todos 
  //los espacios en blanco que contenga
  peticion = peticion.replace(" ", "");

  // GET/moodelHTTP/1.1
  //si realmente se trata de una petición 'GET' (que es la única que vamos a
  //implementar en nuestro Servidor)
  //SI LA PETICION NO EMPIEZA GET DARA ERROR 
  //LA SUBCADENA DEL CARACTER 3 
//  Peticion del Objeto
  if (peticion.startsWith("GET")) {
   //extrae la subcadena entre 'GET' y 'HTTP/1.1'
   peticion = peticion.substring(3, peticion.lastIndexOf("HTTP"));
// ► ESTE SALTO DE LINEA PROVOCA QUE SE VEA LA ETIQUETAS HTML
//   printWriter.println();
//   ********************
   //si corresponde a la página de inicio
   //VAMOS A SERVIR LA PAGINA PRINCIPAL
   if (peticion.length() == 0 || peticion.equals("/")) {
    // ► Sirve la página
    //TODA LA PAGINA SE GUARDA EN EL STRING HTML
    html = Paginas.html_index;
//    Linea Inicial
    //LE VAMOS A MANDAR EL 200 OK Y DEMAS Contenido
    printWriter.println(Mensajes.lineaInicial_OK);
//    Linea Cabecera Content-Type
    //PASARLE LA 1º CABECERA Y LA LONGITUD
    printWriter.println(Paginas.primeraCabecera);
//    Indica caracteres para un Content-Type
    printWriter.println("Content-Length: " + html.length());
//    Muestra la cabecera Date 
    printWriter.println("Date: " + getDateValue1());
//    Linea Separacion
    printWriter.println("\n");
//    Cuerpo Mensaje
    printWriter.println(html);
//si corresponde a la página del Quijote
   } else if (peticion.equals("/quijote")) {
    //sirve la página
    html = Paginas.html_quijote;
//    Linea Inicial
    printWriter.println(Mensajes.lineaInicial_OK);
//    Linea Cabecera Content-Type
    printWriter.println(Paginas.primeraCabecera);
//    Indica caracteres para un Content-Type
    // EL +1 ES PORQUE NECESITA que empiece a leer desde el caracter 1º 
    printWriter.println("Content-Length: " + html.length() + 1);
    //    Muestra la cabecera Date 
    printWriter.println("Date: " + getDateValue1());
//    Linea Separacion
    printWriter.println();
    printWriter.println("\n");
//    Cuerpo Mensaje
    printWriter.println(html);
//    printWriter.println("Tiempo "+Cabecera_Tiempo.getDateValue());
   } else {
//    sirve la pagina
    html = Paginas.html_noEncontrado;
//    Linea Inicial
    printWriter.println(Mensajes.lineaInicial_NotFound);
//    Linea Cabecera Content-Type
    printWriter.println(Paginas.primeraCabecera);
//    Indica caracteres para un Content-Type
    printWriter.println("Content-Length: " + html.length() + 1);
    //    Muestra la cabecera Date 
    printWriter.println("Date: " + getDateValue1());
    printWriter.println();
//    Linea Separacion
    printWriter.println("\n");
//    Cuerpo Mensaje  
    printWriter.println(html);
//    printWriter.println("Tiempo "+Cabecera_Tiempo.getDateValue());
   }
  }

// Tiempo Final
  long time_end = System.currentTimeMillis();
  long tiempo_total = time_end - time_start;
  System.out.println("Tiempo procesamiento -> ");
  System.out.print("tiempo inicio " + time_start);
  System.out.println(" - tiempo final " + time_end);
  System.out.println("tiempo total = " + tiempo_total + " ms ");
 }

 // Metodo con la hora correcta y actualizada
 public static String getDateValue1() {
  DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
  df.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
  return df.format(new Date());
 }
}
