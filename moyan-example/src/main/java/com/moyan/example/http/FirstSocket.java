package com.moyan.example.http;

import com.moyan.example.j2se.base.ViolateTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URLConnection;
import java.net.UnknownHostException;

public class FirstSocket {

	private static Logger logger = LoggerFactory.getLogger(ViolateTest.class);

	public static void main(String args[]) throws Exception {
		String strServer = "www.sina.com.cn";
		// 取得第一个参数
		String strPage = "o/2017-03-27/doc-ifycsukm3847674.shtml";
//		strServer = "tool.oschina.net";
//		strPage = "apidocs/apidoc?api=jdk-zh";
		strServer = "localhost";
		 strPage = "Spring-mybatic-test/index.jsp";
		  
		// 取得第二个参数
		String hostname = strServer;
		int port = 8080;
		InetAddress addr = InetAddress.getByName(hostname);
		Socket socket = new Socket(addr, port);
		// 建立一个Socket //发送命令
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream(), "UTF8"));
		wr.write("GET / " + strPage + " HTTP/1.0\r\n");
		wr.write("HOST:" + strServer + "\r\n");
		wr.write("\r\n");
		wr.flush(); // 接收返回的结果
		 BufferedReader rd = new BufferedReader(new InputStreamReader(
		 socket.getInputStream()));
		 String line;
		 while ((line = rd.readLine()) != null) {
		 logger.info(line);
		 }
		 wr.close();
		 rd.close();
	}

	public static void writeToFile(InputStream inputStream,
			OutputStream outputStream) throws Exception {
		byte[] data = new byte[1000];
		int len;
		long total = 0;
		while (true) {
			len = inputStream.read(data, 0, data.length);
			if (len == -1) {
				logger.info("read end..");
				break;
			}
			outputStream.write(data, 0, len);
			total = total + len;
			if (total % 500 == 0) {
				System.out.printf("has read total %d bytes data..\n", total);
				System.out.flush();
			}
		}
		outputStream.flush();
		outputStream.close();
		inputStream.close();
	}
}