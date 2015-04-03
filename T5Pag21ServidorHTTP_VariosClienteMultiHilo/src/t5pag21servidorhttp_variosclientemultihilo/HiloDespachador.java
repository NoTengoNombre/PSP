/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t5pag21servidorhttp_variosclientemultihilo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author InTylerWeTrust
 */
// ESTA CLASE EXTIENDE DE LA CLASE Thread 
public class HiloDespachador extends Thread {
 
 private Socket socketCliente;
 private ServerSocket socServidor;
 private static int puerto = 8066;
 
 HiloDespachador hilo;
// Constructor almacena socketCliente recibe una variable
// local utilizada para luego utilizarla metodo run() para
// tramitar respuesta
 public HiloDespachador(Socket socketCliente) {
  this.socketCliente =  socketCliente;
 }
 
// METODO RUN 
 public void run(){
  try {
//   tramita la peticion por el socketCliente
  socServidor = new ServerSocket(puerto);
  while(true){
//   acepta una peticion y le asigna un socket cliente para la respuesta
   socketCliente = socServidor.accept();
//   crea un nuevo hilo para despachar la peticion por el 
//   socketCliente que le asigno 
   hilo = new HiloDespachador(socketCliente);
   hilo.start();
  }
  }catch(IOException ex){
  }
 } // fin run
 } // fin clase HiloDespachador



















