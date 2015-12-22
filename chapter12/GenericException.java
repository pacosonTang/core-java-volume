package com.corejava.chapter12_3;

import static java.lang.System.*;
public class GenericException
{
	public static void main(String[] args)
	{
		
	}
}
class Block
{
	@SuppressWarnings("unchecked")
	public static <T extends Throwable> void throwAS(Throwable e) throws T
	{
		throw (T)e;
	}
	
	public static void throwExceptionTest()
	{
		Throwable t = new Throwable();
		Block.<RuntimeException>throwAS(t);
	}
}

