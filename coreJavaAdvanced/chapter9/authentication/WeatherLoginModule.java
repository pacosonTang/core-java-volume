package com.corejava.chapter9.authentication;

import java.util.Map;
import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

// 以下代码展示了一个LoginModule简单的实现. 这个例子是非常简单的，
// 因为他仅仅有一个认证字符串和一个Principal（特征） "SunnyDay", 两个都是硬编码。
// 如果去login，系统将显示"What is the weather like today?", 如果答案是"Sunny", 用户就能通过。
public class WeatherLoginModule implements LoginModule
{
	private Subject subject;
	private ExamplePrincipal entity;
	private CallbackHandler callbackhandler;
	private static final int NOT = 0;
	private static final int OK = 1;
	private static final int COMMIT = 2;
	private int status;

	// initialize: 这个方法的目的就是用有关的信息去实例化这个LoginModule。
	// 如果login成功，在这个方法里的Subject就被用在存储Principals和Credentials.  
	// 注意这个方法有一个能被用作输入认证信息的CallbackHandler。在这个例子里，我没有用CallbackHandler. 
	// CallbackHandler是有用的，因为它从被用作特定输入设备里分离了服务提供者。
	public void initialize(Subject subject, CallbackHandler
			callbackhandler, Map state, Map options)
	{
		status = NOT;
		entity = null;
		this.subject = subject;
		this.callbackhandler = callbackhandler;
	}

	// login: 请求LoginModule去认证Subject. 注意此时Principal还没有被指定。
	public boolean login() throws LoginException
	{

		if (callbackhandler == null)
		{
			throw new LoginException("No callback handler is available");
		}
		Callback callbacks[] = new Callback[1];
		callbacks[0] = new NameCallback("What is the weather like today?");
		String name = null;
		try
		{
			// 调用 MyCallbackHandler.java 中的 handle 方法进行处理
			// 以读入用户输入的认证信息（如 username）
			callbackhandler.handle(callbacks); 
			name = ((NameCallback) callbacks[0]).getName();
		} catch (java.io.IOException ioe)
		{
			throw new LoginException(ioe.toString());
		} catch (UnsupportedCallbackException ce)
		{
			throw new LoginException("Error: " + ce.getCallback().toString());
		}
		if (name.equals("Sunny"))
		{
			entity = new ExamplePrincipal("SunnyDay");
			status = OK;
			return true;
		} else
		{
			return false;
		}
	}

	// commit: 如果LoginContext的认证全部成功就调用这个方法。
	public boolean commit() throws LoginException
	{
		if (status == NOT)
		{
			return false;
		}
		if (subject == null)
		{
			return false;
		}
		Set entities = subject.getPrincipals();
		if (!entities.contains(entity))
		{
			entities.add(entity);
		}
		status = COMMIT;
		return true;
	}
	
	// abort: 通知其他LoginModule供应者或LoginModule模型认证已经失败了。整个login将失败。
	public boolean abort() throws LoginException
	{
		if ((subject != null) && (entity != null))
		{
			Set entities = subject.getPrincipals();
			if (entities.contains(entity))
			{
				entities.remove(entity);
			}
		}
		subject = null;
		entity = null;
		status = NOT;
		return true;
	}

	// logout: 通过从Subject里移除Principals和Credentials注销Subject。
	public boolean logout() throws LoginException
	{
		subject.getPrincipals().remove(entity);
		status = NOT;
		subject = null;
		return true;
	}

}
