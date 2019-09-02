package com.moyan.example.j2se.thread;


import com.moyan.example.j2se.base.ViolateTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadTest {

	private static Logger logger = LoggerFactory.getLogger(ViolateTest.class);

	private int number1 = 0;
	
	public void test1() {
		number1 = 1;
	}
	
	public void test2() {
		number1 =  2;
	}
	
	public void check() {
		logger.info("execute method:check() " );
		
		if(number1 != 1 && number1 != 2) {
			logger.info("check test...");
		}
	}
	
	public static void main(String[] args) {
		
		final ThreadTest threadTest = new ThreadTest();
		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				while(true) {
					threadTest.test1();
				}
			}
		});
		thread1.start();
		
		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				while(true) {
					threadTest.test2();
				}
			}
		});
		thread2.start();
		
		Thread thread3 = new Thread(new Runnable() {
			public void run() {
				while(true) {
					threadTest.check();
				}
			}
		});
		thread3.start();
		
	}
}
