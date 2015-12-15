package com.corejava.chapter13;

import static java.lang.System.*;
public class EqualsHashCodeTest
{
	public static void main(String[] args)
	{
		Employee e1 = new Employee(1, "xiao");
		Employee e2 = new Employee(1, "xiao");
		out.println(e1.hashCode() + " " + e2.hashCode());
		out.println(e1.getName().hashCode() + " " + e2.getName().hashCode());
		out.print(e1.getName().hashCode() == e2.getName().hashCode());
	}
	public static void main1()
	{
		Employee e1 = new Employee(1, "xiao");
		Employee e2 = new Employee(1, "xiao");
		out.println("e1 == e2?: " + (e1 == e2));
		out.println("e1.equals(e2)?: " + e1.equals(e2));
		out.println(e1.hashCode() + " " + e2.hashCode());
		out.println(e1.getName().hashCode() + " " + e2.getName().hashCode());
	}
}

class Employee
{
	private int age;
	private String name;
	 
	public Employee(int age, String name)
	{
	   this.age = age;
	   this.name = name;
	}
	@Override
	public boolean equals(Object another)
	{
		if(this == another)
			return true;
		else if(another == null)
			return false;
		else if(this.getClass() != another.getClass())
			return false;
		Employee e = (Employee)another;
		return this.age == e.age && this.name.equals(e.getName());
	}
	
	@Override
    public String toString()
	{
	   return "[name=" + name + ", age=" + age + "]";
	}
	public String getName()
	{
		return name;
	}
}
 
