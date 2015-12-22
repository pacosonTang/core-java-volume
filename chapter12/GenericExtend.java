package com.corejava.chapter12_3;

import static java.lang.System.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GenericExtend
{
	public static void main(String[] args)
	{
		List<Manager> list = new ArrayList<>();
	}
	
	public static void main1(String[] args)
	{
		Manager[] m1 = new Manager[2];
		Pair<Employee> results = ArrayAlg2.minmax(m1); // ERROR
		//minmax  方法返回 Pair<Manager> ，而不是 Pair<Employee> ,并且这样的赋值是不合法的；
	}
}

class ArrayAlg2
{
   public static <T extends Comparable & Serializable> Pair<T> minmax(T[] a) 
   {
      if (a == null || a.length == 0) return null;
      T min = a[0];
      T max = a[0];
      for (int i = 1; i < a.length; i++)
      {
         if (min.compareTo(a[i]) > 0) min = a[i];
         if (max.compareTo(a[i]) < 0) max = a[i];
      }
      return new Pair<>(min, max);
   }
}

class Manager
{}

class Employee extends Manager
{}