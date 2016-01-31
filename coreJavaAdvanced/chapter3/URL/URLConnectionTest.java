package com.corejava.chapter3;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * This program connects to an URL and displays the response header data and the first 10 lines of
 * the requested data.
 * 
 * Supply the URL and an optional username and password (for HTTP basic authentication) on the
 * command line.
 * @version 1.11 2007-06-26
 * @author Cay Horstmann
 */
public class URLConnectionTest
{
   public static void main(String[] args)
   {
      try
      {
         String urlName;
         if (args.length > 0) urlName = args[0];
         else urlName = "http://horstmann.com";
         URL url = new URL(urlName);
         // 推荐使用URLConnection ，它能够得到比 URL类更多的控制功能
         URLConnection connection = url.openConnection();
         // set username, password if specified on command line
         if (args.length > 2)
         {
            String username = args[1];
            String password = args[2];
            String input = username + ":" + password;
            // Base64 编码用于将字节序列编码成可打印的ASCII字节序列（自定义的编码器）
            // 区别自定义的 Base64Encode 和 java.util.Base64 编码器
            String encoding = Base64Encode.base64Encode(input);
            // 访问有密码保护的web页面，要设置Authorization的value
            connection.setRequestProperty("Authorization", "Basic " + encoding);
         }
         connection.connect();
         // 一旦调用了 connect方法， 就可以查询响应头信息
         // getHeaderFields 方法： 返回一个 封装类响应头字段的Map 对象；
         Map<String, List<String>> headers = connection.getHeaderFields();
         for (Map.Entry<String, List<String>> entry : headers.entrySet())
         {
            String key = entry.getKey();
            for (String value : entry.getValue())
               System.out.println(key + ": " + value);
         }
         // print convenience functions
         System.out.println("----------");
         System.out.println("getContentType: " + connection.getContentType());
         System.out.println("getContentLength: " + connection.getContentLength());
         System.out.println("getContentEncoding: " + connection.getContentEncoding());
         System.out.println("getDate: " + connection.getDate());
         System.out.println("getExpiration: " + connection.getExpiration());
         System.out.println("getLastModifed: " + connection.getLastModified());
         System.out.println("----------");

         Scanner in = new Scanner(connection.getInputStream());
         // print first ten lines of contents
         for (int n = 1; in.hasNextLine() && n <= 10; n++)
            System.out.println(in.nextLine());
         if (in.hasNextLine()) System.out.println(". . .");
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }
}















