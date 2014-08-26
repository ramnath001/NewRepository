/*
 * RegisterWithRMIServer.java
 * 
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$ 
 */



/* This class looks up for the server object in the registry using
 * the keyword specified and the client can call remote methods
 * using the object 
 *  
 *
 */




//import statements are placed here

import java.rmi.registry.*; 
public class RegisterWithRMIServer { 

 // main function

  public static void main(String args[]) { 
   try { 
      tt obj = new tictactoe();  		//server class object

      Registry registry = LocateRegistry.getRegistry(); 
      registry.rebind("localhost", obj);	//bind server object with the keyword
      System.out.println("Student server " + obj + " registered"); 
   } 
   catch (Exception ex) { 
      ex.printStackTrace(); 
   } 
  } 
} 
