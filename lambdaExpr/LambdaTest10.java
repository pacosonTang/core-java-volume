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

public class LambdaTest10
{
	public static void main(String[] args)
	{
		mainWithLambda();
	}
	public static void mainWithLambda()
	{
		out.print("WithLambdaExpr: ");
		List<Integer> primes = Arrays.asList(29, 2, 3, 5, 7, 11, 17, 13, 19);
		IntSummaryStatistics statistics = 
				primes.stream().mapToInt((x)->x).summaryStatistics();   
		out.println("maximum prime :" + statistics.getMax());
		out.println("minimum prime :" + statistics.getMin());
		out.println("average:" + statistics.getAverage());
		out.println("sum:" + statistics.getSum());
	} 
}


