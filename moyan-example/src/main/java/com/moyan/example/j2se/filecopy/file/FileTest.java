package com.moyan.example.j2se.filecopy.file;

import com.moyan.example.j2se.base.ViolateTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTest {

	private static Logger logger = LoggerFactory.getLogger(ViolateTest.class);


	public static void main(String[] args) {
		
		Path path = Paths.get("src", "log4j.properties");
		logger.info(path.toFile().getAbsolutePath());
		logger.info("" + path.toFile().exists());
		
//		logger.info(Files.exists(path, null));
		
//		Files.copy(path, target, options)
	}
}
