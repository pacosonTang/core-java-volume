package com.corejava.chapter11.client;

import java.util.*;
import javax.naming.*;
import com.corejava.chapter11.server.Warehouse;

/**
 * A client that invokes a remote method.
 * 
 * @version 1.0 2007-10-09
 * @author Cay Horstmann
 */
// 客户端如何获得远程仓库对象的存根，并调用远程的getPrice方法。
public class WarehouseClient
{
	public static void main(String[] args)
	{
		try
		{
			Context namingContext = new InitialContext();
			// NameClassPair是一个助手类：　它包含绑定对象的名字和该对象所属类的名字。
			Enumeration<NameClassPair> e = namingContext
					.list("rmi://localhost:1099/");
			while (e.hasMoreElements())
				System.out.println(e.nextElement().getName());

			// 客户端可以通过下面的方式，来指定服务器和远程对象的名字，以此获得访问远程对象所需的存根：
			String url = "rmi://localhost:1099/warehouseService";
			Warehouse centralWarehouse = (Warehouse) namingContext.lookup(url);

			String descr = "A";
			double price = centralWarehouse.getPrice(descr);
			System.out.println(descr + ": " + price);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	/*
	 * public static void main(String[] args) throws NamingException,
	 * RemoteException { Context namingContext = new InitialContext();
	 * 
	 * System.out.print("RMI registry bindings: "); //
	 * NameClassPair是一个助手类：　它包含绑定对象的名字和该对象所属类的名字。 Enumeration<NameClassPair> e =
	 * namingContext.list("rmi://localhost:1099/warehouseService"); while
	 * (e.hasMoreElements()) System.out.println(e.nextElement().getName());
	 * 
	 * // 客户端可以通过下面的方式，来指定服务器和远程对象的名字，以此获得访问远程对象所需的存根： String url =
	 * "rmi://localhost/central_warehouse"; Warehouse centralWarehouse =
	 * (Warehouse) namingContext.lookup(url);
	 * 
	 * String descr = "Blackwell Toaster"; double price =
	 * centralWarehouse.getPrice(descr); System.out.println(descr + ": " +
	 * price); }
	 */
}
