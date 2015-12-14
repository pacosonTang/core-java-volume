package com.lambda.expr;

import static java.lang.System.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.JButton;

public class LambdaTest9
{
	public static void main(String[] args)
	{
		mainWithLambda();
	}
	public static void mainWithLambda()
	{
		out.print("WithLambdaExpr: ");
		// creating a List with string more than 2 characters
		List<Integer> numbers = Arrays.asList(new Integer[]{1, 7, 6, 0, 8, 0, 1});
		List<Integer> distinct = numbers.stream().map(i-> i*i).distinct().collect(Collectors.toList());   
		out.printf("original list : %s , square without duplicates : %s", numbers, distinct);
	} 
}


