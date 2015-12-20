package com.corejava.chapter14_4;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class FutureTest
{
   public static void main(String[] args)
   {
      Scanner in = new Scanner(System.in);
      System.out.print("Enter base directory (e.g. /usr/local/jdk5.0/src): ");
      String directory = in.nextLine();
      System.out.print("Enter keyword (e.g. volatile): ");
      String keyword = in.nextLine();

      MatchCounter counter = new MatchCounter(new File(directory), keyword);
      FutureTask<Integer> task = new FutureTask<>(counter);
      Thread t = new Thread(task);
      t.start();
      try
      {
         System.out.println(task.get() + " matching files.");
      }
      catch (ExecutionException e)
      {
         e.printStackTrace();
      }
      catch (InterruptedException e)
      {
      }
   }
}

class MatchCounter implements Callable<Integer>
{
   private File directory;
   private String keyword;
   private int count;

   public MatchCounter(File directory, String keyword)
   {
      this.directory = directory;
      this.keyword = keyword;
   }

   public Integer call()
   {
      count = 0;
      try
      {
         File[] files = directory.listFiles();
         List<Future<Integer>> results = new ArrayList<>();

         for (File file : files)
            if (file.isDirectory())
            {
               MatchCounter counter = new MatchCounter(file, keyword);
               FutureTask<Integer> task = new FutureTask<>(counter);
               results.add(task);
               Thread t = new Thread(task);
               t.start();// this is a recursive case
            }
            else
            {
               if (search(file)) count++;
            }

         for (Future<Integer> result : results)
            try
            {
               count += result.get();
            }
            catch (ExecutionException e)
            {
               e.printStackTrace();
            }
      }
      catch (InterruptedException e)
      {
      }
      return count;
   }

   public boolean search(File file)
   {
      try
      {
         try (Scanner in = new Scanner(file))
         {
            boolean found = false;
            while (!found && in.hasNextLine())
            {
               String line = in.nextLine();
               if (line.contains(keyword)) found = true;
            }
            return found;
         }
      }
      catch (IOException e)
      {
         return false;
      }
   }
}
