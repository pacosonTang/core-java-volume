package com.corejava.chapter11.activation.download;

import java.io.IOException;

public class NanoHTTPDMain extends NanoHTTPD
{
	public NanoHTTPDMain() throws IOException
	{
		super(8080);
		start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);  
        System.out.println("\n nano http web server is running! Point your browers to http://localhost:8080/ \n");  
	}

	public static void main(String[] args)
	{
		try
		{
			new NanoHTTPDMain();	
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
