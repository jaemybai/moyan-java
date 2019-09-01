package com.moyan.example.j2se.swing;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class EmptyFrame extends JFrame{

	public static void main(String[] args) {
		
		JFrame frame = new EmptyFrame();
		frame.setVisible(true);
		frame.setSize(300, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
	}
}
