package com.communication.qq;

import java.net.Socket;

public class ClientPlainTest {
	public static void main(String[] args) {
		Socket s = null;
		try {
			s = new Socket("localhost", 8189);
			System.out.println("connection with the server successfully.");
			new Thread(new ReceiveHandler(s, Role.CLIENT)).start();
			new Thread(new SendHandler(s, Role.CLIENT)).start();
		} catch (Exception e) {
			System.out.println(e);
		} 
	}
}
