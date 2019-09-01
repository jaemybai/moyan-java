package com.moyan.example.j2se.base;

public class AbstractImpl extends AbstractClass{

	private int abstractA = 2;
	public static void main(String[] args) {
		
		AbstractClass abstractClass,abstractClass2;
		AbstractImpl impl1,impl2;
		 abstractClass = new AbstractImpl();
		 impl1 = new AbstractImpl();
		 impl2 = (AbstractImpl) abstractClass;
		System.out.println(abstractClass.abstractA);
		System.out.println(impl1.abstractA);
		abstractClass.test1();
		abstractClass.test2();

		impl1.test1();
		impl1.test2();

		System.out.println(impl2.abstractA);
		impl2.test1();
		impl2.test2();

	}
	
	void test1() {
		System.out.println("AbstractImpl.test1-abstractA: " + abstractA);
	}
	@Override
	void test2() {
		System.out.println("AbstractImpl.test2-abstractA: " + abstractA);
	}
	
	

}
