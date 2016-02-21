package com.corejava.chapter9.authentication;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

// LoginContext：是javax.security.auth.login包里的一个类，它描述了用于验证对象(subjects)的方法。
// LoginContext对象调用负责实现和执行认证的LoginModules。
public class MyClient
{
	public static void main(String[] args)
	{
		LoginContext context = null;
		try
		{
			// 在配置文件example.conf里，实体名"WeatherLogin"就是被MyClient.java用作关联这个实体的名字。
			context = new LoginContext("WeatherLogin", new MyCallbackHandler());
		} catch (LoginException le)
		{
			System.err.println("LoginContext cannot be created. "
					+ le.getMessage());
			System.exit(-1);
		} catch (SecurityException se)
		{
			System.err.println("LoginContext cannot be created. "
					+ se.getMessage());
		}
		try
		{
			context.login();
		} catch (LoginException le)
		{
			System.out.println("Authentication failed. " + le.getMessage());
			System.exit(-1);
		}
		System.out.println("authentication succeeded.");
		System.exit(-1);

	}
}
