package main;

import frame.GFrame;

public class GMain {
	
	private static GFrame frame;
	public static void main(String[] args) {
		// main - ���α׷� ������
		frame = new GFrame();
		frame.initialize();
		
		frame.setVisible(true);
		
 	}
}
