package com.corejava.chapter13;

import java.util.BitSet;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class LegacyTest
{
	public static void main(String[] s)
   {
      int n = 20;
      long start = System.currentTimeMillis();
      BitSet b = new BitSet(n + 1);
      int count = 0;
      int i;
      for (i = 2; i <= n; i++)
         b.set(i);//¿ªÆô×´Ì¬
      i = 2;
      while (i * i <= n)
      {
         if (b.get(i)) //²é¿´×´Ì¬
         {
            count++;
            int k = 2 * i;
            while (k <= n)
            {
               b.clear(k); //¹Ø±Õ×´Ì¬
               k += i;
            }
         }
         i++;
      }
      while (i <= n)
      {
         if (b.get(i)) count++;
         i++;
      }
      long end = System.currentTimeMillis();
      System.out.println(count + " primes");
      System.out.println((end - start) + " milliseconds");
   }

	public static void main2(String[] args)
	{
		List<Integer> numbers = new LinkedList<>();
		for (int i = 1; i <= 5; i++)
	         numbers.add(i);
	    Enumeration e = Collections.enumeration(numbers);
	    while(e.hasMoreElements())
	    	  System.out.print(e.nextElement() + " ");
	}
	
	public static void main1(String[] args)
	{
		Hashtable<Integer, String> numbers = new Hashtable<>();
	      for (int i = 1; i <= 5; i++)
	         numbers.put(i, "hashtable" + i);
		
	      Enumeration<String> e = numbers.elements();
	      while(e.hasMoreElements())
	    	  System.out.println(e.nextElement());
	}
}
