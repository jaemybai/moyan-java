package com.moyan.example.sqlhelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JdbcBase {

	private static Log logger = LogFactory.getLog(JdbcBase.class);
	private static String clazz = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/test";
	private static String user = "root";
	private static String password = "123456";
	private static String param1 = "characterEncoding";
	private static String value1 = "utf8";
	private static Properties properties = new Properties();
	
	private static Connection connection = null;
	
	static {
		properties.put("user", user);
		properties.put("password", password);
		properties.put(param1,value1);
	}
	
	public static Connection getConn(String url, String clazz, String user, String passwrod)
			throws Exception {
		
		Connection conn = null;
		logger.info("url:" +url);
		logger.info("clazz:" +clazz);
		logger.info("user" +user);
		logger.info("passwrod:" +passwrod);

		try {
			Class.forName(clazz);
			conn = DriverManager.getConnection(url, user,passwrod);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		} 
		return conn;
	}
	
	public static Connection getConn(String url, String clazz, Properties properties)
			throws Exception {
		
		logger.info("url:" +url);
		logger.info("clazz:" +clazz);
		if(properties != null) {
			for(Object key : properties.keySet()) {
				logger.info(""+key+" : " +properties.getProperty(key.toString()));
			}
		}
		
		Connection conn = null;
		try {
			Class.forName(clazz);
			conn = DriverManager.getConnection(url, properties);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return conn;
	}

	
	public static Statement getStatement(Connection conn) throws SQLException {
		
		Statement statement = null;
		try {
			statement = conn.createStatement();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return statement;
	}
	
	public static PreparedStatement getPreStatement(Connection conn, String sql) throws SQLException {
		
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement(sql);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return statement;
	}
	
	public static ResultSet query(Connection conn, String sql) throws SQLException {
		
		ResultSet rs = null;
		Statement stmt  = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return rs;
	}
	
	public static boolean execute(Connection conn, String sql) throws SQLException {
		
		boolean isSuccess = false;
		Statement stmt  = null;
		try {
			stmt = conn.createStatement();
			isSuccess = stmt.execute(sql);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return isSuccess;
	}

	public static List<List<String>> parseResultSet(ResultSet rs) throws SQLException {
		
		List<List<String>> result = new ArrayList<List<String>>();
		try {
			List<String> columnNames = new ArrayList<String>();
			ResultSetMetaData metaData = rs.getMetaData();
			int columns = metaData.getColumnCount();
			for(int i=1;i<=columns;i++) {
				columnNames.add(metaData.getColumnName(i));
			}
			result.add(columnNames);
			while(rs.next()) {
				List<String> contentRow = new ArrayList<String>();
				for(int i=1;i<=columns;i++) {
					contentRow.add(rs.getString(i));
				}
				result.add(contentRow);
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return result;
	}
	
	
	public static Connection getConnection() {
		return connection;
	}

	public static void setConnection(Connection connection) {
		JdbcBase.connection = connection;
	}
	
}
