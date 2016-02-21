package com.corejava.chapter11.activation.client;

import java.rmi.Naming;
import java.util.*;
import javax.naming.*;
import com.corejava.chapter11.activation.server.Warehouse;

// 客户端如何获得远程仓库对象的存根，并调用远程的getPrice方法。
public class WarehouseClient
{
	public static void main(String[] args)
	{
		try
		{
			System.setProperty("java.security.policy", "com/corejava/chapter11/activation/client/client.policy");
			System.setSecurityManager(new SecurityManager());
			
			Context namingContext = new InitialContext();
			// NameClassPair是一个助手类：　它包含绑定对象的名字和该对象所属类的名字。
			Enumeration<NameClassPair> e = namingContext.list("rmi://localhost:1099/");
			while (e.hasMoreElements())
				System.out.println(e.nextElement().getName());

			// 客户端可以通过下面的方式，来指定服务器和远程对象的名字，以此获得访问远程对象所需的存根：
			String url = "rmi://localhost:1099/central_warehouse";

			Warehouse centralWarehouse = (Warehouse) Naming.lookup(url);
			String descr = "Blackwell Toaster";
			double price = centralWarehouse.getPrice(descr);
			System.out.println(descr + ": " + price);
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
