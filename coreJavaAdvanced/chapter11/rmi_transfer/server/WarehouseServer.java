package com.corejava.chapter11.transfer.server;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import javax.naming.*;

public class WarehouseServer
{
	public static void main(String[] args) throws RemoteException,
			NamingException
	{
		//System.setProperty("java.security.policy", "server.policy");
		//System.setSecurityManager(new SecurityManager());

		System.out.println("Constructing server implementation...");
		WarehouseImpl backupWarehouse = new WarehouseImpl(null);
		WarehouseImpl centralWarehouse = new WarehouseImpl(backupWarehouse);

		centralWarehouse.add("toaster", new Product("Black Toaster", 23.95));
		backupWarehouse.add("java", new Book("Core Java vol. 2", "0132354799", 44.95));

		System.out.println("Binding server implementation to registry...");
		try
		{ 
			// 注册通讯端口
			LocateRegistry.createRegistry(1099);
			// 注册通讯路径
			Naming.rebind("rmi://localhost:1099/centralWarehouse", centralWarehouse);
			System.out.println("warehouse service starts");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
