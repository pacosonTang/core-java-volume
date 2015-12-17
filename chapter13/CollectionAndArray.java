package com.corejava.chapter13;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import static java.lang.System.*;

public class CollectionAndArray
{
	public static void main(String[] args)
	{
		HashSet<String> numbers = 
				new HashSet<>(Arrays.asList(new String[]{"1", "7", "6"}));
		String[] values = (String[]) numbers.toArray(new String[0]);
		for(String str : values)
			out.print(str + " ");
	}
	
	public static void main1(String[] args)
	{
		HashSet<String> numbers = new HashSet<>(Arrays.asList(new String[]{"1", "7", "6"}));
		String[] values = (String[]) numbers.toArray(); 
	}
}
