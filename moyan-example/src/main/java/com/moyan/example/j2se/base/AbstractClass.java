package com.moyan.example.j2se.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractClass {

	private static Logger logger = LoggerFactory.getLogger(AbstractClass.class);
	public AbstractClass(){
		
	}
	protected int abstractA = 1;
		
		private int abstractB = 1;

		abstract void test1() ;
		
		void test2() {
			logger.info("AbstractClass.test2-abstractA: " + abstractA);
		}
}
