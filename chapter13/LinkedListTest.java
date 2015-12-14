package com.corejava.chapter13;

import java.util.*;
import static java.lang.System.*;

public class LinkedListTest
{
	public static void main(String[] args)
	{
	    String[] numbers = new String[]{"4","10","7","5"};
	    LinkedList<String> list = new LinkedList<>(Arrays.asList(numbers));
	    ListIterator<String> iter1 = list.listIterator();
	    System.out.println("next index = " + iter1.nextIndex());
	    System.out.println("previous index = " + iter1.previousIndex());
	}
	
	public static void main4(String[] args)
	{
	    String[] numbers = new String[]{"4","10","7","5"};
	    LinkedList<String> list = new LinkedList<>(Arrays.asList(numbers));
	    ListIterator<String> iter1 = list.listIterator();
	    ListIterator<String> iter2 = list.listIterator();
	    
	    iter1.next(); // returns first element
	    iter1.remove();
	    iter2.next();	    	  
	}
	
	public static void main3(String[] args)
	{
	    String[] numbers = new String[]{"4","10","7","5"};
	    LinkedList<String> list = new LinkedList<>(Arrays.asList(numbers));
	    ListIterator<String> iter = list.listIterator();
	    iter.next(); // returns first element
	    iter.set("newValue"); // sets first element to newValue
	    for(String str : list)
    		out.print(str + " ");
	}
    public static void main2(String[] args)
    {
    	String[] numbers = new String[]{"4","10","7","5"};
    	LinkedList<String> list = new LinkedList<>(Arrays.asList(numbers));
    	ListIterator<String> listIter = list.listIterator();
    	listIter.add("tang");
    	listIter.add("xiao");
    	
    	for(String str : list)
    		out.print(str + " ");
    	
    	out.print("\n ordered traversal:");
    	while(listIter.hasNext())
    	{
    		String str = listIter.next();
    		out.print(str + " ");
    	}
    	
    	out.print("\n reverse traversal:");
    	while(listIter.hasPrevious())
    	{
    		String str = listIter.previous();
    		out.print(str + " ");
    	}
    }
	
   public static void main1()
   {
	   List<String> staff = new LinkedList<>();
	   
	   staff.add("amy");
	   staff.add("bob");
	   staff.add("carl");
	   Iterator iter = staff.iterator();
	   iter.next(); // visit first element
	   iter.next(); // visit second element
	   iter.remove(); // remove last visited element
	   for (String str : staff)
		   out.print(str + " ");
   }
}
