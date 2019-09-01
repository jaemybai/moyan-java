package com.moyan.example.j2se.base;

public class SubClass extends ParentClass{

	private String desc1 = "SubClass1";
	
	protected String desc2 = "SubClass2";
	
	public static void main(String[] args) {
		
		ParentClass parentClass1,parentClass2;
		SubClass subClass1,subClass2;
		
		parentClass1 = new ParentClass();
		parentClass1.test1();
		System.out.println(parentClass1.desc2);
		System.out.println("--------------");
		
		subClass1 = new SubClass();
		subClass1.test1();
		System.out.println(subClass1.desc2);
		System.out.println("--------------");
		
		parentClass2 = new SubClass();
		parentClass2.test1();
		System.out.println(parentClass2.desc2);
		System.out.println("--------------");
		
//		subClass2 = (SubClass) parentClass1;
//		subClass2.test1();
//		System.out.println(subClass2.desc2);
	}
	public void test1() {
		
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		StackTraceElement element = elements[elements.length-2];
		
		System.out.println(
				this.getClass().getSimpleName() + element.getMethodName() 
				+ ".ParentClass2: " +desc2) ;
	}
	
}
