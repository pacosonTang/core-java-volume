package com.corejava.chapter1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import static java.lang.System.*;

public class BinaryIO
{
	private static String path = System.getProperty("user.dir") + File.separator + "src" + File.separator +  
			"com" + File.separator + "corejava" + File.separator +  "chapter1" + File.separator;
	
	//LargeMappedFiles 荔枝 
	public static void main(String[] args) throws IOException
	{
		int length = 0x8000000; // 128mb == buffer size
		// 为了以可读可写的方式打开文件，这里使用RandomAccessFile来创建文件。 
		FileChannel channel = new RandomAccessFile(path + "randomBinary.dat", "rw").getChannel();
		//注意，文件通道的可读可写要建立在文件流本身可读写的基础之上  
		MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, length);
		//写128M的内容  
		for (int i = 0; i < length/3; i++)
		{
			mappedByteBuffer.put((byte)'x');
			mappedByteBuffer.put((byte)'y');
			mappedByteBuffer.put((byte)'z');
		}
		out.println("writing over!");
		//读取文件中间6个字节内容
		for (int i = length/2; i < length/2+6; i++)
		{
			out.print((char)mappedByteBuffer.get(i) + " ");
		}
	}
	public static void main2(String[] args) throws IOException
	{
		RandomAccessFile raf = new RandomAccessFile(path + "randomBinary.dat", "rw");
		for (int i = 0; i < 5; i++)
		{
			raf.writeInt(i); //写入基本类型int数据 
		}
		raf.close();
		raf = new RandomAccessFile(path + "randomBinary.dat", "rw");
		raf.seek(3*4);//直接将文件指针移到第3个int数据后面 
		raf.writeInt(10000);//覆盖第4个int 数据 
		raf.close();
		raf = new RandomAccessFile(path + "randomBinary.dat", "r");
		for (int i = 0; i < 5; i++)
		{
			out.print(raf.readInt() + " ");
		}
		raf.close();
	}
	public static void main1(String[] args) throws IOException
	{
		InputStreamReader reader = new InputStreamReader(System.in);
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(path + "binary.dat"));
		
		int t;
		out.println("input data: ");
		while((t=in.read()) != 48)
		{
			out.print(t + " ");
			dos.writeInt(t);
		}
		dos.flush();
		dos.close();
		
		DataInputStream dis = new DataInputStream(new FileInputStream(path + "binary.dat"));
		for (int i = 0; i < dis.available(); i++)
		{
			out.print(dis.readInt() + " ");
		}
	}
}
