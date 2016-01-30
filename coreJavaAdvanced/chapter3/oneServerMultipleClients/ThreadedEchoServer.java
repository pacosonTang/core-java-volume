package com.corejava.chapter3;

import java.io.*;
import java.net.*;
import java.util.*;

import static java.lang.System.*;

public class ThreadedEchoServer
{  
   public static void main(String[] args )
   {  
      try
      {  
         int i = 1;
         ServerSocket s = new ServerSocket(8189);

         while (true)
         {  
            Socket incoming = s.accept();
            System.out.println("successful link with thread  " + i);
            Runnable r = new ThreadedEchoHandler(incoming);
            Thread t = new Thread(r);
            t.start();
            i++;
         }
      }
      catch (IOException e)
      {  
         e.printStackTrace();
      }
   }
}

/**
   This class handles the client input for one server socket connection. 
*/
class ThreadedEchoHandler implements Runnable
{ 
   private Socket incoming;
   private String client;
   /**
      Constructs a handler.
      @param i the incoming socket
   */
   public ThreadedEchoHandler(Socket i)
   { 
      incoming = i;       
   }

   public void run()
   {  
      try
      {  
         try
         {
            InputStream inStream = incoming.getInputStream();
            OutputStream outStream = incoming.getOutputStream();
            
            Scanner in = new Scanner(inStream);         
            PrintWriter writer = new PrintWriter(outStream, true /* autoFlush */);
            
            if(in.hasNextLine())
            {
            	client = in.nextLine();
            }
            writer.println("from server: " + client + ", Hello! connection created! " );
            
            // echo client input
            boolean done = false;
            while (!done)
            {  
	        	if(in.hasNextLine())
	        	{
	        		String line = in.nextLine();
	        		out.println("from client["+ client + "]: " + line);
	        		writer.println("from server: " + client + " said " + line);            
	                if (line.trim().equals("BYE"))
	                {
	                   done = true;
	                }
	        	}  
            } 
         } 
         finally
         {
            incoming.close();
            System.out.println("server close");
         }
      }
      catch (IOException e)
      {  
         e.printStackTrace();
      }
   }
}
