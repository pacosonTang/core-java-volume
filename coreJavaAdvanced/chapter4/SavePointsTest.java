package com.corejava.chapter4;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Properties;

public class SavePointsTest
{
	private static String cur_dir = System.getProperty("user.dir")
			+ File.separator + "com" + File.separator + "corejava"
			+ File.separator + "chapter4" + File.separator;

	public static void main(String[] args)
	{
		Connection conn = null;
		Savepoint savepoint1 = null;
		try
		{
			Connection conn1 = getConnection();
			conn = conn1;
			conn.setAutoCommit(false);// step1.关闭自动提交
			Statement stat = conn.createStatement();
			
			// set a Savepoint
			savepoint1 = conn.setSavepoint("savepoint1");	
			
			// step2.收集批量操作（add batch update）
			stat.addBatch("insert into student values(1,'savepoint1')");
			stat.addBatch("insert into student values(1,'savepoint2')");
			
			// step3.执行并提交该操作
			stat.executeBatch();
			// If there is no error, commit the changes.
			conn.commit();
			
			// step4.回复最初的自动提交模式
			conn.setAutoCommit(true);
			System.out.println("successful update!");
			
			conn.releaseSavepoint(savepoint1); //当不再需要保存点 的时候， 必须释放它
			conn.close();//关闭数据库连接
		 
		} catch (Exception e)
		{
			try
			{
				conn.rollback(savepoint1);//回滚
				System.out.println("failed update!");
				e.printStackTrace(); 
			} catch (SQLException e1)
			{
				e1.printStackTrace();
			}
		}
	}

	public static Connection getConnection() throws IOException, SQLException
	{
		Properties prop = new Properties();
		try (InputStream in = Files.newInputStream(Paths.get(cur_dir
				+ "database.properties")))
		{
			prop.load(in);
		}
		String drivers = prop.getProperty("jdbc.drivers");
		if (drivers != null)
		{
			System.setProperty("jdbc.drivers", drivers); // register drivers for
															// accessing
															// database
		}

		String url = prop.getProperty("jdbc.url");
		String user = prop.getProperty("jdbc.username");
		String pass = prop.getProperty("jdbc.password");
		return DriverManager.getConnection(url, user, pass);
	}
}
