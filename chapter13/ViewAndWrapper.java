package com.corejava.chapter13;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static java.lang.System.*;

public class ViewAndWrapper
{
	public static void main(String[] args)
	{
		List<String> list = Collections.nCopies(4, "DEFAULT");
		Set set = Collections.singleton(list);
		for (Object object : set)
			out.print(object);
	}
	
	public static void main1(String[] args)
	{
		List<String> settings = Collections.nCopies(100, "DEFAULT");
		out.print(settings.get(0) == settings.get(1));
	}
}	
