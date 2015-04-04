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
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author InTylerWeTrust
 */
public class Cliente extends JFrame {
// ♣ Objetos de la clase

 private JTextField campoIntroducir;
 private JTextArea areaPantalla;
 private ObjectOutputStream salida;
 private ObjectInputStream entrada;
 private String mensaje = "";
 private String servidorChat;
 private Socket cliente;

// Inicializar servidorChat y configurar GUI 
 public Cliente(String host) throws HeadlessException {
//  Coge los atributos y metodos de la superclase
  super("Cliente");

// Establecer el servidor al que se va a conectar este cliente
  servidorChat = host;

  Container contenedor = getContentPane();

// Crear campoIntroducir y registrar componente de escucha
  campoIntroducir = new JTextField();
  campoIntroducir.setEditable(false);
  campoIntroducir.addActionListener(new ActionListener(){
// ♦ METODO
//   enviar mensaje al servidor
   public void actionPerformed(ActionEvent evento) {
    enviarDatos(evento.getActionCommand());
    campoIntroducir.setText("");
   }
  }
  );

  contenedor.add(campoIntroducir, BorderLayout.NORTH);

//  crear areaPantalla
  areaPantalla = new JTextArea();
  contenedor.add(new JScrollPane(areaPantalla), BorderLayout.CENTER);

  setSize(300, 150);
  setVisible(true);
 } // fin del constructor 

// ♦ METODO 
// Conectarse al Servidor y Procesar Mensaje del Servidor
 private void ejecutarCliente() {
//  Conectarse al Servidor , obtener Flujos , Procesar Conexion
  try {
   conectarAServidor(); // Paso 1 : crear un socket para realizar la conexion 
   obtenerFlujos(); // Paso 2 : obtener los flujos de entrada y salida
   procesarConexion(); // Paso 3 : procesar la conexion
  } //  el servidor cerro la conexion
  catch (EOFException excepcionEOF) {
   System.err.println("El cliente termino la conexion");
  } // procesar los problemas que pueden ocurrir al comunicarse con el servidor
  catch (IOException excepcionES) {
   excepcionES.printStackTrace();
  } finally {
   cerrarConexion(); // Paso 4 : Cerrar la conexion
  }
 } // fin del metodo ejecutarCliente

// ♦ METODO
// conectarse al servidor
 private void conectarAServidor() throws IOException {
  mostrarMensaje("Intentando realizar conexion");
// crear Socket para realizar la conexion con el servidor
  cliente = new Socket(InetAddress.getByName(servidorChat), 12345);
// Mostrar la informacion de la conexion
  mostrarMensaje("Conectado a " + cliente.getInetAddress().getHostName());
 }

// ♦ METODO
// Obtener flujos para enviar y recibir datos
 private void obtenerFlujos() throws IOException {
//  establecer flujo de salida para los objetos
  salida = new ObjectOutputStream(cliente.getOutputStream());
// vaciar bufer de salida para enviar informacion de encabezado
  salida.flush();
//  establecer flujo de entrada para los objetos
  entrada = new ObjectInputStream(cliente.getInputStream());
//  Mostrar mensaje
  mostrarMensaje("\n Se recibieron los flujos de E\\S \n ");
 }

// ♦ METODO
// Procesar la conexion con el servidor
 private void procesarConexion() throws IOException {
//  Habilitar campoIntroducir para que el usuario del cliente pueda enviar mensajes
  establecerCampoTextoEditable(true);
// ☼ HACER
  do { // procesar mensajes enviados al servidor
// ↕ INTENTA
   try {
    mensaje = (String) entrada.readObject();
    mostrarMensaje("\n" + mensaje);
   } // atrapar los problemas que pueden ocurrir al leer del servidor
   catch (ClassNotFoundException excepcionClaseNoEntrada) {
    mostrarMensaje("\nSe recibio un objeto de tipo desconocido");
   }
// ☼ MIENTRAs
  } while (!mensaje.equals("SERVIDOR>>> TERMINAR"));
 } // fin del metodo procesarConexion
//  cerrar flujo y socket

// ♦ METODO
 private void cerrarConexion() 
 {
  mostrarMensaje("\n Cerrando conexion");
// Deshabilitar campoIntroducir
  establecerCampoTextoEditable(false); // deshabilitar campoIntroducir
//  ↕ INTENTA
  try {
   salida.close();
   entrada.close();
   cliente.close();
  } 
  catch (IOException excepcionES) {
   excepcionES.printStackTrace();
  }
 }

// ♦ METODO
// enviar mensaje al servidor
 private void enviarDatos(String mensaje) {
// enviar objeto al servidor
// ↕ INTENTA
  try {
   salida.writeObject("Cliente>>> " + mensaje);
   salida.flush();
   mostrarMensaje("\n CLIENTE>>>" + mensaje);
  } // procesar los problemas que pueden ocurrir al enviar el objeto
  catch (IOException excepcioES) {
   areaPantalla.append("\nError al escribir el objeto");
  }
 }

// ♦ METODO utilitario
// Es llamado desde otros subprocesos para manipular a
// areaPantalla en el subproceso despachador de eventos
 private void mostrarMensaje(final String mensajeAMostrar) {
// mostrar mensaje del subproceso de ejecucion de la GUI
  SwingUtilities.invokeLater(
          new Runnable() { // ♥ clase interna para asegurar que la GUI se actualice apropiadamente 

           public void run() { // actualiza areaPantalla
            areaPantalla.append(mensajeAMostrar);
            areaPantalla.setCaretPosition(areaPantalla.getText().length());
           }
          } // fin de la clase interna
  ); // fin de la llamada a SwingUtilities.invokeLater
 }

// ♦ METODO utilitario 
// Llamado desde otro subprocesos para manipula a 
// campoIntroducir en el subprocesos despachador de eventos
 private void establecerCampoTextoEditable(final boolean editable) {
// Mostrar mensaje del subproceso de ejecucion de la GUI 
  SwingUtilities.invokeLater(
          new Runnable() { // clase interna para asegurar que la GUI se actualice correctamente

           @Override
           public void run() 
           { // establece la capacidad de modificar campoIntroducir
            campoIntroducir.setEditable(editable);
           }
          } // fin de la clase interna
  ); // fin de la llamada a SwingUtilities.invokeLater
 }

// ♦ METODO MAIN
 public static void main(String args[]) {
  JFrame.setDefaultLookAndFeelDecorated(true);
  Cliente aplicacionCliente;
// ☼ BUCLE SI
  if (args.length == 0) {
   aplicacionCliente = new Cliente("127.0.0.1");
  } else {
   aplicacionCliente = new Cliente(args[0]);
  }
  aplicacionCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  aplicacionCliente.ejecutarCliente();
 }
}
