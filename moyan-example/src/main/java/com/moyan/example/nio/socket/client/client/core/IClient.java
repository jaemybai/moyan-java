package com.moyan.example.nio.socket.client.client.core;


import com.moyan.example.nio.socket.client.data.Event;
import com.moyan.example.nio.socket.client.data.Packet;
import com.moyan.example.nio.socket.client.protocal.IProtocalHandler;
import com.moyan.example.nio.socket.client.protocal.text.TextProtocalHandler;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author chenjie 2013-6-8
 */
public class IClient implements Runnable {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private ByteBuffer readBuffer; // ��ȡ����

	private Selector selector;

	private SocketChannel client;

	private Queue<ByteBuffer> pendingData; // ��д�������
	private Queue<Packet> readData; // ��ȡ������

	private IDataHandler dataHandler; // ���ݴ���

	private IProtocalHandler<Event> protocalHandler; // Э�鴦��

	public IClient() {
		this.readBuffer = ByteBuffer.allocate(2048);
		this.pendingData = new LinkedBlockingQueue<ByteBuffer>();
		this.readData = new LinkedBlockingQueue<Packet>();
		
		this.protocalHandler = new TextProtocalHandler();
		
		DefaultDataHandler handler = new DefaultDataHandler(this);
		this.dataHandler = handler;
		new Thread(handler).start();
	}

	public void init(int port) {
		try {
			this.selector = Selector.open();
			this.client = SocketChannel.open();
			this.client.configureBlocking(false);
			this.client.register(this.selector, SelectionKey.OP_CONNECT);
			this.client.connect(new InetSocketAddress(port));
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}

	// ��������
	public void send(String data) {

		this.addWritableData(data.getBytes());
	}

	// �����Ҫд�������
	public void addWritableData(byte[] data) {

		this.pendingData.add(ByteBuffer.wrap(data));
		logger.info("begin to write data and wakeup, data:{},", new String(data));
		this.selector.wakeup();
	}

	// �������
	private void finishConnection(SelectionKey key) {
		SocketChannel channel = (SocketChannel) key.channel();
		try {
			channel.finishConnect();
		} catch (IOException e) {
			key.cancel();
			logger.error(e.getMessage(),e);
		}
		key.interestOps(SelectionKey.OP_WRITE);
	}

	// д����
	private void write(SelectionKey key) {

		SocketChannel channel = (SocketChannel) key.channel();
		if(CollectionUtils.isEmpty(this.pendingData)) {
			logger.info("this is no data to write");
		}
		while (!this.pendingData.isEmpty()) {
			ByteBuffer buffer = this.pendingData.poll();
			try {
				channel.write(buffer);
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
		}
		key.interestOps(SelectionKey.OP_READ);
	}

	// ��ȡ����
	private void read(SelectionKey key) {
		this.readBuffer.clear();
		SocketChannel channel = (SocketChannel) key.channel();
		try {
			int size = channel.read(this.readBuffer);

			if (size == -1) {
				logger.info("this socket has no data transfer,no cacel it.");
				key.cancel();
				return;
			}

			if (this.dataHandler != null) {
				this.dataHandler.handleRead(this.readBuffer.array(), size);
			} else {
				logger.info("The data handler not specified");
			}

		} catch (IOException e) {
			key.cancel();
			logger.error(e.getMessage(),e);
		}

		key.interestOps(SelectionKey.OP_WRITE);
	}

	@Override
	public void run() {

		while (true) {

			try {

				int num = this.selector.select();
				Set<SelectionKey> keys = this.selector.selectedKeys();
				logger.info("select return code :{}, size:{}", num, keys.size());

				Iterator<SelectionKey> keyItors = keys.iterator();
				while (keyItors.hasNext()) {
					SelectionKey key = keyItors.next();
					if (!key.isValid()) {
						continue;
					}

					if (key.isConnectable()) {
						// finishConnection
						logger.info("connect sucessfully,opt:{},readyOps:{}",
								key.interestOps(),key.readyOps());
						this.finishConnection(key);
					} else if (key.isReadable()) {
						// read data
						logger.info("start read a socket,opt:{},readyOps:{}",
								key.interestOps(),key.readyOps());
						this.read(key);
					} else if (key.isWritable()) {
						// write data
						logger.info("start write a socket,opt:{},readyOps:{}",
								key.interestOps(),key.readyOps());
						this.write(key);
					}
				}

			} catch (IOException e) {
				try {
					this.selector.close();
				} catch (IOException e1) {
					logger.error(e.getMessage(),e);
				}
			}

		}

	}

	public final Queue<Packet> getReadData() {
		return readData;
	}
	
	public final IProtocalHandler<Event> getProtocalHandler(){
		
		return this.protocalHandler;
	}
	

}
