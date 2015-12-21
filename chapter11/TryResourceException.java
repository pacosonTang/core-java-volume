package com.corejava.chapter11_4;

import static java.lang.System.out;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class TryResourceException
{
	public static void main(String[] args) throws Exception  
	{
		try(Scanner in = new Scanner(new FileInputStream("d://inputhehehe.txt")); 
				PrintWriter out = new PrintWriter("d://out.txt"))
		{
			out.println("1");
		    while(in.hasNext())
		        out.println(in.next().toUpperCase());
		}
		out.println(" write data over!");
	}
}
 
 