package com.corejava.chapter5_reflection;

import static java.lang.System.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ReflectionFactoryProperties
{
	public static void main(String[] args) throws Exception
	{
		Properties properties = MyProperties.getProperties();
		Fruit f = FactoryProperties.getInstance(properties.getProperty("Apple")); 
		f.eat();
	}
}
class MyProperties
{
	public static Properties getProperties() throws Exception
	{
		Properties properties = new Properties();
		File file = new File("E:\\bench-cluster\\cloud-data-preprocess\\CoreJavaBasic\\src\\com\\"
		+ "corejava\\chapter5_reflection\\fruit.properties");
		if(file.exists())
		{
			properties.load(new FileInputStream(file));
		}else
		{
			properties.setProperty("Apple", "com.corejava.chapter5_reflection.ReflectionFactory.Apple");
			properties.setProperty("Orange", "com.corejava.chapter5_reflection.ReflectionFactory.Orange");
			properties.store(new FileOutputStream(file), "FRUIT CLASS");
		}
		return properties;
	}
}
class FactoryProperties
{
	public static Fruit getInstance(String className) throws Exception
	{
		Fruit f = null;
		f = (Fruit)Class.forName(className).newInstance();

		return f;
	}
}

 

