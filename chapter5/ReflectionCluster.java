package com.corejava.chapter5_reflection;

import static java.lang.System.*;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionCluster
{
	public static void main1(String[] args)
	{
		Demo demo = new Demo();
		out.println(demo.getClass().getName());
	}
	public static void main2(String[] args)
	{
		Class<?> demo1 = null;
		Class<?> demo2 = null;
		Class<?> demo3 = null;
		try
		{
			//一般采用这种形式（推荐）
			demo1 = Class.forName("com.corejava.chapter5_reflection.Demo");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		demo2 = new Demo().getClass();
		demo3 = Demo.class;
		
		out.println(demo1.getName() + "\n" 
						+ demo2.getName() + "\n" + demo3.getName());
	}
	
	public static void main3(String[] args) throws ClassNotFoundException
	{
		Class<?> demo = null;
		Person person = null;
		
		demo = Class.forName("com.corejava.chapter5_reflection.Demo");
		try
		{
			person = (Person)demo.newInstance();
		} catch (InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
		person.setName("tang");
		person.setAge(1);
		out.print(person);
	}
	public static void main4(String[] args) throws ClassNotFoundException
	{
		Class<?> demo = null;
		Person p1 = null;
		Person p2 = null;
		Person p3 = null;
		
		demo = Class.forName("com.corejava.chapter5_reflection.Demo");
		Constructor<?>[] cons = demo.getConstructors();
		try
		{
			p1 = (Person)cons[0].newInstance(20);
			p2 = (Person)cons[1].newInstance("nihao");
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		out.print(p1);
	}
	
	public static void main5(String[] args) throws ClassNotFoundException
	{
		Class<?> person = null;
		person = Class.forName("com.corejava.chapter5_reflection.Person");
		Class<?>[] interfaces = person.getInterfaces();//获得Person类实现的接口
		Class<?> parent = person.getSuperclass(); //获得Person类继承的父类
		for (int i = 0; i < interfaces.length; i++)
		{
			out.println("interface name = " + interfaces[i].getName());
		}
		out.println(parent.getName());
	}
	
	public static void main6(String[] args)
	{
		Class<?> demo = null;
		try
		{
			demo = Class.forName("com.corejava.chapter5_reflection.Demo");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		Constructor<?>[] cons = demo.getConstructors();
		for (int i = 0; i < cons.length; i++)
		{
			out.println(cons[i]);
		}
	}
	public static void main7(String[] args) throws ClassNotFoundException
	{
		Class<?> demo = null;
		demo = Class.forName("com.corejava.chapter5_reflection.Person");
		Method[] methods = demo.getMethods();
		
		for (int i = 0; i < methods.length; i++)
		{
			Class<?> returnType = methods[i].getReturnType();//返回值类型
			Class<?>[] parameters = methods[i].getParameterTypes();//方法参数列表
			int modifier = methods[i].getModifiers();
			out.print(Modifier.toString(modifier) + " ");//modifier == private | protected | public
			out.print(returnType.getName() + " ");
			out.print(methods[i].getName() + " "); // 方法名称
			out.print("(");
			for (int j = 0; j < parameters.length; j++) // 循环遍历参数列表
			{
				out.print(parameters[j].getName() + " " + "arg" + j);
				if(j < parameters.length - 1)
					out.print(",");
			}
			Class<?>[] exceptions = methods[i].getExceptionTypes();
			if(exceptions.length > 0)
			{
				out.print(") throws ");
				for (int k = 0; k < exceptions.length; k++) //循环遍历该方法抛出的异常
				{
					out.print(exceptions[k].getName() + " ");
					if(k < exceptions.length - 1)
						out.print(",");
				}
			}
			else
			{
				out.print(")");
			}
			out.println();
		}
	}
	
	public static void main8(String[] args) throws ClassNotFoundException
	{
		Class<?> demo = null;
		demo = Class.forName("com.corejava.chapter5_reflection.Person");
		Field[] fields = demo.getDeclaredFields();
		
		out.print("all properties defined in Person itself are as follows : \n");
		for (int i = 0; i < fields.length; i++)
		{
			int modifier = fields[i].getModifiers();
			String modifierStr = Modifier.toString(modifier); //属性修饰符,(private|protected|public)
			Class<?> type = fields[i].getType();//属性类型
			out.println(modifierStr + " " + type.getName() + " " 
									+ fields[i].getName() + ";");//属性名称
		}
		Field[] otherFields = demo.getFields();
		out.print("all interfaces implemented and super class extended are as follows : \n");
		for (int i = 0; i < otherFields.length; i++)
		{
			int modifier = otherFields[i].getModifiers();
			String modifierStr = Modifier.toString(modifier);
			Class<?> type = otherFields[i].getType();
			out.println(modifierStr + " " + type.getName() + " " 
									+ otherFields[i].getName() + ";");
		}
	}
	public static void main9(String[] args) throws ClassNotFoundException
	{
		Class<?> demo = null;
		Method method = null;
		demo = Class.forName("com.corejava.chapter5_reflection.Person");
		
		try
		{
			// call method Person.sayChengdu
			method = demo.getMethod("sayChengdu"); // also has demo.getDeclaredMethods("sayChengdu")
			method.invoke(demo.newInstance());
			// call method Person.sayPerson
			method = demo.getDeclaredMethod("sayPerson", String.class, int.class);
			method.invoke(demo.newInstance(), "tangrong", 24);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void setter(Object obj, String field, Object value, Class<?> type)
	{
		Method method = null;
		try{
			method = obj.getClass().getMethod("set" + field, type);
			method.invoke(obj, value);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void getter(Object obj, String field) 
	{
		try
		{
			Method method = obj.getClass().getMethod("get" + field);
			out.println(method.invoke(obj));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main10(String[] args) 
	{
		Object obj = null;
		
		try
		{
			obj = Class.forName("com.corejava.chapter5_reflection.Person").newInstance();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		setter(obj, "Age", 11, int.class);
		getter(obj, "Age");
	}
	
	public static void main11(String[] args) throws Exception
	{
		Class<?> demo = null;
		Object obj = null;
		
		demo = Class.forName("com.corejava.chapter5_reflection.Person");
		obj = demo.newInstance();
		Field field = demo.getDeclaredField("age");
		field.setAccessible(true); // 要知道age本是private，在类外部是不可访问的
		field.set(obj, 101);
		out.println(field.get(obj));
	}
	
	public static void main12(String[] args)
	{
		int[] array = {1, 1, 0};
		Class<?> demo = array.getClass().getComponentType();
		out.println("array type = " + demo.getName());
		out.println("array length = " + Array.getLength(array));
		out.println("1st element in array = " + Array.get(array, 0));
		Array.set(array, 0, 100); //修改数组的第一个元素为100（下标为0）
		out.println("after modification, 1st element in array is " + Array.get(array, 0));
	}
	
	public static Object modifyArray(Object obj, int start, int newLen) 
	{
		int oldLen = Array.getLength(obj);
		Class<?> arrayType =  obj.getClass().getComponentType();
		Object newArray = Array.newInstance(arrayType, newLen);
		
		newLen = newLen > oldLen ? oldLen : newLen;
		System.arraycopy(obj, start, newArray, 0, newLen);

		return newArray;
	}
	
	public static void print(Object obj)
	{
		int length = Array.getLength(obj);
		Class<?> c = obj.getClass();
		
		if(!c.isArray())
			return ;
		out.println("array length :" + length);
		for (int i = 0; i < length; i++)
			out.print(Array.get(obj, i) + " "); // Array.get(obj, i) == obj[i] 
	}
	// modify the size of array
	public static void main13(String[] args)
	{
		int[] array = {1, 7, 6, 0, 8};
		int[] newArray = (int[])modifyArray(array, 0, 3);
		for(int i : newArray)
		{
			out.print(i + " ");
		}
		out.println();
		print(newArray);
		
		newArray = (int[])modifyArray(array, 0, 8);//当新数组长度大于原始数组长度的时候
		print(newArray);
	}
	
	public static void main(String[] args)
	{
		Person p = new Person();
		out.println("class loader : " + 
					p.getClass().getClassLoader().getClass().getName());
	}
}
interface Chengdu
{
	public int girls = 100;
	public void sayChengdu();
}
class Person implements Chengdu
{
	private int age;
	private String name;
	
	public Person(){}
	
	public Person(int age, String name)
	{
		this.age = age;
		this.name = name;
	}
	
	@Override
	public String toString()
	{
		return "name = " + name + "age = " + age;
	}
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void sayChengdu()
	{
		out.println("chengdu");
	}
	
	public void sayPerson(String name, int age)
	{
		out.println(name + " " + age);
	}
}


class Demo
{
	private int intField;
	private String strField;
	
	public Demo(){}
	
	public Demo(int i, String s)
	{
		this.intField = i;
		this.strField = s;
	}
	
	public Demo(String s)
	{
		this.strField = s;
	}
	public Demo(int i)
	{
		this.intField = i;
	}
}
