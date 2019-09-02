package com.moyan.example.j2se.socket;

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

public class ServerSocketProcess extends Thread{

	private static Logger logger = LoggerFactory.getLogger(ServerSocketProcess.class);

	private Socket socket;
	private InputStream inputStream;
	private OutputStream outputStream;
	
	public ServerSocketProcess() {
		super();
	}

	
	public ServerSocketProcess(Socket socket) {
		super();
		this.socket = socket;
	}


	@Override
	public void run() {
		
		try {
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
			logger.info(Thread.currentThread().getName()+":接受来自"+socket.getInetAddress());
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			BufferedWriter bw  = new BufferedWriter(new OutputStreamWriter(outputStream));
			while(true) {
				logger.info("start1");
				String str = br.readLine();
				logger.info("start2");

				if(str == null) {
					logger.info(Thread.currentThread().getName()+":通信结束来自"+socket.getInetAddress());
					break;
				}
				logger.info(Thread.currentThread().getName()+
						":接受来自"+socket.getInetAddress()+"消息内容："+str);
				bw.write("接收完成{内容}："+str+"来自"+socket.getInetAddress());
				logger.info("end");

			}
//			br.close();
//			bw.close();
//			inputStream.close();
//			outputStream.close();
//			socket.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(),e);
		}
		
	}

	
}
