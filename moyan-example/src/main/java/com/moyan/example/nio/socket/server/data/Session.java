package com.moyan.example.nio.socket.server.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author chenjie
 * 2013-6-8
 */
public class Session {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private SocketChannel channel;
	private SelectionKey selectionKey;
	
	private BlockingQueue<Packet> readQueue;	//������
	private BlockingQueue<Packet> writeQueue;	//д����
	
	public Session(SocketChannel client, SelectionKey key){
		this.channel = client;
		this.selectionKey = key;
		this.readQueue = new LinkedBlockingQueue<Packet>();
		this.writeQueue = new LinkedBlockingQueue<Packet>();
	}
	
	//���ӿɶ��İ�
	public void addReadablePacket(Packet packet){
		try {
			this.readQueue.put(packet);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	//��ȡһ���ɶ��İ�
	public Packet popReadablePacket(){
		return this.readQueue.poll();
	}
	
	//���ӿ�д�İ�
	public void addWritablePacket(Packet packet){
		try {
			this.writeQueue.put(packet);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	//��ȡһ����д�İ�
	public Packet popWritablePacket(){
		return this.writeQueue.poll();
	}

	public final SocketChannel getChannel() {
		return channel;
	}

	public final void setChannel(SocketChannel channel) {
		this.channel = channel;
	}

	public final SelectionKey getSelectionKey() {
		return selectionKey;
	}

	public final void setSelectionKey(SelectionKey selectionKey) {
		this.selectionKey = selectionKey;
	}

	public final BlockingQueue<Packet> getReadQueue() {
		return readQueue;
	}

	public final void setReadQueue(BlockingQueue<Packet> readQueue) {
		this.readQueue = readQueue;
	}

	public final BlockingQueue<Packet> getWriteQueue() {
		return writeQueue;
	}

	public final void setWriteQueue(BlockingQueue<Packet> writeQueue) {
		this.writeQueue = writeQueue;
	}



	
}
