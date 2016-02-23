package com.corejava.chapter8;

import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.InetAddress;

public class FactoryPersistentDelegateTest
{
	public static void main(String[] args)
	{
		// 持久化代理对象
		PersistenceDelegate delegate = new DefaultPersistenceDelegate()
		{
			protected Expression instantiate(Object oldInstance, Encoder out)
			{
				return new Expression(oldInstance, InetAddress.class, "getByAddress", 
						new Object[] {((InetAddress)oldInstance).getAddress()});
			}
		};
		
		XMLEncoder out = null;
		// 持久化 java类到 XML
		try
		{
			out = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream("com/corejava/chapter8/factory_persistent_delegate.xml")));
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		// 安装持久化代理对象
		out.setPersistenceDelegate(InetAddress.class, delegate);
		Employee employee = new Employee("xiao tang", 110, 25);
		out.writeObject(employee);
		out.close();
		// 持久化处理结束
		
		System.out.println("write Sucess");
	}
}
