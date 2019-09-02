package com.moyan.example.nio.socket.server.core;

import com.moyan.example.nio.socket.server.config.ServerDefine;
import com.moyan.example.nio.socket.server.data.Request;
import com.moyan.example.nio.socket.server.data.Response;
import com.moyan.example.nio.socket.server.manager.SessionManager;
import com.moyan.example.nio.socket.server.protocal.IDecoder;
import com.moyan.example.nio.socket.server.protocal.IEncoder;
import com.moyan.example.nio.socket.server.protocal.text.TextDecoder;
import com.moyan.example.nio.socket.server.protocal.text.TextEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author chenjie 2013-6-7
 */
public class IServer implements Runnable {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private Selector selector;

	private IDataHandler dataHandler; // 数据包处理

	private SessionManager sessionManager; // 会话管理

	private Queue<SelectionKeyChangeReq> keyChangeReqList; // 请求改变通道的感兴趣事件
	private Map<SocketChannel, Queue<byte[]>> pendingData; // 待写入的数据
	
	private IDecoder<Request> decoder;	//协议解析
	private IEncoder<Response> encoder; //协议解析

	public IServer() {
		init();
	}

	public void init() {
		this.sessionManager = SessionManager.getInstance();
		this.dataHandler = new DefaultDataHandler(this);

		this.keyChangeReqList = new LinkedBlockingQueue<SelectionKeyChangeReq>();
		this.pendingData = new ConcurrentHashMap<SocketChannel, Queue<byte[]>>();
		
		this.decoder = new TextDecoder();
		this.encoder = new TextEncoder();
	}

	public void bind(int port) {
		try {
			selector = Selector.open();

			ServerSocketChannel server = ServerSocketChannel.open();
			server.configureBlocking(false);
			server.socket().bind(new InetSocketAddress(port));
			server.socket().setReuseAddress(true);
			server.register(this.selector, SelectionKey.OP_ACCEPT);

		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}

	}

	/**
	 * 上层调用该接口写入数据
	 * 
	 * @param channel
	 * @param data
	 */
	public void addWritableData(SocketChannel channel, byte[] data) {

		logger.info("start write date to pendingData queue...,chanel:{}", channel.keyFor(selector).interestOps());
		SelectionKeyChangeReq req = new SelectionKeyChangeReq(channel, SelectionKey.OP_WRITE);
		this.keyChangeReqList.add(req);

		Queue<byte[]> queue = this.pendingData.get(channel);
		if (queue == null) {
			queue = new LinkedBlockingQueue<byte[]>();
			this.pendingData.put(channel, queue);

		}

		queue.add(data);

		this.selector.wakeup(); // 通知有新的通道可写
	}

	/**
	 * 注册新的连接
	 * 
	 * @param key
	 * @throws IOException
	 */
	private void acceptNewSocket(SelectionKey key) throws IOException {
		ServerSocketChannel server = (ServerSocketChannel) key.channel();
		SocketChannel client = server.accept();
		client.configureBlocking(false);
		SelectionKey keySelected = client.register(this.selector, SelectionKey.OP_READ);

		this.sessionManager.addNewSession(client, keySelected);
	}

	/**
	 * 读取数据
	 * 
	 * @param key
	 * @throws IOException
	 */
	private void read(ByteBuffer readBuffer, SelectionKey key) throws IOException {

		SocketChannel client = (SocketChannel) key.channel();
		try {
			readBuffer.clear();

			int size = client.read(readBuffer);

			if (size == -1) {
				logger.info("this socket has no data,so cancel it .");
				key.channel().close();
				key.cancel();
				return;
			}

			if (this.dataHandler != null) {
				this.dataHandler.handleRead(client, readBuffer.array(), size);
			} else {
				// data handler not exists;
				logger.info("The data handler not specified");
			}

		} catch (IOException e) {
			key.channel().close();
			key.cancel();
			logger.error(e.getMessage(),e);
		}

	}

	/**
	 * 写数据
	 * 
	 * @param key
	 */
	private void write(ByteBuffer writeBuffer, SelectionKey key) {
		SocketChannel client = (SocketChannel) key.channel();
		Queue<byte[]> queue = this.pendingData.get(client);
		if (queue != null) {
			while (!queue.isEmpty()) {
				writeBuffer.clear();
				byte[] buffer = queue.poll();
				writeBuffer.put(buffer);
				writeBuffer.flip();
				try {
					client.write(writeBuffer);
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
			}

		} else {
			logger.info("this is no data to write...");
		}
		
		key.interestOps(SelectionKey.OP_READ);	//写完了,将其设为可读

	}

	//注册感兴趣事件
	private void handleSelectionKeyChangeReqs() {

		while (!this.keyChangeReqList.isEmpty()) {

			SelectionKeyChangeReq req = this.keyChangeReqList.poll();

			SelectionKey key = req.getChannel().keyFor(this.selector);
			key.interestOps(req.getType());
		}

		this.keyChangeReqList.clear();
	}

	@Override
	public void run() {

		ByteBuffer writeBuffer = ByteBuffer.allocate(ServerDefine.WRITE_BUFFER_SIZE);
		ByteBuffer readBuffer = ByteBuffer.allocate(ServerDefine.READ_BUFFER_SIZE);
		while (true) {

			// handle the changed key op req;
			handleSelectionKeyChangeReqs();

			try {
				int num = this.selector.select();
				Set<SelectionKey> keySet = this.selector.selectedKeys();
				logger.info("select return code : {}, size:{}", num, keySet.size());
				Iterator<SelectionKey> keyItor = keySet.iterator();
				while (keyItor.hasNext()) {
					SelectionKey key = keyItor.next();
					keyItor.remove();

					if (!key.isValid()) {
						continue;
					}

					if (key.isAcceptable()) {
						logger.info("accept a socket connection,opt:{},readyOps:{}",
								key.interestOps(),key.readyOps());
						this.acceptNewSocket(key);

					} else if (key.isReadable()) {
						logger.info("accept a read connection,opt:{},readyOps:{}",
								key.interestOps(),key.readyOps());
						this.read(readBuffer, key);
					}

					else if (key.isWritable()) {
						logger.info("accept a write connection,opt:{},readyOps:{}",
								key.interestOps(),key.readyOps());
						this.write(writeBuffer, key);
					}
				}

			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
		}

	}

	public final IDataHandler getDataHandler() {
		return dataHandler;
	}

	public final IDecoder<Request> getDecoder() {
		return decoder;
	}

	public final IEncoder<Response> getEncoder() {
		return encoder;
	}
	
	

}
