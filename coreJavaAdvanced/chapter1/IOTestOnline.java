package com.corejava.chapter1;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOTestOnline
{
	public static void main(String[] args) throws IOException
	{
		out.print("内存中采用 Unicode编码: ");
		char c = '好';
		int lowBit = c & 0xFF;
		int highBit = (c & 0xFF00) >> 8;
		
		out.println(" " + lowBit + " " + highBit);
		String s = "好";
		
		out.print("本地操作系统默认字符编码： ");
		readBuffer(s.getBytes());
		
		out.print("采用 GBK 字符编码： ");
		readBuffer(s.getBytes("GBK"));
		
		out.print("采用  UTF-8 字符编码： ");
		readBuffer(s.getBytes("UTF-8"));
	}
	public static void readBuffer(byte[] buffer) throws IOException
	{
		ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
		int data;
		while((data = bais.read()) != -1)
		{
			out.print(data + " ");
		}
		out.print("\n");
		bais.close();
	}
	
	public static void main8(String[] args) throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		FileWriter fw = new FileWriter("src/com/corejava/chapter1/output.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		String s;
		
		out.println("please input : ");
		while((s = br.readLine()).length() > 0)
		{
			bw.write(s, 0, s.length());
			bw.write("\n");
		}
		bw.flush();
		out.println("writing over!");
		br.close();
		bw.close();
	}
	public static void main7(String[] args)
	{
		try
		{
			FileInputStream fis = new FileInputStream("src/com/corejava/chapter1/test.txt");
			FileOutputStream fos = new FileOutputStream("src/com/corejava/chapter1/output.txt");
			int c;
			
			while((c = fis.read()) != -1)
			{
				fos.write(c);
			}
			fis.close();
			fos.close();
			out.println("successful writing! ");
		} catch (Exception e)
		{
			err.println("failed writing " + e);
		}
	}
	public static void main6(String[] args)
	{
		try
		{
			out.println("please input:");
			int count;
			int n = 512;
			byte[] buffer = new byte[n];
			
			count = System.in.read(buffer);
			FileOutputStream fos = new FileOutputStream(""
					+ "src/com/corejava/chapter1/test.txt");
			fos.write(buffer, 0, count);
			fos.close();
			out.println("save to the file");
			
		} catch (Exception e)
		{
			out.println("failed writing!");
		}
	}
	
	public static void main5(String[] args)
	{
		try
		{
			FileInputStream fis = new FileInputStream(""
					+ "src/com/corejava/chapter1/CombineStreamFilter.java");
			int n = 512;
			byte[] buffer = new byte[n];
			while((fis.read(buffer, 0, n) != -1) && n > 0)
			{
				out.println(new String(buffer));
			}
			fis.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main4(String[] args) throws IOException
	{
		File dir = new File("\\root");
		File f1 = new File(dir, "fileOne.txt");
		File f2 = new File(dir, "fileTwo.txt");
		
		if(!dir.exists())
		{
			dir.mkdir();
		}
		if(!f1.exists())
		{
			f1.createNewFile();
		}
		if(!f2.exists())
		{
			f2.createNewFile();
		}
		out.println("f1's absolute path = " + f1.getAbsolutePath());
		out.println("f1 can read ? = " + f1.canRead());
		out.println("f1's length = " + f1.length());
		
		String[] fileList;
		int count = 0;
		fileList = dir.list();
		for (int i = 0; i < fileList.length; i++)
		{	
			count++;
			out.println(fileList[i] + " is in the dir !");
		}
		out.println("there are " + count + "files in the dir !");
	}
	public static void main3(String[] args)
	{
		String s;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		out.println("Unix系统: ctrl-d 或 ctrl-c 退出"  
                + "\nWindows系统: ctrl-z 退出"); 
		try
		{
			s = br.readLine();
			while(s != null) 
			{
				out.println("read :" + s);
				s = br.readLine();
			}
			in.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main2(String[] args)
	{
		char b;
		try
		{
			out.println("please input: ");
			// read method return ascii code responding to the char
			while((b = (char)System.in.read()) != '#') 
			{
				out.print((char)b);
			}
			out.println("\n accept input over");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main1(String[] args)
	{
		for (int i = 0; i < args.length; i++)
		{
			out.print("args[" + i + "] = " + args[i] + " , ");
		}
	}
}
