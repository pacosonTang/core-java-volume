package com.corejava.chapter11_4;

import static java.lang.System.out;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FinallyClauseException
{
	public static void main(String[] args) throws Exception  
	{
		InputStream is = null;
		Exception ex = null;
		try
		{
			 try
			{
				out.println("1");
				//code that might throw exceptions
				is = new FileInputStream(new File("D://sims20150713lalal.sql"));
				out.println("2");
			}catch(Exception e)
			{
				ex = e;				
				throw e;
			}
		}finally
		{
			try
			{
				is.close();
				out.println("3");
			} catch (Exception e) // original exception might lost
			{
				if(ex == null) throw e;
			}
		}
		
		 out.println("6");
	}
	
	public static void main6(String[] args)
	{
		out.print(f(2));
	}
	
	public static int f(int n)
	{
	    try
	    {
	        int r = n * n;
	        return r;
	    }
	    finally
	    {
	          if(n==2) return 0;
	    }
	}
	
	public static void main5(String[] args)  
	{
		InputStream is = null;
		try
		{
			 try
			{
				out.println("1");
				//code that might throw exceptions
				is = new FileInputStream(new File("D://sims20150713lalal.sql"));
				out.println("2");
			}finally
			{
				out.println("5");
				is.close();
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		 out.println("6");
	}
	
	public static void main4(String[] args) throws IOException, AgeOutOfBound 
	{
		InputStream is = null;
		 try
		{
			out.println("1");
			//code that might throw exceptions
			is = new FileInputStream(new File("D://sims20150713lalal.sql"));
			checkAge(20);
			out.println("2");
		} catch (AgeOutOfBound e)
		{
			out.println("3");
			//e.printStackTrace();
			checkAge(17);
			out.println("4");
		}finally
		{
			out.println("5");
			is.close();
		}
		 out.println("6");
	}
	
	public static void main3(String[] args) throws IOException, AgeOutOfBound 
	{
		InputStream is = new FileInputStream(new File("D://sims20150713.sql"));
		 try
		{
			out.println("1");
			//code that might throw exceptions
			checkAge(17);
			out.println("2");
		} catch (Exception e)
		{
			out.println("3");
			//e.printStackTrace();
			checkAge(17);
			out.println("4");
		}finally
		{
			out.println("5");
			is.close();
		}
		 out.println("6");
	}
	
	public static void main2(String[] args) throws IOException 
	{
		InputStream is = new FileInputStream(new File("D://sims20150713.sql"));
		 try
		{
			out.println("1");
			//code that might throw exceptions
			checkAge(17);
			out.println("2");
		} catch (Exception e)
		{
			out.println("3");
			//e.printStackTrace();
			out.println("4");
		}finally
		{
			out.println("5");
			is.close();
		}
		 out.println("6");
	}
	
	public static void main1(String[] args) throws IOException 
	{
		InputStream is = new FileInputStream(new File("D://sims20150713.sql"));
		 try
		{
			out.println("1");
			//code that might throw exceptions
			//checkAge(17);
			out.println("2");
		} catch (Exception e)
		{
			out.println("3");
			e.printStackTrace();
			out.println("4");
		}finally
		{
			out.println("5");
			is.close();
		}
		 out.println("6");
	}
	
	public static void checkAge(int age) throws AgeOutOfBound
	{
		if(age < 18)
			throw new AgeOutOfBound();
	}
}

class AgeOutOfBound extends Exception
{
	public AgeOutOfBound()
	{
		out.println("sorry, age out of bound");
	}
	
	public AgeOutOfBound(String str)
	{
		out.println("msg : " + str);
		out.println("sorry, age out of bound");
	}
}
 