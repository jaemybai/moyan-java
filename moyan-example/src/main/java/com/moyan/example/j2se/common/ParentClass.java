package com.moyan.example.j2se.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParentClass {

	private static Logger logger = LoggerFactory.getLogger(ParentClass.class);

	public static String parentStaticAttrA = "parentStaticAttrA";
	public String parentAttrA = "parentAttrA";
	static {
		logger.info("parent static block 1");
	}
	
	static {
		logger.info("parent static block 2");
	}
	
	{
		logger.info("parent block 1");
	}
	
	{
		logger.info("parent block 2");
	}
	
	public ParentClass() {
		logger.info("parent construct without params");
	}
	
	public ParentClass(String parentAttrA) {
		this.parentAttrA = parentAttrA;
		logger.info("parent construct with params:"+parentAttrA);
	}
}
