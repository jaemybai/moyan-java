package com.moyan.example.j2se.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubClass extends ParentClass{

	private static Logger logger = LoggerFactory.getLogger(SubClass.class);

	private String desc1 = "SubClass1";
	
	protected String desc2 = "SubClass2";
	
	public static void main(String[] args) {
		
		ParentClass parentClass1,parentClass2;
		SubClass subClass1,subClass2;
		
		parentClass1 = new ParentClass();
		parentClass1.test1();
		logger.info(parentClass1.desc2);
		logger.info("--------------");
		
		subClass1 = new SubClass();
		subClass1.test1();
		logger.info(subClass1.desc2);
		logger.info("--------------");
		
		parentClass2 = new SubClass();
		parentClass2.test1();
		logger.info(parentClass2.desc2);
		logger.info("--------------");
		
//		subClass2 = (SubClass) parentClass1;
//		subClass2.test1();
//		logger.info(subClass2.desc2);
	}
	public void test1() {
		
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		StackTraceElement element = elements[elements.length-2];
		
		logger.info(
				this.getClass().getSimpleName() + element.getMethodName() 
				+ ".ParentClass2: " +desc2) ;
	}
	
}
