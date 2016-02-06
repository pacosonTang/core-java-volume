package com.corejava.chapter573;

import java.util.ListResourceBundle;

public class StatsBundle_zh_CN extends ListResourceBundle
{
	private Object[][] contents = 
		{
			{"GDP", 299000},
			{"Population", 1300000000},
			{"Literacy", new Double(0.88)},
		};
	
	public Object[][] getContents()
	{
		return contents;
	}
}
