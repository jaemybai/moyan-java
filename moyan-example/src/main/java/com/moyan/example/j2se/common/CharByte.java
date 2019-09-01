package com.moyan.example.j2se.common;


public class CharByte {

	public static void main(String[] args) throws Exception {
		
		String a = "214";
		a.getBytes();
		for(byte bb : a.getBytes()) {
			System.out.print(bb);
			System.out.print("\t");
		}
		System.out.println(a.getBytes().length);
		
		for(char cc: a.toCharArray()) {
			System.out.print(cc);
			System.out.print("byte:"+(byte)cc+"int:"+(int)cc);
			System.out.print("\t");
		}
		System.out.println(a.toCharArray().length);
		
		Class<?> cla = Class.forName("java.lang.StringCoding");
		cla.newInstance();
	}
}
