package com.moyan.example.j2se.swing;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ss extends JFrame {
	Container con = this.getContentPane();
	JButton jb1 = new JButton("jb1");
	JButton jb2 = new JButton("jb2");
	JLabel jl1 = new JLabel("jl1");
	JTextArea area = new JTextArea("before");

	JTextField field = new JTextField("before");
	FlowLayout gly = new FlowLayout();
	JPanel jp = new JPanel(gly);

	public Ss() {
		con.add(jp);

		jp.add(jb1);
		jp.add(jb2);
		jp.add(field);
		jp.add(area);

		MyListener ml = new MyListener();
		jb1.addMouseListener(ml);
		this.setSize(400, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	private class MyListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			try {
				new Thread(){
					public void run() {
					for(int i=0;i<4;i++) {
						field.setText(i+"_after");
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					}
				}.start();
				System.out.println(12);
				Thread.sleep(2000);
				System.out.println(22);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String s[]) {
		
		Ss sss = new Ss();
		sss.setVisible(true);
//		new Thread(){
//			public void run() {
//				Ss sss = new Ss();
//				sss.setVisible(true);
//			}
//		}.start();
	}
}