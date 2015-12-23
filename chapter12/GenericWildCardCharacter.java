package com.corejava.chapter12_4;

import static java.lang.System.*;

public class GenericWildCardCharacter
{
	public static void main(String[] args)
	{
		Pair<Employee> pe = new Pair<>();
	}
	
	public static void main2(String[] args)
	{
		Pair<Manager> managerBuddies = new Pair<>(new Manager("first"),new Manager("second"));
		Pair<? extends Employee> wildCardBuddies = managerBuddies; //OK
		wildCardBuddies.setFirst(lowlyEmployee); // compile-time error
	}
	
	public static void main1(String[] args)
	{
		Pair<Manager> pm = new Pair<>(new Manager("first"),new Manager("second"));
		printBuddiesEmployee(pm);
		printBuddiesManager(pm);
	}

	public static void printBuddiesManager(Pair<? extends Employee> p)
	{
	    Employee first = p.getFirst();
	    Employee second = p.getSecond();
	    System.out.println(first.getName() + second.getName());
	}
	
	public static void printBuddiesEmployee(Pair<Employee> p)
	{
	    Employee first = p.getFirst();
	    Employee second = p.getSecond();
	    System.out.println(first.getName() + second.getName());
	}
}

class Employee
{
	private String name;

	public Employee(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}

class Manager extends Employee
{
	public Manager(String str)
	{
		super(str);
	}
}