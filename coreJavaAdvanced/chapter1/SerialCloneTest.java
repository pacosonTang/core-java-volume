package com.corejava.chapter1;

/**
   @version 1.20 17 Aug 1998
   @author Cay Horstmann
*/

import java.io.*;
import java.util.*;

public class SerialCloneTest
{  
   public static void main(String[] args)
   {  
	   MyEmployee harry = new MyEmployee("Harry Hacker", 35000, 1989, 10, 1);
	   MyEmployee harry2 = (MyEmployee) harry.clone();// clone harry
	   harry2.setName("copyHarry"); // mutate harry
      // now harry and the clone are different
      System.out.println("harry = " + harry);
      System.out.println("harry2 = " + harry2);
   }
}
class SerialCloneable implements Cloneable, Serializable
{  
   public Object clone()
   {  
      try{  
         // save the object to a byte array
         ByteArrayOutputStream bout = new ByteArrayOutputStream();
         ObjectOutputStream out = new ObjectOutputStream(bout);
         out.writeObject(this);
         out.close();
         // read a clone of the object from the byte array
         ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
         ObjectInputStream in = new ObjectInputStream(bin);
         Object ret = in.readObject();
         in.close();

         return ret;
      }  catch (Exception e)
      {  
         return null;
      }
   }
}
class MyEmployee extends SerialCloneable
{  
   private String name;
   private double salary;
   private Date hireDay;

   public MyEmployee(String n, double s, int year, int month, int day)
   {  
      name = n;
      salary = s;
      GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
      hireDay = calendar.getTime();
   }

   public void setName(String name)
	{
		this.name = name;
	}


public String getName()
   {  
      return name;
   }

   public double getSalary()
   {  
      return salary;
   }

   public Date getHireDay()
   {  
      return hireDay;
   }

   public void raiseSalary(double byPercent)
   {  
      double raise = salary * byPercent / 100;
      salary += raise;
   }

   public String toString()
   {  
      return getClass().getName()
         + "[name=" + name
         + ",salary=" + salary
         + ",hireDay=" + hireDay
         + "]";
   }
}
