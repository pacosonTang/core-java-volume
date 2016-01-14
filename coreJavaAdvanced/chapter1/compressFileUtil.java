package com.corejava.chapter1;

import static java.lang.System.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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

public class compressFileUtil
{
	public static void main(String[] args) throws Exception
	{
		compressFiles("d:" + File.separator + "srcZip" + File.separator, 
				"d:" + File.separator + "desZip");
		out.println("successful compression !");
	}
	public static void compressFiles(String srcPath, String desPath) throws Exception
	{
		File srcfile = new File(srcPath);
		 
		FileOutputStream fos = new FileOutputStream(desPath + ".zip");
		ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(fos));
		
		createCompressedFile(zos, srcfile, "");
		zos.close();
	}
	
	public static void createCompressedFile(ZipOutputStream zos, File file, String dir) throws Exception
	{
		if(file.isDirectory()) //如果当前文件类型是文件夹，则进行进一步处理  
		{
			File[] files = file.listFiles(); //得到文件列表信息  
			zos.putNextEntry(new ZipEntry(dir + File.separator)); //将文件夹添加到下一级打包目录  
			dir = dir.length() == 0 ? "" : dir + File.separator; 
			for (int i = 0; i < files.length; i++) //循环将文件夹中的文件打包  
			{
				createCompressedFile(zos, files[i], dir + files[i].getName());
			}
		}
		else //当前文件类型是文件，打包处理  
		{
			FileInputStream fis = new FileInputStream(file); //文件输入流  
			zos.putNextEntry(new ZipEntry(dir));
			int j = 0;
			byte[] buffer = new byte[1024];
			while((j = fis.read(buffer)) > 0)
			{
				zos.write(buffer, 0, j); // 进行写操作 
			}
			fis.close(); //关闭输入流  
		}
	}
}
