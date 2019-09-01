package com.moyan.example.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioRead {
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
				System.out.println("end------------readCount:" + readCount);
				break;
			}
			cc++;
			System.out.println("readCount: " + readCount);
			buffer.flip();
			while (buffer.remaining() > 0) {
				byte b = buffer.get();
				System.out.print(((char) b));
				count++;
			}
			buffer.clear();
		}
		System.out.println("cc:" + cc);
		fin.close();
	}
}