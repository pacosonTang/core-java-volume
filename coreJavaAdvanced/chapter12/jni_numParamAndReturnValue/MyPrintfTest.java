package com.corejava.chapter12_2;

public class MyPrintfTest
{
	public static void main(String[] args)
	{
		int count = 0;
		count = MyPrintf.print(6, 4, 3.1415926);
		for (int i = 0; i < count; i++)
		{
			System.out.println("-");
		}
	}
	
	static
	{
		System.loadLibrary("MyPrintf");
	}
}
