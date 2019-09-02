package com.moyan.example.j2se.base;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ViolateTest implements  Runnable {

	private static Logger logger = LoggerFactory.getLogger(ViolateTest.class);
	private  boolean flag = true;
	private  long count = 0;
	public void run() {
//		logger.info(11111);
//		for(int i =0;i<1000000;i++) {
//			if(flag) {
//				count++;
//				if(count%30000 == 0) {
////					logger.info(count);
//				}
//			}
//		}
		
		while(flag) {
			count++;
		}
		
		logger.info("count:" + count);
		
	}
	public static void main(String[] args) throws Exception {
		ViolateTest test = new ViolateTest();
		Thread thread = new Thread(test);
		thread.start();
		Thread.sleep(10);
		test.flag = false;
		Thread.sleep(1000);
		logger.info("3:"+test.count);
		logger.info("test.count:" + test.count);
		logger.info("test.flag:" + test.flag);
		logger.info("end-----");
	}
	
}
