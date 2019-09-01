package com.moyan.example.j2se.thread;

public class VolatileTest {
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
		System.out.println("cost:" + (end-start));
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
			e.printStackTrace();
		}

//		 Runtime.getRuntime().addShutdownHook(new Thread(){
//	    	  
//	    	  public void run() {
//	    		  System.out.println("运行结果:Counter.count=" + MyObject.mycount);
//	      }
//	      });
		
		 while(Thread.activeCount()>1)  //保证前面的线程都执行完
	            Thread.yield();
		 System.out.println("运行结果:Counter.count=" + MyObject.mycount);
	}
}