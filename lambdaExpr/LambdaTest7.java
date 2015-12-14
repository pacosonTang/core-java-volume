package com.lambda.expr;

import static java.lang.System.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.JButton;

public class LambdaTest7
{
	public static void main(String[] args)
	{
		mainWithLambda();
	}
	public static void mainWithLambda()
	{
		out.print("WithLambdaExpr: ");
		//convert str to uppercase and join them 
		List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
		String G7Countries = G7.stream().map(x->x.toUpperCase()).collect(Collectors.joining(", "));
		out.print("the uppercase of G7 are " + G7Countries);
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


