package com.corejava.chapter1;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

import static java.lang.System.*;

public class CharsetTest
{
	public static void main(String[] args)
	{
		Charset charset = Charset.forName("ISO-8859-1");
		Set<String> aliases = charset.aliases();
		for(String alias : aliases)
		    out.println(alias);
		
		Map<String, Charset> charsets = Charset.availableCharsets();
		for(String name : charsets.keySet())
		    out.println(name);
	}
}
