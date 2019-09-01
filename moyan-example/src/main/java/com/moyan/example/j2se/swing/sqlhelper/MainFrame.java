package com.moyan.example.j2se.swing.sqlhelper;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.moyan.example.j2se.jdbc.JdbcBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;


public class MainFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -854194571915108287L;

	private Log logger = LogFactory.getLog(MainFrame.class);
	
	private JSplitPane splitPane = null;
	private Memu memu = null;
	private JTextArea queryArea = null;
	private JTextArea resultArea = null;
	private JTable table = null;
	private JPanel tablePane = null;
	private Connection connection = null;

	MainFrame() {
		logger.info("this program will be starting...");
		init();
		initPanel();
		addListener();
		initFrame();
	}
	
	public void init() {
		memu = new Memu(this);
		queryArea = new JTextArea();
		resultArea = new JTextArea();
		tablePane = new JPanel();
		resultArea.setEditable(false);
	}
	
	public void initPanel(){
		JTabbedPane resultTab = new JTabbedPane(JTabbedPane.TOP);
		table = new JTable();
//		tablePane.add(table.getTableHeader(), BorderLayout.NORTH);
		tablePane.add(table);
		resultTab.addTab("script output", resultArea);
		resultTab.addTab("query result", tablePane);
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				queryArea, resultTab);

	}
	
	public void initFrame() {
		this.setJMenuBar(memu);
		this.getContentPane().add(splitPane, BorderLayout.CENTER);
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
			public  TableModel initTable(List<List<String>> list, int row)
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

	public static void main(String[] args) {
		
		PropertyConfigurator.configure("src/log4j.properties");
		new MainFrame();
	}
	

}
