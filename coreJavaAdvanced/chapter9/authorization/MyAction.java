package com.corejava.chapter9.authorization;

import java.io.File;
import java.security.PrivilegedAction;

public class MyAction implements PrivilegedAction
{
	@Override
	public Object run()
	{
		System.out.println("this is the 1st authorization task after passing authentication");  
		File file = new File("com/corejava/chapter9/authorization/test.txt");  
	    if(file.exists()) 
	    {  
	      System.out.println("The file exists in the current working directory");  
	    }
	    else 
	    {  
	      System.out.println("The file does not exist in the current working directory");  
	    }  
	    return null;  
	}
}
