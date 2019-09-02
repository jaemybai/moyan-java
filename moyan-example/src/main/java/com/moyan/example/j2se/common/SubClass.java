package com.moyan.example.j2se.common;


import com.moyan.example.j2se.base.ViolateTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubClass extends ParentClass{
	private static Logger logger = LoggerFactory.getLogger(ViolateTest.class);

	public static String parentStaticAttrA = "subStaticAttrA";
	public String parentAttrA = "subAttrA";
	static {
		logger.info("sub static block 1");
	}
	
	static {
		logger.info("sub static block 2");
	}
	
	{
		logger.info("sub block 1");
	}
	
	{
		logger.info("sub block 2");
	}
	
	public SubClass() {
		logger.info("sub construct without params");
	}
	
	public SubClass(String parentAttrA) {
//		super(parentAttrA);
		this.parentAttrA = parentAttrA;
		logger.info("sub construct with params:"+parentAttrA);
	}
	
	public static void main(String[] args) {
		logger.info("1SubClass.parentStaticAttrA:"+SubClass.parentStaticAttrA);
		SubClass subClass1 = new SubClass();
		logger.info("2subClass1.parentAttrA:"+subClass1.parentAttrA);
		SubClass subClass2 = new SubClass("2");
		logger.info("3subClass2.parentAttrA:"+subClass2.parentAttrA);

	}
}
