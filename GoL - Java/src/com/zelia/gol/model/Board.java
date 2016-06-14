package com.zelia.gol.model;

import java.util.Random;

public class Board {
	private boolean board[][];
	private int generation;
	private GenerationCheck gc;

	// CSTOR
	public Board(){
		createBoard(10,10);
	}
	
	public Board(boolean board[][]){
		createBoard(board.length,board[0].length);
		this.board = board;
	}
	
	public Board(int x, int y){
		createBoard(x,y);
	}
	
	// Private Methods
	private void createBoard(int x, int y){
		gc = new GenerationCheck();
		board = new boolean[x][y];
				
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
		board = gc.startCheckup(board);
	}
	
	public void changeCell(int x, int y){
		board[x][y] = !board[x][y];
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
		gc.displayInfos();
	}
	
	// getters
	public boolean[][] getBoard(){
		return board;
	}
	
	public boolean getCell(int x, int y){
		return board[x][y];
	}

	public int getBoardX(){ return board.length;}
	public int getBoardY(){ return board[0].length;}

	public void switchCellState(int x, int y) {
		board[x][y] = !board[x][y];
	}
	
}
