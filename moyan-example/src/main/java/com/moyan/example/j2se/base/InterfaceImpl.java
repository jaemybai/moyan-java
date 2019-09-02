package com.moyan.example.j2se.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterfaceImpl implements InterfaceTest{

	private static Logger logger = LoggerFactory.getLogger(ViolateTest.class);

	public static void main(String[] args) {
		logger.info("" + variable);
	}
	@Override
	public int test1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void test2() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void test3() {
		// TODO Auto-generated method stub
		
	}
	
}
