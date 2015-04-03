/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t5pag21servidorhttp;

/**
 *
 * @author InTylerWeTrust
 */
public class Paginas {
 
 public static final String primeraCabecera = 
         "Content-Type:text/html;charset=UTF-8";
//Contenido Index
 public static final String html_index = "<html>"
         +"<head><title>index</title></head>"
         +"<body>"
         +"<h1>!Enhorabuena!</h1>"
         +"<p> Tu servidor HTTP minimo funciona correctamente ☻ </p>"
         +"<p> Ya te puedes dar con un puto canto en los dientes </p>"
         +"<p> InTylerWeTrust </p>"
         +"</body>"
         +"</html>";
 
//Contenido Quijote
 public static final String html_quijote = "<html>"
         +"<head><title>quijote</title></head>"
         +"<body>"
         +"<h1>Asi comienza el Quijote</h1>"
         +"<p>Si estas leyendo esto, va dirigido a ti. \n "
         + "Cada palabra que leas de esta letra pequeña e inútil \n "
         + "es un segundo menos de vida que te queda. \n "
         + "¿No tienes otras cosas que hacer? \n "
         + "¿Tu vida está tan vacía que no se te \n "
         + "ocurre otra forma de pasar estos momentos? \n "
         + "¿O te impresiona tanto la autoridad que concedes crédito \n"
         + " y respeto a todos los que dicen ostentarla?.\n" 
         +"¿Lees todo lo que te dicen que leas? \n "
         + "¿Pruebas todo lo que te dicen que pruebes? \n "
         + "¿Compras todo lo que te dicen que compres? Despierta, \n "
         + "sal de tu apartamento, \n "
         + "busca a alguien de tu sexo opuesto, \n "
         + "basta ya de tantas compras y masturbaciones. \n "
         + "Deja tu trabajo, empieza a luchar, \n "
         + "demuestra que estas vivo, \n "
         + "si no reivindicas tu humanidad, te convertirás en una estadística.\n</p>"
         +"</body>"
         +"</html>";

// Contenido noEncontrado
 public static final String html_noEncontrado = 
         "<html>"
         +"<head><title>No encontrado</tile></head>"
         +"<body>"
         +"<h1>!ERROR! Página no Encontrado</h1>"
         +"<p>La pagina que solicitastes no existe en nuestro"
         +"servidor </p>"
         +"</body>"
         +"</html>";
}
