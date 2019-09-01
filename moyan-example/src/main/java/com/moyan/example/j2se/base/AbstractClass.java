package com.moyan.example.j2se.base;

public abstract class AbstractClass {

	public AbstractClass(){
		
	}
	protected int abstractA = 1;
		
		private int abstractB = 1;

		abstract void test1() ;
		
		void test2() {
			System.out.println("AbstractClass.test2-abstractA: " + abstractA);
		}
}
