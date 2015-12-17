package com.corejava.chapter13;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import static java.lang.System.*; 

public class PropertiesTest  
{
	public static void main(String[] args) throws IOException
	{
		test();
	}
	
	public static void test() throws IOException
	{
		InputStream is = new FileInputStream("D://Users//corejava//corein.properties");
		Properties prop = new Properties();
		prop.load(is);
		for (Object key : prop.keySet())
			out.print(key + " ");
		
		OutputStream os1 = new FileOutputStream("D://Users//corejava//coreout.xml");
		OutputStream os2 = new FileOutputStream("D://Users//corejava//coreout.properties");
		
		prop.storeToXML(os1, "output to .XML");
		prop.store(os2, "output to .properties");
		
		is.close();
		os1.close();
		os2.close();
		
		// load info from xml
		prop.loadFromXML(new FileInputStream("D://Users//corejava//coreout.xml"));
		out.println();
		for (Object key : prop.keySet())
			out.print(key + " ");
		
		prop.put("aaa", "110");
		prop.storeToXML(new FileOutputStream("D://Users//corejava//coreout.xml"), "update info");
	}
}
