package com.corejava.chapter4;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderedImageFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class MyGetPrimaryKey
{
	private static String cur_dir = System.getProperty("user.dir") + File.separator +  
			"com" + File.separator + "corejava" + File.separator +  "chapter4" + File.separator;
	
	// PreparedStatement for query operation
	public static void main3(String[] args) throws SQLException, IOException
	{		
		try
		{
			try(Connection conn = getConnection())
			{
				// String sql = "insert employee(name, salary, address) values('zhangsan',1000,'beijing')";
				String sql = "select * from employee where id > ?";
				
				PreparedStatement stat = conn.prepareStatement(sql);
				stat.setInt(1, 12);
				ResultSet rs = stat.executeQuery();
				// attention for not writing stat.executeQuery(sql);
				while(rs.next())
				{
					System.out.println(rs.getInt(1) + ", " + rs.getString(2) + ", " + rs.getDouble(3) + ", " + rs.getString(4));
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	// PreparedStatement for insert operation
	public static void main2(String[] args) throws SQLException, IOException
	{		
		try
		{
			try(Connection conn = getConnection())
			{
				// String sql = "insert employee(name, salary, address) values('zhangsan',1000,'beijing')";
				String sql = "insert into employee(name,salary,address) values(?,?,?)";
				
				//Statement stat = conn.createStatement();
				PreparedStatement stat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				String[] names = {"lisi", "wangwu", "zhaoliu"};
				double[] salary = {120, 110, 999};
				String[] address = {"chengdu", "shanghai", "shenzheng"};
				
				for (int i = 0; i < address.length; i++)
				{
					// 除非使用 set 或者 clearParameters 方法，否则所有宿主变量(?)的绑定都不会改变；
					stat.setString(1, names[i]);
					stat.setDouble(2, salary[i]);
					stat.setString(3, address[i]);
					stat.executeUpdate();
					// attention for not writing stat.executeUpdate(sql);
				}				
				sql = "select * from employee";
				ResultSet rs = stat.executeQuery(sql);
				while(rs.next())
				{
					System.out.println(rs.getInt(1) + ", " + rs.getString(2) + ", " + rs.getDouble(3) + ", " + rs.getString(4));
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main1(String[] args) throws SQLException, IOException
	{		
		try
		{
			try(Connection conn = getConnection())
			{
				String sql = "insert employee(name, salary, address) values('zhangsan',1000,'beijing')";
				Statement stat = conn.createStatement();
				stat.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
				ResultSet rs = stat.getGeneratedKeys();
				if(rs.next())
				{
					System.out.println(rs.getInt(1)); // print generated keys
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
