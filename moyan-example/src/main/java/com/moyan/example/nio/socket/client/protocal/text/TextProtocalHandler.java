package com.moyan.example.nio.socket.client.protocal.text;


import com.moyan.example.nio.socket.client.data.Event;
import com.moyan.example.nio.socket.client.data.Packet;
import com.moyan.example.nio.socket.client.protocal.IProtocalHandler;
import com.moyan.example.nio.socket.client.protocal.ProtocalType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenjie
 * 2013-6-13
 */
public class TextProtocalHandler implements IProtocalHandler<Event> {

	private static Logger logger = LoggerFactory.getLogger(TextProtocalHandler.class);

	@Override
	public Packet encode(Event data) {
		String id = data.getId();
		Object params = data.getParams();
		
		String resData = id + "\\:" + params.toString();
		
		Packet packet = new Packet(resData.getBytes());
		packet.setProtocalType(ProtocalType.TEXT_PROTOCAL.getType());
		
		return packet;
	}

	@Override
	public Event decode(Packet packet) {
		
		String msg = new String(packet.getData());

		String[] slices = msg.split("\\:");

		if (slices.length == 2) {
			return new Event(slices[0], slices[1]);
		} else {
			// not match the protocal
			logger.info("Drop a packet not match the protocal text");
			return null;
		}
	}

}
