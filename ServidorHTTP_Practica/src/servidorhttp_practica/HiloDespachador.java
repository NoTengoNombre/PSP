/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorhttp_practica;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
 private ServerSocket socServidor;

 private static int puerto = 8066;
 
 HiloDespachador hilo;
 
 public HiloDespachador(Socket socketCliente) {
  this.socketCliente = socketCliente;
 }

 public void run() {
  try {
   System.out.println("Atendiendo al cliente");
   ServidorHTTP_Practica.procesaPeticion(socketCliente);
   socketCliente.close();
   System.out.println("cliente atendido");
  } catch (IOException ex) {
   System.out.println("Error I/O : "+ex);
  }
 }

}
