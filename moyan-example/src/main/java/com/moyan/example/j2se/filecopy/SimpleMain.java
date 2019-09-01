package com.moyan.example.j2se.filecopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * siple file coping processing class 
 * @author Lenovo
 *
 */
public class SimpleMain {

	public static void main(String[] args) throws Exception {
		
		String srcDir = "G:\\Program Files\\download\\xunlei";
		String desDir = "F:\\电影大全\\古惑仔";
		boolean onlyOne = false;
		boolean needDelete = false;
		InputStream in = null;
		OutputStream out = null;
		File srcFile = new File(srcDir);
		File[] allHandlingFile = srcFile.listFiles();
		byte[] data = new byte[1000];
		int lengh = 0;
		long startTime = 0;
		long endTime = 0;
		long totalTime = 0;
		File desFile = null;
		long fileSize = 0;
		for(File oneFile : allHandlingFile) {
			
			desFile = new File(desDir, oneFile.getName());
			if(desFile.exists()) {
				System.out.printf("the file[%s] exists ,no need to copy.",desFile.getAbsolutePath());
				continue;
			}
			in = new FileInputStream(oneFile);
			out = new FileOutputStream(desFile);
			startTime = System.currentTimeMillis();
			while(true) {
				lengh = in.read(data);
				if(lengh<0) {
					break;
				}
				fileSize = fileSize + lengh;
				out.write(data, 0, lengh);
			}
			out.flush();
			out.close();
			in.close();
			in = null;
			out = null;
			
			endTime = System.currentTimeMillis();
			System.out.printf("it costs [%s] seconds\n that the file[%s] has copy to the dir[%s]",
					(endTime-startTime)/1000,oneFile,desDir);
			System.out.println();
			totalTime += (endTime-startTime)/1000;
			
			if(needDelete) {
				if(oneFile.delete()) {
					System.out.printf("the file[%s] deleted", oneFile.getAbsolutePath());
				} 
				else {
					System.out.printf("the file[%s] failed to delete", oneFile.getAbsolutePath());
				}
				System.out.println();
			}
			else {
				System.out.println("the file no need to be deleted");
			}
			if(onlyOne) {
				break;
			}
		}
		System.out.printf("it cost total time is {%s} seconds ",totalTime);
		System.out.println();
	}
	
	
}
