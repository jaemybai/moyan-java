package com.moyan.example.j2se.base;



public class ViolateTest implements  Runnable {
	
	private  boolean flag = true;
	private  long count = 0;
	public void run() {
//		System.out.println(11111);
//		for(int i =0;i<1000000;i++) {
//			if(flag) {
//				count++;
//				if(count%30000 == 0) {
////					System.out.println(count);
//				}
//			}
//		}
		
		while(flag) {
			count++;
		}
		
		System.out.println("count:" + count);
		
	}
	public static void main(String[] args) throws Exception {
		ViolateTest test = new ViolateTest();
		Thread thread = new Thread(test);
		thread.start();
		Thread.sleep(10);
		test.flag = false;
		Thread.sleep(1000);
		System.out.println("3:"+test.count);
		System.out.println("test.count:" + test.count);
		System.out.println("test.flag:" + test.flag);
		System.out.println("end-----");
	}
	
}
