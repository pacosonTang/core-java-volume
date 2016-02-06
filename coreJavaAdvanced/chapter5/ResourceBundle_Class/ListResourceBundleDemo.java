package com.corejava.chapter573;

import java.util.Locale;
import java.util.ResourceBundle;

public class ListResourceBundleDemo
{
	// 显示与给定Locale相关的.properties文件中的key对应的value
	static void displayValue(Locale locale)
	{
		ResourceBundle stats = ResourceBundle.getBundle("com.corejava.chapter573.StatsBundle", locale);
		Integer gdp = (Integer) stats.getObject("GDP");
		Integer population = (Integer) stats.getObject("Population");
		Double literacy = (Double)stats.getObject("Literacy");
		
		System.out.println("locale = " + locale.getDisplayName() + 
				", gdp = " + gdp + ", population = " + population + ", literacy = " + literacy);
	}
	
	public static void main(String[] args)
	{
		Locale.setDefault(Locale.US);
		Locale[] locales = 
			{
				new Locale("en", "CA"),
				new Locale("de", "DE"),
				new Locale("zh", "CN") 
			};
		
		for (int i = 0; i < locales.length; i++)
		{
			displayValue(locales[i]);
		}
	}
}
