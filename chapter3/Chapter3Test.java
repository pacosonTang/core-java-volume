package com.corejava.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Chapter3Test
{
	
	public static void main15(String[] args)
	{
		int max;
		int[][] jaggedArray;
		
		max = 5;
		jaggedArray = new int[max][];
		
		for(int i=0; i < max; i++)
			jaggedArray[i] = new int[i+1];
		for(int[] temp : jaggedArray)
		{
			for(int value : temp)
				System.out.print(value);
			System.out.printf("\n");
		}
	}
	public static void main14(String[] args)
	{
		int[] data = {10, 9, 1, 5, 6};
		int n;
		int r;
		
		n = 100;
		r = (int)(Math.random() * n); // 0~99之间的随机数
		
		System.out.println("the random is " + r);
		System.out.println(Arrays.toString(data));
		Arrays.sort(data);
		System.out.println(Arrays.toString(data));
	}
	public static void main13(String[] args)
	{
		int[] smallPrimes = {1, 2, 3, 4, 5};
		int[] luckyNumbers;
		
		luckyNumbers = smallPrimes;
		
		luckyNumbers[3] = 12 ;// 现在 smllPrimes[3] 也是 12 了
		System.out.println(smallPrimes[3]);
		
		luckyNumbers = Arrays.copyOf(smallPrimes, smallPrimes.length);
		luckyNumbers[3] = 22 ;// 现在 smllPrimes[3] 没有变，12 not 22
		System.out.println(luckyNumbers[3]);
	}
	public static void main12(String[] args)
	{
		int n;
		
		n = 1;
		
		break_while:
		while(n++ < 10)
		{
			if(n == 7)
				break break_while;
		}
		
		break_if:
		if(n == 7) 
		{
			System.out.printf("%s", "bingo");
			break break_if;			
		}
		else
			System.out.printf("%s", "never bingo");
	}
	
	public static void main11(String[] args) throws FileNotFoundException, IOException
	{
		Scanner in;
		PrintWriter pw;
		String str;
		String filePath;
		String dir;
				
		filePath = "D:\\classical_books\\java_set\\temp.txt";
		in = new Scanner(Paths.get(filePath));		
		str = in.nextLine();
		in.close();
		
		System.out.println(str);
		dir = System.getProperty("user.dir");
		System.out.println(dir);
		
		pw = new PrintWriter(filePath);
		pw.write("hello, test for PrintWriter");
		pw.flush();
		pw.close();
	}
	
	public static void main10(String[] args)
	{
		double intData;
		String message;
		String name;
		int age;
		
		intData = 123123123.123;
		name = "pacoson";
		age = 10;
		
		System.out.printf("%,+.2f\n", intData); //java SE 5.0 沿用了C语言库函数中的printf 方法
		message = String.format("hello, %s. next year, you'll be %d", name , age);
		System.out.println(message);
		System.out.printf("%1$s %2$tB %2$te, %2$tY ", "Due date:", new Date());
	}
	
	public static void main9(String[] args)
	{
		Scanner scanner;
		int intData;
		double doubleData;
		String str;
		String word;
		
		scanner = new Scanner(System.in);
		
		System.out.println("input int data:");
		intData = scanner.nextInt();
		System.out.println("input a line:");
		str = scanner.nextLine(); // nextLine 方法将输入一行：
		System.out.println("input a word");
		word = scanner.next(); // 要想读取一个单词（以空白符作为分隔符）
		System.out.println("input double data:");
		doubleData = scanner.nextDouble();
		
		System.out.println(intData + " " + doubleData  + " " +  str + " " + word );
	}
	public static void main8(String[] args)
	{
		StringBuilder sb;
		
		sb = new StringBuilder();
		
		sb.append('a'); //添加单个字符
		sb.append("nihao");//添加一个字符串
		System.out.println(sb);
	}
	public static void main7(String[] args)
	{
		String emptyStr;
		String nullStr;
		
		emptyStr = "";
		nullStr = null;
		
		System.out.println("emptyStr = [" + emptyStr + "], nullStr = " + nullStr);
	}
	public static void main6(String[] args)
	{
		 String greeting;
		 
		 greeting = "hello";
		 
		 System.out.println(greeting == "hello");
		 System.out.println(greeting.substring(0,3) == "hel");
		 greeting = greeting.substring(0,3) + "p!";//拼接
		 System.out.println(greeting);
	}
	
	enum Size {SMALL, MEDIUM, LARGE, EXTRA_LARGE};
	public static void main5(String[] args)
	{
		Size s; 
		
		s = Size.MEDIUM;
		switch (s)
		{
		case SMALL:		
			break;
		default:
			break;
		}
		System.out.println(s);
	}
	
	public static void main4(String[] args)
	{
		double x; 
		int oneX;
		int twoX;
		byte byteData;
		
		x = 9.997;
		oneX = (int)x;
		twoX = (int)Math.round(x);
		byteData = (byte)300;
		
		System.out.println(oneX + " " + twoX + " " + byteData);
	}
	public static void main3(String[] args)
	{
		int intData;
		long longData;
		double doubleData;
		float floatData;
		
		intData = 10;
		longData = 10L;
		doubleData = 10;
		floatData = 10;
		
		doubleData += floatData;
		floatData += longData;
		longData += intData;
		
		System.out.println(intData + " " + longData + " " + doubleData + " " + floatData);
	}
	public static void main2(String[] args)
	{
		 int[] array1 = {1, 2, 3, 4, 5};
		 int[] zeroArray;
		 int[] nullArray;
		 
		 zeroArray = new int[0];
		 nullArray = null;
		 
		 for(int temp : array1)
			 System.out.print(temp + " ");
		 System.out.println(Arrays.toString(array1));
		 
		 System.out.println(Arrays.toString(zeroArray));
		 System.out.println(Arrays.toString(nullArray));
	}
	
	public static void main1(String[] args)
	{
		int temp;
		BigDecimal data1;
		BigDecimal data2;
		
		temp = 9;
		data1 = BigDecimal.valueOf(2.0);
		data2 = BigDecimal.valueOf(1.1);
		
		System.out.println(2.0-1.1);
		System.out.println(data1.subtract(data2));
	}
}
 