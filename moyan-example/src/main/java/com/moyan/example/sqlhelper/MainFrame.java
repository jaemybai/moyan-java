package com.moyan.example.sqlhelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.RollingFileAppender;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;


public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -854194571915108287L;

	private static Log logger = LogFactory.getLog(MainFrame.class);
	
	private JSplitPane splitPane = null;
	private Memu memu = null;
	private JTextArea queryArea = null;
	private JPanel queryAreaPane = null;
	private JTextArea resultArea = null;
	private JPanel resultAreaPane = null;
	private JTable table = null;
	private JPanel tablePane = null;
	private JScrollPane queryScollPane = null;
	private JScrollPane resultScoolAreaPane = null;
	private JScrollPane tablePaneScoolAreaPane = null;

	private Connection connection = null;

	MainFrame() {
		init();
		initPanel();
		addListener();
		initFrame();
	}
	
	public void init() {
		memu = new Memu(this);
		queryArea = new JTextArea("eqweq");
		queryAreaPane = new JPanel();
		resultArea = new JTextArea();
		resultAreaPane  = new JPanel();
		tablePane = new JPanel();
		resultArea.setEditable(false);
//		queryAreaPane.add(queryArea);
		queryArea.setRows(10);
		queryScollPane = new JScrollPane(queryArea);
		resultAreaPane.add(resultArea);
		resultScoolAreaPane = new JScrollPane(resultAreaPane);
		table = new JTable(10,5);
		tablePane.add(table);
		tablePaneScoolAreaPane = new JScrollPane(tablePane);
	}
	
	public void initPanel(){
		JTabbedPane resultTab = new JTabbedPane(JTabbedPane.TOP);
		resultTab.addTab("script output", resultScoolAreaPane);
		resultTab.addTab("query result", tablePaneScoolAreaPane);


		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				queryScollPane, resultTab);

	}
	
	public void initFrame() {
		this.setJMenuBar(memu);
		this.getContentPane().add(splitPane, BorderLayout.CENTER);
//		this.getContentPane().add(queryAreaPane);
//		this.getContentPane().add(tablePane);
		setSize(1000, 800);
		setTitle("sql helper");
		setLocationRelativeTo(null);
		setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.addWindowListener(new WindowAdapter() {
			@SuppressWarnings("unused")
			public void WindowClosing(WindowEvent e) {
				logger.info("this program will be exit....");
				dispose();
				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				logger.info("this program will be exit....");
				dispose();
				System.exit(0);
			}
			
		});
	}
	
	public void addListener() {
		queryArea.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER
				&& e.isControlDown()) {
					logger.debug("start to execute sql....");
					connection = memu.getDialog().getConnection();
					if(connection == null) {
						logger.debug("connection null,please connect.");
						return;
					}
					String sql = queryArea.getSelectedText().trim();
					logger.debug("sql:" +sql);
					if(sql.toUpperCase().startsWith("SELECT")) {
						try {
							List<List<String>> list =
									JdbcBase.parseResultSet(JdbcBase.query(connection, sql));
							TableModel model = initTable(list, list.size());
							table.setModel(model);
						} catch (SQLException e1) {
							logger.error(e1.getMessage(), e1);
						}
					}
					else {
						try {
							JdbcBase.execute(connection, sql);
						} catch (SQLException e1) {
							logger.error(e1.getMessage(), e1);
						}
					}
				}
			}
			public TableModel initTable(List<List<String>> list, int row)
			  {
			    String[][] data = convertListToDoubleArray(list, row);
			    TableModel tableModel = new DefaultTableModel(data, list.get(0).toArray());
			    return tableModel;
			  }
			
			public  String[][] convertListToDoubleArray(List<List<String>> list, int row)
			  {
			    if (list == null) return null;

			    if (row > list.size()) {
			    	row = list.size();
			    }
			    
			    int length = (list.get(0)).size();
			    String[][] doubleArray = new String[row][length];
			    List list1 = null;
			    for (int i = 0; i < row; i++) {
			      list1 = (List)list.get(i);
			      for (int j = 0; j < length && j<list1.size(); j++) {
			        doubleArray[i][j] = ((String)list1.get(j));
			      }
			    }
			    return doubleArray;
			  }
			
		});
	}

	public static void main(String[] args) throws Exception {
		
		PropertyConfigurator.configure("lib/log4j.properties");
		RollingFileAppender a = null;
		//%d %-4r [%t] %-5p %c %x - %m%n  |  %d %p [%c] - %m%n
//		2016-10-12 00:44:48,156 0    [main] INFO  swing.sqlhelper.MainFrame  - this program will be starting...
//		2016-10-12 00:45:36,098 INFO [swing.sqlhelper.MainFrame] - this program will be starting...
		logger.info("this program will be starting...");
		String lookAndFeel= null;
		lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
		lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
//		UIManager.setLookAndFeel(lookAndFeel);
		lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		//com.sun.java.swing.plaf.windows.WindowsLookAndFeel
		logger.info(lookAndFeel);
	
//		SwingUtilities.updateComponentTreeUI(frame);
		Font font = new Font("dsa", Font.PLAIN, 18);
		 FontUIResource fontRes = new FontUIResource(font);
		
		for (Enumeration<Object> keys = UIManager.getDefaults().keys();
             keys.hasMoreElements(); ) {
				  Object key = keys.nextElement();
				  Object value = UIManager.get(key);
				  logger.info(key+"\t"+value);
				  if(value instanceof FontUIResource) {
					  UIManager.put(key, fontRes);
				  }
					  
		}
		MainFrame frame = new MainFrame();

	}

}
