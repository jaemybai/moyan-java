package com.moyan.example.nio.socket.client.client.core;


import com.alibaba.fastjson.JSON;
import com.moyan.example.nio.socket.client.data.Event;
import com.moyan.example.nio.socket.client.data.Packet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenjie 2013-6-8
 */
public class DefaultDataHandler implements IDataHandler, Runnable {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private IClient client;

	public DefaultDataHandler(IClient client) {
		this.client = client;
	}

	@Override
	public void run() {

		int count = 1;
		while (true) {

			Packet packet = this.client.getReadData().poll();

			while (packet != null) {
				Event event = client.getProtocalHandler().decode(packet);
				logger.info("start read a event,event:{}", JSON.toJSONString(event));
				packet = this.client.getReadData().poll();

			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				logger.error(e.getMessage(),e);
			}

			if(++count <0) {
				this.client.send(this.getClass().getName() + "Hi server " + count);
			}
		}

	}


	@Override
	public void handleRead(byte[] data, int len) {

		byte[] readed = new byte[len];

		System.arraycopy(data, 0, readed, 0, len);
		logger.info("read a data,data : {}", new String(readed));
		this.client.getReadData().add(new Packet(readed));

	}
}
