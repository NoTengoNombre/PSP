/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clase_url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

//  http://lineadecodigo.com/java/leer-una-url-con-java/

  /* Normalmente, cuando tenemos una URL lo que hacemos es ponerla 
   en un navegador para ver su contenido. 
   Pero hay muchos programas que se dedican a analizar 
   el código fuente de las páginas. 
   Ya sean buscadores, optimizadores de código, validadores,...

   En este segundo caso necesitaremos de un código 
   que abra una conexión y examine su código, 
   en vez de mostrarlo por pantalla. 
   En nuestro caso vamos a utilizar el lenguaje Java 
   para llevar a cabo nuestro cometido.

   Lo primero es tener una URL. 
   Para ello nos apoyamos en la clase URL,
   que nos validará si el texto introducido es 
   realmente una URL bien formada (con su protocolo, 
   su nombre de servidor,...)*/

public class LeerURLconJava {

// *** Opcion en caso de error : Crea una variable static para toda la clase 
// solo 1 copia para toda la clase
// Create field in Class LeerURLconJava 
// private static String inputLine;

 // *** Opcion en caso de error : Crea un parametro dentro del metodo Main
// Create parameter inputLine 
 public static void main(String[] args) throws MalformedURLException, IOException {

  //  *** Opcion en caso de error : crea una variable 
//  que es una copia que se repite para 
//  toda la clase
//  *** Opcion en caso de error : 
//  Create local variable
//  String inputLine;
  
// Intenta
  try {
//   Indicamos la URL donde nos conectamos
   URL url = new URL("http://www.lineadecodigo.com");
//   Buffer con los datos recibidos
   BufferedReader in = null;
// ------------------------------------------------------------
//  Si por cualquier motivo insertamos otro texto que no fuese una URL
//          acabariamos teniendo un MalformedURLException
//Una vez creado el objeto URL deberemos de abrir un 
//   InputStream sobre el para poder leer la informacion que cotiene
//                Como si fuera cualquier otro origen de datos
//                        En este caso una drieccion web
//  -----------------------------------------------------------
// Intenta
   try {
//   Volcamos lo recibido al objeto BufferedReader de una entrada de la clase InputStreamReader
    in = new BufferedReader(new InputStreamReader(url.openStream()));
// Captura y lanza la excepción de errores 
   } catch (Throwable t) {}

// Transformamos el contenido del buffer a texto
// Entrada de linea  
   String inputLine;
// Entrada de texto
   String inputText= "";

//--------------------------------------------
//  Este InputStream lo manejaremos con un BufferedReader el cual nos 
//  facilitara en sobre manera la lectura del contenido
// --------------------------------------------

//  Sobre este BufferedReader iremos leyendo linea por linea
//  que el contenido de lo leido sea diferente de null
//  Controlaremos de la misma forma que no se produzca excepciones 
//  IOException
   
// Mientras haya bytes en el buffer los volcamos a las cadena de texto
   while ((inputLine = in.readLine()) != null) 
   {
//  Obj.almacena = Objeto +       inputLine = Objeto cargado BufferedReader+readLine           
    inputText = inputText + "\n"+ inputLine;
   }
//   Mostrar objeto
   System.out.print("El contenido de la URL es : "+inputText);
   
// Mostramos el contenido y cerramos la entrada 
// Cerramos el objeto in de la clase BufferedReader con el metodo close()  
   in.close();
// Capturamos las excepciones 
// Capturamos las excepciones mal formada URL
  }catch(MalformedURLException me){
   System.out.println("URL erroneo");
// Capturamos las excepciones entrada/salida 
  }catch(IOException ioe){
   System.out.println("Error IO");
  }
//  El contenido leido lo podemos almacenar en un String que 
//          mostraremos posteriormente por pantalla
//                  Al final solo quedara cerrar el stream
  }
 }
