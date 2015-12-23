package com.corejava.chapter5_reflection;

import static java.lang.System.*;

import java.util.Properties;

public class ReflectionFactoryWithReflection
{
	public static void main(String[] args) throws Exception
	{
		MyFruit f = FactoryReflection.getInstance("com.corejava.chapter5_reflection.MyApple");
		f.eat();
	}
}
 
class FactoryReflection
{
	public static MyFruit getInstance(String className) throws Exception
	{
		MyFruit f = null;
		f = (MyFruit)Class.forName(className).newInstance();
		return f;
	}
}

interface MyFruit
{
	public abstract void eat();
}
class MyApple implements MyFruit
{
	@Override
	public void eat()
	{
		out.println("eating apple!");
	}
}
class MyOrange implements MyFruit
{
	@Override
	public void eat()
	{
		out.println("eating orange!");
	}
}


