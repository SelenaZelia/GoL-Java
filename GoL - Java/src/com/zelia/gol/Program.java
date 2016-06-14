package com.zelia.gol;

import com.zelia.gol.model.Board;
import com.zelia.gol.view.MainWindow;

/*
 * Hello world program
 */
public class Program {

	public static void main(String[] args) {
		if(args.length != 2){
			System.out.println("Args usage : [size x] [size y]");
			System.out.println("Now exiting");
			System.exit(0);
		}
		
		
		int sizeX = Integer.parseInt(args[0]);
		int sizeY = Integer.parseInt(args[1]);
		System.out.println("Starting app with size : " + sizeX + ";" + sizeY);
		
		//Creating board
		Board board = new Board(sizeX, sizeY);
		
		//Creating GUI
		MainWindow mw = new MainWindow(board);
		mw.setVisible(true);
		
		System.out.println("GUI OK");
	}

}
