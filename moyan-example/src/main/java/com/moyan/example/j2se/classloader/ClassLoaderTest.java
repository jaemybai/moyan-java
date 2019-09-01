package com.moyan.example.j2se.classloader;

import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;

public class ClassLoaderTest {

	private static Logger logger = Logger.getLogger(ClassLoaderTest.class);
	String root = "G://Program Files//workspace//Test//target//classes";
	String className = "com.xlbai.base.j2se.classloader.Test";
	String parClassName3 = "com.xlbai.base.j2se.classloader.ParentClass";
	String subClassName3 = "com.xlbai.base.j2se.classloader.SubClass";
	String methodStr = "test3";
	public static void main(String[] args) throws Exception {
		ClassLoaderTest main = new ClassLoaderTest();
//		main.getBootstrapClassPath();
//		logger.info("-------------------------");
//		logger.info(System.getProperty("sun.boot.class.path").replace(";", "//n"));  
//		logger.info("-------------------------");
//		ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
//		classLoader.loadClass("java.lang.Class");
//		main.getAllClassLoader();
		main.test2();
	}
	
	public void test2() throws ClassNotFoundException, Exception {
		root = "G://Program Files//j2se-workspace//JvmTest1.6//bin";
		String fieldStr = "parentClass";
		String methodStr = "test1";
		CustomClassLoader1 customClassLoader1 = new CustomClassLoader1(root);
		CustomClassLoader1 customClassLoader2 = new CustomClassLoader1(root);
		Class<?> clazz1= customClassLoader1.loadClass(className);
		Class<?> parClass1 = customClassLoader1.loadClass(parClassName3);
		Class<?> subClass1 = customClassLoader1.loadClass(subClassName3);
		Class<?> parClass2 = customClassLoader2.loadClass(parClassName3);
		Class<?> subClass2 = customClassLoader2.loadClass(subClassName3);
		Object obj1 = clazz1.newInstance();
//		Object field1 = getObj(clazz1, fieldStr, obj1);
//		System.out.println(field1.getClass().getClassLoader());
		Method method = clazz1.getDeclaredMethod(methodStr, parClass1);
		method.invoke(obj1, parClass2.newInstance());
//		callMethod(clazz1, methodStr, obj1,parClass1.newInstance());
	}
	
	public void test1() throws ClassNotFoundException, Exception {
		root = "G://Program Files//j2se-workspace//JvmTest1.6//bin";
		
		CustomClassLoader1 customClassLoader1 = new CustomClassLoader1(root);
		CustomClassLoader1 customClassLoader2 = new CustomClassLoader1(root);
		Class<?> clazz1= customClassLoader1.loadClass(className);
		Class<?> clazz2 = customClassLoader2.loadClass(className);
		Class<?> parClass = customClassLoader2.loadClass(parClassName3);
		Class<?> subClass = customClassLoader2.loadClass(subClassName3);
//		printAllMethod(clazz1);
		Object obj1 = clazz1.newInstance();
		Object obj2 = clazz2.newInstance();
		Object parObj= parClass.newInstance();
		Object subObj = subClass.newInstance();
		logger.info("1"+obj1.getClass().getClassLoader());
//		callMethod(clazz1, methodStr, obj2);
//		callMethod(clazz2, methodStr, obj2);
		Method method = clazz1.getDeclaredMethod(methodStr,Object.class);
		method.invoke(obj1,obj1);
		method.invoke(obj1,obj2);

		


//		((ClassLoaderTest)obj1).getAllClassLoader();
	}
	
	private void printAllMethod(Class clazz) {
		int i=1;
		for(Method method : clazz.getDeclaredMethods()) {
			logger.info(i+"\t"+method.getName());
			i++;
		}
	}
	private void callMethod(Class clazz,String methodStr,Object obj,Object... args) throws Exception {
		Method method = clazz.getDeclaredMethod(methodStr,args.getClass());
		method.setAccessible(true);
		method.invoke(obj,args);
	}
	
	private Object getObj(Class clazz,String fieldStr,Object obj) throws Exception {
		Field field = clazz.getDeclaredField(fieldStr);
		field.setAccessible(true);
		return field.get(obj);
	}
	private void getBootstrapClassPath() {
		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();  
		for (int i = 0; i < urls.length; i++) {  
		    logger.info(urls[i].toExternalForm());  
		}  
	}
	
	private void getAllClassLoader(){
		ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
		ClassLoader parent = classLoader;
		while( parent!= null) {
			logger.info(parent.getClass());
			parent = parent.getParent();
		}
		logger.info(parent);
	}

}
