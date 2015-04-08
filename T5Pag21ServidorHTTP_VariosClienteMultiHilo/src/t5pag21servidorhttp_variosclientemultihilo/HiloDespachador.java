/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t5pag21servidorhttp_variosclientemultihilo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

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
 //   La peticion que queramos 
   ServidorHTTP.procesaPeticion(socketCliente);
//   Cierra la conexion entrante
   socketCliente.close();
//   Atiendo un cliente
   System.out.println("Atendiendo al cliente que se conecto al Servidor");
//   La peticion que queramos 
//   procesaPeticion(socketCliente);
//   Cierra la conexion entrante
//   socketCliente.close();
   System.out.println("Cliente Atendido y cierra Conexion");
  } catch (IOException ex) {
 Logger.getLogger(HiloDespachador.class.getName()).log(Level.SEVERE, null, ex);
  }
 } // fin run
 } // fin clase HiloDespachador



















