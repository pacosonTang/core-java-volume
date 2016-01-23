package com.corejava.chapter2;

import static java.lang.System.out;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StAXBasedIterator
{
	private static String workDir = System.getProperty("user.dir") + File.separator + "src" + File.separator +  
			"com" + File.separator + "corejava" + File.separator +  "chapter2" + File.separator;
	
	public static void main(String[] args)
	{
		String filename = workDir + "generateEmployee.xml";
		String rootElement = "Employee";
		StaxXMLWriter writer = new StaxXMLWriter();
		Map<String, String> elementsMap = new HashMap<>();
		elementsMap.put("name", "xiaotangtang");
		elementsMap.put("age", "25");
		elementsMap.put("role", "java developer");
		elementsMap.put("gender", "male");
		
		writer.writeXML(filename, rootElement, elementsMap);
		System.out.println("successful writing over !");
	}
	
	public static void main2(String[] args)
	{
		StaxXMLReader XMLReader = new StaxXMLReader();
		List<Employee> list = XMLReader.parseXML(workDir+"employee.xml"); 
		for(Employee e : list)
		{
			System.out.println(e.toString());
		}
		System.out.println("successful reading over !");
	}
}
class StaxXMLWriter
{
	public StaxXMLWriter(){}
	public void writeXML(String filename, String rootElement, Map<String, String> elementsMap)
	{
		XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
		try
		{
			XMLEventWriter writer = xmlOutputFactory.createXMLEventWriter(new FileOutputStream(filename));
			XMLEventFactory eventFactory = XMLEventFactory.newInstance();
			XMLEvent newline = eventFactory.createDTD("\n");
			StartDocument startdoc = eventFactory.createStartDocument();//1st line
			writer.add(startdoc);
			writer.add(newline);// new line
			StartElement configStartElement = eventFactory.createStartElement("", "", rootElement);// <Employee>
			writer.add(configStartElement);
			writer.add(newline);
			// write the element nodes
			Set<String> elementNodes = elementsMap.keySet();
			for(String key : elementNodes)
			{
				createNode(writer, key, elementsMap.get(key));
			}
			
			writer.add(eventFactory.createEndElement("", "", rootElement)); // </Employee>
			writer.add(newline);
			writer.add(eventFactory.createEndDocument());
			writer.flush();
			writer.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void createNode(XMLEventWriter writer, String element, String value) throws XMLStreamException
	{
		XMLEventFactory factory = XMLEventFactory.newInstance(); 
		XMLEvent newline = factory.createDTD("\n"); // new line
		XMLEvent indent = factory.createDTD("\t"); // indent
		// create start node
		StartElement startElement = factory.createStartElement("", "", element);
		writer.add(indent);
		writer.add(startElement); // <role>
		// create content
		Characters characters = factory.createCharacters(value); 
		writer.add(characters); // java developer
		// create end node
		EndElement endElement = factory.createEndElement("", "", element);
		writer.add(endElement); // </role>
		
		writer.add(newline); // new line 
	}
}

class StaxXMLReader
{
	public List<Employee> parseXML(String filename)
	{
		List<Employee> list = new ArrayList<>();
		Employee employee = null;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		try
		{
			XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(filename));
			while(reader.hasNext())
			{
				XMLEvent event = reader.nextEvent();
				if(event.isStartElement())
				{
					StartElement element = event.asStartElement();
					if(element.getName().getLocalPart().equals("Employee"))
					{
						employee = new Employee();
						Attribute id = element.getAttributeByName(new QName("id"));
						if(id != null)
						{
							employee.setId(Integer.parseInt(id.getValue()));
						}
					}
					else if(element.getName().getLocalPart().equals("age"))
					{
						event = reader.nextEvent();
						employee.setAge(Integer.parseInt(event.asCharacters().getData()));
					}
					else if(element.getName().getLocalPart().equals("name"))
					{
						event = reader.nextEvent();
						employee.setName(event.asCharacters().getData());
					}
					else if(element.getName().getLocalPart().equals("gender"))
					{
						event = reader.nextEvent();
						employee.setGender(event.asCharacters().getData());
					}
					else if(element.getName().getLocalPart().equals("role"))
					{
						event = reader.nextEvent();
						employee.setRole(event.asCharacters().getData());
					}
				}
				else if(event.isEndElement())
				{
					EndElement element = event.asEndElement();
					if(element.getName().getLocalPart().equals("Employee"))
					{
						list.add(employee);
					}
				}
			}
		}
		catch(FileNotFoundException | XMLStreamException e)
		{
			e.printStackTrace();
		}
		return list;
	}
}