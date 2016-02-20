package com.corejava.chapter11.transfer.server;

public class Book extends Product
{
	private String isbn;
	
	public Book(String title, String isbn, double price)
	{
		super(title, price);
		this.isbn = isbn;
	}
	
	public String getDesc()
	{
		return super.getDesc() + " " + isbn;
	}
}
