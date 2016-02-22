package com.corejava.chapter10;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class SimpleGreeter
{
	public static void main(String[] args) throws FileNotFoundException, ScriptException
	{
		// 脚本引擎管理器
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		
		engine.eval(new FileReader("com/corejava/chapter10/test.js"));
		Object goodbyeGreeter = engine.eval("new SimpleGreeter('goodbye')");
		Greeter g = ((Invocable)engine).getInterface(goodbyeGreeter, Greeter.class);
		String result = g.greet("World");
		System.out.println(result);// goodbye, World
	}
}
