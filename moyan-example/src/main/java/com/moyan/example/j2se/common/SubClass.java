package com.moyan.example.j2se.common;


public class SubClass extends ParentClass{

	public static String parentStaticAttrA = "subStaticAttrA";
	public String parentAttrA = "subAttrA";
	static {
		System.out.println("sub static block 1");
	}
	
	static {
		System.out.println("sub static block 2");
	}
	
	{
		System.out.println("sub block 1");
	}
	
	{
		System.out.println("sub block 2");
	}
	
	public SubClass() {
		System.out.println("sub construct without params");
	}
	
	public SubClass(String parentAttrA) {
//		super(parentAttrA);
		this.parentAttrA = parentAttrA;
		System.out.println("sub construct with params:"+parentAttrA);
	}
	
	public static void main(String[] args) {
		System.out.println("1SubClass.parentStaticAttrA:"+SubClass.parentStaticAttrA);
		SubClass subClass1 = new SubClass();
		System.out.println("2subClass1.parentAttrA:"+subClass1.parentAttrA);
		SubClass subClass2 = new SubClass("2");
		System.out.println("3subClass2.parentAttrA:"+subClass2.parentAttrA);

	}
}
