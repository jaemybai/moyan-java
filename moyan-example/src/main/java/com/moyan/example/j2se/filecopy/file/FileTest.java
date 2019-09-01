package com.moyan.example.j2se.filecopy.file;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTest {

	public static void main(String[] args) {
		
		Path path = Paths.get("src", "log4j.properties");
		System.out.println(path.toFile().getAbsolutePath());
		System.out.println(path.toFile().exists());
		
//		System.out.println(Files.exists(path, null));
		
//		Files.copy(path, target, options)
	}
}
