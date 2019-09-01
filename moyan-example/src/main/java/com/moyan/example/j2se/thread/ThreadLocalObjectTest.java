package com.moyan.example.j2se.thread;

public class ThreadLocalObjectTest {

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
					System.out.println(this.getName() + "_" + localObjectTest.i);
				}finally {

				}
			}
		}.start();
		}
	}
}