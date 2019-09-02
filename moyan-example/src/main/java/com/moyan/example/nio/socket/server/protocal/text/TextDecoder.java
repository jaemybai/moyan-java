package com.moyan.example.nio.socket.server.protocal.text;

import com.moyan.example.j2se.base.ViolateTest;
import com.moyan.example.nio.socket.server.data.Packet;
import com.moyan.example.nio.socket.server.data.Request;
import com.moyan.example.nio.socket.server.data.Session;
import com.moyan.example.nio.socket.server.manager.SessionManager;
import com.moyan.example.nio.socket.server.protocal.IDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 解析[命令;参数]格式的协议
 * 
 * @author chenjie 2013-6-8
 */
public class TextDecoder implements IDecoder<Request> {

	private static Logger logger = LoggerFactory.getLogger(TextDecoder.class);

	@Override
	public Request decode(Packet packet) {

		String msg = new String(packet.getData());

		String[] slices = msg.split("\\:");

		if (slices.length == 2) {
			Session sender = SessionManager.getInstance().getSession(packet.getSender());
			return new Request(slices[0], slices[1], sender);
		} else {
			return null;
		}

	}
}
