package com.xmpp.client;

import java.util.Scanner;

import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.chat.Chat;

// 创建发送msg 线程.
public class MessageHandler {
	private Chat chat;
	
	public MessageHandler(Chat chat) {
		this.chat = chat;
	}

	class Sender implements Runnable{
		/*public Sender(Chat chat) {
			MessageHandler.this.chat = chat;
		}*/
		public Sender() {}
		
		@Override
		public void run() {
			Scanner scanner = new Scanner(System.in);
			while(scanner.hasNext()) {
				String line = scanner.nextLine();
				try {
					chat.sendMessage(line);
				} catch (NotConnectedException e) {
					e.printStackTrace();
					break;
				}
			}
		} 
	}
	
	class Receiver {
		
	}
}