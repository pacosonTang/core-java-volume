package com.lambda.expr;

import static java.lang.System.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.JButton;

public class LambdaTest6
{
	public static void main(String[] args)
	{
		mainWithOutLambda();
		mainWithLambda();
	}
	public static void mainWithLambda()
	{
		 List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		 out.println("WithLambdaExpr: ");
		 costBeforeTax.stream().map((cost)->cost+.12*cost + " ").forEach(out::print);
	}
	public static void mainWithOutLambda()
	{
		 List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		 out.println("WithOutLambdaExpr:");
		 for(Integer cost : costBeforeTax)
		 {
			 double price = cost + .12 * cost;
			 out.print(price + " ");
		 }
	}
}


