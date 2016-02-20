package com.corejava.chapter11.transfer.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Warehouse extends Remote
{
	double getPrice(String desc) throws RemoteException;
	Product getProduct(List<String> keys) throws RemoteException;
}
