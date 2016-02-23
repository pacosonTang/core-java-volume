package com.corejava.chapter8;

import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class PersistentDelegateTest
{
	public static void main(String[] args)
	{
		// 持久化代理对象
		PersistenceDelegate delegate = new DefaultPersistenceDelegate()
		{
			protected Expression instantiate(Object oldInstance, Encoder out)
			{
				Employee e = (Employee) oldInstance;
				return new Expression(oldInstance, Employee.class, "new", new Object[]
				{
					e.getName(),
					e.getSalary(),
					e.getAge()
				});
			}
		};
		
		XMLEncoder out = null;
		// 持久化 java类到 XML
		try
		{
			out = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream("com/corejava/chapter8/persistent_delegate.xml")));
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		// 安装持久化代理对象
		out.setPersistenceDelegate(Employee.class, delegate);
		Employee employee = new Employee("xiao tang", 110, 25);
		out.writeObject(employee);
		out.close();
		// 持久化处理结束
		
		System.out.println("write Sucess");
	}
}
