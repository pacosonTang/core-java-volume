package com.lambda.expr;

import static java.lang.System.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.JButton;

public class LambdaReview6
{
	public static void main(String[] args)
	{
		mainWithLambda();
	}
	public static void mainWithLambda()
	{
		 List<Integer> primes = Arrays.asList(new Integer[]{2, 3, 7, 5});
		 int factor = 2;
		 primes.forEach(element->{out.print(factor*element + " ");});
	} 
	
	public static void mainWithLambdaCopy()
	{
		 List<Integer> primes = Arrays.asList(new Integer[]{2, 3, 7, 5});
		 int factor = 2;
		 //primes.forEach(element->{factor++;});
	} 
}


