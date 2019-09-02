package com.moyan.example.j2se.socket;

import com.moyan.example.j2se.base.ViolateTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocket {

	private static Logger logger = LoggerFactory.getLogger(ViolateTest.class);

	public static void main(String[] args) throws Exception {
		
		Socket socket = new Socket("127.0.0.1", 4002);
		OutputStream outputStream = socket.getOutputStream();
		BufferedWriter bw  = new BufferedWriter(new OutputStreamWriter(outputStream));
		for(int i=0;i<4;i++) {
			bw.write("发送消息："+i);
			bw.flush();
			Thread.sleep(2000);
		}
		bw.flush();
//		bw.close();
//		outputStream.close();
		InputStream inputStream = socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

		while(true) {
			String str = br.readLine();
			if(str == null) {
				break;
			}
			logger.info("响应："+str);
		}
		
//		br.close();
//		inputStream.close();
//		socket.close();
	}
}
