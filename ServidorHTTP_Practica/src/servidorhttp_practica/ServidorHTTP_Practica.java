/*
 La práctica se podrá realizar en grupos de dos alumnos

 EJERCICIO 1.
 Modifica el ejemplo del servidor HTTP (Proyecto java ServerHTTP, apartado 5.1 de los contenidos) 
 para que incluya la cabecera  Date.

 EJERCICIO 2.
 Modifica el ejemplo del servidor HTTP (Proyecto java ServerHTTP, apartado 5.1 de los contenidos) 
 para que implemente multihilo, y pueda gestionar la concurrencia de manera eficiente.

 EJERCICIO 3. Prueba que se puede acceder a tu servidor web 
 desde una máquina distinta a donde tienes ubicado el servidor 
 (puedes usar una máquina virtual) mediante un cliente HTTP (un navegador), 
 por ejemplo si la dir. IP de tu servidor web es 192.168.208.55 y el puerto 8066,  
 escribiendo en la URL:  192.168.208.55:8066.

 EJERCICIO 4. Averigua el tiempo de transmisión de tu servidor HTTP. 
 Hazlo desde una máquina distinta a donde tienes ubicado tu ordenador.

 */
package servidorhttp_practica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
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
 *
 * /**
 * *****************************************************************************
 * Servidor HTTP que atiende peticiones de tipo 'GET' recibidas por el puerto
 * 8066
 *
 * NOTA: para probar este código, comprueba primero de que no tienes ningún otro
 * servicio por el puerto 8066 (por ejemplo, con el comando 'netstat' si estás
 * utilizando Windows)
 */
public class ServidorHTTP_Practica {

 /**
  * @param args the command line arguments
  * @throws java.io.IOException
  */
// Creo una sola copia para toda la clase
  public static Socket socketCliente;
  public static ServerSocket socServidor;
  public static ServidorHTTP_Practica tiempo; 

  
 //  Puerto basico para la conexion
 public static final int puerto = 8066;

 public static void main(String[] args) throws IOException {
  // TODO code application logic here
 
//  Objeto de la clase ServidorHTTP_Practica para usar el metodo getDateValue()
  tiempo = new ServidorHTTP_Practica();
  
//  Clase con el hilo 
  HiloDespachador hilo;

//  Mensaje con las ordenes 
  imprimeDisponible();

// INTENTA
  try {
   socServidor = new ServerSocket(puerto);
   while (true) {
//  acepta una peticion y le asigna un socket cliente para la respuesta
    socketCliente = socServidor.accept();
    hilo = new HiloDespachador(socketCliente);
    hilo.start();
//  System.out.println("Atendiendo al cliente");
//  procesaPeticion(socketCliente);
//  socketCliente.close();
//  System.out.println("cliente atendido");
   }
  } catch (IOException ex) {
   System.out.println("Error " + ex);
  }
 }

 public static void procesaPeticion(Socket socketCliente) throws IOException {
  
  String peticion;
  String html;


  InputStreamReader inSR = new InputStreamReader(socketCliente.getInputStream());
  BufferedReader bufferLeer = new BufferedReader(inSR);
  PrintWriter printWriter = new PrintWriter(socketCliente.getOutputStream(), true);
  peticion = bufferLeer.readLine();
  peticion = peticion.replace(" ","");

  if (peticion.startsWith("GET")) {
   peticion = peticion.substring(3, peticion.lastIndexOf("HTTP"));
   if (peticion.length() == 0 || peticion.equals("/")) {
    html = Paginas.html_index;
    printWriter.println(Mensajes.lineaInicial_OK);
    printWriter.println(Paginas.primeraCabecera);
    printWriter.println("Content-Length: " + html.length());
    printWriter.println(tiempo.getDateValue());
    printWriter.println("\n");
    printWriter.println(html);

   } else if (peticion.equals("/quijote")) {
    html = Paginas.html_quijote;
    printWriter.println(Mensajes.lineaInicial_OK);
    printWriter.println(Paginas.primeraCabecera);
    printWriter.println("Content-Length: " + html.length() + 1);
    printWriter.println(tiempo.getDateValue());
    printWriter.println("\n");
    printWriter.println(html);
  
   } else {
    html = Paginas.html_noEncontrado;
    printWriter.println(Mensajes.lineaInicial_NotFound);
    printWriter.println(Paginas.primeraCabecera);
    printWriter.println("Content-Length: " + html.length() + 1);
    printWriter.println(tiempo.getDateValue());
    printWriter.println("\n");
    printWriter.println(html);
   }
  }
 }

 private static void imprimeDisponible() {
  System.out.println(tiempo.getDateValue1());
  System.out.println("El Servidor WEB se está ejecutando y permanece a la "
          + "escucha por el puerto 8066.\nEscribe en la barra de direcciones "
          + "de tu explorador preferido:\n\nhttp://localhost:8066\npara "
          + "solicitar la página de bienvenida\n\nhttp://localhost:8066/"
          + "quijote\n para solicitar una página del Quijote,\n\nhttp://"
          + "localhost:8066/q\n para simular un error");
 }

// Metodo con la hora desfasada
 String getDateValue() {
 DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
  df.setTimeZone(TimeZone.getTimeZone("GMT"));
  return df.format(new Date());
 }
 
// Metodo con la hora correcta y actualizada
 String getDateValue1() {
 DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
  df.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
  return df.format(new Date());
 }
}
