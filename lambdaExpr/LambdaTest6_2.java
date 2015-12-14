package com.lambda.expr;

import static java.lang.System.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.JButton;

public class LambdaTest6_2
{
	public static void main(String[] args)
	{
		mainWithOutLambda();
		mainWithLambda();
	}
	public static void mainWithLambda()
	{
		out.print("WithLambdaExpr: ");
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		double total = costBeforeTax.stream().map((cost)->cost+.12*cost).reduce((sum, cost)->sum+cost).get();
		out.println("total = " + total);
	}
	public static void mainWithOutLambda()
	{
		out.print("WithOutLambdaExpr:");
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		double total = 0;
		 for(Integer cost : costBeforeTax)
		 {
			 double price = cost + .12 * cost;
			 total += price;
		 }
		 out.println("total = " + total);
	}
}


