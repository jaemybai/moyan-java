package com.moyan.example.nio.socket.server.sample.handler;

import com.moyan.example.j2se.base.ViolateTest;
import com.moyan.example.nio.socket.server.core.PacketDispatcher;
import com.moyan.example.nio.socket.server.data.Request;
import com.moyan.example.nio.socket.server.data.Response;
import com.moyan.example.nio.socket.server.sample.CommandEnum;
import com.moyan.example.nio.socket.server.sample.IRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenjie
 * 2013-6-13
 */
public class TestHandler implements IRequestHandler {

	private static Logger logger = LoggerFactory.getLogger(ViolateTest.class);

	@Override
	public void handleRequest(Request request) {
		
		logger.info("Test Handler Begin.....");
		
		logger.info("Params:"+request.getParams().toString());
		
		Response res = new Response(CommandEnum.RES_TEST, "haha", request.getSender());
		
		PacketDispatcher.getInstance().write(res);
	}

}
