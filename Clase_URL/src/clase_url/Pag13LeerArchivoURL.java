/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clase_url;

import com.sun.org.apache.xerces.internal.util.URI.MalformedURIException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 *
 * @author InTylerWeTrust
 */
public class Pag13LeerArchivoURL {

 public static void main(String[] args) throws IOException {
  
//  define objeto url asociado a un recurso de Internet
  URL url = null;
// Intenta
  try {
//   Objeto de la clase URL
   url = new URL("http://ftp.rediris.es/debian/README.mirrors.txt");
//   Conecta a esa URL
   url.openConnection();
//   Asocia un flujo de entrada a la conexion URL
   InputStream flujoIn = url.openStream();
//   Crea un flujo de salida asociado al destino 
//   Si no pongo ruta por defecto se crea dentro del proyecto de Netbeans

//   Segmento de codigo para :
//   Crear la ruta del archivo
//   Crear el tipo de archivo
   File ruta = new File("C:\\ruta");
//   Si ruta no existe
   if (!ruta.exists()) {
    System.out.println("Ruta no creada");
//  Crea la ruta 
    ruta.mkdir();
//  Sino ruta esta creada
   }else{
    System.out.println("Ruta Creada "+ruta);
   }
   
// Crea objeto fichero tipo .doc
//   File fichero = new File("texto2.doc");
// Crea objeto fichero tipo .txt  
   File fichero = new File("texto2.txt");
// Si fichero no existe
   if (!fichero.exists()) {
//    fichero se crea 
     fichero.createNewFile();
     System.out.println("fichero creado");
//   fichero ya existe
   }else{
    System.out.println("fichero ya existe");
   }
   
//   File crearFichero = new File(ruta.getAbsolutePath(),fichero.toString());
//                                    fichero necesita convertirse a String para que funcione
   File crearFichero = new File(ruta,fichero.toString());
   
// Salida del fichero en forma de objeto con la ruta y el tipo de fichero que se va a usar
   FileOutputStream flujoOutFile = new FileOutputStream(crearFichero);
  
//   Definir el buffer de lectura con un tamaño de 512 bytes
   byte [] buffer = new byte[512];
   int BytesLeidos , totalBytesLeidos = 0;
   
//   Mientras hay bytes leer del archivo
   while (( BytesLeidos = flujoIn.read(buffer)) > 0){
//    almacena lo que lee en el buffer 
    flujoOutFile.write(buffer , 0 , BytesLeidos);
    totalBytesLeidos += BytesLeidos;
    System.out.print(".");
   }
  }catch(MalformedURIException e){
   System.out.println("MalformedURIException : "+e);
  }catch(IOException ioe){
   System.out.println("IOException : "+ioe);
  }
 }
}





















