/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *******************************************************

 Ejercicio : Servidor_Practica_Muestra_Etiquetas
 Crea un servidor para recibir peticiones del cliente
 mediante <code>hilo</code> y mide el tiempo de procesamiento 
 */

/**
 * *****************************************************************************
 * Servidor HTTP que atiende peticiones de tipo 'GET' recibidas por el puerto
 * 8066
 *
 * NOTA: para probar este código, comprueba primero de que no tienes ningún otro
 * servicio por el puerto 8066 (por ejemplo, con el comando 'netstat' si estás
 * utilizando Windows)
 *
 */
package servidorpractica;

// Paquetes IO ( entrada y salida de flujo de datos )
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
// Paquetes Language ( Sistema en general )
import static java.lang.System.out;
// Paquetes Net ( Conexión ) 
import java.net.ServerSocket;
import java.net.Socket;
// Paquetes Text ( Formato de texto y conversion de fechas o valores )
import java.text.DateFormat;
import java.text.SimpleDateFormat;
// Paquetes Time ( Ajuste de tiempo / horas )
import java.time.ZoneId;
// Paquetes Util ( Opciones y Herramientas en general )
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author Raul Vela Salas
 */
public class ServidorPractica {

 /**
  * procedimiento principal que asigna a cada petición entrante un socket
  * cliente, por donde se enviará la respuesta una vez procesada
  *
  * @param args the command line arguments
  */
// Atributos 
// Objetos de Clases
 public static Socket socketCliente;
 public static ServerSocket socServidor;
 public static ServidorPractica tiempo;
 public static ServidorPractica fecha = new ServidorPractica();

// Atributos 
// Constante define puerto para la conexion
 public static final int puerto = 8066;

 /*
  * **************************************************************************
  * procedimiento principal que asigna a cada petición entrante un socket
  * cliente, por donde se enviará la respuesta una vez procesada
  *
  */
 public static void main(String[] args) throws IOException {
  // TODO code application logic here

//  Instancia de clase 
  tiempo = new ServidorPractica();

//  Instancia de la clase Hilo 
  HiloDespachador hilo;

// Procedimiento de clase : Muestra un mensaje 
  imprimeDisponible();

// INTENTA   
  try {
// Objeto de la clase ServerSocket crear una conexion por el puerto 8066
   socServidor = new ServerSocket(puerto);
// Objeto para el metodo getDateValue()
   //ante una petición entrante, procesa la petición por el socket cliente
   //por donde la recibe
//  Bucle : Mientras
   while (true) {
//    Objeto cliente acepta la petición del ServerSocket con el puerto asignado
    socketCliente = socServidor.accept();
//   crea un nuevo hilo (Despachador) para despachar la peticion por el SocketCliente que le asigno
    hilo = new HiloDespachador(socketCliente);
//   comienza la ejecución del hilo
    hilo.start();
   }
   // Captura Excepcion
  } catch (IOException e) {
   // Muestra la excepcion
   System.err.println("Error I/O : " + e);
  }
 }

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
    printWriter.println("Date : " + fecha.getDateValue1());
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
    printWriter.println("Date : " + fecha.getDateValue1());
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
    printWriter.println("Date : " + fecha.getDateValue1());
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

// ♦ Metodo
 private static void imprimeDisponible() {
  out.println("Date : " + fecha.getDateValue1());
  System.out.println("El Servidor WEB se está ejecutando y permanece a la "
          + "escucha por el puerto 8066.\nEscribe en la barra de direcciones "
          + "de tu explorador preferido:\n\nhttp://localhost:8066\npara "
          + "solicitar la página de bienvenida\n\nhttp://localhost:8066/"
          + "quijote\n para solicitar una página del Quijote,\n\nhttp://"
          + "localhost:8066/q\n para simular un error");
 }

// Metodo muestra hora pero necesita ajustarse
 String getDateValue() {
  DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
  df.setTimeZone(TimeZone.getTimeZone("GMT"));
  return df.format(new Date());
 }

// Metodo muestra hora actual
 String getDateValue1() {
  DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
  df.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
  return df.format(new Date());
 }

}
