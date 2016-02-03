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

public class JDBCReadWriteImage
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
				// inserting starts.
				String sql = "insert into employee(name, headportrait) values(?,?)";
				PreparedStatement insertStat = conn.prepareStatement(sql);
				insertStat.setString(1, "imageAdmin");				
				insertStat.setBlob(2, new FileInputStream(new File(cur_dir+"jdbc.jpg")));
				if(insertStat.executeUpdate() != 0)
				{
					System.out.println("successful inserting!");
				}
				else
				{
					System.out.println("failed inserting!");
				}
				// inserting ends.
				
				// query starts.
				sql = "select headportrait from employee where name = 'imageAdmin'";
				Statement stat = conn.createStatement();								
				ResultSet rs = stat.executeQuery(sql);
				
				int i = 1;
				while(rs.next())
				{
					Blob blob = rs.getBlob(1);
					InputStream instream = blob.getBinaryStream();
					OutputStream outstream = new FileOutputStream(new File(cur_dir + "blog" + (i++) +".jpg"));
					
					byte[] buffer = new byte[1024];
					int readLength = -1;
					while((readLength = instream.read(buffer)) != -1)
					{
						outstream.write(buffer, 0, readLength);
					}
					
					System.out.println("successful query and saving the file !");
				}
				// query ends.
				
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
