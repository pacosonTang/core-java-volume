package com.corejava.chapter11.transfer.server;

import java.io.Serializable;

public class Product implements Serializable
{
	private String desc;
	private double price;
	private Warehouse location;
	
	public Product(String desc, double price)
	{
		this.desc = desc;
		this.price = price;
	}
	
	public void setLocation(Warehouse location)
	{
		this.location = location;
	}

	public String getDesc()
	{
		return desc;
	}

	public double getPrice()
	{
		return price;
	}

	public Warehouse getLocation()
	{
		return location;
	}
}
