package com.moyan.example.j2se.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadLocalObjectTest {

	private static Logger logger = LoggerFactory.getLogger(ThreadLocalObjectTest.class);

	public static  final ThreadLocal<ThreadLocalObjectTest> threadLocal =
			new ThreadLocal<ThreadLocalObjectTest>();
	 int i = 0;


	public static void main(String []args) {
		final ThreadLocalObjectTest localObjectTest = new ThreadLocalObjectTest();
		for(int i = 0 ; i < 200 ; i ++) {
			new Thread() {
				public void run() {
				try {
//					threadLocal.set(localObjectTest);
//					ThreadLocalObjectTest test = threadLocal.get();
					localObjectTest.i = localObjectTest.i + 1;
					logger.info(this.getName() + "_" + localObjectTest.i);
				}finally {

				}
			}
		}.start();
		}
	}
}