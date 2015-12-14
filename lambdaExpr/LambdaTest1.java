package com.lambda.expr;

import static java.lang.System.*;

public class LambdaTest1
{
	public static void main(String[] args)
	{
		new Thread(()->out.println("LambdaTest1")).start();
	}
}

