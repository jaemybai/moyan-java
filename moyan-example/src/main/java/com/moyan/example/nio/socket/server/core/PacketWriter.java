package com.moyan.example.nio.socket.server.core;

import com.moyan.example.nio.socket.server.data.Packet;
import com.moyan.example.nio.socket.server.data.Session;
import com.moyan.example.nio.socket.server.manager.SessionManager;

import java.util.List;

/**
 * @author chenjie
 * 2013-6-8
 */
public class PacketWriter implements Runnable{
	
	private IServer server;
	
	public PacketWriter(IServer server){
		this.server = server;
	}

	@Override
	public void run() {
		
		while(true){
			
			List<Session> sessionList = SessionManager.getInstance().getAllSessions();
			
			for(Session session : sessionList){
				Packet packet = session.popWritablePacket();
				while(packet != null){
					//do the write 
					this.server.getDataHandler().handleWrite(packet.getSender(), packet.getData());
					
					packet = session.popWritablePacket();
				}
			}
			
		}
		
	}

}
