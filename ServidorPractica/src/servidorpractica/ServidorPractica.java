/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorpractica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author InTylerWeTrust
 */
public class ServidorPractica {

 /**
  * @param args the command line arguments
  */
 
 public static void main(String[] args) throws IOException {
  // TODO code application logic here
 ServerSocket socServidor = new ServerSocket(8066);
 imprimeDisponible();
 Socket socCliente;
 
// Objeto para el metodo getDateValue()

 
 while(true){
  socCliente = socServidor.accept();
  System.out.println("");
  System.out.println("Atendiendo al cliente");
  procesaPeticion(socCliente);
  socCliente.close();
  System.out.println("");
  System.out.println("cliente atendido");
 }
 }
 
 private static void procesaPeticion(Socket socketCliente)throws IOException
 {
  String peticion;
  String html;
  
  
  ServidorPractica fecha = new ServidorPractica();
 
  InputStreamReader inSR = new InputStreamReader(socketCliente.getInputStream());
  BufferedReader bufLeer = new BufferedReader(inSR);
  PrintWriter printWriter = new PrintWriter(socketCliente.getOutputStream(),true);
  peticion = bufLeer.readLine();
  peticion = peticion.replace(" ","");
  
//  Peticion del Objeto
  if (peticion.startsWith("GET")) {
   peticion = peticion.substring(3,peticion.lastIndexOf("HTTP"));
   printWriter.println();

   if (peticion.length() == 0 || peticion.equals("/")) {
    html = Paginas.html_index;
//    Linea Inicial
    printWriter.println(Mensajes.lineaInicial_OK);
//    Linea Cabecera Content-Type
    printWriter.println(Paginas.primeraCabecera);
//    Indica caracteres para un Content-Type
    printWriter.println("Content-Length: "+html.length());
    printWriter.println("Date : "+fecha.getDateValue());
//    Linea Separacion
    printWriter.println("\n");
//    Cuerpo Mensaje
    printWriter.println(html);
   }
   else if (peticion.equals("/quijote")) {
    html = Paginas.html_quijote;
//    Linea Inicial
    printWriter.println(Mensajes.lineaInicial_OK);
//    Linea Cabecera Content-Type
    printWriter.println(Paginas.primeraCabecera);
//    Indica caracteres para un Content-Type
    printWriter.println("Content-Length: "+html.length()+1);
    printWriter.println("Date : "+fecha.getDateValue());
    printWriter.println();
//    Linea Separacion
    printWriter.println("\n");
//    Cuerpo Mensaje
    printWriter.println(html);
//    printWriter.println("Tiempo "+Cabecera_Tiempo.getDateValue());
   }
   else{
    html = Paginas.html_noEncontrado;
//    Linea Inicial
    printWriter.println(Mensajes.lineaInicial_NotFound);
//    Linea Cabecera Content-Type
    printWriter.println(Paginas.primeraCabecera);
//    Indica caracteres para un Content-Type
    printWriter.println("Content-Length: "+html.length()+1);
    printWriter.println("Date : "+fecha.getDateValue());
    printWriter.println();
//    Linea Separacion
    printWriter.println("\n");
//    Cuerpo Mensaje  
    printWriter.println(html);
//    printWriter.println("Tiempo "+Cabecera_Tiempo.getDateValue());
   }
  }
 }
 
  private static void imprimeDisponible() {
    System.out.println("El Servidor WEB se está ejecutando y permanece a la "
            + "escucha por el puerto 8066.\nEscribe en la barra de direcciones "
            + "de tu explorador preferido:\n\nhttp://localhost:8066\npara "
            + "solicitar la página de bienvenida\n\nhttp://localhost:8066/"
            + "quijote\n para solicitar una página del Quijote,\n\nhttp://"
            + "localhost:8066/q\n para simular un error");
 }

  String getDateValue(){
   DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
   df.setTimeZone(TimeZone.getTimeZone("GMT"));
   return df.format(new Date());
  }
}
  
  


 
  