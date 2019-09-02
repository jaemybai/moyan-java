package com.moyan.example.j2se.common;
import com.moyan.example.j2se.base.ViolateTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Console;
import java.io.PrintWriter;


public class ShutDownHook {

	private static Logger logger = LoggerFactory.getLogger(ViolateTest.class);

	public static void main(String[] args) {
		
		logger.info("start,...");
		Runtime.getRuntime().addShutdownHook(new Thread(){
			public void run(){
				logger.info("addShutdownHook...");
			}
		});
		logger.info("end...");
		
		Console console = System.console();
		
		if(console == null){
			logger.info("null");
			return;
		}
		PrintWriter  writer = console.writer();
				writer.write("str\n");
				writer.flush();
		String read = console.readLine();
		logger.info(read);
		
		System.exit(-1);
	}
}
