package com.corejava.chapter11;

import static java.lang.System.out;

public class DefineException
{
	public static void main(String[] args) 
	{
		 main2(17);
		 main1(17);
	}
	
	public static void main1(int age) throws AgeOutOfBound
	{
		checkAge(age);
	}
	
	public static void main2(int age)
	{
		try
		{
			checkAge(age);
		} catch (AgeOutOfBound e)
		{
			e.printStackTrace();
		}
	}
	
	public static void checkAge(int age) throws AgeOutOfBound
	{
		if(age > 18)
			out.println("accepted!");
		else
			throw new AgeOutOfBound("rejected!");
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