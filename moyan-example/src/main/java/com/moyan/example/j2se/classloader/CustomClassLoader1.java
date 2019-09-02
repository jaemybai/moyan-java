package com.moyan.example.j2se.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

public class CustomClassLoader1 extends ClassLoader{

	private String rootPath = "";
	private static Logger logger = Logger.getLogger(ClassLoaderTest.class);

	public CustomClassLoader1(){
		super();
	}
	public CustomClassLoader1(String rootPath){
		this.rootPath = rootPath;
	}
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		
		logger.info("\n"+this.getClass().getName()+":is loading " + name);
		String absolutePath = getAsolutePath(name);
		File file  = new File(absolutePath);
		if(!file.exists()){
			logger.info(file.getAbsolutePath());
			throw new ClassNotFoundException();
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		InputStream inputStream = null;
		try {
			byte[] buff = new byte[1024*4];
			inputStream = new FileInputStream(file);
			int len = -1;
			while(true) {
				len = inputStream.read(buff);
				if(len == -1) {
					break;
				}
				outputStream.write(buff, 0, len);
			}
			outputStream.flush();
			 byte[] classData  = outputStream.toByteArray();
			return defineClass(name, classData, 0, classData.length);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(),e);
		} catch(IOException e ) {
			logger.error(e.getMessage(),e);
		}finally {
			try {
				outputStream.close();
				inputStream.close();
			}catch(Exception e) {
				logger.error(e.getMessage(),e);
				throw new ClassNotFoundException();
			}
		}
		 throw new ClassNotFoundException();  
	}

	private String getAsolutePath(String name){
		return rootPath
				+ File.separator + name.replace(".",File.separator)
				+ ".class";
	}
}
