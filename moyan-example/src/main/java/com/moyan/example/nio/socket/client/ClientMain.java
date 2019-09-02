package com.moyan.example.nio.socket.client;


import com.moyan.example.nio.socket.client.client.core.IClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenjie
 * 2013-6-8
 */
public class ClientMain {

	private static Logger logger = LoggerFactory.getLogger(ClientMain.class);

	public static void main(String[] args){

		logger.info("start a client socket...");
		IClient client = new IClient();
		client.init(9900);
		new Thread(client).start();
		client.send(ClientMain.class.getName() + "Hi Server 0");
		
	}
	
}
