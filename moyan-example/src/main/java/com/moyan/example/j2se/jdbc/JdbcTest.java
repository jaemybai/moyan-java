package com.moyan.example.j2se.jdbc;

import com.moyan.example.j2se.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcTest {

	private static Logger logger = LoggerFactory.getLogger(JdbcTest.class);

	public static void main(String[] args) throws Exception {
		
		String sql1= "insert into test11 values (?);";
		String sql2 = "select * from test11";

		StringBuilder builder = new StringBuilder();
		builder.append("����").append(DateUtil.getCurDate());
		Connection con = null;
		Statement statement = null;
		PreparedStatement pstm = null;
		ResultSet set = null;
		con = JdbcBase.getConn(sql2, sql2, null);
		statement = con.createStatement();
//		pstm = con.prepareStatement(sql1);
//		pstm.setString(1, builder.toString());
//		pstm.executeUpdate();
		sql1 = "insert into test11 values ('test');";
		statement.execute(sql1);
		set = statement.executeQuery(sql2);
		String desc = null ;
		logger.info("start........");
		while(set.next()) {
			desc = set.getString(1);
			logger.info(desc);
		}
		logger.info("end......");
	}

}
