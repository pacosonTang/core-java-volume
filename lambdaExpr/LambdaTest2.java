package com.lambda.expr;

import static java.lang.System.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class LambdaTest2
{
	public static void main(String[] args)
	{
		JButton show = new JButton("show");
		show.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				out.println("LambdaTest2");	
			}
		});
	}
}


