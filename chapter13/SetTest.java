package com.corejava.chapter13;

import java.util.*;
import static java.lang.System.*;

public class SetTest
{
	public static void main(String[] args)
	{ 	
		String[] numbers = new String[]{"4","10","7","5", "4"};
		Set<String> set = new HashSet<>(Arrays.asList(numbers)); // HashSet implements Set
		set.add("4");
	    for (String str : set)
			out.print(str + " ");
	}
	
   public static void main1(String[] args)
   {
      Set<String> words = new HashSet<>(); // HashSet implements Set
      long totalTime = 0;

      Scanner in = new Scanner(System.in);
      while (in.hasNext())
      {
         String word = in.next();
         long callTime = System.currentTimeMillis();
         words.add(word);
         callTime = System.currentTimeMillis() - callTime;
         totalTime += callTime;
      }

      Iterator<String> iter = words.iterator();
      for (int i = 1; i <= 20 && iter.hasNext(); i++)
         System.out.println(iter.next());
      System.out.println(". . .");
      System.out.println(words.size() + " distinct words. " + totalTime + " milliseconds.");
   }
}
