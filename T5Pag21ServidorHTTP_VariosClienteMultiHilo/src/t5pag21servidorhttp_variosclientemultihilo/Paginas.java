/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

NOTA : Clase no instanciable donde se define algunos valores finales

*/
package t5pag21servidorhttp_variosclientemultihilo;

/**
 *
 * @author InTylerWeTrust
 */
public class Paginas {

 public static final String primeraCabecera = 
         "Content-Type:text/html;charset=UTF-8";
// Contenido index
 public static final String html_index = 
         "<html>"
         +"<head><title>Index</title></head>"
         +"<body>"
         +"<h1>!Enhorabuena ☻ !</h1>"
         +"<p>Tu servidor HTTP minimo funciona correctamente</p>"
//         +"<img src =file:C:\\Users\\InTylerWeTrust\\Pictures\\Iconos App\\tyler1.jpg heigth="1050" widht="1680px" </img>";
         +"</body>"
         +"</html>";

// Contenido Quijote
 public static final String html_quijote = 
         "<html>"
         +"<head><title>quijote</title></head>"
         + "<body>"
         + "<h1 align=center>Mensaje</h1>"
         + "<center>"
         + "<img src=http://s3-ec.buzzfed.com/static/2014-01/enhanced/webdr05/10/0/enhanced-buzz-8724-1389332638-0.jpg  height=391 width=625 >"
         + "</center>"
         + "</body>"
         +"/<head>";

// Contenido noEncontrado
 public static final String html_noEncontrado = 
         "<html>"
         +"<head><title>No Encontrado</head></title>"
         +"<body>"
         +"<h1> ! ERROR ! Pagina no encontrada </h1>"
         +"<p>La pagina que solicitastes no existe en nuestro servidor </p>"
         +"<p>Advertencia: Si está leyéndola, entonces esta advertencia es para usted. Transcurre un segundo de su vida por cada palabra que lea de esta inservible cláusula. ¿No tiene otras cosas en qué ocuparse? ¿O le impresiona tanto la autoridad que respeta y le cree todo lo que dice tenerla? ¿Lee todo lo que supuestamente debe leer? ¿Piensa todo lo que supuestamente debe pensar? ¿Compra lo que dicen que debe querer? Salga de su departamento. Conozca alguien del sexo opuesto. Deje a un lado las compras excesivas y la masturbación. Renuncie a su empleo. Búsquele una pelea a alguien. Pruebe que está vivo. Si no proclama su humanidad se convertirá en una estadística. Ya está advertido………………………….Tyler </p>"
         +"</body>"
         +"</html>";
}



























