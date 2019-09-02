package com.moyan.example.j2se.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParentClass {

	private static Logger logger = LoggerFactory.getLogger(ViolateTest.class);

	private String desc1 = "ParentClass1";
	
	protected String desc2 = "ParentClass2";
	
	public static void main(String[] args) {
		
		ParentClass parentClass = new ParentClass();
		parentClass.test1();
	}
	public void test1() {
		
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		StackTraceElement element = elements[elements.length-2];
		
		logger.info(
				this.getClass().getSimpleName() + element.getMethodName() 
				+ ".ParentClass2: " +desc2) ;
	}
}
