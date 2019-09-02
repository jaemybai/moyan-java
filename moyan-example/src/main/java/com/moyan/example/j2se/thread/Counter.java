package com.moyan.example.j2se.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Counter {

    private static Logger logger = LoggerFactory.getLogger(Counter.class);

    public static int count = 0;
 
    public static void inc() {
 
        //这里延迟1毫秒，使得结果明显
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }
 
        synchronized (Counter.class) {
        	count++;
		}
    }
 
    public static void main(String[] args) {
 
        //同时启动1000个线程，去进行i++计算，看看实际结果
 
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Counter.inc();
                }
            }).start();
        }
 
      Runtime.getRuntime().addShutdownHook(new Thread(){
    	  
    	  public void run() {
    		  logger.info("运行结果:Counter.count=" + Counter.count);
      }
      });
    }
}