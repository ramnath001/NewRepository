/*
 * RMIClient.java
 * 
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$ 
 */



/* This program consists of class RMIClient which contains 
 * functions to implement the 'tictactoe' game 
 *
 *
 */



import java.net.*;  
import java.io.*;
import java.rmi.*;

public class RMIClient 
{

	//main class

	public static void main(String[] args) throws Exception  
	{
		String detail;			
		tictac game = (tictac)Naming.     	                           
        lookup("localhost");
		char pick='-';				
		char victor='-';			
		char[][] board=new char[3][3];

		do
		{
			if (game.getTurn()=='X')
			{}
			else
			{
			  board=game.retboard();
			  System.out.println("\n\n\n");
			for (int i=0;i<2;i++)
			{	
				for (int j=0;j<2;j++)
					System.out.print(board[i][j]+" | ");
				System.out.print(board[i][2]);
				System.out.print("\n---------\n");
			}
			for (int j=0;j<2;j++)
				System.out.print(board[2][j]+" | ");
				
			System.out.print(board[2][2]);
			System.out.println("\n"+game.getTurn()+" turn.");
				try
				{
					pick = (char)System.in.read();
				}
				catch(Exception exe){}
			
			//call to 'move' to store 'X', '0' in board
				game.Move(pick);		
			}
			victor=game.victoryCheck();

			//checks for the winner and prints it

			if (victor=='X'||victor=='O')
				System.out.println( "\n\n\t\t\tThe winner is "+victor+"  :D");
			if (victor=='D')
				System.out.println( "\nDraw :(");
		}while(victor=='-');
		board=game.retboard();
		System.out.println("\n\n\n");
		//prints the board

		for (int i=0;i<2;i++)
		{	
			for (int j=0;j<2;j++)
				System.out.print(board[i][j]+" | ");
			System.out.print(board[i][2]);
			System.out.print("\n---------\n");
		}
		for (int j=0;j<2;j++)
			System.out.print(board[2][j]+" | ");
		System.out.print(board[2][2]);
	

	}
}
