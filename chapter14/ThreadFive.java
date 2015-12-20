package com.corejava.chapter14;

import java.text.SimpleDateFormat;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.*;

public class ThreadFive
{
	public static void main2(String[] args)
	{
		 TransferRunnable run = new TransferRunnable();
		 Thread r1 = new Thread(run , "1st thread");
		 Thread r2 = new Thread(run , "2nd thread");
		 r1.start();
		 r2.start();
	}
	public static void main1(String[] args)
	{
		Runnable r = new ThreadSecurity();
		Thread t1 = new Thread(r, "first thread");
		Thread t2 = new Thread(r, "second thread");
		t1.start();
		t2.start();
	}
	public static void main3(String[] args)
	{
		 Runnable run = new ConditionTransferRunnable();
		 Thread r1 = new Thread(run , "1st thread");
		 Thread r2 = new Thread(run , "2nd thread");
		 r1.start();
		 r2.start();
	}
}

class ConditionTransferRunnable implements Runnable
{
	SynchBank bank = new SynchBank(4, 10000);
	Random r = new Random();
	@Override
	public void run()
	{
		int accountNum = bank.size(); 
		for (int i = 0; i < 2; i++)
		{
			int from = r.nextInt(10) % accountNum;
			int to = r.nextInt(10) % accountNum;
			try
			{
				bank.transfer(from, to, r.nextInt() % 1000);
				Thread.sleep(4000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}


class ThreadSecurity implements Runnable
{
	List<String> list = Collections.synchronizedList(
			new ArrayList(Arrays.asList(new String[]{"C","B","D"})));
	
	@Override
	public void run()
	{
		synchronized (this)
		{
			if(list.size() > 0)
			{
				list.removeAll(list);
			}
		}
	}
}

class TransferRunnable implements Runnable
{
	private Random r = new Random();
	private Bank bank = new Bank(4, 10000);
	
	@Override
	public void run()
	{
		int accountNum = bank.getAccountNum(); 
		for (int i = 0; i < 2; i++)
		{
			int from = r.nextInt(10) % accountNum;
			int to = r.nextInt(10) % accountNum;
			bank.transfer(from, to, r.nextInt() % 1000);
			try
			{
				Thread.sleep(4000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}

class Bank
{
	private final double[] accounts;
	private int accountNum;
	private Lock bankLock = new ReentrantLock();
	
	public Bank(int n, double init)
	{
		this.accountNum = n;
		accounts = new double[n];
		Arrays.fill(accounts, init);
	}
	
	public void transfer(int from, int to, double amount)
	{
		bankLock.lock();
		try
		{
			if(accounts[from] < amount)
				return ;
			accounts[from] -= amount;
			out.printf(Thread.currentThread().getName() + ": transfer amount=%.2f from account%d to account%d\n", amount, from ,to);
			
			accounts[to] += amount;
			out.println(Thread.currentThread().getName() + ": total suplus is " + getTotal());
		}
		finally
		{
			bankLock.unlock();
		}
	}
	
	/*public void transfer(int from, int to, double amount)
	{
		synchronized (this)
		{
			if(accounts[from] < amount)
				return ;
			accounts[from] -= amount;
			out.printf(Thread.currentThread().getName() + ": transfer amount=%.2f from account%d to account%d\n", amount, from ,to);
			
			accounts[to] += amount;
			out.println(Thread.currentThread().getName() + ": total suplus is " + getTotal());
		}
	}*/
	/*
	public synchronized void transfer(int from, int to, double amount)
	{
		if(accounts[from] < amount)
			return ;
		accounts[from] -= amount;
		out.printf(Thread.currentThread().getName() + ": transfer amount=%.2f from account%d to account%d\n", amount, from ,to);
		
		accounts[to] += amount;
		out.println(Thread.currentThread().getName() + ": total suplus is " + getTotal());
	}*/
	
	public double getTotal()
	{
		double sum = 0;
		for(double a : accounts)
			sum += a;
		return sum;
	} 
	
	public int getAccountNum()
	{
		return this.accountNum;
	}
}

