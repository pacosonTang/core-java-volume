package com.lambda.expr;

import static java.lang.System.*;

public class AnonymousTest1
{
	public static void main(String[] args)
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				out.println("AnonymousTest1");
			}
		}).start();
	}
}
