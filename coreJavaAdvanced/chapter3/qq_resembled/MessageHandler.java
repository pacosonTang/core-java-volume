package com.communication.qq;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MessageHandler {
}

// receive msg.
class ReceiveHandler implements Runnable {
	private Socket socket;
	private Role role;

	public ReceiveHandler(Socket socket, Role role) {
		this.socket = socket;
		this.role = role;		
	}

	public void run() {
		try {
			System.out.println(role + " executes ReceiveHandler.run method.");
			InputStream is = socket.getInputStream();
			Scanner in = new Scanner(is);
			
			// echo client input
			boolean done = false;
			while (!done && in.hasNextLine()) {
				String line = in.nextLine();
				System.out.println(line);

				if (line.trim().equalsIgnoreCase("bye")) {
					done = true;
				}
			}
			// close relative instance.
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}

// send msg.
class SendHandler implements Runnable {
	private Socket socket;
	private Role role;

	public SendHandler(Socket socket, Role role) {
		this.socket = socket;
		this.role = role;		
	}

	public void run() {
		PrintWriter pw = null;
		try {
			System.out.println(role + " executes SendHandler.run method.");
			Scanner s = new Scanner(System.in);
			OutputStream os = socket.getOutputStream();
			pw = new PrintWriter(os, true); // autoFlush=true.
			
			if (role.equals(Role.SERVER)) {
				pw.println("from server: you've connected with the server.");
			}

			while (s.hasNext()) {
				String line = s.nextLine();
				pw.println("from " + role + ": " + line);
			}
			pw.println("connection closed successfully. bye!");
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
}