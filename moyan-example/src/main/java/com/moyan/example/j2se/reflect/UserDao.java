package com.moyan.example.j2se.reflect;

import com.moyan.example.j2se.base.ViolateTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class UserDao {

	private static Logger logger = LoggerFactory.getLogger(ViolateTest.class);

	public static void main(String[] args) throws Exception {
		
		UserDto dto = new UserDto();
		
		Class<UserDto> dtoClass = UserDto.class;
		Field[] allFields = dtoClass.getDeclaredFields();
		logger.info("===赋值前");
		for(Field field:allFields) {
			
			field.setAccessible(true);
			logger.info(field.getType().getName()+"\t"
		+field.getModifiers()+"\t"
		+Modifier.toString(field.getModifiers())+"\t"
		+field.getName()+"\t"
		+field.get(dto));
			field.setAccessible(false);
		}
		logger.info("" + dto);
		logger.info("====赋值后");
		int i = 0;
		for(Field field:allFields) {
			field.setAccessible(true);
			if(field.getType().getName().equals("int")) {
				field.set(dto, (Object)i++);
			} else {
				field.set(dto, ((Object)i++).toString());
			}
			
			logger.info(field.getType().getName()+"\t"
		+field.getModifiers()+"\t"
		+field.getName()+"\t"
		+field.get(dto));
		field.setAccessible(false);
		}
		logger.info("" + dto);
		
//		Field[] publicFields = dtoClass.getFields();
//		for(Field field:publicFields) {
//			logger.info(field.getModifiers()+"\t"+field.getName());
//		}
		int intTest = 0;
		Object obj = (Object)intTest;
		logger.info("" + obj);
	}
}
