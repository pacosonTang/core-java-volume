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

public class UpdatableResultSet
{
	private static String cur_dir = System.getProperty("user.dir") + File.separator +  
			"com" + File.separator + "corejava" + File.separator +  "chapter4" + File.separator;
	
	public static void main(String[] args)
	{
		try
		{
			try(Connection conn = getConnection())
			{
				String sql = "select id, name from student";
				// TYPE_SCROLL_SENSITIVE == 结果集可以滚动且对数据库变化不敏感；
				// CONCUR_UPDATABLE == 且结果集能够应用于更新数据库；
				Statement stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
															ResultSet.CONCUR_UPDATABLE);
				// get 可更新的 结果集
				ResultSet rs = stat.executeQuery(sql);
				
				int rowno;
				while(true)
				{
					rowno = rs.getRow();					
					if(rowno < 1)
					{
						rs.absolute(1); // 将游标设置到指定的行号上
					}
					
					if(rs.getRow() % 2 != 0)
					{
						rs.updateString("name", rs.getString("name") + "_odd");
					}
					else
					{
						rs.updateString("name", rs.getString("name") + "_even");
					}
					rs.updateRow();
					// attention for rs.getString not changing row cursor
					System.out.println("row[" + rs.getRow() + "] = " + rs.getString(2));
					
					// 将游标向后或向前移动多行： rs.relative(n)； n为正数， 向前移动；或负数， 向后移动； n为0， 不移动；
					rs.relative(1);
					if(rs.isAfterLast())
					{
						break;
					}
				}
				
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
