package com.corejava.chapter8;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class PersistentTest
{
	public static void main(String[] args)
	{
		XMLEncoder e = null;
		// 持久化 java类到 XML
		try
		{
			e = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream("com/corejava/chapter8/persistent.xml")));
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		Hello hello = new Hello();
		hello.setMsg("this is a test");
		hello.setSpeaker("persistent tang");
		e.writeObject(hello);
		e.close();
		// 持久化处理结束
		
		System.out.println("write Sucess");

		// 读取持久化对象
		XMLDecoder d = null;
		try
		{
			d = new XMLDecoder(new BufferedInputStream(
					new FileInputStream("com/corejava/chapter8/persistent.xml")));
		} catch (Exception f)
		{
			f.printStackTrace();
		}
		Object result = d.readObject();
		Hello test = (Hello) result;
		System.out.println(test.getMsg() + test.getSpeaker());
		// 读取持久化对象结束
	}
}













