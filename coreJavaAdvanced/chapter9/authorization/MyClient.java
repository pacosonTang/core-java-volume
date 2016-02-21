package com.corejava.chapter9.authorization;

import java.security.PrivilegedAction;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import com.corejava.chapter9.authentication.MyCallbackHandler;

public class MyClient
{
	 public static void main(String argv[]) 
	 {  
	    LoginContext ctx = null;  
	    try {  
	      ctx = new LoginContext("WeatherLogin", new MyCallbackHandler());  
	    } catch(LoginException le) 
	    {  
	      System.err.println("LoginContext cannot be created. "+ le.getMessage());  
	      System.exit(-1);  
	    } catch(SecurityException se) 
	    {  
	      System.err.println("LoginContext cannot be created. "+ se.getMessage());  
	    }
	    
	    try 
	    {  
	      ctx.login();  
	    } catch(LoginException le) 
	    {  
	     System.out.println("Authentication failed");  
	     System.exit(-1);  
	    }  
	    System.out.println("Authentication succeeded");  
	    // 为了使客户端能够赋予用户权限，一旦认证成功（用户已经被认证了），
	    // 认证了的主体就使用通过Subject subject=ctx.getSubject()来获取。
	    Subject subject = ctx.getSubject();  
	    System.out.println(subject.toString());
	    PrivilegedAction action = new MyAction();  
	    // Subject的doAs(或者doAsPrivileged)方法必须被调用，它在反过来调用run方法（包含作为主体去执行的）。
	    Subject.doAsPrivileged(subject, action, null);  
	    try 
	    {  
	      ctx.logout();  
	    } catch(LoginException le) 
	    {  
	      System.out.println("Logout: " + le.getMessage());  
	    }  
	 }  
}
