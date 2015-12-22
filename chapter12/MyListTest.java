package com.corejava.chapter12_3;

import static java.lang.System.*;

import java.lang.reflect.Array;

public class MyListTest
{
	public static void main1(String[] args)
	{
		MyList<String> myList = new MyList(5);
		myList.set(0, "hello");
		out.println(myList.get(0));
	}
	
	public static void main(String[] args)
	{
		//String[] ss = minmax("tom", "dick", "Harry"); // throws Exception
		String[] ss = minmaxReflect("tom", "dick", "Harry");
		out.println("it's ok");
	}
	
	public static <T extends Comparable> T[] minmaxReflect(T...a)
	{
		T[] mm = (T[])Array.newInstance(a.getClass().getComponentType(), 2);
		T min;
		T max;
		
		mm[0] = a[0];//min
		mm[1] = a[0];//max
		for (int i = 1; i < a.length; i++)
		{
			if(a[i].compareTo(mm[0]) < 0)
				mm[0] = a[i];
			else if(a[i].compareTo(mm[1]) > 0)
				mm[1] = a[i];
		}
		return (T[])mm;
	}
	
	public static <T extends Comparable> T[] minmax(T...a)
	{
		Object[] mm = new Object[2];
		T min;
		T max;
		
		mm[0] = a[0];//min
		mm[1] = a[0];//max
		for (int i = 1; i < a.length; i++)
		{
			if(a[i].compareTo(mm[0]) < 0)
				mm[0] = a[i];
			else if(a[i].compareTo(mm[1]) > 0)
				mm[1] = a[i];
		}
		return (T[])mm;
	}
}

class MyList<E>
{
    private Object[] elements;
    public MyList(int n)
    {
    	elements = new Object[n];
    }
    
    @SuppressWarnings("unchecked")
    public E get(int n)
    {
        return (E) elements[n]; //获取元素时进行类型转换
    }
    public void set(int n, E e)
    {	
    	if(n < elements.length && n >= 0)
    		elements[n] = e;
    }
}
