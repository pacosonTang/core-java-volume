package com.corejava.chapter10.annotation;
 
@MyAnnotation1("this is the annotation1")
public class AnnotationDemo
{
	@MyAnnotation2(desc="this is the annotation2", isAnnotation=true)
	public void sayHello()
	{
		System.out.println("hello world!");
	}
}
