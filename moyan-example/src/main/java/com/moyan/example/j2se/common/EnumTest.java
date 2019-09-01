package com.moyan.example.j2se.common;

public enum EnumTest {
	
	TEST("1"),
	TEST1("2"),
	TEST2("2"),
	TEST3("2"),
	TEST4("2");
	
	private String value = "";
	
	private EnumTest(String value) {
		this.value = value;
	}
	
	private String getValue() {
		return this.value;
	}
	
	public static void main(String[] args) {
		EnumTest a= TEST;
		System.out.println(a.getValue());
	}
	

}
