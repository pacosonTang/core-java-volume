package com.corejava.chapter13;

import java.util.ArrayDeque;

import static java.lang.System.*;

public class QueueTest
{
	public static void main(String[] args)
	{
		ArrayDeque<String> queue = new ArrayDeque<>();
		for (int i = 0; i < 6; i++)
			queue.add(i+ " ");
		String[] queueArray = queue.toArray(new String[0]); 
		// public <T> T[] toArray(T[] a)
		for (int i = 0; i < queueArray.length; i++)
			out.print(queueArray[i] + " ");
	}
}
 
