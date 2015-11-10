package com.corejava.test;

import static java.lang.System.out;

public class Chapter5Test
{
	public enum Size {SMALL, MEDIUM, LARGE, EXTRA_LARGE};
	
	public enum SizeSibling
	{
		SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");
	    private String abbreviation;
	    
	    private SizeSibling(String abbreviation) 
	    {
	    	this.abbreviation = abbreviation;
	    }
	    
	    public String getAbbreviation() 
	    {
	    	return abbreviation; 
    	}
	}
	
	public static void main(String[] args)
	{
		Size s ; 
		SizeSibling[] sizeSiblings;
		
		//s = Enum.valueOf(Size.class, "MALL");error
		s = Enum.valueOf(Size.class, "SMALL");
		out.printf("s = %s\n", s);
		
		sizeSiblings = SizeSibling.values();
		for(SizeSibling ss : sizeSiblings)
		{
			out.printf("%s ", ss);
			out.printf("%s ", ss.ordinal());
		}
	}
	
	public static void main2(String[] args)
	{
		out.printf("max = %2f", 
				max(new double[]{10, 100, 1, 2, 3, 4, 5}));
	}
	
	public static double max(double... values)
	{
		double largest ;
		
		largest = Double.MIN_VALUE;
		for(double v: values) 
			if(v > largest)
				largest = v;
		return largest;
	}
	
	public static void main1(String[] args)
	{
		int n;
		
		n = 100;
		
		System.out.printf("%d %s", new Object[] {new Integer(n), "widgets"} );
		test(new Object[] {new Integer(n), "widgets"});
	}

	public static void test(Object...objects)//Object...objects == Object[] objects
	{
		for(Object obj : objects)
			System.out.println(obj.toString());
	}
}
