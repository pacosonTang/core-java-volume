package com.lambda.expr;

import static java.lang.System.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;

public class LambdaTest3
{
	public static void main(String[] args)
	{
		List<String> features = Arrays.
				asList("Lambdas1","Default method1","Sream API1","Date and Time API1");
		features.forEach(n->out.print(n + " "));
		out.println();
		main1();
	}
	public static void main1()
	{
		 List<String> features = Arrays.
				 asList("Lambdas2","Default method2","Sream API2","Date and Time API2");
		 for(String str : features)
			 out.print(str + " "); 
	}
}


