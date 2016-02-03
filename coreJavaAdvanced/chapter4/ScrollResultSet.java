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

public class ScrollResultSet
{
	private static String cur_dir = System.getProperty("user.dir") + File.separator +  
			"com" + File.separator + "corejava" + File.separator +  "chapter4" + File.separator;
	
	public static void main(String[] args)
	{
		try
		{
			try(Connection conn = getConnection())
			{
				String sql = "select name from student";
				// TYPE_SCROLL_INSENSITIVE == 结果集可以滚动但对数据库变化不敏感；
				// CONCUR_READ_ONLY == 且结果集不能用于更新数据库（default）；
				Statement stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
															ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stat.executeQuery(sql);
				
				int rowno;
				while(true)
				{
					rowno = rs.getRow();					
					if(rowno < 1)
					{
						rs.absolute(1); // 将游标设置到指定的行号上
					}
					System.out.println("rowno = " + rs.getRow());
					System.out.println("row[" + rs.getRow() + "] = " + rs.getString(1));
					if(!rs.isLast())
					{
						// 将游标向后或向前移动多行： rs.relative(n)； n为正数， 向前移动；或负数， 向后移动； n为0， 不移动；
						rs.relative(2);
					}
					if(rs.isLast())
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
