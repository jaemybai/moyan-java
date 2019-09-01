package com.moyan.example.j2se.socket;

import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(4002);
		while(true) {
			Socket socket = serverSocket.accept();
			new ServerSocketProcess(socket).start();
		}
	}
}
