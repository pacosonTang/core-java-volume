package com.corejava.chapter13;

import java.util.*;

/**
 * This program demonstrates the use of a map with key type String and value type Manager.
 * @version 1.11 2012-01-26
 * @author Cay Horstmann
 */
public class MapTestOne
{
	public static void main(String[] args)
	{
		System.out.println("\n=== HashMap ===");
		main1();
		System.out.println("\n=== LinkedHashMap ===");
		main2();
	}
	public static void main2()
   {
      Map<String, Manager> staff = new LinkedHashMap();
      staff.put("14", new Manager("Amy"));
      staff.put("56", new Manager("Harry"));
      staff.put("15", new Manager("Gary"));
      staff.put("45", new Manager("Francesca"));
      
      Iterator iter1 = staff.keySet().iterator();
      while(iter1.hasNext())
    	  System.out.println(iter1.next());
       
   }
	
   public static void main1()
   {
      Map<String, Manager> staff = new HashMap<>();
      staff.put("14", new Manager("Amy"));
      staff.put("56", new Manager("Harry"));
      staff.put("15", new Manager("Gary"));
      staff.put("45", new Manager("Francesca"));
      
      Iterator iter1 = staff.keySet().iterator();
      while(iter1.hasNext())
    	  System.out.println(iter1.next());
   }
}


class Manager
{
	private String name;
	
	public Manager(){} 
	
	public Manager(String name)
	{
		this.name = name;
	}
	@Override
	public String toString()
	{
		return "name = " + name;
	}
}
