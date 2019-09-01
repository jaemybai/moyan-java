package com.moyan.example.j2se.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class UserDao {

	public static void main(String[] args) throws Exception {
		
		UserDto dto = new UserDto();
		
		Class<UserDto> dtoClass = UserDto.class;
		Field[] allFields = dtoClass.getDeclaredFields();
		System.out.println("===赋值前");
		for(Field field:allFields) {
			
			field.setAccessible(true);
			System.out.println(field.getType().getName()+"\t"
		+field.getModifiers()+"\t"
		+Modifier.toString(field.getModifiers())+"\t"
		+field.getName()+"\t"
		+field.get(dto));
			field.setAccessible(false);
		}
		System.out.println(dto);
		System.out.println("====赋值后");
		int i = 0;
		for(Field field:allFields) {
			field.setAccessible(true);
			if(field.getType().getName().equals("int")) {
				field.set(dto, (Object)i++);
			} else {
				field.set(dto, ((Object)i++).toString());
			}
			
			System.out.println(field.getType().getName()+"\t"
		+field.getModifiers()+"\t"
		+field.getName()+"\t"
		+field.get(dto));
		field.setAccessible(false);
		}
		System.out.println(dto);
		
//		Field[] publicFields = dtoClass.getFields();
//		for(Field field:publicFields) {
//			System.out.println(field.getModifiers()+"\t"+field.getName());
//		}
		int intTest = 0;
		Object obj = (Object)intTest;
		System.out.println(obj);
	}
}
