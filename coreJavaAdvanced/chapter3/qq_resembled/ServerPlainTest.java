package com.communication.qq;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerPlainTest {
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(8189);
			System.out.println("the server has startuped, waiting for connections.");
			while (true) { // accept multiple clients connection request.
				Socket s = ss.accept();
				System.out.println("a client has connected successfully.");
				new Thread(new ReceiveHandler(s, Role.SERVER)).start();
				new Thread(new SendHandler(s, Role.SERVER)).start();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}