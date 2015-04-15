/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
**********************************************
Se crea una clase que genera un hilo aparte 
( Hilo Despachador ) 
para atender las peticiones del cliente
 */
package servidorpractica;

// Importa Paquetes necesarias
// Paquetes IO 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
// Paquetes Socket
import java.net.Socket;
import static servidorpractica.ServidorPractica.fecha;

/**
 *
 * @author InTylerWeTrust
 */

// Creamos una clase que hereda de Thread para procesar/gestionar los hilos 
public class HiloDespachador extends Thread {
// Objeto de la clase Socket 
 private Socket socketCliente;
// Constructor de la clase que mediante this 
// usa el atributo Socket declarado
 public HiloDespachador(Socket socketCliente) {
  this.socketCliente = socketCliente;
 }
// Metodo sobreescribe el metodo de la clase Thread
// para utilizar hilo
 @Override
 public void run() {
//  Intenta
  try {
// Mostrar mensaje 
   System.out.println("");
   System.out.println("Atendiendo al cliente");
//  Se llama al metodo de la clase ServidorPractica
//  mediante el metodo procesaPeticion el cual recibe como parametro
//  el socketCliente con los valores asignados
    procesaPeticion(socketCliente);
// Se cierra el flujo de entrada del socketCliente 
//   y la conexion
  socketCliente.close();
// Mostrar mensaje 
   System.out.println("");
   System.out.println("cliente atendido");
//   Captura la excepcion
  } catch (IOException e) {
// Mostrar mensaje de excepcion
   System.out.println("Error I/O : " + e);
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
//BARRA SECA LE DAMOS LA DIRECCION
  //
  //si realmente se trata de una petición 'GET' (que es la única que vamos a
  //implementar en nuestro Servidor)
  //SI LA PETICION NO EMPIEZA GET DARA ERROR 
  //LA SUBCADENA DEL CARACTER 3 
//  Peticion del Objeto
  if (peticion.startsWith("GET")) {
   //extrae la subcadena entre 'GET' y 'HTTP/1.1'
   peticion = peticion.substring(3,peticion.lastIndexOf("HTTP"));
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
    printWriter.println("Date: " + fecha.getDateValue1());
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
    printWriter.println("Date: " + fecha.getDateValue1());
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
    printWriter.println("Date: " + fecha.getDateValue1());
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
  System.out.print("tiempo inicio "+time_start);
  System.out.println(" - tiempo final "+time_end);
  System.out.println("tiempo total = "+tiempo_total +" ms ");
 }
}
