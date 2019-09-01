package com.moyan.example.j2se.reflect.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {

	public enum Color {RED,BLUE,GREEN,DEFAULTCOLOR};
	
	Color fruitColor() default Color.DEFAULTCOLOR;
}
