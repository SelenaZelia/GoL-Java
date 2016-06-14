package com.zelia.gol;

/*
 * Hello world program
 */
public class Program {

	public static void main(String[] args) {
		Board b = new Board(10,10);
		b.randomizeBoard(128390L);
		b.display();
	}

}
