package com.corejava.chapter11.server;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

/**
 * This class is the implementation for the remote Warehouse interface.
 * @version 1.01 2012-01-26
 * @author Cay Horstmann
 */
// 这个WarehouseImpl类是远程方法调用的目标，因为它继承自UnicastRemoteObject，
// 这个类的构造器使得它的对象可供远程访问; 
public class WarehouseImpl extends UnicastRemoteObject implements Warehouse
{
   private Map<String, Double> prices;

   public WarehouseImpl() throws RemoteException
   {
      prices = new HashMap<>();
      prices.put("A", 24.95);
      prices.put("B", 49.95);
   }

   public double getPrice(String description) throws RemoteException
   {
      Double price = prices.get(description);
      return price == null ? 0 : price;
   }
}
