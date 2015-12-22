package com.corejava.chapter12_3;

import static java.lang.System.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
public class GenericErasure
{
	public static void main(String[] args)
	{
		
	}
}



class MyCalendarNoneGeneric implements Comparable
{
	@Override
	public int compareTo(Object o)
	{
		return 0;
	}
}
class MyGregorianCalendarNoneGeneric extends MyCalendarNoneGeneric implements Comparable
{
} // ERROR


class MyCalendar implements Comparable<Calendar>
{
	@Override
	public int compareTo(Calendar o)
	{
		return 0;
	}
}
class MyGregorianCalendar extends MyCalendar implements Comparable<GregorianCalendar>
{
} // ERROR

class PairErasure<T>
{
	private T first;
	private T second;
	
	/*
    public boolean equals(T value) 
    {
    	return first.equals(value) && second.equals(value); 
    }*/
}
