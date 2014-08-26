/*
 * RMIServer.java
 * 
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$ 
 */

/* This program defines an interface consisting of methods of
 * 'tictactoe' class.
 * 
 * 
 */
  

import java.io.*;
import java.net.*;  
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

//declaring the interface

interface tictac extends java.rmi.Remote{
char[][] retboard() throws java.rmi.RemoteException; 
public void Move(char position) throws java.rmi.RemoteException; 
public char victoryCheck() throws java.rmi.RemoteException; 
public char getTurn() throws java.rmi.RemoteException;
public void boardDisplay()  throws java.rmi.RemoteException;
}


class tictactoe extends UnicastRemoteObject implements tictac 
{
	private char turn;			
	private char board[][];			
	private int turns;			
	public tictactoe() throws Exception
	{
		board = new char[3][3];
		turn='X';
		char count='1';
		for (int i=0;i<3;i++)
			for (int j=0;j<3;j++)
				board[i][j]=count++;
		turns=0;
		System.out.println("\nGame Loaded");
	}


	public void boardDisplay()
	{
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
	}

	//returns the board array

	public char[][] retboard()
	{
		return board;
	}

    public char getTurn()
	{	
		return turn;
	}

	//this function stores 'x' and '0' in the board array

	public void Move(char position)
	{
		boolean flag=false;
		for (int i=0;i<3;i++)
			for (int j=0;j<3;j++)
			{       
               if(position==board[i][j])
			   {
				  board[i][j]=turn;
				  turns++;
				  flag=true;
				}
			}

		//checks for position already taken

		if (!flag)
		{	System.out.println(position);
			if (position>='1' && position<='9')
				System.out.println("\nThat position is already taken... Look again ...");				
			else
				System.out.println("\nInvalid position");
		}
		else
		{
			if (turn=='O')
				turn='X';
			else
				turn='O';
		}
		
	}
	
	//checks for the winner of the game

	public char victoryCheck()
	{
		char winner='-';
		if (turns==9)
			winner='D';

		//checks for column wins

		for (int j=0;j<3;j++)
		{
			if (board[0][j]==board[1][j] && board[0][j]==board[2][j])
				winner=board[0][j];
		}

		//checks for row wins

		for (int i=0;i<3;i++)
		{
			if (board[i][0]==board[i][1] && board[i][0]==board[i][2])
				winner=board[i][0];
		}
		if (board[0][0]==board[1][1] && board[0][0]==board[2][2])
			winner=board[0][0];
		if (board[0][2]==board[1][1] && board[0][2]==board[2][0])
			winner=board[0][2];
		return winner;
	}
	
}

// this class implements RMIserver class which binds 'game' object
// with the rmiregistry.

public class RMIServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		char victor='-';	
		char pick='-';		
tt game = new tictactoe(); 
                   
		 
	try{
	     Naming.rebind("localhost", game);		  //binds the object with the keyword
		 System.out.println("tictactoe bound in registry"); 
	   }
	   catch (Exception e) {}
		  System.out.println("Connection established");

		do
		{

			if (game.getTurn()=='O')
			{				
			}
			else
			{			
			  game.boardDisplay();
			  System.out.println("\n"+game.getTurn()+" turn.");
			  InputStreamReader isr=new InputStreamReader b=new InputStreamReader(System.in);
			  BufferedReader b = new BufferedReader(isr);
			  pick=b.readLine().charAt(0);
			  game.Move(pick);
			}
			victor=game.victoryCheck();		
			if (victor=='X'||victor=='O')
				 System.out.println( "\n\n\t\t\tThe winner is "+victor+"  :D");
			if (victor=='D')
				 System.out.println( "\nDraw :(");
		}while(victor=='-');
		game.boardDisplay();
	}

}
