package com.corejava.chapter2;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

public class GenerateXMLTest
{
	private static String workDir = System.getProperty("user.dir") + File.separator + "src" + File.separator +  
			"com" + File.separator + "corejava" + File.separator +  "chapter2" + File.separator;
	
	// 生成 XML 文档
	public static void main(String[] args) throws Exception
	{
		// 3rd method: adapt StAX parser
		// 开始进行转换
		XMLOutputFactory factory =  XMLOutputFactory.newInstance();
		FileOutputStream out = new FileOutputStream(new File(workDir+"generateStAX.xml"));
		XMLStreamWriter writer = factory.createXMLStreamWriter(out);
		
		//要产生XML 文件头， 需要调用
		writer.writeStartDocument();	
		
		writer.writeStartDocument("font");		
		// 添加属性调用
		writer.writeAttribute("name" , "ab");
		writer.writeAttribute("size" , "36 pt");
		
		// 再次调用 writeStartElement 添加新的子节点
		writer.writeStartDocument("name");
		// 用下面 的语句写出字符
		writer.writeCharacters("abcdefg");
		writer.writeEndElement();
		
		writer.writeStartDocument("size");
		writer.writeAttribute("unit", "pt");
		writer.writeCharacters("36");
		writer.writeEndElement();
		
		// 添加完所有子节点后， 调用 writeEndElement
		writer.writeEndElement();		
		writer.writeEndDocument();
		System.out.println("generating over!");
	}
	
	// 生成 XML 文档
	public static void main2(String[] args) throws Exception
	{
		//step1: 要建立一颗DOM树，可以从一个空文档开始
		Document doc = DocumentBuilderFactory.newInstance().
												newDocumentBuilder().newDocument();
		
		//step2: 使用Document类 的 createElement 方法可以构建文档里的元素
		Element root = doc.createElement("font");
		// 需要设置元素属性， 只需要调用 Element类的 setAttribute方法
		root.setAttribute("name", "ab");
		root.setAttribute("size", "36 pt");
		
		Element name = doc.createElement("name");	
		// step3: 使用 createTextNode 方法可以构建文本节点：
		name.appendChild(doc.createTextNode("abcdefg"));
		
		Element size = doc.createElement("size");
		size.setAttribute("unit", "pt");
		size.appendChild(doc.createTextNode("36"));
		
		// step4: 给文档添加根元素，给父节点添加子节点
		root.appendChild(name);
		root.appendChild(size);
		doc.appendChild(root);
		
		// 2nd method: adapt LSSerializer interface
		// step5: 开始进行转换
		DOMImplementation impl = doc.getImplementation();
		DOMImplementationLS imlpLS = (DOMImplementationLS)impl.getFeature("LS", "3.0");
		LSSerializer ser = imlpLS.createLSSerializer();
		
		// step5.2  如果需要空格和换行，可以设置下面的标志
		ser.getDomConfig().setParameter("format-pretty-print", true);
		// 将 文档转换为 字符串
		//String str = ser.writeToString(doc);
		
		// step5.3  如果想要将输出直接写入到文件中， 则需要一个 LSOutput：
		LSOutput out = imlpLS.createLSOutput();
		out.setEncoding("UTF-8");
		out.setByteStream(new FileOutputStream(new File(workDir + "generate2.xml")));		
		ser.write(doc, out);
		
		System.out.println("generate xml over");
	}	
	
	// 生成 XML 文档
	public static void main1(String[] args) throws Exception
	{
		//step1: 要建立一颗DOM树，可以从一个空文档开始
		Document doc = DocumentBuilderFactory.newInstance().
												newDocumentBuilder().newDocument();
		
		//step2: 使用Document类 的 createElement 方法可以构建文档里的元素
		Element root = doc.createElement("font");
		// 需要设置元素属性， 只需要调用 Element类的 setAttribute方法
		root.setAttribute("name", "ab");
		root.setAttribute("size", "36 pt");
		
		Element name = doc.createElement("name");	
		// step3: 使用 createTextNode 方法可以构建文本节点：
		name.appendChild(doc.createTextNode("abcdefg"));
		
		Element size = doc.createElement("size");
		size.setAttribute("unit", "pt");
		size.appendChild(doc.createTextNode("36"));
		
		// step4: 给文档添加根元素，给父节点添加子节点
		root.appendChild(name);
		root.appendChild(size);
		doc.appendChild(root);
		
		Transformer t = TransformerFactory.newInstance().newTransformer();
		t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, workDir+"myfont.dtd");
		t.setOutputProperty(OutputKeys.INDENT, "yes");
		t.setOutputProperty(OutputKeys.METHOD, "xml");
		t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(
				new File(workDir+"generate.xml"))));
		System.out.println("generate xml over");
	}	
}



















