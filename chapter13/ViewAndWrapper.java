package com.corejava.chapter13;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import static java.lang.System.*;

public class ViewAndWrapper
{
	public static void main(String[] args)
	{
		String[] alpha = new String[]{"B", "A", "D", "G", "C"};
		List<String> list = Arrays.asList(alpha);
		
		Set set = new HashSet(list);
		NavigableSet<String> nSet = new TreeSet<>(set);
		SortedSet<String> subNSet = nSet.subSet("A", true , "C", true);//String 大于等于"A"小于等于"C"(true)
		out.println();
		for (String str : subNSet) // print sub list
			out.print(str + " ");
		
		Map<Integer, String> map = new HashMap();
		map.put(1, "A");
		map.put(2, "B");
		map.put(3, "C");
		NavigableMap<Integer, String> nMap = new TreeMap<>(map);
		SortedMap<Integer, String> subNMap = nMap.subMap(1, true, 2, true); //键值大于等于 1 小于等于 2
		out.println();
		for (String str : subNMap.values()) // sub map's value
			out.print(str + " ");

		
	}
	
	public static void main6(String[] args)
	{
		String[] alpha = new String[]{"B", "A", "D", "G", "C"};
		List<String> list = Arrays.asList(alpha);
		List<String> subList = list.subList(0, 2);
		for (String str : subList) // print sub list
			out.print(str + " ");
				
		out.println();
		SortedSet<String> set = new TreeSet<>(list);
		SortedSet<String> subset = set.subSet("A", "C");//String 大于"A"小于"C"
		for (String str : subset) // print sub list
			out.print(str + " ");
		
		NavigableSet<String> nSet = new TreeSet<>(set);
		SortedSet<String> subNSet = nSet.subSet("A", "C");//String 大于"A"小于等于"C"
		
		out.println();
		SortedMap<Integer, String> map = new TreeMap<>();
		map.put(1, "A");
		map.put(2, "B");
		map.put(3, "D");
		map.put(4, "G");
		SortedMap<Integer, String> submap = map.subMap(2, 4);//key 大于2小于4
		for (String str : submap.values()) // print sub list
			out.print(str + " ");
		
		//NavigableMap<Integer, String> nmap = new TreeMap<>(map);
	}
	public static void main5(String[] args)
	{
		List<String> list = Collections.nCopies(4, "DEFAULT");
		List view = Collections.unmodifiableList(list);
		out.println(view.get(0));
		view.add("nihao");
		out.println("has Exception?");
	}
	public static void main4(String[] args)
	{
		ArrayList<String> strings = new ArrayList<>();
		List<String> safe = Collections.checkedList(strings, String.class);
		List rawList = safe;
		rawList.add(new Date(1)); // 这里编译器确实没有检测出 add 添加异常
		out.print("ok?");
	}
	public static void main3(String[] args)
	{
		ArrayList<String> strings = new ArrayList<>();
		ArrayList rawList = strings;
		rawList.add(new Date(1)); // 这里编译器确实没有检测出 add 添加异常
		
		Date date = (Date)rawList.get(0);
		out.println(date); // true
		
		String str = (String)rawList.get(0); // error
	}
	
	public static void main2(String[] args)
	{
		List<String> list = Collections.nCopies(4, "DEFAULT");
		Set set = Collections.singleton(list);
		for (Object object : set)
			out.print(object);
	}
	
	public static void main1(String[] args)
	{
		List<String> settings = Collections.nCopies(100, "DEFAULT");
		out.print(settings.get(0) == settings.get(1));
	}
}	
