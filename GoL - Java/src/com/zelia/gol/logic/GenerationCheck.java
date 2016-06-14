package com.zelia.gol.logic;

/*
 * Any live cell with fewer than two live neighbours dies, as if caused by under-population.
 * Any live cell with two or three live neighbours lives on to the next generation.
 * Any live cell with more than three live neighbours dies, as if by over-population.
 * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
 */
public class GenerationCheck {
	private int deadCellUP;
	private int deadCellOP;
	private int survCell;
	private int newCell;

	private boolean[][] board;
	private boolean[][] nextGen;
	
	public boolean[][] startCheckup(boolean[][] board){
		// ct
		deadCellUP = 0;
		deadCellOP = 0;
		survCell = 0;
		newCell = 0;
		
		// boards
		nextGen = new boolean[board.length][board[0].length];
		this.board = board; 
		
		checkup();
		
		return nextGen;
	}
	
	private void checkup(){
		int currentNei;
		
		for(int x = 0; x < board.length; ++x){
			for(int y = 0; y < board[0].length; ++y){
				currentNei = getNei(x,y);
				if(currentNei < 2 && board[x][y]){
					deadCellUP++;
					nextGen[x][y] = false;
				} else if (currentNei > 3 && board[x][y]){
					deadCellOP++;
					nextGen[x][y] = false;
				} else if (currentNei == 3 && !board[x][y]){
					newCell++;
					nextGen[x][y] = true;
				} else if (board[x][y]) {
					survCell++;
					nextGen[x][y] = true;
				}
			}
		} 
	}
	
	public int getNei(int x, int y){
		// m min ; M max
		// checking 8(less on borders) neighbors around a cell
		// <!> uses the initial board
		int aM = (x == (board.length-1)) ? 0 : 1;
		int bM = (y == (board[0].length-1)) ? 0 : 1;
		
		int res = 0;
		
		for(int am = (x == 0) ? 0 : -1;am <= aM; am++) 
			for(int bm = (y == 0) ? 0 : -1;bm <= bM; bm++) 
				if(am != 0 || bm != 0)
					res = (board[x+am][y+bm]) ? res + 1 : res;  
		return res;
	}
	
	public void displayInfos(){
		System.out.println(" -- Current gen step infos ----");
		System.out.println("  - Dead cells (UP) : " + deadCellUP);
		System.out.println("  - Dead cells (OP) : " + deadCellOP);
		System.out.println("  - New cells       : " + newCell);
		System.out.println("  - Surviving cells : " + survCell);
		System.out.println(" ------------------------------");
	}
}
