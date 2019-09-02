package com.moyan.example.j2se.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharByte {

	private static Logger logger = LoggerFactory.getLogger(CharByte.class);

	public static void main(String[] args) throws Exception {
		
		String a = "214";
		a.getBytes();
		for(byte bb : a.getBytes()) {
			System.out.print(bb);
			System.out.print("\t");
		}
		logger.info("" + a.getBytes().length);
		
		for(char cc: a.toCharArray()) {
			System.out.print(cc);
			System.out.print("byte:"+(byte)cc+"int:"+(int)cc);
			System.out.print("\t");
		}
		logger.info("" + a.toCharArray().length);
		
		Class<?> cla = Class.forName("java.lang.StringCoding");
		cla.newInstance();
	}
}
