package com.corejava.chapter8;

import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class DefaultPersistentDelegateTest
{
	public static void main(String[] args)
	{
		XMLEncoder out = null;
		// 持久化 java类到 XML
		try
		{
			out = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream("com/corejava/chapter8/default_persistent_delegate.xml")));
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		// 安装持久化代理对象
		// 它告诉编译器： “要编码一个 Employee 对象， 
		// 就要取得它的 name, salary, age 属性”， 并且用它们来调用构造器。
		out.setPersistenceDelegate(Employee.class, 
				new DefaultPersistenceDelegate(new String[]{"name","salary","age"}));
		Employee employee = new Employee("default xiao tang", 110, 25);
		out.writeObject(employee);
		out.close();
		// 持久化处理结束
		
		System.out.println("DefaultPersistentDelegateTest write Sucess");
	}
}
