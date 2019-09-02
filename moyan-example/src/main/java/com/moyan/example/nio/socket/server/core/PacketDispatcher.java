package com.moyan.example.nio.socket.server.core;

import com.moyan.example.nio.socket.server.data.Packet;
import com.moyan.example.nio.socket.server.data.Response;
import com.moyan.example.nio.socket.server.data.Session;
import com.moyan.example.nio.socket.server.manager.SessionManager;
import com.moyan.example.nio.socket.server.protocal.IEncoder;
import com.moyan.example.nio.socket.server.protocal.text.TextEncoder;

import java.nio.channels.SocketChannel;

/**
 * @author chenjie
 * 2013-6-13
 */
public class PacketDispatcher {

	private static PacketDispatcher instance;
	
	private IEncoder<Response> encoder;
	
	private PacketDispatcher(){
		this.encoder = new TextEncoder();
	}
	
	public static PacketDispatcher getInstance(){
		if(instance == null){
			instance = new PacketDispatcher();
		}
		return instance;
	}
	
	//Ð´Êý¾Ý
	public void write(Response res){
		
		Packet packet = this.encoder.encode(res);
		if(res.getRecipients() != null){

			for(SocketChannel recv : packet.getRecipients()){
				Session session = SessionManager.getInstance().getSession(recv);
				if(session != null){
					Packet tmpPacket = new Packet(recv, packet.getData());
					session.addWritablePacket(tmpPacket);
				}
			}
				
		}
	}
	
}
