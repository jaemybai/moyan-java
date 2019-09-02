package com.moyan.example.nio.socket.server.core;

import java.nio.channels.SocketChannel;

/**
 * @author chenjie
 * 2013-6-8
 */
public class SelectionKeyChangeReq {
	
	private SocketChannel channel;		//ͨ��
	private int type;					//����
	
	public SelectionKeyChangeReq(SocketChannel c, int op){
		this.channel = c;
		this.type = op;
	}

	public final SocketChannel getChannel() {
		return channel;
	}

	public final void setChannel(SocketChannel channel) {
		this.channel = channel;
	}

	public final int getType() {
		return type;
	}

	public final void setType(int type) {
		this.type = type;
	}
	
	
	
}
