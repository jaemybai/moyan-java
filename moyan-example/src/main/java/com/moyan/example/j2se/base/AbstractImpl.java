package com.moyan.example.j2se.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractImpl extends AbstractClass{

	private static Logger logger = LoggerFactory.getLogger(ViolateTest.class);

	private int abstractA = 2;
	public static void main(String[] args) {
		
		AbstractClass abstractClass,abstractClass2;
		AbstractImpl impl1,impl2;
		 abstractClass = new AbstractImpl();
		 impl1 = new AbstractImpl();
		 impl2 = (AbstractImpl) abstractClass;
		logger.info("" + abstractClass.abstractA);
		logger.info("" + impl1.abstractA);
		abstractClass.test1();
		abstractClass.test2();

		impl1.test1();
		impl1.test2();

		logger.info("" + impl2.abstractA);
		impl2.test1();
		impl2.test2();

	}
	
	void test1() {
		logger.info("AbstractImpl.test1-abstractA: " + abstractA);
	}
	@Override
	void test2() {
		logger.info("AbstractImpl.test2-abstractA: " + abstractA);
	}
	
	

}
