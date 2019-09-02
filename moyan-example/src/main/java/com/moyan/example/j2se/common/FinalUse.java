package com.moyan.example.j2se.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FinalUse {

	private static Logger logger = LoggerFactory.getLogger(FinalUse.class);

	public static void main(String[] args) {
		
		A a = new A();
		new FinalUse().add(a);
		logger.info("" + a.i);
		new FinalUse().add(a.i);
	}
	
	public void add(final A a) {
//		a = new A();
		logger.info("" + ++a.i);
	}
	
	public void add(final int a) {
		logger.info("" + a);
	}
}


class A {
	public int i;
}