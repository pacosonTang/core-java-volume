package com.corejava.chapter13;

import static java.lang.System.*;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

public class EnumSetAndEnumMapTest
{
	enum Weekday {MON, TUE, WED, THUR, FRI, SAT, SUN}
	public static void main(String[] args)
	{
		EnumMap<Weekday, Integer> map = new EnumMap<>(Weekday.class);
		map.put(Weekday.MON, 1);
		map.put(Weekday.SUN, 7);		
		
		for(Map.Entry<Weekday, Integer> e : map.entrySet())
			out.println(e);
	}
	public static void main1(String[] args)
	{
		EnumSet<Weekday> always = EnumSet.allOf(Weekday.class);
		EnumSet<Weekday> never = EnumSet.noneOf(Weekday.class);
		EnumSet<Weekday> workday= EnumSet.range(Weekday.MON, Weekday.FRI);
		EnumSet<Weekday> mwf = EnumSet.of(Weekday.MON, Weekday.WED, Weekday.FRI);
		
		out.println(always);
		out.println(never);
		out.println(workday);
		out.println(mwf);
	}
}
