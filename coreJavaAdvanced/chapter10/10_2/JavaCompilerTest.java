package com.corejava.chapter10_2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class JavaCompilerTest
{
	public static void main(String[] args) throws FileNotFoundException
	{
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		OutputStream out = new FileOutputStream("out.txt");
		OutputStream err = new FileOutputStream("err.txt");
		System.out.println(compiler);
		System.out.println(System.getProperty("java.home"));
		int result = compiler.run(null, out, err, "com/corejava/chapter10_2/Hello.java");
		if(result == 0)
		{
			System.out.println("bingo");
		}
		else
		{
			System.out.println("oops");
		}
	}
}
