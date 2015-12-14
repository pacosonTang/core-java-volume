package com.lambda.expr;

import static java.lang.System.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.JButton;

public class LambdaTest4
{
	public static void main(String[] args)
	{
		 List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
	
		 out.println("language which starts with J:");
		 filterBetter(languages, (str)->((String) str).startsWith("J"));
		 
		 out.println("language which ends with a:");
		 filterBetter(languages, (str)->((String) str).endsWith("a"));
		 
		 out.println("print all languages:");
		 filterBetter(languages, (str)->true);
		 
		 out.println("print no language:");
		 filterBetter(languages, (str)->false);
		 
		 out.println("print language whose length greater than 4:");
		 filterBetter(languages, (str)->((String)str).length()>4);
	}
	
	public static void filterBetter(List<String> names, Predicate condition)
	{
		names.stream().filter((name)->(condition.test(name))).forEach((name)->
		{
			out.println(name + " ");
		});
	}
	
	public static void filter(List<String> names, Predicate condition)
	{
		for(String name : names)
			if(condition.test(name))
			{
				out.println(name + " ");
			}
	}
}


