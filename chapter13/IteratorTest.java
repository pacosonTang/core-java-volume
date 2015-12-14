package com.corejava.chapter13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import static java.lang.System.*;
public class IteratorTest
{
	public static void main(String[] args)
	{
		String[] numbers = new String[]{"4","10","7","5"};
		ArrayList<String> list = 
				new ArrayList<>(Arrays.asList(numbers)); //集合
		Iterator<String> iter = list.iterator();
		while(iter.hasNext())
		{
			iter.next();
			iter.remove();
//			iter.next();
			iter.remove();
		}
		
	}
	
	public static void main2(String[] args)
	{
		String[] numbers = new String[]{"4","10","7","5"};
		ArrayList<String> list = new ArrayList<>(Arrays.asList(numbers)); //集合
		out.println("ArrayList： ");
		for(String str : list)
			out.print(str + " ");
		
		out.println("\nHashSet : ");
		HashSet<String> set = new HashSet<>(Arrays.asList(numbers));
		for(String str : set)
			out.print(str + " ");
	}
	
	public static void main1(String[] args)
	{
		Collection<String> c = Arrays.asList("5","3","1","2"); //集合
		Iterator<String> iter = c.iterator(); //集合迭代器
		out.println("iter.hasNext:");
		while(iter.hasNext()) //判断集合中是否还有下一元素
		{
		    String element = iter.next();
		    out.print(element + " ");
		}
		out.println("\n for each:");
		for(String str : c)
			out.print(str + " ");
	}
}
