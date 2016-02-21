package com.corejava.chapter11.activation.server;

import java.io.*;
import java.rmi.*;
import java.rmi.activation.*;
import java.util.*;

import javax.naming.*;

public class WarehouseActivator
{
	public static void main(String[] args) throws RemoteException,
			NamingException, ActivationException, IOException,
			AlreadyBoundException
	{
		System.out.println("Constructing activation descriptors...");
		System.setProperty("java.security.policy",
				"com/corejava/chapter11/activation/server/server.policy");
		System.setSecurityManager(new SecurityManager());

		// 如何构建激活程序
		Properties props = new Properties();
		// use the server.policy file in the current directory
		props.put("java.security.policy", new File(
				"com/corejava/chapter11/activation/server/server.policy")
				.getCanonicalPath());
		// step1)需要定义一个激活组
		// step2） 然后如下构造一个激活组描述符：
		ActivationGroupDesc group = new ActivationGroupDesc(props, null);
		// step3)创建一个组ID
		ActivationGroupID id = ActivationGroup.getSystem().registerGroup(group);

		Map<String, Double> prices = new HashMap<>();
		prices.put("Blackwell Toaster", 24.95);
		prices.put("ZapXpress Microwave Oven", 49.95);

		MarshalledObject<Map<String, Double>> param = new MarshalledObject<Map<String, Double>>(
				prices);

		String codebase = "http://localhost:8080/";

		// step4） 构造一个激活描述符了。对于需要构造的每一个对象，都应该包括以下内容（contents）：
		// 激活组ID + 类的名字 + URL字符串 + 编组后的构造信息
		// LocateRegistry.createRegistry(1099);
		ActivationDesc desc = new ActivationDesc(id,
				"com.corejava.chapter11.activation.server.WarehouseImpl",
				codebase, param);
		Warehouse centralWarehouse = (Warehouse) Activatable.register(desc);

		Naming.rebind("rmi://localhost:1099/central_warehouse", centralWarehouse);
		System.out.println("Exiting...");
	}
}
