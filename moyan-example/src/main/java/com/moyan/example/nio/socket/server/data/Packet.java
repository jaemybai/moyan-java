package com.moyan.example.nio.socket.server.data;


import com.moyan.example.nio.socket.server.protocal.ProtocalType;

import java.nio.channels.SocketChannel;
import java.util.List;

/**
 * @author chenjie 2013-6-8
 */
public class Packet {

	private int protocalType;		//Э������
	private SocketChannel sender;	//������
	private byte[] data;	//����
	
	private List<SocketChannel> recipients;	//������

	public Packet(SocketChannel sender, byte[] data) {
		this.protocalType = ProtocalType.TEXT_PROTOCAL.getType();
		this.sender = sender;
		this.data = data;
	}
	
	public Packet(byte[] data){
		this.protocalType = ProtocalType.TEXT_PROTOCAL.getType();
		this.data = data;
	}

	public final SocketChannel getSender() {
		return sender;
	}

	public final void setSender(SocketChannel sender) {
		this.sender = sender;
	}

	public final byte[] getData() {
		return data;
	}

	public final void setData(byte[] data) {
		this.data = data;
	}

	public final int getProtocalType() {
		return protocalType;
	}

	public final void setProtocalType(int protocalType) {
		this.protocalType = protocalType;
	}

	public final List<SocketChannel> getRecipients() {
		return recipients;
	}

	public final void setRecipients(List<SocketChannel> recipients) {
		this.recipients = recipients;
	}
	
	

}
