package com.corejava.chapter14_6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import static java.lang.System.*;

public class SemaphoreTest
{
	public static void main(String[] args)
	{
		// thread pool
		ExecutorService exec = Executors.newCachedThreadPool();
		// there must be 5 threads accessing certain source
		final Semaphore semaphore = new Semaphore(5);
		for (int i = 0; i < 10; i++)
		{
			final int num = i;
			Runnable run = new Runnable() // create 10 threads circularly
			{
				@Override
				public void run()
				{
					try
					{
						semaphore.acquire();// acquire permission
						out.println("accessing " + num);
						Thread.sleep((long)(Math.random()*10000));
						semaphore.release(); // release permission
						out.println("semaphore.availablePermits = " 
						+ semaphore.availablePermits());
					}catch(InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			};
			exec.execute(run);// thread's startup
		}
		exec.shutdown(); // shutdown all of threads
	}
}
