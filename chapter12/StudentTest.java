package com.corejava.chapter12_4;

import static java.lang.System.*;
public class StudentTest
{
	public static void main(String[] args)
	{
		CollegeStudent[] stu = new CollegeStudent[]{
				   new CollegeStudent(3),new CollegeStudent(2),
				   new CollegeStudent(5),new CollegeStudent(4)};
		sortWithoutWildCard(stu);
		for(CollegeStudent cs : stu)
		{
			out.println(cs.toString());
		}
	}
	
	// take insertion sort by id from small to larger order
	// T=CollegeStudent，然而CollegeStudent并没有继承Comparable<CollegeStudent>,
	// 仅仅是继承了Comparable<Student>,且Comparable<Student> 与 Comparable<CollegeStudent> 并没有任何关系
	// 即使 CollegeStudent extends Student
	// 但是这里并没有报错，不觉明里（理论上是说不通的，但实际上却没有报错）
	public static <T extends Comparable<T>> void sortWithoutWildCard(T[] a)
	{		// Compa
		T temp;
		int j;
		
		for (int i = 1; i < a.length; i++)
		{
			temp = a[i];
			for (j = i; j > 0 && a[j-1].compareTo(temp) > 0; j--)
			{
				a[j] = a[j-1];
			}
			a[j] = temp;
		}
	}
	
	// 这里T=CollegeStudent，理论上是说的通的（实际编译也可以通过）
	// 因为这里的泛型方法的类型限定为 <T extends Comparable<? super T>>
	// 而 Comparable<Student> 是  Comparable<? super T> 的子类，且CollegeStudent extends Comparable<Student>
	public static <T extends Comparable<? super T>> void sortWithWildCard(T[] a)
	{
		T temp;
		int j;
		
		for (int i = 1; i < a.length; i++)
		{
			temp = a[i];
			for (j = i; j > 0 && a[j-1].compareTo(temp) > 0; j--)
			{
				a[j] = a[j-1];
			}
			a[j] = temp;
		}
	}
}

class Student implements Comparable<Student>
{
	private int id;
	public Student(int id)
	{
		this.id = id;
	}
	@Override
	public int compareTo(Student o)
	{
		return this.id - o.id;
	}
	@Override
	public String toString()
	{
		return id + " ";
	}
}

class CollegeStudent extends Student //implements Comparable<CollegeStudent>
{
	public CollegeStudent(int id)
	{
		super(id);
	}
}