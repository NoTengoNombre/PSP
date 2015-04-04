/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t5pag23tiempoprocesamiento;


import java.util.Date;
/**
 *
 * @author InTylerWeTrust
 */
public class T5Pag23TiempoProcesamiento {

 /**
  * @param args the command line arguments
  */
 
 public static void tiempo_procesamiento(){
//  Tiempo inicial
  long tiempo1 = (new Date().getTime());
//  Acciones a realizar para procesar la peticion del cliente
//  Tiempo final
  long tiempo2 = (new Date().getTime());
  System.out.println("\t Tiempo = "+(tiempo2-tiempo1)+ " ms ");
 }

 
 public static void main(String[] args) {
  // TODO code application logic here

  System.out.println("-------------Metodo-1------------");

  tiempo_procesamiento();
  
  
  System.out.println("-------------Metodo-2------------");
  long time_start , time_end;
  long t1 = System.currentTimeMillis();
  
//  Acciones a realizar para procesar
//    la peticion del cliente
  long t2 = System.currentTimeMillis();
  
  System.out.println("\t Tiempo = "+(t2-t1)+ " ms ");
 
 
 
 
 
 
 }
}























