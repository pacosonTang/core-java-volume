package com.corejava.chapter13;

import java.util.*;

import static java.lang.System.*;

public class MapTest
{
   public static void main(String[] args)
   {
      Map<String, MyEmployee> staff = new HashMap<>();
      staff.put("14", new MyEmployee("Amy"));
      staff.put("45", new MyEmployee("Francesca Miller"));
      System.out.println(staff.get("14"));
      
      Set<String> keys = staff.keySet(); //映射表视图——键集
      for(String str : keys)
    	  out.print(str + " ");
      
      out.println();////映射表视图——键值集(entry条目集)
      Set<Map.Entry<String, MyEmployee>> entries = staff.entrySet();
      for(Map.Entry e : entries)
      {
    	  out.println(e);
    	  out.println(e.getKey() + "   " +  e.getValue());
      }
      
      out.println();////映射表视图——值集
      Collection<MyEmployee> values = staff.values();
      for(MyEmployee e : values)
    	  out.println(e);
      
   }
}

class MyEmployee
{
	private String name;
	
	public MyEmployee(){} 
	
	public MyEmployee(String name)
	{
		this.name = name;
	}
	@Override
	public String toString()
	{
		return "name = " + name;
	}
}


