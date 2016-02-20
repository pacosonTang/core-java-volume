package com.corejava.chapter11.transfer.client;

import java.rmi.*;
import java.util.*;

import javax.naming.*;

import com.corejava.chapter11.transfer.server.Product;
import com.corejava.chapter11.transfer.server.Warehouse;

public class WarehouseClient
{
   public static void main(String[] args) throws NamingException, RemoteException
   {
      //System.setProperty("java.security.policy", "client.policy");
      //System.setSecurityManager(new SecurityManager());
      Context namingContext = new InitialContext();
      
      System.out.print("RMI registry bindings: ");
      
      NamingEnumeration<NameClassPair> e = namingContext.list("rmi://localhost:1099/");
      while (e.hasMore())
         System.out.println(e.next().getName());
      
      String url = "rmi://localhost:1099/centralWarehouse";      
      Warehouse centralWarehouse = (Warehouse) namingContext.lookup(url);
      
      Scanner in = new Scanner(System.in);
      System.out.print("Enter keywords: ");
      List<String> keywords = Arrays.asList(in.nextLine().split("\\s+"));
      Product prod = centralWarehouse.getProduct(keywords);
      
      System.out.println(prod.getDesc() + ": " + prod.getPrice());
   }
}
