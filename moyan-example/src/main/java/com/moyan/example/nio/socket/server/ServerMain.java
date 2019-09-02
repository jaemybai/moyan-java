package com.moyan.example.nio.socket.server;

import com.moyan.example.nio.socket.server.core.IServer;
import com.moyan.example.nio.socket.server.core.PacketReader;
import com.moyan.example.nio.socket.server.core.PacketWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenjie
 * 2013-6-7
 */
public class ServerMain {

	private static Logger logger = LoggerFactory.getLogger(ServerMain.class);

	public static void main(String[] args){

		logger.info("start server socket.........");
		IServer server = new IServer();
		server.bind(9900);
		
		PacketReader reader = new PacketReader(server);
		PacketWriter writer = new PacketWriter(server);
		new Thread(server).start();
		new Thread(reader).start();
		new Thread(writer).start();

		Runtime.getRuntime().addShutdownHook( new Thread(() -> {
			try {
				logger.info("---------------stop the server...");
				Thread.sleep(1000);
				logger.info("----------------the server is stopped....");
			}catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}) );
	}

}
