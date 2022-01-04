package main;

import frame.GFrame;

public class GMain {
	
	private static GFrame frame;
	public static void main(String[] args) {
		// main - 프로그램 시작점
		frame = new GFrame();
		frame.initialize();
		
		frame.setVisible(true);
		
 	}
}
