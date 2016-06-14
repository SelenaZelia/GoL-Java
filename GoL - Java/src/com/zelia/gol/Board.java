package com.zelia.gol;

import java.util.Random;

public class Board {
	private boolean board[][];
	private int generation;

	// CSTOR
	public Board(){
		createBoard(10,10);
	}
	
	public Board(int x, int y){
		createBoard(x,y);
	}
	
	// Private Methods
	private void createBoard(int x, int y){
		board = new boolean[x][y];
				
		if(Debug.debug) 
			System.out.println("Board created [" + x + "," + y +"]");
	}
	
	// Public Methods 
	public void randomizeBoard(long seed){
		Random r = new Random(seed);
		generation = 0;
		
		for(int x = 0; x < board.length; ++x)
			for(int y = 0; y < board[0].length; ++y)
				board[x][y] = r.nextBoolean(); 
	}
	
	public void nextGen(){
		++generation;
		
		
	}
	
	
	public void display(){
		System.out.println("= Gen " + generation + " ====");
		for(int x = 0; x < board.length; ++x){
			for(int y = 0; y < board[0].length; ++y){
				if(board[x][y])
					System.out.print(" | #");
				else
					System.out.print(" |  ");
			}
			System.out.println(" |");
		}
	}
	
}
