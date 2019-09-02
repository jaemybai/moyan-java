package com.moyan.example.nio.socket.server.core;


import com.moyan.example.nio.socket.server.data.Packet;
import com.moyan.example.nio.socket.server.data.Request;
import com.moyan.example.nio.socket.server.data.Session;
import com.moyan.example.nio.socket.server.manager.SessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenjie
 * 2013-6-8
 */
public class PacketReader implements Runnable{

	private Logger logger = LoggerFactory.getLogger(getClass());

	private IServer server;
	private AtomicInteger readCounts = new AtomicInteger(0);

	public PacketReader(IServer server){
		this.server = server;
	}

	@Override
	public void run() {
		
		while(true){
			List<Session> sessionList = SessionManager.getInstance().getAllSessions();
			
			for(Session session : sessionList){
			
				Packet packet = session.popReadablePacket();
				while(packet != null){

					Request request = this.server.getDecoder().decode(packet);
					
					if(request != null){
						
					}
					
					String data = new String(packet.getData());
					
					logger.info("packetReader read data: {}", data);
					String returnData = this.getClass().getName() + ",reto:"+data;
					packet.setData(returnData.getBytes());
					if(readCounts.incrementAndGet() <= 2) {
						session.addWritablePacket(packet);
					}
					packet = session.popReadablePacket();
				}
				
			}
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				logger.error(e.getMessage(),e);
			}
		}

		
	}

}
