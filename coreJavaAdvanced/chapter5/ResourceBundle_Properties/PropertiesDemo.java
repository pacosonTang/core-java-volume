package com.corejava.chapter5;

import java.io.File;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class PropertiesDemo
{
	// 显示与给定Locale相关的.properties文件中的key对应的value
	static void displayValue(Locale locale, String key)
	{
		ResourceBundle labels = ResourceBundle.getBundle("LabelBundle", locale);
		String value = labels.getString(key);
		System.out.println("locale = " + locale.getDisplayName() + 
				", key = " + key + ", value = " + value);
	}
	// 迭代与给定Locale相关的.properties文件中的keys
	static void iterateKeys(Locale locale)
	{
		ResourceBundle labels = ResourceBundle.getBundle("LabelBundle", locale);
		Enumeration<String> keys = labels.getKeys();
		while(keys.hasMoreElements())
		{
			String key = keys.nextElement();
			String value = labels.getString(key);
			System.out.println("locale = " + locale.getDisplayName() + 
					", key = " + key + ", value = " + value);
		}
	}
	
	public static void main(String[] args)
	{
		Locale.setDefault(Locale.ENGLISH);
		Locale[] supportedLocales = 
		{
			Locale.CHINA,
			Locale.GERMAN,
			Locale.ENGLISH
		};
		
		System.out.println("=== the output of method displayValue is as follows: ===\n");
		for (int i = 0; i < supportedLocales.length; i++)
		{
			displayValue(supportedLocales[i], "s2");
		}
		System.out.println();
		System.out.println("=== the output of method iterateKeys is as follows: ===\n");
		iterateKeys(supportedLocales[0]);
	}
}
