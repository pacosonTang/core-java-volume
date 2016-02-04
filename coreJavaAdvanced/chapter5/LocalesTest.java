package com.corejava.chapter5;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;

public class LocalesTest
{
	public static void main(String[] args)
	{
		// concurrencyFormat();
		// numberFormat();
		currencyFormatAgain();
	}
	
	// 设置货币的显示格式
	public static void currencyFormatAgain()
	{
		double num = 123.456;
		
		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		System.out.println("default: " + defaultFormat.format(num));
		
		// 说英语的中国人
		Locale chinese = new Locale("en", "CN");
		NumberFormat englishFormat = NumberFormat.getCurrencyInstance(chinese);
		System.out.println(englishFormat.format(num));
	}
	
	// number format and parse
	public static void numberFormat()
	{
		Locale locale = new Locale("de", "DE");
		NumberFormat format = NumberFormat.getNumberInstance(locale);
		double amt = 65545454.8976;
		String result = format.format(amt); //格式化double类型的数字
		System.out.println("after number format : " + result);
				
		Number result1;
		try
		{
			// 解析String类型的数字；
			result1 = format.parse("65545454.8976");
			System.out.println("after number parse : " + result1);
		} catch (ParseException e)
		{
			e.printStackTrace();
		} 
	}
	
	// format concurrency
	public static void concurrencyFormat()
	{
		Locale locale = new Locale("de", "DE");
		NumberFormat format = NumberFormat.getCurrencyInstance(locale);
		double amt = 65545454.8976;
		String result = format.format(amt);
		System.out.println(result);
	}
	public static void main1(String[] a)
	{
		// 获得作为本地OS的一部分而存放的默认Locale
		Locale l = Locale.getDefault();
		System.out.println("   Language, Country, Variant, Name");
		System.out.println(" ");
		System.out.println("Default locale: ");
		System.out.println("   " + l.getLanguage() + ", " + l.getCountry()
				+ ", " + ", " + l.getVariant() + ", " + l.getDisplayName());

		// Locale(language, contury)
		// Locale.CANADA_FRENCH == Locale(FRENCH, CANADA )
		l = Locale.CANADA_FRENCH;
		System.out.println("A predefined locale - Locale.CANADA_FRENCH:");
		System.out.println("   " + l.getLanguage() + ", " + l.getCountry()
				+ ", " + ", " + l.getVariant() + ", " + l.getDisplayName());

		l = new Locale("en", "CN");
		System.out.println("User defined locale -Locale(\"en\",\"CN\"):");
		System.out.println("   " + l.getLanguage() + ", " + l.getCountry()
				+ ", " + ", " + l.getVariant() + ", " + l.getDisplayName());

		Locale[] s = Locale.getAvailableLocales();
		System.out.println("Supported locales: ");
		for (int i = 1; i <= 10; i++)
		{
			System.out.println("   " + s[i].getLanguage() + ", "
					+ s[i].getCountry() + ", " + s[i].getVariant() + ", "
					+ s[i].getDisplayName());
		}
	}
}
