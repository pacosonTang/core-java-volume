package com.corejava.chapter4;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class DBMetadataTest
{
	private static String cur_dir = System.getProperty("user.dir") + File.separator +  
			"com" + File.separator + "corejava" + File.separator +  "chapter4" + File.separator;
	
	public static void main(String[] args)
	{
		try
		{
			try(Connection conn = getConnection())
			{
				String sql = "select * from student";
				Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery(sql);
				
				ResultSetMetaData metadata = rs.getMetaData();
				// index starts from 1 not 0.
				for (int i = 1; i <= metadata.getColumnCount(); i++)
				{
					// 返回该列所建议的名称.
					String colName = metadata.getColumnLabel(i);
					int colWidth = metadata.getColumnDisplaySize(i);
					// 返回给定序列号的列的最大宽度.
					System.out.println("colName = " + colName + ", colWidth = " + colWidth);
				}
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
