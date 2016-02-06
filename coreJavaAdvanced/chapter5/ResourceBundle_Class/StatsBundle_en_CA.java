package com.corejava.chapter573;

import java.util.ListResourceBundle;

public class StatsBundle_en_CA extends ListResourceBundle
{
	private static final Object[][] contents = 
		{
			{"GDP", 21300},
			{"Population", 125449703},
			{"Literacy", new Double(0.99)},
		};
	
	public Object[][] getContents()
	{
		return contents;
	}
}
