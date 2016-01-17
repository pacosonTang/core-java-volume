package com.corejava.chapter1;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;
import java.util.zip.*;

import static java.lang.System.*;

public class MemoryMapTest
{
	private static String workDir = System.getProperty("user.dir") + File.separator + "src" + File.separator +  
			"com" + File.separator + "corejava" + File.separator +  "chapter1" + File.separator;
	private static int RECORD_SIZE = 2048;
	
	public static void main2(String[] args) throws IOException
	{
		Path path = Paths.get(workDir, "test.txt");
		// ByteBuffer.allocate 或 ByteBuffer.wrap ： 要获得缓冲区，调用它们
		ByteBuffer buffer = ByteBuffer.allocate(RECORD_SIZE);
		
		try(FileChannel channel = FileChannel.open(path))
		{
			channel.read(buffer);
			printByteBuffer("all content", buffer);// all content
			
			// put方法：将值添加到缓冲区；
			buffer.put("test for memory buffer map".getBytes());
			printByteBuffer("after put", buffer);// after put
			
			buffer.position(10);
			// flip方法：将界限设置到当前位置，并把位置复位到0；
			buffer.flip();
			printByteBuffer("after flip", buffer);// after flip
			
			buffer.position(5);
			// buffer.remaining = limit - position = 10 - 5
			// remaining方法： 现在在 remaining 方法返回整数时（它返回的值是 “界限—位置”）
			out.println("\n buffer.remaining = " + buffer.remaining());
		}
	}
	
	public static void printByteBuffer(String comment, ByteBuffer buffer)
	{
		out.println("\n === " + comment +" ===");
		for (int i = 0; i < buffer.limit(); i++)
		{
			out.print((char)buffer.get(i));
		}
	}
	
   public static long checksumInputStream(Path filename) throws IOException
   {
      try (InputStream in = Files.newInputStream(filename))
      {
         CRC32 crc = new CRC32();
   
         int c;
         while ((c = in.read()) != -1)
            crc.update(c);
         return crc.getValue();
      }
   }

   public static long checksumBufferedInputStream(Path filename) throws IOException
   {
      try (InputStream in = new BufferedInputStream(Files.newInputStream(filename)))
      {
         CRC32 crc = new CRC32();
   
         int c;
         while ((c = in.read()) != -1)
            crc.update(c);
         return crc.getValue();
      }
   }

   public static long checksumRandomAccessFile(Path filename) throws IOException
   {
      try (RandomAccessFile file = new RandomAccessFile(filename.toFile(), "r"))
      {
         long length = file.length();
         CRC32 crc = new CRC32();
   
         for (long p = 0; p < length; p++)
         {
            file.seek(p);
            int c = file.readByte();
            crc.update(c);
         }
         return crc.getValue();
      }
   }

   public static long checksumMappedFile(Path filename) throws IOException
   {
      try (FileChannel channel = FileChannel.open(filename))
      {
         CRC32 crc = new CRC32();
         int length = (int) channel.size();
         MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);
   
         for (int p = 0; p < length; p++)
         {
            int c = buffer.get(p);
            crc.update(c);
         }
         return crc.getValue();
      }
   }

   public static void main1(String[] args) throws IOException
   {
      System.out.println("Input Stream:");
      long start = System.currentTimeMillis();
      Path filename = Paths.get(args[0]);
      long crcValue = checksumInputStream(filename);
      long end = System.currentTimeMillis();
      System.out.println(Long.toHexString(crcValue));
      System.out.println((end - start) + " milliseconds");

      System.out.println("\nBuffered Input Stream:");
      start = System.currentTimeMillis();
      crcValue = checksumBufferedInputStream(filename);
      end = System.currentTimeMillis();
      System.out.println(Long.toHexString(crcValue));
      System.out.println((end - start) + " milliseconds");

      System.out.println("\nRandom Access File:");
      start = System.currentTimeMillis();
      crcValue = checksumRandomAccessFile(filename);
      end = System.currentTimeMillis();
      System.out.println(Long.toHexString(crcValue));
      System.out.println((end - start) + " milliseconds");

      System.out.println("\nMapped File:"); // 内存映射文件
      start = System.currentTimeMillis();
      crcValue = checksumMappedFile(filename);
      end = System.currentTimeMillis();
      System.out.println(Long.toHexString(crcValue));
      System.out.println((end - start) + " milliseconds");
   }
   
   public static void main3(String[] args) throws IOException
	{
		Path path = Paths.get(workDir, "test.txt");
		new Thread(new Runnable(){
			@Override
			public void run()
			{
				out.print("\n1st thread starts.\n");
				try(FileChannel channel = FileChannel.open(path, StandardOpenOption.READ))
				{
					FileLock lock = channel.tryLock(0, 10, true);
					out.println(lock.isShared() + " from 1st thread");
					lock.release();
				} catch (IOException e){e.printStackTrace();}
				out.print("\n1st thread ends.\n");
			}
		}).start();
		new Thread(new Runnable(){
			@Override
			public void run()
			{
				out.print("\n2nd thread starts.\n");
				try(FileChannel channel = FileChannel.open(path, StandardOpenOption.READ))
				{
					FileLock lock = channel.tryLock(0, 10, true);
					out.println(lock.isShared() + " from 2nd thread");
					lock.release();
				} catch (IOException e){e.printStackTrace();}
				out.print("\n2nd thread ends.\n");
			}
		}).start();
	}
   
   public static void main(String[] args) throws IOException, InterruptedException
  	{
  		Path path = Paths.get(workDir, "test.txt");
  		new Thread(new Runnable(){
  			@Override
  			public void run()
  			{
  				out.print("\n1st thread starts.\n");
  				try(FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE))
  				{
  					// 锁定第1个到第10个字节（index from 0 to 9）
  					FileLock lock = channel.tryLock(0, 10, false);
  					Thread.sleep(3000);
  					out.println(lock.isShared() + " from 1st thread");
  					lock.release();
  				} catch (IOException | InterruptedException e){e.printStackTrace();}
  				out.print("\n1st thread ends.\n");
  			}
  		}).start();
  		new Thread(new Runnable(){
  			@Override
  			public void run()
  			{
  				try(FileChannel channel = FileChannel.open(path))
  				{
  					Thread.sleep(1000);
  					out.print("\n2nd thread starts.\n");
  					ByteBuffer buffer = ByteBuffer.allocate(10);
  					//channel.position(9); // read from index 9 ,throws exception
  					channel.position(10); // just substitute 9 with 10 
  					channel.read(buffer);
  					printByteBuffer("2nd thread read buffer", buffer);
  				} catch (IOException | InterruptedException e){e.printStackTrace();}
  				out.print("\n2nd thread ends.\n");
  			}
  		}).start();  		
  	}
}










