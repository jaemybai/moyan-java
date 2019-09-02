package com.moyan.example.j2se.reflect.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class AnnotationMain1 {

	private static Logger logger = LoggerFactory.getLogger(AnnotationMain1.class);

	public static void main(String[] args) throws Exception {
		
		AnnotationDto dto = new AnnotationDto();
		logger.info("" + dto);

		Class<?> clazz = getClass(dto);
		Annotation[] annos = clazz.getDeclaredAnnotations();
		logger.info("" + annos);
		Field[] fields = getField(clazz);
		
		for(Field field :fields) {
			setField(field, dto);
		}
		logger.info("" + dto);
	}
	
	public static <T> Class<? extends Object> getClass(T t) {
		return t.getClass();
	}
	
	public static Field[] getField(Class<?> clazz) {
		return clazz.getDeclaredFields();
	}
	
	public static void setField(Field field,Object obj) throws Exception {
		
		FruitName anno = field.getAnnotation(FruitName.class);
		if(anno != null) {
			setField(field, obj, anno.value());
			return;
		}
		FruitColor anno2 = field.getAnnotation(FruitColor.class);
		if(anno2 != null) {
			setField(field, obj, anno2.fruitColor().toString());
			return;
		}
		setField(field, obj, null);
	}
	
	public static void setField(Field field, Object obj,String value) throws Exception {
		
		if(field.isAccessible()) {
			field.set(obj, value);
		}
		else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}
}