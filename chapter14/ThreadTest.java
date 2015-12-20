package com.corejava.chapter14;

import static java.lang.System.*;

public class ThreadTest
{
	
	public static void main1(String[] args)
	{
		Runnable br2 = new BallRunnable(2);
		Runnable br3 = new BallRunnable(3);
		Thread t2 = new Thread(br2);
		Thread t3 = new Thread(br3);
		t2.start();
		t3.start();
	}
	public static void main2(String[] args)
	{
		Runnable r = new MyThread();
		new Thread(r, "first window").start();
		new Thread(r, "second window").start();
		new Thread(r, "third window").start();
	}
	public static void main3(String[] args)
	{
		Thread demo = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				for(int i=0; i < 3; i++)
					out.println(Thread.currentThread().getName());
			}
		});
		out.println("before thread start " + demo.isAlive());
		demo.start();
		out.println("after thread start " + demo.isAlive());
	}
	
	
}

class MyThread implements Runnable
{
	private int tickets = 4;
	
	@Override
	public void run()
	{
		for (int i = 0; i < 20 ; i++)
		{
			if(tickets > 0) 
				out.println(Thread.currentThread().getName() + " is selling ticket" + this.tickets--);
		}
	}
}

class BallRunnable implements Runnable
{
	private int id;
	
	public BallRunnable() {}
	public BallRunnable(int id) 
	{
		this.id = id;
	}
	
	@Override
	public void run()
	{
		out.print("ball" + id + ".run start\n");
		for (int i = 0; i < 5; i++)
		{
			out.println("ball" + id + "'s i = " + i);	
		}
		out.print("ball" + id + ".run over\n");
	}
}