package com.corejava.chapter11_2;

import static java.lang.System.out;

public class DefineExceptionMultiCatch
{
	public static void main(String[] args) 
	{
		 main2(17, 13);
		 //main1(17, 13);//这里为什么出现编译错误？@smile
	}
	
	public static void main1(int age, int month) throws AgeOutOfBound, MonthOutOfBound
	{
		checkAge(age, month);
	}
	
	public static void main2(int age, int month)
	{
		try
		{
			checkAge(age, month);
		} catch (AgeOutOfBound e)
		{
			e.printStackTrace();
		} catch (MonthOutOfBound e)
		{
			e.printStackTrace();
		}
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