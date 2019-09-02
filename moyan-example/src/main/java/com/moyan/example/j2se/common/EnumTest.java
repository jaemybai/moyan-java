package com.moyan.example.j2se.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum EnumTest {


	TEST("1"),
	TEST1("2"),
	TEST2("2"),
	TEST3("2"),
	TEST4("2");
	
	private String value = "";
	private static Logger logger = LoggerFactory.getLogger(EnumTest.class);

	private EnumTest(String value) {
		this.value = value;
	}
	
	private String getValue() {
		return this.value;
	}
	
	public static void main(String[] args) {
		EnumTest a= TEST;
		logger.info(a.getValue());
	}
	

}
