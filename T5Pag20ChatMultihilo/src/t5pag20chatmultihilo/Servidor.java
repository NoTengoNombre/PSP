/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t5pag20chatmultihilo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author InTylerWeTrust
 */
public class Servidor extends JFrame {
 // Atributos de clase 

 private JTextField campoIntroducir;
 private JTextArea areaPantalla;
 private ObjectOutputStream salida;
 private ObjectInputStream entrada;
 private ServerSocket servidor;
 private Socket conexion;
 private int contador = 1;

// configurar GUI
// HeadlessException Lanza una excepcion cuando
// codigo depende del teclado , pantalla o raton
// no soporta ambos formatos
 public Servidor() throws HeadlessException {
//  sobreescribiendo la clase Servidor
  super("Servidor");
// Clase que contiene objetos AWT y el metodo que los obtiene
  Container contenedor = getContentPane();
// Crear campoIntroducir y registrar componente de escucha
  campoIntroducir = new JTextField();
  campoIntroducir.setEditable(false);
//  Añade al objeto una accion para escuchar dentro fija
//  una clase 
  campoIntroducir.addActionListener(new ActionListener() {
// ♦ METODO
//   enviar mensaje al cliente
   public void actionPerformed(ActionEvent evento) {
    enviarDatos(evento.getActionCommand());
    campoIntroducir.setText("");
   }
  }
  );
// Objeto contenedor
  contenedor.add(campoIntroducir, BorderLayout.NORTH);
//  crear AreaPantalla
//  Objeto JTextArea
  areaPantalla = new JTextArea();
  contenedor.add(new JScrollPane(areaPantalla), BorderLayout.CENTER);
  setSize(300,150);
  setVisible(true);
 } // fin del constructor del Servidor 

// ♦ METODO
// Configurar y ejecutar el servior
 public void ejecutarServidor() throws IOException {
//  Configurar Servidor para que reciba conexiones:
//   procesar las conexiones
  try {
//   Paso 1 : crear un objeto ServerSocket
//                            port  backlog
   servidor = new ServerSocket(12345, 100);
// ♠ MIENTRAS
   while (true) {
//  ↕ INTENTA
    try {
     esperarConexion(); // Paso 2 : esperar conexion
     obtenerFlujos(); // Paso 3 : obtener flujos de entrada y salida
     procesarConexion(); // Paso 4 : procesar la conexion
    } //  Procesar Expecion EOFException cuando el cliente
    //    cierre la conexion
    catch (EOFException excepcionEOF) {
     System.out.println("El servidor termino la conexion ");
    } finally {
     cerrarConexion(); // Paso 5 : cerrar conexion
     ++contador;
    } // fin de instruccion while
   } // fin del bloque try
  } catch (IOException excepcionEOF) {
   excepcionEOF.printStackTrace();
  }
 } // fin del metodo ejecutarServidor

 // ♦ METODO
//Esperar que la conexion llegue,despues mostrar informacion conexion
 private void esperarConexion() throws IOException {
  mostrarMensaje("Esperando conexion ");
  conexion = servidor.accept(); // permitir servidor aceptar conexion
  mostrarMensaje("Conexion " + contador + "recibida de : " + conexion.getInetAddress().getHostAddress());
 }

// ♦ METODO
// Obtener flujo para enviar y recibir datos
 private void obtenerFlujos() throws IOException {
// Establecer flujo de salida para los objetos
  salida = new ObjectOutputStream(conexion.getOutputStream());
  salida.flush(); // vaciar buffer de salida para enviar infor de encabezado
// establecer flujo de entrada para los objetos
  entrada = new ObjectInputStream(conexion.getInputStream());
  mostrarMensaje("\n Se recibieron los flujos de E/S\n");
 }

// ♦ METODO
//procesar la conexion con el cliente
 private void procesarConexion() throws IOException {
//Enviar mensaje de conexion exitosa al cliente
  String mensaje = "Conexión exitosa";
  enviarDatos(mensaje);
//Habilitar campoIntroducir para que el usuario del Servidor
// pueda enviar mensajes
  establecerCampoTextoEditable(true);

// ♠ MIENTRAS
  do { // procesar mensaje enviados 
//  leer el mensaje y mostrarlo en pantalla
//↕ INTENTA
   try {
    mensaje = (String) entrada.readObject();
    mostrarMensaje("\n " + mensaje);
   } //  atrapar problemas que puedan ocurrir al tratar de leer el cliente
   catch (ClassNotFoundException exceptionClaseNoEncontrada) {
    mostrarMensaje("\n Se recibio un tipo de objeto desconocido");
   }
// ♠ MIENTRAS
//  Mensaje distinto sea igual a ( "Cliente >>> terminal" );
  } while (!mensaje.equals("Cliente>>>Terminar"));
 } // fin del metodo procesarConexion

// ♦ METODO
//cerrar flujos y socket
 private void cerrarConexion() {
  mostrarMensaje("\nFinalizado la conexion \n");
  establecerCampoTextoEditable(false); //deshabilitar campoIntroducir
// ↕ INTENTA
  try {
   salida.close();
   entrada.close();
   conexion.close();
  } catch (IOException excepcionES) {
   excepcionES.printStackTrace();
  }
 }

// ♦ METODO
// Enviar mensaje al cliente
 private void enviarDatos(String mensaje) {
// ↕ INTENTA
//  Enviar objeto al cliente
  try {
   salida.writeObject("SERVIDOR >>> " + mensaje);
   salida.flush();
   mostrarMensaje("\nServidor >>>" + mensaje);
  } // procesar problemas que pueden ocurrir al enviar el objeto
  catch (IOException excepcionES) {
   areaPantalla.append("\nError al escribir objeto");
  }
 }

// ♦ METODO
//utilitario que es llamado desde otros subprocesos para 
//manipular la areaPantalla en el subproceso de eventos
 private void mostrarMensaje(final String mensajeAMostrar) {
// mostrar mensaje del subproceso de ejecucion despachador de eventos
  SwingUtilities.invokeLater(
          new Runnable() { // clase interna para asegurar que la GUI se actualice apropiadamente 

// ♦ METODO
           public void run() { // actualizar areaPantalla
            areaPantalla.append(mensajeAMostrar);
            areaPantalla.setCaretPosition(areaPantalla.getText().length());
           }
          } // fin de la clase interna
  ); // fin de la llamada a SwingUtilities.InvokeLater
 }

// ♦ METODO
//utilitario que es llamado desde otros subprocesos para manipular a campoIntroducir en el subproceso despachador de eventos
 private void establecerCampoTextoEditable(final boolean editable) {
// mostrar mensaje del subproceso de ejecucion despachador de eventos
  SwingUtilities.invokeLater(new Runnable() { // Clase Interna para asegurar 
//  que la GUI se actualice apropiadamente 

// ♦ METODO 
//  Para sincronizar
   public void run() { // establece la capacidad de modificar a campoIntroducir
    campoIntroducir.setEditable(editable);
   }
  } // fin de clase interna
  ); // fin de la llamada a SwingUtilities.invokeLater
 }

// ♦ METODO MAIN
 public static void main(String[] args) throws IOException {
//  Clase 
  JFrame.setDefaultLookAndFeelDecorated(true);
  Servidor aplicacionServidor = new Servidor();
  aplicacionServidor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  aplicacionServidor.ejecutarServidor();
 }
} // fin de la clase Servidor

