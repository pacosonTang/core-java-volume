package com.corejava.chapter4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class OneCommitMultipleSelect
{
	private static String cur_dir = System.getProperty("user.dir") + File.separator +  
			"com" + File.separator + "corejava" + File.separator +  "chapter4" + File.separator;
	
	// insert and select blob obj
	public static void main(String[] args)
	{
		try
		{
			try(Connection conn = getConnection())
			{
				// query starts. one sql commits multiple select statements.
				// String sql = "select id from student;select name from student";
				String sql = "call multi_select()";
				Statement stat = conn.createStatement();												
				
				boolean isResult = stat.execute(sql);
				boolean done = false;
				
			    if(isResult)
			    {
			        ResultSet result = stat.getResultSet();
			        while(result.next()) // print result set named id 
			        {
			        	System.out.println("student.id = " + result.getInt(1));
			        }
			    }
			    
			    // 重复调用 getMoreResults 方法以移动到下一个结果集
			    if((isResult = stat.getMoreResults()))
			    {
			    	ResultSet result = stat.getResultSet();
			        while(result.next()) // print result set named 'name' 
			        {
			        	System.out.println("student.name = " + result.getString(1));
			        }
			    }
				
			    System.out.println("the last stat.getMoreResults() == " + stat.getMoreResults());
				stat.close();
				conn.close();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws IOException, SQLException
	{
		Properties prop = new Properties();
		try(InputStream in = Files.newInputStream(Paths.get(cur_dir + "database.properties")))
		{
			prop.load(in);
		}
		String drivers = prop.getProperty("jdbc.drivers");
		if(drivers != null)
		{
			System.setProperty("jdbc.drivers", drivers); // register drivers for accessing database 
		}
		
		String url = prop.getProperty("jdbc.url");
		String user = prop.getProperty("jdbc.username");
		String pass = prop.getProperty("jdbc.password");
		return DriverManager.getConnection(url, user, pass);
	}
}
