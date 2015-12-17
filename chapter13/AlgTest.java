package com.corejava.chapter13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AlgTest
{
	public static void main(String[] args)
   {
      List<Integer> numbers = new ArrayList<>();
      for (int i = 1; i <= 49; i++)
         numbers.add(i);
      Comparator<Integer> comparator = new Comparator<Integer>()//比较器是匿名内部类
      {
		@Override
		public int compare(Integer o1, Integer o2)
		{
			return o2-o1;
		}
      };
      Collections.sort(numbers, comparator);
      System.out.println(numbers.subList(0, 6));
   }
	public static void main1(String[] args)
	   {
	      List<Integer> numbers = new ArrayList<>();
	      for (int i = 1; i <= 49; i++)
	         numbers.add(i);
	      Collections.shuffle(numbers);
	      List<Integer> winningCombination = numbers.subList(0, 6);
	      System.out.println(winningCombination);
	      Collections.sort(winningCombination);
	      System.out.println(winningCombination);
	   }
}
