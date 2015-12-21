package com.corejava.chapter11_3;

import static java.lang.System.out;

public class DefineExceptionClauseException
{
	public static void main(String[] args) 
	{
		 try
		{
			main2(17, 13);
		} catch (Throwable e)
		{
			Throwable se = e.getCause();// get init exception
			out.println(se);
		}
		 //main1(17, 13);//这里为什么出现编译错误？@smile
	}
	
	public static void main2(int age, int month) throws Throwable
	{
		try
		{
			checkAge(age, month);
		} catch (MonthOutOfBound e)
		{
			throw new MonthOutOfBound("AgeOutOfBound switch to MonthOutOfBound " + e.getMessage());
		} catch (AgeOutOfBound  e) // strongly recommend this packaging tech 
		{
			Throwable se = new MonthOutOfBound();
			se.initCause(e);
			throw se;
		}
	}
	
	public static void main1(int age, int month) throws AgeOutOfBound, MonthOutOfBound
	{
		checkAge(age, month);
	}
	
	public static void checkAge(int age, int month) throws AgeOutOfBound, MonthOutOfBound
	{
		if(age < 18)
			throw new AgeOutOfBound();
		if(month > 12)
			throw new MonthOutOfBound();
	}
}

class AgeOutOfBound extends Exception
{
	public AgeOutOfBound()
	{
		out.println("sorry, age out of bound");
	}
	
	public AgeOutOfBound(String str)
	{
		out.println("msg : " + str);
		out.println("sorry, age out of bound");
	}
}

class MonthOutOfBound extends Exception
{
	public MonthOutOfBound()
	{
		out.println("sorry, month out of bound");
	}
	
	public MonthOutOfBound(String str)
	{
		out.println("msg : " + str);
		out.println("sorry, month out of bound");
	}
}