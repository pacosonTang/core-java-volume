package com.corejava.chapter12;

import java.io.Serializable;
import java.util.*;

public class PairTest2
{
   public static void main(String[] args)
   {
      GregorianCalendar[] birthdays = 
         { 
            new GregorianCalendar(1906, Calendar.DECEMBER, 9), 
            new GregorianCalendar(1815, Calendar.DECEMBER, 10), 
            new GregorianCalendar(1903, Calendar.DECEMBER, 3), 
            new GregorianCalendar(1910, Calendar.JUNE, 22), 
         };
      Pair<GregorianCalendar> mm = ArrayAlg2.minmax(birthdays);
      System.out.println("min = " + mm.getFirst().getTime());
      System.out.println("max = " + mm.getSecond().getTime());
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
