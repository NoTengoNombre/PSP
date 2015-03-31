/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;


/**
 *
 * @author InTylerWeTrust
 */
public class Pruebas {

 /**
  * @param args the command line arguments
  */
 public static int  n1 = 1;
 public static int n2 = 2;

 public static void Suma1(int n1,int n2) {
  int n3 = n1 + n2;
  System.out.println(n3);
 } 

 public static Nueva_Clase Nuevo1(Nueva_Clase n1 , Suma n2){
  System.out.println("n2 " +n2);
  return n1;
 }
 
 public static void main(String[] args) {
  // TODO code application logic here
  Suma1(n1,n2);
 }
 
 public class Nueva_Clase{
  int nuevo1 = 1;
  int nuevo2 = 2;
  int nuevo3 = 3;
 }
 
}
