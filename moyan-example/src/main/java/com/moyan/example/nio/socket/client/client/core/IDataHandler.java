package com.moyan.example.nio.socket.client.client.core;

/**
 * @author chenjie
 * 2013-6-8
 */
public interface IDataHandler {
	
	public void handleRead(byte[] data, int len);
}
