package com.corejava.chapter10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptTest
{
	public static void main(String[] args) throws ScriptException, FileNotFoundException
	{
		// 脚本引擎管理器
		//ScriptEngineManager manager = new ScriptEngineManager();
		/*List<ScriptEngineFactory> list = manager.getEngineFactories();
		for(ScriptEngineFactory engine : list)
		{
			System.out.println(engine.getEngineName());
		}*/
		
		// 脚本引擎管理器
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		
		// 向引擎中添加变量绑定
		engine.eval("n=1728");
		Object result = engine.eval("n+1"); // 1729.0
		System.out.println(result);
		
		// 获取由脚本语句绑定的变量 
		engine.put("k", 100);
		result = engine.eval("k+1"); // 101
		System.out.println(result);
		
		// 除了向引擎或全局作用域添加绑定外，还可以将绑定收集到一个类型为 Bindings 的对象中， 
		// 然后将其传递给 eval 方法：
		Bindings scope = engine.createBindings();
		scope.put("a", "hello, ");
		result = engine.eval("a+ 'world!'", scope); // hello, world!
		System.out.println(result);
		
		// 重定向输入和输出
		// 任何用 js 的 print 和 println函数产生的输出都会发送到 writer
		StringWriter writer = new StringWriter();
		engine.getContext().setWriter(new PrintWriter(
				new File("com/corejava/chapter10/output.txt")));
		engine.eval("print('hello world, this msg is from java app')");
	}
}














