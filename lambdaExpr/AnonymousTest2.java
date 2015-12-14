package com.lambda.expr;

import static java.lang.System.*;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class AnonymousTest2
{
	public static void main(String[] args)
	{
		 EventQueue.invokeLater(new Runnable()
		 {
			 @Override
			 public void run()
			 {
				 JFrame frame = new MyLambdaFrame();
				 frame.setTitle("hello");
				 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 frame.setVisible(true);
			 }
		 });
	}
}

class MyLambdaFrame extends JFrame
{
	public MyLambdaFrame()
	{
		setSize(300, 200);
		JButton show = new JButton("show");
		show.addActionListener((e)->
		{
			out.println("MyLambdaFrame");
		});
		
		this.add(show);
	}
}


class MyFrame extends JFrame
{
	public MyFrame()
	{
		setSize(300, 200);
		JButton show = new JButton("show");
		show.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				out.println("LambdaTest2");	
			}
		});
		this.add(show);
	}
}


