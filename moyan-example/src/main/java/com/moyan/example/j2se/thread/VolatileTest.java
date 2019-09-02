package com.moyan.example.j2se.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VolatileTest {

	private static Logger logger = LoggerFactory.getLogger(VolatileTest.class);

	static class MyObject {
		static volatile int mycount = 0;
	}

	public static void inc() {
		MyObject.mycount++;
	}

	public static void main(String[] args) {
		long start,end;
		start = System.currentTimeMillis();
		for(int i=0;i<10;i++) {
			MyObject.mycount = 0;
			main1(args);
		}
		end = System.currentTimeMillis();
		logger.info("cost:" + (end-start));
	}
	public static void main1(String[] args) {

		// 同时启动1000个线程，去进行i++计算，看看实际结果

		for (int i = 0; i < 10000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					inc();
				}
			}).start();
		}
		
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(),e);
		}

//		 Runtime.getRuntime().addShutdownHook(new Thread(){
//	    	  
//	    	  public void run() {
//	    		  logger.info("运行结果:Counter.count=" + MyObject.mycount);
//	      }
//	      });
		
		 while(Thread.activeCount()>1)  //保证前面的线程都执行完
	            Thread.yield();
		 logger.info("运行结果:Counter.count=" + MyObject.mycount);
	}
}