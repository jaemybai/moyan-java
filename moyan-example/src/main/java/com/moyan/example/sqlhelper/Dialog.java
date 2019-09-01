package com.moyan.example.sqlhelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class Dialog {

	private static Log logger = LogFactory.getLog(Dialog.class);
	public static final String lineSplit = "\n";
	public static final String keyValueSplit = "="; JDialog dialog = null;
	private JFrame frame = null;
	private JLabel nameLabel = null;
	private JLabel userLabel = null;
	private JLabel passwordLabel = null;
	private JLabel urlLabel = null;
	private JLabel classLable = null;
	private JLabel propertiesLable = null;

	private JTextField nameField = null;
	private JTextField userField = null;
//	private JTextField passwordField = null;
	JPasswordField passwordField= null;
	private JTextField urlField = null;
	private JTextField classField = null;

	private JTextArea propertiesArea = null;
//	private JPanel propertiesPane = null;
	private JScrollPane propertiesScrollPane = null;

	private JButton connButton = null;
	private JLabel conInfoLabel = null;
	private Connection connection = null;
	private int dialogWidth = 800;
	private int dialogHight = 600;
	private int screenWidth = 0;
	private int screenHight = 0;
	private int startX = 450;
	private int startY = 300;
	public Dialog() {
		
	}
	
	public Dialog(JFrame frame) {
		Dimension screensize= Toolkit.getDefaultToolkit().getScreenSize();
//		this.screenWidth = screensize.width;
//		this.screenHight = screensize.height;
//		startX = screenWidth/2 - dialogWidth/2;
//		startY = screenHight/2 - dialogHight/2;

		this.frame = frame;
		init();
		setBounds();
		addListener();
		initDialog();
	}
	
	public void init() {
		nameLabel = new JLabel("name");
		userLabel = new JLabel("user");
		passwordLabel = new JLabel("password");
		urlLabel = new JLabel("url");
		classLable = new JLabel("class");
		propertiesLable = new JLabel("properties");
		
		nameField = new JTextField("test");
		userField = new JTextField("root");
		passwordField = new JPasswordField("123456");
		urlField = new JTextField("jdbc:mysql://localhost:3306/test");
		classField = new JTextField("com.mysql.jdbc.Driver");
		propertiesArea = new JTextArea("1=1");
//		propertiesPane = new JPanel();
//		propertiesPane.add(propertiesArea);
		propertiesScrollPane = new JScrollPane(propertiesArea);
		connButton = new JButton("connection");
		conInfoLabel = new JLabel("connection infomation");
		dialog = new JDialog(frame, false);
	}
	
	public void setBounds() {
		int width = 100;
		int hight = 50;
		int widthSize = 100;
		int hightSize = 50;
		int temstartX = startX - 300;
		int temstartY = startY - 300;
		nameLabel.setBounds(temstartX+width, temstartY+hight,widthSize,hight);
		userLabel.setBounds(temstartX+width, temstartY+2*hight,widthSize,hight);
		passwordLabel.setBounds(temstartX+width, temstartY+3*hight,widthSize,hight);
		urlLabel.setBounds(temstartX+width, temstartY+4*hight,widthSize,hight);
		classLable.setBounds(temstartX+width, temstartY+5*hight,widthSize,hight);
		propertiesLable.setBounds(temstartX+width, temstartY+6*hight,widthSize,hight);
		conInfoLabel.setBounds(temstartX+width, temstartY+9*hight,widthSize,hight);

		nameField.setBounds(temstartX+3*width, temstartY+hight,widthSize,hight);
		userField.setBounds(temstartX+3*width, temstartY+2*hight,widthSize,hight);
		passwordField.setBounds(temstartX+3*width, temstartY+3*hight,widthSize,hight);
		urlField.setBounds(temstartX+3*width, temstartY+4*hight,widthSize*3,hight);
		classField.setBounds(temstartX+3*width, temstartY+5*hight,widthSize*3,hight);
//		propertiesArea.setBounds(temstartX+3*width, temstartY+6*hight,widthSize*3,hight*2);
		propertiesScrollPane.setBounds(temstartX+3*width, temstartY+6*hight,widthSize*3,hight*2);
		connButton.setBounds(temstartX+3*width, temstartY+9*hight,widthSize,hight);

	}
	public void initDialog() {
		
		Container container = dialog.getContentPane();
		container.setLayout(null);
		container.add(nameLabel);
		container.add(userLabel);
		container.add(passwordLabel);
		container.add(urlLabel);
		container.add(classLable);
		container.add(propertiesLable);
		container.add(nameField);
		container.add(userField);
		container.add(passwordField);
		container.add(urlField);
		container.add(classField);
		container.add(propertiesScrollPane);
		container.add(connButton);
		container.add(conInfoLabel);
		dialog.setTitle("new connection");
		dialog.setBounds(startX, startY, dialogWidth, dialogHight);
//		setLocationRelativeTo(null);
		dialog.setVisible(false);
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		dialog.addWindowListener(new WindowAdapter() {
			@SuppressWarnings("unused")
			public void WindowClosing(WindowEvent e) {
				logger.info("shutdown the dialog...");
				System.exit(0);
			}
		});
	}
	public void addListener() {
		connButton.addActionListener(new ConnActionListener());
	}
	
	class ConnActionListener implements ActionListener {

		String name = "";
		String user = "";
		String password = "";
		String url = "";
		String clazz = "";
		String otherProperties = "";
		Properties properties = new Properties();
	
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			new Thread(){
				public void run() {
					runInThread();
				}
			}.start();
		}
		
		public void runInThread() {
			try {
				setConInfoLabel("");
				name = nameField.getText().trim();
				user = userField.getText().trim();
				password = new String(passwordField.getPassword());
				url = urlField.getText().trim();
				clazz = classField.getText().trim();
				otherProperties = propertiesArea.getText();
				if(properties == null) {
					 properties = new Properties();
				}
				else {
					properties.clear();
				}
				properties.put("user", user);
				properties.put("password", password);

				Map<String, String> otherMap = null;
				logger.info("otherProperties:" + otherProperties);
				otherMap = parseStr(otherProperties);
				logger.info("otherMap:" + otherMap);

				properties.putAll(otherMap);
				if(connection != null) {
					connection.close();
					connection = null;
					logger.info("the connection has no closed,so will close before get another.");

				}
				connection = JdbcBase.getConn(url, clazz, properties);
				logger.info("succeessfull to get a connection.: "+ connection);
				setConInfoLabel("succeessfull to get a connection.: "+ connection);
				Thread.sleep(2000);
				dialog.dispose();
			} 
			catch (Exception ee) {
				logger.error(ee.getMessage(), ee);
				setConInfoLabel(ee.getMessage());
			}
		}
	}
	
	public void setConInfoLabel(String text) {
		setLabelAndTool(conInfoLabel, text, true);
	}
	public void setLabelAndTool(JLabel label , String text, boolean isShowTool) {
		
		label.setText(text);
		if(isShowTool) {
			label.setToolTipText(text);
		}
		label.repaint();
	}
	
	private Map<String, String> parseStr(String str) {
		
		Map<String, String> resMap = new HashMap<String, String>();
		if(str == null || str.isEmpty()) {
			return resMap;
		}
		String[] lines = str.split(lineSplit);
		for(String line : lines) {
			if(line == null || line.isEmpty()
					|| !line.contains(keyValueSplit)) {
				logger.info("line:"+line+"\ncontinue");
				continue;
			}
			
			String[] keyvalues = str.split(keyValueSplit);
			if(keyvalues.length != 2) {
				logger.info("line:"+line+"\ncontinue");
				continue;
			}
			if(keyvalues[0] == null || keyvalues[0].isEmpty() ||
					keyvalues[1] == null || keyvalues[1].isEmpty()) {
				logger.info("line:"+line+"\ncontinue");
				continue;
			}
			resMap.put(keyvalues[0].trim(), keyvalues[1].trim());
			
		}
		return resMap;
		
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public JDialog getDialog() {
		return dialog;
	}

	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}
	
}
