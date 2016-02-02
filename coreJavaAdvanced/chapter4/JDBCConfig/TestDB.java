package com.corejava.chapter4;

import java.nio.file.*;
import java.sql.*;
import java.io.*;
import java.util.*;

/**
 * This program tests that the database and the JDBC driver are correctly configured.
 * @version 1.02 2012-06-05
 * @author Cay Horstmann
 */
public class TestDB
{
	private static String cur_dir = System.getProperty("user.dir") + File.separator +  
			"com" + File.separator + "corejava" + File.separator +  "chapter4" + File.separator;
	
   public static void main(String args[]) throws IOException
   {
      try
      {
         runTest();
      }
      catch (SQLException ex)
      {
         for (Throwable t : ex)
            t.printStackTrace();
      }
   }

   /**
    * Runs a test by creating a table, adding a value, showing the table contents, and removing the
    * table.
    */
   public static void runTest() throws SQLException, IOException
   {
      try (Connection conn = getConnection())
      {
         Statement stat = conn.createStatement();

         stat.executeUpdate("CREATE TABLE Greetings ("
         						+ "Message CHAR(20))");
         stat.executeUpdate("INSERT INTO Greetings VALUES ('Hello, World!')");

         try (ResultSet result = stat.executeQuery("SELECT * FROM Greetings"))
         {
            if (result.next())
               System.out.println(result.getString(1));
         }
         stat.executeUpdate("DROP TABLE Greetings");
      }
   }
   public static Connection getConnection() throws SQLException, IOException
   {
      Properties props = new Properties();
      try (InputStream in = Files.newInputStream(Paths.get(cur_dir + "database.properties")))
      {
         props.load(in); // 加载数据库连接信息的 .properties 文件
      }
      String drivers = props.getProperty("jdbc.drivers"); //数据库驱动器
      if (drivers != null) System.setProperty("jdbc.drivers", drivers);
      String url = props.getProperty("jdbc.url");
      String username = props.getProperty("jdbc.username");
      String password = props.getProperty("jdbc.password");

      return DriverManager.getConnection(url, username, password); //利用驱动管理器打开一个数据库连接
   }
}
