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

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
public class ServidorHTTP_Practica_RepararFallos {

 /**
  * @param args the command line arguments
  * @throws java.io.IOException
  */
// Creo una sola copia para toda la clase
  public static Socket socketCliente;
  public static ServerSocket socServidor;

  
 //  Puerto basico para la conexion
 public static final int puerto = 8066;

 public static void main(String[] args) throws IOException {
  // TODO code application logic here
 
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
   }
  } catch (IOException ex) {
   System.out.println("Error " + ex);
  }
 }

 private static void imprimeDisponible() {
  System.out.println("Date:"+HiloDespachador.getDateValue1());
  System.out.println("El Servidor WEB se está ejecutando y permanece a la "
          + "escucha por el puerto 8066.\nEscribe en la barra de direcciones "
          + "de tu explorador preferido:\n\nhttp://localhost:8066\npara "
          + "solicitar la página de bienvenida\n\nhttp://localhost:8066/"
          + "quijote\n para solicitar una página del Quijote,\n\nhttp://"
          + "localhost:8066/q\n para simular un error");
 }
}
 