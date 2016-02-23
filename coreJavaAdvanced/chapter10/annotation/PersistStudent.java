package com.corejava.chapter10.annotation;

import java.lang.reflect.Method;

public class PersistStudent
{
	public static void main(String[] args) throws Exception
	{
		Object obj = Class.forName("com.corejava.chapter10.annotation.Student").newInstance();
		try
		{
			Method[] methods = obj.getClass().getDeclaredMethods();
			for (int i = 0; i < methods.length; i++)
			{
				if(methods[i].isAnnotationPresent(ValueBind.class))
				{
					ValueBind annotation = methods[i].getAnnotation(ValueBind.class);
					String type = String.valueOf(annotation.type());
					String value = annotation.value();
					if(type.equals("INT"))
					{
						methods[i].invoke(obj, new Integer[]{new Integer(value)});
					}
					else
					{
						methods[i].invoke(obj, new String[]{value});
					}
				}
			}
			Student stu = (Student)obj;
			System.out.println("studentId = " + stu.getStudentid() + "\nstudentName = " + stu.getName() + "\nstudentAge"
					+ " = " + stu.getAge()); 
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
