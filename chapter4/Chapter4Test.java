package com.corejava.test;

import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
import static java.lang.System.out;

class Employee
{
	private static double nextSalary;
	private String name;
	private double salary;
	private GregorianCalendar entryTime;

	public Employee(String name)
	{
		this.name = name;
		//this.salary = salary;
		out.printf("this is the func Employee(String name, double salary)\n");
	}
	
	{
		name = "rongrong";
		nextSalary = 100; //而初始化块 既可以 对静态域开放，也可以对非静态域开放
		nextSalary += 100;
		salary = nextSalary;
		out.print("this is the object initialization block! \n");
	}
	
	static 
	{
		nextSalary = 100;//静态初始化块值对静态域开放
		out.print("this is the object static initialization block! \n");
	}
	
	
	
	public Employee(double salary)
	{
		//this("Employee #", salary);
		this.salary += 20;
	}

	public Employee(String name, double salary, GregorianCalendar entryTime)
	{
		this.name = name;
		this.salary = salary;
		this.entryTime = entryTime;
	}
	/* final 实例域不能有 setter 更改器
	 public void setEntryTime(GregorianCalendar entryTime)
	{
		this.entryTime = entryTime;
	}*/ 
	
	public void setTime(GregorianCalendar entryTime)
	{
		entryTime.set(2015, 11, 11);
	}
	
	public GregorianCalendar getEntryTime()
	{
		return entryTime;
	}

	public GregorianCalendar getCloneEntryTime()
	{
		
		Object object = entryTime.clone();
		if(object instanceof GregorianCalendar)
			return (GregorianCalendar)object;
		else
			return null;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public double getSalary()
	{
		return salary;
	}

	public void setSalary(double salary)
	{
		this.salary = salary;
	}
	
}

class Hello
{
	static
	{
		System.out.println("Hello, World");
	}
}

public class Chapter4Test
{
	public static void main(String[] args)
	{
		double aDouble;
		
		aDouble = sqrt(4);
		
		out.printf("%4f", pow(aDouble, 2));
	}
	public static void main6(String[] args)
	{
		Employee employee;
		
		employee = new Employee("tangtang");
		out.print(employee.getSalary());
	}
	
	public static void main5(String[] args)
	{
		Employee employee;
		
		employee = new Employee(1000);
		out.printf("%s", employee.getName());
	}
	public static void main4(String[] args)
	{
		NumberFormat obj1 = NumberFormat.getCurrencyInstance();
		NumberFormat obj2 = NumberFormat.getPercentInstance();
		double x = 0.1;
		System.out.println(obj1.format(x));
		System.out.println(obj2.format(x));
	}
	public static void main3(String[] args)
	{
		Employee employee;
		Date date;
		
		Locale.setDefault(Locale.CHINESE);
		employee = new Employee("tangtang", 3000, new GregorianCalendar(2015, 1, 1));
		employee.setTime(employee.getEntryTime());
		date = employee.getEntryTime().getTime();
		out.println(date.toString());
		
	}
	public static void main2(String[] args)
	{
		Employee employee;
		GregorianCalendar dateCopy;
		GregorianCalendar dateCloneCopy;
		String str;
		
		employee = new Employee("tangtang", 3000, new GregorianCalendar(2015, 11, 11));
		str = employee.getName();
		str = "rongrong";
		
		out.printf(employee.getName() + "\n");
		
		dateCopy = employee.getEntryTime();
		dateCopy.add(Calendar.YEAR, 10);
		out.println(employee.getEntryTime().get(Calendar.YEAR));
		
		dateCloneCopy = employee.getCloneEntryTime();
		dateCloneCopy.add(Calendar.YEAR, 10);
		out.println(employee.getEntryTime().get(Calendar.YEAR));
	}
	public static void main1(String[] args)
	{
		GregorianCalendar gc;
		int today;
		int month;
		int weekday;
		int firstDayOfWeek;
		String[] weekdayNames;		
		
		Locale.setDefault(Locale.CHINESE);// 设置方言为 简体中文;
		gc = new GregorianCalendar(TimeZone.getTimeZone("Asia/Chongqing"),Locale.SIMPLIFIED_CHINESE);
		// 上行设置日历的时区和方言
		today = gc.get(Calendar.DAY_OF_MONTH);
		gc.set(Calendar.DAY_OF_MONTH, 1); //设置 gc 为当前月的第一天
		weekday = gc.get(Calendar.DAY_OF_WEEK);//这个月的第一天是星期几
		firstDayOfWeek = gc.getFirstDayOfWeek(); // 一周的第一天，美国是周日，中国是周一
		month = gc.get(Calendar.MONTH); //当前月份
		weekdayNames = new DateFormatSymbols().getShortWeekdays();//星期名数组
		
		//System.out.println(today + " " + month + " " + weekday + " " + firstDayOfWeek);
		for(String str : weekdayNames) //打印星期名称数组
			out.printf("%6s", str);
		out.printf("\n");
		
		for (int i = 0; i < weekday - firstDayOfWeek; i++)
			out.printf("%5s", " "); //打印前面的缩进空格

		for (int i = 1; i <= 100; i++)
		{
			gc.set(Calendar.DAY_OF_MONTH, i);
			if(gc.get(Calendar.MONTH) != month)
				break;
			if(gc.get(Calendar.DAY_OF_WEEK) == firstDayOfWeek)
				out.printf("\n");
			out.printf("%5d", i);
			if(i == today)
				out.printf("*");
		}
	}
}
