package com.moyan.example.j2se.common;

public class ParentClass {

	public static String parentStaticAttrA = "parentStaticAttrA";
	public String parentAttrA = "parentAttrA";
	static {
		System.out.println("parent static block 1");
	}
	
	static {
		System.out.println("parent static block 2");
	}
	
	{
		System.out.println("parent block 1");
	}
	
	{
		System.out.println("parent block 2");
	}
	
	public ParentClass() {
		System.out.println("parent construct without params");
	}
	
	public ParentClass(String parentAttrA) {
		this.parentAttrA = parentAttrA;
		System.out.println("parent construct with params:"+parentAttrA);
	}
}
