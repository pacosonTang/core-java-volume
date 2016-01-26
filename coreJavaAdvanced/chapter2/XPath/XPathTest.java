package com.corejava.chapter2;

import static java.lang.System.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XPathTest
{
	private static String workDir = System.getProperty("user.dir") + File.separator + "src" + File.separator +  
			"com" + File.separator + "corejava" + File.separator +  "chapter2" + File.separator;
	public static void main(String[] args) throws Exception
	{
		// build doc starts.
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(workDir + "XPathTest.xml"));
		// build over.
		
		// build XPath starts.
		XPathFactory xpf = XPathFactory.newInstance();
		XPath path = xpf.newXPath();
		// build over.		
		
		NodeList children = (NodeList) path.evaluate("bookstore/book", doc, XPathConstants.NODESET);
		for (int i = 1; i <= children.getLength(); i++)
		{	
			out.println(path.evaluate("bookstore/book/[" + i + "]", doc, XPathConstants.NODE));			
		}		
	}
}











