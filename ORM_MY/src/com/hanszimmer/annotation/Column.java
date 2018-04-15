package com.hanszimmer.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

	String name();
	
	boolean isPrimary() default false;
	
	String type() default "char";
	
	String size() default "20";
	
	String increment() default "1";
	
	String incrementstep() default "1";

}
