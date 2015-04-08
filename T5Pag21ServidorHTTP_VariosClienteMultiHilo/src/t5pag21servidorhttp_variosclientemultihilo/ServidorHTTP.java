/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 Servidor HTTP que atiende peticiones de tipo 'GET'
 recibidas por el puerto 8066

NOTA : Para probar este codigo , comprueba 1º que no tienes otro 
servicio por el puerto 8066 ( por ejemplo comando 'netstat' si estas 
utilizando Windows )

 */
package t5pag21servidorhttp_variosclientemultihilo;

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

 /*
  Procedimiento principal que asigna a cada peticion
 entrante un socket 
  cliente , por donde se enviará la respuesta una ves procesada
 */
 
// Atributos
 private static int puerto = 8066; 
 
 
 public static void main(String[] args) throws IOException {
  
//  Asociamos al servidor el puerto 8066
  ServerSocket socServidor;
//  Clase Thread
  HiloDespachador hilo;
  
  socServidor = new ServerSocket(puerto);
// ♣ METODO
//  imprimeDisponible();
//  ♥ CLASE
  Socket socketCliente;
  
  try {
   socServidor = new ServerSocket(puerto);
//  Ante una peticion entrante , procesa la peticion 
//  por el Socket Cliente
//  por donde la recibe
  while (true) {   
//   A la espera de Peticiones
//   Acepta una peticion y le asigna un Socket Cliente para la respuesta
   socketCliente = socServidor.accept();
//   CREA UN NUEVO HILO PARA DESPACHAR LA PETICION POR EL SOCKETCLIENTE QUE 
//   LE ASIGNO
   hilo = new HiloDespachador(socketCliente);
//   Comienza el hilo
   hilo.start();

   }
  }catch(IOException ex){
//   System.out.println("Error I/O → "+ex);
  }
 }
 
/*
 Procesa la peticion recibida
 */

// ♣ METODOS
 public static void procesaPeticion(Socket socketCliente) throws IOException{
//  Variable Locales
  String peticion;
  String html;
//  Flujo de entrada 
//  Socket del Cliente
  InputStreamReader inSR = new InputStreamReader(socketCliente.getInputStream());
//  Espacio en memoria para la entrada de peticiones
//  lo envuelve en el BufferedReader el inSR
  BufferedReader bufLeer = new BufferedReader(inSR);
//  Objeto de java.io que entre otras caracteristicas 
//  permite escribir
//  'Linea a linea' en el flujo de salida
  PrintWriter printWriter = new PrintWriter(socketCliente.getOutputStream(),true);
//  Mensaje peticion Cliente
//  Definido el flujo de entrada
//  LEER UNA LINEA Y SE ALMACENA LA PETICION GET -> GET/LO QUE SEA 
//  DEL PROTOCOLO 1 
//  EL GET IMPORTA POCO NECESITA TENERLO
  peticion = bufLeer.readLine();
  
// QUITAMOS LOS ESPACIOS EN BLANCO Y 
//  LO COMPRIMIMOS 
//   TENEMOS GET /HTTP/1.1 -> PETICION DEL CLIENTE
//     MEJOR QUITAR LOS ESPACIOS EN BLANCO  
  
//  Para compactar la peticion y facilitar asi su analisis
//  suprimimos todos los espacios en blanco que contenga
  peticion = peticion.replaceAll(" ","");
//  GET/moodelHTTP/1.1
//  BARRA SECA LE DAMOS LA DIRECCION
//  
//  Si realmente se trata de una peticion 'GET'
// (que es la única que vamos a implementar en nuestro Servidor)
//  SI LA PETICION EMPIEZA NO GET DARA ERROR
//  LA SUBCADENA DEL CARACTER 3
  if (peticion.startsWith("GET")) {
//   Extrae la subcadena entre 'GET' y 'HTTP/1.1'
   peticion = peticion.substring( 3 , peticion.lastIndexOf("HTTP"));

//  SI corresponde a la pagina de inicio
//   VAMOS A SERVIR LA PAGINA PRINCIPAL 
   if (peticion.length() == 0 || peticion.equals("/")) {
//    Sirve la pagina
//    TODA LA PAGINA SE GUARDA EN EL STRING HTML
    html = Paginas.html_index;
//    LE VAMOS A MANDAR EL 200 OK Y DEMAS
    printWriter.println(Mensajes.lineaInicial_OK);
//    PASARLE LA 1º CABECERA Y LA LONGITUD
    printWriter.println(Paginas.primeraCabecera);
    printWriter.println("Content-Length: "+html.length() + 1);
    printWriter.println("\n");
    printWriter.println(html);

// Si Corresponde a la pagina del Quijote
   }else if(peticion.equals("/quijote")){
//   Sirve la pagina 
    html = Paginas.html_quijote;
    printWriter.println(Mensajes.lineaInicial_OK);
    printWriter.println(Paginas.primeraCabecera);
// EL +1 ES PORQUE NECESITA UNO MAS EL PROFE NO SABE PORQUE
    printWriter.println("Content-Length"+html.length()+1 );
    printWriter.println("\n");
    printWriter.println(html);
   } // en cualquier otro caso
   else {
    //sirve la pagina
    html = Paginas.html_noEncontrado;
    printWriter.println(Mensajes.lineaInicial_NotFound);
    printWriter.println(Paginas.primeraCabecera);
    printWriter.println("Content-Length: "+html.length());
    printWriter.println("\n");
    printWriter.println(html);
   }
  }
 }

/*
 Muestra un mensaje en la Salida que confirma el arranque
 y da algunas indicaciones posteriores
 */
 private static void imprimeDisponible(){
  System.out.println("El Servidor WEB se esta ejecutando y permanece a la "
          + "escucha por el puerto 8066.\n"
          + "Escribe en la barra de direcciones : "
          + "de tu explorador preferido:\n\n"
          + "http://localhost:8066 \n"
          + "Para solicitar la pagina de bienvenida \n"
        + "\n http://localhost:8066/quijote\n para solicitar la pagina del Quijote\n"
          + "\n http://localhost:8066/q\n para simular un error");
 }
}

























































