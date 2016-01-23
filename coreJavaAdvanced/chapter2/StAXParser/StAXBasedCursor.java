package com.corejava.chapter2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public class StAXBasedCursor
{
	private static String workDir = System.getProperty("user.dir") + File.separator + "src" + File.separator +  
			"com" + File.separator + "corejava" + File.separator +  "chapter2" + File.separator;
	public static void main1(String[] args)
	{
		StaxCursorReader reader = new StaxCursorReader();
		
		String fileName = workDir + "employee.xml";
		List<Employee> list = reader.parseXML(fileName);
		for (Employee employee : list)
		{
			System.out.println(employee.toString());
		}
		System.out.println("successful reading over !");
	}
	public static void main(String[] args)
	{
		String fileName = workDir + "employeeCursor.xml";
        String rootElement = "Employee";
        StaxCursorWriter xmlStreamWriter = new StaxCursorWriter();
        Map<String,String> elementsMap = new HashMap<String, String>();
        elementsMap.put("id", "1");
        elementsMap.put("name", "Pankaj");
        elementsMap.put("age", "29");
        elementsMap.put("role", "Java Developer");
        elementsMap.put("gender", "Male");
        xmlStreamWriter.writeXML(fileName, rootElement, elementsMap);
        System.out.println("successful writing over !");
	}
}

class StaxCursorWriter
{
	public void writeXML(String fileName, String rootElement, Map<String, String> elementsMap)
	{
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		try
		{
			XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream(fileName), "UTF-8");
			// start writing xml file
			writer.writeStartDocument("UTF-8", "1.0");
			writer.writeCharacters("\n");
			writer.writeStartElement(rootElement);
			
			// write id as attribute
			writer.writeAttribute("id", elementsMap.get("id"));
			
			// write other elements
			writer.writeCharacters("\n\t");
			writer.writeStartElement("name");
			writer.writeCharacters(elementsMap.get("name"));			
			writer.writeEndElement();
			
			// write other elements
			writer.writeCharacters("\n\t");
			writer.writeStartElement("age");
			writer.writeCharacters(elementsMap.get("age"));
			writer.writeEndElement();
			
			// write other elements
			writer.writeCharacters("\n\t");
			writer.writeStartElement("gender");
			writer.writeCharacters(elementsMap.get("gender"));
			writer.writeEndElement();
			
			// write other elements
			writer.writeCharacters("\n\t");
			writer.writeStartElement("role");
			writer.writeCharacters(elementsMap.get("role"));
			writer.writeEndElement();
			
			// write end tag of Employee element
			writer.writeCharacters("\n");
			writer.writeEndElement();
			
			// write end document
			writer.writeEndDocument();
			
			// flush data to file and close writer
			writer.flush();
			writer.close();
		} 
		catch (XMLStreamException | FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}

class StaxCursorReader
{
	private boolean name;
	private boolean age;
	private boolean gender;
	private boolean role;
	
	public StaxCursorReader(){}
	public List<Employee> parseXML(String fileName) 
	{
        List<Employee> empList = new ArrayList<>();
        Employee emp = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try 
        {
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new FileInputStream(fileName));
            int event = xmlStreamReader.getEventType();
            while(true)
            {
                switch(event) 
                {
                case XMLStreamConstants.START_ELEMENT:
                    if(xmlStreamReader.getLocalName().equals("Employee"))
                    {
                        emp = new Employee();
                        emp.setId(Integer.parseInt(xmlStreamReader.getAttributeValue(0)));
                    }
                    else if(xmlStreamReader.getLocalName().equals("name"))
                    {
                        name=true;
                    }
                    else if(xmlStreamReader.getLocalName().equals("age"))
                    {
                        age=true;
                    }
                    else if(xmlStreamReader.getLocalName().equals("role"))
                    {
                        role=true;
                    }
                    else if(xmlStreamReader.getLocalName().equals("gender"))
                    {
                        gender=true;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    if(name)
                    {
                        emp.setName(xmlStreamReader.getText());
                        name=false;
                    }
                    else if(age)
                    {
                        emp.setAge(Integer.parseInt(xmlStreamReader.getText()));
                        age=false;
                    }
                    else if(gender)
                    {
                        emp.setGender(xmlStreamReader.getText());
                        gender=false;
                    }
                    else if(role)
                    {
                        emp.setRole(xmlStreamReader.getText());
                        role=false;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if(xmlStreamReader.getLocalName().equals("Employee"))
                    {
                        empList.add(emp);
                    }
                    break;
                }
                if (!xmlStreamReader.hasNext())
                {
                    break;
                }
              event = xmlStreamReader.next();
            }
        }
        catch (FileNotFoundException | XMLStreamException e) 
        {
            e.printStackTrace();
        }
        return empList;
    }
}