package com.moyan.example.j2se.common;

public class FinalUse {

	public static void main(String[] args) {
		
		A a = new A();
		new FinalUse().add(a);
		System.out.println(a.i);
		new FinalUse().add(a.i);
	}
	
	public void add(final A a) {
//		a = new A();
		System.out.println(++a.i);
	}
	
	public void add(final int a) {
		System.out.println(a);
	}
}


class A {
	public int i;
}