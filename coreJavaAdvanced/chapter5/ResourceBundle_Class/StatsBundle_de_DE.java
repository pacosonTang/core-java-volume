package com.corejava.chapter573;

import java.util.ListResourceBundle;

public class StatsBundle_de_DE extends ListResourceBundle
{
	private Object[][] contents = 
		{
			{"GDP", 1113000},
			{"Population", 111233},
			{"Literacy", new Double(0.77)},
		};
	
	public Object[][] getContents()
	{
		return contents;
	}
}
