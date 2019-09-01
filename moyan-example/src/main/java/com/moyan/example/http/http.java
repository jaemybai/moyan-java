package com.moyan.example.http;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class http {

	public static void main(String[] args) throws Exception {
		
		
		String urlStr = "https://passport.csdn.net/?service=http://write.blog.csdn.net/postlist";
		String fileName;
		urlStr = "https://github.com/";
//		urlStr = "https://workspace.citi.com/vpn/index.html";
		urlStr = "https://www.baidu.com/";
//		urlStr = "http://repo.maven.apache.org/maven2/org/apache/httpcomponents/httpclient/4.0/httpclient-4.0.jar";
//		urlStr = "http://apache.fayea.com//activemq/5.14.4/apache-activemq-5.14.4-bin.zip";
		urlStr = "http://ifeve.com/java-nio-vs-io/";
		urlStr = "http://localhost:8080/Spring-mybatic-test/index.jsp";
//		fileName = "httpclient-4.0.jar";
		fileName = "url.html";
//		fileName = "apache-activemq-5.14.4-bin.zip";
		URL url = new URL(urlStr);
		URLConnection urlConnection = url.openConnection();
		System.out.println(urlConnection.getClass().getCanonicalName());
		System.out.println("start.............");
		operation(urlConnection);
		writeToFile(urlConnection, fileName);
		System.out.println("end...........");
	}
	
	public static void operation(URLConnection urlConnection) throws IOException{
		urlConnection.setDoOutput(true);
		InputStream inputStream = null;
		OutputStream outputStream  = null;
		outputStream = urlConnection.getOutputStream();
		String str = "set Ok successfully.";
		outputStream.write(str.getBytes());
		outputStream.flush();
		outputStream.close();
//		urlConnection.connect()
	}
	public static void writeToFile(URLConnection urlConnection,String fileName) throws Exception {
		byte[] data = new byte[1000];
		InputStream inputStream = urlConnection.getInputStream(); 
		
		OutputStream outputStream = new FileOutputStream(fileName);
		int len;
		long total = 0 ;
		while(true) {
			len = inputStream.read(data, 0, data.length);
			if(len == -1) {
				System.out.println("read end..");
				break;
			}
			outputStream.write(data, 0, len);
			total = total + len;
			if(total%500 == 0 ){
				System.out.printf("has read total %d bytes data..\n",total);
				System.out.flush();
			}
		}
		outputStream.flush();
		outputStream.close();
		inputStream.close();
	}
}
