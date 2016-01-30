package com.corejava.chapter3;

import static java.lang.System.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class MyInterruptibleClient
{
	public static void main(String[] args) throws IOException, InterruptedException
	{
		try(SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8189)))
		{
			Scanner scanner = new Scanner(channel);
			OutputStream outStream = Channels.newOutputStream(channel);
			PrintWriter writer = new PrintWriter(outStream, true);
			writer.println("MyInterruptibleClient");
			System.out.println("isInterrupted = " + Thread.currentThread().isInterrupted());
			while(!Thread.currentThread().isInterrupted())
			{
				if(scanner.hasNext())
				{
					String line = scanner.nextLine();
					System.out.println(line);
				}
				
				Scanner scanner1 = new Scanner(System.in);
				if(scanner1.hasNext())
				{
					writer.println(scanner1.nextLine());					
				}
			}
		}
		finally
		{
			out.println("current thread is interrupted !");
		}
	}
}
 