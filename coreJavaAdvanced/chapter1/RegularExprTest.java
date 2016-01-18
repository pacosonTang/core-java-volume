package com.corejava.chapter1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.System.*;

public class RegularExprTest
{
	public static void main(String[] args)
	{
		Pattern p;
		Matcher m;
		String input;
		
		input = "11:59am";
		p = Pattern.compile("((1?[0-9]):([0-5][0-9]))"); // 贪婪匹配
		m = p.matcher(input); 
		if(m.find())
		{
			int start = m.start();
			int end = m.end();
			out.println("start = " + start + " end = " + end);
			String match = input.substring(start, end);
			out.println(match);
		}
		out.println(m.replaceAll("#"));
		// 替换字符串可以包含对模式中群组的引用： $n 表示替换成第n 个数组
		out.println(m.replaceAll("$1"));
		out.println(m.replaceAll("$2"));
		out.println(m.replaceAll("$3"));
	}
	public static void main8(String[] args)
	{
		Pattern p;
		Matcher m;
		String input;
		
		// （干货——使用群组来定义子表达式，群组用括号（） 括起来）	
		// （干货——正则表达式中的字符类[] ）
		input = "11:59am";
		p = Pattern.compile("((1?[0-9]):([0-5][0-9]))"); 
		m = p.matcher(input); 
		if(m.find())
		{
			int start = m.start();
			int end = m.end();
			out.println("start = " + start + " end = " + end);
			String match = input.substring(start, end);
			out.println(match);
		}
	}
	
	public static void main7(String[] args)
	{
		Pattern p;
		Matcher m;
		// （干货——使用群组来定义子表达式，群组用括号（） 括起来）	
		// （干货——正则表达式中的字符类[] ）
		p = Pattern.compile("((1?[0-9]):([0-5][0-9]))[ap]m"); 
		m = p.matcher("11:59am"); 
		if(m.find())
		{
			out.println(m.groupCount());
			for (int i = 0; i <= m.groupCount(); i++)
			{
				out.print("m.start(" + i + ") = " + m.start(i) + "  ");
				out.print("m.group(" + i + ") = " + m.group(i) + "  ");
				out.print("m.end(" + i + ") = " + m.end(i) + "\n");
			}
		}
	}
	public static void main6(String[] args)
	{
		Pattern p;
		Matcher m;
		// （干货——使用群组来定义子表达式，群组用括号（） 括起来）	
		p = Pattern.compile("([a-z]+)(\\d+)"); 
		m = p.matcher("aaa2223bb"); 
		out.println("m.find() = " + m.find());   //匹配aaa2223 
		out.println("m.groupCount() = " + m.groupCount());   //返回2,因为有2组 
		out.println("m.start(1) = " + m.start(1));   //返回0 返回第一组匹配到的子字符串在字符串中的索引号 
		out.println("m.start(2) = " + m.start(2));   //返回3 
		out.println("m.end(1) = " + m.end(1));   //返回3 返回第一组匹配到的子字符串的最后一个字符在字符串中的索引位置. 
		out.println("m.end(2) = " + m.end(2));   //返回7 
		out.println("m.group(1) = " + m.group(1));   //返回aaa,返回第一组匹配到的子字符串 
		out.println("m.group(2) = " + m.group(2));   //返回2223,返回第二组匹配到的子字符串 
	}
	public static void main5(String[] args)
	{
		Pattern p;
		Matcher m;
		
		out.print("\n=== Matcher.find() ===\n");
		p = Pattern.compile("\\d+"); 
		m = p.matcher("aaa2223bb"); 
		//out.println(m.find());//匹配2223 
		if(m.find())
		{
			out.println(m.start());//返回3 
			out.println(m.end());//返回7,返回的是2223后的索引号 
			out.println(m.group());//返回2223 
		}
		
		out.print("\n=== Matcher.lookingAt() ===\n");
		Matcher m2 = p.matcher("2223bb"); 
		out.println(m2.lookingAt());   //匹配2223
		if(m2.lookingAt())
		{
			//返回0,由于lookingAt()只能匹配前面的字符串,
			// 所以当使用lookingAt()匹配时,start()方法总是返回0
			out.println(m2.start()); //返回0
			out.println(m2.end());   //返回4 
			out.println(m2.group());   //返回2223 
		}

		out.print("\n=== Matcher.matches() ===\n");
		Matcher m3 = p.matcher("2223bb"); 
		out.println(m3.matches());   //匹配整个字符串
		if(m3.matches())
		{
			out.println(m3.start());  			//
			out.println(m3.end());    
			out.println(m3.group());  
		}
	}
	
	public static void main4(String[] args)
	{
		Pattern p;
		Matcher m;
		
		p = Pattern.compile("[a-z]*ab");// +匹配0个或多个
		m = p.matcher("cab");
		out.println(m.matches()); // true
		
		p = Pattern.compile("[a-z]*+ab");// +匹配1个或多个
		m = p.matcher("cab");
		// 贪婪版本 [a-z]*+ 将匹配字符 cab， 模式 的剩余部分将无法匹配；
		out.println(m.matches()); // false
	}
	
	public static void main3(String[] args)
	{
		Pattern p;
		Matcher m;
		
		p = Pattern.compile("[Jj]ava.+");// +匹配1个或多个
		m = p.matcher("java");
		out.println(m.matches()); // false
		
		p = Pattern.compile("[Jj]ava.*");// *匹配0个或多个
		m = p.matcher("java");
		out.println(m.matches()); // true
		
		p = Pattern.compile("[Jj]ava.?");// ?匹配0个或1个
		m = p.matcher("java");
		out.println(m.matches());// true
		
		p = Pattern.compile("[Jj]ava.+");
		m = p.matcher("javaa");
		out.println(m.matches());// true
	}
	
	public static void main2(String[] args)
	{
		out.print("\n=== Pattern.pattern() ===\n");
		Pattern p = Pattern.compile("\\w+");
		out.println(p.pattern());//返回 \w+
		
		out.print("\n=== Pattern.split() ===\n");
		p = Pattern.compile("\\d+"); // d = digit
		String[] str=p.split("我的QQ是:456456我的电话是:0532214我的邮箱是:aaa@aaa.com"); 
		for (String single : str)
		{
			out.println(single);
		}
		
		out.print("\n=== Pattern.matches() ===\n");
		out.println(Pattern.matches("\\d+","2223"));//返回true 
		//返回false,需要匹配到所有字符串才能返回true,这里aa不能匹配到
		out.println(Pattern.matches("\\d+","2223aa"));
		//返回false,需要匹配到所有字符串才能返回true,这里bb不能匹配到
		out.println(Pattern.matches("\\d+","22bb23"));
		
		out.print("\n=== Matcher.pattern()===\n");
		p = Pattern.compile("\\d+"); 
		Matcher m = p.matcher("22bb23"); 
		out.println(m.pattern());//返回p 也就是返回该Matcher对象是由哪个Pattern对象的创建的
		
		out.print("\n=== Matcher.matches()===\n");
		p = Pattern.compile("\\d+"); 
		m = p.matcher("22bb23"); 
		out.println(m.matches());//返回false,因为bb不能被\d+匹配,导致整个字符串匹配未成功. 
		Matcher m2 = p.matcher("2223"); 
		out.println(m2.matches());//返回true,因为\d+匹配到了整个字符串
		
		// lookingAt()对前面的字符串进行匹配,只有匹配到的字符串在最前面才返回true 
		out.print("\n=== Matcher.lookingAt()===\n");
		p = Pattern.compile("\\d+"); 
		m = p.matcher("22bb23"); 
		out.println(m.lookingAt());//返回true,因为\d+匹配到了前面的22 
		m2 = p.matcher("aa2223"); 
		out.println(m2.lookingAt());//返回false,因为\d+不能匹配前面的aa
		
		// find()对字符串进行匹配,匹配到的字符串可以在任何位置.
		out.print("\n=== Matcher.find()===\n");
		p = Pattern.compile("\\d+"); 
		m = p.matcher("22bb23"); 
		out.println(m.find());//返回true 
		m2 = p.matcher("aa2223"); 
		out.println(m2.find());//返回true 
		Matcher m3=p.matcher("aa2223bb"); 
		out.println(m3.find());//返回true 
		Matcher m4=p.matcher("aabb"); 
		out.println(m4.find());//返回false
	}
	public static void main1(String[] args)
	{
		String patternStr = "[Ji]ava";
		String input = "java";
		
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(input);
		if(matcher.matches())
		{
			out.println("bingo!");
		}else
		{
			out.println("o m g !");
		}
	}
}
