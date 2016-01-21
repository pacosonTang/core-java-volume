package com.corejava.chapter2;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import static java.lang.System.*;

public class ParseXMLTest
{
	private static String workDir = System.getProperty("user.dir") + File.separator + "src" + File.separator +  
			"com" + File.separator + "corejava" + File.separator +  "chapter2" + File.separator;
	
	public static void main(String[] args) throws Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		factory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		File f = new File(workDir + "XSDTest.xml");
		Document doc = builder.parse(f);
		// 通过匿名内部类来设置文档构建器 的错误处理器
		builder.setErrorHandler(new ErrorHandler()
		{
			@Override
			public void warning(SAXParseException exception) throws SAXException
			{
				out.println("warning");
			}
			
			@Override
			public void fatalError(SAXParseException exception) throws SAXException
			{
				out.println("fatalError");
			}
			
			@Override
			public void error(SAXParseException exception) throws SAXException
			{
				out.println("error");
			}
		});
		Element root = doc.getDocumentElement();
		 
		NodeList children = root.getChildNodes();
		Element e1 = (Element) children.item(0);
		Element e2 = (Element) children.item(1);
		out.println(e1.getNodeName() + ", " + e1.getTextContent());
		out.println(e2.getNodeName() + ", " + e2.getTextContent());
		out.print(e1.getAttribute("tangrong"));// 居然没有抛异常
	}
	
	public static void main2(String[] args) throws Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		factory.setValidating(true);
		factory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		File f = new File(workDir + "test1.xml");
		Document doc = builder.parse(f);
		// 通过匿名内部类来设置文档构建器 的错误处理器
		builder.setErrorHandler(new ErrorHandler()
		{
			@Override
			public void warning(SAXParseException exception) throws SAXException
			{
				out.println("warning");
			}
			
			@Override
			public void fatalError(SAXParseException exception) throws SAXException
			{
				out.println("fatalError");
			}
			
			@Override
			public void error(SAXParseException exception) throws SAXException
			{
				out.println("error");
			}
		});
		Element root = doc.getDocumentElement();
		 
		NodeList children = root.getChildNodes();
		Element e1 = (Element) children.item(0);
		Element e2 = (Element) children.item(1);
		out.println(e1.getNodeName() + ", " + e1.getTextContent());
		out.println(e2.getNodeName() + ", " + e2.getTextContent());
		out.print(e1.getAttribute("tangrong"));// 居然没有抛异常
	}
	
	public static void main1(String[] args) throws Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		File f = new File(workDir + "test1.xml");
		Document doc = builder.parse(f);
		Element root = doc.getDocumentElement();
		//out.print(root.getNodeName()); // font
		out.println(root.getTagName() + " = " + root.getAttribute("name"));
		out.println(root.getTagName() + " = " + root.getAttribute("size"));
		// font = ab
		// font = 36 pt
		
		// false access to font start
		NodeList children = root.getChildNodes();
		
		Element e1 = (Element) children.item(0);
		Element e2 = (Element) children.item(1);
		out.println(e1.getNodeName() + ", " + e1.getTextContent());
		out.println(e2.getNodeName() + ", " + e2.getTextContent());
		// false over
		
		/* true access to font start
		for (int i = 0; i < children.getLength(); i++)
		{
		    Node child = children.item(i);
		    if(child instanceof Element)
		    {
		    	Element childElement = (Element)child;
			    out.println(childElement.getTagName() + " = " + 
			    		childElement.getTextContent());
			    // name = abcdefg
			    // size = 36
		    }
		}
		true over*/
	}
}

























