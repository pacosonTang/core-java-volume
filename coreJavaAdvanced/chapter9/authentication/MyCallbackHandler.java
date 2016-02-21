package com.corejava.chapter9.authentication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

// 一个基于JAAS的应用程序实现了CallbackHandler接口，
// 因此它能够提示用户去输入特定的认证信息，比如用户名或者密码，或者显示错误或者警告信息。
// 基于传递的callbacks，回调处理程序决定怎样去获取和显示信息。
public class MyCallbackHandler implements CallbackHandler
{
	@Override
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException
	{
		for (int i = 0; i < callbacks.length; i++)
		{
			if(callbacks[i] instanceof NameCallback)
			{
				NameCallback nc = (NameCallback)callbacks[0];
				System.err.println(nc.getPrompt());
				System.err.flush();
				String name = (new BufferedReader(new InputStreamReader(System.in))).readLine();
				nc.setName(name);
			}
			else
			{
				throw new UnsupportedCallbackException(callbacks[i], "callback handler not support");
			}
		}
	}
	
}
