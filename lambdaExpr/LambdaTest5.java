package com.lambda.expr;

import static java.lang.System.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.JButton;

public class LambdaTest5
{
	public static void main(String[] args)
	{
		 List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
		 
		 Predicate<String> startsWithJ = (n)->n.startsWith("J");
		 Predicate<String> fourLen = (n)->n.length() == 4;
		 filterBetter(languages, startsWithJ.and(fourLen));
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


