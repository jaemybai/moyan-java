package com.moyan.example.nio.socket.server.protocal;


import com.moyan.example.nio.socket.server.data.Packet;

/**
 * @author chenjie
 * 2013-6-8
 */
public interface IDecoder<T> {

	public T decode(Packet packet);
	
}
