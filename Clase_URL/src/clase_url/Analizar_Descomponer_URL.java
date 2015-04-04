/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clase_url;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author InTylerWeTrust
 */
public class Analizar_Descomponer_URL {

 public static void main(String[] args) throws MalformedURLException {

  URL url = null;
  try {
//   url = new URL("http://java.sun.com:80/tutorial/intro.html#DOWNLOADING");
//  Sin puerto aparece -1 
   url = new URL("http://java.sun.com/tutorial/intro.html#DOWNLOADING");
   System.out.println("protocolo = " + url.getProtocol());
   System.out.println("host = " + url.getHost());
   System.out.println("filename = " + url.getFile());
   System.out.println("port = " + url.getPort());
   System.out.println("ref = " + url.getRef());
  } catch (MalformedURLException e) {
   System.out.println("MalformedURLException " + e);
  }
 }
}
