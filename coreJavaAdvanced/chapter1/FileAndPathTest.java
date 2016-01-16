package com.corejava.chapter1;

import static java.lang.System.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.util.List;

public class FileAndPathTest
{
	private static String workDir = System.getProperty("user.dir") + File.separator + "src" + File.separator +  
			"com" + File.separator + "corejava" + File.separator +  "chapter1" + File.separator;
	public static void main(String[] args) throws IOException
	{		
		FileSystem fs = FileSystems.newFileSystem(Paths.get(workDir, "testZip.zip"), null);
		Files.walkFileTree(fs.getPath("/"), new SimpleFileVisitor<Path>()
	    {
	        public FileVisitResult visitFile(Path file , BasicFileAttributes attrs)
	        {
	            out.println(file);
	            return FileVisitResult.CONTINUE;
	        }
	    });
	}
	
	public static void main9(String[] args) throws IOException
	{
		Path path = Paths.get("E:", "tempdocument");
		Files.walkFileTree(path, new SimpleFileVisitor<Path>()
		{
			@Override
			public FileVisitResult visitFile(Path file,
					BasicFileAttributes attrs) throws IOException
			{
				out.println(file);
				return FileVisitResult.CONTINUE;
			}
			@Override
			public FileVisitResult preVisitDirectory(Path dir,
					BasicFileAttributes attrs) throws IOException
			{
				out.print("\npreVisitDirectory");
				return FileVisitResult.CONTINUE;
			}
			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc)
					throws IOException
			{
				out.print("\npostVisitDirectory");
				return FileVisitResult.CONTINUE;
			}
			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc)
					throws IOException
			{
				return FileVisitResult.CONTINUE;
			}
		});
	}
	
	public static void main8(String[] args) throws IOException
	{
		Path path = Paths.get("E:", "tempdocument");
		
		try(DirectoryStream<Path> entries = Files.newDirectoryStream(path))
		{
		    for(Path entry : entries)
		    {
		    	out.println(entry.getFileName());
		    }
		        
		}
		out.println("\n用 glob 模式来过滤(*.txt)");
		try(DirectoryStream<Path> entries = Files.newDirectoryStream(path, "*.txt"))
		{
		    for(Path entry : entries)
		    {
		    	out.println(entry.getFileName());
		    }
		        
		}
	}
	public static void main7(String[] args) throws IOException
	{
		Path path = Paths.get(workDir, "test.txt");
		// 静态方法list： 
		// exists + isHidden + isReadable + isWritable + isExecutable 
		// + isRegularFile + isDirectory + isSymbolicLink
		out.println("exists = " + Files.exists(path, LinkOption.NOFOLLOW_LINKS));
		out.println("isHidden = " + Files.isHidden(path));
		out.println("isReadable = " + Files.isReadable(path));
		out.println("isWritable = " + Files.isWritable(path));
		out.println("isExecutable = " + Files.isExecutable(path));
		out.println("isRegularFile = " + Files.isRegularFile(path));
		out.println("isDirectory = " + Files.isDirectory(path));
		out.println("isSymbolicLink = " + Files.isSymbolicLink(path));
		
		// 所有的文件系统都会报告一个基本属性集， 它们被封装在 BasicFileAttributes 接口中
		out.println("\nBasicFileAttributes as follows: ");
		BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
		out.println(attributes.isDirectory());
		out.println(attributes.isRegularFile());
		out.println(attributes.isSymbolicLink());		
		
		//如果你了解到文件系统兼容 POSIX， 可以获取一个 PosiXFileAttributes 实例：
		out.println("\nPosixFileAttributes as follows:");
		PosixFileAttributes attrs = Files.readAttributes(path, PosixFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
		out.println(attrs.isDirectory()); 
		out.println(attrs.creationTime());
		out.println(attrs.lastAccessTime());
		out.println(attrs.group());
		out.println(attrs.owner());
	}
	
	public static void main6(String[] args) throws IOException
	{
		Path path = Paths.get("E:", "tempdocument", "xiaotang");
		Files.deleteIfExists(path);
		//创建目录
		Files.createDirectory(path);
		out.println("create dir over !");
		
		path = Paths.get("E:", "tempdocument", "xiaoxiaotang", "xiaoxiaoxiaotang");
		Files.deleteIfExists(path);
		//迭代创建中间目录
		Files.createDirectories(path);
		out.println("iteratively create dirs over !");
		
		// 创建空文件
		path = Paths.get("E:", "tempdocument", "tangtang.txt");
		Files.deleteIfExists(path);
		Files.createFile(path);
		out.println(" create new file over !");

		// 有些便捷方法用来在给定位置或系统指定位置创建临时文件或 临时目录：
		Path newPath = Files.createTempFile(path.getParent(), null, null);
		/*Path newPath = Files.createTempFile( prefix, suffix);
		Path newPath = Files.createTempFile(dir, prefix);
		Path newPath = Files.createTempFile(prefix);*/
		out.println(newPath.getFileName());
		
		newPath = Files.createTempFile(path.getParent(), "a", "b");
		out.println(newPath.getFileName());
		
		newPath = Files.createTempFile(path.getParent(), "a", "b.txt");
		out.println(newPath.getFileName());
	}
	
	public static void main5(String[] args) throws IOException
	{
		Path source = Paths.get(workDir, "test.txt");
		Path destination = Paths.get("E:", "tempdocument", "copy.txt");
		
		// 如果要删除的文件不存在， delete 方法就会抛出异常；使用 deleteIfExists 不会抛出异常
		Files.deleteIfExists(destination);
		
		// 复制文件：Files.copy(fromPath, toPath);
		// 1）如果想要覆盖已有的目标路径， 可以使用 REPLACE_EXISTING 选项；
		// 2）如果想要复制文件的属性， 可以使用 COPY_ATTRIBUTES ；
		Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
		out.println("copy over! ");
		
		// 移动文件 （复制并删除源文件）
		source = Paths.get(workDir, "test.dat");
		destination = Paths.get("E:", "tempdocument", source.getFileName().toString());
		
		// 将移动操作定义为 原子性的（要么全部成功， 要么就不成功）
		Files.move(source, destination, StandardCopyOption.ATOMIC_MOVE);
		out.println("move over! ");
		
		Files.delete(source);
	}
	
	public static void main4(String[] args) throws IOException
	{
		Path absolute = Paths.get(workDir, "output.txt");
		// 用下面的方式很容易的读取文件的所有内容：
		byte[] bytes = Files.readAllBytes(absolute);
		
		//向指定文件追加内容
		Files.write(absolute.resolveSibling("test.txt"), bytes, StandardOpenOption.APPEND);
		out.print("\n writing over !");
		
		//如果希望 将文件当做行序列输入
		List<String> lines = Files.readAllLines(absolute);
		// 将一行集合写出到文件中
		Files.write(absolute.resolveSibling("test.txt"), lines);
	}
	
	public static void main3(String[] args) throws IOException
	{
		Path absolute = Paths.get(workDir, "output.txt");
		// 用下面的方式很容易的读取文件的所有内容：
		byte[] bytes = Files.readAllBytes(absolute);
		for (byte b : bytes)
		{
			out.print((char)b);
		}
		
		// 如果想将文件当做字符串输入
		String content = new String(bytes, Charset.defaultCharset());
		out.print("\n" + content + "\n");
		
		//如果希望 将文件当做行序列输入
		List<String> lines = Files.readAllLines(absolute);
		for (String str : lines)
		{
			out.println(str);
		}
		
		//如果希望写出一个字符串到文件中
		Files.write(absolute.resolveSibling("test.txt"), content.getBytes());
		out.print("\n writing over !");
	}
	public static void main2(String[] args) throws IOException
	{
//		Path absolute = Paths.get(workDir, "output.txt");
		Path absolute = Paths.get(workDir);
		out.println("absolute path = " + absolute.toString());
		
		Path child1 =  absolute.resolve("output.txt");
		out.println("child1 path = " + child1.toString());
		
		Path child2 =  absolute.resolve("E:\\");
		out.println("child2 path = " + child2.toString());
		
		Path brother1 =  absolute.resolveSibling("xiaotang");
		out.println("brother1 path = " + brother1.toString());
		
	}
	
	public static void main1(String[] args) throws IOException
	{
		Path absolute = Paths.get(workDir, "output.txt");
		out.println("root = " + absolute.getRoot());  
        out.println("full path = " + absolute.toString());  
        out.println("file name = " + absolute.getFileName());  
        // 8 个路径分隔符'\\'
        out.println("name count = " + absolute.getNameCount());  
        out.println("sub path = " + absolute.subpath(0, 7)); 
        
        out.println("\n ===relative path as follows ");
        Path relative = Paths.get("myprog", "cay");
        out.println(relative.getRoot());  
        out.println(relative.toString());  
        out.println(relative.getFileName());  
	}
}
