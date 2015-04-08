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
import java.io.IOException;
// Paquetes Socket
import java.net.Socket;

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
   ServidorPractica.procesaPeticion(socketCliente);
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

// Metodo para mostrar los tiempos de procesamiento
// !!! Como no estoy seguro si también es necesario ejecutarlo
// en esta clase , lo tengo preparado por si necesito usarlo 
// mas adelante 
// con una rapida modificación los transformo es complementos 
// para el metodo principal del metodo run()
 
 private static void tiempo_procesamiento() {
  long time_start, time_end;
//  tiempo inicial
  int t1 = (int) System.currentTimeMillis();

//  Acciones a realizar para procesar
//  la peticion del cliente
//  tiempo final
  int t2 = (int) System.currentTimeMillis();
  System.out.print("\t Tiempo = ");
  System.out.print((t2 - t1));
  System.out.print(" ms");
 }
}
