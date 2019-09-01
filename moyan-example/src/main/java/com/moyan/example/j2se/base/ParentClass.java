package com.moyan.example.j2se.base;

public class ParentClass {

	
	private String desc1 = "ParentClass1";
	
	protected String desc2 = "ParentClass2";
	
	public static void main(String[] args) {
		
		ParentClass parentClass = new ParentClass();
		parentClass.test1();
	}
	public void test1() {
		
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		StackTraceElement element = elements[elements.length-2];
		
		System.out.println(
				this.getClass().getSimpleName() + element.getMethodName() 
				+ ".ParentClass2: " +desc2) ;
	}
}
