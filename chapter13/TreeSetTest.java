package com.corejava.chapter13;

import java.util.*;

import static java.lang.System.*;

public class TreeSetTest
{  
   public static void main(String[] args)
   {  
      SortedSet<MyItem> parts = new TreeSet<>();
      parts.add(new MyItem("B", 1234));
      parts.add(new MyItem("D", 4562));
      parts.add(new MyItem("A", 9912));
      System.out.println("set sorted by part num:");
      System.out.println(parts);

      SortedSet<MyItem> set = new TreeSet<>(new
         Comparator<MyItem>() //匿名内部类
         {  
            public int compare(MyItem a, MyItem b)
            {  
               String descrA = a.getDescription();
               String descrB = b.getDescription();
               return descrA.compareTo(descrB);
            }
         });
      set.addAll(parts);
      
      System.out.println("set sorted by desc :");
      System.out.println(set);
   }
}

class MyItem implements Comparable<MyItem>
{
   private String description;
   private int partNumber;

   public MyItem(String aDescription, int aPartNumber)
   {
      description = aDescription;
      partNumber = aPartNumber;
   }
   // ordered by partNumber
   public int compareTo(MyItem other)
   {
      return Integer.compare(partNumber, other.partNumber);
   }

   public String getDescription()
   {
      return description;
   }

   public String toString()
   {
      return "[desc = " + description + 
    		  ", num = " + partNumber + "]";
   }

   public boolean equals(Object otherObject)
   {
      if (this == otherObject) return true;
      if (otherObject == null) return false;
      if (getClass() != otherObject.getClass()) return false;
      MyItem other = (MyItem) otherObject;
      return Objects.equals(description, other.description) 
    		  && partNumber == other.partNumber;
   }

   public static void main1(String[] args)
	{
		Object obj;
		SortedSet<String> set = new TreeSet<>();
		set.add("bob");
		set.add("car");
		set.add("amy");
		for (String str : set)
			out.print(str + " ");
	}
}