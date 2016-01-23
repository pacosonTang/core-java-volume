package com.corejava.chapter2.XSLT;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XSLTTestMultiple
{
	private static String workDir = System.getProperty("user.dir") + File.separator + "src" + File.separator +  
			"com" + File.separator + "corejava" + File.separator +  "chapter2" + File.separator + "XSLT" + File.separator;
	
	public static void main(String[] args)
	{
		try
		{
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer(new StreamSource(workDir + "employee.xsl"));
			transformer.transform(new StreamSource(workDir + "employee.xml"),
								new StreamResult(new FileOutputStream(workDir + "employee.html")));
			System.out.println("successful transforming over ! ");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main1(String[] args)
	{
		try
		{
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer(new StreamSource(workDir + "howto.xsl"));
			transformer.transform(new StreamSource(workDir + "howto.xml"),
								new StreamResult(new FileOutputStream(workDir + "howto.html")));
			System.out.println("successful transforming over ! ");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
