package com.corejava.chapter14_6;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import static java.lang.System.*;

public class CyclicBarrierTest
{
	public static void main(String[] args)
	{
		int[] arr = new int[3];
		//可以提供一个可选的 障栅 动作（Barrier Action）， 当所有线程到达障栅的时候就会执行这个工作
		//CyclicBarrier cyclicBarrier = new CyclicBarrier(nthreds, barrienAction)
		CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable()
		{
			@Override
			public void run()
			{
				arr[2] = arr[0] + arr[1];
				for (int i = 0; i < arr.length; i++)
					out.println("arr[" + i +"] = " + arr[i]);
			}
		});
		
		new Thread(new MyCyclicBarrier(1, arr, cyclicBarrier)).start();
		new Thread(new MyCyclicBarrier(0, arr, cyclicBarrier)).start();
	}
}

class MyCyclicBarrier implements Runnable
{
	int id;
	int[] arr;
	CyclicBarrier cyclicBarrier;
	
	public MyCyclicBarrier(int id, int[] arr, CyclicBarrier barrier)
	{
		this.id = id;
		this.arr = arr;
		this.cyclicBarrier = barrier;
	}
	
	@Override
	public void run()
	{
		arr[id] = new Random().nextInt(100);
		try
		{
			out.println(id + " starts to have a rest!");
			cyclicBarrier.await();
			out.println(id + " wake up!");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
