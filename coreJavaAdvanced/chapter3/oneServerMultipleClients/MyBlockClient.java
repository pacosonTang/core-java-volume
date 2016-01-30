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

public class MyBlockClient
{
	public static void main(String[] args) throws IOException, InterruptedException
	{
		try(Socket socket = new Socket("127.0.0.1", 8189))
		{
			Scanner scanner = new Scanner(socket.getInputStream());
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
			writer.println("MyBlockClient");
			System.out.println("isInterrupted = " + Thread.currentThread().isInterrupted());
			while(true)
			{
				if(scanner.hasNext())
				{
					String line = scanner.nextLine();
					System.out.println(line);
					if(line.equals("100"))
					{
						break;						
					}
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
