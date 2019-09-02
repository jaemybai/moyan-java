package com.moyan.example.j2se.filecopy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * It's a test class about file and stream size 
 * @author Lenovo
 *
 */
public class FileSizeTest {

	private static Logger logger = LoggerFactory.getLogger(FileSizeTest.class);

	public static void main(String[] args) throws Exception {
		logger.info("" + Integer.MAX_VALUE);

	}
	
//	public static void fileSizeTest() throws Exception{
//		InputStream in = null;
//		File srcFile = new File("F:\\电影大全\\古惑仔\\X战警.天启.X-Men.Apocalypse.2016.TC1080P.英语中字.rarbt.mp4");
//		in = new FileInputStream(srcFile);
//		byte[] bb = new byte[1000000];
//		int length = 0;
//		logger.info("file.length:" + srcFile.length());
//		int available = 0 ;
//		while(true) {
//
//			length = in.read(bb);
//			available = in.available();
//			logger.info(new String(bb));
//			logger.info("length:" + length );
//			logger.info("available:"+ available );
//			break;
//		}
//		in.close();
//		in = null;
//		logger.info("available:"+ available );
//	}
}
