package com.communication.qq;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerPlainTest {
	public static void main(String[] args) {
		try (ServerSocket ss = new ServerSocket(8189)) {
			System.out.println("the server has startuped, waiting for connections.");
			while (true) { // 接收多个client 连接请求.
				Socket s = ss.accept();
				new Thread(new ReceiveHandler(s, Role.SERVER)).start();
				new Thread(new SendHandler(s, Role.SERVER)).start();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

// receive msg.
class ReceiveHandler implements Runnable {
	private Socket incoming;
	private Role role;
	
	public ReceiveHandler(Socket s, Role role) {
		incoming = s;
		this.role = role;
	}

	public void run() {
		try {
			try {
				InputStream inStream = incoming.getInputStream();
				OutputStream outStream = incoming.getOutputStream();

				Scanner in = new Scanner(inStream);
				PrintWriter out = new PrintWriter(outStream, true /* autoFlush */);
				
				// echo client input
				boolean done = false;
				while (!done && in.hasNextLine()) {
					String line = in.nextLine();
					if(role.equals(Role.SERVER)) {
						System.out.println("from client: " + line);
					}
					if (line.trim().equalsIgnoreCase("bye")) {
						done = true;
					}
				}
				out.println("connection closed successfully. bye!");
				// close relative instance.
				in.close();
				inStream.close();
				outStream.close();
			} finally {
				incoming.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

// send msg.
class SendHandler implements Runnable {
	private Socket socket;
	private Role role;
	
	public SendHandler(Socket s, Role role) {
		socket = s;
		this.role = role;
	}

	public void run() {
		try {
			 Scanner s = new Scanner(System.in);
			 OutputStream os = socket.getOutputStream();
			 PrintWriter pw = new PrintWriter(os, true); // autoFlush=true.
			 if(role.equals(Role.SERVER)) {
				 pw.println("from server: you've connected with the server.");
			 }
				 
			 while(s.hasNext()) {
				 String line = s.nextLine();
				 pw.println("from " + role + ": " + line);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

