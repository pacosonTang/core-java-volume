package com.corejava.chapter12_3;

import static java.lang.System.*;

public class GenericMethod
{
	public static void main(String[] args)
	{
		out.println(ArrayAlg.getMiddle(new String[]{"C", "B", "D"}));
	}
	
	
}

class ArrayAlg
{
	public static <T> T getMiddle(T...a)
	{
		return a[a.length/2];
	}
}

