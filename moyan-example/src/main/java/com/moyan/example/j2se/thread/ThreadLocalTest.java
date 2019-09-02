package com.moyan.example.j2se.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadLocalTest {

	private static Logger logger = LoggerFactory.getLogger(ThreadLocalTest.class);

	static class ResourceClass {

		public final static ThreadLocal<String> RESOURCE_1 =
                new ThreadLocal<String>();

		public final static ThreadLocal<String> RESOURCE_2 =
									   new ThreadLocal<String>();

	}

	static class A {

		public void setOne(String value) {
			ResourceClass.RESOURCE_1.set(value);
		}

		public void setTwo(String value) {
			ResourceClass.RESOURCE_2.set(value);
		}
	}

	static class B {
		public void display() {
			logger.info(ResourceClass.RESOURCE_1.get()
						+ ":" + ResourceClass.RESOURCE_2.get());
		}
	}

	public static void main(String []args) {
		final A a = new A();
		final B b = new B();
		for(int i = 0 ; i < 15 ; i ++) {
			final String resouce1 = "线程-" + i;
			final String resouce2 = " value = (" + i + ")";
			new Thread() {
				public void run() {
				try {
					a.setOne(resouce1);
					a.setTwo(resouce2);
					b.display();
				}finally {
					ResourceClass.RESOURCE_1.remove();
					ResourceClass.RESOURCE_2.remove();
				}
			}
		}.start();
		}
	}
}