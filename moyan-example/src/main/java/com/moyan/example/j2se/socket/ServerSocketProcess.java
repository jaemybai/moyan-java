package com.moyan.example.j2se.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerSocketProcess extends Thread{

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
			System.out.println(Thread.currentThread().getName()+":接受来自"+socket.getInetAddress());
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			BufferedWriter bw  = new BufferedWriter(new OutputStreamWriter(outputStream));
			while(true) {
				System.out.println("start1");
				String str = br.readLine();
				System.out.println("start2");

				if(str == null) {
					System.out.println(Thread.currentThread().getName()+":通信结束来自"+socket.getInetAddress());
					break;
				}
				System.out.println(Thread.currentThread().getName()+
						":接受来自"+socket.getInetAddress()+"消息内容："+str);
				bw.write("接收完成{内容}："+str+"来自"+socket.getInetAddress());
				System.out.println("end");

			}
//			br.close();
//			bw.close();
//			inputStream.close();
//			outputStream.close();
//			socket.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
