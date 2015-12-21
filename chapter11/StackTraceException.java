package com.corejava.chapter11_4;

import static java.lang.System.out;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StackTraceException
{
	public static void main(String[] args) throws Exception  
	{
		Throwable t = new Throwable();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		t.printStackTrace();
		String descriptoin = out.toString(); 
	}
}