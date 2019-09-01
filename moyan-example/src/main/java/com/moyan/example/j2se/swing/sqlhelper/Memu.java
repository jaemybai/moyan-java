package com.moyan.example.j2se.swing.sqlhelper;

import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Memu extends JMenuBar{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1260052538691158115L;

	private Log logger = LogFactory.getLog(Menu.class);

	private JMenu menu = null;
	private JMenuItem newConnItem = null;
	private JMenuItem exitItem = null;
	private Dialog dialog = null;
	private JFrame frame = null;
	
	public Memu() {
	}
	
	public Memu(JFrame frame) {
		this.frame = frame;
		init();
		addListener();
	}
	
	public void init() {
		menu = new JMenu("file");
		newConnItem = new JMenuItem("new connection");
		exitItem = new JMenuItem("exit");
		menu.add(newConnItem);
		menu.add(exitItem);
		
		dialog = new Dialog(frame);
		this.add(menu);
	}
	
	public void addListener() {
		
		newConnItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("open the new connection dialog..");
				dialog.getDialog().setVisible(true);
			}
		});
		
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("this pragram will be exit...");
				Connection  conn = dialog.getConnection();
				if(conn != null) {
					try {
						conn.close();
						conn = null;

					} catch (SQLException e1) {
						logger.info(e1.getMessage(),e1);
					}
				}
				System.exit(0);
			}
		});
	}

	public Dialog getDialog() {
		return dialog;
	}

	public void setDialog(Dialog dialog) {
		this.dialog = dialog;
	}

}
