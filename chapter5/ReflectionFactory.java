package com.corejava.chapter5_reflection;

import static java.lang.System.*;

import java.util.Properties;

public class ReflectionFactory
{
	public static void main(String[] args) throws Exception
	{
		Fruit f = Factory.getInstance("Apple");
		f.eat();
	}
}
 
class Factory
{
	public static Fruit getInstance(String name)
	{
		Fruit f = null;
		if("Apple".equals(name))
		{
			f = new Apple();
		}
		if("Orange".equals(name))
		{
			f = new Orange();
		}
		return f;
	}
}

interface Fruit
{
	public abstract void eat();
}
class Apple implements Fruit
{
	@Override
	public void eat()
	{
		out.println("eating apple!");
	}
}
class Orange implements Fruit
{
	@Override
	public void eat()
	{
		out.println("eating orange!");
	}
}


