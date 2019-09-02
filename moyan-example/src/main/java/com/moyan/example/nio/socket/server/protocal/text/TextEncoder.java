package com.moyan.example.nio.socket.server.protocal.text;

import com.moyan.example.nio.socket.server.data.Packet;
import com.moyan.example.nio.socket.server.data.Response;
import com.moyan.example.nio.socket.server.data.Session;
import com.moyan.example.nio.socket.server.protocal.IEncoder;
import com.moyan.example.nio.socket.server.protocal.ProtocalType;

import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjie
 * 2013-6-9
 */
public class TextEncoder implements IEncoder<Response> {

	@Override
	public Packet encode(Response data) {
		String id = data.getId();
		Object params = data.getParams();
		
		String resData = id + "\\:" + params.toString();
		
		Packet packet = new Packet(resData.getBytes());
		packet.setProtocalType(ProtocalType.TEXT_PROTOCAL.getType());
		
		List<SocketChannel> recipients = new ArrayList<SocketChannel>();
		for(Session session : data.getRecipients()){
			recipients.add(session.getChannel());
		}
		
		return packet;
	}

}
