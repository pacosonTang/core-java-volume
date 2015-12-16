package com.corejava.chapter13;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import static java.lang.System.*;

public class DequeTest
{
	public static void main(String[] args)
	{
		Deque<String> deque = 
				new ArrayDeque<>(Arrays.asList(new String[]{"A"}));
		for (String str : deque)
			out.print(str + " ");
		out.println();
		
		deque.offerLast("world");
		deque.addFirst("hello");
		for (String str : deque)
			out.print(str + " ");
		out.println();
		
		deque.pollLast();//删除尾部元素，如为空，不抛出异常，而返回null
		for (String str : deque)
			out.print(str + " ");
		out.println();
		
		out.println("peekFirst() = " + deque.peekFirst());//返回第一个元素，不删除元素
		for (String str : deque)
			out.print(str + " ");
		out.println();
	}
}
