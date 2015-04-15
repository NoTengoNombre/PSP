/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t5pruebasclientesmtp;

import java.util.Properties;
import javax.mail.Session;

/**
 *
 * @author InTylerWeTrust
 */
public class T5PruebasClienteSMTP {

public abstract class Authenticator{

  public Authenticator() {
   int pas = 1234;
  }
  

}
 
  public static Session sesion;
  public static Properties propiedades;
 

  public static void main(String[] args) {
  // TODO code application logic here
  
  
  sesion = Session.getDefaultInstance(propiedades);
  
  
  
 }
}
