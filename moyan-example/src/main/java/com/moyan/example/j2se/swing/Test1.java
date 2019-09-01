package com.moyan.example.j2se.swing;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

public class Test1 extends JFrame {

	public Test1() {

		MenuTest menuTest = new MenuTest();
		this.setJMenuBar(menuTest);
		this.addWindowListener(new WindowAdapter() {
			public void WindowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});
		setSize(700, 500);
		setTitle("Swing �����ȫ�����");
		setLocation(200, 150);
		setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

	class MenuTest extends JMenuBar {

		private JDialog aboutDialog;
		public MenuTest() {
			JMenu fileMenu = new JMenu("file");

			JMenuItem exitMenuItem = new JMenuItem("exit", KeyEvent.VK_E);

			JMenuItem aboutMenuItem = new JMenuItem("new connection..",
					KeyEvent.VK_A);

			fileMenu.add(aboutMenuItem);
			fileMenu.add(exitMenuItem);
			this.add(fileMenu);
			aboutDialog = new JDialog();
			initAboutDialog();

			exitMenuItem.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					dispose();
					System.exit(0);
				}
			});

			aboutMenuItem.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					aboutDialog.setVisible(true);
				}
			});

		}
		public void initAboutDialog() {

			aboutDialog.setTitle("new connection");
			Container con = aboutDialog.getContentPane();
			JLabel aboutLabel = new JLabel("dsa");
			aboutLabel.setLocation(500, 500);
			con.add(aboutLabel);
			aboutDialog.setSize(450, 225);
			aboutDialog.setLocation(300, 300);
			aboutDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

			aboutDialog.addWindowListener(new WindowAdapter() {
				public void WindowClosing(WindowEvent e) {
					dispose();
				}
			});

		}

	}


	public static void main(String args[]) {

		new Test1();

	}

}