package com.moyan.example.nio.socket.server.core;


import com.moyan.example.nio.socket.server.data.Packet;
import com.moyan.example.nio.socket.server.data.Session;
import com.moyan.example.nio.socket.server.manager.SessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.channels.SocketChannel;

/**
 * @author chenjie
 * 2013-6-8
 */
public class DefaultDataHandler implements IDataHandler {

	private Logger logger = LoggerFactory.getLogger(DefaultDataHandler.class);

	private IServer server;
	
	public DefaultDataHandler(IServer server){
		this.server = server;
	}

	@Override
	public void handleRead(SocketChannel channel, byte[] data, int len) {
		
		Session session = SessionManager.getInstance().getSession(channel);
		
		if(session != null){
			byte[] dest = new byte[len];
			System.arraycopy(data, 0, dest, 0, len);
			logger.info("add to read data to queue,length:{}, data:{}", dest.length, new String(dest));
			session.addReadablePacket(new Packet(channel, dest));
		} else {
			byte[] dest = new byte[len];
			System.arraycopy(data, 0, dest, 0, len);
			logger.error("there is no session to read data,", new String(dest));
		}
		
	}

	@Override
	public void handleWrite(SocketChannel channel, byte[] data) {
		
		this.server.addWritableData(channel, data);
		
	}

}
