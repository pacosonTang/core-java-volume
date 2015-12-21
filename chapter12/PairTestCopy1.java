package com.corejava.chapter12;

import static java.lang.System.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class PairTestCopy1
{
	public static void main(String[] args)
	{
		Pair pair = Pair.makePair(String.class);
		out.println(pair.getFirst() + " " + pair.getSecond());
	}
	public static void main5(String[] args)
	{
		Collection<Pair<String>> table = new ArrayList<>();
		Pair<String> pair1 = new Pair<>("1", "2");
		Pair<String> pair2 = new Pair<>("one", "two");
		addAll(table, pair1, pair2);
		for(Pair<String> temp : table)
		{
			out.println(temp.getFirst() + " " + temp.getSecond());
		}
	}
	
	//1st method : @SuppressWarnings("unchecked")
	//2nd method : 
	@SafeVarargs
	public static<T> void addAll(Collection<T> coll, T...ts)
	{
		Collection temp = Arrays.asList(ts);
	    coll.addAll(temp);
	}
	
	public static void main4(String[] args)
	{
		//Pair<String>[] stringPairs=new Pair<>[10];//error
		//Pair<Integer>[] intPairs=new Pair<>[10];//error
		Pair<String>[] table =(Pair<String>[]) new Pair<?>[10];
		table[0] = new Pair<String>("first", "second");
		out.println(table[0].getFirst() + " "+ table[0].getSecond());
	}
	
	public static void main3(String[] args)
	{
		Pair<String> p1 = new Pair<>();
		Pair<Integer> p2 = new Pair<>();
		if(p1.getClass() == p2.getClass())
			out.println(" they have the same original type");
		else
			out.println(" they have the different original type");
			
	}
	public static void main2(String[] args)
	{
//		Pair<double> p1 = new Pair<>();//false
		Pair<Double> p2 = new Pair<>();//true
	}
	
   public static void main1(String[] args)
   {
      String[] words = { "Mary", "had", "a", "little", "lamb" };
      Pair<String> mm = ArrayAlg.minmax(words);
      System.out.println("min = " + mm.getFirst());
      System.out.println("max = " + mm.getSecond());
   }
}

class ArrayAlg
{
   public static Pair<String> minmax(String[] a)
   {
      if (a == null || a.length == 0) return null;
      String min = a[0];
      String max = a[0];
      for (int i = 1; i < a.length; i++)
      {
         if (min.compareTo(a[i]) > 0) min = a[i];
         if (max.compareTo(a[i]) < 0) max = a[i];
      }
      return new Pair<>(min, max);
   }
}
