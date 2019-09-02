package com.moyan.example.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioRead {

	private static Logger logger = LoggerFactory.getLogger(NioRead.class);

	static public void main(String args[]) throws Exception {
		FileInputStream fin = new FileInputStream("test.log");

		// 获取通道
		FileChannel fc = fin.getChannel();

		// 创建缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(300);

		// 读取数据到缓冲区
		int readCount = 0;

		int cc = 0;
		int count = 0;
		while (true) {
			readCount = fc.read(buffer);
			if (readCount <= 0) {
				logger.info("end------------readCount:" + readCount);
				break;
			}
			cc++;
			logger.info("readCount: " + readCount);
			buffer.flip();
			while (buffer.remaining() > 0) {
				byte b = buffer.get();
				System.out.print(((char) b));
				count++;
			}
			buffer.clear();
		}
		logger.info("cc:" + cc);
		fin.close();
	}
}