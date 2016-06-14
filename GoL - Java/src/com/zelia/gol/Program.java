package com.zelia.gol;

import com.zelia.gol.view.MainWindow;
 
public class Program {

	public static void main(String[] args) {
		// Fixing an exception happening for some reasons on the jdk
		// (can't upgrade on main computer)
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		
		// Creating GUI
		MainWindow mw = new MainWindow();
		
		System.out.println("Display window");
		mw.setVisible(true);

		System.out.println("GUI OK");
	} 
}
