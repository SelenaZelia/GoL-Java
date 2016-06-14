package com.zelia.gol;

/*
 * Hello world program
 */
public class Program {

	public static void main(String[] args) {
		//boolean[][] board=  new boolean[10][10];
		//board[4][4] = true;
		//board[4][5] = true;
		//board[5][4] = true;
		//board[5][5] = true;
		
		//Board b = new Board(board);
		Board b = new Board(10,5);
		b.randomizeBoard(128390L);
		b.display();
		
		for(int gen = 0; gen < 10; ++gen){
			b.nextGen();
			b.display();
		}
		
	}

}
