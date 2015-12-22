package com.corejava.chapter12_3;

import static java.lang.System.*;

public class GenericStatic
{
	public static void main(String[] args)
	{
		
	}
	
	public static <T extends Throwable> void dowork(Class<T> t)
	{
	    try
		{
	    	out.print("hhe");
	    //catch 子句中不能使用类型变量， 以下方法不能通过编译：
		}catch(T e) // ERROR--can't catch type variable
	    {
	    }
	}
}
//泛型类扩展 Throwable 也是不合法的
class Problem<T> extends Exception 
{ }

class Singleton<T>
{
	
	
    /*private static T single; // ERROR
    private static getSingle() // ERROR
    {
    	//.....
    }*/
}
