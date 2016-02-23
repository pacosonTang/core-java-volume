package com.corejava.chapter10.annotation;

import com.corejava.chapter10.annotation.ValueBind.FieldType;

public class Student
{
	private String name = "";
	private int age = 0;
	private String studentid = "";
	
	@ValueBind(type=FieldType.STRING,value="xiaotang")
	public void seName(String name)
	{
		this.name = name;
	}
	
	@ValueBind(type=FieldType.INT,value="110")
	public void setAge(int age)
	{
		this.age = age;
	}
	@ValueBind(type=FieldType.STRING,value="2014210")
	public void setStudentid(String studentid)
	{
		this.studentid = studentid;
	}
	
	public String getName()
	{
		return name;
	}
	public String getStudentid()
	{
		return studentid;
	}
	public int getAge()
	{
		return age;
	}
}
