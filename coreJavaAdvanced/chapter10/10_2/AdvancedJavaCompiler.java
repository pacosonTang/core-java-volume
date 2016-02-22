package com.corejava.chapter10_2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class AdvancedJavaCompiler
{
	public static void main(String[] args) throws IOException
	{
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler(); // 返回java 编译器
		// DiagnosticCollector 是监听器的一种实现
		DiagnosticCollector<JavaFileObject>  diagnostics = new DiagnosticCollector<>(); 
		// java 文件管理器
		StandardJavaFileManager manager = compiler.getStandardFileManager(diagnostics, Locale.CHINA, Charset.forName("UTF-8")); 
		/* Iterable<? extends JavaFileObject> getJavaFileObjectsFromFiles(Iterable<? extends File> files) 
		   Iterable<? extends JavaFileObject> getJavaFileObjectsFromStrings(Iterable<String> names) */
		
		// 所要编译的源文件
		Iterable<? extends JavaFileObject> compilationUnits = manager.getJavaFileObjects("com/corejava/chapter10_2/StandardJavaFileManagerTest.java");
		CompilationTask task = compiler.getTask(null, manager, diagnostics, null, null, compilationUnits);
		// 如果没有编译警告和错误,这个call() 方法会编译所有的 compilationUnits 变量指定的文件,以及有依赖关系的可编译的文件. 
		Boolean suc = task.call();
		
		/* 只有当所有的编译单元都执行成功了,这个 call() 方法才返回 Boolean.TRUE  . 一旦有任何错误,这个方法就会返回 Boolean.FALSE .
		 * 在展示运行这个例子之前,让我们添加最后一个东西,DiagnosticListener , 或者更确切的说,  DiagnosticCollector .的实现类.
		 * 把这个监听器当作getTask()的第三个参数传递进去,你就可以在编译之后进行一些调式信息的查询了. */
		for(Diagnostic diagnostic : diagnostics.getDiagnostics())
		{
			System.console().printf(
				"Code: %s%n" + 
		        "Kind: %s%n" + 
		        "Position: %s%n" + 
		        "Start Position: %s%n" + 
		        "End Position: %s%n" + 
		        "Source: %s%n" + 
		        "Message:  %s%n", 
		        diagnostic.getCode(), diagnostic.getKind(), 
		        diagnostic.getPosition(), diagnostic.getStartPosition(), 
		        diagnostic.getEndPosition(), diagnostic.getSource(), 
		        diagnostic.getMessage(null));
		}
		manager.close();
		System.out.println("success : " + suc);
	}
}
