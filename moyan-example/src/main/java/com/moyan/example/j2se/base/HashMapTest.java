package com.moyan.example.j2se.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public  class HashMapTest {

	private static Logger logger = LoggerFactory.getLogger(HashMapTest.class);
	
	public static void main(String[] args) throws Exception {
		HashMapTest hashMapTest = new HashMapTest();
		hashMapTest.test();
		
	}
	
	
	void test() {
		
		Map map = new ConcurrentHashMap();
		logger.info("" + map.get("test"));
	}
}
