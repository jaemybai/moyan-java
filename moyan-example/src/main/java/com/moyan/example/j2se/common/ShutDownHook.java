package com.moyan.example.j2se.common;
import java.io.Console;
import java.io.PrintWriter;


public class ShutDownHook {

	
	public static void main(String[] args) {
		
		System.out.println("start,...");
		Runtime.getRuntime().addShutdownHook(new Thread(){
			public void run(){
				System.out.println("addShutdownHook...");
			}
		});
		System.out.println("end...");
		
		Console console = System.console();
		
		if(console == null){
			System.out.println("null");
			return;
		}
		PrintWriter  writer = console.writer();
				writer.write("str\n");
				writer.flush();
		String read = console.readLine();
		System.out.println(read);
		
		System.exit(-1);
	}
}
