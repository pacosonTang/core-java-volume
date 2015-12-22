package com.corejava.chapter12_3;

import static java.lang.System.*;

public class MyListTest
{
	public static void main1(String[] args)
	{
		MyList<String> myList = new MyList(5);
		myList.set(0, "hello");
		out.println(myList.get(0));
	}
	
	public static <T extends Comparable> T[] minmax(T...a)
	{
		Object[] mm = new Object[2];
		
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
