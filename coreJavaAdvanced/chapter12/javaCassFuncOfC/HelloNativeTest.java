package com.corejava.chapter12;
 
public class HelloNativeTest
{  
   public static void main(String[] args)
   {  
      HelloNative.greeting();
   }

   static
   {  
      System.loadLibrary("HelloNative");
   }
}
