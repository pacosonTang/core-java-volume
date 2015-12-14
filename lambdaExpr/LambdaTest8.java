package com.lambda.expr;

import static java.lang.System.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.JButton;

public class LambdaTest8
{
	public static void main(String[] args)
	{
		mainWithLambda();
	}
	public static void mainWithLambda()
	{
		out.print("WithLambdaExpr: ");
		// creating a List with string more than 2 characters
		List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
		List<String> filtered = languages.stream().filter(x->x.length()>4).collect(Collectors.toList());
		out.printf("Original List : %s, filtered list : %s %n", languages, filtered);
	}
	public static void mainWithOutLambda()
	{
		out.println("WithOutLambdaExpr:");
		 List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		 for(Integer cost : costBeforeTax)
		 {
			 double price = cost + .12 * cost;
			 out.print(price + " ");
		 }
	}
}


