package com.moyan.example.nio.socket.server.sample;


import com.moyan.example.nio.socket.server.data.Request;

/**
 * @author chenjie
 * 2013-6-13
 */
public interface IRequestHandler {

	public void handleRequest(Request request);
	
}
