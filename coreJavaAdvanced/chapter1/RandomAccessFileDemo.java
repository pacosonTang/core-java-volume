package com.corejava.chapter1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import static java.lang.System.*;

public class RandomAccessFileDemo
{
	public static String path = System.getProperty("user.dir") + File.separator + "src" + File.separator +  
			"com" + File.separator + "corejava" + File.separator +  "chapter1" + File.separator;
	
	public static void main2(String[] args) throws IOException
	{
		insertCase(5, "xiaoTangTang", path+"binary1.dat");
	}
	/**
	 * RandomAccessFile 插入写示例：
	 * @param skip 跳过的字节数
	 * @param str 要插入的字符串
	 * @param fileName 文件路径
	 * @throws IOException 
	 */
	public static void insertCase(long skip, String str, String fileName) throws IOException
	{
		RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
		byte[] array = str.getBytes();
		raf.setLength(raf.length() + array.length);
		for (long i = raf.length()-1; i > array.length + skip - 1; i--)
		{
			raf.seek(i-array.length);
			byte temp = raf.readByte();
			raf.seek(i);
			raf.writeByte(temp);
		}
		raf.seek(skip);
		raf.write(array);
		raf.close();
		out.println("successful insertion !");
	}
	public static void main1(String[] args) throws IOException
	{
		RandomAccessFile file = new RandomAccessFile(path + "binary1.dat", "rw");
		file.writeInt(20); // 占4个字节  
		file.writeDouble(2.3); // 占8个字节
		// 这个长度写在当前文件指针的前两个字节处，可用readShort()读取
		file.writeUTF("this is a utf str");   
		file.writeBoolean(true); // 占1个字节
		file.writeShort(399); // 占2个字节
		file.writeLong(321321); // 占8个字节
		file.writeUTF("this is a also utf str"); 
		file.writeFloat(2.3f); // 占4个字节
		file.writeChar('a'); // 占2个字节
		file.seek(0); // 把文件指针位置设置到文件起始处  
		
		// 以下从file文件中读数据，要注意文件指针的位置  
		out.println("从file文件指定位置读数据");
		out.print(file.readInt() + " ");
		out.print(file.readDouble()+ " ");
		out.print(file.readUTF() + " ");
		// 将文件指针跳过3个字节，本例中即跳过了一个boolean值和short值。
		file.skipBytes(3);
		out.print(file.readLong() + " ");
		// 跳过文件中“also utf str”所占字节，注意readShort()方法会移动文件指针，所以不用加2。		
		file.skipBytes(file.readShort());
		out.print(file.readFloat() + " ");
		//以下演示文件复制操作  
		out.println("\n——————文件复制（从file到fileCopy）——————");
		file.seek(0);
		RandomAccessFile filecopy = new RandomAccessFile("filecopy.dat", "rw");
		int len = (int) file.length(); //取得文件长度（字节数）  
		byte[] array = new byte[len];
		file.readFully(array);
		filecopy.write(array);
		out.println("copy over!");
	}
	
//	测试利用多线程进行文件的写操作
	public static void main(String[] args) throws IOException
	{
		RandomAccessFile file = new RandomAccessFile(path + "binary2.dat", "rw");
		 // 预分配文件所占的磁盘空间，磁盘中会创建一个指定大小的文件  
		file.setLength(1024*1024); // 预分配 1M 的文件空间  
		file.close();
		
		 // 所要写入的文件内容  
		String s1 = "first str";
		String s2 = "second str";
		String s3 = "third str";
		
		// 利用多线程同时写入一个文件  
		 // 从文件的1024字节之后开始写入数据  
		new Thread(new FileWriterThread(1024*1, s1.getBytes()), "thread1").start();
		 // 从文件的1024*2字节之后开始写入数据  
		new Thread(new FileWriterThread(1024*2, s1.getBytes()), "thread2").start();
		 // 从文件的1024*3字节之后开始写入数据  
		new Thread(new FileWriterThread(1024*3, s1.getBytes()), "thread3").start();
		
		file = new RandomAccessFile(path + "binary2.dat", "rw");
		for (int i = 0; i < 3; i++)
		{
			out.println(file.readUTF() + " ");
		}
	}
}

//利用线程在文件的指定位置写入指定数据  
class FileWriterThread implements Runnable
{
	private int skip;
	private byte[] content;
	
	public FileWriterThread(int skip, byte[] content)
	{
		this.skip = skip;
		this.content = content;
	}
	
	@Override
	public void run()
	{
		RandomAccessFile raf = null;
		try
		{
			raf = new RandomAccessFile(RandomAccessFileDemo.path + "binary2", "rw");
			raf.seek(skip);
			raf.write(content);
		} catch (IOException e)
		{
			e.printStackTrace();
		}finally 
		{
			try
			{
				raf.close();
				out.println(Thread.currentThread().getName() + " write over!");
			} catch (Exception e2)
			{
			}
		}
	}
}



