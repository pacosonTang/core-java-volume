package com.corejava.chapter13;

import java.util.*;

import static java.lang.System.*;

public class PriorityQueueTest
{
   public static void main(String[] args)
   {
      PriorityQueue<Good> pq = new PriorityQueue<>();
      pq.add(new Good("adidas", 775));
      pq.add(new Good("gxg", 1009));
      pq.add(new Good("adivon", 699));
      
      out.println("remove minima priority queue ordered by price : ");
      out.println(pq.remove() + " ");
      out.println(pq.remove() + " ");
      out.println(pq.remove() + " ");
      
      PriorityQueue<Good> pq2 = new PriorityQueue<>(new 
    		 Comparator<Good>()
    		 {
				@Override
				public int compare(Good o1, Good o2)
				{
					return o1.getName().compareTo(o2.getName());
				}
    		 });
      
      pq2.add(new Good("adidas", 775));
      pq2.add(new Good("gxg", 1009));
      pq2.add(new Good("adivon", 699));
      out.println("remove minima priority queue ordered by name : ");
      out.println(pq2.remove() + " ");
      out.println(pq2.remove() + " ");
      out.println(pq2.remove() + " ");
   }
}

class Good implements Comparable<Good>
{
	private String name;
	private double price;
	
	public Good(String name, double price)
	{
		this.name = name;
		this.price = price;
	}
	@Override
	public int compareTo(Good o)
	{
		return Double.compare(price, o.price);		
	}
	@Override
	public String toString()
	{
		return "Good [name=" + name + ", price=" + price + "]";
	}
	public String getName()
	{
		return name;
	}
}
