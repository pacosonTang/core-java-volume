package com.corejava.chapter14;

import static java.lang.System.out;

import java.util.Date;

public class MultiThreadFour
{
	public static void main(String[] args)
	{
		Thread demo = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				out.println("cur time = " + System.currentTimeMillis());
				try
				{
					Thread.sleep(1000);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
				for (int i = 0; i < 3; i++)
					out.print(" " + i);
				out.println("\ncur time = " + System.currentTimeMillis());
			}
		}, "thread forced to execute");
		demo.start();
	}
}
