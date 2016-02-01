package com.corejava.chapter12_4;

/**
 * @version 1.10 1999-11-13
 * @author Cay Horstmann
 */

public class Employee
{
   private String name;
   private double salary;

   public native void raiseSalary(double byPercent);

   public Employee(String n, double s)
   {
      name = n;
      salary = s;
   }

   public void print()
   {
      System.out.println("name = " + name + 
    		  " , salary = " + salary);
   }

   static
   {
      System.loadLibrary("Employee");
   }
}
