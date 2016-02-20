package com.corejava.chapter11.server;

import java.rmi.*;

/**
   The remote interface for a simple warehouse.
   @version 1.0 2007-10-09
   @author Cay Horstmann
*/
// 远程对象的接口必须扩展Remote接口
public interface Warehouse extends Remote
{  
   double getPrice(String description) throws RemoteException;
}
