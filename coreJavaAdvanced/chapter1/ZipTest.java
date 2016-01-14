package com.corejava.chapter1;

import static java.lang.System.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipTest
{
	private static String path = System.getProperty("user.dir") + File.separator + "src" + File.separator +  
			"com" + File.separator + "corejava" + File.separator +  "chapter1" + File.separator;
	private static String  dir = "D:\\testZip";
	
	public static void main(String[] args) throws IOException
	{
		multiTest();
	}
	public static void multiTest() throws IOException
	{
		File file = new File(dir);
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("D:\\testZip.zip"));
		zos.setComment("multiple files compressed into zip file");
		compressMultiFile(file, zos, "");
		zos.close();
	}
	public static void compressMultiFile(File file, ZipOutputStream zos, String dir) throws IOException
	{
		String entryName;
		ZipEntry entry;
		if(file.isDirectory())		{
			entryName = file.getName();
			entry = new ZipEntry(entryName);
			zos.putNextEntry(entry);
			File[] filelist = file.listFiles();
			for(File single:filelist)
			{
				compressMultiFile(single, zos, dir);
			}
		}
		else
		{
			entryName = file.getName();
			zos.putNextEntry(new ZipEntry(entryName));
			Scanner scanner = new Scanner(new FileInputStream(file));
			
			while(scanner.hasNextLine())
			{
				zos.write(scanner.nextLine().getBytes());
				
				//System.getProperty("line.separator") 来获得 ）行结束符字符串
				zos.write(System.getProperty("line.separator").getBytes());
			}
			scanner.close();
		}
		out.println("compress multi files over!");
	}
	
	public static void test2() throws IOException
	{
		ZipInputStream zis = new ZipInputStream(new FileInputStream(path + "testZip.zip"));
		byte[] content = new byte[2048];
		ZipEntry entry;
		Scanner in; 
		
		while((entry=zis.getNextEntry()) != null)
		{
			in = new Scanner(zis);
			while(in.hasNextLine())
			{
				out.println(in.nextLine());
			}
			zis.closeEntry();
		}
		zis.close();
	}
	public static void test1() throws IOException
	{
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(path + "testZip.zip"));
		//实例化一个名称为abc.txt 的ZipEntry对象
		ZipEntry entry = new ZipEntry("aaa.txt");
		zos.setComment(" single zip test");  //设置注释
		//把生成的ZipEntry对象加入到压缩文件中，而之后往压缩文件中写入的内容都会放在这个ZipEntry对象里面
		zos.putNextEntry(entry); 
		InputStream is = new FileInputStream(path + "output.txt");
		int len = 0;
		while((len = is.read()) != -1)
		{
			zos.write(len);
		}
		zos.closeEntry();
		is.close();
		zos.close();
		out.print("compression over!");
	}
}
