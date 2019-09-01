package com.moyan.example.j2se.base;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public  class HashMapTest {

	
	public static void main(String[] args) throws Exception {
		HashMapTest hashMapTest = new HashMapTest();
		hashMapTest.test();
		
	}
	
	
	void test() {
		
		Map map = new ConcurrentHashMap();
		System.out.println(map.get("test"));
	}
}
